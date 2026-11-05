package org.cipherkey.data.repository;

import androidx.core.os.EnvironmentCompat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.i18n.TextBundle;
import org.bouncycastle.math.Primes;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.common.SessionId;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.DecryptionContext;
import org.cipherkey.core.crypto.DecryptionResult;
import org.cipherkey.core.crypto.EncryptedMessage;
import org.cipherkey.core.crypto.EncryptionMode;
import org.cipherkey.core.keys.KeyProtector;
import org.cipherkey.core.messages.Ck1;
import org.cipherkey.core.messages.Ck1FormatException;
import org.cipherkey.core.messages.MessageDecoder;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.SessionKey;
import org.cipherkey.data.repository.DecryptOutcome;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\u0018\u0010\u0019J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001dH\u0086@¢\u0006\u0002\u0010#J\u0018\u0010$\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b&\u0010#J\u000e\u0010'\u001a\u00020(H\u0086@¢\u0006\u0002\u0010)J\u0018\u0010*\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b+\u0010#J\u001a\u0010,\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b-\u0010#J\u001a\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b0\u0010#J\u001a\u00101\u001a\u0004\u0018\u0001022\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b3\u0010#J \u00104\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00105\u001a\u000202H\u0086@¢\u0006\u0004\b6\u00107R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/cipherkey/data/repository/MessageCipher;", "", "contacts", "Lorg/cipherkey/data/repository/ContactRepository;", "sessionKeys", "Lorg/cipherkey/data/repository/SessionKeyRepository;", "sessions", "Lorg/cipherkey/core/sessions/KeySessionManager;", "protector", "Lorg/cipherkey/core/keys/KeyProtector;", "provider", "Lorg/cipherkey/core/crypto/CryptoProvider;", "sessionTimeout", "Lorg/cipherkey/data/repository/SessionTimeoutSource;", "dispatchers", "Lorg/cipherkey/core/common/DispatcherProvider;", "<init>", "(Lorg/cipherkey/data/repository/ContactRepository;Lorg/cipherkey/data/repository/SessionKeyRepository;Lorg/cipherkey/core/sessions/KeySessionManager;Lorg/cipherkey/core/keys/KeyProtector;Lorg/cipherkey/core/crypto/CryptoProvider;Lorg/cipherkey/data/repository/SessionTimeoutSource;Lorg/cipherkey/core/common/DispatcherProvider;)V", "unlockContact", "Lorg/cipherkey/data/repository/UnlockOutcome;", "contactId", "Lorg/cipherkey/core/common/ContactId;", "passphrase", "", "unlockContact-exK5HwQ", "(Ljava/lang/String;[CLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encryptDraft", "Lorg/cipherkey/data/repository/EncryptOutcome;", "plaintext", "", "encryptDraft-exK5HwQ", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decryptEnvelope", "Lorg/cipherkey/data/repository/DecryptOutcome;", TextBundle.TEXT_ENTRY, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockContact", "", "lockContact-ovFUVq0", "lockAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUnlocked", "isUnlocked-ovFUVq0", "activeSessionFingerprint", "activeSessionFingerprint-ovFUVq0", "currentEraId", "Lorg/cipherkey/core/common/SessionId;", "currentEraId-wb5GA7Y", "exportSessionKey", "", "exportSessionKey-ovFUVq0", "unlockContactWithKey", "keyBytes", "unlockContactWithKey-exK5HwQ", "(Ljava/lang/String;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessageCipher {
    private final ContactRepository contacts;
    private final DispatcherProvider dispatchers;
    private final KeyProtector protector;
    private final CryptoProvider provider;
    private final SessionKeyRepository sessionKeys;
    private final SessionTimeoutSource sessionTimeout;
    private final KeySessionManager sessions;

    public MessageCipher(ContactRepository contacts, SessionKeyRepository sessionKeys, KeySessionManager sessions, KeyProtector protector, CryptoProvider provider, SessionTimeoutSource sessionTimeout, DispatcherProvider dispatchers) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(sessionKeys, "sessionKeys");
        Intrinsics.checkNotNullParameter(sessions, "sessions");
        Intrinsics.checkNotNullParameter(protector, "protector");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(sessionTimeout, "sessionTimeout");
        Intrinsics.checkNotNullParameter(dispatchers, "dispatchers");
        this.contacts = contacts;
        this.sessionKeys = sessionKeys;
        this.sessions = sessions;
        this.protector = protector;
        this.provider = provider;
        this.sessionTimeout = sessionTimeout;
        this.dispatchers = dispatchers;
    }

    /* renamed from: unlockContact-exK5HwQ, reason: not valid java name */
    public final Object m10690unlockContactexK5HwQ(String str, char[] cArr, Continuation<? super UnlockOutcome> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new MessageCipher$unlockContact$2(cArr, this, str, null), continuation);
    }

    /* renamed from: encryptDraft-exK5HwQ, reason: not valid java name */
    public final Object m10686encryptDraftexK5HwQ(String str, String str2, Continuation<? super EncryptOutcome> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new MessageCipher$encryptDraft$2(str2, this, str, null), continuation);
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lorg/cipherkey/data/repository/DecryptOutcome;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.data.repository.MessageCipher$decryptEnvelope$2", f = "MessageCipher.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {208, Primes.SMALL_FACTOR_LIMIT, 216, 217, 222, 231}, m = "invokeSuspend", n = {"trimmed", "message", "trimmed", "message", "era", "trimmed", "message", "era", "latest", "trimmed", "message", "era", "latest", "session", "trimmed", "message", "era", "latest", "session", "contactName", "trimmed", "message", "era", "latest", "session", "contactName", "result"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 1)
    /* renamed from: org.cipherkey.data.repository.MessageCipher$decryptEnvelope$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DecryptOutcome>, Object> {
        final /* synthetic */ String $text;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        final /* synthetic */ MessageCipher this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, MessageCipher messageCipher, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$text = str;
            this.this$0 = messageCipher;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$text, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DecryptOutcome> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x00ed  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x012f  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0132  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0194  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0198  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x01a2  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01eb  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x023a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            String string;
            EncryptedMessage encryptedMessageDecode;
            ContactSession contactSession;
            ContactSession contactSession2;
            EncryptedMessage encryptedMessage;
            String str;
            String strM10675getSessionIdAXtOyIM;
            ContactSession contactSession3;
            EncryptedMessage encryptedMessage2;
            ContactSession contactSession4;
            Object objM10667contactNameovFUVq0;
            SessionKey sessionKey;
            EncryptedMessage encryptedMessage3;
            String str2;
            String str3;
            String str4;
            ContactSession contactSession5;
            SessionKey sessionKey2;
            ContactSession contactSession6;
            DecryptionResult decryptionResult;
            DecryptionResult decryptionResult2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    string = StringsKt.trim((CharSequence) this.$text).toString();
                    if (!Ck1.INSTANCE.isEnvelope(string)) {
                        return DecryptOutcome.NotAnEnvelope.INSTANCE;
                    }
                    try {
                        encryptedMessageDecode = MessageDecoder.INSTANCE.decode(string);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(string);
                        this.L$1 = encryptedMessageDecode;
                        this.label = 1;
                        obj = this.this$0.sessionKeys.eraByKeyId(encryptedMessageDecode.getKeyId(), this);
                        if (obj != coroutine_suspended) {
                            contactSession = (ContactSession) obj;
                            if (contactSession != null) {
                                return DecryptOutcome.UnknownKey.INSTANCE;
                            }
                            this.L$0 = SpillingKt.nullOutSpilledVariable(string);
                            this.L$1 = encryptedMessageDecode;
                            this.L$2 = contactSession;
                            this.label = 2;
                            Object objM10696latestEraovFUVq0 = this.this$0.sessionKeys.m10696latestEraovFUVq0(contactSession.m10674getContactIdYaFaGk(), this);
                            if (objM10696latestEraovFUVq0 != coroutine_suspended) {
                                EncryptedMessage encryptedMessage4 = encryptedMessageDecode;
                                contactSession2 = contactSession;
                                obj = objM10696latestEraovFUVq0;
                                encryptedMessage = encryptedMessage4;
                                str = string;
                                ContactSession contactSession7 = (ContactSession) obj;
                                strM10675getSessionIdAXtOyIM = contactSession7 == null ? contactSession7.m10675getSessionIdAXtOyIM() : null;
                                if (strM10675getSessionIdAXtOyIM != null ? false : SessionId.m10647equalsimpl0(strM10675getSessionIdAXtOyIM, contactSession2.m10675getSessionIdAXtOyIM())) {
                                    return DecryptOutcome.StaleKeyEra.INSTANCE;
                                }
                                this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                                this.L$1 = encryptedMessage;
                                this.L$2 = contactSession2;
                                this.L$3 = SpillingKt.nullOutSpilledVariable(contactSession7);
                                this.label = 3;
                                Object objM10654activeSessionovFUVq0 = this.this$0.sessions.m10654activeSessionovFUVq0(contactSession2.m10674getContactIdYaFaGk(), this);
                                if (objM10654activeSessionovFUVq0 != coroutine_suspended) {
                                    ContactSession contactSession8 = contactSession2;
                                    contactSession3 = contactSession7;
                                    obj = objM10654activeSessionovFUVq0;
                                    encryptedMessage2 = encryptedMessage;
                                    contactSession4 = contactSession8;
                                    SessionKey sessionKey3 = (SessionKey) obj;
                                    this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                                    this.L$1 = encryptedMessage2;
                                    this.L$2 = contactSession4;
                                    this.L$3 = SpillingKt.nullOutSpilledVariable(contactSession3);
                                    this.L$4 = sessionKey3;
                                    this.label = 4;
                                    objM10667contactNameovFUVq0 = this.this$0.contacts.m10667contactNameovFUVq0(contactSession4.m10674getContactIdYaFaGk(), this);
                                    if (objM10667contactNameovFUVq0 != coroutine_suspended) {
                                        EncryptedMessage encryptedMessage5 = encryptedMessage2;
                                        sessionKey = sessionKey3;
                                        obj = objM10667contactNameovFUVq0;
                                        encryptedMessage3 = encryptedMessage5;
                                        str2 = str;
                                        str3 = (String) obj;
                                        if (str3 == null) {
                                            str3 = EnvironmentCompat.MEDIA_UNKNOWN;
                                        }
                                        if (sessionKey != null) {
                                            return new DecryptOutcome.NeedsUnlock(contactSession4.m10674getContactIdYaFaGk(), str3, null);
                                        }
                                        this.L$0 = SpillingKt.nullOutSpilledVariable(str2);
                                        this.L$1 = SpillingKt.nullOutSpilledVariable(encryptedMessage3);
                                        this.L$2 = contactSession4;
                                        this.L$3 = SpillingKt.nullOutSpilledVariable(contactSession3);
                                        this.L$4 = SpillingKt.nullOutSpilledVariable(sessionKey);
                                        this.L$5 = str3;
                                        this.label = 5;
                                        Object objDecrypt = this.this$0.provider.decrypt(encryptedMessage3, new DecryptionContext(EncryptionMode.PASSPHRASE, sessionKey.getKeyBytes(), 262144), this);
                                        if (objDecrypt != coroutine_suspended) {
                                            ContactSession contactSession9 = contactSession3;
                                            str4 = str3;
                                            obj = objDecrypt;
                                            contactSession5 = contactSession9;
                                            ContactSession contactSession10 = contactSession4;
                                            sessionKey2 = sessionKey;
                                            contactSession6 = contactSession10;
                                            decryptionResult = (DecryptionResult) obj;
                                            if (decryptionResult instanceof DecryptionResult.Success) {
                                                if (decryptionResult instanceof DecryptionResult.Failure) {
                                                    return new DecryptOutcome.Failed(((DecryptionResult.Failure) decryptionResult).getError());
                                                }
                                                throw new NoWhenBranchMatchedException();
                                            }
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(str2);
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(encryptedMessage3);
                                            this.L$2 = contactSession6;
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(contactSession5);
                                            this.L$4 = SpillingKt.nullOutSpilledVariable(sessionKey2);
                                            this.L$5 = str4;
                                            this.L$6 = decryptionResult;
                                            this.label = 6;
                                            if (this.this$0.sessionKeys.m10697markUsedCueUshU(contactSession6.m10675getSessionIdAXtOyIM(), this) != coroutine_suspended) {
                                                decryptionResult2 = decryptionResult;
                                                return new DecryptOutcome.Decrypted(new String(((DecryptionResult.Success) decryptionResult2).getPlaintext(), Charsets.UTF_8), contactSession6.m10674getContactIdYaFaGk(), str4, null);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return coroutine_suspended;
                    } catch (Ck1FormatException e) {
                        return new DecryptOutcome.Failed(MessageDecoder.INSTANCE.errorOf(e));
                    }
                case 1:
                    encryptedMessageDecode = (EncryptedMessage) this.L$1;
                    string = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    contactSession = (ContactSession) obj;
                    if (contactSession != null) {
                    }
                    break;
                case 2:
                    contactSession2 = (ContactSession) this.L$2;
                    EncryptedMessage encryptedMessage6 = (EncryptedMessage) this.L$1;
                    String str5 = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    str = str5;
                    encryptedMessage = encryptedMessage6;
                    ContactSession contactSession72 = (ContactSession) obj;
                    if (contactSession72 == null) {
                    }
                    if (strM10675getSessionIdAXtOyIM != null ? false : SessionId.m10647equalsimpl0(strM10675getSessionIdAXtOyIM, contactSession2.m10675getSessionIdAXtOyIM())) {
                    }
                    break;
                case 3:
                    contactSession3 = (ContactSession) this.L$3;
                    ContactSession contactSession11 = (ContactSession) this.L$2;
                    EncryptedMessage encryptedMessage7 = (EncryptedMessage) this.L$1;
                    str = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    contactSession4 = contactSession11;
                    encryptedMessage2 = encryptedMessage7;
                    SessionKey sessionKey32 = (SessionKey) obj;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    this.L$1 = encryptedMessage2;
                    this.L$2 = contactSession4;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(contactSession3);
                    this.L$4 = sessionKey32;
                    this.label = 4;
                    objM10667contactNameovFUVq0 = this.this$0.contacts.m10667contactNameovFUVq0(contactSession4.m10674getContactIdYaFaGk(), this);
                    if (objM10667contactNameovFUVq0 != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                case 4:
                    SessionKey sessionKey4 = (SessionKey) this.L$4;
                    ContactSession contactSession12 = (ContactSession) this.L$3;
                    contactSession4 = (ContactSession) this.L$2;
                    EncryptedMessage encryptedMessage8 = (EncryptedMessage) this.L$1;
                    String str6 = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    sessionKey = sessionKey4;
                    contactSession3 = contactSession12;
                    str2 = str6;
                    encryptedMessage3 = encryptedMessage8;
                    str3 = (String) obj;
                    if (str3 == null) {
                    }
                    if (sessionKey != null) {
                    }
                    break;
                case 5:
                    str4 = (String) this.L$5;
                    SessionKey sessionKey5 = (SessionKey) this.L$4;
                    ContactSession contactSession13 = (ContactSession) this.L$3;
                    ContactSession contactSession14 = (ContactSession) this.L$2;
                    encryptedMessage3 = (EncryptedMessage) this.L$1;
                    str2 = (String) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    sessionKey2 = sessionKey5;
                    contactSession6 = contactSession14;
                    contactSession5 = contactSession13;
                    decryptionResult = (DecryptionResult) obj;
                    if (decryptionResult instanceof DecryptionResult.Success) {
                    }
                    break;
                case 6:
                    decryptionResult2 = (DecryptionResult) this.L$6;
                    str4 = (String) this.L$5;
                    contactSession6 = (ContactSession) this.L$2;
                    ResultKt.throwOnFailure(obj);
                    return new DecryptOutcome.Decrypted(new String(((DecryptionResult.Success) decryptionResult2).getPlaintext(), Charsets.UTF_8), contactSession6.m10674getContactIdYaFaGk(), str4, null);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object decryptEnvelope(String str, Continuation<? super DecryptOutcome> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new AnonymousClass2(str, this, null), continuation);
    }

    /* renamed from: lockContact-ovFUVq0, reason: not valid java name */
    public final Object m10689lockContactovFUVq0(String str, Continuation<? super Boolean> continuation) {
        return this.sessions.m10656lockovFUVq0(str, continuation);
    }

    public final Object lockAll(Continuation<? super Integer> continuation) {
        return this.sessions.lockAll(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: isUnlocked-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10688isUnlockedovFUVq0(String str, Continuation<? super Boolean> continuation) {
        MessageCipher$isUnlocked$1 messageCipher$isUnlocked$1;
        if (continuation instanceof MessageCipher$isUnlocked$1) {
            messageCipher$isUnlocked$1 = (MessageCipher$isUnlocked$1) continuation;
            if ((messageCipher$isUnlocked$1.label & Integer.MIN_VALUE) != 0) {
                messageCipher$isUnlocked$1.label -= Integer.MIN_VALUE;
            } else {
                messageCipher$isUnlocked$1 = new MessageCipher$isUnlocked$1(this, continuation);
            }
        }
        Object objM10654activeSessionovFUVq0 = messageCipher$isUnlocked$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = messageCipher$isUnlocked$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM10654activeSessionovFUVq0);
            KeySessionManager keySessionManager = this.sessions;
            messageCipher$isUnlocked$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            messageCipher$isUnlocked$1.label = 1;
            objM10654activeSessionovFUVq0 = keySessionManager.m10654activeSessionovFUVq0(str, messageCipher$isUnlocked$1);
            if (objM10654activeSessionovFUVq0 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objM10654activeSessionovFUVq0);
        }
        return Boxing.boxBoolean(objM10654activeSessionovFUVq0 != null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: activeSessionFingerprint-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10684activeSessionFingerprintovFUVq0(String str, Continuation<? super String> continuation) {
        MessageCipher$activeSessionFingerprint$1 messageCipher$activeSessionFingerprint$1;
        if (continuation instanceof MessageCipher$activeSessionFingerprint$1) {
            messageCipher$activeSessionFingerprint$1 = (MessageCipher$activeSessionFingerprint$1) continuation;
            if ((messageCipher$activeSessionFingerprint$1.label & Integer.MIN_VALUE) != 0) {
                messageCipher$activeSessionFingerprint$1.label -= Integer.MIN_VALUE;
            } else {
                messageCipher$activeSessionFingerprint$1 = new MessageCipher$activeSessionFingerprint$1(this, continuation);
            }
        }
        Object objM10654activeSessionovFUVq0 = messageCipher$activeSessionFingerprint$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = messageCipher$activeSessionFingerprint$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM10654activeSessionovFUVq0);
            KeySessionManager keySessionManager = this.sessions;
            messageCipher$activeSessionFingerprint$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            messageCipher$activeSessionFingerprint$1.label = 1;
            objM10654activeSessionovFUVq0 = keySessionManager.m10654activeSessionovFUVq0(str, messageCipher$activeSessionFingerprint$1);
            if (objM10654activeSessionovFUVq0 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objM10654activeSessionovFUVq0);
        }
        SessionKey sessionKey = (SessionKey) objM10654activeSessionovFUVq0;
        if (sessionKey != null) {
            return sessionKey.getFingerprint();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: currentEraId-wb5GA7Y, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10685currentEraIdwb5GA7Y(String str, Continuation<? super SessionId> continuation) {
        MessageCipher$currentEraId$1 messageCipher$currentEraId$1;
        if (continuation instanceof MessageCipher$currentEraId$1) {
            messageCipher$currentEraId$1 = (MessageCipher$currentEraId$1) continuation;
            if ((messageCipher$currentEraId$1.label & Integer.MIN_VALUE) != 0) {
                messageCipher$currentEraId$1.label -= Integer.MIN_VALUE;
            } else {
                messageCipher$currentEraId$1 = new MessageCipher$currentEraId$1(this, continuation);
            }
        }
        Object objM10696latestEraovFUVq0 = messageCipher$currentEraId$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = messageCipher$currentEraId$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM10696latestEraovFUVq0);
            SessionKeyRepository sessionKeyRepository = this.sessionKeys;
            messageCipher$currentEraId$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            messageCipher$currentEraId$1.label = 1;
            objM10696latestEraovFUVq0 = sessionKeyRepository.m10696latestEraovFUVq0(str, messageCipher$currentEraId$1);
            if (objM10696latestEraovFUVq0 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objM10696latestEraovFUVq0);
        }
        ContactSession contactSession = (ContactSession) objM10696latestEraovFUVq0;
        if (contactSession != null) {
            return contactSession.m10675getSessionIdAXtOyIM();
        }
        return null;
    }

    /* renamed from: exportSessionKey-ovFUVq0, reason: not valid java name */
    public final Object m10687exportSessionKeyovFUVq0(String str, Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new MessageCipher$exportSessionKey$2(this, str, null), continuation);
    }

    /* renamed from: unlockContactWithKey-exK5HwQ, reason: not valid java name */
    public final Object m10691unlockContactWithKeyexK5HwQ(String str, byte[] bArr, Continuation<? super UnlockOutcome> continuation) {
        return BuildersKt.withContext(this.dispatchers.getIo(), new MessageCipher$unlockContactWithKey$2(this, str, bArr, null), continuation);
    }
}
