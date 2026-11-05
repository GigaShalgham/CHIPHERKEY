package org.cipherkey.data.repository;

import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.keys.KeyProtectionException;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.SessionKey;
import org.cipherkey.data.repository.UnlockOutcome;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/UnlockOutcome;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.MessageCipher$unlockContactWithKey$2", f = "MessageCipher.kt", i = {1, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6}, l = {284, 286, 295, 303, 306, 319, 323}, m = "invokeSuspend", n = {"era", "era", "era", "wrapped", "era", "wrapped", "timeout", "era", "e", "era", "e"}, s = {"L$0", "L$0", "L$0", "L$1", "L$0", "L$1", "J$0", "L$0", "L$1", "L$0", "L$1"}, v = 1)
/* loaded from: classes3.dex */
final class MessageCipher$unlockContactWithKey$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UnlockOutcome>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f176$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ byte[] $keyBytes;
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MessageCipher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MessageCipher$unlockContactWithKey$2(MessageCipher messageCipher, String str, byte[] bArr, Continuation<? super MessageCipher$unlockContactWithKey$2> continuation) {
        super(2, continuation);
        this.this$0 = messageCipher;
        this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
        this.$keyBytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MessageCipher$unlockContactWithKey$2(this.this$0, this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0, this.$keyBytes, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UnlockOutcome> continuation) {
        return ((MessageCipher$unlockContactWithKey$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x019a, code lost:
    
        if (r13.this$0.sessions.m10656lockovFUVq0(r13.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0, r13) != r1) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01d5, code lost:
    
        if (r13.this$0.sessions.m10656lockovFUVq0(r13.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0, r13) != r1) goto L72;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x018b: INVOKE (r2 I:java.lang.Object) = (r2 I:java.lang.Object) STATIC call: kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(java.lang.Object):java.lang.Object A[MD:(java.lang.Object):java.lang.Object (m)], block:B:62:0x0176 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x01c2: INVOKE (r2 I:java.lang.Object) = (r2 I:java.lang.Object) STATIC call: kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(java.lang.Object):java.lang.Object A[MD:(java.lang.Object):java.lang.Object (m)], block:B:69:0x01ad */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c4 A[Catch: Exception -> 0x0050, KeyProtectionException -> 0x0054, TRY_ENTER, TryCatch #2 {KeyProtectionException -> 0x0054, Exception -> 0x0050, blocks: (B:9:0x0034, B:60:0x0167, B:12:0x0041, B:57:0x0122, B:15:0x004b, B:48:0x00de, B:50:0x00e3, B:52:0x00f9, B:54:0x0106, B:45:0x00c4), top: B:74:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0122 A[Catch: Exception -> 0x0050, KeyProtectionException -> 0x0054, PHI: r0 r2 r14
      0x0122: PHI (r0v21 byte[]) = (r0v19 byte[]), (r0v23 byte[]) binds: [B:55:0x011e, B:12:0x0041] A[DONT_GENERATE, DONT_INLINE]
      0x0122: PHI (r2v14 org.cipherkey.data.repository.ContactSession) = (r2v11 org.cipherkey.data.repository.ContactSession), (r2v16 org.cipherkey.data.repository.ContactSession) binds: [B:55:0x011e, B:12:0x0041] A[DONT_GENERATE, DONT_INLINE]
      0x0122: PHI (r14v41 java.lang.Object) = (r14v35 java.lang.Object), (r14v0 java.lang.Object) binds: [B:55:0x011e, B:12:0x0041] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {KeyProtectionException -> 0x0054, Exception -> 0x0050, blocks: (B:9:0x0034, B:60:0x0167, B:12:0x0041, B:57:0x0122, B:15:0x004b, B:48:0x00de, B:50:0x00e3, B:52:0x00f9, B:54:0x0106, B:45:0x00c4), top: B:74:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0166  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object objNullOutSpilledVariable;
        Object objNullOutSpilledVariable2;
        ContactSession contactSession;
        ContactSession contactSession2;
        String sessionId;
        byte[] bArr;
        long jLongValue;
        KeySessionManager keySessionManager;
        String str;
        String strM10675getSessionIdAXtOyIM;
        byte[] keyId;
        byte[] salt;
        Argon2Params params;
        byte[] bArrCopyOf;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (KeyProtectionException e) {
            ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(objNullOutSpilledVariable2);
            this.L$1 = SpillingKt.nullOutSpilledVariable(e);
            this.label = 6;
        } catch (Exception e2) {
            e = e2;
            ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(objNullOutSpilledVariable);
            this.L$1 = e;
            this.label = 7;
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = this.this$0.sessionKeys.m10696latestEraovFUVq0(this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
                if (obj != coroutine_suspended) {
                    contactSession = (ContactSession) obj;
                    if (contactSession == null) {
                        this.L$0 = contactSession;
                        this.label = 2;
                        Object objM10654activeSessionovFUVq0 = this.this$0.sessions.m10654activeSessionovFUVq0(this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
                        if (objM10654activeSessionovFUVq0 != coroutine_suspended) {
                            contactSession2 = contactSession;
                            obj = objM10654activeSessionovFUVq0;
                            SessionKey sessionKey = (SessionKey) obj;
                            sessionId = sessionKey == null ? sessionKey.getSessionId() : null;
                            if (sessionId != null ? false : SessionId.m10647equalsimpl0(sessionId, contactSession2.m10675getSessionIdAXtOyIM())) {
                                this.L$0 = contactSession2;
                                this.label = 3;
                                obj = this.this$0.sessionKeys.m10700wrappedKeyOfCueUshU(contactSession2.m10675getSessionIdAXtOyIM(), this);
                                if (obj != coroutine_suspended) {
                                    bArr = (byte[]) obj;
                                    if (bArr != null || MessageDigest.isEqual(this.this$0.protector.unwrap(bArr, contactSession2.getKeyId()), this.$keyBytes)) {
                                        this.L$0 = contactSession2;
                                        this.L$1 = SpillingKt.nullOutSpilledVariable(bArr);
                                        this.label = 4;
                                        obj = this.this$0.sessionTimeout.timeoutMillis(this);
                                        if (obj == coroutine_suspended) {
                                            jLongValue = ((Number) obj).longValue();
                                            keySessionManager = this.this$0.sessions;
                                            str = this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0;
                                            strM10675getSessionIdAXtOyIM = contactSession2.m10675getSessionIdAXtOyIM();
                                            keyId = contactSession2.getKeyId();
                                            salt = contactSession2.getSalt();
                                            params = contactSession2.getParams();
                                            byte[] bArr2 = this.$keyBytes;
                                            bArrCopyOf = Arrays.copyOf(bArr2, bArr2.length);
                                            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(contactSession2);
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(bArr);
                                            this.J$0 = jLongValue;
                                            this.label = 5;
                                            if (keySessionManager.m10653activatefEq4QiY(str, strM10675getSessionIdAXtOyIM, keyId, salt, params, bArrCopyOf, jLongValue, this) == coroutine_suspended) {
                                            }
                                            ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
                                            return UnlockOutcome.Unlocked.INSTANCE;
                                        }
                                    } else {
                                        ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
                                        return UnlockOutcome.WrongPassphrase.INSTANCE;
                                    }
                                }
                            } else {
                                ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
                                return UnlockOutcome.AlreadyUnlocked.INSTANCE;
                            }
                        }
                    } else {
                        return UnlockOutcome.NoSession.INSTANCE;
                    }
                }
                return coroutine_suspended;
            case 1:
                ResultKt.throwOnFailure(obj);
                contactSession = (ContactSession) obj;
                if (contactSession == null) {
                }
                break;
            case 2:
                ContactSession contactSession3 = (ContactSession) this.L$0;
                ResultKt.throwOnFailure(obj);
                contactSession2 = contactSession3;
                SessionKey sessionKey2 = (SessionKey) obj;
                if (sessionKey2 == null) {
                }
                if (sessionId != null ? false : SessionId.m10647equalsimpl0(sessionId, contactSession2.m10675getSessionIdAXtOyIM())) {
                }
                break;
            case 3:
                contactSession2 = (ContactSession) this.L$0;
                ResultKt.throwOnFailure(obj);
                bArr = (byte[]) obj;
                if (bArr != null) {
                    break;
                }
                this.L$0 = contactSession2;
                this.L$1 = SpillingKt.nullOutSpilledVariable(bArr);
                this.label = 4;
                obj = this.this$0.sessionTimeout.timeoutMillis(this);
                if (obj == coroutine_suspended) {
                }
                return coroutine_suspended;
            case 4:
                bArr = (byte[]) this.L$1;
                contactSession2 = (ContactSession) this.L$0;
                ResultKt.throwOnFailure(obj);
                jLongValue = ((Number) obj).longValue();
                keySessionManager = this.this$0.sessions;
                str = this.f176$$v$c$orgcipherkeycorecommonContactId$contactId$0;
                strM10675getSessionIdAXtOyIM = contactSession2.m10675getSessionIdAXtOyIM();
                keyId = contactSession2.getKeyId();
                salt = contactSession2.getSalt();
                params = contactSession2.getParams();
                byte[] bArr22 = this.$keyBytes;
                bArrCopyOf = Arrays.copyOf(bArr22, bArr22.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                this.L$0 = SpillingKt.nullOutSpilledVariable(contactSession2);
                this.L$1 = SpillingKt.nullOutSpilledVariable(bArr);
                this.J$0 = jLongValue;
                this.label = 5;
                if (keySessionManager.m10653activatefEq4QiY(str, strM10675getSessionIdAXtOyIM, keyId, salt, params, bArrCopyOf, jLongValue, this) == coroutine_suspended) {
                }
                ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
                return UnlockOutcome.Unlocked.INSTANCE;
            case 5:
                ResultKt.throwOnFailure(obj);
                ArraysKt.fill$default(this.$keyBytes, (byte) 0, 0, 0, 6, (Object) null);
                return UnlockOutcome.Unlocked.INSTANCE;
            case 6:
                ResultKt.throwOnFailure(obj);
                return new UnlockOutcome.Failed("key verification failed");
            case 7:
                e = (Exception) this.L$1;
                ResultKt.throwOnFailure(obj);
                String message = e.getMessage();
                if (message == null) {
                    message = "activate failed";
                }
                return new UnlockOutcome.Failed(message);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
