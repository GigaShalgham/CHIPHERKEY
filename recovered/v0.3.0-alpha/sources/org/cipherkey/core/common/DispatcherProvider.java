package org.cipherkey.core.common;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: DispatcherProvider.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/common/DispatcherProvider;", "", "io", "Lkotlin/coroutines/CoroutineContext;", "default", "main", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "getIo", "()Lkotlin/coroutines/CoroutineContext;", "getDefault", "getMain", "common"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DispatcherProvider {
    private final CoroutineContext default;
    private final CoroutineContext io;
    private final CoroutineContext main;

    public DispatcherProvider() {
        this(null, null, null, 7, null);
    }

    public DispatcherProvider(CoroutineContext io, CoroutineContext coroutineContext, CoroutineContext main) {
        Intrinsics.checkNotNullParameter(io, "io");
        Intrinsics.checkNotNullParameter(coroutineContext, "default");
        Intrinsics.checkNotNullParameter(main, "main");
        this.io = io;
        this.default = coroutineContext;
        this.main = main;
    }

    public /* synthetic */ DispatcherProvider(CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, MainCoroutineDispatcher mainCoroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dispatchers.getIO() : coroutineDispatcher, (i & 2) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2, (i & 4) != 0 ? Dispatchers.getMain() : mainCoroutineDispatcher);
    }

    public final CoroutineContext getIo() {
        return this.io;
    }

    public final CoroutineContext getDefault() {
        return this.default;
    }

    public final CoroutineContext getMain() {
        return this.main;
    }
}
