package org.cipherkey.data.repository;

import java.util.Arrays;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.core.messages.SessionInvite;
import org.cipherkey.data.database.SessionKeyEntity;
import org.cipherkey.data.repository.EraImport;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/EraImport;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.SessionKeyRepository$importEra$2", f = "SessionKeyRepository.kt", i = {1, 1, 1}, l = {63, 84}, m = "invokeSuspend", n = {"existing", "entity", "now"}, s = {"L$0", "L$1", "J$0"}, v = 1)
/* loaded from: classes3.dex */
final class SessionKeyRepository$importEra$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EraImport>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f180$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ SessionInvite $invite;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SessionKeyRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SessionKeyRepository$importEra$2(SessionKeyRepository sessionKeyRepository, SessionInvite sessionInvite, String str, Continuation<? super SessionKeyRepository$importEra$2> continuation) {
        super(2, continuation);
        this.this$0 = sessionKeyRepository;
        this.$invite = sessionInvite;
        this.f180$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionKeyRepository$importEra$2(this.this$0, this.$invite, this.f180$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EraImport> continuation) {
        return ((SessionKeyRepository$importEra$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objByKeyId;
        SessionKeyEntity sessionKeyEntity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            objByKeyId = this.this$0.sessionKeyDao.byKeyId(this.$invite.getKeyId(), this);
            if (objByKeyId != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            sessionKeyEntity = (SessionKeyEntity) this.L$1;
            ResultKt.throwOnFailure(obj);
            return new EraImport.Imported(this.this$0.toDomain(sessionKeyEntity));
        }
        ResultKt.throwOnFailure(obj);
        objByKeyId = obj;
        SessionKeyEntity sessionKeyEntity2 = (SessionKeyEntity) objByKeyId;
        if (sessionKeyEntity2 == null) {
            long jNowMillis = this.this$0.timeSource.nowMillis();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            String str = this.f180$$v$c$orgcipherkeycorecommonContactId$contactId$0;
            byte[] keyId = this.$invite.getKeyId();
            byte[] bArrCopyOf = Arrays.copyOf(keyId, keyId.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            byte[] salt = this.$invite.getSalt();
            byte[] bArrCopyOf2 = Arrays.copyOf(salt, salt.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "copyOf(...)");
            SessionKeyEntity sessionKeyEntity3 = new SessionKeyEntity(string, str, bArrCopyOf, bArrCopyOf2, this.$invite.getParams().getMemoryExponent(), this.$invite.getParams().getIterations(), this.$invite.getParams().getParallelism(), null, jNowMillis, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(sessionKeyEntity2);
            this.L$1 = sessionKeyEntity3;
            this.J$0 = jNowMillis;
            this.label = 2;
            if (this.this$0.sessionKeyDao.upsert(sessionKeyEntity3, this) != coroutine_suspended) {
                sessionKeyEntity = sessionKeyEntity3;
                return new EraImport.Imported(this.this$0.toDomain(sessionKeyEntity));
            }
            return coroutine_suspended;
        }
        if (Intrinsics.areEqual(sessionKeyEntity2.getContactId(), this.f180$$v$c$orgcipherkeycorecommonContactId$contactId$0)) {
            return new EraImport.AlreadyPresent(this.this$0.toDomain(sessionKeyEntity2));
        }
        return EraImport.KeyBelongsToOtherContact.INSTANCE;
    }
}
