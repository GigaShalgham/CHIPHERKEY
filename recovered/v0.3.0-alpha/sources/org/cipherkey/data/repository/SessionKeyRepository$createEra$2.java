package org.cipherkey.data.repository;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.data.database.SessionKeyEntity;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/ContactSession;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$createEra$2", f = "SessionKeyRepository.kt", i = {0, 0}, l = {56}, m = "invokeSuspend", n = {"entity", "now"}, s = {"L$0", "J$0"}, v = 1)
/* loaded from: classes3.dex */
final class SessionKeyRepository$createEra$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ContactSession>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f177$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ Argon2Params $params;
    long J$0;
    Object L$0;
    int label;
    final /* synthetic */ SessionKeyRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SessionKeyRepository$createEra$2(SessionKeyRepository sessionKeyRepository, String str, Argon2Params argon2Params, Continuation<? super SessionKeyRepository$createEra$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionKeyRepository;
        this.f177$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
        this.$params = argon2Params;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionKeyRepository$createEra$2(this.this$0, this.f177$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$params, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ContactSession> continuation) {
        return ((SessionKeyRepository$createEra$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SessionKeyEntity sessionKeyEntity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long jNowMillis = this.this$0.timeSource.nowMillis();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            SessionKeyEntity sessionKeyEntity2 = new SessionKeyEntity(string, this.f177$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.this$0.random.keyId(), this.this$0.random.salt(), this.$params.getMemoryExponent(), this.$params.getIterations(), this.$params.getParallelism(), null, jNowMillis, null);
            this.L$0 = sessionKeyEntity2;
            this.J$0 = jNowMillis;
            this.label = 1;
            if (this.this$0.sessionKeyDao.upsert(sessionKeyEntity2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            sessionKeyEntity = sessionKeyEntity2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sessionKeyEntity = (SessionKeyEntity) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return this.this$0.toDomain(sessionKeyEntity);
    }
}
