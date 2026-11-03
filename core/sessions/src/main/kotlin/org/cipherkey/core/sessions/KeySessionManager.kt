package org.cipherkey.core.sessions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.Argon2KeyDerivator
import org.cipherkey.core.crypto.Argon2Params
import java.util.Arrays
import kotlin.coroutines.CoroutineContext

/**
 * In-memory registry of unlocked per-contact session keys.
 *
 * Responsibilities:
 *  - derive keys (Argon2id) off the caller's thread;
 *  - hold at most ONE unlocked session per contact (the latest era);
 *  - enforce a fixed lifetime per session (no sliding renewal — the countdown
 *    starts at unlock);
 *  - wipe key material on lock, replacement and expiry;
 *  - guarantee isolation: a key derived for contact A is never returned for
 *    contact B (see KeySessionManagerTest).
 *
 * This class performs NO persistence and NO Keystore access; those live in
 * the data and security modules.
 */
class KeySessionManager(
    private val derivator: Argon2KeyDerivator = Argon2KeyDerivator(),
    private val timeSource: TimeSource = TimeSource.SYSTEM,
    private val deriveContext: CoroutineContext = Dispatchers.Default,
) {

    private val mutex = Mutex()
    private val active = HashMap<ContactId, Entry>()

    private class Entry(
        val sessionKey: SessionKey,
        val expiresAtMillis: Long,
    )

    /**
     * Derives a 256-bit key from the passphrase and the session's salt.
     * CPU/memory heavy: always called from [deriveContext] (Default
     * dispatcher unless overridden).
     */
    suspend fun deriveKey(
        passphrase: CharArray,
        salt: ByteArray,
        params: Argon2Params,
    ): ByteArray = withContext(deriveContext) {
        derivator.derive(passphrase, salt, params)
    }

    /**
     * Activates (or replaces) the unlocked session for a contact. The
     * previous session's key material, if any, is wiped first.
     *
     * The keyId and salt buffers are COPIED: a SessionKey owns its buffers so
     * that wiping on lock/expiry can never mutate storage-layer objects that
     * may still be aliased by the caller (defensive against DAOs or
     * repositories that hand out shared byte arrays).
     */
    suspend fun activate(
        contactId: ContactId,
        sessionId: SessionId,
        keyId: ByteArray,
        salt: ByteArray,
        params: Argon2Params,
        keyBytes: ByteArray,
        timeoutMillis: Long,
    ): SessionKey = mutex.withLock {
        val existing = active.remove(contactId)
        if (existing != null) wipe(existing.sessionKey)

        val sessionKey = SessionKey(
            sessionId = sessionId,
            contactId = contactId,
            keyId = keyId.copyOf(),
            salt = salt.copyOf(),
            params = params,
            keyBytes = keyBytes,
        )
        active[contactId] = Entry(sessionKey, timeSource.nowMillis() + timeoutMillis)
        sessionKey
    }

    /**
     * Returns the unlocked session for the contact, or null when no session
     * is active or it has expired (expired sessions are wiped and removed as
     * a side effect).
     */
    suspend fun activeSession(contactId: ContactId): SessionKey? = mutex.withLock {
        val entry = active[contactId] ?: return@withLock null
        if (entry.expiresAtMillis <= timeSource.nowMillis()) {
            active.remove(contactId)
            wipe(entry.sessionKey)
            return@withLock null
        }
        entry.sessionKey
    }

    /** Locks (wipes) the contact's session. Returns true if one existed. */
    suspend fun lock(contactId: ContactId): Boolean = mutex.withLock {
        val entry = active.remove(contactId) ?: return@withLock false
        wipe(entry.sessionKey)
        true
    }

    /** Locks every session. Returns the number of sessions wiped. */
    suspend fun lockAll(): Int = mutex.withLock {
        val count = active.size
        active.values.forEach { wipe(it.sessionKey) }
        active.clear()
        count
    }

    /** Snapshot of contacts with a live (non-expired) session. */
    suspend fun unlockedContactIds(): Set<ContactId> = mutex.withLock {
        sweepExpired()
        active.keys.toSet()
    }

    /** Absolute expiry timestamp of the contact's session, or null. */
    suspend fun expiryOf(contactId: ContactId): Long? = mutex.withLock {
        sweepExpired()
        active[contactId]?.expiresAtMillis
    }

    private fun wipe(sessionKey: SessionKey) {
        Arrays.fill(sessionKey.keyBytes, 0.toByte())
        sessionKey.keyId.fill(0) // public identifier, wiped for symmetry
    }

    private fun sweepExpired() {
        val now = timeSource.nowMillis()
        val expired = active.filterValues { it.expiresAtMillis <= now }.keys
        expired.forEach { id ->
            active.remove(id)?.let { wipe(it.sessionKey) }
        }
    }
}
