package org.cipherkey.data.database;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Entities.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010%\u001a\u00020\tH\u0016J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\tHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u000eHÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010 Jv\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u00101J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 ¨\u00063"}, d2 = {"Lorg/cipherkey/data/database/SessionKeyEntity;", "", "sessionId", "", "contactId", "keyId", "", "salt", "memoryExponent", "", "iterations", "parallelism", "wrappedKey", "createdAt", "", "lastUsedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;[B[BIII[BJLjava/lang/Long;)V", "getSessionId", "()Ljava/lang/String;", "getContactId", "getKeyId", "()[B", "getSalt", "getMemoryExponent", "()I", "getIterations", "getParallelism", "getWrappedKey", "getCreatedAt", "()J", "getLastUsedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "equals", "", "other", "hashCode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;[B[BIII[BJLjava/lang/Long;)Lorg/cipherkey/data/database/SessionKeyEntity;", "toString", "database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SessionKeyEntity {
    private final String contactId;
    private final long createdAt;
    private final int iterations;
    private final byte[] keyId;
    private final Long lastUsedAt;
    private final int memoryExponent;
    private final int parallelism;
    private final byte[] salt;
    private final String sessionId;
    private final byte[] wrappedKey;

    public static /* synthetic */ SessionKeyEntity copy$default(SessionKeyEntity sessionKeyEntity, String str, String str2, byte[] bArr, byte[] bArr2, int i, int i2, int i3, byte[] bArr3, long j, Long l, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = sessionKeyEntity.sessionId;
        }
        if ((i4 & 2) != 0) {
            str2 = sessionKeyEntity.contactId;
        }
        if ((i4 & 4) != 0) {
            bArr = sessionKeyEntity.keyId;
        }
        if ((i4 & 8) != 0) {
            bArr2 = sessionKeyEntity.salt;
        }
        if ((i4 & 16) != 0) {
            i = sessionKeyEntity.memoryExponent;
        }
        if ((i4 & 32) != 0) {
            i2 = sessionKeyEntity.iterations;
        }
        if ((i4 & 64) != 0) {
            i3 = sessionKeyEntity.parallelism;
        }
        if ((i4 & 128) != 0) {
            bArr3 = sessionKeyEntity.wrappedKey;
        }
        if ((i4 & 256) != 0) {
            j = sessionKeyEntity.createdAt;
        }
        if ((i4 & 512) != 0) {
            l = sessionKeyEntity.lastUsedAt;
        }
        Long l2 = l;
        long j2 = j;
        int i5 = i3;
        byte[] bArr4 = bArr3;
        int i6 = i;
        int i7 = i2;
        return sessionKeyEntity.copy(str, str2, bArr, bArr2, i6, i7, i5, bArr4, j2, l2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: component10, reason: from getter */
    public final Long getLastUsedAt() {
        return this.lastUsedAt;
    }

    /* renamed from: component2, reason: from getter */
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
    public final int getMemoryExponent() {
        return this.memoryExponent;
    }

    /* renamed from: component6, reason: from getter */
    public final int getIterations() {
        return this.iterations;
    }

    /* renamed from: component7, reason: from getter */
    public final int getParallelism() {
        return this.parallelism;
    }

    /* renamed from: component8, reason: from getter */
    public final byte[] getWrappedKey() {
        return this.wrappedKey;
    }

    /* renamed from: component9, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final SessionKeyEntity copy(String sessionId, String contactId, byte[] keyId, byte[] salt, int memoryExponent, int iterations, int parallelism, byte[] wrappedKey, long createdAt, Long lastUsedAt) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(contactId, "contactId");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        return new SessionKeyEntity(sessionId, contactId, keyId, salt, memoryExponent, iterations, parallelism, wrappedKey, createdAt, lastUsedAt);
    }

    public String toString() {
        return "SessionKeyEntity(sessionId=" + this.sessionId + ", contactId=" + this.contactId + ", keyId=" + Arrays.toString(this.keyId) + ", salt=" + Arrays.toString(this.salt) + ", memoryExponent=" + this.memoryExponent + ", iterations=" + this.iterations + ", parallelism=" + this.parallelism + ", wrappedKey=" + Arrays.toString(this.wrappedKey) + ", createdAt=" + this.createdAt + ", lastUsedAt=" + this.lastUsedAt + ")";
    }

    public SessionKeyEntity(String sessionId, String contactId, byte[] keyId, byte[] salt, int i, int i2, int i3, byte[] bArr, long j, Long l) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(contactId, "contactId");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        this.sessionId = sessionId;
        this.contactId = contactId;
        this.keyId = keyId;
        this.salt = salt;
        this.memoryExponent = i;
        this.iterations = i2;
        this.parallelism = i3;
        this.wrappedKey = bArr;
        this.createdAt = j;
        this.lastUsedAt = l;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getContactId() {
        return this.contactId;
    }

    public final byte[] getKeyId() {
        return this.keyId;
    }

    public final byte[] getSalt() {
        return this.salt;
    }

    public final int getMemoryExponent() {
        return this.memoryExponent;
    }

    public final int getIterations() {
        return this.iterations;
    }

    public final int getParallelism() {
        return this.parallelism;
    }

    public final byte[] getWrappedKey() {
        return this.wrappedKey;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final Long getLastUsedAt() {
        return this.lastUsedAt;
    }

    public boolean equals(Object other) {
        if (other instanceof SessionKeyEntity) {
            SessionKeyEntity sessionKeyEntity = (SessionKeyEntity) other;
            if (Intrinsics.areEqual(this.sessionId, sessionKeyEntity.sessionId) && Intrinsics.areEqual(this.contactId, sessionKeyEntity.contactId) && Arrays.equals(this.keyId, sessionKeyEntity.keyId) && Arrays.equals(this.salt, sessionKeyEntity.salt) && this.memoryExponent == sessionKeyEntity.memoryExponent && this.iterations == sessionKeyEntity.iterations && this.parallelism == sessionKeyEntity.parallelism) {
                byte[] bArr = this.wrappedKey;
                if ((bArr != null ? Arrays.equals(bArr, sessionKeyEntity.wrappedKey) : sessionKeyEntity.wrappedKey == null) && this.createdAt == sessionKeyEntity.createdAt && Intrinsics.areEqual(this.lastUsedAt, sessionKeyEntity.lastUsedAt)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.sessionId.hashCode() * 31) + this.contactId.hashCode()) * 31) + Arrays.hashCode(this.keyId)) * 31) + Arrays.hashCode(this.salt)) * 31) + this.memoryExponent) * 31) + this.iterations) * 31) + this.parallelism) * 31;
        byte[] bArr = this.wrappedKey;
        int iHashCode2 = (((iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31) + Long.hashCode(this.createdAt)) * 31;
        Long l = this.lastUsedAt;
        return iHashCode2 + (l != null ? Long.hashCode(l.longValue()) : 0);
    }
}
