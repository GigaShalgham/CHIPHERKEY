package org.cipherkey.app.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.ToolsViewModel;

/* compiled from: EncryptScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class EncryptScreenKt$EncryptScreen$2$4$1 extends FunctionReferenceImpl implements Function1<char[], Unit> {
    EncryptScreenKt$EncryptScreen$2$4$1(Object obj) {
        super(1, obj, ToolsViewModel.class, "unlockAndContinue", "unlockAndContinue([C)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(char[] cArr) {
        invoke2(cArr);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(char[] p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((ToolsViewModel) this.receiver).unlockAndContinue(p0);
    }
}
