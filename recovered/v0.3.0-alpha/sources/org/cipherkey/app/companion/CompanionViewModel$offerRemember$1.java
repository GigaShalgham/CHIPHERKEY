package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.bouncycastle.pqc.crypto.sqisign.SQIsignEncodeLvl3;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.data.preferences.AppSettings;

/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$offerRemember$1", f = "CompanionViewModel.kt", i = {}, l = {527, SQIsignEncodeLvl3.SECRETKEY_BYTES}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class CompanionViewModel$offerRemember$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f143$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    int label;
    final /* synthetic */ CompanionViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CompanionViewModel$offerRemember$1(CompanionViewModel companionViewModel, String str, Continuation<? super CompanionViewModel$offerRemember$1> continuation) {
        super(2, continuation);
        this.this$0 = companionViewModel;
        this.f143$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CompanionViewModel$offerRemember$1(this.this$0, this.f143$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CompanionViewModel$offerRemember$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0064, code lost:
    
        if (r2 == r1) goto L23;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object objFirst;
        Object objM10489currentBiometricContactKeyovFUVq0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            objFirst = FlowKt.first(this.this$0.settings.getSettings(), this);
            if (objFirst != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            objM10489currentBiometricContactKeyovFUVq0 = obj;
            String str = (String) objM10489currentBiometricContactKeyovFUVq0;
            if (str != null && !this.this$0.biometrics.hasWrapped(str)) {
                this.this$0._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) this.this$0._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, new BiometricPromptRequest.Remember(str), 131071, null));
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        objFirst = obj;
        if (((AppSettings) objFirst).getBiometricUnlockEnabled() && this.this$0.biometrics.isDeviceCapable()) {
            this.label = 2;
            objM10489currentBiometricContactKeyovFUVq0 = this.this$0.m10489currentBiometricContactKeyovFUVq0(this.f143$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
        }
        return Unit.INSTANCE;
    }
}
