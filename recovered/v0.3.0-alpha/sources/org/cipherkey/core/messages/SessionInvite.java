package org.cipherkey.core.messages;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.crypto.Argon2Params;

/* compiled from: InviteCodec.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/cipherkey/core/messages/SessionInvite;", "", "keyId", "", "salt", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "<init>", "([B[BLorg/cipherkey/core/crypto/Argon2Params;)V", "getKeyId", "()[B", "getSalt", "getParams", "()Lorg/cipherkey/core/crypto/Argon2Params;", "equals", "", "other", "hashCode", "", "component1", "component2", "component3", "copy", "toString", "", "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SessionInvite {
    private final byte[] keyId;
    private final Argon2Params params;
    private final byte[] salt;

    public static /* synthetic */ SessionInvite copy$default(SessionInvite sessionInvite, byte[] bArr, byte[] bArr2, Argon2Params argon2Params, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = sessionInvite.keyId;
        }
        if ((i & 2) != 0) {
            bArr2 = sessionInvite.salt;
        }
        if ((i & 4) != 0) {
            argon2Params = sessionInvite.params;
        }
        return sessionInvite.copy(bArr, bArr2, argon2Params);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getKeyId() {
        return this.keyId;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getSalt() {
        return this.salt;
    }

    /* renamed from: component3, reason: from getter */
    public final Argon2Params getParams() {
        return this.params;
    }

    public final SessionInvite copy(byte[] keyId, byte[] salt, Argon2Params params) {
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        return new SessionInvite(keyId, salt, params);
    }

    public String toString() {
        return "SessionInvite(keyId=" + Arrays.toString(this.keyId) + ", salt=" + Arrays.toString(this.salt) + ", params=" + this.params + ")";
    }

    public SessionInvite(byte[] keyId, byte[] salt, Argon2Params params) {
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        this.keyId = keyId;
        this.salt = salt;
        this.params = params;
    }

    public final byte[] getKeyId() {
        return this.keyId;
    }

    public final byte[] getSalt() {
        return this.salt;
    }

    public final Argon2Params getParams() {
        return this.params;
    }

    public boolean equals(Object other) {
        if (!(other instanceof SessionInvite)) {
            return false;
        }
        SessionInvite sessionInvite = (SessionInvite) other;
        return Arrays.equals(this.keyId, sessionInvite.keyId) && Arrays.equals(this.salt, sessionInvite.salt) && Intrinsics.areEqual(this.params, sessionInvite.params);
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.keyId) * 31) + Arrays.hashCode(this.salt)) * 31) + this.params.hashCode();
    }
}
