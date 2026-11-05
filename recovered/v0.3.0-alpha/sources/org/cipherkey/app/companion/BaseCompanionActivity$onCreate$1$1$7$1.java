package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: BaseCompanionActivity.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class BaseCompanionActivity$onCreate$1$1$7$1 extends FunctionReferenceImpl implements Function0<Unit> {
    BaseCompanionActivity$onCreate$1$1$7$1(Object obj) {
        super(0, obj, BaseCompanionActivity.class, "finishWithResult", "finishWithResult()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((BaseCompanionActivity) this.receiver).finishWithResult();
    }
}
