package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.EncryptionContext;
import org.cipherkey.core.crypto.EncryptionMode;
import org.cipherkey.core.crypto.EncryptionResult;
import org.cipherkey.core.messages.MessageEncoder;
import org.cipherkey.core.sessions.SessionKey;
import org.cipherkey.data.repository.EncryptOutcome;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/EncryptOutcome;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.MessageCipher$encryptDraft$2", f = "MessageCipher.kt", i = {1, 2, 2, 3, 3}, l = {169, 172, 177, 181}, m = "invokeSuspend", n = {"era", "era", "session", "era", "session"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1"}, v = 1)
/* loaded from: classes3.dex */
final class MessageCipher$encryptDraft$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EncryptOutcome>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f173$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ String $plaintext;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MessageCipher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MessageCipher$encryptDraft$2(String str, MessageCipher messageCipher, String str2, Continuation<? super MessageCipher$encryptDraft$2> continuation) {
        super(2, continuation);
        this.$plaintext = str;
        this.this$0 = messageCipher;
        this.f173$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MessageCipher$encryptDraft$2(this.$plaintext, this.this$0, this.f173$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EncryptOutcome> continuation) {
        return ((MessageCipher$encryptDraft$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b8, code lost:
    
        if (r13.this$0.sessions.m10656lockovFUVq0(r13.f173$$v$c$orgcipherkeycorecommonContactId$contactId$0, r13) == r0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ff, code lost:
    
        if (r14 == r0) goto L41;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ContactSession contactSession;
        SessionKey sessionKey;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$plaintext.length() == 0) {
                return EncryptOutcome.EmptyDraft.INSTANCE;
            }
            this.label = 1;
            obj = this.this$0.sessionKeys.m10696latestEraovFUVq0(this.f173$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
            if (obj != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    return EncryptOutcome.NeedsUnlock.INSTANCE;
                }
                if (i != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                EncryptionResult encryptionResult = (EncryptionResult) obj;
                if (encryptionResult instanceof EncryptionResult.Success) {
                    return new EncryptOutcome.Encrypted(MessageEncoder.INSTANCE.encode(((EncryptionResult.Success) encryptionResult).getMessage()));
                }
                if (encryptionResult instanceof EncryptionResult.Failure) {
                    return new EncryptOutcome.Failed(((EncryptionResult.Failure) encryptionResult).getError());
                }
                throw new NoWhenBranchMatchedException();
            }
            contactSession = (ContactSession) this.L$0;
            ResultKt.throwOnFailure(obj);
            sessionKey = (SessionKey) obj;
            if (sessionKey != null) {
                return EncryptOutcome.NeedsUnlock.INSTANCE;
            }
            if (SessionId.m10647equalsimpl0(sessionKey.getSessionId(), contactSession.m10675getSessionIdAXtOyIM())) {
                CryptoProvider cryptoProvider = this.this$0.provider;
                byte[] bytes = this.$plaintext.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                this.L$0 = SpillingKt.nullOutSpilledVariable(contactSession);
                this.L$1 = SpillingKt.nullOutSpilledVariable(sessionKey);
                this.label = 4;
                obj = cryptoProvider.encrypt(bytes, new EncryptionContext(EncryptionMode.PASSPHRASE, sessionKey.getKeyBytes(), contactSession.getKeyId(), contactSession.getSalt(), contactSession.getParams(), 16384), this);
            } else {
                this.L$0 = SpillingKt.nullOutSpilledVariable(contactSession);
                this.L$1 = SpillingKt.nullOutSpilledVariable(sessionKey);
                this.label = 3;
            }
            return coroutine_suspended;
        }
        ResultKt.throwOnFailure(obj);
        contactSession = (ContactSession) obj;
        if (contactSession != null) {
            this.L$0 = contactSession;
            this.label = 2;
            obj = this.this$0.sessions.m10654activeSessionovFUVq0(this.f173$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
            if (obj != coroutine_suspended) {
                sessionKey = (SessionKey) obj;
                if (sessionKey != null) {
                }
            }
            return coroutine_suspended;
        }
        return EncryptOutcome.NoSession.INSTANCE;
    }
}
