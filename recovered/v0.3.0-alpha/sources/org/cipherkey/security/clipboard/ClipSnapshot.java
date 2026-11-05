package org.cipherkey.security.clipboard;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClipboardAccess.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0015"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipSnapshot;", "", "mimeTypes", "", "", "items", "", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMimeTypes", "()Ljava/util/List;", "getItems", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ClipSnapshot {
    private final List<CharSequence> items;
    private final List<String> mimeTypes;

    /* JADX WARN: Multi-variable type inference failed */
    public ClipSnapshot() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClipSnapshot copy$default(ClipSnapshot clipSnapshot, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = clipSnapshot.mimeTypes;
        }
        if ((i & 2) != 0) {
            list2 = clipSnapshot.items;
        }
        return clipSnapshot.copy(list, list2);
    }

    public final List<String> component1() {
        return this.mimeTypes;
    }

    public final List<CharSequence> component2() {
        return this.items;
    }

    public final ClipSnapshot copy(List<String> mimeTypes, List<? extends CharSequence> items) {
        Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
        Intrinsics.checkNotNullParameter(items, "items");
        return new ClipSnapshot(mimeTypes, items);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClipSnapshot)) {
            return false;
        }
        ClipSnapshot clipSnapshot = (ClipSnapshot) other;
        return Intrinsics.areEqual(this.mimeTypes, clipSnapshot.mimeTypes) && Intrinsics.areEqual(this.items, clipSnapshot.items);
    }

    public int hashCode() {
        return (this.mimeTypes.hashCode() * 31) + this.items.hashCode();
    }

    public String toString() {
        return "ClipSnapshot(mimeTypes=" + this.mimeTypes + ", items=" + this.items + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ClipSnapshot(List<String> mimeTypes, List<? extends CharSequence> items) {
        Intrinsics.checkNotNullParameter(mimeTypes, "mimeTypes");
        Intrinsics.checkNotNullParameter(items, "items");
        this.mimeTypes = mimeTypes;
        this.items = items;
    }

    public /* synthetic */ ClipSnapshot(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final List<String> getMimeTypes() {
        return this.mimeTypes;
    }

    public final List<CharSequence> getItems() {
        return this.items;
    }
}
