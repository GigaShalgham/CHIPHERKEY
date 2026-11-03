package org.cipherkey.core.sessions

import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.crypto.Argon2Params
import org.cipherkey.core.keys.KeyFingerprint

/**
 * An unlocked session key held in memory.
 *
 * SECURITY CONTRACT:
 *  - [keyBytes] is raw AES-256 key material. Callers MUST NOT clone, retain
 *    or log it; it is wiped by [KeySessionManager] when the session is locked,
 *    replaced or expires.
 *  - Instances must never be persisted; at rest only the Keystore-wrapped
 *    form is ever stored (see core:keys KeyProtector).
 */
class SessionKey(
    val sessionId: SessionId,
    val contactId: ContactId,
    /** Public key identifier — matches the CK1 envelope's keyId field. */
    val keyId: ByteArray,
    val salt: ByteArray,
    val params: Argon2Params,
    val keyBytes: ByteArray,
) {
    /** Short human-comparable fingerprint of the raw key material. */
    val fingerprint: String
        get() = KeyFingerprint.of(keyBytes)

    override fun toString(): String =
        "SessionKey(sessionId=$sessionId, contactId=$contactId, fingerprint=$fingerprint)"
}
