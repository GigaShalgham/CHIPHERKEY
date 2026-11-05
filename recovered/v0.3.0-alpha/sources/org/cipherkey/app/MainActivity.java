package org.cipherkey.app;

import android.os.Bundle;
import android.provider.Settings;
import androidx.activity.compose.ComponentActivityKt;
import androidx.lifecycle.LifecycleOwnerKt;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.cipherkey.app.assistant.AssistantReadiness;
import org.cipherkey.app.assistant.FloatingAssistantService;
import org.cipherkey.data.preferences.AppSettings;
import org.cipherkey.data.preferences.SettingsRepository;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lorg/cipherkey/app/MainActivity;", "Landroidx/fragment/app/FragmentActivity;", "<init>", "()V", "settingsRepository", "Lorg/cipherkey/data/preferences/SettingsRepository;", "getSettingsRepository", "()Lorg/cipherkey/data/preferences/SettingsRepository;", "setSettingsRepository", "(Lorg/cipherkey/data/preferences/SettingsRepository;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "maybeAutoStartAssistant", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes3.dex */
public final class MainActivity extends Hilt_MainActivity {
    public static final int $stable = 8;

    @Inject
    public SettingsRepository settingsRepository;

    public final SettingsRepository getSettingsRepository() {
        SettingsRepository settingsRepository = this.settingsRepository;
        if (settingsRepository != null) {
            return settingsRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settingsRepository");
        return null;
    }

    public final void setSettingsRepository(SettingsRepository settingsRepository) {
        Intrinsics.checkNotNullParameter(settingsRepository, "<set-?>");
        this.settingsRepository = settingsRepository;
    }

    @Override // org.cipherkey.app.Hilt_MainActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentActivityKt.setContent$default(this, null, ComposableSingletons$MainActivityKt.INSTANCE.getLambda$1341940379$app_release(), 1, null);
        maybeAutoStartAssistant();
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.MainActivity$maybeAutoStartAssistant$1", f = "MainActivity.kt", i = {0}, l = {49}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"}, v = 1)
    /* renamed from: org.cipherkey.app.MainActivity$maybeAutoStartAssistant$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = MainActivity.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = FlowKt.first(MainActivity.this.getSettingsRepository().getSettings(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (AssistantReadiness.INSTANCE.shouldAutoStart(((AppSettings) obj).getAssistantEnabled(), Settings.canDrawOverlays(MainActivity.this))) {
                MainActivity mainActivity = MainActivity.this;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    FloatingAssistantService.INSTANCE.start(mainActivity);
                    Result.m8783constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m8783constructorimpl(ResultKt.createFailure(th));
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final void maybeAutoStartAssistant() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(null), 3, null);
    }
}
