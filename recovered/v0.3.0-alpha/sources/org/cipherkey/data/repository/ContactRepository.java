package org.cipherkey.data.repository;

import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.ContactDao;
import org.cipherkey.data.database.ContactEntity;

/* compiled from: ContactRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0015\u0010\u0012J\u000e\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010\u0012J\"\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b!\u0010\u0012J\f\u0010\"\u001a\u00020\r*\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/cipherkey/data/repository/ContactRepository;", "", "contactDao", "Lorg/cipherkey/data/database/ContactDao;", "timeSource", "Lorg/cipherkey/core/sessions/TimeSource;", "dispatchers", "Lorg/cipherkey/core/common/DispatcherProvider;", "<init>", "(Lorg/cipherkey/data/database/ContactDao;Lorg/cipherkey/core/sessions/TimeSource;Lorg/cipherkey/core/common/DispatcherProvider;)V", "observeContacts", "Lkotlinx/coroutines/flow/Flow;", "", "Lorg/cipherkey/data/repository/Contact;", "contact", "contactId", "Lorg/cipherkey/core/common/ContactId;", "contact-ovFUVq0", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contactName", "", "contactName-ovFUVq0", "count", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createContact", "displayName", "renameContact", "newName", "renameContact-exK5HwQ", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteContact", "", "deleteContact-ovFUVq0", "toDomain", "Lorg/cipherkey/data/database/ContactEntity;", "Companion", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactRepository {
    public static final int MAX_NAME_LENGTH = 64;
    private final ContactDao contactDao;
    private final DispatcherProvider dispatchers;
    private final TimeSource timeSource;

    public ContactRepository(ContactDao contactDao, TimeSource timeSource, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(contactDao, "contactDao");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.contactDao = contactDao;
        this.timeSource = timeSource;
        this.dispatchers = dispatchers;
    }

    public final Flow<List<Contact>> observeContacts() {
        final Flow<List<ContactEntity>> flowObserveAll = this.contactDao.observeAll();
        return (Flow) new Flow<List<? extends Contact>>() { // from class: org.cipherkey.data.repository.ContactRepository$observeContacts$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: org.cipherkey.data.repository.ContactRepository$observeContacts$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ ContactRepository this$0;

                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "org.cipherkey.data.repository.ContactRepository$observeContacts$$inlined$map$1$2", f = "ContactRepository.kt", i = {0, 0, 0, 0, 0}, l = {217}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u240", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                /* renamed from: org.cipherkey.data.repository.ContactRepository$observeContacts$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ContactRepository contactRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = contactRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label -= Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = anonymousClass1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        List list = (List) obj;
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(this.this$0.toDomain((ContactEntity) it.next()));
                        }
                        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                        anonymousClass1.I$0 = 0;
                        anonymousClass1.label = 1;
                        if (flowCollector.emit(arrayList, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        int i2 = anonymousClass1.I$0;
                        Object obj3 = anonymousClass1.L$2;
                        Object obj4 = anonymousClass1.L$0;
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super List<? extends Contact>> flowCollector, Continuation continuation) {
                Object objCollect = flowObserveAll.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }

    /* renamed from: contact-ovFUVq0, reason: not valid java name */
    public final Object m10666contactovFUVq0(String str, Continuation<? super Contact> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new ContactRepository$contact$2(this, str, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: contactName-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10667contactNameovFUVq0(String str, Continuation<? super String> continuation) {
        ContactRepository$contactName$1 contactRepository$contactName$1;
        if (continuation instanceof ContactRepository$contactName$1) {
            contactRepository$contactName$1 = (ContactRepository$contactName$1) continuation;
            if ((contactRepository$contactName$1.label & Integer.MIN_VALUE) != 0) {
                contactRepository$contactName$1.label -= Integer.MIN_VALUE;
            } else {
                contactRepository$contactName$1 = new ContactRepository$contactName$1(this, continuation);
            }
        }
        Object objM10666contactovFUVq0 = contactRepository$contactName$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = contactRepository$contactName$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM10666contactovFUVq0);
            contactRepository$contactName$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            contactRepository$contactName$1.label = 1;
            objM10666contactovFUVq0 = m10666contactovFUVq0(str, contactRepository$contactName$1);
            if (objM10666contactovFUVq0 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objM10666contactovFUVq0);
        }
        Contact contact = (Contact) objM10666contactovFUVq0;
        if (contact != null) {
            return contact.getDisplayName();
        }
        return null;
    }

    /* compiled from: ContactRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.data.repository.ContactRepository$count$2", f = "ContactRepository.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.data.repository.ContactRepository$count$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactRepository.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            Object objCount = ContactRepository.this.contactDao.count(this);
            return objCount == coroutine_suspended ? coroutine_suspended : objCount;
        }
    }

    public final Object count(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new AnonymousClass2(null), continuation);
    }

    /* compiled from: ContactRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/Contact;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.data.repository.ContactRepository$createContact$2", f = "ContactRepository.kt", i = {0, 0, 0}, l = {51}, m = "invokeSuspend", n = {HintConstants.AUTOFILL_HINT_NAME, "entity", "now"}, s = {"L$0", "L$1", "J$0"}, v = 1)
    /* renamed from: org.cipherkey.data.repository.ContactRepository$createContact$2, reason: invalid class name and case insensitive filesystem */
    static final class C05142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Contact>, Object> {
        final /* synthetic */ String $displayName;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ ContactRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05142(String str, ContactRepository contactRepository, Continuation<? super C05142> continuation) {
            super(2, continuation);
            this.$displayName = str;
            this.this$0 = contactRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C05142(this.$displayName, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Contact> continuation) {
            return ((C05142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ContactEntity contactEntity;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String string = StringsKt.trim((CharSequence) this.$displayName).toString();
                if (string.length() <= 0) {
                    throw new IllegalArgumentException("contact name must not be empty".toString());
                }
                if (string.length() <= 64) {
                    long jNowMillis = this.this$0.timeSource.nowMillis();
                    String string2 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                    ContactEntity contactEntity2 = new ContactEntity(string2, string, jNowMillis, jNowMillis);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(string);
                    this.L$1 = contactEntity2;
                    this.J$0 = jNowMillis;
                    this.label = 1;
                    if (this.this$0.contactDao.upsert(contactEntity2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    contactEntity = contactEntity2;
                } else {
                    throw new IllegalArgumentException("contact name must be at most 64 chars".toString());
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                contactEntity = (ContactEntity) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            return this.this$0.toDomain(contactEntity);
        }
    }

    public final Object createContact(String str, Continuation<? super Contact> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new C05142(str, this, null), continuation);
    }

    /* renamed from: renameContact-exK5HwQ, reason: not valid java name */
    public final Object m10669renameContactexK5HwQ(String str, String str2, Continuation<? super Contact> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new ContactRepository$renameContact$2(str2, this, str, null), continuation);
    }

    /* renamed from: deleteContact-ovFUVq0, reason: not valid java name */
    public final Object m10668deleteContactovFUVq0(String str, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.dispatchers.getIo(), new ContactRepository$deleteContact$2(this, str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Contact toDomain(ContactEntity contactEntity) {
        return new Contact(ContactId.m10638constructorimpl(contactEntity.getId()), contactEntity.getDisplayName(), contactEntity.getCreatedAt(), contactEntity.getUpdatedAt(), null);
    }
}
