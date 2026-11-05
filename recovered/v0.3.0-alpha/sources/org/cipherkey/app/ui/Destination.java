package org.cipherkey.app.ui;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CipherKeyApp.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lorg/cipherkey/app/ui/Destination;", "", "route", "", "labelRes", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "<init>", "(Ljava/lang/String;ILandroidx/compose/ui/graphics/vector/ImageVector;)V", "getRoute", "()Ljava/lang/String;", "getLabelRes", "()I", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* data */ class Destination {
    private final ImageVector icon;
    private final int labelRes;
    private final String route;

    public static /* synthetic */ Destination copy$default(Destination destination, String str, int i, ImageVector imageVector, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = destination.route;
        }
        if ((i2 & 2) != 0) {
            i = destination.labelRes;
        }
        if ((i2 & 4) != 0) {
            imageVector = destination.icon;
        }
        return destination.copy(str, i, imageVector);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRoute() {
        return this.route;
    }

    /* renamed from: component2, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }

    /* renamed from: component3, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    public final Destination copy(String route, int labelRes, ImageVector icon) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new Destination(route, labelRes, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) other;
        return Intrinsics.areEqual(this.route, destination.route) && this.labelRes == destination.labelRes && Intrinsics.areEqual(this.icon, destination.icon);
    }

    public int hashCode() {
        return (((this.route.hashCode() * 31) + Integer.hashCode(this.labelRes)) * 31) + this.icon.hashCode();
    }

    public String toString() {
        return "Destination(route=" + this.route + ", labelRes=" + this.labelRes + ", icon=" + this.icon + ")";
    }

    public Destination(String route, int i, ImageVector icon) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.route = route;
        this.labelRes = i;
        this.icon = icon;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }

    public final String getRoute() {
        return this.route;
    }
}
