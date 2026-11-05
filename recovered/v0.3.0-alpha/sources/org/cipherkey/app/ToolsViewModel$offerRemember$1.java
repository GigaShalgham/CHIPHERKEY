package org.cipherkey.app;

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
import kotlinx.coroutines.flow.MutableStateFlow;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.data.preferences.AppSettings;

/* compiled from: ToolsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$offerRemember$1", f = "ToolsViewModel.kt", i = {}, l = {386, 388}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class ToolsViewModel$offerRemember$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f142$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    int label;
    final /* synthetic */ ToolsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ToolsViewModel$offerRemember$1(ToolsViewModel toolsViewModel, String str, Continuation<? super ToolsViewModel$offerRemember$1> continuation) {
        super(2, continuation);
        this.this$0 = toolsViewModel;
        this.f142$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ToolsViewModel$offerRemember$1(this.this$0, this.f142$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ToolsViewModel$offerRemember$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        Object objM10468biometricContactKeyovFUVq0;
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
            objM10468biometricContactKeyovFUVq0 = obj;
            String str = (String) objM10468biometricContactKeyovFUVq0;
            if (str != null && !this.this$0.getBiometricStore().hasWrapped(str)) {
                MutableStateFlow mutableStateFlow = this.this$0._state;
                ToolsUiState toolsUiState = (ToolsUiState) this.this$0._state.getValue();
                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : false, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : new BiometricPromptRequest.Remember(str)));
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        objFirst = obj;
        if (((AppSettings) objFirst).getBiometricUnlockEnabled() && this.this$0.getBiometricStore().isDeviceCapable()) {
            this.label = 2;
            objM10468biometricContactKeyovFUVq0 = this.this$0.m10468biometricContactKeyovFUVq0(this.f142$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
        }
        return Unit.INSTANCE;
    }
}
