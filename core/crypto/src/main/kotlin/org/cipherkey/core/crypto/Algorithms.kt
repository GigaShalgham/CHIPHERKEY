package org.cipherkey.core.crypto

/**
 * Wire identifiers for the CK1 protocol.
 *
 * Every identifier is a single byte. Unknown values MUST be rejected at
 * decode time; adding a value therefore requires a protocol version bump
 * (see docs/PROTOCOL.md).
 */
enum class EncryptionMode(val id: Int) {
    /** Shared-passphrase communication. */
    PASSPHRASE(0x01),

    /** Public-key communication. RESERVED — no implementation yet (see docs). */
    PUBLIC_KEY(0x02);

    companion object {
        fun fromId(id: Int): EncryptionMode? = entries.firstOrNull { it.id == id }
    }
}

enum class CipherAlgorithm(val id: Int, val keyLengthBytes: Int, val nonceLengthBytes: Int, val tagLengthBytes: Int) {
    /** AES-256-GCM with a 96-bit random nonce and a 128-bit tag. */
    AES_256_GCM(0x01, keyLengthBytes = 32, nonceLengthBytes = 12, tagLengthBytes = 16);

    companion object {
        fun fromId(id: Int): CipherAlgorithm? = entries.firstOrNull { it.id == id }
    }
}

enum class KdfAlgorithm(val id: Int) {
    /** Argon2id, version 0x13, per RFC 9106. */
    ARGON2ID(0x01);

    companion object {
        fun fromId(id: Int): KdfAlgorithm? = entries.firstOrNull { it.id == id }
    }
}
