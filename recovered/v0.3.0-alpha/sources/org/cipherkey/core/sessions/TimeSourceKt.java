package org.cipherkey.core.sessions;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TimeSource.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0019\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"wipe", "", "", "sessions"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TimeSourceKt {
    public static final void wipe(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        Arrays.fill(cArr, (char) 0);
    }
}
