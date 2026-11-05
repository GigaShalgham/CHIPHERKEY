package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.crypto.CryptoError;

/* compiled from: ProtocolHeader.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/cipherkey/core/crypto/ProtocolHeader;", "", "<init>", "()V", "VERSION", "", "PASSPHRASE_HEADER_LENGTH", "serialize", "", "message", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "parse", "payload", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProtocolHeader {
    public static final ProtocolHeader INSTANCE = new ProtocolHeader();
    public static final int PASSPHRASE_HEADER_LENGTH = 55;
    public static final int VERSION = 1;

    private ProtocolHeader() {
    }

    public final byte[] serialize(EncryptedMessage message) throws AeadFailure {
        Intrinsics.checkNotNullParameter(message, "message");
        if (message.getMode() != EncryptionMode.PASSPHRASE) {
            throw new AeadFailure(new CryptoError.UnsupportedMode(message.getMode().getId()), null, 2, null);
        }
        Argon2Params argon2Params = message.getArgon2Params();
        if (argon2Params == null) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, null, 2, null);
        }
        byte[] salt = message.getSalt();
        if (salt == null) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, null, 2, null);
        }
        if (salt.length != 16) {
            throw new AeadFailure(CryptoError.InvalidSalt.INSTANCE, null, 2, null);
        }
        if (message.getNonce().length != CipherAlgorithm.AES_256_GCM.getNonceLengthBytes()) {
            throw new AeadFailure(CryptoError.InvalidNonce.INSTANCE, null, 2, null);
        }
        if (message.getKeyId().length != 16) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, null, 2, null);
        }
        byte[] bArr = new byte[55];
        bArr[0] = 1;
        bArr[1] = (byte) message.getMode().getId();
        bArr[2] = (byte) message.getAlgorithm().getId();
        bArr[3] = (byte) message.getKdf().getId();
        ArraysKt.copyInto$default(message.getKeyId(), bArr, 4, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(argon2Params.encode(), bArr, 20, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(salt, bArr, 27, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(message.getNonce(), bArr, 43, 0, 0, 12, (Object) null);
        return bArr;
    }

    public final EncryptedMessage parse(byte[] payload) throws AeadFailure {
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (payload.length < CipherAlgorithm.AES_256_GCM.getTagLengthBytes() + 55) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, null, 2, null);
        }
        if ((payload[0] & UByte.MAX_VALUE) != 1) {
            throw new AeadFailure(new CryptoError.UnsupportedVersion(payload[0] & UByte.MAX_VALUE), null, 2, null);
        }
        EncryptionMode encryptionModeFromId = EncryptionMode.INSTANCE.fromId(payload[1] & UByte.MAX_VALUE);
        if (encryptionModeFromId == null) {
            throw new AeadFailure(new CryptoError.UnsupportedMode(payload[1] & UByte.MAX_VALUE), null, 2, null);
        }
        CipherAlgorithm cipherAlgorithmFromId = CipherAlgorithm.INSTANCE.fromId(payload[2] & UByte.MAX_VALUE);
        if (cipherAlgorithmFromId == null) {
            throw new AeadFailure(new CryptoError.UnsupportedAlgorithm(payload[2] & UByte.MAX_VALUE), null, 2, null);
        }
        KdfAlgorithm kdfAlgorithmFromId = KdfAlgorithm.INSTANCE.fromId(payload[3] & UByte.MAX_VALUE);
        if (kdfAlgorithmFromId == null) {
            throw new AeadFailure(new CryptoError.UnsupportedKdf(payload[3] & UByte.MAX_VALUE), null, 2, null);
        }
        if (encryptionModeFromId != EncryptionMode.PASSPHRASE) {
            throw new AeadFailure(new CryptoError.UnsupportedMode(encryptionModeFromId.getId()), null, 2, null);
        }
        if (cipherAlgorithmFromId != CipherAlgorithm.AES_256_GCM) {
            throw new AeadFailure(new CryptoError.UnsupportedAlgorithm(cipherAlgorithmFromId.getId()), null, 2, null);
        }
        if (kdfAlgorithmFromId != KdfAlgorithm.ARGON2ID) {
            throw new AeadFailure(new CryptoError.UnsupportedKdf(kdfAlgorithmFromId.getId()), null, 2, null);
        }
        try {
            return new EncryptedMessage(encryptionModeFromId, cipherAlgorithmFromId, kdfAlgorithmFromId, ArraysKt.copyOfRange(payload, 4, 20), Argon2Params.INSTANCE.decode(ArraysKt.copyOfRange(payload, 20, 27)), ArraysKt.copyOfRange(payload, 27, 43), ArraysKt.copyOfRange(payload, 43, 55), ArraysKt.copyOfRange(payload, 55, payload.length));
        } catch (IllegalArgumentException e) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, e);
        }
    }
}
