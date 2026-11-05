package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptoProvider.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/cipherkey/core/crypto/DecryptionContext;", "", "mode", "Lorg/cipherkey/core/crypto/EncryptionMode;", "key", "", "maxSealedBytes", "", "<init>", "(Lorg/cipherkey/core/crypto/EncryptionMode;[BI)V", "getMode", "()Lorg/cipherkey/core/crypto/EncryptionMode;", "getKey", "()[B", "getMaxSealedBytes", "()I", "equals", "", "other", "hashCode", "component1", "component2", "component3", "copy", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DecryptionContext {
    private final byte[] key;
    private final int maxSealedBytes;
    private final EncryptionMode mode;

    public static /* synthetic */ DecryptionContext copy$default(DecryptionContext decryptionContext, EncryptionMode encryptionMode, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            encryptionMode = decryptionContext.mode;
        }
        if ((i2 & 2) != 0) {
            bArr = decryptionContext.key;
        }
        if ((i2 & 4) != 0) {
            i = decryptionContext.maxSealedBytes;
        }
        return decryptionContext.copy(encryptionMode, bArr, i);
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
    public final int getMaxSealedBytes() {
        return this.maxSealedBytes;
    }

    public final DecryptionContext copy(EncryptionMode mode, byte[] key, int maxSealedBytes) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(key, "key");
        return new DecryptionContext(mode, key, maxSealedBytes);
    }

    public boolean equals(Object other) {
        return this == other;
    }

    public String toString() {
        return "DecryptionContext(mode=" + this.mode + ", key=" + Arrays.toString(this.key) + ", maxSealedBytes=" + this.maxSealedBytes + ")";
    }

    public DecryptionContext(EncryptionMode mode, byte[] key, int i) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(key, "key");
        this.mode = mode;
        this.key = key;
        this.maxSealedBytes = i;
    }

    public final EncryptionMode getMode() {
        return this.mode;
    }

    public final byte[] getKey() {
        return this.key;
    }

    public final int getMaxSealedBytes() {
        return this.maxSealedBytes;
    }

    public int hashCode() {
        return EncryptedMessageKt.varargDeepHashCode(this.key);
    }
}
