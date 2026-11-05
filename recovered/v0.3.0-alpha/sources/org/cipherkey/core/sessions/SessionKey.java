package org.cipherkey.core.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.keys.KeyFingerprint;

/* compiled from: SessionKey.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u001b\u001a\u00020\u0019H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/cipherkey/core/sessions/SessionKey;", "", "sessionId", "Lorg/cipherkey/core/common/SessionId;", "contactId", "Lorg/cipherkey/core/common/ContactId;", "keyId", "", "salt", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "keyBytes", "<init>", "(Ljava/lang/String;Ljava/lang/String;[B[BLorg/cipherkey/core/crypto/Argon2Params;[BLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSessionId-AXtOyIM", "()Ljava/lang/String;", "Ljava/lang/String;", "getContactId-Y-aFaGk", "getKeyId", "()[B", "getSalt", "getParams", "()Lorg/cipherkey/core/crypto/Argon2Params;", "getKeyBytes", "fingerprint", "", "getFingerprint", "toString", "sessions"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionKey {
    private final String contactId;
    private final byte[] keyBytes;
    private final byte[] keyId;
    private final Argon2Params params;
    private final byte[] salt;
    private final String sessionId;

    public /* synthetic */ SessionKey(String str, String str2, byte[] bArr, byte[] bArr2, Argon2Params argon2Params, byte[] bArr3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, bArr, bArr2, argon2Params, bArr3);
    }

    private SessionKey(String sessionId, String contactId, byte[] keyId, byte[] salt, Argon2Params params, byte[] keyBytes) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(contactId, "contactId");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(keyBytes, "keyBytes");
        this.sessionId = sessionId;
        this.contactId = contactId;
        this.keyId = keyId;
        this.salt = salt;
        this.params = params;
        this.keyBytes = keyBytes;
    }

    /* renamed from: getSessionId-AXtOyIM, reason: not valid java name and from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: getContactId-Y-aFaGk, reason: not valid java name and from getter */
    public final String getContactId() {
        return this.contactId;
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

    public final byte[] getKeyBytes() {
        return this.keyBytes;
    }

    public final String getFingerprint() {
        return KeyFingerprint.INSTANCE.of(this.keyBytes);
    }

    public String toString() {
        return "SessionKey(sessionId=" + SessionId.m10649toStringimpl(this.sessionId) + ", contactId=" + ContactId.m10642toStringimpl(this.contactId) + ", fingerprint=" + getFingerprint() + ")";
    }
}
