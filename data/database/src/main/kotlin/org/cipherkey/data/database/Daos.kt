package org.cipherkey.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsert(contact: ContactEntity)

    @Query("SELECT * FROM contacts ORDER BY displayName COLLATE NOCASE")
    fun observeAll(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun byId(id: String): ContactEntity?

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun count(): Int

    @Query("DELETE FROM contacts WHERE id = :id")
    suspend fun delete(id: String)
}

@Dao
interface SessionKeyDao {

    @Upsert
    suspend fun upsert(session: SessionKeyEntity)

    // Deterministic ordering (deep-scan finding 16): same-millisecond
    // eras are broken by sessionId, so "latest era" never depends on
    // SQLite's scan order.
    @Query("SELECT * FROM session_keys WHERE contactId = :contactId ORDER BY createdAt DESC, sessionId DESC")
    fun observeByContact(contactId: String): Flow<List<SessionKeyEntity>>

    @Query("SELECT * FROM session_keys WHERE contactId = :contactId ORDER BY createdAt DESC, sessionId DESC LIMIT 1")
    suspend fun latestByContact(contactId: String): SessionKeyEntity?

    @Query("SELECT * FROM session_keys WHERE keyId = :keyId LIMIT 1")
    suspend fun byKeyId(keyId: ByteArray): SessionKeyEntity?

    @Query("SELECT * FROM session_keys WHERE sessionId = :sessionId LIMIT 1")
    suspend fun bySessionId(sessionId: String): SessionKeyEntity?

    @Query("UPDATE session_keys SET wrappedKey = :wrapped, lastUsedAt = :now WHERE sessionId = :sessionId")
    suspend fun updateWrappedKey(sessionId: String, wrapped: ByteArray?, now: Long)

    @Query("UPDATE session_keys SET lastUsedAt = :now WHERE sessionId = :sessionId")
    suspend fun touch(sessionId: String, now: Long)

    @Query("DELETE FROM session_keys WHERE contactId = :contactId")
    suspend fun deleteByContact(contactId: String)

    @Query("SELECT COUNT(*) FROM session_keys WHERE contactId = :contactId")
    suspend fun countByContact(contactId: String): Int
}
