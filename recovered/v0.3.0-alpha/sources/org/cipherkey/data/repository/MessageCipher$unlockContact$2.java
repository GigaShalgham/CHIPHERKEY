package org.cipherkey.data.repository;

import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.pqc.crypto.sqisign.PrecompLvl1;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.keys.KeyProtectionException;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.SessionKey;
import org.cipherkey.data.repository.UnlockOutcome;
import org.cipherkey.security.policies.KeyPolicy;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/UnlockOutcome;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.MessageCipher$unlockContact$2", f = "MessageCipher.kt", i = {0, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8}, l = {116, 119, 123, 124, PrecompLvl1.SQIsign_RESPONSE_LENGTH, 134, 149, 155, 159}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "era", "$this$withContext", "era", "$this$withContext", "era", "timeout", "$this$withContext", "era", "derived", "timeout", "$this$withContext", "era", "derived", "wrapped", "timeout", "$this$withContext", "era", "derived", "wrapped", "$this$invokeSuspend_u24lambda_u241", "timeout", "$i$a$-runCatching-MessageCipher$unlockContact$2$2", "$this$withContext", "era", "derived", "e", "timeout", "$this$withContext", "era", "derived", "e", "timeout"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "J$0", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "I$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0"}, v = 1)
/* loaded from: classes3.dex */
final class MessageCipher$unlockContact$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UnlockOutcome>, Object> {

    /* renamed from: $$v$c$org-cipherkey-core-common-ContactId$-contactId$0, reason: not valid java name */
    final /* synthetic */ String f175$$v$c$orgcipherkeycorecommonContactId$contactId$0;
    final /* synthetic */ char[] $passphrase;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ MessageCipher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MessageCipher$unlockContact$2(char[] cArr, MessageCipher messageCipher, String str, Continuation<? super MessageCipher$unlockContact$2> continuation) {
        super(2, continuation);
        this.$passphrase = cArr;
        this.this$0 = messageCipher;
        this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MessageCipher$unlockContact$2 messageCipher$unlockContact$2 = new MessageCipher$unlockContact$2(this.$passphrase, this.this$0, this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0, continuation);
        messageCipher$unlockContact$2.L$0 = obj;
        return messageCipher$unlockContact$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UnlockOutcome> continuation) {
        return ((MessageCipher$unlockContact$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x029a, code lost:
    
        if (r17.this$0.sessions.m10656lockovFUVq0(r17.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0, r17) == r3) goto L124;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x02e6, code lost:
    
        if (r17.this$0.sessions.m10656lockovFUVq0(r17.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0, r17) == r3) goto L124;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0181 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007c A[PHI: r0 r6 r8 r9
      0x007c: PHI (r0v39 java.lang.Object) = (r0v36 java.lang.Object), (r0v54 java.lang.Object) binds: [B:62:0x0179, B:18:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r6v27 long) = (r6v25 long), (r6v34 long) binds: [B:62:0x0179, B:18:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r8v14 byte[]) = (r8v12 byte[]), (r8v27 byte[]) binds: [B:62:0x0179, B:18:0x007a] A[DONT_GENERATE, DONT_INLINE]
      0x007c: PHI (r9v9 org.cipherkey.data.repository.ContactSession) = (r9v8 org.cipherkey.data.repository.ContactSession), (r9v14 org.cipherkey.data.repository.ContactSession) binds: [B:62:0x0179, B:18:0x007a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0094 A[PHI: r0 r6 r8
      0x0094: PHI (r0v33 org.cipherkey.data.repository.ContactSession) = (r0v30 org.cipherkey.data.repository.ContactSession), (r0v38 org.cipherkey.data.repository.ContactSession) binds: [B:58:0x0157, B:24:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x0094: PHI (r6v25 long) = (r6v23 long), (r6v26 long) binds: [B:58:0x0157, B:24:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x0094: PHI (r8v11 java.lang.Object) = (r8v10 java.lang.Object), (r8v13 java.lang.Object) binds: [B:58:0x0157, B:24:0x0089] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01e5  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ContactSession contactSession;
        byte[] bArr;
        long j;
        Object objM10696latestEraovFUVq0;
        ContactSession contactSession2;
        Object objM10654activeSessionovFUVq0;
        String sessionId;
        Object objTimeoutMillis;
        long jLongValue;
        Object objDeriveKey;
        ContactSession contactSession3;
        byte[] bArr2;
        Object objM10700wrappedKeyOfCueUshU;
        long j2;
        byte[] bArr3;
        ContactSession contactSession4;
        byte[] bArr4;
        byte[] bArr5;
        KeySessionManager keySessionManager;
        String str;
        String strM10675getSessionIdAXtOyIM;
        byte[] keyId;
        byte[] salt;
        Argon2Params params;
        MessageCipher$unlockContact$2 messageCipher$unlockContact$2;
        byte[] bArr6;
        ContactSession contactSession5;
        SessionKeyRepository sessionKeyRepository;
        String strM10675getSessionIdAXtOyIM2;
        byte[] bArrWrap;
        ContactSession contactSession6;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
        } catch (KeyProtectionException e) {
            e = e;
            contactSession = contactSession6;
        } catch (Exception e2) {
            e = e2;
            contactSession = contactSession6;
        }
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                String strPassphraseProblem = KeyPolicy.INSTANCE.passphraseProblem(this.$passphrase);
                if (strPassphraseProblem != null) {
                    return new UnlockOutcome.Rejected(strPassphraseProblem);
                }
                this.L$0 = coroutineScope;
                this.label = 1;
                objM10696latestEraovFUVq0 = this.this$0.sessionKeys.m10696latestEraovFUVq0(this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
                if (objM10696latestEraovFUVq0 != coroutine_suspended) {
                    contactSession2 = (ContactSession) objM10696latestEraovFUVq0;
                    if (contactSession2 == null) {
                        this.L$0 = coroutineScope;
                        this.L$1 = contactSession2;
                        this.label = 2;
                        objM10654activeSessionovFUVq0 = this.this$0.sessions.m10654activeSessionovFUVq0(this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0, this);
                        if (objM10654activeSessionovFUVq0 != coroutine_suspended) {
                            SessionKey sessionKey = (SessionKey) objM10654activeSessionovFUVq0;
                            sessionId = sessionKey == null ? sessionKey.getSessionId() : null;
                            if (sessionId != null ? false : SessionId.m10647equalsimpl0(sessionId, contactSession2.m10675getSessionIdAXtOyIM())) {
                                this.L$0 = coroutineScope;
                                this.L$1 = contactSession2;
                                this.label = 3;
                                objTimeoutMillis = this.this$0.sessionTimeout.timeoutMillis(this);
                                if (objTimeoutMillis != coroutine_suspended) {
                                    jLongValue = ((Number) objTimeoutMillis).longValue();
                                    this.L$0 = coroutineScope;
                                    this.L$1 = contactSession2;
                                    this.J$0 = jLongValue;
                                    this.label = 4;
                                    objDeriveKey = this.this$0.sessions.deriveKey(this.$passphrase, contactSession2.getSalt(), contactSession2.getParams(), this);
                                    if (objDeriveKey != coroutine_suspended) {
                                        contactSession3 = contactSession2;
                                        bArr2 = (byte[]) objDeriveKey;
                                        this.L$0 = coroutineScope;
                                        this.L$1 = contactSession3;
                                        this.L$2 = bArr2;
                                        this.J$0 = jLongValue;
                                        this.label = 5;
                                        objM10700wrappedKeyOfCueUshU = this.this$0.sessionKeys.m10700wrappedKeyOfCueUshU(contactSession3.m10675getSessionIdAXtOyIM(), this);
                                        if (objM10700wrappedKeyOfCueUshU != coroutine_suspended) {
                                            j2 = jLongValue;
                                            bArr3 = bArr2;
                                            contactSession4 = contactSession3;
                                            try {
                                                bArr5 = (byte[]) objM10700wrappedKeyOfCueUshU;
                                                if (bArr5 != null) {
                                                    try {
                                                        if (!MessageDigest.isEqual(this.this$0.protector.unwrap(bArr5, contactSession4.getKeyId()), bArr3)) {
                                                            ArraysKt.fill$default(bArr3, (byte) 0, 0, 0, 6, (Object) null);
                                                            return UnlockOutcome.WrongPassphrase.INSTANCE;
                                                        }
                                                    } catch (KeyProtectionException e3) {
                                                        e = e3;
                                                        bArr = bArr3;
                                                        contactSession = contactSession4;
                                                        j = j2;
                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                        this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                        this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                        this.L$3 = SpillingKt.nullOutSpilledVariable(e);
                                                        this.L$4 = null;
                                                        this.J$0 = j;
                                                        this.label = 8;
                                                        break;
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        bArr = bArr3;
                                                        contactSession = contactSession4;
                                                        j = j2;
                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                        this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                        this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                        this.L$3 = e;
                                                        this.L$4 = null;
                                                        this.J$0 = j;
                                                        this.label = 9;
                                                        break;
                                                    }
                                                }
                                                keySessionManager = this.this$0.sessions;
                                                str = this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0;
                                                strM10675getSessionIdAXtOyIM = contactSession4.m10675getSessionIdAXtOyIM();
                                                keyId = contactSession4.getKeyId();
                                                salt = contactSession4.getSalt();
                                                params = contactSession4.getParams();
                                                messageCipher$unlockContact$2 = this;
                                                this.L$0 = coroutineScope;
                                                this.L$1 = contactSession4;
                                                this.L$2 = bArr3;
                                                this.L$3 = bArr5;
                                                this.J$0 = j2;
                                                this.label = 6;
                                                contactSession = contactSession4;
                                                bArr4 = bArr3;
                                                try {
                                                    if (keySessionManager.m10653activatefEq4QiY(str, strM10675getSessionIdAXtOyIM, keyId, salt, params, bArr4, j2, messageCipher$unlockContact$2) != coroutine_suspended) {
                                                        bArr = bArr4;
                                                        j = j2;
                                                        if (bArr5 != null) {
                                                            try {
                                                                MessageCipher messageCipher = this.this$0;
                                                                try {
                                                                    Result.Companion companion = Result.INSTANCE;
                                                                    sessionKeyRepository = messageCipher.sessionKeys;
                                                                    strM10675getSessionIdAXtOyIM2 = contactSession.m10675getSessionIdAXtOyIM();
                                                                    bArrWrap = messageCipher.protector.wrap(bArr, contactSession.getKeyId());
                                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                                    this.L$2 = bArr;
                                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(bArr5);
                                                                    this.L$4 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                    this.J$0 = j;
                                                                    this.I$0 = 0;
                                                                    this.label = 7;
                                                                } catch (Throwable th) {
                                                                    th = th;
                                                                    bArr6 = bArr;
                                                                    contactSession5 = contactSession;
                                                                    try {
                                                                        Result.Companion companion2 = Result.INSTANCE;
                                                                        Result.m8783constructorimpl(ResultKt.createFailure(th));
                                                                        contactSession6 = contactSession5;
                                                                        bArr = bArr6;
                                                                        return UnlockOutcome.Unlocked.INSTANCE;
                                                                    } catch (KeyProtectionException e5) {
                                                                        e = e5;
                                                                        contactSession = contactSession5;
                                                                        bArr = bArr6;
                                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                        this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                                        this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                                        this.L$3 = SpillingKt.nullOutSpilledVariable(e);
                                                                        this.L$4 = null;
                                                                        this.J$0 = j;
                                                                        this.label = 8;
                                                                    } catch (Exception e6) {
                                                                        e = e6;
                                                                        contactSession = contactSession5;
                                                                        bArr = bArr6;
                                                                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                        this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                                        this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                                        this.L$3 = e;
                                                                        this.L$4 = null;
                                                                        this.J$0 = j;
                                                                        this.label = 9;
                                                                    }
                                                                }
                                                                if (sessionKeyRepository.m10699storeWrappedKeydEMjVhk(strM10675getSessionIdAXtOyIM2, bArrWrap, this) != coroutine_suspended) {
                                                                    bArr6 = bArr;
                                                                    contactSession5 = contactSession;
                                                                    Result.m8783constructorimpl(Unit.INSTANCE);
                                                                    contactSession6 = contactSession5;
                                                                    bArr = bArr6;
                                                                    return UnlockOutcome.Unlocked.INSTANCE;
                                                                }
                                                            } catch (KeyProtectionException e7) {
                                                                e = e7;
                                                                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                                this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                                this.L$3 = SpillingKt.nullOutSpilledVariable(e);
                                                                this.L$4 = null;
                                                                this.J$0 = j;
                                                                this.label = 8;
                                                            } catch (Exception e8) {
                                                                e = e8;
                                                                ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                                this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                                this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                                this.L$3 = e;
                                                                this.L$4 = null;
                                                                this.J$0 = j;
                                                                this.label = 9;
                                                            }
                                                        } else {
                                                            contactSession6 = contactSession;
                                                            return UnlockOutcome.Unlocked.INSTANCE;
                                                        }
                                                    }
                                                } catch (KeyProtectionException e9) {
                                                    e = e9;
                                                    bArr = bArr4;
                                                    j = j2;
                                                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                    this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(e);
                                                    this.L$4 = null;
                                                    this.J$0 = j;
                                                    this.label = 8;
                                                } catch (Exception e10) {
                                                    e = e10;
                                                    bArr = bArr4;
                                                    j = j2;
                                                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(contactSession);
                                                    this.L$2 = SpillingKt.nullOutSpilledVariable(bArr);
                                                    this.L$3 = e;
                                                    this.L$4 = null;
                                                    this.J$0 = j;
                                                    this.label = 9;
                                                }
                                            } catch (KeyProtectionException e11) {
                                                e = e11;
                                                contactSession = contactSession4;
                                                bArr4 = bArr3;
                                            } catch (Exception e12) {
                                                e = e12;
                                                contactSession = contactSession4;
                                                bArr4 = bArr3;
                                            }
                                        }
                                    }
                                }
                            } else {
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
                objM10696latestEraovFUVq0 = obj;
                contactSession2 = (ContactSession) objM10696latestEraovFUVq0;
                if (contactSession2 == null) {
                }
                break;
            case 2:
                contactSession2 = (ContactSession) this.L$1;
                ResultKt.throwOnFailure(obj);
                objM10654activeSessionovFUVq0 = obj;
                SessionKey sessionKey2 = (SessionKey) objM10654activeSessionovFUVq0;
                if (sessionKey2 == null) {
                }
                if (sessionId != null ? false : SessionId.m10647equalsimpl0(sessionId, contactSession2.m10675getSessionIdAXtOyIM())) {
                }
                break;
            case 3:
                contactSession2 = (ContactSession) this.L$1;
                ResultKt.throwOnFailure(obj);
                objTimeoutMillis = obj;
                jLongValue = ((Number) objTimeoutMillis).longValue();
                this.L$0 = coroutineScope;
                this.L$1 = contactSession2;
                this.J$0 = jLongValue;
                this.label = 4;
                objDeriveKey = this.this$0.sessions.deriveKey(this.$passphrase, contactSession2.getSalt(), contactSession2.getParams(), this);
                if (objDeriveKey != coroutine_suspended) {
                }
                return coroutine_suspended;
            case 4:
                jLongValue = this.J$0;
                contactSession2 = (ContactSession) this.L$1;
                ResultKt.throwOnFailure(obj);
                objDeriveKey = obj;
                contactSession3 = contactSession2;
                bArr2 = (byte[]) objDeriveKey;
                this.L$0 = coroutineScope;
                this.L$1 = contactSession3;
                this.L$2 = bArr2;
                this.J$0 = jLongValue;
                this.label = 5;
                objM10700wrappedKeyOfCueUshU = this.this$0.sessionKeys.m10700wrappedKeyOfCueUshU(contactSession3.m10675getSessionIdAXtOyIM(), this);
                if (objM10700wrappedKeyOfCueUshU != coroutine_suspended) {
                }
                return coroutine_suspended;
            case 5:
                jLongValue = this.J$0;
                bArr2 = (byte[]) this.L$2;
                contactSession3 = (ContactSession) this.L$1;
                ResultKt.throwOnFailure(obj);
                objM10700wrappedKeyOfCueUshU = obj;
                j2 = jLongValue;
                bArr3 = bArr2;
                contactSession4 = contactSession3;
                bArr5 = (byte[]) objM10700wrappedKeyOfCueUshU;
                if (bArr5 != null) {
                }
                keySessionManager = this.this$0.sessions;
                str = this.f175$$v$c$orgcipherkeycorecommonContactId$contactId$0;
                strM10675getSessionIdAXtOyIM = contactSession4.m10675getSessionIdAXtOyIM();
                keyId = contactSession4.getKeyId();
                salt = contactSession4.getSalt();
                params = contactSession4.getParams();
                messageCipher$unlockContact$2 = this;
                this.L$0 = coroutineScope;
                this.L$1 = contactSession4;
                this.L$2 = bArr3;
                this.L$3 = bArr5;
                this.J$0 = j2;
                this.label = 6;
                contactSession = contactSession4;
                bArr4 = bArr3;
                if (keySessionManager.m10653activatefEq4QiY(str, strM10675getSessionIdAXtOyIM, keyId, salt, params, bArr4, j2, messageCipher$unlockContact$2) != coroutine_suspended) {
                }
                return coroutine_suspended;
            case 6:
                j = this.J$0;
                bArr5 = (byte[]) this.L$3;
                bArr = (byte[]) this.L$2;
                ContactSession contactSession7 = (ContactSession) this.L$1;
                ResultKt.throwOnFailure(obj);
                contactSession = contactSession7;
                if (bArr5 != null) {
                }
                break;
            case 7:
                j = this.J$0;
                bArr6 = (byte[]) this.L$2;
                contactSession5 = (ContactSession) this.L$1;
                try {
                    ResultKt.throwOnFailure(obj);
                    Result.m8783constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion22 = Result.INSTANCE;
                    Result.m8783constructorimpl(ResultKt.createFailure(th));
                    contactSession6 = contactSession5;
                    bArr = bArr6;
                    return UnlockOutcome.Unlocked.INSTANCE;
                }
                contactSession6 = contactSession5;
                bArr = bArr6;
                return UnlockOutcome.Unlocked.INSTANCE;
            case 8:
                ResultKt.throwOnFailure(obj);
                return UnlockOutcome.WrongPassphrase.INSTANCE;
            case 9:
                e = (Exception) this.L$3;
                ResultKt.throwOnFailure(obj);
                String message = e.getMessage();
                if (message == null) {
                    message = "unlock failed";
                }
                return new UnlockOutcome.Failed(message);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
