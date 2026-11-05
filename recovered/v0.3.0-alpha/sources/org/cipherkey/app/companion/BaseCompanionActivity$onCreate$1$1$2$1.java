package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.ContactId;

/* compiled from: BaseCompanionActivity.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class BaseCompanionActivity$onCreate$1$1$2$1 extends FunctionReferenceImpl implements Function1<ContactId, Unit> {
    BaseCompanionActivity$onCreate$1$1$2$1(Object obj) {
        super(1, obj, CompanionViewModel.class, "selectContact", "selectContact-M53eizw(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ContactId contactId) {
        m10477invokeM53eizw(contactId.m10643unboximpl());
        return Unit.INSTANCE;
    }

    /* renamed from: invoke-M53eizw, reason: not valid java name */
    public final void m10477invokeM53eizw(String str) {
        Intrinsics.checkNotNullParameter(str, "$v$c$org-cipherkey-core-common-ContactId$-p0$0");
        ((CompanionViewModel) this.receiver).m10492selectContactM53eizw(str);
    }
}
