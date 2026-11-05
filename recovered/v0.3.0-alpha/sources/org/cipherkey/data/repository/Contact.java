package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.ContactId;

/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0013\u0010\fJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J8\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006 "}, d2 = {"Lorg/cipherkey/data/repository/Contact;", "", "id", "Lorg/cipherkey/core/common/ContactId;", "displayName", "", "createdAt", "", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getId-Y-aFaGk", "()Ljava/lang/String;", "Ljava/lang/String;", "getDisplayName", "getCreatedAt", "()J", "getUpdatedAt", "component1", "component1-Y-aFaGk", "component2", "component3", "component4", "copy", "copy--BuCuLQ", "(Ljava/lang/String;Ljava/lang/String;JJ)Lorg/cipherkey/data/repository/Contact;", "equals", "", "other", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Contact {
    private final long createdAt;
    private final String displayName;
    private final String id;
    private final long updatedAt;

    public /* synthetic */ Contact(String str, String str2, long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, j2);
    }

    /* renamed from: copy--BuCuLQ$default, reason: not valid java name */
    public static /* synthetic */ Contact m10662copyBuCuLQ$default(Contact contact, String str, String str2, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contact.id;
        }
        if ((i & 2) != 0) {
            str2 = contact.displayName;
        }
        if ((i & 4) != 0) {
            j = contact.createdAt;
        }
        if ((i & 8) != 0) {
            j2 = contact.updatedAt;
        }
        long j3 = j2;
        return contact.m10664copyBuCuLQ(str, str2, j, j3);
    }

    /* renamed from: component1-Y-aFaGk, reason: not valid java name and from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component3, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component4, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: copy--BuCuLQ, reason: not valid java name */
    public final Contact m10664copyBuCuLQ(String id, String displayName, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(id, "$v$c$org-cipherkey-core-common-ContactId$-id$0");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        return new Contact(id, displayName, createdAt, updatedAt, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) other;
        return ContactId.m10640equalsimpl0(this.id, contact.id) && Intrinsics.areEqual(this.displayName, contact.displayName) && this.createdAt == contact.createdAt && this.updatedAt == contact.updatedAt;
    }

    public int hashCode() {
        return (((((ContactId.m10641hashCodeimpl(this.id) * 31) + this.displayName.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "Contact(id=" + ContactId.m10642toStringimpl(this.id) + ", displayName=" + this.displayName + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
    }

    private Contact(String id, String displayName, long j, long j2) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        this.id = id;
        this.displayName = displayName;
        this.createdAt = j;
        this.updatedAt = j2;
    }

    /* renamed from: getId-Y-aFaGk, reason: not valid java name */
    public final String m10665getIdYaFaGk() {
        return this.id;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
