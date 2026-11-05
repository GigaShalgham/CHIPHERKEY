package org.cipherkey.core.sessions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.crypto.Argon2KeyDerivator;
import org.cipherkey.core.crypto.Argon2Params;

/* compiled from: KeySessionManager.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00015B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018JH\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0086@¢\u0006\u0004\b\"\u0010#J\u001a\u0010$\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b%\u0010&J\u0018\u0010'\u001a\u00020(2\u0006\u0010\u001b\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b)\u0010&J\u000e\u0010*\u001a\u00020+H\u0086@¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0.H\u0086@¢\u0006\u0002\u0010,J\u001a\u0010/\u001a\u0004\u0018\u00010!2\u0006\u0010\u001b\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b0\u0010&J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u001aH\u0002J\b\u00104\u001a\u000202H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lorg/cipherkey/core/sessions/KeySessionManager;", "", "derivator", "Lorg/cipherkey/core/crypto/Argon2KeyDerivator;", "timeSource", "Lorg/cipherkey/core/sessions/TimeSource;", "deriveContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lorg/cipherkey/core/crypto/Argon2KeyDerivator;Lorg/cipherkey/core/sessions/TimeSource;Lkotlin/coroutines/CoroutineContext;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "active", "Ljava/util/HashMap;", "Lorg/cipherkey/core/common/ContactId;", "Lorg/cipherkey/core/sessions/KeySessionManager$Entry;", "Lkotlin/collections/HashMap;", "deriveKey", "", "passphrase", "", "salt", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "([C[BLorg/cipherkey/core/crypto/Argon2Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "activate", "Lorg/cipherkey/core/sessions/SessionKey;", "contactId", "sessionId", "Lorg/cipherkey/core/common/SessionId;", "keyId", "keyBytes", "timeoutMillis", "", "activate-fEq4QiY", "(Ljava/lang/String;Ljava/lang/String;[B[BLorg/cipherkey/core/crypto/Argon2Params;[BJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "activeSession", "activeSession-ovFUVq0", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lock", "", "lock-ovFUVq0", "lockAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlockedContactIds", "", "expiryOf", "expiryOf-ovFUVq0", "wipe", "", "sessionKey", "sweepExpired", "Entry", "sessions"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KeySessionManager {
    private final HashMap<ContactId, Entry> active;
    private final Argon2KeyDerivator derivator;
    private final CoroutineContext deriveContext;
    private final Mutex mutex;
    private final TimeSource timeSource;

    /* compiled from: KeySessionManager.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.core.sessions.KeySessionManager", f = "KeySessionManager.kt", i = {0, 0}, l = {149}, m = "lockAll", n = {"$this$withLock_u24default$iv", "$i$f$withLock"}, s = {"L$0", "I$0"}, v = 1)
    /* renamed from: org.cipherkey.core.sessions.KeySessionManager$lockAll$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KeySessionManager.this.lockAll(this);
        }
    }

    /* compiled from: KeySessionManager.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.core.sessions.KeySessionManager", f = "KeySessionManager.kt", i = {0, 0}, l = {149}, m = "unlockedContactIds", n = {"$this$withLock_u24default$iv", "$i$f$withLock"}, s = {"L$0", "I$0"}, v = 1)
    /* renamed from: org.cipherkey.core.sessions.KeySessionManager$unlockedContactIds$1, reason: invalid class name and case insensitive filesystem */
    static final class C05081 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C05081(Continuation<? super C05081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KeySessionManager.this.unlockedContactIds(this);
        }
    }

    public KeySessionManager() {
        this(null, null, null, 7, null);
    }

    public KeySessionManager(Argon2KeyDerivator derivator, TimeSource timeSource, CoroutineContext deriveContext) {
        Intrinsics.checkNotNullParameter(derivator, "derivator");
        Intrinsics.checkNotNullParameter(timeSource, "timeSource");
        Intrinsics.checkNotNullParameter(deriveContext, "deriveContext");
        this.derivator = derivator;
        this.timeSource = timeSource;
        this.deriveContext = deriveContext;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.active = new HashMap<>();
    }

    public /* synthetic */ KeySessionManager(Argon2KeyDerivator argon2KeyDerivator, TimeSource timeSource, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Argon2KeyDerivator() : argon2KeyDerivator, (i & 2) != 0 ? TimeSource.INSTANCE.getSYSTEM() : timeSource, (i & 4) != 0 ? Dispatchers.getDefault() : coroutineDispatcher);
    }

    /* compiled from: KeySessionManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/sessions/KeySessionManager$Entry;", "", "sessionKey", "Lorg/cipherkey/core/sessions/SessionKey;", "expiresAtMillis", "", "<init>", "(Lorg/cipherkey/core/sessions/SessionKey;J)V", "getSessionKey", "()Lorg/cipherkey/core/sessions/SessionKey;", "getExpiresAtMillis", "()J", "sessions"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Entry {
        private final long expiresAtMillis;
        private final SessionKey sessionKey;

        public Entry(SessionKey sessionKey, long j) {
            Intrinsics.checkNotNullParameter(sessionKey, "sessionKey");
            this.sessionKey = sessionKey;
            this.expiresAtMillis = j;
        }

        public final SessionKey getSessionKey() {
            return this.sessionKey;
        }

        public final long getExpiresAtMillis() {
            return this.expiresAtMillis;
        }
    }

    /* compiled from: KeySessionManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.core.sessions.KeySessionManager$deriveKey$2", f = "KeySessionManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.core.sessions.KeySessionManager$deriveKey$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
        final /* synthetic */ Argon2Params $params;
        final /* synthetic */ char[] $passphrase;
        final /* synthetic */ byte[] $salt;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(char[] cArr, byte[] bArr, Argon2Params argon2Params, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$passphrase = cArr;
            this.$salt = bArr;
            this.$params = argon2Params;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return KeySessionManager.this.new AnonymousClass2(this.$passphrase, this.$salt, this.$params, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Argon2KeyDerivator.derive$default(KeySessionManager.this.derivator, this.$passphrase, this.$salt, this.$params, 0, null, null, 56, null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object deriveKey(char[] cArr, byte[] bArr, Argon2Params argon2Params, Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(this.deriveContext, new AnonymousClass2(cArr, bArr, argon2Params, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* renamed from: activate-fEq4QiY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10653activatefEq4QiY(String str, String str2, byte[] bArr, byte[] bArr2, Argon2Params argon2Params, byte[] bArr3, long j, Continuation<? super SessionKey> continuation) {
        KeySessionManager$activate$1 keySessionManager$activate$1;
        String str3;
        byte[] bArr4;
        byte[] bArr5;
        Argon2Params argon2Params2;
        byte[] bArr6;
        long j2;
        Mutex mutex;
        String str4;
        if (continuation instanceof KeySessionManager$activate$1) {
            keySessionManager$activate$1 = (KeySessionManager$activate$1) continuation;
            if ((keySessionManager$activate$1.label & Integer.MIN_VALUE) != 0) {
                keySessionManager$activate$1.label -= Integer.MIN_VALUE;
            } else {
                keySessionManager$activate$1 = new KeySessionManager$activate$1(this, continuation);
            }
        }
        Object obj = keySessionManager$activate$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = keySessionManager$activate$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Mutex mutex2 = this.mutex;
            keySessionManager$activate$1.L$0 = str;
            str3 = str2;
            keySessionManager$activate$1.L$1 = str3;
            bArr4 = bArr;
            keySessionManager$activate$1.L$2 = bArr4;
            bArr5 = bArr2;
            keySessionManager$activate$1.L$3 = bArr5;
            argon2Params2 = argon2Params;
            keySessionManager$activate$1.L$4 = argon2Params2;
            bArr6 = bArr3;
            keySessionManager$activate$1.L$5 = bArr6;
            keySessionManager$activate$1.L$6 = mutex2;
            j2 = j;
            keySessionManager$activate$1.J$0 = j2;
            keySessionManager$activate$1.I$0 = 0;
            keySessionManager$activate$1.label = 1;
            if (mutex2.lock(null, keySessionManager$activate$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = mutex2;
            str4 = str;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = keySessionManager$activate$1.I$0;
            long j3 = keySessionManager$activate$1.J$0;
            mutex = (Mutex) keySessionManager$activate$1.L$6;
            byte[] bArr7 = (byte[]) keySessionManager$activate$1.L$5;
            Argon2Params argon2Params3 = (Argon2Params) keySessionManager$activate$1.L$4;
            bArr5 = (byte[]) keySessionManager$activate$1.L$3;
            byte[] bArr8 = (byte[]) keySessionManager$activate$1.L$2;
            String str5 = (String) keySessionManager$activate$1.L$1;
            str4 = (String) keySessionManager$activate$1.L$0;
            ResultKt.throwOnFailure(obj);
            bArr6 = bArr7;
            str3 = str5;
            argon2Params2 = argon2Params3;
            bArr4 = bArr8;
            j2 = j3;
        }
        try {
            Entry entryRemove = this.active.remove(ContactId.m10637boximpl(str4));
            if (entryRemove != null) {
                wipe(entryRemove.getSessionKey());
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr4, bArr4.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            byte[] bArrCopyOf2 = Arrays.copyOf(bArr5, bArr5.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "copyOf(...)");
            SessionKey sessionKey = new SessionKey(str3, str4, bArrCopyOf, bArrCopyOf2, argon2Params2, bArr6, null);
            this.active.put(ContactId.m10637boximpl(str4), new Entry(sessionKey, this.timeSource.nowMillis() + j2));
            return sessionKey;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: activeSession-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10654activeSessionovFUVq0(String str, Continuation<? super SessionKey> continuation) {
        KeySessionManager$activeSession$1 keySessionManager$activeSession$1;
        Mutex mutex;
        SessionKey sessionKey;
        if (continuation instanceof KeySessionManager$activeSession$1) {
            keySessionManager$activeSession$1 = (KeySessionManager$activeSession$1) continuation;
            if ((keySessionManager$activeSession$1.label & Integer.MIN_VALUE) != 0) {
                keySessionManager$activeSession$1.label -= Integer.MIN_VALUE;
            } else {
                keySessionManager$activeSession$1 = new KeySessionManager$activeSession$1(this, continuation);
            }
        }
        Object obj = keySessionManager$activeSession$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = keySessionManager$activeSession$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.mutex;
            keySessionManager$activeSession$1.L$0 = str;
            keySessionManager$activeSession$1.L$1 = mutex;
            keySessionManager$activeSession$1.I$0 = 0;
            keySessionManager$activeSession$1.label = 1;
            if (mutex.lock(null, keySessionManager$activeSession$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = keySessionManager$activeSession$1.I$0;
            Mutex mutex2 = (Mutex) keySessionManager$activeSession$1.L$1;
            String str2 = (String) keySessionManager$activeSession$1.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            str = str2;
        }
        try {
            Entry entry = this.active.get(ContactId.m10637boximpl(str));
            if (entry == null) {
                sessionKey = null;
            } else if (entry.getExpiresAtMillis() <= this.timeSource.nowMillis()) {
                this.active.remove(ContactId.m10637boximpl(str));
                wipe(entry.getSessionKey());
                sessionKey = null;
            } else {
                sessionKey = entry.getSessionKey();
            }
            return sessionKey;
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: lock-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10656lockovFUVq0(String str, Continuation<? super Boolean> continuation) {
        KeySessionManager$lock$1 keySessionManager$lock$1;
        Mutex mutex;
        if (continuation instanceof KeySessionManager$lock$1) {
            keySessionManager$lock$1 = (KeySessionManager$lock$1) continuation;
            if ((keySessionManager$lock$1.label & Integer.MIN_VALUE) != 0) {
                keySessionManager$lock$1.label -= Integer.MIN_VALUE;
            } else {
                keySessionManager$lock$1 = new KeySessionManager$lock$1(this, continuation);
            }
        }
        Object obj = keySessionManager$lock$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = keySessionManager$lock$1.label;
        boolean z = false;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.mutex;
            keySessionManager$lock$1.L$0 = str;
            keySessionManager$lock$1.L$1 = mutex;
            keySessionManager$lock$1.I$0 = 0;
            keySessionManager$lock$1.label = 1;
            if (mutex.lock(null, keySessionManager$lock$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = keySessionManager$lock$1.I$0;
            Mutex mutex2 = (Mutex) keySessionManager$lock$1.L$1;
            String str2 = (String) keySessionManager$lock$1.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            str = str2;
        }
        try {
            Entry entryRemove = this.active.remove(ContactId.m10637boximpl(str));
            if (entryRemove != null) {
                wipe(entryRemove.getSessionKey());
                z = true;
            }
            return Boxing.boxBoolean(z);
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object lockAll(Continuation<? super Integer> continuation) {
        AnonymousClass1 anonymousClass1;
        Mutex mutex;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Mutex mutex2 = this.mutex;
            anonymousClass1.L$0 = mutex2;
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            if (mutex2.lock(null, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = mutex2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            mutex = (Mutex) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            int size = this.active.size();
            Collection<Entry> collectionValues = this.active.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
            Iterator<T> it = collectionValues.iterator();
            while (it.hasNext()) {
                wipe(((Entry) it.next()).getSessionKey());
            }
            this.active.clear();
            return Boxing.boxInt(size);
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object unlockedContactIds(Continuation<? super Set<ContactId>> continuation) {
        C05081 c05081;
        Mutex mutex;
        if (continuation instanceof C05081) {
            c05081 = (C05081) continuation;
            if ((c05081.label & Integer.MIN_VALUE) != 0) {
                c05081.label -= Integer.MIN_VALUE;
            } else {
                c05081 = new C05081(continuation);
            }
        }
        Object obj = c05081.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c05081.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Mutex mutex2 = this.mutex;
            c05081.L$0 = mutex2;
            c05081.I$0 = 0;
            c05081.label = 1;
            if (mutex2.lock(null, c05081) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = mutex2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c05081.I$0;
            mutex = (Mutex) c05081.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            sweepExpired();
            Set<ContactId> setKeySet = this.active.keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
            return CollectionsKt.toSet(setKeySet);
        } finally {
            mutex.unlock(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* renamed from: expiryOf-ovFUVq0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object m10655expiryOfovFUVq0(String str, Continuation<? super Long> continuation) {
        KeySessionManager$expiryOf$1 keySessionManager$expiryOf$1;
        Mutex mutex;
        if (continuation instanceof KeySessionManager$expiryOf$1) {
            keySessionManager$expiryOf$1 = (KeySessionManager$expiryOf$1) continuation;
            if ((keySessionManager$expiryOf$1.label & Integer.MIN_VALUE) != 0) {
                keySessionManager$expiryOf$1.label -= Integer.MIN_VALUE;
            } else {
                keySessionManager$expiryOf$1 = new KeySessionManager$expiryOf$1(this, continuation);
            }
        }
        Object obj = keySessionManager$expiryOf$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = keySessionManager$expiryOf$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            mutex = this.mutex;
            keySessionManager$expiryOf$1.L$0 = str;
            keySessionManager$expiryOf$1.L$1 = mutex;
            keySessionManager$expiryOf$1.I$0 = 0;
            keySessionManager$expiryOf$1.label = 1;
            if (mutex.lock(null, keySessionManager$expiryOf$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = keySessionManager$expiryOf$1.I$0;
            Mutex mutex2 = (Mutex) keySessionManager$expiryOf$1.L$1;
            String str2 = (String) keySessionManager$expiryOf$1.L$0;
            ResultKt.throwOnFailure(obj);
            mutex = mutex2;
            str = str2;
        }
        try {
            sweepExpired();
            Entry entry = this.active.get(ContactId.m10637boximpl(str));
            return entry != null ? Boxing.boxLong(entry.getExpiresAtMillis()) : null;
        } finally {
            mutex.unlock(null);
        }
    }

    private final void wipe(SessionKey sessionKey) {
        Arrays.fill(sessionKey.getKeyBytes(), (byte) 0);
        ArraysKt.fill$default(sessionKey.getKeyId(), (byte) 0, 0, 0, 6, (Object) null);
    }

    private final void sweepExpired() {
        long jNowMillis = this.timeSource.nowMillis();
        HashMap<ContactId, Entry> map = this.active;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<ContactId, Entry> entry : map.entrySet()) {
            if (entry.getValue().getExpiresAtMillis() <= jNowMillis) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.keySet().iterator();
        while (it.hasNext()) {
            Entry entryRemove = this.active.remove(ContactId.m10637boximpl(((ContactId) it.next()).m10643unboximpl()));
            if (entryRemove != null) {
                wipe(entryRemove.getSessionKey());
            }
        }
    }
}
