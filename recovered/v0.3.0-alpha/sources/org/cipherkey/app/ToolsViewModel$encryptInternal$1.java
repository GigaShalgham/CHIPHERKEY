package org.cipherkey.app;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.cipherkey.app.companion.CompanionMode;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.EncryptOutcome;

/* compiled from: ToolsViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$encryptInternal$1", f = "ToolsViewModel.kt", i = {1}, l = {296, 298}, m = "invokeSuspend", n = {"out"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class ToolsViewModel$encryptInternal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-id$0, reason: not valid java name */
    final /* synthetic */ String f141$$v$c$orgcipherkeycorecommonContactId$id$0;
    final /* synthetic */ String $draft;
    Object L$0;
    int label;
    final /* synthetic */ ToolsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ToolsViewModel$encryptInternal$1(ToolsViewModel toolsViewModel, String str, String str2, Continuation<? super ToolsViewModel$encryptInternal$1> continuation) {
        super(2, continuation);
        this.this$0 = toolsViewModel;
        this.f141$$v$c$orgcipherkeycorecommonContactId$id$0 = str;
        this.$draft = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ToolsViewModel$encryptInternal$1(this.this$0, this.f141$$v$c$orgcipherkeycorecommonContactId$id$0, this.$draft, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ToolsViewModel$encryptInternal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00bb  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object objM10686encryptDraftexK5HwQ;
        EncryptOutcome encryptOutcome;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow = this.this$0._state;
            ToolsUiState toolsUiState = (ToolsUiState) this.this$0._state.getValue();
            mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : true, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
            this.label = 1;
            objM10686encryptDraftexK5HwQ = this.this$0.messageCipher.m10686encryptDraftexK5HwQ(this.f141$$v$c$orgcipherkeycorecommonContactId$id$0, this.$draft, this);
            if (objM10686encryptDraftexK5HwQ != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            encryptOutcome = (EncryptOutcome) this.L$0;
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow2 = this.this$0._state;
            ToolsUiState toolsUiState2 = (ToolsUiState) this.this$0._state.getValue();
            String envelope = ((EncryptOutcome.Encrypted) encryptOutcome).getEnvelope();
            Contact selectedContact = ((ToolsUiState) this.this$0._state.getValue()).getSelectedContact();
            mutableStateFlow2.setValue(toolsUiState2.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState2.contacts : null, (16376 & 2) != 0 ? toolsUiState2.unlocked : null, (16376 & 4) != 0 ? toolsUiState2.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState2.resultText : envelope, (16376 & 16) != 0 ? toolsUiState2.resultContactName : selectedContact != null ? selectedContact.getDisplayName() : null, (16376 & 32) != 0 ? toolsUiState2.resultIsEnvelope : true, (16376 & 64) != 0 ? toolsUiState2.copied : false, (16376 & 128) != 0 ? toolsUiState2.busy : false, (16376 & 256) != 0 ? toolsUiState2.notice : null, (16376 & 512) != 0 ? toolsUiState2.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState2.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState2.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState2.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState2.biometricPrompt : null));
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        objM10686encryptDraftexK5HwQ = obj;
        EncryptOutcome encryptOutcome2 = (EncryptOutcome) objM10686encryptDraftexK5HwQ;
        if (encryptOutcome2 instanceof EncryptOutcome.Encrypted) {
            this.L$0 = encryptOutcome2;
            this.label = 2;
            if (this.this$0.settings.setLastContactId(this.f141$$v$c$orgcipherkeycorecommonContactId$id$0, this) != coroutine_suspended) {
                encryptOutcome = encryptOutcome2;
                MutableStateFlow mutableStateFlow22 = this.this$0._state;
                ToolsUiState toolsUiState22 = (ToolsUiState) this.this$0._state.getValue();
                String envelope2 = ((EncryptOutcome.Encrypted) encryptOutcome).getEnvelope();
                Contact selectedContact2 = ((ToolsUiState) this.this$0._state.getValue()).getSelectedContact();
                mutableStateFlow22.setValue(toolsUiState22.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState22.contacts : null, (16376 & 2) != 0 ? toolsUiState22.unlocked : null, (16376 & 4) != 0 ? toolsUiState22.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState22.resultText : envelope2, (16376 & 16) != 0 ? toolsUiState22.resultContactName : selectedContact2 != null ? selectedContact2.getDisplayName() : null, (16376 & 32) != 0 ? toolsUiState22.resultIsEnvelope : true, (16376 & 64) != 0 ? toolsUiState22.copied : false, (16376 & 128) != 0 ? toolsUiState22.busy : false, (16376 & 256) != 0 ? toolsUiState22.notice : null, (16376 & 512) != 0 ? toolsUiState22.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState22.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState22.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState22.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState22.biometricPrompt : null));
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
        if (encryptOutcome2 instanceof EncryptOutcome.NeedsUnlock) {
            this.this$0.pendingMode = CompanionMode.ENCRYPT;
            this.this$0.pendingText = this.$draft;
            MutableStateFlow mutableStateFlow3 = this.this$0._state;
            ToolsUiState toolsUiState3 = (ToolsUiState) this.this$0._state.getValue();
            String str = this.f141$$v$c$orgcipherkeycorecommonContactId$id$0;
            Contact selectedContact3 = ((ToolsUiState) this.this$0._state.getValue()).getSelectedContact();
            mutableStateFlow3.setValue(toolsUiState3.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState3.contacts : null, (16376 & 2) != 0 ? toolsUiState3.unlocked : null, (16376 & 4) != 0 ? toolsUiState3.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState3.resultText : null, (16376 & 16) != 0 ? toolsUiState3.resultContactName : null, (16376 & 32) != 0 ? toolsUiState3.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState3.copied : false, (16376 & 128) != 0 ? toolsUiState3.busy : false, (16376 & 256) != 0 ? toolsUiState3.notice : null, (16376 & 512) != 0 ? toolsUiState3.unlockNeeded : true, (16376 & 1024) != 0 ? toolsUiState3.pendingContactId : str, (16376 & 2048) != 0 ? toolsUiState3.unlockContactName : selectedContact3 != null ? selectedContact3.getDisplayName() : null, (16376 & 4096) != 0 ? toolsUiState3.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState3.biometricPrompt : null));
            this.this$0.refreshBiometricOffer();
        } else if (encryptOutcome2 instanceof EncryptOutcome.NoSession) {
            MutableStateFlow mutableStateFlow4 = this.this$0._state;
            ToolsUiState toolsUiState4 = (ToolsUiState) this.this$0._state.getValue();
            mutableStateFlow4.setValue(toolsUiState4.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState4.contacts : null, (16376 & 2) != 0 ? toolsUiState4.unlocked : null, (16376 & 4) != 0 ? toolsUiState4.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState4.resultText : null, (16376 & 16) != 0 ? toolsUiState4.resultContactName : null, (16376 & 32) != 0 ? toolsUiState4.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState4.copied : false, (16376 & 128) != 0 ? toolsUiState4.busy : false, (16376 & 256) != 0 ? toolsUiState4.notice : new UiNotice(R.string.notice_no_era, null, 2, null), (16376 & 512) != 0 ? toolsUiState4.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState4.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState4.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState4.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState4.biometricPrompt : null));
        } else if (encryptOutcome2 instanceof EncryptOutcome.EmptyDraft) {
            MutableStateFlow mutableStateFlow5 = this.this$0._state;
            ToolsUiState toolsUiState5 = (ToolsUiState) this.this$0._state.getValue();
            mutableStateFlow5.setValue(toolsUiState5.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState5.contacts : null, (16376 & 2) != 0 ? toolsUiState5.unlocked : null, (16376 & 4) != 0 ? toolsUiState5.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState5.resultText : null, (16376 & 16) != 0 ? toolsUiState5.resultContactName : null, (16376 & 32) != 0 ? toolsUiState5.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState5.copied : false, (16376 & 128) != 0 ? toolsUiState5.busy : false, (16376 & 256) != 0 ? toolsUiState5.notice : new UiNotice(R.string.notice_empty_input, null, 2, null), (16376 & 512) != 0 ? toolsUiState5.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState5.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState5.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState5.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState5.biometricPrompt : null));
        } else if (encryptOutcome2 instanceof EncryptOutcome.Failed) {
            MutableStateFlow mutableStateFlow6 = this.this$0._state;
            ToolsUiState toolsUiState6 = (ToolsUiState) this.this$0._state.getValue();
            mutableStateFlow6.setValue(toolsUiState6.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState6.contacts : null, (16376 & 2) != 0 ? toolsUiState6.unlocked : null, (16376 & 4) != 0 ? toolsUiState6.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState6.resultText : null, (16376 & 16) != 0 ? toolsUiState6.resultContactName : null, (16376 & 32) != 0 ? toolsUiState6.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState6.copied : false, (16376 & 128) != 0 ? toolsUiState6.busy : false, (16376 & 256) != 0 ? toolsUiState6.notice : new UiNotice(R.string.notice_encrypt_failed, ((EncryptOutcome.Failed) encryptOutcome2).getError().toString()), (16376 & 512) != 0 ? toolsUiState6.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState6.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState6.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState6.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState6.biometricPrompt : null));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }
}
