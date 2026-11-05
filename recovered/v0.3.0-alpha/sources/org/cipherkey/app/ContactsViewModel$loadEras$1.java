package org.cipherkey.app;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.core.common.ContactId;

/* compiled from: ContactsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ContactsViewModel$loadEras$1", f = "ContactsViewModel.kt", i = {1}, l = {59, 61}, m = "invokeSuspend", n = {"list"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class ContactsViewModel$loadEras$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f138$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ boolean $notify;
    Object L$0;
    int label;
    final /* synthetic */ ContactsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContactsViewModel$loadEras$1(ContactsViewModel contactsViewModel, String str, boolean z, Continuation<? super ContactsViewModel$loadEras$1> continuation) {
        super(2, continuation);
        this.this$0 = contactsViewModel;
        this.f138$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
        this.$notify = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContactsViewModel$loadEras$1(this.this$0, this.f138$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$notify, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContactsViewModel$loadEras$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object value;
        List list;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.sessionKeys.m10694erasForContactovFUVq0(this.f138$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) this.L$0;
            ResultKt.throwOnFailure(obj);
            if (this.$notify && list.isEmpty()) {
                this.this$0._feedback.setValue(new UiNotice(R.string.feedback_no_era, null, 2, null));
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        List list2 = (List) obj;
        MutableStateFlow mutableStateFlow = this.this$0._eras;
        String str = this.f138$$v$c$orgcipherkeycorecommonContactId$contactId$0;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, MapsKt.plus((Map) value, TuplesKt.to(ContactId.m10637boximpl(str), list2))));
        this.L$0 = list2;
        this.label = 2;
        if (this.this$0.refreshUnlocked(this) != coroutine_suspended) {
            list = list2;
            if (this.$notify) {
                this.this$0._feedback.setValue(new UiNotice(R.string.feedback_no_era, null, 2, null));
            }
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }
}
