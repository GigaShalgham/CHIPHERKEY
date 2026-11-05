package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.data.database.SessionKeyEntity;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/ContactSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$latestEra$2", f = "SessionKeyRepository.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class SessionKeyRepository$latestEra$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ContactSession>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f181$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    int label;
    final /* synthetic */ SessionKeyRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SessionKeyRepository$latestEra$2(SessionKeyRepository sessionKeyRepository, String str, Continuation<? super SessionKeyRepository$latestEra$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionKeyRepository;
        this.f181$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionKeyRepository$latestEra$2(this.this$0, this.f181$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ContactSession> continuation) {
        return ((SessionKeyRepository$latestEra$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.sessionKeyDao.latestByContact(this.f181$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
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
            return this.this$0.toDomain(sessionKeyEntity);
        }
        return null;
    }
}
