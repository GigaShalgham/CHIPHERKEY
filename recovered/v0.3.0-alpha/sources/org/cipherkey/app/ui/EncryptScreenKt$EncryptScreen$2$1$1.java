package org.cipherkey.app.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.ToolsViewModel;
import org.cipherkey.core.common.ContactId;

/* compiled from: EncryptScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
final /* synthetic */ class EncryptScreenKt$EncryptScreen$2$1$1 extends FunctionReferenceImpl implements Function1<ContactId, Unit> {
    EncryptScreenKt$EncryptScreen$2$1$1(Object obj) {
        super(1, obj, ToolsViewModel.class, "selectContact", "selectContact-M53eizw(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ContactId contactId) {
        m10580invokeM53eizw(contactId.m10643unboximpl());
        return Unit.INSTANCE;
    }

    /* renamed from: invoke-M53eizw, reason: not valid java name */
    public final void m10580invokeM53eizw(String str) {
        Intrinsics.checkNotNullParameter(str, "$v$c$org-cipherkey-core-common-ContactId$-p0$0");
        ((ToolsViewModel) this.receiver).m10472selectContactM53eizw(str);
    }
}
