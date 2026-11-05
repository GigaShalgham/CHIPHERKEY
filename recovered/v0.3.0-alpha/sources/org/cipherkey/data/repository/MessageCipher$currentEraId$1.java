package org.cipherkey.data.repository;

import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.cipherkey.core.common.SessionId;

/* compiled from: MessageCipher.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.data.repository.MessageCipher", f = "MessageCipher.kt", i = {0}, l = {InputDeviceCompat.SOURCE_KEYBOARD}, m = "currentEraId-wb5GA7Y", n = {"$v$c$org-cipherkey-core-common-ContactId$-contactId$0"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class MessageCipher$currentEraId$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MessageCipher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MessageCipher$currentEraId$1(MessageCipher messageCipher, Continuation<? super MessageCipher$currentEraId$1> continuation) {
        super(continuation);
        this.this$0 = messageCipher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM10685currentEraIdwb5GA7Y = this.this$0.m10685currentEraIdwb5GA7Y(null, this);
        if (objM10685currentEraIdwb5GA7Y == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return objM10685currentEraIdwb5GA7Y;
        }
        String str = (String) objM10685currentEraIdwb5GA7Y;
        if (str != null) {
            return SessionId.m10644boximpl(str);
        }
        return null;
    }
}
