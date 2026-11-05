package org.cipherkey.core.common;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Ids.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\t\u0010\u0005J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0013"}, d2 = {"Lorg/cipherkey/core/common/SessionId;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "toString", "toString-impl", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class SessionId {
    private final String value;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SessionId m10644boximpl(String str) {
        return new SessionId(str);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m10646equalsimpl(String str, Object obj) {
        return (obj instanceof SessionId) && Intrinsics.areEqual(str, ((SessionId) obj).m10650unboximpl());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m10647equalsimpl0(String str, String str2) {
        return Intrinsics.areEqual(str, str2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m10648hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m10649toStringimpl(String str) {
        return str;
    }

    public boolean equals(Object obj) {
        return m10646equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m10648hashCodeimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m10650unboximpl() {
        return this.value;
    }

    private /* synthetic */ SessionId(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static String m10645constructorimpl(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value.length() > 0) {
            return value;
        }
        throw new IllegalArgumentException("SessionId must not be empty".toString());
    }

    public String toString() {
        return m10649toStringimpl(this.value);
    }
}
