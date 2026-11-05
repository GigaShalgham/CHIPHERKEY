package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EncryptedMessage.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\"H\u0016J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J]\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tHÆ\u0001J\t\u0010,\u001a\u00020-HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018¨\u0006."}, d2 = {"Lorg/cipherkey/core/crypto/EncryptedMessage;", "", "mode", "Lorg/cipherkey/core/crypto/EncryptionMode;", "algorithm", "Lorg/cipherkey/core/crypto/CipherAlgorithm;", "kdf", "Lorg/cipherkey/core/crypto/KdfAlgorithm;", "keyId", "", "argon2Params", "Lorg/cipherkey/core/crypto/Argon2Params;", "salt", "nonce", "ciphertext", "<init>", "(Lorg/cipherkey/core/crypto/EncryptionMode;Lorg/cipherkey/core/crypto/CipherAlgorithm;Lorg/cipherkey/core/crypto/KdfAlgorithm;[BLorg/cipherkey/core/crypto/Argon2Params;[B[B[B)V", "getMode", "()Lorg/cipherkey/core/crypto/EncryptionMode;", "getAlgorithm", "()Lorg/cipherkey/core/crypto/CipherAlgorithm;", "getKdf", "()Lorg/cipherkey/core/crypto/KdfAlgorithm;", "getKeyId", "()[B", "getArgon2Params", "()Lorg/cipherkey/core/crypto/Argon2Params;", "getSalt", "getNonce", "getCiphertext", "equals", "", "other", "hashCode", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EncryptedMessage {
    private final CipherAlgorithm algorithm;
    private final Argon2Params argon2Params;
    private final byte[] ciphertext;
    private final KdfAlgorithm kdf;
    private final byte[] keyId;
    private final EncryptionMode mode;
    private final byte[] nonce;
    private final byte[] salt;

    public static /* synthetic */ EncryptedMessage copy$default(EncryptedMessage encryptedMessage, EncryptionMode encryptionMode, CipherAlgorithm cipherAlgorithm, KdfAlgorithm kdfAlgorithm, byte[] bArr, Argon2Params argon2Params, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, Object obj) {
        if ((i & 1) != 0) {
            encryptionMode = encryptedMessage.mode;
        }
        if ((i & 2) != 0) {
            cipherAlgorithm = encryptedMessage.algorithm;
        }
        if ((i & 4) != 0) {
            kdfAlgorithm = encryptedMessage.kdf;
        }
        if ((i & 8) != 0) {
            bArr = encryptedMessage.keyId;
        }
        if ((i & 16) != 0) {
            argon2Params = encryptedMessage.argon2Params;
        }
        if ((i & 32) != 0) {
            bArr2 = encryptedMessage.salt;
        }
        if ((i & 64) != 0) {
            bArr3 = encryptedMessage.nonce;
        }
        if ((i & 128) != 0) {
            bArr4 = encryptedMessage.ciphertext;
        }
        byte[] bArr5 = bArr3;
        byte[] bArr6 = bArr4;
        Argon2Params argon2Params2 = argon2Params;
        byte[] bArr7 = bArr2;
        return encryptedMessage.copy(encryptionMode, cipherAlgorithm, kdfAlgorithm, bArr, argon2Params2, bArr7, bArr5, bArr6);
    }

    /* renamed from: component1, reason: from getter */
    public final EncryptionMode getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final CipherAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    /* renamed from: component3, reason: from getter */
    public final KdfAlgorithm getKdf() {
        return this.kdf;
    }

    /* renamed from: component4, reason: from getter */
    public final byte[] getKeyId() {
        return this.keyId;
    }

    /* renamed from: component5, reason: from getter */
    public final Argon2Params getArgon2Params() {
        return this.argon2Params;
    }

    /* renamed from: component6, reason: from getter */
    public final byte[] getSalt() {
        return this.salt;
    }

    /* renamed from: component7, reason: from getter */
    public final byte[] getNonce() {
        return this.nonce;
    }

    /* renamed from: component8, reason: from getter */
    public final byte[] getCiphertext() {
        return this.ciphertext;
    }

    public final EncryptedMessage copy(EncryptionMode mode, CipherAlgorithm algorithm, KdfAlgorithm kdf, byte[] keyId, Argon2Params argon2Params, byte[] salt, byte[] nonce, byte[] ciphertext) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(kdf, "kdf");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(nonce, "nonce");
        Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
        return new EncryptedMessage(mode, algorithm, kdf, keyId, argon2Params, salt, nonce, ciphertext);
    }

    public boolean equals(Object other) {
        return this == other;
    }

    public String toString() {
        return "EncryptedMessage(mode=" + this.mode + ", algorithm=" + this.algorithm + ", kdf=" + this.kdf + ", keyId=" + Arrays.toString(this.keyId) + ", argon2Params=" + this.argon2Params + ", salt=" + Arrays.toString(this.salt) + ", nonce=" + Arrays.toString(this.nonce) + ", ciphertext=" + Arrays.toString(this.ciphertext) + ")";
    }

    public EncryptedMessage(EncryptionMode mode, CipherAlgorithm algorithm, KdfAlgorithm kdf, byte[] keyId, Argon2Params argon2Params, byte[] bArr, byte[] nonce, byte[] ciphertext) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(kdf, "kdf");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(nonce, "nonce");
        Intrinsics.checkNotNullParameter(ciphertext, "ciphertext");
        this.mode = mode;
        this.algorithm = algorithm;
        this.kdf = kdf;
        this.keyId = keyId;
        this.argon2Params = argon2Params;
        this.salt = bArr;
        this.nonce = nonce;
        this.ciphertext = ciphertext;
    }

    public final EncryptionMode getMode() {
        return this.mode;
    }

    public final CipherAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    public final KdfAlgorithm getKdf() {
        return this.kdf;
    }

    public final byte[] getKeyId() {
        return this.keyId;
    }

    public final Argon2Params getArgon2Params() {
        return this.argon2Params;
    }

    public final byte[] getSalt() {
        return this.salt;
    }

    public final byte[] getNonce() {
        return this.nonce;
    }

    public final byte[] getCiphertext() {
        return this.ciphertext;
    }

    public int hashCode() {
        return EncryptedMessageKt.varargDeepHashCode(new Object[0]);
    }
}
