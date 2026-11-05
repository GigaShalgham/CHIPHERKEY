package org.cipherkey.app.security;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.app.security.BiometricPromptRequest;

/* compiled from: BiometricPromptHost.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.security.BiometricPromptHostKt$BiometricPromptHost$1$1", f = "BiometricPromptHost.kt", i = {}, l = {28}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class BiometricPromptHostKt$BiometricPromptHost$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super byte[]>, Object> $consumeRememberKey;
    final /* synthetic */ BiometricPromptHelper $helper;
    final /* synthetic */ Function1<Boolean, Unit> $onRememberDone;
    final /* synthetic */ Function1<byte[], Unit> $onUnlockResult;
    final /* synthetic */ BiometricPromptRequest $prompt;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    BiometricPromptHostKt$BiometricPromptHost$1$1(BiometricPromptRequest biometricPromptRequest, BiometricPromptHelper biometricPromptHelper, Function1<? super Continuation<? super byte[]>, ? extends Object> function1, Function1<? super Boolean, Unit> function12, Function1<? super byte[], Unit> function13, Continuation<? super BiometricPromptHostKt$BiometricPromptHost$1$1> continuation) {
        super(2, continuation);
        this.$prompt = biometricPromptRequest;
        this.$helper = biometricPromptHelper;
        this.$consumeRememberKey = function1;
        this.$onRememberDone = function12;
        this.$onUnlockResult = function13;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BiometricPromptHostKt$BiometricPromptHost$1$1(this.$prompt, this.$helper, this.$consumeRememberKey, this.$onRememberDone, this.$onUnlockResult, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BiometricPromptHostKt$BiometricPromptHost$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BiometricPromptRequest biometricPromptRequest = this.$prompt;
            if (biometricPromptRequest instanceof BiometricPromptRequest.Unlock) {
                BiometricPromptHelper biometricPromptHelper = this.$helper;
                String contactKey = ((BiometricPromptRequest.Unlock) biometricPromptRequest).getContactKey();
                final Function1<byte[], Unit> function1 = this.$onUnlockResult;
                biometricPromptHelper.unlockKey(contactKey, new Function1() { // from class: org.cipherkey.app.security.BiometricPromptHostKt$BiometricPromptHost$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return BiometricPromptHostKt$BiometricPromptHost$1$1.invokeSuspend$lambda$0(function1, (byte[]) obj2);
                    }
                });
                return Unit.INSTANCE;
            }
            if (!(biometricPromptRequest instanceof BiometricPromptRequest.Remember)) {
                throw new NoWhenBranchMatchedException();
            }
            Function1<Continuation<? super byte[]>, Object> function12 = this.$consumeRememberKey;
            this.label = 1;
            obj = function12.invoke(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        byte[] bArr = (byte[]) obj;
        if (bArr == null) {
            this.$onRememberDone.invoke(Boxing.boxBoolean(false));
        } else {
            BiometricPromptHelper biometricPromptHelper2 = this.$helper;
            String contactKey2 = ((BiometricPromptRequest.Remember) this.$prompt).getContactKey();
            final Function1<Boolean, Unit> function13 = this.$onRememberDone;
            biometricPromptHelper2.rememberKey(contactKey2, bArr, new Function1() { // from class: org.cipherkey.app.security.BiometricPromptHostKt$BiometricPromptHost$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return BiometricPromptHostKt$BiometricPromptHost$1$1.invokeSuspend$lambda$1(function13, ((Boolean) obj2).booleanValue());
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(Function1 function1, byte[] bArr) {
        function1.invoke(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(Function1 function1, boolean z) {
        function1.invoke(Boolean.valueOf(z));
        return Unit.INSTANCE;
    }
}
