package org.cipherkey.app;

import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.messages.Ck1FormatException;
import org.cipherkey.core.messages.InviteCodec;
import org.cipherkey.core.messages.SessionInvite;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.ContactSession;
import org.cipherkey.data.repository.EraImport;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.data.repository.SessionKeyRepository;
import org.cipherkey.data.repository.UnlockOutcome;
import org.cipherkey.security.policies.Argon2Profile;

/* compiled from: ContactsViewModel.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010#\u001a\u00020$J\u001f\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00132\b\b\u0002\u0010'\u001a\u00020(¢\u0006\u0004\b)\u0010*J4\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020 2\b\u0010-\u001a\u0004\u0018\u00010 2\u0006\u0010.\u001a\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020$01J1\u00102\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00132\u0006\u00103\u001a\u0002042\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020$01¢\u0006\u0004\b6\u00107J\u0015\u00108\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0013¢\u0006\u0004\b9\u0010:J\u0006\u0010;\u001a\u00020$J\u0015\u0010<\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0013¢\u0006\u0004\b=\u0010:J\u0006\u0010>\u001a\u00020$J\u000e\u0010?\u001a\u00020$H\u0082@¢\u0006\u0002\u0010@J\u0018\u0010A\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0013H\u0082@¢\u0006\u0004\bB\u0010CR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR&\u0010\u0016\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\f0\u00170\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0019\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\f0\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR \u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020 0\u00170\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020 0\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000f¨\u0006D"}, d2 = {"Lorg/cipherkey/app/ContactsViewModel;", "Landroidx/lifecycle/ViewModel;", "contactsRepository", "Lorg/cipherkey/data/repository/ContactRepository;", "sessionKeys", "Lorg/cipherkey/data/repository/SessionKeyRepository;", "messageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "<init>", "(Lorg/cipherkey/data/repository/ContactRepository;Lorg/cipherkey/data/repository/SessionKeyRepository;Lorg/cipherkey/data/repository/MessageCipher;)V", "contacts", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lorg/cipherkey/data/repository/Contact;", "getContacts", "()Lkotlinx/coroutines/flow/StateFlow;", "_unlocked", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lorg/cipherkey/core/common/ContactId;", "unlocked", "getUnlocked", "_eras", "", "Lorg/cipherkey/data/repository/ContactSession;", "eras", "getEras", "_feedback", "Lorg/cipherkey/app/companion/UiNotice;", "feedback", "getFeedback", "_fingerprints", "", "fingerprints", "getFingerprints", "refreshAll", "", "loadEras", "contactId", "notify", "", "loadEras-ovFUVq0", "(Ljava/lang/String;Z)V", "createContact", "displayName", "inviteText", "profile", "Lorg/cipherkey/security/policies/Argon2Profile;", "onResult", "Lkotlin/Function1;", "unlockContact", "passphrase", "", "Lorg/cipherkey/data/repository/UnlockOutcome;", "unlockContact-exK5HwQ", "(Ljava/lang/String;[CLkotlin/jvm/functions/Function1;)V", "lockContact", "lockContact-M53eizw", "(Ljava/lang/String;)V", "lockAll", "deleteContact", "deleteContact-M53eizw", "clearFeedback", "refreshUnlocked", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshFingerprint", "refreshFingerprint-ovFUVq0", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Map<ContactId, List<ContactSession>>> _eras;
    private final MutableStateFlow<UiNotice> _feedback;
    private final MutableStateFlow<Map<ContactId, String>> _fingerprints;
    private final MutableStateFlow<Set<ContactId>> _unlocked;
    private final StateFlow<List<Contact>> contacts;
    private final ContactRepository contactsRepository;
    private final StateFlow<Map<ContactId, List<ContactSession>>> eras;
    private final StateFlow<UiNotice> feedback;
    private final StateFlow<Map<ContactId, String>> fingerprints;
    private final MessageCipher messageCipher;
    private final SessionKeyRepository sessionKeys;
    private final StateFlow<Set<ContactId>> unlocked;

    /* compiled from: ContactsViewModel.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ContactsViewModel", f = "ContactsViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {156}, m = "refreshUnlocked", n = {"$this$filter$iv", "$this$filterTo$iv$iv", "destination$iv$iv", "element$iv$iv", "it", "$i$f$filter", "$i$f$filterTo", "$i$a$-filter-ContactsViewModel$refreshUnlocked$unlockedIds$2"}, s = {"L$0", "L$1", "L$2", "L$4", "L$5", "I$0", "I$1", "I$2"}, v = 1)
    /* renamed from: org.cipherkey.app.ContactsViewModel$refreshUnlocked$1, reason: invalid class name and case insensitive filesystem */
    static final class C04851 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C04851(Continuation<? super C04851> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ContactsViewModel.this.refreshUnlocked(this);
        }
    }

    @Inject
    public ContactsViewModel(ContactRepository contactsRepository, SessionKeyRepository sessionKeys, MessageCipher messageCipher) {
        Intrinsics.checkNotNullParameter(contactsRepository, "contactsRepository");
        Intrinsics.checkNotNullParameter(sessionKeys, "sessionKeys");
        Intrinsics.checkNotNullParameter(messageCipher, "messageCipher");
        this.contactsRepository = contactsRepository;
        this.sessionKeys = sessionKeys;
        this.messageCipher = messageCipher;
        this.contacts = FlowKt.stateIn(contactsRepository.observeContacts(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), CollectionsKt.emptyList());
        MutableStateFlow<Set<ContactId>> MutableStateFlow = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
        this._unlocked = MutableStateFlow;
        this.unlocked = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<Map<ContactId, List<ContactSession>>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this._eras = MutableStateFlow2;
        this.eras = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<UiNotice> MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._feedback = MutableStateFlow3;
        this.feedback = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow<Map<ContactId, String>> MutableStateFlow4 = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this._fingerprints = MutableStateFlow4;
        this.fingerprints = FlowKt.asStateFlow(MutableStateFlow4);
    }

    public final StateFlow<List<Contact>> getContacts() {
        return this.contacts;
    }

    public final StateFlow<Set<ContactId>> getUnlocked() {
        return this.unlocked;
    }

    public final StateFlow<Map<ContactId, List<ContactSession>>> getEras() {
        return this.eras;
    }

    public final StateFlow<UiNotice> getFeedback() {
        return this.feedback;
    }

    public final StateFlow<Map<ContactId, String>> getFingerprints() {
        return this.fingerprints;
    }

    /* compiled from: ContactsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ContactsViewModel$refreshAll$1", f = "ContactsViewModel.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ContactsViewModel$refreshAll$1, reason: invalid class name and case insensitive filesystem */
    static final class C04841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04841(Continuation<? super C04841> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactsViewModel.this.new C04841(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (ContactsViewModel.this.refreshUnlocked(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<Contact> value = ContactsViewModel.this.getContacts().getValue();
            ContactsViewModel contactsViewModel = ContactsViewModel.this;
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                contactsViewModel.m10455loadErasovFUVq0(((Contact) it.next()).m10665getIdYaFaGk(), false);
            }
            return Unit.INSTANCE;
        }
    }

    public final void refreshAll() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04841(null), 3, null);
    }

    /* renamed from: loadEras-ovFUVq0$default, reason: not valid java name */
    public static /* synthetic */ void m10452loadErasovFUVq0$default(ContactsViewModel contactsViewModel, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        contactsViewModel.m10455loadErasovFUVq0(str, z);
    }

    /* renamed from: loadEras-ovFUVq0, reason: not valid java name */
    public final void m10455loadErasovFUVq0(String contactId, boolean notify) {
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ContactsViewModel$loadEras$1(this, contactId, notify, null), 3, null);
    }

    /* compiled from: ContactsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ContactsViewModel$createContact$1", f = "ContactsViewModel.kt", i = {1, 1, 2, 2, 3}, l = {80, 83, 86, 93}, m = "invokeSuspend", n = {"contact", "invite", "contact", "invite", "contact"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.ContactsViewModel$createContact$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $displayName;
        final /* synthetic */ String $inviteText;
        final /* synthetic */ Function1<Boolean, Unit> $onResult;
        final /* synthetic */ Argon2Profile $profile;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(String str, String str2, Function1<? super Boolean, Unit> function1, Argon2Profile argon2Profile, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$displayName = str;
            this.$inviteText = str2;
            this.$onResult = function1;
            this.$profile = argon2Profile;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactsViewModel.this.new AnonymousClass1(this.$displayName, this.$inviteText, this.$onResult, this.$profile, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00bb A[Catch: IllegalArgumentException -> 0x0046, Ck1FormatException -> 0x0049, TryCatch #2 {IllegalArgumentException -> 0x0046, Ck1FormatException -> 0x0049, blocks: (B:9:0x001c, B:56:0x0122, B:14:0x0031, B:47:0x00dd, B:17:0x003e, B:37:0x00a8, B:39:0x00ae, B:42:0x00b3, B:44:0x00bb, B:49:0x00f9, B:50:0x00fe, B:51:0x00ff, B:18:0x0042, B:28:0x0064, B:30:0x006c, B:33:0x0074, B:52:0x0103, B:25:0x004f), top: B:70:0x000e }] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00f9 A[Catch: IllegalArgumentException -> 0x0046, Ck1FormatException -> 0x0049, TryCatch #2 {IllegalArgumentException -> 0x0046, Ck1FormatException -> 0x0049, blocks: (B:9:0x001c, B:56:0x0122, B:14:0x0031, B:47:0x00dd, B:17:0x003e, B:37:0x00a8, B:39:0x00ae, B:42:0x00b3, B:44:0x00bb, B:49:0x00f9, B:50:0x00fe, B:51:0x00ff, B:18:0x0042, B:28:0x0064, B:30:0x006c, B:33:0x0074, B:52:0x0103, B:25:0x004f), top: B:70:0x000e }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Contact contact;
            SessionInvite sessionInviteDecode;
            Contact contact2;
            EraImport eraImport;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            String str = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            int i2 = 2;
            try {
            } catch (IllegalArgumentException e) {
                MutableStateFlow mutableStateFlow = ContactsViewModel.this._feedback;
                int i3 = R.string.feedback_invalid_invite;
                String message = e.getMessage();
                mutableStateFlow.setValue(new UiNotice(i3, message != null ? message : ""));
                this.$onResult.invoke(Boxing.boxBoolean(false));
            } catch (Ck1FormatException e2) {
                MutableStateFlow mutableStateFlow2 = ContactsViewModel.this._feedback;
                int i4 = R.string.feedback_invalid_invite;
                String message2 = e2.getMessage();
                mutableStateFlow2.setValue(new UiNotice(i4, message2 != null ? message2 : ""));
                this.$onResult.invoke(Boxing.boxBoolean(false));
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = ContactsViewModel.this.contactsRepository.createContact(this.$displayName, this);
                if (obj == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_invite_other_contact, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0));
                        this.$onResult.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    }
                    if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    contact = (Contact) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    ContactsViewModel.this.m10455loadErasovFUVq0(contact.m10665getIdYaFaGk(), false);
                    ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_contact_created, str, i2, objArr3 == true ? 1 : 0));
                    this.$onResult.invoke(Boxing.boxBoolean(true));
                    return Unit.INSTANCE;
                }
                sessionInviteDecode = (SessionInvite) this.L$1;
                contact2 = (Contact) this.L$0;
                ResultKt.throwOnFailure(obj);
                eraImport = (EraImport) obj;
                if (!(eraImport instanceof EraImport.Imported) && !(eraImport instanceof EraImport.AlreadyPresent)) {
                    if (!Intrinsics.areEqual(eraImport, EraImport.KeyBelongsToOtherContact.INSTANCE)) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(contact2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(sessionInviteDecode);
                        this.label = 3;
                        if (ContactsViewModel.this.contactsRepository.m10668deleteContactovFUVq0(contact2.m10665getIdYaFaGk(), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_invite_other_contact, objArr2 == true ? 1 : 0, i2, objArr == true ? 1 : 0));
                        this.$onResult.invoke(Boxing.boxBoolean(false));
                        return Unit.INSTANCE;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                Unit unit = Unit.INSTANCE;
                contact = contact2;
                ContactsViewModel.this.m10455loadErasovFUVq0(contact.m10665getIdYaFaGk(), false);
                ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_contact_created, str, i2, objArr3 == true ? 1 : 0));
                this.$onResult.invoke(Boxing.boxBoolean(true));
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            Contact contact3 = (Contact) obj;
            String str2 = this.$inviteText;
            if (str2 == null || StringsKt.isBlank(str2)) {
                this.L$0 = contact3;
                this.label = 4;
                if (ContactsViewModel.this.sessionKeys.m10692createEraexK5HwQ(contact3.m10665getIdYaFaGk(), this.$profile.getParams(), this) != coroutine_suspended) {
                    contact = contact3;
                    ContactsViewModel.this.m10455loadErasovFUVq0(contact.m10665getIdYaFaGk(), false);
                    ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_contact_created, str, i2, objArr3 == true ? 1 : 0));
                    this.$onResult.invoke(Boxing.boxBoolean(true));
                    return Unit.INSTANCE;
                }
            } else {
                sessionInviteDecode = InviteCodec.INSTANCE.decode(StringsKt.trim((CharSequence) this.$inviteText).toString());
                this.L$0 = contact3;
                this.L$1 = SpillingKt.nullOutSpilledVariable(sessionInviteDecode);
                this.label = 2;
                Object objM10695importEraexK5HwQ = ContactsViewModel.this.sessionKeys.m10695importEraexK5HwQ(contact3.m10665getIdYaFaGk(), sessionInviteDecode, this);
                if (objM10695importEraexK5HwQ != coroutine_suspended) {
                    contact2 = contact3;
                    obj = objM10695importEraexK5HwQ;
                    eraImport = (EraImport) obj;
                    if (!(eraImport instanceof EraImport.Imported)) {
                        if (!Intrinsics.areEqual(eraImport, EraImport.KeyBelongsToOtherContact.INSTANCE)) {
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    contact = contact2;
                    ContactsViewModel.this.m10455loadErasovFUVq0(contact.m10665getIdYaFaGk(), false);
                    ContactsViewModel.this._feedback.setValue(new UiNotice(R.string.feedback_contact_created, str, i2, objArr3 == true ? 1 : 0));
                    this.$onResult.invoke(Boxing.boxBoolean(true));
                    return Unit.INSTANCE;
                }
            }
            return coroutine_suspended;
        }
    }

    public final void createContact(String displayName, String inviteText, Argon2Profile profile, Function1<? super Boolean, Unit> onResult) {
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(displayName, inviteText, onResult, profile, null), 3, null);
    }

    /* renamed from: unlockContact-exK5HwQ, reason: not valid java name */
    public final void m10457unlockContactexK5HwQ(String contactId, char[] passphrase, Function1<? super UnlockOutcome, Unit> onResult) {
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ContactsViewModel$unlockContact$1(this, contactId, passphrase, onResult, null), 3, null);
    }

    /* renamed from: lockContact-M53eizw, reason: not valid java name */
    public final void m10456lockContactM53eizw(String contactId) {
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ContactsViewModel$lockContact$1(this, contactId, null), 3, null);
    }

    /* compiled from: ContactsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.ContactsViewModel$lockAll$1", f = "ContactsViewModel.kt", i = {}, l = {134, 135}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.ContactsViewModel$lockAll$1, reason: invalid class name and case insensitive filesystem */
    static final class C04831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04831(Continuation<? super C04831> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactsViewModel.this.new C04831(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x003e, code lost:
        
            if (r4.this$0.refreshUnlocked(r4) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (ContactsViewModel.this.messageCipher.lockAll(this) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ContactsViewModel.this._fingerprints.setValue(MapsKt.emptyMap());
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 2;
        }
    }

    public final void lockAll() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04831(null), 3, null);
    }

    /* renamed from: deleteContact-M53eizw, reason: not valid java name */
    public final void m10454deleteContactM53eizw(String contactId) {
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new ContactsViewModel$deleteContact$1(this, contactId, null), 3, null);
    }

    public final void clearFeedback() {
        this._feedback.setValue(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00d0 -> B:24:0x00d3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object refreshUnlocked(Continuation<? super Unit> continuation) {
        C04851 c04851;
        Collection collection;
        Iterable iterable;
        Iterable iterable2;
        int i;
        Iterator it;
        int i2;
        if (continuation instanceof C04851) {
            c04851 = (C04851) continuation;
            if ((c04851.label & Integer.MIN_VALUE) != 0) {
                c04851.label -= Integer.MIN_VALUE;
            } else {
                c04851 = new C04851(continuation);
            }
        }
        Object objM10688isUnlockedovFUVq0 = c04851.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c04851.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objM10688isUnlockedovFUVq0);
            List<Contact> value = this.contacts.getValue();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
            Iterator<T> it2 = value.iterator();
            while (it2.hasNext()) {
                arrayList.add(ContactId.m10637boximpl(((Contact) it2.next()).m10665getIdYaFaGk()));
            }
            ArrayList arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList();
            Iterator it3 = arrayList2.iterator();
            collection = arrayList3;
            iterable = arrayList2;
            iterable2 = iterable;
            i = 0;
            it = it3;
            i2 = 0;
            if (it.hasNext()) {
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = c04851.I$2;
            i = c04851.I$1;
            i2 = c04851.I$0;
            Object next = c04851.L$4;
            it = (Iterator) c04851.L$3;
            collection = (Collection) c04851.L$2;
            iterable = (Iterable) c04851.L$1;
            iterable2 = (Iterable) c04851.L$0;
            ResultKt.throwOnFailure(objM10688isUnlockedovFUVq0);
            if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                collection.add(next);
            }
            if (it.hasNext()) {
                next = it.next();
                String strM10643unboximpl = ((ContactId) next).m10643unboximpl();
                MessageCipher messageCipher = this.messageCipher;
                c04851.L$0 = SpillingKt.nullOutSpilledVariable(iterable2);
                c04851.L$1 = SpillingKt.nullOutSpilledVariable(iterable);
                c04851.L$2 = collection;
                c04851.L$3 = it;
                c04851.L$4 = next;
                c04851.L$5 = SpillingKt.nullOutSpilledVariable(strM10643unboximpl);
                c04851.I$0 = i2;
                c04851.I$1 = i;
                c04851.I$2 = 0;
                c04851.label = 1;
                objM10688isUnlockedovFUVq0 = messageCipher.m10688isUnlockedovFUVq0(strM10643unboximpl, c04851);
                if (objM10688isUnlockedovFUVq0 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) objM10688isUnlockedovFUVq0).booleanValue()) {
                }
                if (it.hasNext()) {
                    this._unlocked.setValue(CollectionsKt.toSet((List) collection));
                    return Unit.INSTANCE;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: refreshFingerprint-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10453refreshFingerprintovFUVq0(String str, Continuation<? super Unit> continuation) {
        ContactsViewModel$refreshFingerprint$1 contactsViewModel$refreshFingerprint$1;
        Map<ContactId, String> value;
        Map<ContactId, String> value2;
        if (continuation instanceof ContactsViewModel$refreshFingerprint$1) {
            contactsViewModel$refreshFingerprint$1 = (ContactsViewModel$refreshFingerprint$1) continuation;
            if ((contactsViewModel$refreshFingerprint$1.label & Integer.MIN_VALUE) != 0) {
                contactsViewModel$refreshFingerprint$1.label -= Integer.MIN_VALUE;
            } else {
                contactsViewModel$refreshFingerprint$1 = new ContactsViewModel$refreshFingerprint$1(this, continuation);
            }
        }
        Object objM10684activeSessionFingerprintovFUVq0 = contactsViewModel$refreshFingerprint$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = contactsViewModel$refreshFingerprint$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM10684activeSessionFingerprintovFUVq0);
            MessageCipher messageCipher = this.messageCipher;
            contactsViewModel$refreshFingerprint$1.L$0 = str;
            contactsViewModel$refreshFingerprint$1.label = 1;
            objM10684activeSessionFingerprintovFUVq0 = messageCipher.m10684activeSessionFingerprintovFUVq0(str, contactsViewModel$refreshFingerprint$1);
            if (objM10684activeSessionFingerprintovFUVq0 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = (String) contactsViewModel$refreshFingerprint$1.L$0;
            ResultKt.throwOnFailure(objM10684activeSessionFingerprintovFUVq0);
        }
        String str2 = (String) objM10684activeSessionFingerprintovFUVq0;
        if (str2 != null) {
            MutableStateFlow<Map<ContactId, String>> mutableStateFlow = this._fingerprints;
            do {
                value2 = mutableStateFlow.getValue();
            } while (!mutableStateFlow.compareAndSet(value2, MapsKt.plus(value2, TuplesKt.to(ContactId.m10637boximpl(str), str2))));
        } else {
            MutableStateFlow<Map<ContactId, String>> mutableStateFlow2 = this._fingerprints;
            do {
                value = mutableStateFlow2.getValue();
            } while (!mutableStateFlow2.compareAndSet(value, MapsKt.minus(value, ContactId.m10637boximpl(str))));
        }
        return Unit.INSTANCE;
    }
}
