package org.cipherkey.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * A locally managed contact ("key era owner").
 *
 * Contacts are purely local: no phone-book integration, no identifiers that
 * could leak to other apps. The id is a random UUID string.
 */
@Entity(
    tableName = "contacts",
    indices = [Index("displayName")],
)
data class ContactEntity(
    @PrimaryKey val id: String,
    val displayName: String,
    val createdAt: Long,
    val updatedAt: Long,
)

/**
 * One key era belonging to a contact.
 *
 * A "key era" is the tuple (keyId, salt, Argon2id params). The passphrase is
 * never stored. `wrappedKey` holds the Android-Keystore-wrapped derived key
 * after the first successful unlock on this device — it is device-bound,
 * never leaves the device, and serves as the on-device passphrase verifier.
 * Older eras of a contact may coexist (rotation creates a new era); only the
 * newest era is used for sending.
 */
@Entity(
    tableName = "session_keys",
    foreignKeys = [
        ForeignKey(
            entity = ContactEntity::class,
            parentColumns = ["id"],
            childColumns = ["contactId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index("contactId"),
        Index(value = ["keyId"], unique = true),
    ],
)
data class SessionKeyEntity(
    @PrimaryKey val sessionId: String,
    val contactId: String,
    /** 16-byte public session key identifier (CK1 envelope binding). */
    val keyId: ByteArray,
    /** 16-byte Argon2id salt. Public: also present in every envelope/invite. */
    val salt: ByteArray,
    val memoryExponent: Int,
    val iterations: Int,
    val parallelism: Int,
    /** Keystore-wrapped derived key (device-bound verifier), or null until first unlock. */
    val wrappedKey: ByteArray?,
    val createdAt: Long,
    val lastUsedAt: Long?,
) {
    override fun equals(other: Any?): Boolean =
        other is SessionKeyEntity &&
            sessionId == other.sessionId &&
            contactId == other.contactId &&
            keyId.contentEquals(other.keyId) &&
            salt.contentEquals(other.salt) &&
            memoryExponent == other.memoryExponent &&
            iterations == other.iterations &&
            parallelism == other.parallelism &&
            (wrappedKey?.contentEquals(other.wrappedKey) ?: (other.wrappedKey == null)) &&
            createdAt == other.createdAt &&
            lastUsedAt == other.lastUsedAt

    override fun hashCode(): Int {
        var result = sessionId.hashCode()
        result = 31 * result + contactId.hashCode()
        result = 31 * result + keyId.contentHashCode()
        result = 31 * result + salt.contentHashCode()
        result = 31 * result + memoryExponent
        result = 31 * result + iterations
        result = 31 * result + parallelism
        result = 31 * result + (wrappedKey?.contentHashCode() ?: 0)
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + (lastUsedAt?.hashCode() ?: 0)
        return result
    }
}
