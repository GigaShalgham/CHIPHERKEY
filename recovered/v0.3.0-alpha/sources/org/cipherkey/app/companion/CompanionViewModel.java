package org.cipherkey.app.companion;

import androidx.core.view.InputDeviceCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.CompanionUiState;
import org.cipherkey.app.companion.ProcessTextContract;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.sessions.TimeSourceKt;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.DecryptOutcome;
import org.cipherkey.data.repository.EncryptOutcome;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.data.repository.UnlockOutcome;
import org.cipherkey.security.clipboard.ClipboardAccess;
import org.cipherkey.security.clipboard.ClipboardRead;

/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ \u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u0016J\u001e\u0010&\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0016J\u0015\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001e¢\u0006\u0004\b)\u0010*J\u0006\u0010+\u001a\u00020\u001aJ\u0018\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u0016J\u0006\u00100\u001a\u00020\u001aJ\u000e\u00101\u001a\u00020\u001a2\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u00020\u001a2\u0006\u00102\u001a\u000203J\u0006\u00105\u001a\u00020\u001aJ\u0010\u00106\u001a\u00020\u001a2\b\u00107\u001a\u0004\u0018\u000108J\u0010\u00109\u001a\u0004\u0018\u000108H\u0086@¢\u0006\u0002\u0010:J\u000e\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u0016J\u0006\u0010=\u001a\u00020\u001aJ\u000e\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020\u001aJ \u0010B\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0016H\u0002J \u0010C\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0016H\u0002J\u001a\u0010D\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u0016H\u0002J\u0016\u0010E\u001a\u00020\u001a2\u0006\u0010F\u001a\u00020\"H\u0082@¢\u0006\u0002\u0010GJ=\u0010H\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020\u001e2\u0006\u00102\u001a\u0002032\u001c\u0010J\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0018H\u0002¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020\u001eH\u0002¢\u0006\u0004\bN\u0010*J\b\u0010O\u001a\u00020\u001aH\u0002J\u001a\u0010P\u001a\u0004\u0018\u00010\"2\u0006\u0010I\u001a\u00020\u001eH\u0082@¢\u0006\u0004\bQ\u0010GJ\u0010\u0010P\u001a\u0004\u0018\u00010\"H\u0082@¢\u0006\u0002\u0010:R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0017\u001a\u001a\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001f¨\u0006R"}, d2 = {"Lorg/cipherkey/app/companion/CompanionViewModel;", "Landroidx/lifecycle/ViewModel;", "messageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "contactsRepository", "Lorg/cipherkey/data/repository/ContactRepository;", "settings", "Lorg/cipherkey/data/preferences/SettingsRepository;", "clipboard", "Lorg/cipherkey/security/clipboard/ClipboardAccess;", "biometrics", "Lorg/cipherkey/app/security/BiometricKeyStore;", "<init>", "(Lorg/cipherkey/data/repository/MessageCipher;Lorg/cipherkey/data/repository/ContactRepository;Lorg/cipherkey/data/preferences/SettingsRepository;Lorg/cipherkey/security/clipboard/ClipboardAccess;Lorg/cipherkey/app/security/BiometricKeyStore;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/cipherkey/app/companion/CompanionUiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "begun", "", "afterUnlock", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/jvm/functions/Function1;", "lastUnlockedContactId", "Lorg/cipherkey/core/common/ContactId;", "Ljava/lang/String;", "beginEncrypt", TextBundle.TEXT_ENTRY, "", "source", "Lorg/cipherkey/app/companion/TextSource;", "readOnly", "beginDecrypt", "selectContact", "id", "selectContact-M53eizw", "(Ljava/lang/String;)V", "dismissNotice", "showNotice", "notice", "Lorg/cipherkey/app/companion/UiNotice;", "retryable", "encryptNow", "unlockAndEncrypt", "passphrase", "", "unlockAndDecrypt", "requestBiometricUnlock", "onBiometricUnlockResult", "keyBytes", "", "consumeRememberKey", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRememberDone", "success", "copyResult", "beginFromClipboard", "mode", "Lorg/cipherkey/app/companion/CompanionMode;", "retryClipboard", "startEncryptFlow", "startDecryptFlow", "clipboardNotice", "decryptInternal", "envelope", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlockThen", "contactId", "andThen", "unlockThen-exK5HwQ", "(Ljava/lang/String;[CLkotlin/jvm/functions/Function1;)V", "offerRemember", "offerRemember-M53eizw", "refreshBiometricOffer", "currentBiometricContactKey", "currentBiometricContactKey-ovFUVq0", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CompanionViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<CompanionUiState> _state;
    private Function1<? super Continuation<? super Unit>, ? extends Object> afterUnlock;
    private final MutableStateFlow<Boolean> begun;
    private final BiometricKeyStore biometrics;
    private final ClipboardAccess clipboard;
    private final ContactRepository contactsRepository;
    private String lastUnlockedContactId;
    private final MessageCipher messageCipher;
    private final SettingsRepository settings;
    private final StateFlow<CompanionUiState> state;

    /* compiled from: CompanionViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel", f = "CompanionViewModel.kt", i = {0}, l = {553}, m = "currentBiometricContactKey-ovFUVq0", n = {"$v$c$org-cipherkey-core-common-ContactId$-contactId$0"}, s = {"L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$currentBiometricContactKey$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CompanionViewModel.this.m10489currentBiometricContactKeyovFUVq0(null, this);
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel", f = "CompanionViewModel.kt", i = {0}, l = {441}, m = "decryptInternal", n = {"envelope"}, s = {"L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$decryptInternal$1, reason: invalid class name and case insensitive filesystem */
    static final class C04991 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C04991(Continuation<? super C04991> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CompanionViewModel.this.decryptInternal(null, this);
        }
    }

    @Inject
    public CompanionViewModel(MessageCipher messageCipher, ContactRepository contactsRepository, SettingsRepository settings, ClipboardAccess clipboard, BiometricKeyStore biometrics) {
        Intrinsics.checkNotNullParameter(messageCipher, "messageCipher");
        Intrinsics.checkNotNullParameter(contactsRepository, "contactsRepository");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(clipboard, "clipboard");
        Intrinsics.checkNotNullParameter(biometrics, "biometrics");
        this.messageCipher = messageCipher;
        this.contactsRepository = contactsRepository;
        this.settings = settings;
        this.clipboard = clipboard;
        this.biometrics = biometrics;
        MutableStateFlow<CompanionUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new CompanionUiState(null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, null, 262143, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        this.begun = StateFlowKt.MutableStateFlow(false);
    }

    public final StateFlow<CompanionUiState> getState() {
        return this.state;
    }

    public static /* synthetic */ void beginEncrypt$default(CompanionViewModel companionViewModel, String str, TextSource textSource, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        companionViewModel.beginEncrypt(str, textSource, z);
    }

    public final void beginEncrypt(String text, TextSource source, boolean readOnly) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.begun.compareAndSet(false, true)) {
            startEncryptFlow(text, source, readOnly);
        }
    }

    public final void beginDecrypt(String text, TextSource source, boolean readOnly) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(source, "source");
        if (this.begun.compareAndSet(false, true)) {
            startDecryptFlow(text, source, readOnly);
        }
    }

    /* renamed from: selectContact-M53eizw, reason: not valid java name */
    public final void m10492selectContactM53eizw(String id) {
        Intrinsics.checkNotNullParameter(id, "$v$c$org-cipherkey-core-common-ContactId$-id$0");
        MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
        mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, id, null, null, null, null, false, null, false, false, false, null, 253823, null));
        refreshBiometricOffer();
    }

    public final void dismissNotice() {
        MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
        mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, null, 253951, null));
    }

    public static /* synthetic */ void showNotice$default(CompanionViewModel companionViewModel, UiNotice uiNotice, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        companionViewModel.showNotice(uiNotice, z);
    }

    public final void showNotice(UiNotice notice, boolean retryable) {
        Intrinsics.checkNotNullParameter(notice, "notice");
        if (this.begun.compareAndSet(false, true)) {
            this._state.setValue(new CompanionUiState(this._state.getValue().getMode(), this._state.getValue().getSource(), CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, notice, retryable, false, false, null, 237560, null));
        }
    }

    public final void encryptNow() {
        String incoming;
        CompanionUiState value = this._state.getValue();
        String strM10486getSelectedContactIdP5lcXoU = value.m10486getSelectedContactIdP5lcXoU();
        if (strM10486getSelectedContactIdP5lcXoU == null || (incoming = value.getIncoming()) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05001(strM10486getSelectedContactIdP5lcXoU, incoming, value, null), 3, null);
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$encryptNow$1", f = "CompanionViewModel.kt", i = {1}, l = {170, 172}, m = "invokeSuspend", n = {"out"}, s = {"L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$encryptNow$1, reason: invalid class name and case insensitive filesystem */
    static final class C05001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $draft;
        final /* synthetic */ String $id;
        final /* synthetic */ CompanionUiState $s;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05001(String str, String str2, CompanionUiState companionUiState, Continuation<? super C05001> continuation) {
            super(2, continuation);
            this.$id = str;
            this.$draft = str2;
            this.$s = companionUiState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CompanionViewModel.this.new C05001(this.$id, this.$draft, this.$s, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00bc  */
        /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String, kotlin.jvm.internal.DefaultConstructorMarker] */
        /* JADX WARN: Type inference failed for: r5v3 */
        /* JADX WARN: Type inference failed for: r5v8 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object objM10686encryptDraftexK5HwQ;
            EncryptOutcome encryptOutcome;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 2;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, true, false, null, 221183, null));
                this.label = 1;
                objM10686encryptDraftexK5HwQ = CompanionViewModel.this.messageCipher.m10686encryptDraftexK5HwQ(this.$id, this.$draft, this);
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
                MutableStateFlow mutableStateFlow = CompanionViewModel.this._state;
                CompanionUiState companionUiState = (CompanionUiState) CompanionViewModel.this._state.getValue();
                CompanionUiState.Phase phase = CompanionUiState.Phase.Result;
                String envelope = ((EncryptOutcome.Encrypted) encryptOutcome).getEnvelope();
                Contact selectedContact = this.$s.getSelectedContact();
                mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(companionUiState, null, null, phase, null, false, null, null, null, null, null, envelope, selectedContact != null ? selectedContact.getDisplayName() : null, false, null, false, false, false, null, 222203, null));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            objM10686encryptDraftexK5HwQ = obj;
            EncryptOutcome encryptOutcome2 = (EncryptOutcome) objM10686encryptDraftexK5HwQ;
            if (encryptOutcome2 instanceof EncryptOutcome.Encrypted) {
                this.L$0 = encryptOutcome2;
                this.label = 2;
                if (CompanionViewModel.this.settings.setLastContactId(this.$id, this) != coroutine_suspended) {
                    encryptOutcome = encryptOutcome2;
                    MutableStateFlow mutableStateFlow2 = CompanionViewModel.this._state;
                    CompanionUiState companionUiState2 = (CompanionUiState) CompanionViewModel.this._state.getValue();
                    CompanionUiState.Phase phase2 = CompanionUiState.Phase.Result;
                    String envelope2 = ((EncryptOutcome.Encrypted) encryptOutcome).getEnvelope();
                    Contact selectedContact2 = this.$s.getSelectedContact();
                    mutableStateFlow2.setValue(CompanionUiState.m10481copygvgolLs$default(companionUiState2, null, null, phase2, null, false, null, null, null, null, null, envelope2, selectedContact2 != null ? selectedContact2.getDisplayName() : null, false, null, false, false, false, null, 222203, null));
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
            if (encryptOutcome2 instanceof EncryptOutcome.NeedsUnlock) {
                CompanionViewModel.this.afterUnlock = new C00871(CompanionViewModel.this, null);
                MutableStateFlow mutableStateFlow3 = CompanionViewModel.this._state;
                CompanionUiState companionUiState3 = (CompanionUiState) CompanionViewModel.this._state.getValue();
                CompanionUiState.Phase phase3 = CompanionUiState.Phase.NeedsUnlock;
                Contact selectedContact3 = this.$s.getSelectedContact();
                mutableStateFlow3.setValue(CompanionUiState.m10481copygvgolLs$default(companionUiState3, null, null, phase3, null, false, null, null, null, this.$id, selectedContact3 != null ? selectedContact3.getDisplayName() : 0, null, null, false, null, false, false, false, null, 228603, null));
                CompanionViewModel.this.refreshBiometricOffer();
            } else if (encryptOutcome2 instanceof EncryptOutcome.NoSession) {
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_no_era, str, i2, str), false, false, false, null, 221183, null));
            } else if (encryptOutcome2 instanceof EncryptOutcome.EmptyDraft) {
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_nothing_to_encrypt, str, i2, str), false, false, false, null, 221183, null));
            } else {
                if (!(encryptOutcome2 instanceof EncryptOutcome.Failed)) {
                    throw new NoWhenBranchMatchedException();
                }
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_encrypt_failed, ((EncryptOutcome.Failed) encryptOutcome2).getError().toString()), false, false, false, null, 221183, null));
            }
            return Unit.INSTANCE;
        }

        /* compiled from: CompanionViewModel.kt */
        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$encryptNow$1$1", f = "CompanionViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        /* renamed from: org.cipherkey.app.companion.CompanionViewModel$encryptNow$1$1, reason: invalid class name and collision with other inner class name */
        static final class C00871 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ CompanionViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00871(CompanionViewModel companionViewModel, Continuation<? super C00871> continuation) {
                super(1, continuation);
                this.this$0 = companionViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new C00871(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Continuation<? super Unit> continuation) {
                return ((C00871) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.encryptNow();
                return Unit.INSTANCE;
            }
        }
    }

    public final void unlockAndEncrypt(char[] passphrase) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        String strM10486getSelectedContactIdP5lcXoU = this._state.getValue().m10486getSelectedContactIdP5lcXoU();
        if (strM10486getSelectedContactIdP5lcXoU == null) {
            TimeSourceKt.wipe(passphrase);
        } else {
            m10491unlockThenexK5HwQ(strM10486getSelectedContactIdP5lcXoU, passphrase, new C05071(null));
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$unlockAndEncrypt$1", f = "CompanionViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$unlockAndEncrypt$1, reason: invalid class name and case insensitive filesystem */
    static final class C05071 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;

        C05071(Continuation<? super C05071> continuation) {
            super(1, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return CompanionViewModel.this.new C05071(continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C05071) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CompanionViewModel.this.encryptNow();
            return Unit.INSTANCE;
        }
    }

    public final void unlockAndDecrypt(char[] passphrase) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        String strM10485getPendingContactIdP5lcXoU = this._state.getValue().m10485getPendingContactIdP5lcXoU();
        if (strM10485getPendingContactIdP5lcXoU == null) {
            TimeSourceKt.wipe(passphrase);
            return;
        }
        String incoming = this._state.getValue().getIncoming();
        if (incoming == null) {
            TimeSourceKt.wipe(passphrase);
        } else {
            m10491unlockThenexK5HwQ(strM10485getPendingContactIdP5lcXoU, passphrase, new C05061(incoming, null));
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$unlockAndDecrypt$1", f = "CompanionViewModel.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$unlockAndDecrypt$1, reason: invalid class name and case insensitive filesystem */
    static final class C05061 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ String $envelope;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05061(String str, Continuation<? super C05061> continuation) {
            super(1, continuation);
            this.$envelope = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return CompanionViewModel.this.new C05061(this.$envelope, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((C05061) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (CompanionViewModel.this.decryptInternal(this.$envelope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$requestBiometricUnlock$1", f = "CompanionViewModel.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$requestBiometricUnlock$1, reason: invalid class name and case insensitive filesystem */
    static final class C05031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05031(Continuation<? super C05031> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CompanionViewModel.this.new C05031(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objCurrentBiometricContactKey;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objCurrentBiometricContactKey = CompanionViewModel.this.currentBiometricContactKey(this);
                if (objCurrentBiometricContactKey == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objCurrentBiometricContactKey = obj;
            }
            String str = (String) objCurrentBiometricContactKey;
            if (str == null) {
                return Unit.INSTANCE;
            }
            CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, new BiometricPromptRequest.Unlock(str), 122879, null));
            return Unit.INSTANCE;
        }
    }

    public final void requestBiometricUnlock() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05031(null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onBiometricUnlockResult(byte[] keyBytes) {
        MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
        mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, null, 131071, null));
        String str = null;
        Object[] objArr = 0;
        if (keyBytes == null) {
            MutableStateFlow<CompanionUiState> mutableStateFlow2 = this._state;
            mutableStateFlow2.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow2.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_biometric_failed, str, 2, objArr == true ? 1 : 0), false, false, false, null, 253951, null));
            return;
        }
        String strM10485getPendingContactIdP5lcXoU = this._state.getValue().m10485getPendingContactIdP5lcXoU();
        if (strM10485getPendingContactIdP5lcXoU == null) {
            strM10485getPendingContactIdP5lcXoU = this._state.getValue().m10486getSelectedContactIdP5lcXoU();
        }
        if (strM10485getPendingContactIdP5lcXoU != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05011(strM10485getPendingContactIdP5lcXoU, keyBytes, null), 3, null);
        } else {
            ArraysKt.fill$default(keyBytes, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$onBiometricUnlockResult$1", f = "CompanionViewModel.kt", i = {0, 1, 1, 2, 3}, l = {InputDeviceCompat.SOURCE_KEYBOARD, 266, 272, 281}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "next", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$1", "L$0", "L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$onBiometricUnlockResult$1, reason: invalid class name and case insensitive filesystem */
    static final class C05011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $contactId;
        final /* synthetic */ byte[] $keyBytes;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05011(String str, byte[] bArr, Continuation<? super C05011> continuation) {
            super(2, continuation);
            this.$contactId = str;
            this.$keyBytes = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05011 c05011 = CompanionViewModel.this.new C05011(this.$contactId, this.$keyBytes, continuation);
            c05011.L$0 = obj;
            return c05011;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00de, code lost:
        
            if (r1 == r2) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0152, code lost:
        
            if (r1 == r2) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0227, code lost:
        
            if (r3.invoke(r32) == r2) goto L48;
         */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object objM10691unlockContactWithKeyexK5HwQ;
            BiometricKeyStore biometricKeyStore;
            Object objM10489currentBiometricContactKeyovFUVq0;
            BiometricKeyStore biometricKeyStore2;
            Object objM10489currentBiometricContactKeyovFUVq02;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 2;
            String str = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, true, false, null, 221183, null));
                this.L$0 = coroutineScope;
                this.label = 1;
                objM10691unlockContactWithKeyexK5HwQ = CompanionViewModel.this.messageCipher.m10691unlockContactWithKeyexK5HwQ(this.$contactId, this.$keyBytes, this);
                if (objM10691unlockContactWithKeyexK5HwQ != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                if (i == 3) {
                    BiometricKeyStore biometricKeyStore3 = (BiometricKeyStore) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    biometricKeyStore2 = biometricKeyStore3;
                    objM10489currentBiometricContactKeyovFUVq02 = obj;
                    String str2 = (String) objM10489currentBiometricContactKeyovFUVq02;
                    biometricKeyStore2.clear(str2 != null ? str2 : "");
                    CompanionViewModel.this.afterUnlock = null;
                    CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_biometric_stale, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), false, false, false, null, 155647, null));
                    return Unit.INSTANCE;
                }
                if (i != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                BiometricKeyStore biometricKeyStore4 = (BiometricKeyStore) this.L$1;
                ResultKt.throwOnFailure(obj);
                biometricKeyStore = biometricKeyStore4;
                objM10489currentBiometricContactKeyovFUVq0 = obj;
                String str3 = (String) objM10489currentBiometricContactKeyovFUVq0;
                biometricKeyStore.clear(str3 != null ? str3 : "");
                CompanionViewModel.this.afterUnlock = null;
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_biometric_failed, str, i2, objArr3 == true ? 1 : 0), false, false, false, null, 221183, null));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            objM10691unlockContactWithKeyexK5HwQ = obj;
            UnlockOutcome unlockOutcome = (UnlockOutcome) objM10691unlockContactWithKeyexK5HwQ;
            if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.Unlocked.INSTANCE) || Intrinsics.areEqual(unlockOutcome, UnlockOutcome.AlreadyUnlocked.INSTANCE)) {
                CompanionViewModel.this.lastUnlockedContactId = this.$contactId;
                MutableStateFlow mutableStateFlow = CompanionViewModel.this._state;
                CompanionUiState companionUiState = (CompanionUiState) CompanionViewModel.this._state.getValue();
                Set<ContactId> unlocked = ((CompanionUiState) CompanionViewModel.this._state.getValue()).getUnlocked();
                String str4 = this.$contactId;
                mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(companionUiState, null, null, null, null, false, null, SetsKt.plus(unlocked, str4 != null ? ContactId.m10637boximpl(str4) : null), null, null, null, null, null, false, null, false, false, false, null, 261823, null));
                Function1 function1 = CompanionViewModel.this.afterUnlock;
                CompanionViewModel.this.afterUnlock = null;
                if (function1 == null) {
                    CompanionViewModel companionViewModel = CompanionViewModel.this;
                    companionViewModel._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) companionViewModel._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, null, 229375, null));
                } else {
                    this.L$0 = coroutineScope;
                    this.L$1 = SpillingKt.nullOutSpilledVariable(function1);
                    this.label = 2;
                }
                return Unit.INSTANCE;
            }
            if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.NoSession.INSTANCE)) {
                biometricKeyStore2 = CompanionViewModel.this.biometrics;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = biometricKeyStore2;
                this.label = 3;
                objM10489currentBiometricContactKeyovFUVq02 = CompanionViewModel.this.m10489currentBiometricContactKeyovFUVq0(this.$contactId, this);
            } else {
                biometricKeyStore = CompanionViewModel.this.biometrics;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = biometricKeyStore;
                this.label = 4;
                objM10489currentBiometricContactKeyovFUVq0 = CompanionViewModel.this.m10489currentBiometricContactKeyovFUVq0(this.$contactId, this);
            }
            return coroutine_suspended;
        }
    }

    public final Object consumeRememberKey(Continuation<? super byte[]> continuation) {
        String str;
        if ((this._state.getValue().getBiometricPrompt() instanceof BiometricPromptRequest.Remember) && (str = this.lastUnlockedContactId) != null) {
            return this.messageCipher.m10687exportSessionKeyovFUVq0(str, continuation);
        }
        return null;
    }

    public final void onRememberDone(boolean success) {
        MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
        mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, this._state.getValue().getBiometricOffered() || success, null, 65535, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void copyResult() {
        String resultText = this._state.getValue().getResultText();
        if (resultText == null) {
            return;
        }
        if (this.clipboard.setText(resultText)) {
            MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
            mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, true, null, false, false, false, null, 258047, null));
        } else {
            MutableStateFlow<CompanionUiState> mutableStateFlow2 = this._state;
            mutableStateFlow2.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow2.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_clipboard_access_failed, null, 2, 0 == true ? 1 : 0), false, false, false, null, 253951, null));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void beginFromClipboard(CompanionMode mode) {
        ProcessTextContract.SelectedText envelope;
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (this.begun.compareAndSet(false, true)) {
            ClipboardRead clipboardRead = this.clipboard.read();
            int i = 2;
            String str = null;
            if (clipboardRead instanceof ClipboardRead.Text) {
                if (mode == CompanionMode.ENCRYPT) {
                    envelope = ProcessTextContract.INSTANCE.parsePlaintext(((ClipboardRead.Text) clipboardRead).getText());
                } else {
                    envelope = ProcessTextContract.INSTANCE.parseEnvelope(((ClipboardRead.Text) clipboardRead).getText());
                }
                if (envelope instanceof ProcessTextContract.SelectedText.Valid) {
                    if (mode == CompanionMode.ENCRYPT) {
                        startEncryptFlow(((ProcessTextContract.SelectedText.Valid) envelope).getText(), TextSource.CLIPBOARD, true);
                        return;
                    } else {
                        startDecryptFlow(((ProcessTextContract.SelectedText.Valid) envelope).getText(), TextSource.CLIPBOARD, true);
                        return;
                    }
                }
                if (Intrinsics.areEqual(envelope, ProcessTextContract.SelectedText.Empty.INSTANCE)) {
                    clipboardNotice$default(this, new UiNotice(R.string.notice_clipboard_blank, str, i, null == true ? 1 : 0), false, 2, null);
                    return;
                } else {
                    if (!(envelope instanceof ProcessTextContract.SelectedText.Oversized)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    clipboardNotice(new UiNotice(R.string.notice_oversized, String.valueOf(((ProcessTextContract.SelectedText.Oversized) envelope).getLimitBytes() / 1024)), false);
                    return;
                }
            }
            if (Intrinsics.areEqual(clipboardRead, ClipboardRead.Empty.INSTANCE)) {
                clipboardNotice$default(this, new UiNotice(R.string.notice_clipboard_empty, null == true ? 1 : 0, i, null == true ? 1 : 0), false, 2, null);
                return;
            }
            if (clipboardRead instanceof ClipboardRead.NonText) {
                int i2 = R.string.notice_clipboard_nontext;
                String mimeType = ((ClipboardRead.NonText) clipboardRead).getMimeType();
                if (mimeType == null) {
                    mimeType = "?";
                }
                clipboardNotice$default(this, new UiNotice(i2, mimeType), false, 2, null);
                return;
            }
            if (!(clipboardRead instanceof ClipboardRead.Blocked)) {
                throw new NoWhenBranchMatchedException();
            }
            int i3 = R.string.notice_clipboard_blocked;
            String reason = ((ClipboardRead.Blocked) clipboardRead).getReason();
            if (reason == null) {
                reason = "";
            }
            clipboardNotice$default(this, new UiNotice(i3, reason), false, 2, null);
        }
    }

    public final void retryClipboard() {
        CompanionUiState value = this._state.getValue();
        if (value.getPhase() == CompanionUiState.Phase.Notice && value.getRetryable()) {
            CompanionMode mode = value.getMode();
            this.begun.setValue(false);
            this._state.setValue(new CompanionUiState(mode, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, false, null, 262142, null));
            beginFromClipboard(mode);
        }
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$startEncryptFlow$1", f = "CompanionViewModel.kt", i = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2}, l = {387, 389, 392}, m = "invokeSuspend", n = {"contacts", "$this$filter$iv", "$this$filterTo$iv$iv", "destination$iv$iv", "element$iv$iv", "it", "$i$f$filter", "$i$f$filterTo", "$i$a$-filter-CompanionViewModel$startEncryptFlow$1$unlocked$2", "contacts", "unlocked"}, s = {"L$0", "L$1", "L$3", "L$4", "L$6", "L$7", "I$0", "I$1", "I$2", "L$0", "L$1"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$startEncryptFlow$1, reason: invalid class name and case insensitive filesystem */
    static final class C05051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextSource $source;
        final /* synthetic */ String $text;
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05051(TextSource textSource, String str, boolean z, Continuation<? super C05051> continuation) {
            super(2, continuation);
            this.$source = textSource;
            this.$text = str;
            this.$readOnly = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CompanionViewModel.this.new C05051(this.$source, this.$text, this.$readOnly, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0077, code lost:
        
            if (r2 == r1) goto L33;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00c8  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x010c  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0112  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0156  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x016d  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0174  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0184  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0187  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0191  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x019c  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0168 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0102 -> B:27:0x0104). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object objFirst;
            List list;
            Collection arrayList;
            Iterator it;
            CompanionViewModel companionViewModel;
            int i;
            Iterable iterable;
            Iterable iterable2;
            int i2;
            Object objFirst2;
            Set set;
            List list2;
            Iterator it2;
            Object next;
            CompanionUiState.Phase phase;
            UiNotice uiNotice;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            int i4 = 2;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objFirst = FlowKt.first(CompanionViewModel.this.contactsRepository.observeContacts(), this);
            } else {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        Set set2 = (Set) this.L$1;
                        list = (List) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objFirst2 = obj;
                        set = set2;
                        list2 = list;
                        String str = (String) objFirst2;
                        it2 = list2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it2.next();
                            if (Intrinsics.areEqual(((Contact) next).m10665getIdYaFaGk(), str)) {
                                break;
                            }
                        }
                        Contact contact = (Contact) next;
                        String strM10665getIdYaFaGk = contact == null ? contact.m10665getIdYaFaGk() : null;
                        MutableStateFlow mutableStateFlow = CompanionViewModel.this._state;
                        CompanionMode companionMode = CompanionMode.ENCRYPT;
                        if (!list2.isEmpty()) {
                            phase = CompanionUiState.Phase.Notice;
                        } else {
                            phase = CompanionUiState.Phase.Prepare;
                        }
                        CompanionUiState.Phase phase2 = phase;
                        if (list2.isEmpty()) {
                            uiNotice = null;
                        } else {
                            uiNotice = new UiNotice(R.string.notice_no_contacts, null, i4, 0 == true ? 1 : 0);
                        }
                        mutableStateFlow.setValue(new CompanionUiState(companionMode, this.$source, phase2, this.$text, this.$readOnly, list2, set, strM10665getIdYaFaGk, null, null, null, null, false, uiNotice, false, false, false, null, 253696, null));
                        CompanionViewModel.this.refreshBiometricOffer();
                        return Unit.INSTANCE;
                    }
                    int i5 = this.I$1;
                    int i6 = this.I$0;
                    Object obj2 = this.L$6;
                    it = (Iterator) this.L$5;
                    arrayList = (Collection) this.L$4;
                    iterable = (Iterable) this.L$3;
                    companionViewModel = (CompanionViewModel) this.L$2;
                    iterable2 = (Iterable) this.L$1;
                    List list3 = (List) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Object objM10688isUnlockedovFUVq0 = obj;
                    int i7 = i6;
                    i = i5;
                    list = list3;
                    if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                        arrayList.add(obj2);
                    }
                    i2 = i7;
                    if (it.hasNext()) {
                        Object next2 = it.next();
                        String strM10643unboximpl = ((ContactId) next2).m10643unboximpl();
                        MessageCipher messageCipher = companionViewModel.messageCipher;
                        this.L$0 = list;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(iterable2);
                        this.L$2 = companionViewModel;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(iterable);
                        this.L$4 = arrayList;
                        this.L$5 = it;
                        this.L$6 = next2;
                        this.L$7 = SpillingKt.nullOutSpilledVariable(strM10643unboximpl);
                        this.I$0 = i2;
                        this.I$1 = i;
                        this.I$2 = 0;
                        this.label = 2;
                        objM10688isUnlockedovFUVq0 = messageCipher.m10688isUnlockedovFUVq0(strM10643unboximpl, this);
                        if (objM10688isUnlockedovFUVq0 != coroutine_suspended) {
                            i7 = i2;
                            obj2 = next2;
                            if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                            }
                            i2 = i7;
                            if (it.hasNext()) {
                                Set set3 = CollectionsKt.toSet((List) arrayList);
                                this.L$0 = list;
                                this.L$1 = set3;
                                this.L$2 = null;
                                this.L$3 = null;
                                this.L$4 = null;
                                this.L$5 = null;
                                this.L$6 = null;
                                this.L$7 = null;
                                this.label = 3;
                                objFirst2 = FlowKt.first(CompanionViewModel.this.settings.getLastContactId(), this);
                                if (objFirst2 != coroutine_suspended) {
                                    set = set3;
                                    list2 = list;
                                    String str2 = (String) objFirst2;
                                    it2 = list2.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                        }
                                    }
                                    Contact contact2 = (Contact) next;
                                    if (contact2 == null) {
                                    }
                                    MutableStateFlow mutableStateFlow2 = CompanionViewModel.this._state;
                                    CompanionMode companionMode2 = CompanionMode.ENCRYPT;
                                    if (!list2.isEmpty()) {
                                    }
                                    CompanionUiState.Phase phase22 = phase;
                                    if (list2.isEmpty()) {
                                    }
                                    mutableStateFlow2.setValue(new CompanionUiState(companionMode2, this.$source, phase22, this.$text, this.$readOnly, list2, set, strM10665getIdYaFaGk, null, null, null, null, false, uiNotice, false, false, false, null, 253696, null));
                                    CompanionViewModel.this.refreshBiometricOffer();
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                    return coroutine_suspended;
                }
                ResultKt.throwOnFailure(obj);
                objFirst = obj;
            }
            list = (List) objFirst;
            List list4 = list;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            Iterator it3 = list4.iterator();
            while (it3.hasNext()) {
                arrayList2.add(ContactId.m10637boximpl(((Contact) it3.next()).m10665getIdYaFaGk()));
            }
            ArrayList arrayList3 = arrayList2;
            CompanionViewModel companionViewModel2 = CompanionViewModel.this;
            arrayList = new ArrayList();
            it = arrayList3.iterator();
            companionViewModel = companionViewModel2;
            i = 0;
            iterable = arrayList3;
            iterable2 = iterable;
            i2 = 0;
            if (it.hasNext()) {
            }
            return coroutine_suspended;
        }
    }

    private final void startEncryptFlow(String text, TextSource source, boolean readOnly) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05051(source, text, readOnly, null), 3, null);
    }

    private final void startDecryptFlow(String text, TextSource source, boolean readOnly) {
        this._state.setValue(new CompanionUiState(CompanionMode.DECRYPT, source, CompanionUiState.Phase.Prepare, text, readOnly, null, null, null, null, null, null, null, false, null, false, true, false, null, 229344, null));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05041(text, null), 3, null);
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$startDecryptFlow$1", f = "CompanionViewModel.kt", i = {}, l = {425}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$startDecryptFlow$1, reason: invalid class name and case insensitive filesystem */
    static final class C05041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $text;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05041(String str, Continuation<? super C05041> continuation) {
            super(2, continuation);
            this.$text = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CompanionViewModel.this.new C05041(this.$text, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (CompanionViewModel.this.decryptInternal(this.$text, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ void clipboardNotice$default(CompanionViewModel companionViewModel, UiNotice uiNotice, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        companionViewModel.clipboardNotice(uiNotice, z);
    }

    private final void clipboardNotice(UiNotice notice, boolean retryable) {
        this._state.setValue(new CompanionUiState(this._state.getValue().getMode(), TextSource.CLIPBOARD, CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, notice, retryable, false, false, null, 237560, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object decryptInternal(String str, Continuation<? super Unit> continuation) {
        C04991 c04991;
        String str2 = str;
        if (continuation instanceof C04991) {
            c04991 = (C04991) continuation;
            if ((c04991.label & Integer.MIN_VALUE) != 0) {
                c04991.label -= Integer.MIN_VALUE;
            } else {
                c04991 = new C04991(continuation);
            }
        }
        Object objDecryptEnvelope = c04991.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c04991.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objDecryptEnvelope);
            MutableStateFlow<CompanionUiState> mutableStateFlow = this._state;
            mutableStateFlow.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, true, false, null, 221183, null));
            MessageCipher messageCipher = this.messageCipher;
            c04991.L$0 = str2;
            c04991.label = 1;
            objDecryptEnvelope = messageCipher.decryptEnvelope(str2, c04991);
            if (objDecryptEnvelope == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) c04991.L$0;
            ResultKt.throwOnFailure(objDecryptEnvelope);
        }
        DecryptOutcome decryptOutcome = (DecryptOutcome) objDecryptEnvelope;
        if (decryptOutcome instanceof DecryptOutcome.Decrypted) {
            MutableStateFlow<CompanionUiState> mutableStateFlow2 = this._state;
            DecryptOutcome.Decrypted decrypted = (DecryptOutcome.Decrypted) decryptOutcome;
            mutableStateFlow2.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow2.getValue(), null, null, CompanionUiState.Phase.Result, null, false, null, null, null, null, null, decrypted.getPlaintext(), decrypted.getContactName(), false, null, false, false, false, null, 222203, null));
        } else {
            String str3 = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            Object[] objArr4 = 0;
            Object[] objArr5 = 0;
            Object[] objArr6 = 0;
            Object[] objArr7 = 0;
            if (decryptOutcome instanceof DecryptOutcome.NeedsUnlock) {
                this.afterUnlock = new AnonymousClass2(str2, null);
                MutableStateFlow<CompanionUiState> mutableStateFlow3 = this._state;
                DecryptOutcome.NeedsUnlock needsUnlock = (DecryptOutcome.NeedsUnlock) decryptOutcome;
                mutableStateFlow3.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow3.getValue(), null, null, CompanionUiState.Phase.NeedsUnlock, null, false, null, null, null, needsUnlock.m10683getContactIdYaFaGk(), needsUnlock.getContactName(), null, null, false, null, false, false, false, null, 228603, null));
                refreshBiometricOffer();
            } else {
                int i2 = 2;
                if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.NotAnEnvelope.INSTANCE)) {
                    MutableStateFlow<CompanionUiState> mutableStateFlow4 = this._state;
                    mutableStateFlow4.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow4.getValue(), null, null, CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_not_envelope, str3, i2, objArr7 == true ? 1 : 0), false, false, false, null, 221179, null));
                } else if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.UnknownKey.INSTANCE)) {
                    MutableStateFlow<CompanionUiState> mutableStateFlow5 = this._state;
                    mutableStateFlow5.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow5.getValue(), null, null, CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_unknown_key, objArr6 == true ? 1 : 0, i2, objArr5 == true ? 1 : 0), false, false, false, null, 221179, null));
                } else if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.StaleKeyEra.INSTANCE)) {
                    MutableStateFlow<CompanionUiState> mutableStateFlow6 = this._state;
                    mutableStateFlow6.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow6.getValue(), null, null, CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_stale_era, objArr4 == true ? 1 : 0, i2, objArr3 == true ? 1 : 0), false, false, false, null, 221179, null));
                } else {
                    if (!(decryptOutcome instanceof DecryptOutcome.Failed)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    MutableStateFlow<CompanionUiState> mutableStateFlow7 = this._state;
                    mutableStateFlow7.setValue(CompanionUiState.m10481copygvgolLs$default(mutableStateFlow7.getValue(), null, null, CompanionUiState.Phase.Notice, null, false, null, null, null, null, null, null, null, false, new UiNotice(R.string.notice_decrypt_failed, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), false, false, false, null, 221179, null));
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$decryptInternal$2", f = "CompanionViewModel.kt", i = {}, l = {450}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$decryptInternal$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ String $envelope;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$envelope = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return CompanionViewModel.this.new AnonymousClass2(this.$envelope, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (CompanionViewModel.this.decryptInternal(this.$envelope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* renamed from: unlockThen-exK5HwQ, reason: not valid java name */
    private final void m10491unlockThenexK5HwQ(String contactId, char[] passphrase, Function1<? super Continuation<? super Unit>, ? extends Object> andThen) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CompanionViewModel$unlockThen$1(this, contactId, passphrase, andThen, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: offerRemember-M53eizw, reason: not valid java name */
    public final void m10490offerRememberM53eizw(String contactId) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new CompanionViewModel$offerRemember$1(this, contactId, null), 3, null);
    }

    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.companion.CompanionViewModel$refreshBiometricOffer$1", f = "CompanionViewModel.kt", i = {0, 0}, l = {542}, m = "invokeSuspend", n = {"s", "target"}, s = {"L$0", "L$1"}, v = 1)
    /* renamed from: org.cipherkey.app.companion.CompanionViewModel$refreshBiometricOffer$1, reason: invalid class name and case insensitive filesystem */
    static final class C05021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        C05021(Continuation<? super C05021> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CompanionViewModel.this.new C05021(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM10489currentBiometricContactKeyovFUVq0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CompanionUiState companionUiState = (CompanionUiState) CompanionViewModel.this._state.getValue();
                String strM10485getPendingContactIdP5lcXoU = companionUiState.m10485getPendingContactIdP5lcXoU();
                if (strM10485getPendingContactIdP5lcXoU == null && (strM10485getPendingContactIdP5lcXoU = companionUiState.m10486getSelectedContactIdP5lcXoU()) == null) {
                    return Unit.INSTANCE;
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(companionUiState);
                this.L$1 = SpillingKt.nullOutSpilledVariable(strM10485getPendingContactIdP5lcXoU);
                this.label = 1;
                objM10489currentBiometricContactKeyovFUVq0 = CompanionViewModel.this.m10489currentBiometricContactKeyovFUVq0(strM10485getPendingContactIdP5lcXoU, this);
                if (objM10489currentBiometricContactKeyovFUVq0 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM10489currentBiometricContactKeyovFUVq0 = obj;
            }
            String str = (String) objM10489currentBiometricContactKeyovFUVq0;
            boolean z = false;
            if (str != null) {
                CompanionViewModel companionViewModel = CompanionViewModel.this;
                z = companionViewModel.biometrics.isDeviceCapable() && companionViewModel.biometrics.hasWrapped(str);
            }
            if (((CompanionUiState) CompanionViewModel.this._state.getValue()).getBiometricOffered() != z) {
                CompanionViewModel.this._state.setValue(CompanionUiState.m10481copygvgolLs$default((CompanionUiState) CompanionViewModel.this._state.getValue(), null, null, null, null, false, null, null, null, null, null, null, null, false, null, false, false, z, null, 196607, null));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshBiometricOffer() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C05021(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: currentBiometricContactKey-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10489currentBiometricContactKeyovFUVq0(String str, Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objM10685currentEraIdwb5GA7Y;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MessageCipher messageCipher = this.messageCipher;
            anonymousClass1.L$0 = str;
            anonymousClass1.label = 1;
            objM10685currentEraIdwb5GA7Y = messageCipher.m10685currentEraIdwb5GA7Y(str, anonymousClass1);
            if (objM10685currentEraIdwb5GA7Y == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            SessionId sessionId = (SessionId) obj;
            objM10685currentEraIdwb5GA7Y = sessionId != null ? sessionId.m10650unboximpl() : null;
        }
        String str2 = (String) objM10685currentEraIdwb5GA7Y;
        if (str2 == null) {
            return null;
        }
        return str + ":" + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object currentBiometricContactKey(Continuation<? super String> continuation) {
        String strM10485getPendingContactIdP5lcXoU = this._state.getValue().m10485getPendingContactIdP5lcXoU();
        if (strM10485getPendingContactIdP5lcXoU == null && (strM10485getPendingContactIdP5lcXoU = this._state.getValue().m10486getSelectedContactIdP5lcXoU()) == null) {
            return null;
        }
        return m10489currentBiometricContactKeyovFUVq0(strM10485getPendingContactIdP5lcXoU, continuation);
    }
}
