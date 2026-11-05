package org.cipherkey.app;

import androidx.core.view.MotionEventCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.Collection;
import java.util.Iterator;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.MessageCipher;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u0015"}, d2 = {"Lorg/cipherkey/app/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "contactsRepository", "Lorg/cipherkey/data/repository/ContactRepository;", "messageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "<init>", "(Lorg/cipherkey/data/repository/ContactRepository;Lorg/cipherkey/data/repository/MessageCipher;)V", "_contactCount", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "contactCount", "Lkotlinx/coroutines/flow/StateFlow;", "getContactCount", "()Lkotlinx/coroutines/flow/StateFlow;", "_unlockedCount", "unlockedCount", "getUnlockedCount", "refresh", "", "onCleared", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Integer> _contactCount;
    private final MutableStateFlow<Integer> _unlockedCount;
    private final StateFlow<Integer> contactCount;
    private final ContactRepository contactsRepository;
    private final MessageCipher messageCipher;
    private final StateFlow<Integer> unlockedCount;

    @Inject
    public MainViewModel(ContactRepository contactsRepository, MessageCipher messageCipher) {
        Intrinsics.checkNotNullParameter(contactsRepository, "contactsRepository");
        Intrinsics.checkNotNullParameter(messageCipher, "messageCipher");
        this.contactsRepository = contactsRepository;
        this.messageCipher = messageCipher;
        MutableStateFlow<Integer> MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this._contactCount = MutableStateFlow;
        this.contactCount = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<Integer> MutableStateFlow2 = StateFlowKt.MutableStateFlow(0);
        this._unlockedCount = MutableStateFlow2;
        this.unlockedCount = FlowKt.asStateFlow(MutableStateFlow2);
        refresh();
    }

    public final StateFlow<Integer> getContactCount() {
        return this.contactCount;
    }

    public final StateFlow<Integer> getUnlockedCount() {
        return this.unlockedCount;
    }

    /* compiled from: MainViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.MainViewModel$refresh$1", f = "MainViewModel.kt", i = {2, 2, 2, 2, 2, 2}, l = {40, MotionEventCompat.AXIS_GENERIC_11, MotionEventCompat.AXIS_GENERIC_12}, m = "invokeSuspend", n = {"$this$count$iv", "element$iv", "it", "$i$f$count", "count$iv", "$i$a$-count-MainViewModel$refresh$1$1"}, s = {"L$0", "L$3", "L$4", "I$0", "I$1", "I$2"}, v = 1)
    /* renamed from: org.cipherkey.app.MainViewModel$refresh$1, reason: invalid class name */
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
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MainViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0083, code lost:
        
            if (r14 == r0) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00db, code lost:
        
            if (r14 == r0) goto L28;
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ee  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00db -> B:29:0x00de). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            MutableStateFlow mutableStateFlow2;
            Iterable iterable;
            MainViewModel mainViewModel;
            Iterator it;
            int i;
            MutableStateFlow mutableStateFlow3;
            int i2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            int i4 = 0;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                mutableStateFlow = MainViewModel.this._contactCount;
                this.L$0 = mutableStateFlow;
                this.label = 1;
                obj = MainViewModel.this.contactsRepository.count(this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i3 == 1) {
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i3 == 2) {
                    mutableStateFlow2 = (MutableStateFlow) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    Iterable iterable2 = (Iterable) obj;
                    MainViewModel mainViewModel2 = MainViewModel.this;
                    if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                        iterable = iterable2;
                        mainViewModel = mainViewModel2;
                        it = iterable2.iterator();
                        i = 0;
                        mutableStateFlow3 = mutableStateFlow2;
                        i2 = 0;
                        if (!it.hasNext()) {
                        }
                    }
                    mutableStateFlow2.setValue(Boxing.boxInt(i4));
                    return Unit.INSTANCE;
                }
                if (i3 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i2 = this.I$1;
                i = this.I$0;
                mutableStateFlow3 = (MutableStateFlow) this.L$5;
                it = (Iterator) this.L$2;
                mainViewModel = (MainViewModel) this.L$1;
                iterable = (Iterable) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue() && (i2 = i2 + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
                if (!it.hasNext()) {
                    Object next = it.next();
                    Contact contact = (Contact) next;
                    MessageCipher messageCipher = mainViewModel.messageCipher;
                    String strM10665getIdYaFaGk = contact.m10665getIdYaFaGk();
                    this.L$0 = SpillingKt.nullOutSpilledVariable(iterable);
                    this.L$1 = mainViewModel;
                    this.L$2 = it;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(next);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(contact);
                    this.L$5 = mutableStateFlow3;
                    this.I$0 = i;
                    this.I$1 = i2;
                    this.I$2 = 0;
                    this.label = 3;
                    obj = messageCipher.m10688isUnlockedovFUVq0(strM10665getIdYaFaGk, this);
                } else {
                    i4 = i2;
                    mutableStateFlow2 = mutableStateFlow3;
                    mutableStateFlow2.setValue(Boxing.boxInt(i4));
                    return Unit.INSTANCE;
                }
            }
            mutableStateFlow.setValue(obj);
            mutableStateFlow2 = MainViewModel.this._unlockedCount;
            this.L$0 = mutableStateFlow2;
            this.label = 2;
            obj = FlowKt.first(MainViewModel.this.contactsRepository.observeContacts(), this);
        }
    }

    public final void refresh() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
    }
}
