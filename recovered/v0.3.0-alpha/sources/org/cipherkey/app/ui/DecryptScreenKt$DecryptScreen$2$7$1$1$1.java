package org.cipherkey.app.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.cipherkey.app.ToolsViewModel;

/* compiled from: DecryptScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class DecryptScreenKt$DecryptScreen$2$7$1$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    DecryptScreenKt$DecryptScreen$2$7$1$1$1(Object obj) {
        super(0, obj, ToolsViewModel.class, "copyResult", "copyResult()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((ToolsViewModel) this.receiver).copyResult();
    }
}
