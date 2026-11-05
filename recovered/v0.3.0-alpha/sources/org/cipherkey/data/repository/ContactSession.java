package org.cipherkey.data.repository;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.messages.InviteCodec;
import org.cipherkey.core.messages.SessionInvite;

/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010)\u001a\u00020\u000e2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b.\u0010\u0013J\u0010\u0010/\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b0\u0010\u0013J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\nHÆ\u0003J\t\u00104\u001a\u00020\fHÆ\u0003J\t\u00105\u001a\u00020\u000eHÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010 Jb\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0004\b8\u00109J\t\u0010:\u001a\u00020'HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0011\u0010\"\u001a\u00020#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010\u0013¨\u0006;"}, d2 = {"Lorg/cipherkey/data/repository/ContactSession;", "", "sessionId", "Lorg/cipherkey/core/common/SessionId;", "contactId", "Lorg/cipherkey/core/common/ContactId;", "keyId", "", "salt", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "createdAt", "", "hasWrappedKey", "", "lastUsedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;[B[BLorg/cipherkey/core/crypto/Argon2Params;JZLjava/lang/Long;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSessionId-AXtOyIM", "()Ljava/lang/String;", "Ljava/lang/String;", "getContactId-Y-aFaGk", "getKeyId", "()[B", "getSalt", "getParams", "()Lorg/cipherkey/core/crypto/Argon2Params;", "getCreatedAt", "()J", "getHasWrappedKey", "()Z", "getLastUsedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "invite", "Lorg/cipherkey/core/messages/SessionInvite;", "getInvite", "()Lorg/cipherkey/core/messages/SessionInvite;", "inviteCode", "", "getInviteCode", "equals", "other", "hashCode", "", "component1", "component1-AXtOyIM", "component2", "component2-Y-aFaGk", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "copy-FeDc7tQ", "(Ljava/lang/String;Ljava/lang/String;[B[BLorg/cipherkey/core/crypto/Argon2Params;JZLjava/lang/Long;)Lorg/cipherkey/data/repository/ContactSession;", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ContactSession {
    private final String contactId;
    private final long createdAt;
    private final boolean hasWrappedKey;
    private final byte[] keyId;
    private final Long lastUsedAt;
    private final Argon2Params params;
    private final byte[] salt;
    private final String sessionId;

    public /* synthetic */ ContactSession(String str, String str2, byte[] bArr, byte[] bArr2, Argon2Params argon2Params, long j, boolean z, Long l, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, bArr, bArr2, argon2Params, j, z, l);
    }

    /* renamed from: copy-FeDc7tQ$default, reason: not valid java name */
    public static /* synthetic */ ContactSession m10670copyFeDc7tQ$default(ContactSession contactSession, String str, String str2, byte[] bArr, byte[] bArr2, Argon2Params argon2Params, long j, boolean z, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contactSession.sessionId;
        }
        if ((i & 2) != 0) {
            str2 = contactSession.contactId;
        }
        if ((i & 4) != 0) {
            bArr = contactSession.keyId;
        }
        if ((i & 8) != 0) {
            bArr2 = contactSession.salt;
        }
        if ((i & 16) != 0) {
            argon2Params = contactSession.params;
        }
        if ((i & 32) != 0) {
            j = contactSession.createdAt;
        }
        if ((i & 64) != 0) {
            z = contactSession.hasWrappedKey;
        }
        if ((i & 128) != 0) {
            l = contactSession.lastUsedAt;
        }
        long j2 = j;
        byte[] bArr3 = bArr2;
        Argon2Params argon2Params2 = argon2Params;
        byte[] bArr4 = bArr;
        return contactSession.m10673copyFeDc7tQ(str, str2, bArr4, bArr3, argon2Params2, j2, z, l);
    }

    /* renamed from: component1-AXtOyIM, reason: not valid java name and from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: component2-Y-aFaGk, reason: not valid java name and from getter */
    public final String getContactId() {
        return this.contactId;
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
    public final Argon2Params getParams() {
        return this.params;
    }

    /* renamed from: component6, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getHasWrappedKey() {
        return this.hasWrappedKey;
    }

    /* renamed from: component8, reason: from getter */
    public final Long getLastUsedAt() {
        return this.lastUsedAt;
    }

    /* renamed from: copy-FeDc7tQ, reason: not valid java name */
    public final ContactSession m10673copyFeDc7tQ(String sessionId, String contactId, byte[] keyId, byte[] salt, Argon2Params params, long createdAt, boolean hasWrappedKey, Long lastUsedAt) {
        Intrinsics.checkNotNullParameter(sessionId, "$v$c$org-cipherkey-core-common-SessionId$-sessionId$0");
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        return new ContactSession(sessionId, contactId, keyId, salt, params, createdAt, hasWrappedKey, lastUsedAt, null);
    }

    public String toString() {
        return "ContactSession(sessionId=" + SessionId.m10649toStringimpl(this.sessionId) + ", contactId=" + ContactId.m10642toStringimpl(this.contactId) + ", keyId=" + Arrays.toString(this.keyId) + ", salt=" + Arrays.toString(this.salt) + ", params=" + this.params + ", createdAt=" + this.createdAt + ", hasWrappedKey=" + this.hasWrappedKey + ", lastUsedAt=" + this.lastUsedAt + ")";
    }

    private ContactSession(String sessionId, String contactId, byte[] keyId, byte[] salt, Argon2Params params, long j, boolean z, Long l) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(contactId, "contactId");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        this.sessionId = sessionId;
        this.contactId = contactId;
        this.keyId = keyId;
        this.salt = salt;
        this.params = params;
        this.createdAt = j;
        this.hasWrappedKey = z;
        this.lastUsedAt = l;
    }

    /* renamed from: getSessionId-AXtOyIM, reason: not valid java name */
    public final String m10675getSessionIdAXtOyIM() {
        return this.sessionId;
    }

    /* renamed from: getContactId-Y-aFaGk, reason: not valid java name */
    public final String m10674getContactIdYaFaGk() {
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

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final boolean getHasWrappedKey() {
        return this.hasWrappedKey;
    }

    public final Long getLastUsedAt() {
        return this.lastUsedAt;
    }

    public final SessionInvite getInvite() {
        return new SessionInvite(this.keyId, this.salt, this.params);
    }

    public final String getInviteCode() {
        return InviteCodec.INSTANCE.encode(getInvite());
    }

    public boolean equals(Object other) {
        if (!(other instanceof ContactSession)) {
            return false;
        }
        ContactSession contactSession = (ContactSession) other;
        return SessionId.m10647equalsimpl0(this.sessionId, contactSession.sessionId) && ContactId.m10640equalsimpl0(this.contactId, contactSession.contactId) && Arrays.equals(this.keyId, contactSession.keyId) && Arrays.equals(this.salt, contactSession.salt) && Intrinsics.areEqual(this.params, contactSession.params) && this.createdAt == contactSession.createdAt && this.hasWrappedKey == contactSession.hasWrappedKey && Intrinsics.areEqual(this.lastUsedAt, contactSession.lastUsedAt);
    }

    public int hashCode() {
        int iM10648hashCodeimpl = ((((((((((((SessionId.m10648hashCodeimpl(this.sessionId) * 31) + ContactId.m10641hashCodeimpl(this.contactId)) * 31) + Arrays.hashCode(this.keyId)) * 31) + Arrays.hashCode(this.salt)) * 31) + this.params.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Boolean.hashCode(this.hasWrappedKey)) * 31;
        Long l = this.lastUsedAt;
        return iM10648hashCodeimpl + (l != null ? Long.hashCode(l.longValue()) : 0);
    }
}
