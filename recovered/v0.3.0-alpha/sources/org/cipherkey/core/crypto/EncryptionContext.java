package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptoProvider.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionContext;", "", "mode", "Lorg/cipherkey/core/crypto/EncryptionMode;", "key", "", "keyId", "salt", "kdfParams", "Lorg/cipherkey/core/crypto/Argon2Params;", "maxPlaintextBytes", "", "<init>", "(Lorg/cipherkey/core/crypto/EncryptionMode;[B[B[BLorg/cipherkey/core/crypto/Argon2Params;I)V", "getMode", "()Lorg/cipherkey/core/crypto/EncryptionMode;", "getKey", "()[B", "getKeyId", "getSalt", "getKdfParams", "()Lorg/cipherkey/core/crypto/Argon2Params;", "getMaxPlaintextBytes", "()I", "equals", "", "other", "hashCode", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class EncryptionContext {
    private final Argon2Params kdfParams;
    private final byte[] key;
    private final byte[] keyId;
    private final int maxPlaintextBytes;
    private final EncryptionMode mode;
    private final byte[] salt;

    public static /* synthetic */ EncryptionContext copy$default(EncryptionContext encryptionContext, EncryptionMode encryptionMode, byte[] bArr, byte[] bArr2, byte[] bArr3, Argon2Params argon2Params, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            encryptionMode = encryptionContext.mode;
        }
        if ((i2 & 2) != 0) {
            bArr = encryptionContext.key;
        }
        if ((i2 & 4) != 0) {
            bArr2 = encryptionContext.keyId;
        }
        if ((i2 & 8) != 0) {
            bArr3 = encryptionContext.salt;
        }
        if ((i2 & 16) != 0) {
            argon2Params = encryptionContext.kdfParams;
        }
        if ((i2 & 32) != 0) {
            i = encryptionContext.maxPlaintextBytes;
        }
        Argon2Params argon2Params2 = argon2Params;
        int i3 = i;
        return encryptionContext.copy(encryptionMode, bArr, bArr2, bArr3, argon2Params2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final EncryptionMode getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getKey() {
        return this.key;
    }

    /* renamed from: component3, reason: from getter */
    public final byte[] getKeyId() {
        return this.keyId;
    }

    /* renamed from: component4, reason: from getter */
    public final byte[] getSalt() {
        return this.salt;
    }

    /* renamed from: component5, reason: from getter */
    public final Argon2Params getKdfParams() {
        return this.kdfParams;
    }

    /* renamed from: component6, reason: from getter */
    public final int getMaxPlaintextBytes() {
        return this.maxPlaintextBytes;
    }

    public final EncryptionContext copy(EncryptionMode mode, byte[] key, byte[] keyId, byte[] salt, Argon2Params kdfParams, int maxPlaintextBytes) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(kdfParams, "kdfParams");
        return new EncryptionContext(mode, key, keyId, salt, kdfParams, maxPlaintextBytes);
    }

    public boolean equals(Object other) {
        return this == other;
    }

    public String toString() {
        return "EncryptionContext(mode=" + this.mode + ", key=" + Arrays.toString(this.key) + ", keyId=" + Arrays.toString(this.keyId) + ", salt=" + Arrays.toString(this.salt) + ", kdfParams=" + this.kdfParams + ", maxPlaintextBytes=" + this.maxPlaintextBytes + ")";
    }

    public EncryptionContext(EncryptionMode mode, byte[] key, byte[] keyId, byte[] salt, Argon2Params kdfParams, int i) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(kdfParams, "kdfParams");
        this.mode = mode;
        this.key = key;
        this.keyId = keyId;
        this.salt = salt;
        this.kdfParams = kdfParams;
        this.maxPlaintextBytes = i;
    }

    public final EncryptionMode getMode() {
        return this.mode;
    }

    public final byte[] getKey() {
        return this.key;
    }

    public final byte[] getKeyId() {
        return this.keyId;
    }

    public final byte[] getSalt() {
        return this.salt;
    }

    public final Argon2Params getKdfParams() {
        return this.kdfParams;
    }

    public final int getMaxPlaintextBytes() {
        return this.maxPlaintextBytes;
    }

    public int hashCode() {
        return EncryptedMessageKt.varargDeepHashCode(this.key, this.keyId, this.salt, this.kdfParams);
    }
}
