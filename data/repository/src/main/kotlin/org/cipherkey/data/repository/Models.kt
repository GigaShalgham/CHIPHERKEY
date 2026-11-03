package org.cipherkey.data.repository

import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.Argon2Params
import org.cipherkey.core.messages.InviteCodec
import org.cipherkey.core.messages.SessionInvite

/**
 * Domain-level contact (no persistence annotations).
 */
data class Contact(
    val id: ContactId,
    val displayName: String,
    val createdAt: Long,
    val updatedAt: Long,
)

/**
 * Domain-level key era belonging to a contact.
 *
 * Carries no secret: the passphrase lives only in the user's head, and the
 * derived key only in [org.cipherkey.core.sessions.KeySessionManager] or in
 * its Keystore-wrapped form.
 */
data class ContactSession(
    val sessionId: SessionId,
    val contactId: ContactId,
    val keyId: ByteArray,
    val salt: ByteArray,
    val params: Argon2Params,
    val createdAt: Long,
    val hasWrappedKey: Boolean,
    val lastUsedAt: Long?,
) {
    /** The public invite carrying this era's parameters. */
    val invite: SessionInvite
        get() = SessionInvite(keyId = keyId, salt = salt, params = params)

    /** Encoded CKS1 invite code, ready to share over any channel. */
    val inviteCode: String
        get() = InviteCodec.encode(invite)

    override fun equals(other: Any?): Boolean =
        other is ContactSession &&
            sessionId == other.sessionId &&
            contactId == other.contactId &&
            keyId.contentEquals(other.keyId) &&
            salt.contentEquals(other.salt) &&
            params == other.params &&
            createdAt == other.createdAt &&
            hasWrappedKey == other.hasWrappedKey &&
            lastUsedAt == other.lastUsedAt

    override fun hashCode(): Int {
        var result = sessionId.hashCode()
        result = 31 * result + contactId.hashCode()
        result = 31 * result + keyId.contentHashCode()
        result = 31 * result + salt.contentHashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + hasWrappedKey.hashCode()
        result = 31 * result + (lastUsedAt?.hashCode() ?: 0)
        return result
    }
}
