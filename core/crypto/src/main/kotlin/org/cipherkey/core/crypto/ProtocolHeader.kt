package org.cipherkey.core.crypto

/**
 * Canonical binary serialization of the CK1 authenticated header.
 *
 * THIS LAYOUT IS THE AUTHENTICATED-ASSOCIATED-DATA (AAD) DEFINITION of the
 * CK1 protocol: every byte before the ciphertext is bound to the AEAD tag.
 * The same layout is mirrored in docs/PROTOCOL.md and MUST NOT change for
 * CK1; any change requires a new protocol version.
 *
 * Layout (passphrase mode), all multi-byte integers big-endian:
 *
 * ```
 *  offset  size  field
 *  0       1     protocol version (0x01)
 *  1       1     encryption mode
 *  2       1     cipher algorithm id
 *  3       1     KDF algorithm id
 *  4       16    session key identifier (public)
 *  20      1     Argon2id memory exponent (log2 of KiB)
 *  21      4     Argon2id iterations
 *  25      2     Argon2id parallelism
 *  27      16    Argon2id salt
 *  43      12    AEAD nonce
 *  55      ...   ciphertext || tag
 * ```
 */
object ProtocolHeader {

    const val VERSION: Int = 0x01
    const val PASSPHRASE_HEADER_LENGTH: Int = 55

    fun serialize(message: EncryptedMessage): ByteArray {
        if (message.mode != EncryptionMode.PASSPHRASE) {
            throw AeadFailure(CryptoError.UnsupportedMode(message.mode.id))
        }
        val kdfParams = message.argon2Params ?: throw AeadFailure(CryptoError.MalformedMessage)
        val salt = message.salt ?: throw AeadFailure(CryptoError.MalformedMessage)
        if (salt.size != Argon2KeyDerivator.SALT_LENGTH_BYTES) throw AeadFailure(CryptoError.InvalidSalt)
        if (message.nonce.size != CipherAlgorithm.AES_256_GCM.nonceLengthBytes) throw AeadFailure(CryptoError.InvalidNonce)
        if (message.keyId.size != ProtocolRandom.KEY_ID_LENGTH_BYTES) throw AeadFailure(CryptoError.MalformedMessage)

        val out = ByteArray(PASSPHRASE_HEADER_LENGTH)
        out[0] = VERSION.toByte()
        out[1] = message.mode.id.toByte()
        out[2] = message.algorithm.id.toByte()
        out[3] = message.kdf.id.toByte()
        message.keyId.copyInto(out, 4)
        val kdfBytes = kdfParams.encode()
        kdfBytes.copyInto(out, 20)
        salt.copyInto(out, 27)
        message.nonce.copyInto(out, 43)
        return out
    }

    /**
     * Parses the fixed header and returns the message with ciphertext
     * attached. Rejects unknown versions, algorithms, modes and KDFs, and
     * any structural inconsistency — before any cryptographic work happens.
     */
    fun parse(payload: ByteArray): EncryptedMessage {
        if (payload.size < PASSPHRASE_HEADER_LENGTH + CipherAlgorithm.AES_256_GCM.tagLengthBytes) {
            throw AeadFailure(CryptoError.MalformedMessage)
        }
        if ((payload[0].toInt() and 0xFF) != VERSION) {
            throw AeadFailure(CryptoError.UnsupportedVersion(payload[0].toInt() and 0xFF))
        }
        val mode = EncryptionMode.fromId(payload[1].toInt() and 0xFF)
            ?: throw AeadFailure(CryptoError.UnsupportedMode(payload[1].toInt() and 0xFF))
        val algorithm = CipherAlgorithm.fromId(payload[2].toInt() and 0xFF)
            ?: throw AeadFailure(CryptoError.UnsupportedAlgorithm(payload[2].toInt() and 0xFF))
        val kdf = KdfAlgorithm.fromId(payload[3].toInt() and 0xFF)
            ?: throw AeadFailure(CryptoError.UnsupportedKdf(payload[3].toInt() and 0xFF))

        if (mode != EncryptionMode.PASSPHRASE) {
            // Reserved for the public-key provider (Phase 5); no CK1 v1
            // message may use it.
            throw AeadFailure(CryptoError.UnsupportedMode(mode.id))
        }
        if (algorithm != CipherAlgorithm.AES_256_GCM) throw AeadFailure(CryptoError.UnsupportedAlgorithm(algorithm.id))
        if (kdf != KdfAlgorithm.ARGON2ID) throw AeadFailure(CryptoError.UnsupportedKdf(kdf.id))

        val keyId = payload.copyOfRange(4, 20)
        val argon2Params = try {
            Argon2Params.decode(payload.copyOfRange(20, 27))
        } catch (e: IllegalArgumentException) {
            throw AeadFailure(CryptoError.MalformedMessage, e)
        }
        val salt = payload.copyOfRange(27, 43)
        val nonce = payload.copyOfRange(43, 55)
        val ciphertext = payload.copyOfRange(55, payload.size)

        return EncryptedMessage(
            mode = mode,
            algorithm = algorithm,
            kdf = kdf,
            keyId = keyId,
            argon2Params = argon2Params,
            salt = salt,
            nonce = nonce,
            ciphertext = ciphertext,
        )
    }
}
