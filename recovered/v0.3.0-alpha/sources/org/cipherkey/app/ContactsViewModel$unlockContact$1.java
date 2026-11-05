package org.cipherkey.app;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.core.sessions.TimeSourceKt;
import org.cipherkey.data.repository.UnlockOutcome;

/* compiled from: ContactsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ContactsViewModel$unlockContact$1", f = "ContactsViewModel.kt", i = {1, 2}, l = {116, 118, 119}, m = "invokeSuspend", n = {"outcome", "outcome"}, s = {"L$0", "L$0"}, v = 1)
/* loaded from: classes3.dex */
final class ContactsViewModel$unlockContact$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f140$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ Function1<UnlockOutcome, Unit> $onResult;
    final /* synthetic */ char[] $passphrase;
    Object L$0;
    int label;
    final /* synthetic */ ContactsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ContactsViewModel$unlockContact$1(ContactsViewModel contactsViewModel, String str, char[] cArr, Function1<? super UnlockOutcome, Unit> function1, Continuation<? super ContactsViewModel$unlockContact$1> continuation) {
        super(2, continuation);
        this.this$0 = contactsViewModel;
        this.f140$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
        this.$passphrase = cArr;
        this.$onResult = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContactsViewModel$unlockContact$1(this.this$0, this.f140$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$passphrase, this.$onResult, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContactsViewModel$unlockContact$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0070  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        UnlockOutcome unlockOutcome;
        UnlockOutcome unlockOutcome2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.messageCipher.m10690unlockContactexK5HwQ(this.f140$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$passphrase, this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                unlockOutcome2 = (UnlockOutcome) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.$onResult.invoke(unlockOutcome2);
                return Unit.INSTANCE;
            }
            UnlockOutcome unlockOutcome3 = (UnlockOutcome) this.L$0;
            ResultKt.throwOnFailure(obj);
            unlockOutcome = unlockOutcome3;
            this.L$0 = unlockOutcome;
            this.label = 3;
            if (this.this$0.m10453refreshFingerprintovFUVq0(this.f140$$v$c$orgcipherkeycorecommonContactId$contactId$0, this) != coroutine_suspended) {
                unlockOutcome2 = unlockOutcome;
                this.$onResult.invoke(unlockOutcome2);
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
        ResultKt.throwOnFailure(obj);
        unlockOutcome = (UnlockOutcome) obj;
        TimeSourceKt.wipe(this.$passphrase);
        this.L$0 = unlockOutcome;
        this.label = 2;
        if (this.this$0.refreshUnlocked(this) != coroutine_suspended) {
            this.L$0 = unlockOutcome;
            this.label = 3;
            if (this.this$0.m10453refreshFingerprintovFUVq0(this.f140$$v$c$orgcipherkeycorecommonContactId$contactId$0, this) != coroutine_suspended) {
            }
        }
        return coroutine_suspended;
    }
}
