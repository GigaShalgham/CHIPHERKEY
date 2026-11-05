package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.app.R;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.sessions.TimeSourceKt;
import org.cipherkey.data.repository.UnlockOutcome;

/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$unlockThen$1", f = "CompanionViewModel.kt", i = {1}, l = {489, 498}, m = "invokeSuspend", n = {"outcome"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class CompanionViewModel$unlockThen$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f144$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $andThen;
    final /* synthetic */ char[] $passphrase;
    Object L$0;
    int label;
    final /* synthetic */ CompanionViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CompanionViewModel$unlockThen$1(CompanionViewModel companionViewModel, String str, char[] cArr, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super CompanionViewModel$unlockThen$1> continuation) {
        super(2, continuation);
        this.this$0 = companionViewModel;
        this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
        this.$passphrase = cArr;
        this.$andThen = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CompanionViewModel$unlockThen$1(this.this$0, this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$passphrase, this.$andThen, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CompanionViewModel$unlockThen$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x022c, code lost:
    
        if (r3.invoke(r27) == r1) goto L34;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object objM10690unlockContactexK5HwQ;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, true, false, null, 221183, null));
            this.label = 1;
            objM10690unlockContactexK5HwQ = this.this$0.messageCipher.m10690unlockContactexK5HwQ(this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$passphrase, this);
            if (objM10690unlockContactexK5HwQ != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.m10490offerRememberM53eizw(this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        objM10690unlockContactexK5HwQ = obj;
        UnlockOutcome unlockOutcome = (UnlockOutcome) objM10690unlockContactexK5HwQ;
        TimeSourceKt.wipe(this.$passphrase);
        if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.Unlocked.INSTANCE) || Intrinsics.areEqual(unlockOutcome, UnlockOutcome.AlreadyUnlocked.INSTANCE)) {
            this.this$0.lastUnlockedContactId = this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0;
            this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, SetsKt.plus(((CompanionUiState) this.this$0._state.getValue()).getUnlocked(), ContactId.m10637boximpl(this.f144$$v$c$orgcipherkeycorecommonContactId$contactId$0)), null, null, null, null, null, false, null, false, false, false, null, 261823, null));
            Function1<Continuation<? super Unit>, Object> function1 = this.$andThen;
            this.L$0 = SpillingKt.nullOutSpilledVariable(unlockOutcome);
            this.label = 2;
        } else {
            if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.WrongPassphrase.INSTANCE)) {
                this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_wrong_passphrase, null, 2, null), false, false, false, null, 221183, null));
            } else if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.NoSession.INSTANCE)) {
                this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_no_era, null, 2, null), false, false, false, null, 221183, null));
            } else if (unlockOutcome instanceof UnlockOutcome.Rejected) {
                this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_passphrase_rejected, ((UnlockOutcome.Rejected) unlockOutcome).getReason()), false, false, false, null, 221183, null));
            } else {
                if (!(unlockOutcome instanceof UnlockOutcome.Failed)) {
                    throw new NoWhenBranchMatchedException();
                }
                this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_unlock_failed, ((UnlockOutcome.Failed) unlockOutcome).getReason()), false, false, false, null, 221183, null));
            }
            return Unit.INSTANCE;
        }
    }
}
