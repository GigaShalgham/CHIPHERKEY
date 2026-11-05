package org.cipherkey.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.DispatcherProvider
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.CryptoError
import org.cipherkey.core.crypto.CryptoProvider
import org.cipherkey.core.crypto.DecryptionContext
import org.cipherkey.core.crypto.EncryptionContext
import org.cipherkey.core.crypto.EncryptionMode
import org.cipherkey.core.crypto.EncryptionResult
import org.cipherkey.core.crypto.DecryptionResult
import org.cipherkey.core.keys.KeyProtector
import org.cipherkey.core.keys.KeyProtectionException
import org.cipherkey.core.messages.Ck1
import org.cipherkey.core.messages.Ck1Limits
import org.cipherkey.core.messages.MessageDecoder
import org.cipherkey.core.messages.MessageEncoder
import org.cipherkey.core.sessions.KeySessionManager
import org.cipherkey.security.policies.KeyPolicy

/** Outcome of unlocking a contact with a shared passphrase. */
sealed interface UnlockOutcome {
    data object Unlocked : UnlockOutcome
    data object AlreadyUnlocked : UnlockOutcome
    data object WrongPassphrase : UnlockOutcome

    /** The contact has no key era yet (create or import one first). */
    data object NoSession : UnlockOutcome

    /** The passphrase violates [KeyPolicy]. */
    data class Rejected(val reason: String) : UnlockOutcome

    /** Unexpected internal failure (e.g. Keystore unavailable). */
    data class Failed(val reason: String) : UnlockOutcome
}

/** Outcome of encrypting a draft for a contact. */
sealed interface EncryptOutcome {
    data class Encrypted(val envelope: String) : EncryptOutcome
    data object NeedsUnlock : EncryptOutcome
    data object NoSession : EncryptOutcome
    data object EmptyDraft : EncryptOutcome
    data class Failed(val error: CryptoError) : EncryptOutcome
}

/** Outcome of decrypting a CK1 envelope from the clipboard. */
sealed interface DecryptOutcome {
    data class Decrypted(
        val plaintext: String,
        val contactId: ContactId,
        val contactName: String,
    ) : DecryptOutcome

    /** The matching contact is not unlocked; prompt for the passphrase. */
    data class NeedsUnlock(val contactId: ContactId, val contactName: String) : DecryptOutcome

    /** The text is not a CK1 envelope at all. */
    data object NotAnEnvelope : DecryptOutcome

    /** The keyId is unknown on this device. */
    data object UnknownKey : DecryptOutcome

    /** The envelope belongs to an older key era of the contact (v0.1 limitation). */
    data object StaleKeyEra : DecryptOutcome

    /** Decoding or authentication failure (includes tampered ciphertext). */
    data class Failed(val error: CryptoError) : DecryptOutcome
}

/**
 * Supplies the configured session timeout. Abstracted so the facade is
 * unit-testable on the JVM without DataStore/Android.
 */
fun interface SessionTimeoutSource {
    suspend fun timeoutMillis(): Long
}

/**
 * Composition facade used by the keyboard and the management UI.
 *
 * Orchestrates: session material lookup (Room), key derivation and in-memory
 * session lifetime (KeySessionManager), at-rest wrapping / on-device
 * passphrase verification (KeyProtector) and the actual AEAD work
 * (CryptoProvider).
 *
 * This is the ONLY class allowed to move raw key bytes between layers; the
 * keyboard module never touches key material.
 */
class MessageCipher(
    private val contacts: ContactRepository,
    private val sessionKeys: SessionKeyRepository,
    private val sessions: KeySessionManager,
    private val protector: KeyProtector,
    private val provider: CryptoProvider,
    private val sessionTimeout: SessionTimeoutSource,
    private val dispatchers: DispatcherProvider,
) {

    /**
     * Unlocks the contact's current era with the shared passphrase.
     *
     * Verification path: if this device ever unlocked the era before, the
     * Keystore-wrapped verifier (v2: a digest, see [UnlockVerifier]; v1
     * legacy: the full key — migrated on first successful unlock) is
     * unwrapped and compared — a wrong passphrase is rejected WITHOUT being
     * activated. On the very first unlock on a device there is no verifier
     * yet (wrong passphrases are then detected at decryption time by AEAD
     * authentication).
     *
     * Error honesty (deep-scan finding 2): a [KeyProtectionException] from
     * the Keystore unwrap is an INFRASTRUCTURE failure (master key
     * invalidated, KeyStore unavailable) — the passphrase plays no part in
     * the unwrap, so it is reported as [UnlockOutcome.Failed], never as a
     * false "wrong passphrase".
     */
    suspend fun unlockContact(contactId: ContactId, passphrase: CharArray): UnlockOutcome =
        withContext(dispatchers.io) {
            KeyPolicy.passphraseProblem(passphrase)?.let { return@withContext UnlockOutcome.Rejected(it) }

            val era = sessionKeys.latestEra(contactId)
                ?: return@withContext UnlockOutcome.NoSession

            if (sessions.activeSession(contactId)?.sessionId == era.sessionId) {
                return@withContext UnlockOutcome.AlreadyUnlocked
            }

            val timeout = sessionTimeout.timeoutMillis()
            val derived = sessions.deriveKey(passphrase, era.salt, era.params)
            try {
                val wrapped = sessionKeys.wrappedKeyOf(era.sessionId)
                if (wrapped != null) {
                    val stored = protector.unwrap(wrapped, era.keyId)
                    val matched = UnlockVerifier.matches(stored, derived)
                    val legacy = UnlockVerifier.isLegacy(stored)
                    stored.fill(0) // the unwrapped verifier never outlives the check
                    if (!matched) {
                        derived.fill(0)
                        return@withContext UnlockOutcome.WrongPassphrase
                    }
                    if (legacy) {
                        // v1 -> v2 migration: replace the full-key verifier
                        // with the digest form. Best-effort, like the initial
                        // persist — verification is defence in depth.
                        runCatching {
                            sessionKeys.storeWrappedKey(
                                era.sessionId,
                                protector.wrap(UnlockVerifier.toStorable(derived), era.keyId),
                            )
                        }
                    }
                }
                sessions.activate(
                    contactId = contactId,
                    sessionId = era.sessionId,
                    keyId = era.keyId,
                    salt = era.salt,
                    params = era.params,
                    keyBytes = derived,
                    timeoutMillis = timeout,
                )
                if (wrapped == null) {
                    // First unlock on this device: persist the device-bound
                    // digest verifier so future wrong passphrases can be
                    // rejected at unlock time. Best-effort — verification is
                    // a defence in depth, not a functional requirement.
                    runCatching {
                        sessionKeys.storeWrappedKey(
                            era.sessionId,
                            protector.wrap(UnlockVerifier.toStorable(derived), era.keyId),
                        )
                    }
                }
                // activate() copies the buffer (deep-scan finding 1): the
                // session owns its key material from here on, so this local
                // reference can be wiped on every path.
                derived.fill(0)
                UnlockOutcome.Unlocked
            } catch (e: KeyProtectionException) {
                derived.fill(0)
                sessions.lock(contactId)
                UnlockOutcome.Failed("Keystore error — ${e.message ?: "key protection failed"}")
            } catch (e: Exception) {
                derived.fill(0)
                sessions.lock(contactId)
                UnlockOutcome.Failed(e.message ?: "unlock failed")
            }
        }

    /** Encrypts [plaintext] for the contact's current era and returns the CK1 envelope. */
    suspend fun encryptDraft(contactId: ContactId, plaintext: String): EncryptOutcome =
        withContext(dispatchers.io) {
            if (plaintext.isEmpty()) return@withContext EncryptOutcome.EmptyDraft

            val era = sessionKeys.latestEra(contactId)
                ?: return@withContext EncryptOutcome.NoSession

            val session = sessions.activeSession(contactId)
                ?: return@withContext EncryptOutcome.NeedsUnlock
            if (session.sessionId != era.sessionId) {
                // The active session belongs to an older era: refuse to send
                // under a stale key and ask for a fresh unlock.
                sessions.lock(contactId)
                return@withContext EncryptOutcome.NeedsUnlock
            }

            when (val result = provider.encrypt(
                plaintext = plaintext.toByteArray(Charsets.UTF_8),
                context = EncryptionContext(
                    mode = EncryptionMode.PASSPHRASE,
                    key = session.keyBytes,
                    keyId = era.keyId,
                    salt = era.salt,
                    kdfParams = era.params,
                    maxPlaintextBytes = Ck1Limits.MAX_PLAINTEXT_BYTES,
                ),
            )) {
                is EncryptionResult.Success -> EncryptOutcome.Encrypted(MessageEncoder.encode(result.message))
                is EncryptionResult.Failure -> EncryptOutcome.Failed(result.error)
            }
        }

    /** Decrypts a CK1 envelope, auto-resolving the contact from the envelope's keyId. */
    suspend fun decryptEnvelope(text: String): DecryptOutcome = withContext(dispatchers.io) {
        val trimmed = text.trim()
        if (!Ck1.isEnvelope(trimmed)) return@withContext DecryptOutcome.NotAnEnvelope

        val message = try {
            MessageDecoder.decode(trimmed)
        } catch (e: org.cipherkey.core.messages.Ck1FormatException) {
            return@withContext DecryptOutcome.Failed(MessageDecoder.errorOf(e))
        }

        val era = sessionKeys.eraByKeyId(message.keyId)
            ?: return@withContext DecryptOutcome.UnknownKey

        val latest = sessionKeys.latestEra(era.contactId)
        if (latest?.sessionId != era.sessionId) {
            return@withContext DecryptOutcome.StaleKeyEra
        }

        val session = sessions.activeSession(era.contactId)
        val contactName = contacts.contactName(era.contactId) ?: "unknown"
        if (session == null) {
            return@withContext DecryptOutcome.NeedsUnlock(era.contactId, contactName)
        }

        when (val result = provider.decrypt(
            message = message,
            context = DecryptionContext(
                mode = EncryptionMode.PASSPHRASE,
                key = session.keyBytes,
                maxSealedBytes = Ck1Limits.MAX_ENVELOPE_BYTES,
            ),
        )) {
            is DecryptionResult.Success -> {
                sessionKeys.markUsed(era.sessionId)
                DecryptOutcome.Decrypted(
                    plaintext = String(result.plaintext, Charsets.UTF_8),
                    contactId = era.contactId,
                    contactName = contactName,
                )
            }
            is DecryptionResult.Failure -> DecryptOutcome.Failed(result.error)
        }
    }

    suspend fun lockContact(contactId: ContactId): Boolean = sessions.lock(contactId)

    suspend fun lockAll(): Int = sessions.lockAll()

    suspend fun isUnlocked(contactId: ContactId): Boolean = sessions.activeSession(contactId) != null

    suspend fun activeSessionFingerprint(contactId: ContactId): String? =
        sessions.activeSession(contactId)?.fingerprint

    /**
     * v0.3.0 (biometric unlock): the session id of the contact's LATEST key
     * era, or null when the contact has no era yet. Combined with the
     * contact id it forms the biometric "contact key"
     * (`"$contactId:$eraId"`) used as wrap AAD — when the era rotates, the
     * AAD changes, the wrapped key no longer unwraps, and the stale wrap is
     * discarded instead of unlocking the wrong era.
     */
    suspend fun currentEraId(contactId: ContactId): SessionId? =
        sessionKeys.latestEra(contactId)?.sessionId

    /**
     * v0.3.0 (biometric unlock): returns a COPY of the active session's raw
     * key bytes so the caller can wrap them into the fingerprint-gated
     * BiometricKeyStore, or null when the contact is locked. The copy is
     * deliberately explicit — the session keeps owning its own buffer.
     */
    suspend fun exportSessionKey(contactId: ContactId): ByteArray? {
        val session = sessions.activeSession(contactId) ?: return null
        val keyBytes = session.keyBytes ?: return null
        return keyBytes.copyOf()
    }

    /**
     * v0.3.0 (biometric unlock): activates the current era with key material
     * that was remembered behind a fingerprint wrap. The bytes were derived
     * and verified when the user first unlocked with the passphrase — no
     * re-derivation and no passphrase verification happen here; the GCM
     * authentication of the wrap (AAD = "contactId:eraId") is what binds the
     * bytes to THIS era. The caller's buffer is wiped on every path.
     */
    suspend fun unlockContactWithKey(contactId: ContactId, keyBytes: ByteArray): UnlockOutcome =
        withContext(dispatchers.io) {
            try {
                val era = sessionKeys.latestEra(contactId)
                if (era == null) {
                    keyBytes.fill(0)
                    return@withContext UnlockOutcome.NoSession
                }
                if (sessions.activeSession(contactId)?.sessionId == era.sessionId) {
                    keyBytes.fill(0)
                    return@withContext UnlockOutcome.AlreadyUnlocked
                }
                val timeout = sessionTimeout.timeoutMillis()
                sessions.activate(
                    contactId = contactId,
                    sessionId = era.sessionId,
                    keyId = era.keyId,
                    salt = era.salt,
                    params = era.params,
                    keyBytes = keyBytes,
                    timeoutMillis = timeout,
                )
                UnlockOutcome.Unlocked
            } catch (e: Exception) {
                sessions.lock(contactId)
                UnlockOutcome.Failed(e.message ?: "unlock failed")
            } finally {
                keyBytes.fill(0)
            }
        }
}
