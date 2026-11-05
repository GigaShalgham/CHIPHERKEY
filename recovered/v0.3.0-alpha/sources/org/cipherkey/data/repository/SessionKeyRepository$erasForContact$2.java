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
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.cipherkey.data.database.SessionKeyEntity;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lorg/cipherkey/data/repository/ContactSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$erasForContact$2", f = "SessionKeyRepository.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class SessionKeyRepository$erasForContact$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ContactSession>>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f179$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    int label;
    final /* synthetic */ SessionKeyRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SessionKeyRepository$erasForContact$2(SessionKeyRepository sessionKeyRepository, String str, Continuation<? super SessionKeyRepository$erasForContact$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionKeyRepository;
        this.f179$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionKeyRepository$erasForContact$2(this.this$0, this.f179$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ContactSession>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<ContactSession>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<ContactSession>> continuation) {
        return ((SessionKeyRepository$erasForContact$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = FlowKt.first(this.this$0.sessionKeyDao.observeByContact(this.f179$$v$c$orgcipherkeycorecommonContactId$contactId$0), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Iterable iterable = (Iterable) obj;
        SessionKeyRepository sessionKeyRepository = this.this$0;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(sessionKeyRepository.toDomain((SessionKeyEntity) it.next()));
        }
        return arrayList;
    }
}
