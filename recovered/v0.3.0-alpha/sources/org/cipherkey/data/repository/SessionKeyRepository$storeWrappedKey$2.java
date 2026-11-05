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

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$storeWrappedKey$2", f = "SessionKeyRepository.kt", i = {}, l = {113}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class SessionKeyRepository$storeWrappedKey$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-SessionId$-sessionId$0, reason: not valid java name */
    final /* synthetic */ String f183$$v$c$orgcipherkeycorecommonSessionId$sessionId$0;
    final /* synthetic */ byte[] $wrapped;
    int label;
    final /* synthetic */ SessionKeyRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SessionKeyRepository$storeWrappedKey$2(SessionKeyRepository sessionKeyRepository, String str, byte[] bArr, Continuation<? super SessionKeyRepository$storeWrappedKey$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionKeyRepository;
        this.f183$$v$c$orgcipherkeycorecommonSessionId$sessionId$0 = str;
        this.$wrapped = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionKeyRepository$storeWrappedKey$2(this.this$0, this.f183$$v$c$orgcipherkeycorecommonSessionId$sessionId$0, this.$wrapped, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SessionKeyRepository$storeWrappedKey$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.sessionKeyDao.updateWrappedKey(this.f183$$v$c$orgcipherkeycorecommonSessionId$sessionId$0, this.$wrapped, this.this$0.timeSource.nowMillis(), this) == coroutine_suspended) {
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
