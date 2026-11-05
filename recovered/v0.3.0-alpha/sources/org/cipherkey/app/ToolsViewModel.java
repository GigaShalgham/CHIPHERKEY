package org.cipherkey.app;

import androidx.compose.runtime.ComposerKt;
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
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.app.companion.CompanionMode;
import org.cipherkey.app.companion.ProcessTextContract;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.sessions.TimeSourceKt;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.DecryptOutcome;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.data.repository.UnlockOutcome;
import org.cipherkey.security.clipboard.ClipboardAccess;
import org.cipherkey.security.clipboard.ClipboardRead;

/* compiled from: ToolsViewModel.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001c¢\u0006\u0004\b!\u0010\"J\u0006\u0010#\u001a\u00020\u001fJ\u000e\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001aJ\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001aJ\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\u001fJ\u0010\u0010+\u001a\u00020\u001f2\b\u0010,\u001a\u0004\u0018\u00010-J\u0010\u0010.\u001a\u0004\u0018\u00010-H\u0086@¢\u0006\u0002\u0010/J\u000e\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u000202J\u001c\u00103\u001a\u00020\u001f2\u0014\u00104\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001f05J\u0006\u00106\u001a\u00020\u001fJ\u0006\u00107\u001a\u00020\u001fJ\u001f\u00108\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001c2\u0006\u00109\u001a\u00020\u001aH\u0002¢\u0006\u0004\b:\u0010;J\u0010\u0010<\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020\u001aH\u0002J\u0017\u0010>\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001cH\u0002¢\u0006\u0004\b?\u0010\"J\u0017\u0010@\u001a\u00020\u001f2\u0006\u0010A\u001a\u00020\u001cH\u0002¢\u0006\u0004\bB\u0010\"J\b\u0010C\u001a\u00020\u001fH\u0002J\u001a\u0010D\u001a\u0004\u0018\u00010\u001a2\u0006\u0010A\u001a\u00020\u001cH\u0082@¢\u0006\u0004\bE\u0010FR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001d¨\u0006G"}, d2 = {"Lorg/cipherkey/app/ToolsViewModel;", "Landroidx/lifecycle/ViewModel;", "messageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "contactsRepository", "Lorg/cipherkey/data/repository/ContactRepository;", "settings", "Lorg/cipherkey/data/preferences/SettingsRepository;", "clipboard", "Lorg/cipherkey/security/clipboard/ClipboardAccess;", "biometricStore", "Lorg/cipherkey/app/security/BiometricKeyStore;", "<init>", "(Lorg/cipherkey/data/repository/MessageCipher;Lorg/cipherkey/data/repository/ContactRepository;Lorg/cipherkey/data/preferences/SettingsRepository;Lorg/cipherkey/security/clipboard/ClipboardAccess;Lorg/cipherkey/app/security/BiometricKeyStore;)V", "getBiometricStore", "()Lorg/cipherkey/app/security/BiometricKeyStore;", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lorg/cipherkey/app/ToolsUiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "pendingMode", "Lorg/cipherkey/app/companion/CompanionMode;", "pendingText", "", "lastUnlockedContactId", "Lorg/cipherkey/core/common/ContactId;", "Ljava/lang/String;", "selectContact", "", "id", "selectContact-M53eizw", "(Ljava/lang/String;)V", "dismissNotice", "encrypt", TextBundle.TEXT_ENTRY, "decrypt", "unlockAndContinue", "passphrase", "", "requestBiometricUnlock", "onBiometricUnlockResult", "keyBytes", "", "consumeRememberKey", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRememberDone", "success", "", "pasteIntoInput", "onPasted", "Lkotlin/Function1;", "copyResult", "clearResult", "encryptInternal", "draft", "encryptInternal-ovFUVq0", "(Ljava/lang/String;Ljava/lang/String;)V", "decryptInternal", "envelope", "continuePending", "continuePending-M53eizw", "offerRemember", "contactId", "offerRemember-M53eizw", "refreshBiometricOffer", "biometricContactKey", "biometricContactKey-ovFUVq0", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ToolsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<ToolsUiState> _state;
    private final BiometricKeyStore biometricStore;
    private final ClipboardAccess clipboard;
    private final ContactRepository contactsRepository;
    private String lastUnlockedContactId;
    private final MessageCipher messageCipher;
    private CompanionMode pendingMode;
    private String pendingText;
    private final SettingsRepository settings;
    private final StateFlow<ToolsUiState> state;

    /* compiled from: ToolsViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CompanionMode.values().length];
            try {
                iArr[CompanionMode.ENCRYPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CompanionMode.DECRYPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public ToolsViewModel(MessageCipher messageCipher, ContactRepository contactsRepository, SettingsRepository settings, ClipboardAccess clipboard, BiometricKeyStore biometricStore) {
        Intrinsics.checkNotNullParameter(messageCipher, "messageCipher");
        Intrinsics.checkNotNullParameter(contactsRepository, "contactsRepository");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(clipboard, "clipboard");
        Intrinsics.checkNotNullParameter(biometricStore, "biometricStore");
        this.messageCipher = messageCipher;
        this.contactsRepository = contactsRepository;
        this.settings = settings;
        this.clipboard = clipboard;
        this.biometricStore = biometricStore;
        MutableStateFlow<ToolsUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new ToolsUiState(null, null, null, null, null, false, false, false, null, false, null, null, false, null, 16383, null));
        this._state = MutableStateFlow;
        this.state = FlowKt.asStateFlow(MutableStateFlow);
        this.pendingText = "";
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final BiometricKeyStore getBiometricStore() {
        return this.biometricStore;
    }

    public final StateFlow<ToolsUiState> getState() {
        return this.state;
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$1", f = "ToolsViewModel.kt", i = {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2}, l = {82, 84, 86}, m = "invokeSuspend", n = {"contacts", "$this$filter$iv", "$this$filterTo$iv$iv", "destination$iv$iv", "element$iv$iv", "it", "$i$f$filter", "$i$f$filterTo", "$i$a$-filter-ToolsViewModel$1$unlocked$2", "contacts", "unlocked"}, s = {"L$0", "L$1", "L$3", "L$4", "L$6", "L$7", "I$0", "I$1", "I$2", "L$0", "L$1"}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
        
            if (r2 == r1) goto L33;
         */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0166  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x017d  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0183  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0178 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0101 -> B:27:0x0103). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object objFirst;
            List list;
            Collection arrayList;
            Iterator it;
            ToolsViewModel toolsViewModel;
            int i;
            Iterable iterable;
            Iterable iterable2;
            int i2;
            String str;
            Object objFirst2;
            Set set;
            Iterator it2;
            Object next;
            Contact contact;
            String str2;
            String strM10665getIdYaFaGk;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objFirst = FlowKt.first(ToolsViewModel.this.contactsRepository.observeContacts(), this);
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
                        str = null;
                        List list2 = list;
                        String str3 = (String) objFirst2;
                        MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
                        ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
                        it2 = list2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                next = str;
                                break;
                            }
                            next = it2.next();
                            if (Intrinsics.areEqual(((Contact) next).m10665getIdYaFaGk(), str3)) {
                                break;
                            }
                        }
                        contact = (Contact) next;
                        if (contact == null) {
                            strM10665getIdYaFaGk = contact.m10665getIdYaFaGk();
                        } else {
                            Contact contact2 = (Contact) CollectionsKt.firstOrNull(list2);
                            if (contact2 == null) {
                                str2 = str;
                                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : list2, (16376 & 2) != 0 ? toolsUiState.unlocked : set, (16376 & 4) != 0 ? toolsUiState.selectedContactId : str2, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : false, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
                                return Unit.INSTANCE;
                            }
                            strM10665getIdYaFaGk = contact2.m10665getIdYaFaGk();
                        }
                        str2 = strM10665getIdYaFaGk;
                        mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : list2, (16376 & 2) != 0 ? toolsUiState.unlocked : set, (16376 & 4) != 0 ? toolsUiState.selectedContactId : str2, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : false, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
                        return Unit.INSTANCE;
                    }
                    int i4 = this.I$1;
                    int i5 = this.I$0;
                    Object obj2 = this.L$6;
                    it = (Iterator) this.L$5;
                    arrayList = (Collection) this.L$4;
                    iterable = (Iterable) this.L$3;
                    toolsViewModel = (ToolsViewModel) this.L$2;
                    iterable2 = (Iterable) this.L$1;
                    List list3 = (List) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Object objM10688isUnlockedovFUVq0 = obj;
                    int i6 = i5;
                    i = i4;
                    list = list3;
                    if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                        arrayList.add(obj2);
                    }
                    i2 = i6;
                    if (it.hasNext()) {
                        Object next2 = it.next();
                        String strM10643unboximpl = ((ContactId) next2).m10643unboximpl();
                        MessageCipher messageCipher = toolsViewModel.messageCipher;
                        this.L$0 = list;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(iterable2);
                        this.L$2 = toolsViewModel;
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
                            i6 = i2;
                            obj2 = next2;
                            if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                            }
                            i2 = i6;
                            if (it.hasNext()) {
                                Set set3 = CollectionsKt.toSet((List) arrayList);
                                this.L$0 = list;
                                this.L$1 = set3;
                                str = null;
                                this.L$2 = null;
                                this.L$3 = null;
                                this.L$4 = null;
                                this.L$5 = null;
                                this.L$6 = null;
                                this.L$7 = null;
                                this.label = 3;
                                objFirst2 = FlowKt.first(ToolsViewModel.this.settings.getLastContactId(), this);
                                if (objFirst2 != coroutine_suspended) {
                                    set = set3;
                                    List list22 = list;
                                    String str32 = (String) objFirst2;
                                    MutableStateFlow mutableStateFlow2 = ToolsViewModel.this._state;
                                    ToolsUiState toolsUiState2 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                                    it2 = list22.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                        }
                                    }
                                    contact = (Contact) next;
                                    if (contact == null) {
                                    }
                                    str2 = strM10665getIdYaFaGk;
                                    mutableStateFlow2.setValue(toolsUiState2.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState2.contacts : list22, (16376 & 2) != 0 ? toolsUiState2.unlocked : set, (16376 & 4) != 0 ? toolsUiState2.selectedContactId : str2, (16376 & 8) != 0 ? toolsUiState2.resultText : null, (16376 & 16) != 0 ? toolsUiState2.resultContactName : null, (16376 & 32) != 0 ? toolsUiState2.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState2.copied : false, (16376 & 128) != 0 ? toolsUiState2.busy : false, (16376 & 256) != 0 ? toolsUiState2.notice : null, (16376 & 512) != 0 ? toolsUiState2.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState2.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState2.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState2.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState2.biometricPrompt : null));
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
            ToolsViewModel toolsViewModel2 = ToolsViewModel.this;
            arrayList = new ArrayList();
            it = arrayList3.iterator();
            toolsViewModel = toolsViewModel2;
            i = 0;
            iterable = arrayList3;
            iterable2 = iterable;
            i2 = 0;
            if (it.hasNext()) {
            }
            return coroutine_suspended;
        }
    }

    /* renamed from: selectContact-M53eizw, reason: not valid java name */
    public final void m10472selectContactM53eizw(String id) {
        Intrinsics.checkNotNullParameter(id, "$v$c$org-cipherkey-core-common-ContactId$-id$0");
        MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
        ToolsUiState value = mutableStateFlow.getValue();
        mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : id, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
        refreshBiometricOffer();
    }

    public final void dismissNotice() {
        MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
        ToolsUiState value = mutableStateFlow.getValue();
        mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void encrypt(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ProcessTextContract.SelectedText plaintext = ProcessTextContract.INSTANCE.parsePlaintext(text);
        int i = 2;
        String str = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (plaintext instanceof ProcessTextContract.SelectedText.Empty) {
            MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
            ToolsUiState value = mutableStateFlow.getValue();
            mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : new UiNotice(R.string.notice_empty_input, str, i, objArr3 == true ? 1 : 0), (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
            return;
        }
        if (plaintext instanceof ProcessTextContract.SelectedText.Oversized) {
            MutableStateFlow<ToolsUiState> mutableStateFlow2 = this._state;
            ToolsUiState value2 = mutableStateFlow2.getValue();
            mutableStateFlow2.setValue(value2.m10462copyeam0GUE((16376 & 1) != 0 ? value2.contacts : null, (16376 & 2) != 0 ? value2.unlocked : null, (16376 & 4) != 0 ? value2.selectedContactId : null, (16376 & 8) != 0 ? value2.resultText : null, (16376 & 16) != 0 ? value2.resultContactName : null, (16376 & 32) != 0 ? value2.resultIsEnvelope : false, (16376 & 64) != 0 ? value2.copied : false, (16376 & 128) != 0 ? value2.busy : false, (16376 & 256) != 0 ? value2.notice : new UiNotice(R.string.notice_input_too_long, String.valueOf(((ProcessTextContract.SelectedText.Oversized) plaintext).getLimitBytes() / 1024)), (16376 & 512) != 0 ? value2.unlockNeeded : false, (16376 & 1024) != 0 ? value2.pendingContactId : null, (16376 & 2048) != 0 ? value2.unlockContactName : null, (16376 & 4096) != 0 ? value2.biometricOffered : false, (16376 & 8192) != 0 ? value2.biometricPrompt : null));
        } else {
            if (!(plaintext instanceof ProcessTextContract.SelectedText.Valid)) {
                throw new NoWhenBranchMatchedException();
            }
            String strM10464getSelectedContactIdP5lcXoU = this._state.getValue().m10464getSelectedContactIdP5lcXoU();
            if (strM10464getSelectedContactIdP5lcXoU == null) {
                MutableStateFlow<ToolsUiState> mutableStateFlow3 = this._state;
                ToolsUiState value3 = mutableStateFlow3.getValue();
                mutableStateFlow3.setValue(value3.m10462copyeam0GUE((16376 & 1) != 0 ? value3.contacts : null, (16376 & 2) != 0 ? value3.unlocked : null, (16376 & 4) != 0 ? value3.selectedContactId : null, (16376 & 8) != 0 ? value3.resultText : null, (16376 & 16) != 0 ? value3.resultContactName : null, (16376 & 32) != 0 ? value3.resultIsEnvelope : false, (16376 & 64) != 0 ? value3.copied : false, (16376 & 128) != 0 ? value3.busy : false, (16376 & 256) != 0 ? value3.notice : new UiNotice(R.string.notice_no_contact_selected, objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0), (16376 & 512) != 0 ? value3.unlockNeeded : false, (16376 & 1024) != 0 ? value3.pendingContactId : null, (16376 & 2048) != 0 ? value3.unlockContactName : null, (16376 & 4096) != 0 ? value3.biometricOffered : false, (16376 & 8192) != 0 ? value3.biometricPrompt : null));
            } else {
                this.pendingMode = CompanionMode.ENCRYPT;
                ProcessTextContract.SelectedText.Valid valid = (ProcessTextContract.SelectedText.Valid) plaintext;
                this.pendingText = valid.getText();
                m10470encryptInternalovFUVq0(strM10464getSelectedContactIdP5lcXoU, valid.getText());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void decrypt(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ProcessTextContract.SelectedText envelope = ProcessTextContract.INSTANCE.parseEnvelope(text);
        if (envelope instanceof ProcessTextContract.SelectedText.Empty) {
            MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
            ToolsUiState value = mutableStateFlow.getValue();
            mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : new UiNotice(R.string.notice_empty_input, null, 2, 0 == true ? 1 : 0), (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
            return;
        }
        if (envelope instanceof ProcessTextContract.SelectedText.Oversized) {
            MutableStateFlow<ToolsUiState> mutableStateFlow2 = this._state;
            ToolsUiState value2 = mutableStateFlow2.getValue();
            mutableStateFlow2.setValue(value2.m10462copyeam0GUE((16376 & 1) != 0 ? value2.contacts : null, (16376 & 2) != 0 ? value2.unlocked : null, (16376 & 4) != 0 ? value2.selectedContactId : null, (16376 & 8) != 0 ? value2.resultText : null, (16376 & 16) != 0 ? value2.resultContactName : null, (16376 & 32) != 0 ? value2.resultIsEnvelope : false, (16376 & 64) != 0 ? value2.copied : false, (16376 & 128) != 0 ? value2.busy : false, (16376 & 256) != 0 ? value2.notice : new UiNotice(R.string.notice_input_too_long, String.valueOf(((ProcessTextContract.SelectedText.Oversized) envelope).getLimitBytes() / 1024)), (16376 & 512) != 0 ? value2.unlockNeeded : false, (16376 & 1024) != 0 ? value2.pendingContactId : null, (16376 & 2048) != 0 ? value2.unlockContactName : null, (16376 & 4096) != 0 ? value2.biometricOffered : false, (16376 & 8192) != 0 ? value2.biometricPrompt : null));
        } else {
            if (!(envelope instanceof ProcessTextContract.SelectedText.Valid)) {
                throw new NoWhenBranchMatchedException();
            }
            this.pendingMode = CompanionMode.DECRYPT;
            ProcessTextContract.SelectedText.Valid valid = (ProcessTextContract.SelectedText.Valid) envelope;
            this.pendingText = valid.getText();
            decryptInternal(valid.getText());
        }
    }

    public final void unlockAndContinue(char[] passphrase) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        String strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10463getPendingContactIdP5lcXoU();
        if (strM10463getPendingContactIdP5lcXoU != null || (strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10464getSelectedContactIdP5lcXoU()) != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04971(strM10463getPendingContactIdP5lcXoU, passphrase, null), 3, null);
        } else {
            TimeSourceKt.wipe(passphrase);
        }
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$unlockAndContinue$1", f = "ToolsViewModel.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$unlockAndContinue$1, reason: invalid class name and case insensitive filesystem */
    static final class C04971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $id;
        final /* synthetic */ char[] $passphrase;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04971(String str, char[] cArr, Continuation<? super C04971> continuation) {
            super(2, continuation);
            this.$id = str;
            this.$passphrase = cArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04971(this.$id, this.$passphrase, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04971) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM10690unlockContactexK5HwQ;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
                ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : true, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
                this.label = 1;
                objM10690unlockContactexK5HwQ = ToolsViewModel.this.messageCipher.m10690unlockContactexK5HwQ(this.$id, this.$passphrase, this);
                if (objM10690unlockContactexK5HwQ == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM10690unlockContactexK5HwQ = obj;
            }
            UnlockOutcome unlockOutcome = (UnlockOutcome) objM10690unlockContactexK5HwQ;
            TimeSourceKt.wipe(this.$passphrase);
            if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.Unlocked.INSTANCE) || Intrinsics.areEqual(unlockOutcome, UnlockOutcome.AlreadyUnlocked.INSTANCE)) {
                ToolsViewModel.this.lastUnlockedContactId = this.$id;
                MutableStateFlow mutableStateFlow2 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState2 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow2.setValue(toolsUiState2.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState2.contacts : null, (16376 & 2) != 0 ? toolsUiState2.unlocked : SetsKt.plus(((ToolsUiState) ToolsViewModel.this._state.getValue()).getUnlocked(), ContactId.m10637boximpl(this.$id)), (16376 & 4) != 0 ? toolsUiState2.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState2.resultText : null, (16376 & 16) != 0 ? toolsUiState2.resultContactName : null, (16376 & 32) != 0 ? toolsUiState2.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState2.copied : false, (16376 & 128) != 0 ? toolsUiState2.busy : false, (16376 & 256) != 0 ? toolsUiState2.notice : null, (16376 & 512) != 0 ? toolsUiState2.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState2.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState2.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState2.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState2.biometricPrompt : null));
                ToolsViewModel.this.m10469continuePendingM53eizw(this.$id);
                ToolsViewModel.this.m10471offerRememberM53eizw(this.$id);
            } else {
                int i2 = 2;
                String str = null;
                Object[] objArr = 0;
                Object[] objArr2 = 0;
                Object[] objArr3 = 0;
                if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.WrongPassphrase.INSTANCE)) {
                    MutableStateFlow mutableStateFlow3 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState3 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow3.setValue(toolsUiState3.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState3.contacts : null, (16376 & 2) != 0 ? toolsUiState3.unlocked : null, (16376 & 4) != 0 ? toolsUiState3.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState3.resultText : null, (16376 & 16) != 0 ? toolsUiState3.resultContactName : null, (16376 & 32) != 0 ? toolsUiState3.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState3.copied : false, (16376 & 128) != 0 ? toolsUiState3.busy : false, (16376 & 256) != 0 ? toolsUiState3.notice : new UiNotice(R.string.notice_wrong_passphrase, str, i2, objArr3 == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState3.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState3.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState3.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState3.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState3.biometricPrompt : null));
                } else if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.NoSession.INSTANCE)) {
                    MutableStateFlow mutableStateFlow4 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState4 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow4.setValue(toolsUiState4.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState4.contacts : null, (16376 & 2) != 0 ? toolsUiState4.unlocked : null, (16376 & 4) != 0 ? toolsUiState4.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState4.resultText : null, (16376 & 16) != 0 ? toolsUiState4.resultContactName : null, (16376 & 32) != 0 ? toolsUiState4.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState4.copied : false, (16376 & 128) != 0 ? toolsUiState4.busy : false, (16376 & 256) != 0 ? toolsUiState4.notice : new UiNotice(R.string.notice_no_era, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState4.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState4.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState4.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState4.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState4.biometricPrompt : null));
                } else if (unlockOutcome instanceof UnlockOutcome.Rejected) {
                    MutableStateFlow mutableStateFlow5 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState5 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow5.setValue(toolsUiState5.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState5.contacts : null, (16376 & 2) != 0 ? toolsUiState5.unlocked : null, (16376 & 4) != 0 ? toolsUiState5.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState5.resultText : null, (16376 & 16) != 0 ? toolsUiState5.resultContactName : null, (16376 & 32) != 0 ? toolsUiState5.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState5.copied : false, (16376 & 128) != 0 ? toolsUiState5.busy : false, (16376 & 256) != 0 ? toolsUiState5.notice : new UiNotice(R.string.notice_passphrase_rejected, ((UnlockOutcome.Rejected) unlockOutcome).getReason()), (16376 & 512) != 0 ? toolsUiState5.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState5.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState5.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState5.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState5.biometricPrompt : null));
                } else if (unlockOutcome instanceof UnlockOutcome.Failed) {
                    MutableStateFlow mutableStateFlow6 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState6 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow6.setValue(toolsUiState6.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState6.contacts : null, (16376 & 2) != 0 ? toolsUiState6.unlocked : null, (16376 & 4) != 0 ? toolsUiState6.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState6.resultText : null, (16376 & 16) != 0 ? toolsUiState6.resultContactName : null, (16376 & 32) != 0 ? toolsUiState6.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState6.copied : false, (16376 & 128) != 0 ? toolsUiState6.busy : false, (16376 & 256) != 0 ? toolsUiState6.notice : new UiNotice(R.string.notice_unlock_failed, ((UnlockOutcome.Failed) unlockOutcome).getReason()), (16376 & 512) != 0 ? toolsUiState6.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState6.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState6.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState6.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState6.biometricPrompt : null));
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$requestBiometricUnlock$1", f = "ToolsViewModel.kt", i = {}, l = {ComposerKt.providerKey}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$requestBiometricUnlock$1, reason: invalid class name and case insensitive filesystem */
    static final class C04961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $contactId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04961(String str, Continuation<? super C04961> continuation) {
            super(2, continuation);
            this.$contactId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04961(this.$contactId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM10468biometricContactKeyovFUVq0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objM10468biometricContactKeyovFUVq0 = ToolsViewModel.this.m10468biometricContactKeyovFUVq0(this.$contactId, this);
                if (objM10468biometricContactKeyovFUVq0 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM10468biometricContactKeyovFUVq0 = obj;
            }
            String str = (String) objM10468biometricContactKeyovFUVq0;
            if (str == null) {
                return Unit.INSTANCE;
            }
            MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
            ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
            mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : false, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : new BiometricPromptRequest.Unlock(str)));
            return Unit.INSTANCE;
        }
    }

    public final void requestBiometricUnlock() {
        String strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10463getPendingContactIdP5lcXoU();
        if (strM10463getPendingContactIdP5lcXoU == null && (strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10464getSelectedContactIdP5lcXoU()) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04961(strM10463getPendingContactIdP5lcXoU, null), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onBiometricUnlockResult(byte[] keyBytes) {
        MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
        ToolsUiState value = mutableStateFlow.getValue();
        mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
        String str = null;
        Object[] objArr = 0;
        if (keyBytes == null) {
            MutableStateFlow<ToolsUiState> mutableStateFlow2 = this._state;
            ToolsUiState value2 = mutableStateFlow2.getValue();
            mutableStateFlow2.setValue(value2.m10462copyeam0GUE((16376 & 1) != 0 ? value2.contacts : null, (16376 & 2) != 0 ? value2.unlocked : null, (16376 & 4) != 0 ? value2.selectedContactId : null, (16376 & 8) != 0 ? value2.resultText : null, (16376 & 16) != 0 ? value2.resultContactName : null, (16376 & 32) != 0 ? value2.resultIsEnvelope : false, (16376 & 64) != 0 ? value2.copied : false, (16376 & 128) != 0 ? value2.busy : false, (16376 & 256) != 0 ? value2.notice : new UiNotice(R.string.notice_biometric_failed, str, 2, objArr == true ? 1 : 0), (16376 & 512) != 0 ? value2.unlockNeeded : false, (16376 & 1024) != 0 ? value2.pendingContactId : null, (16376 & 2048) != 0 ? value2.unlockContactName : null, (16376 & 4096) != 0 ? value2.biometricOffered : false, (16376 & 8192) != 0 ? value2.biometricPrompt : null));
            return;
        }
        String strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10463getPendingContactIdP5lcXoU();
        if (strM10463getPendingContactIdP5lcXoU == null) {
            strM10463getPendingContactIdP5lcXoU = this._state.getValue().m10464getSelectedContactIdP5lcXoU();
        }
        if (strM10463getPendingContactIdP5lcXoU != null) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04931(strM10463getPendingContactIdP5lcXoU, keyBytes, null), 3, null);
        } else {
            ArraysKt.fill$default(keyBytes, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$onBiometricUnlockResult$1", f = "ToolsViewModel.kt", i = {}, l = {224, 236}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$onBiometricUnlockResult$1, reason: invalid class name and case insensitive filesystem */
    static final class C04931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $contactId;
        final /* synthetic */ byte[] $keyBytes;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04931(String str, byte[] bArr, Continuation<? super C04931> continuation) {
            super(2, continuation);
            this.$contactId = str;
            this.$keyBytes = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04931(this.$contactId, this.$keyBytes, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00a5  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object objM10691unlockContactWithKeyexK5HwQ;
            Object objM10468biometricContactKeyovFUVq0;
            BiometricKeyStore biometricKeyStore;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            int i2 = 2;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
                ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : true, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
                this.label = 1;
                objM10691unlockContactWithKeyexK5HwQ = ToolsViewModel.this.messageCipher.m10691unlockContactWithKeyexK5HwQ(this.$contactId, this.$keyBytes, this);
                if (objM10691unlockContactWithKeyexK5HwQ != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                biometricKeyStore = (BiometricKeyStore) this.L$0;
                ResultKt.throwOnFailure(obj);
                objM10468biometricContactKeyovFUVq0 = obj;
                str = (String) objM10468biometricContactKeyovFUVq0;
                if (str == null) {
                    str = "";
                }
                biometricKeyStore.clear(str);
                MutableStateFlow mutableStateFlow2 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState2 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow2.setValue(toolsUiState2.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState2.contacts : null, (16376 & 2) != 0 ? toolsUiState2.unlocked : null, (16376 & 4) != 0 ? toolsUiState2.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState2.resultText : null, (16376 & 16) != 0 ? toolsUiState2.resultContactName : null, (16376 & 32) != 0 ? toolsUiState2.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState2.copied : false, (16376 & 128) != 0 ? toolsUiState2.busy : false, (16376 & 256) != 0 ? toolsUiState2.notice : new UiNotice(R.string.notice_biometric_failed, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState2.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState2.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState2.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState2.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState2.biometricPrompt : null));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            objM10691unlockContactWithKeyexK5HwQ = obj;
            UnlockOutcome unlockOutcome = (UnlockOutcome) objM10691unlockContactWithKeyexK5HwQ;
            if (Intrinsics.areEqual(unlockOutcome, UnlockOutcome.Unlocked.INSTANCE) || Intrinsics.areEqual(unlockOutcome, UnlockOutcome.AlreadyUnlocked.INSTANCE)) {
                ToolsViewModel.this.lastUnlockedContactId = this.$contactId;
                MutableStateFlow mutableStateFlow3 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState3 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                Set<ContactId> unlocked = ((ToolsUiState) ToolsViewModel.this._state.getValue()).getUnlocked();
                String str2 = this.$contactId;
                mutableStateFlow3.setValue(toolsUiState3.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState3.contacts : null, (16376 & 2) != 0 ? toolsUiState3.unlocked : SetsKt.plus(unlocked, str2 != null ? ContactId.m10637boximpl(str2) : null), (16376 & 4) != 0 ? toolsUiState3.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState3.resultText : null, (16376 & 16) != 0 ? toolsUiState3.resultContactName : null, (16376 & 32) != 0 ? toolsUiState3.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState3.copied : false, (16376 & 128) != 0 ? toolsUiState3.busy : false, (16376 & 256) != 0 ? toolsUiState3.notice : null, (16376 & 512) != 0 ? toolsUiState3.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState3.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState3.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState3.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState3.biometricPrompt : null));
                ToolsViewModel.this.m10469continuePendingM53eizw(this.$contactId);
                return Unit.INSTANCE;
            }
            BiometricKeyStore biometricStore = ToolsViewModel.this.getBiometricStore();
            this.L$0 = biometricStore;
            this.label = 2;
            objM10468biometricContactKeyovFUVq0 = ToolsViewModel.this.m10468biometricContactKeyovFUVq0(this.$contactId, this);
            if (objM10468biometricContactKeyovFUVq0 != coroutine_suspended) {
                biometricKeyStore = biometricStore;
                str = (String) objM10468biometricContactKeyovFUVq0;
                if (str == null) {
                }
                biometricKeyStore.clear(str);
                MutableStateFlow mutableStateFlow22 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState22 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow22.setValue(toolsUiState22.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState22.contacts : null, (16376 & 2) != 0 ? toolsUiState22.unlocked : null, (16376 & 4) != 0 ? toolsUiState22.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState22.resultText : null, (16376 & 16) != 0 ? toolsUiState22.resultContactName : null, (16376 & 32) != 0 ? toolsUiState22.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState22.copied : false, (16376 & 128) != 0 ? toolsUiState22.busy : false, (16376 & 256) != 0 ? toolsUiState22.notice : new UiNotice(R.string.notice_biometric_failed, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState22.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState22.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState22.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState22.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState22.biometricPrompt : null));
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public final Object consumeRememberKey(Continuation<? super byte[]> continuation) {
        String str;
        BiometricPromptRequest biometricPrompt = this._state.getValue().getBiometricPrompt();
        if ((biometricPrompt instanceof BiometricPromptRequest.Remember ? (BiometricPromptRequest.Remember) biometricPrompt : null) == null || (str = this.lastUnlockedContactId) == null) {
            return null;
        }
        return this.messageCipher.m10687exportSessionKeyovFUVq0(str, continuation);
    }

    public final void onRememberDone(boolean success) {
        MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
        ToolsUiState value = mutableStateFlow.getValue();
        mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : this._state.getValue().getBiometricOffered() || success, (16376 & 8192) != 0 ? value.biometricPrompt : null));
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$pasteIntoInput$1", f = "ToolsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$pasteIntoInput$1, reason: invalid class name and case insensitive filesystem */
    static final class C04941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<String, Unit> $onPasted;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C04941(Function1<? super String, Unit> function1, Continuation<? super C04941> continuation) {
            super(2, continuation);
            this.$onPasted = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04941(this.$onPasted, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ClipboardRead clipboardRead = ToolsViewModel.this.clipboard.read();
                if (clipboardRead instanceof ClipboardRead.Text) {
                    this.$onPasted.invoke(((ClipboardRead.Text) clipboardRead).getText());
                } else {
                    this.$onPasted.invoke(null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void pasteIntoInput(Function1<? super String, Unit> onPasted) {
        Intrinsics.checkNotNullParameter(onPasted, "onPasted");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getMain(), null, new C04941(onPasted, null), 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void copyResult() {
        String resultText = this._state.getValue().getResultText();
        if (resultText == null) {
            return;
        }
        if (this.clipboard.setText(resultText)) {
            MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
            ToolsUiState value = mutableStateFlow.getValue();
            mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : true, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
        } else {
            MutableStateFlow<ToolsUiState> mutableStateFlow2 = this._state;
            ToolsUiState value2 = mutableStateFlow2.getValue();
            mutableStateFlow2.setValue(value2.m10462copyeam0GUE((16376 & 1) != 0 ? value2.contacts : null, (16376 & 2) != 0 ? value2.unlocked : null, (16376 & 4) != 0 ? value2.selectedContactId : null, (16376 & 8) != 0 ? value2.resultText : null, (16376 & 16) != 0 ? value2.resultContactName : null, (16376 & 32) != 0 ? value2.resultIsEnvelope : false, (16376 & 64) != 0 ? value2.copied : false, (16376 & 128) != 0 ? value2.busy : false, (16376 & 256) != 0 ? value2.notice : new UiNotice(R.string.notice_clipboard_access_failed, null, 2, 0 == true ? 1 : 0), (16376 & 512) != 0 ? value2.unlockNeeded : false, (16376 & 1024) != 0 ? value2.pendingContactId : null, (16376 & 2048) != 0 ? value2.unlockContactName : null, (16376 & 4096) != 0 ? value2.biometricOffered : false, (16376 & 8192) != 0 ? value2.biometricPrompt : null));
        }
    }

    public final void clearResult() {
        MutableStateFlow<ToolsUiState> mutableStateFlow = this._state;
        ToolsUiState value = mutableStateFlow.getValue();
        mutableStateFlow.setValue(value.m10462copyeam0GUE((16376 & 1) != 0 ? value.contacts : null, (16376 & 2) != 0 ? value.unlocked : null, (16376 & 4) != 0 ? value.selectedContactId : null, (16376 & 8) != 0 ? value.resultText : null, (16376 & 16) != 0 ? value.resultContactName : null, (16376 & 32) != 0 ? value.resultIsEnvelope : false, (16376 & 64) != 0 ? value.copied : false, (16376 & 128) != 0 ? value.busy : false, (16376 & 256) != 0 ? value.notice : null, (16376 & 512) != 0 ? value.unlockNeeded : false, (16376 & 1024) != 0 ? value.pendingContactId : null, (16376 & 2048) != 0 ? value.unlockContactName : null, (16376 & 4096) != 0 ? value.biometricOffered : false, (16376 & 8192) != 0 ? value.biometricPrompt : null));
    }

    /* renamed from: encryptInternal-ovFUVq0, reason: not valid java name */
    private final void m10470encryptInternalovFUVq0(String id, String draft) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ToolsViewModel$encryptInternal$1(this, id, draft, null), 3, null);
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$decryptInternal$1", f = "ToolsViewModel.kt", i = {}, l = {337}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$decryptInternal$1, reason: invalid class name and case insensitive filesystem */
    static final class C04921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $envelope;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04921(String str, Continuation<? super C04921> continuation) {
            super(2, continuation);
            this.$envelope = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04921(this.$envelope, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objDecryptEnvelope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
                ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : true, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
                this.label = 1;
                objDecryptEnvelope = ToolsViewModel.this.messageCipher.decryptEnvelope(this.$envelope, this);
                if (objDecryptEnvelope == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objDecryptEnvelope = obj;
            }
            DecryptOutcome decryptOutcome = (DecryptOutcome) objDecryptEnvelope;
            if (decryptOutcome instanceof DecryptOutcome.Decrypted) {
                MutableStateFlow mutableStateFlow2 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState2 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                DecryptOutcome.Decrypted decrypted = (DecryptOutcome.Decrypted) decryptOutcome;
                mutableStateFlow2.setValue(toolsUiState2.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState2.contacts : null, (16376 & 2) != 0 ? toolsUiState2.unlocked : null, (16376 & 4) != 0 ? toolsUiState2.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState2.resultText : decrypted.getPlaintext(), (16376 & 16) != 0 ? toolsUiState2.resultContactName : decrypted.getContactName(), (16376 & 32) != 0 ? toolsUiState2.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState2.copied : false, (16376 & 128) != 0 ? toolsUiState2.busy : false, (16376 & 256) != 0 ? toolsUiState2.notice : null, (16376 & 512) != 0 ? toolsUiState2.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState2.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState2.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState2.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState2.biometricPrompt : null));
            } else if (decryptOutcome instanceof DecryptOutcome.NeedsUnlock) {
                ToolsViewModel.this.pendingMode = CompanionMode.DECRYPT;
                ToolsViewModel.this.pendingText = this.$envelope;
                MutableStateFlow mutableStateFlow3 = ToolsViewModel.this._state;
                ToolsUiState toolsUiState3 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                DecryptOutcome.NeedsUnlock needsUnlock = (DecryptOutcome.NeedsUnlock) decryptOutcome;
                mutableStateFlow3.setValue(toolsUiState3.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState3.contacts : null, (16376 & 2) != 0 ? toolsUiState3.unlocked : null, (16376 & 4) != 0 ? toolsUiState3.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState3.resultText : null, (16376 & 16) != 0 ? toolsUiState3.resultContactName : null, (16376 & 32) != 0 ? toolsUiState3.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState3.copied : false, (16376 & 128) != 0 ? toolsUiState3.busy : false, (16376 & 256) != 0 ? toolsUiState3.notice : null, (16376 & 512) != 0 ? toolsUiState3.unlockNeeded : true, (16376 & 1024) != 0 ? toolsUiState3.pendingContactId : needsUnlock.m10683getContactIdYaFaGk(), (16376 & 2048) != 0 ? toolsUiState3.unlockContactName : needsUnlock.getContactName(), (16376 & 4096) != 0 ? toolsUiState3.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState3.biometricPrompt : null));
                ToolsViewModel.this.refreshBiometricOffer();
            } else {
                int i2 = 2;
                String str = null;
                Object[] objArr = 0;
                Object[] objArr2 = 0;
                Object[] objArr3 = 0;
                Object[] objArr4 = 0;
                Object[] objArr5 = 0;
                Object[] objArr6 = 0;
                Object[] objArr7 = 0;
                if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.NotAnEnvelope.INSTANCE)) {
                    MutableStateFlow mutableStateFlow4 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState4 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow4.setValue(toolsUiState4.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState4.contacts : null, (16376 & 2) != 0 ? toolsUiState4.unlocked : null, (16376 & 4) != 0 ? toolsUiState4.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState4.resultText : null, (16376 & 16) != 0 ? toolsUiState4.resultContactName : null, (16376 & 32) != 0 ? toolsUiState4.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState4.copied : false, (16376 & 128) != 0 ? toolsUiState4.busy : false, (16376 & 256) != 0 ? toolsUiState4.notice : new UiNotice(R.string.notice_not_envelope, str, i2, objArr7 == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState4.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState4.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState4.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState4.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState4.biometricPrompt : null));
                } else if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.UnknownKey.INSTANCE)) {
                    MutableStateFlow mutableStateFlow5 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState5 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow5.setValue(toolsUiState5.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState5.contacts : null, (16376 & 2) != 0 ? toolsUiState5.unlocked : null, (16376 & 4) != 0 ? toolsUiState5.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState5.resultText : null, (16376 & 16) != 0 ? toolsUiState5.resultContactName : null, (16376 & 32) != 0 ? toolsUiState5.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState5.copied : false, (16376 & 128) != 0 ? toolsUiState5.busy : false, (16376 & 256) != 0 ? toolsUiState5.notice : new UiNotice(R.string.notice_unknown_key, objArr6 == true ? 1 : 0, i2, objArr5 == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState5.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState5.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState5.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState5.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState5.biometricPrompt : null));
                } else if (Intrinsics.areEqual(decryptOutcome, DecryptOutcome.StaleKeyEra.INSTANCE)) {
                    MutableStateFlow mutableStateFlow6 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState6 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow6.setValue(toolsUiState6.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState6.contacts : null, (16376 & 2) != 0 ? toolsUiState6.unlocked : null, (16376 & 4) != 0 ? toolsUiState6.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState6.resultText : null, (16376 & 16) != 0 ? toolsUiState6.resultContactName : null, (16376 & 32) != 0 ? toolsUiState6.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState6.copied : false, (16376 & 128) != 0 ? toolsUiState6.busy : false, (16376 & 256) != 0 ? toolsUiState6.notice : new UiNotice(R.string.notice_stale_era, objArr4 == true ? 1 : 0, i2, objArr3 == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState6.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState6.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState6.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState6.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState6.biometricPrompt : null));
                } else if (decryptOutcome instanceof DecryptOutcome.Failed) {
                    MutableStateFlow mutableStateFlow7 = ToolsViewModel.this._state;
                    ToolsUiState toolsUiState7 = (ToolsUiState) ToolsViewModel.this._state.getValue();
                    mutableStateFlow7.setValue(toolsUiState7.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState7.contacts : null, (16376 & 2) != 0 ? toolsUiState7.unlocked : null, (16376 & 4) != 0 ? toolsUiState7.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState7.resultText : null, (16376 & 16) != 0 ? toolsUiState7.resultContactName : null, (16376 & 32) != 0 ? toolsUiState7.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState7.copied : false, (16376 & 128) != 0 ? toolsUiState7.busy : false, (16376 & 256) != 0 ? toolsUiState7.notice : new UiNotice(R.string.notice_decrypt_failed, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0), (16376 & 512) != 0 ? toolsUiState7.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState7.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState7.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState7.biometricOffered : false, (16376 & 8192) != 0 ? toolsUiState7.biometricPrompt : null));
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final void decryptInternal(String envelope) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04921(envelope, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: continuePending-M53eizw, reason: not valid java name */
    public final void m10469continuePendingM53eizw(String id) {
        CompanionMode companionMode = this.pendingMode;
        int i = companionMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[companionMode.ordinal()];
        if (i != -1) {
            if (i == 1) {
                m10470encryptInternalovFUVq0(id, this.pendingText);
            } else {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                decryptInternal(this.pendingText);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: offerRemember-M53eizw, reason: not valid java name */
    public final void m10471offerRememberM53eizw(String contactId) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ToolsViewModel$offerRemember$1(this, contactId, null), 3, null);
    }

    /* compiled from: ToolsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ToolsViewModel$refreshBiometricOffer$1", f = "ToolsViewModel.kt", i = {0}, l = {399}, m = "invokeSuspend", n = {"target"}, s = {"L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.ToolsViewModel$refreshBiometricOffer$1, reason: invalid class name and case insensitive filesystem */
    static final class C04951 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C04951(Continuation<? super C04951> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ToolsViewModel.this.new C04951(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04951) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM10468biometricContactKeyovFUVq0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String strM10463getPendingContactIdP5lcXoU = ((ToolsUiState) ToolsViewModel.this._state.getValue()).m10463getPendingContactIdP5lcXoU();
                if (strM10463getPendingContactIdP5lcXoU == null && (strM10463getPendingContactIdP5lcXoU = ((ToolsUiState) ToolsViewModel.this._state.getValue()).m10464getSelectedContactIdP5lcXoU()) == null) {
                    return Unit.INSTANCE;
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(strM10463getPendingContactIdP5lcXoU);
                this.label = 1;
                objM10468biometricContactKeyovFUVq0 = ToolsViewModel.this.m10468biometricContactKeyovFUVq0(strM10463getPendingContactIdP5lcXoU, this);
                if (objM10468biometricContactKeyovFUVq0 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM10468biometricContactKeyovFUVq0 = obj;
            }
            String str = (String) objM10468biometricContactKeyovFUVq0;
            boolean z = false;
            if (str != null) {
                ToolsViewModel toolsViewModel = ToolsViewModel.this;
                z = toolsViewModel.getBiometricStore().isDeviceCapable() && toolsViewModel.getBiometricStore().hasWrapped(str);
            }
            if (((ToolsUiState) ToolsViewModel.this._state.getValue()).getBiometricOffered() != z) {
                MutableStateFlow mutableStateFlow = ToolsViewModel.this._state;
                ToolsUiState toolsUiState = (ToolsUiState) ToolsViewModel.this._state.getValue();
                mutableStateFlow.setValue(toolsUiState.m10462copyeam0GUE((16376 & 1) != 0 ? toolsUiState.contacts : null, (16376 & 2) != 0 ? toolsUiState.unlocked : null, (16376 & 4) != 0 ? toolsUiState.selectedContactId : null, (16376 & 8) != 0 ? toolsUiState.resultText : null, (16376 & 16) != 0 ? toolsUiState.resultContactName : null, (16376 & 32) != 0 ? toolsUiState.resultIsEnvelope : false, (16376 & 64) != 0 ? toolsUiState.copied : false, (16376 & 128) != 0 ? toolsUiState.busy : false, (16376 & 256) != 0 ? toolsUiState.notice : null, (16376 & 512) != 0 ? toolsUiState.unlockNeeded : false, (16376 & 1024) != 0 ? toolsUiState.pendingContactId : null, (16376 & 2048) != 0 ? toolsUiState.unlockContactName : null, (16376 & 4096) != 0 ? toolsUiState.biometricOffered : z, (16376 & 8192) != 0 ? toolsUiState.biometricPrompt : null));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshBiometricOffer() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04951(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: biometricContactKey-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10468biometricContactKeyovFUVq0(String str, Continuation<? super String> continuation) {
        ToolsViewModel$biometricContactKey$1 toolsViewModel$biometricContactKey$1;
        Object objM10685currentEraIdwb5GA7Y;
        if (continuation instanceof ToolsViewModel$biometricContactKey$1) {
            toolsViewModel$biometricContactKey$1 = (ToolsViewModel$biometricContactKey$1) continuation;
            if ((toolsViewModel$biometricContactKey$1.label & Integer.MIN_VALUE) != 0) {
                toolsViewModel$biometricContactKey$1.label -= Integer.MIN_VALUE;
            } else {
                toolsViewModel$biometricContactKey$1 = new ToolsViewModel$biometricContactKey$1(this, continuation);
            }
        }
        Object obj = toolsViewModel$biometricContactKey$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = toolsViewModel$biometricContactKey$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MessageCipher messageCipher = this.messageCipher;
            toolsViewModel$biometricContactKey$1.L$0 = str;
            toolsViewModel$biometricContactKey$1.label = 1;
            objM10685currentEraIdwb5GA7Y = messageCipher.m10685currentEraIdwb5GA7Y(str, toolsViewModel$biometricContactKey$1);
            if (objM10685currentEraIdwb5GA7Y == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = (String) toolsViewModel$biometricContactKey$1.L$0;
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
}
