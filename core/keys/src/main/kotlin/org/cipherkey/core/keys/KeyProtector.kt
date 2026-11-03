package org.cipherkey.core.keys

import java.security.MessageDigest

/**
 * Protects secret key material at rest.
 *
 * Implementations wrap (encrypt+authenticate) raw key bytes before they are
 * handed to persistence, and unwrap them only for the lifetime of a single
 * cryptographic operation. The production implementation is backed by the
 * Android Keystore (security:storage); tests use a local AES key.
 */
interface KeyProtector {
    /** Returns an opaque, authenticated blob safe for database storage. */
    fun wrap(plainKey: ByteArray, keyId: ByteArray): ByteArray

    /**
     * Recovers the raw key. Throws [KeyProtectionException] when the blob
     * fails authentication or the keyId does not match the one used at wrap
     * time (the keyId is bound as associated data).
     */
    fun unwrap(wrapped: ByteArray, keyId: ByteArray): ByteArray
}

class KeyProtectionException(message: String, cause: Throwable? = null) : Exception(message, cause)

/**
 * Human-comparable fingerprint of key material.
 *
 * SHA-256 over the raw key, formatted as five colon-separated groups of
 * decimal numbers (0-255 each), e.g. "072:199:003:...". Users compare this
 * out-of-band to confirm both sides derived the same session key.
 */
object KeyFingerprint {

    fun of(key: ByteArray): String =
        MessageDigest.getInstance("SHA-256").digest(key)
            .takeLast(6)
            .joinToString(":") { String.format("%03d", it.toInt() and 0xFF) }

    /** Fingerprint of a session identity (keyId + salt), for invite cards. */
    fun ofSession(keyId: ByteArray, salt: ByteArray): String =
        MessageDigest.getInstance("SHA-256").digest(keyId + salt)
            .takeLast(6)
            .joinToString(":") { String.format("%03d", it.toInt() and 0xFF) }
}
