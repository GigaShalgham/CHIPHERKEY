package org.cipherkey.app;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: ToolsViewModel.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "org.cipherkey.app.ToolsViewModel", f = "ToolsViewModel.kt", i = {0}, l = {409}, m = "biometricContactKey-ovFUVq0", n = {"$v$c$org-cipherkey-core-common-ContactId$-contactId$0"}, s = {"L$0"}, v = 1)
/* loaded from: classes3.dex */
final class ToolsViewModel$biometricContactKey$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ToolsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ToolsViewModel$biometricContactKey$1(ToolsViewModel toolsViewModel, Continuation<? super ToolsViewModel$biometricContactKey$1> continuation) {
        super(continuation);
        this.this$0 = toolsViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m10468biometricContactKeyovFUVq0(null, this);
    }
}
