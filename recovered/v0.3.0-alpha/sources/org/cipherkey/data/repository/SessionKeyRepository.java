package org.cipherkey.data.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.crypto.ProtocolRandom;
import org.cipherkey.core.messages.SessionInvite;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.SessionKeyDao;
import org.cipherkey.data.database.SessionKeyEntity;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\"2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b#\u0010\u001cJ!\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\"0%2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b&\u0010'J\u001a\u0010(\u001a\u0004\u0018\u00010\u001f2\u0006\u0010)\u001a\u00020*H\u0086@¢\u0006\u0004\b+\u0010\u001cJ \u0010,\u001a\u00020-2\u0006\u0010)\u001a\u00020*2\u0006\u0010.\u001a\u00020\u001fH\u0086@¢\u0006\u0004\b/\u00100J\u0018\u00101\u001a\u00020-2\u0006\u0010)\u001a\u00020*H\u0086@¢\u0006\u0004\b2\u0010\u001cJ\u0018\u00103\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b4\u0010\u001cJ\f\u00105\u001a\u00020\r*\u000206H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/cipherkey/data/repository/SessionKeyRepository;", "", "sessionKeyDao", "Lorg/cipherkey/data/database/SessionKeyDao;", "random", "Lorg/cipherkey/core/crypto/ProtocolRandom;", "timeSource", "Lorg/cipherkey/core/sessions/TimeSource;", "dispatchers", "Lorg/cipherkey/core/common/DispatcherProvider;", "<init>", "(Lorg/cipherkey/data/database/SessionKeyDao;Lorg/cipherkey/core/crypto/ProtocolRandom;Lorg/cipherkey/core/sessions/TimeSource;Lorg/cipherkey/core/common/DispatcherProvider;)V", "createEra", "Lorg/cipherkey/data/repository/ContactSession;", "contactId", "Lorg/cipherkey/core/common/ContactId;", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "createEra-exK5HwQ", "(Ljava/lang/String;Lorg/cipherkey/core/crypto/Argon2Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importEra", "Lorg/cipherkey/data/repository/EraImport;", "invite", "Lorg/cipherkey/core/messages/SessionInvite;", "importEra-exK5HwQ", "(Ljava/lang/String;Lorg/cipherkey/core/messages/SessionInvite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "latestEra", "latestEra-ovFUVq0", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "eraByKeyId", "keyId", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "erasForContact", "", "erasForContact-ovFUVq0", "observeEras", "Lkotlinx/coroutines/flow/Flow;", "observeEras-M53eizw", "(Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", "wrappedKeyOf", "sessionId", "Lorg/cipherkey/core/common/SessionId;", "wrappedKeyOf-CueUshU", "storeWrappedKey", "", "wrapped", "storeWrappedKey-dEMjVhk", "(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markUsed", "markUsed-CueUshU", "deleteAllForContact", "deleteAllForContact-ovFUVq0", "toDomain", "Lorg/cipherkey/data/database/SessionKeyEntity;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SessionKeyRepository {
    private final DispatcherProvider dispatchers;
    private final ProtocolRandom random;
    private final SessionKeyDao sessionKeyDao;
    private final TimeSource timeSource;

    public SessionKeyRepository(SessionKeyDao sessionKeyDao, ProtocolRandom random, TimeSource timeSource, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(sessionKeyDao, "sessionKeyDao");
        Intrinsics.checkNotNullParameter(random, "random");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.sessionKeyDao = sessionKeyDao;
        this.random = random;
        this.timeSource = timeSource;
        this.dispatchers = dispatchers;
    }

    /* renamed from: createEra-exK5HwQ, reason: not valid java name */
    public final Object m10692createEraexK5HwQ(String str, Argon2Params argon2Params, Continuation<? super ContactSession> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$createEra$2(this, str, argon2Params, null), continuation);
    }

    /* renamed from: importEra-exK5HwQ, reason: not valid java name */
    public final Object m10695importEraexK5HwQ(String str, SessionInvite sessionInvite, Continuation<? super EraImport> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$importEra$2(this, sessionInvite, str, null), continuation);
    }

    /* renamed from: latestEra-ovFUVq0, reason: not valid java name */
    public final Object m10696latestEraovFUVq0(String str, Continuation<? super ContactSession> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$latestEra$2(this, str, null), continuation);
    }

    /* compiled from: SessionKeyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/ContactSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$eraByKeyId$2", f = "SessionKeyRepository.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.data.repository.SessionKeyRepository$eraByKeyId$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ContactSession>, Object> {
        final /* synthetic */ byte[] $keyId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(byte[] bArr, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$keyId = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SessionKeyRepository.this.new AnonymousClass2(this.$keyId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ContactSession> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = SessionKeyRepository.this.sessionKeyDao.byKeyId(this.$keyId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            SessionKeyEntity sessionKeyEntity = (SessionKeyEntity) obj;
            if (sessionKeyEntity != null) {
                return SessionKeyRepository.this.toDomain(sessionKeyEntity);
            }
            return null;
        }
    }

    public final Object eraByKeyId(byte[] bArr, Continuation<? super ContactSession> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new AnonymousClass2(bArr, null), continuation);
    }

    /* renamed from: erasForContact-ovFUVq0, reason: not valid java name */
    public final Object m10694erasForContactovFUVq0(String str, Continuation<? super List<ContactSession>> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$erasForContact$2(this, str, null), continuation);
    }

    /* renamed from: observeEras-M53eizw, reason: not valid java name */
    public final Flow<List<ContactSession>> m10698observeErasM53eizw(String contactId) {
        Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
        final Flow<List<SessionKeyEntity>> flowObserveByContact = this.sessionKeyDao.observeByContact(contactId);
        return (Flow) new Flow<List<? extends ContactSession>>() { // from class: org.cipherkey.data.repository.SessionKeyRepository$observeEras-M53eizw$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: org.cipherkey.data.repository.SessionKeyRepository$observeEras-M53eizw$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ SessionKeyRepository this$0;

                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$observeEras-M53eizw$$inlined$map$1$2", f = "SessionKeyRepository.kt", i = {0, 0, 0, 0, 0}, l = {217}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u240", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                /* renamed from: org.cipherkey.data.repository.SessionKeyRepository$observeEras-M53eizw$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SessionKeyRepository sessionKeyRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = sessionKeyRepository;
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
                            arrayList.add(this.this$0.toDomain((SessionKeyEntity) it.next()));
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
            public Object collect(FlowCollector<? super List<? extends ContactSession>> flowCollector, Continuation continuation) {
                Object objCollect = flowObserveByContact.collect(new AnonymousClass2(flowCollector, this), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }

    /* renamed from: wrappedKeyOf-CueUshU, reason: not valid java name */
    public final Object m10700wrappedKeyOfCueUshU(String str, Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$wrappedKeyOf$2(this, str, null), continuation);
    }

    /* renamed from: storeWrappedKey-dEMjVhk, reason: not valid java name */
    public final Object m10699storeWrappedKeydEMjVhk(String str, byte[] bArr, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$storeWrappedKey$2(this, str, bArr, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* renamed from: markUsed-CueUshU, reason: not valid java name */
    public final Object m10697markUsedCueUshU(String str, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$markUsed$2(this, str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* renamed from: deleteAllForContact-ovFUVq0, reason: not valid java name */
    public final Object m10693deleteAllForContactovFUVq0(String str, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.dispatchers.getIo(), new SessionKeyRepository$deleteAllForContact$2(this, str, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContactSession toDomain(SessionKeyEntity sessionKeyEntity) {
        return new ContactSession(SessionId.m10645constructorimpl(sessionKeyEntity.getSessionId()), ContactId.m10638constructorimpl(sessionKeyEntity.getContactId()), sessionKeyEntity.getKeyId(), sessionKeyEntity.getSalt(), new Argon2Params(sessionKeyEntity.getMemoryExponent(), sessionKeyEntity.getIterations(), sessionKeyEntity.getParallelism()), sessionKeyEntity.getCreatedAt(), sessionKeyEntity.getWrappedKey() != null, sessionKeyEntity.getLastUsedAt(), null);
    }
}
