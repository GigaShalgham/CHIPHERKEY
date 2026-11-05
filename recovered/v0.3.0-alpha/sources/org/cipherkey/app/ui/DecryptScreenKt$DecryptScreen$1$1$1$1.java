package org.cipherkey.app.ui;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.cipherkey.app.ToolsViewModel;

/* compiled from: DecryptScreen.kt */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ui.DecryptScreenKt$DecryptScreen$1$1$1$1", f = "DecryptScreen.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
final class DecryptScreenKt$DecryptScreen$1$1$1$1 extends SuspendLambda implements Function1<Continuation<? super byte[]>, Object> {
    final /* synthetic */ ToolsViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DecryptScreenKt$DecryptScreen$1$1$1$1(ToolsViewModel toolsViewModel, Continuation<? super DecryptScreenKt$DecryptScreen$1$1$1$1> continuation) {
        super(1, continuation);
        this.$viewModel = toolsViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DecryptScreenKt$DecryptScreen$1$1$1$1(this.$viewModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super byte[]> continuation) {
        return ((DecryptScreenKt$DecryptScreen$1$1$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        Object objConsumeRememberKey = this.$viewModel.consumeRememberKey(this);
        return objConsumeRememberKey == coroutine_suspended ? coroutine_suspended : objConsumeRememberKey;
    }
}
