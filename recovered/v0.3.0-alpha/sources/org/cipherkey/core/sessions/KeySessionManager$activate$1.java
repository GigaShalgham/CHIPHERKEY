package org.cipherkey.core.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: KeySessionManager.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.core.sessions.KeySessionManager", f = "KeySessionManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {149}, m = "activate-fEq4QiY", n = {"$v$c$org-cipherkey-core-common-ContactId$-contactId$0", "$v$c$org-cipherkey-core-common-SessionId$-sessionId$0", "keyId", "salt", "params", "keyBytes", "$this$withLock_u24default$iv", "timeoutMillis", "$i$f$withLock"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "I$0"}, v = 1)
/* loaded from: classes3.dex */
final class KeySessionManager$activate$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ KeySessionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KeySessionManager$activate$1(KeySessionManager keySessionManager, Continuation<? super KeySessionManager$activate$1> continuation) {
        super(continuation);
        this.this$0 = keySessionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m10653activatefEq4QiY(null, null, null, null, null, null, 0L, this);
    }
}
