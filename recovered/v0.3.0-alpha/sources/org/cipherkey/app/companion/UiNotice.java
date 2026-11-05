package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/cipherkey/app/companion/UiNotice;", "", "res", "", "arg", "", "<init>", "(ILjava/lang/String;)V", "getRes", "()I", "getArg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UiNotice {
    public static final int $stable = 0;
    private final String arg;
    private final int res;

    public static /* synthetic */ UiNotice copy$default(UiNotice uiNotice, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = uiNotice.res;
        }
        if ((i2 & 2) != 0) {
            str = uiNotice.arg;
        }
        return uiNotice.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getRes() {
        return this.res;
    }

    /* renamed from: component2, reason: from getter */
    public final String getArg() {
        return this.arg;
    }

    public final UiNotice copy(int res, String arg) {
        return new UiNotice(res, arg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UiNotice)) {
            return false;
        }
        UiNotice uiNotice = (UiNotice) other;
        return this.res == uiNotice.res && Intrinsics.areEqual(this.arg, uiNotice.arg);
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.res) * 31;
        String str = this.arg;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "UiNotice(res=" + this.res + ", arg=" + this.arg + ")";
    }

    public UiNotice(int i, String str) {
        this.res = i;
        this.arg = str;
    }

    public /* synthetic */ UiNotice(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : str);
    }

    public final String getArg() {
        return this.arg;
    }

    public final int getRes() {
        return this.res;
    }
}
