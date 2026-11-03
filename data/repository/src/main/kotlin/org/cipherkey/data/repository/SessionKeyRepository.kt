package org.cipherkey.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.DispatcherProvider
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.Argon2Params
import org.cipherkey.core.crypto.ProtocolRandom
import org.cipherkey.core.messages.SessionInvite
import org.cipherkey.core.sessions.TimeSource
import org.cipherkey.data.database.SessionKeyDao
import org.cipherkey.data.database.SessionKeyEntity
import java.util.UUID

/** Outcome of importing a CKS1 invite. */
sealed interface EraImport {
    data class Imported(val era: ContactSession) : EraImport

    /** The same keyId is already known locally for the same contact. */
    data class AlreadyPresent(val era: ContactSession) : EraImport

    /** The same keyId exists under a different contact — refusing to alias. */
    data object KeyBelongsToOtherContact : EraImport
}

/**
 * Persistence and lookup of session eras (public key material + optional
 * Keystore-wrapped verifier).
 */
class SessionKeyRepository(
    private val sessionKeyDao: SessionKeyDao,
    private val random: ProtocolRandom,
    private val timeSource: TimeSource,
    private val dispatchers: DispatcherProvider,
) {

    /** Creates a brand-new era (fresh keyId + salt) for the contact. */
    suspend fun createEra(contactId: ContactId, params: Argon2Params): ContactSession =
        withContext(dispatchers.io) {
            val now = timeSource.nowMillis()
            val entity = SessionKeyEntity(
                sessionId = UUID.randomUUID().toString(),
                contactId = contactId.value,
                keyId = random.keyId(),
                salt = random.salt(),
                memoryExponent = params.memoryExponent,
                iterations = params.iterations,
                parallelism = params.parallelism,
                wrappedKey = null,
                createdAt = now,
                lastUsedAt = null,
            )
            sessionKeyDao.upsert(entity)
            entity.toDomain()
        }

    /** Imports an era from a peer's CKS1 invite. */
    suspend fun importEra(contactId: ContactId, invite: SessionInvite): EraImport =
        withContext(dispatchers.io) {
            val existing = sessionKeyDao.byKeyId(invite.keyId)
            if (existing != null) {
                return@withContext if (existing.contactId == contactId.value) {
                    EraImport.AlreadyPresent(existing.toDomain())
                } else {
                    EraImport.KeyBelongsToOtherContact
                }
            }
            val now = timeSource.nowMillis()
            val entity = SessionKeyEntity(
                sessionId = UUID.randomUUID().toString(),
                contactId = contactId.value,
                keyId = invite.keyId.copyOf(),
                salt = invite.salt.copyOf(),
                memoryExponent = invite.params.memoryExponent,
                iterations = invite.params.iterations,
                parallelism = invite.params.parallelism,
                wrappedKey = null,
                createdAt = now,
                lastUsedAt = null,
            )
            sessionKeyDao.upsert(entity)
            EraImport.Imported(entity.toDomain())
        }

    suspend fun latestEra(contactId: ContactId): ContactSession? = withContext(dispatchers.io) {
        sessionKeyDao.latestByContact(contactId.value)?.toDomain()
    }

    suspend fun eraByKeyId(keyId: ByteArray): ContactSession? = withContext(dispatchers.io) {
        sessionKeyDao.byKeyId(keyId)?.toDomain()
    }

    suspend fun erasForContact(contactId: ContactId): List<ContactSession> =
        withContext(dispatchers.io) {
            sessionKeyDao.observeByContact(contactId.value).first().map { it.toDomain() }
        }

    /** Snapshot flow of all eras of a contact (newest first). */
    fun observeEras(contactId: ContactId): Flow<List<ContactSession>> =
        sessionKeyDao.observeByContact(contactId.value)
            .map { list -> list.map { it.toDomain() } }

    /** Raw Keystore-wrapped verifier blob, if this device ever unlocked the era. */
    suspend fun wrappedKeyOf(sessionId: SessionId): ByteArray? = withContext(dispatchers.io) {
        sessionKeyDao.bySessionId(sessionId.value)?.wrappedKey
    }

    suspend fun storeWrappedKey(sessionId: SessionId, wrapped: ByteArray) =
        withContext(dispatchers.io) {
            sessionKeyDao.updateWrappedKey(sessionId.value, wrapped, timeSource.nowMillis())
        }

    suspend fun markUsed(sessionId: SessionId) = withContext(dispatchers.io) {
        sessionKeyDao.touch(sessionId.value, timeSource.nowMillis())
    }

    suspend fun deleteAllForContact(contactId: ContactId) = withContext(dispatchers.io) {
        sessionKeyDao.deleteByContact(contactId.value)
    }

    private fun SessionKeyEntity.toDomain() = ContactSession(
        sessionId = SessionId(sessionId),
        contactId = ContactId(contactId),
        keyId = keyId,
        salt = salt,
        params = Argon2Params(
            memoryExponent = memoryExponent,
            iterations = iterations,
            parallelism = parallelism,
        ),
        createdAt = createdAt,
        hasWrappedKey = wrappedKey != null,
        lastUsedAt = lastUsedAt,
    )
}
