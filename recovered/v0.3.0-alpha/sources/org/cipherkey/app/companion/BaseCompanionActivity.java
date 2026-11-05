package org.cipherkey.app.companion;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import org.cipherkey.app.companion.CompanionUiState;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.app.security.BiometricPromptHelper;
import org.cipherkey.app.security.BiometricPromptHostKt;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.app.ui.theme.ThemeKt;

/* compiled from: BaseCompanionActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u0013H\u0016J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0004J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH$J\b\u0010!\u001a\u00020\u0016H\u0004J\u001a\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u0013H\u0004R\u001b\u0010\u0004\u001a\u00020\u00058DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&²\u0006\n\u0010'\u001a\u00020(X\u008a\u0084\u0002"}, d2 = {"Lorg/cipherkey/app/companion/BaseCompanionActivity;", "Landroidx/fragment/app/FragmentActivity;", "<init>", "()V", "viewModel", "Lorg/cipherkey/app/companion/CompanionViewModel;", "getViewModel", "()Lorg/cipherkey/app/companion/CompanionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "biometrics", "Lorg/cipherkey/app/security/BiometricKeyStore;", "getBiometrics", "()Lorg/cipherkey/app/security/BiometricKeyStore;", "setBiometrics", "(Lorg/cipherkey/app/security/BiometricKeyStore;)V", "promptHelper", "Lorg/cipherkey/app/security/BiometricPromptHelper;", "pendingClipboardDispatch", "", "clipboardDispatched", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onWindowFocusChanged", "hasFocus", "retryClipboardDispatch", "isClipboardLaunch", "dispatchIntent", "finishWithResult", "showNotice", "notice", "Lorg/cipherkey/app/companion/UiNotice;", "retryable", "app_release", "state", "Lorg/cipherkey/app/companion/CompanionUiState;"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BaseCompanionActivity extends FragmentActivity {
    public static final int $stable = 8;

    @Inject
    public BiometricKeyStore biometrics;
    private boolean clipboardDispatched;
    private boolean pendingClipboardDispatch;
    private BiometricPromptHelper promptHelper;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    protected abstract void dispatchIntent(Intent intent);

    public BaseCompanionActivity() {
        final BaseCompanionActivity baseCompanionActivity = this;
        final Function0 function0 = null;
        this.viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CompanionViewModel.class), new Function0<ViewModelStore>() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$special$$inlined$viewModels$default$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return baseCompanionActivity.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$special$$inlined$viewModels$default$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return baseCompanionActivity.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = function0;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? baseCompanionActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    protected final CompanionViewModel getViewModel() {
        return (CompanionViewModel) this.viewModel.getValue();
    }

    public final BiometricKeyStore getBiometrics() {
        BiometricKeyStore biometricKeyStore = this.biometrics;
        if (biometricKeyStore != null) {
            return biometricKeyStore;
        }
        Intrinsics.throwUninitializedPropertyAccessException("biometrics");
        return null;
    }

    public final void setBiometrics(BiometricKeyStore biometricKeyStore) {
        Intrinsics.checkNotNullParameter(biometricKeyStore, "<set-?>");
        this.biometrics = biometricKeyStore;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.promptHelper = new BiometricPromptHelper(this, getBiometrics());
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (isClipboardLaunch(intent)) {
                this.pendingClipboardDispatch = true;
            } else {
                Intent intent2 = getIntent();
                Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
                dispatchIntent(intent2);
            }
        }
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-1349761145, true, new Function2() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return BaseCompanionActivity.onCreate$lambda$0(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0(final BaseCompanionActivity baseCompanionActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C74@3142L1512,74@3127L1527:BaseCompanionActivity.kt#yo7vyd");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1349761145, i, -1, "org.cipherkey.app.companion.BaseCompanionActivity.onCreate.<anonymous> (BaseCompanionActivity.kt:74)");
            }
            ThemeKt.CipherKeyTheme(false, ComposableLambdaKt.rememberComposableLambda(1723536708, true, new Function2() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BaseCompanionActivity.onCreate$lambda$0$0(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0(final BaseCompanionActivity baseCompanionActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C75@3189L29,91@3980L24,92@4038L21,93@4099L27,94@4166L27,95@4224L21,96@4278L18,97@4328L12,103@4588L33,89@3889L751:BaseCompanionActivity.kt#yo7vyd");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1723536708, i, -1, "org.cipherkey.app.companion.BaseCompanionActivity.onCreate.<anonymous>.<anonymous> (BaseCompanionActivity.kt:75)");
            }
            Composer composer2 = composer;
            State stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(baseCompanionActivity.getViewModel().getState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer2, 0, 7);
            BiometricPromptRequest biometricPrompt = onCreate$lambda$0$0$0(stateCollectAsStateWithLifecycle).getBiometricPrompt();
            Function0 function0 = null;
            if (biometricPrompt == null) {
                composer2.startReplaceGroup(-451679348);
            } else {
                composer2.startReplaceGroup(-451679347);
                ComposerKt.sourceInformation(composer2, "*83@3637L34,84@3714L41,85@3798L32,80@3469L384");
                BiometricPromptHelper biometricPromptHelper = baseCompanionActivity.promptHelper;
                if (biometricPromptHelper != null) {
                    ComposerKt.sourceInformationMarkerStart(composer2, -189606238, "CC(remember):BaseCompanionActivity.kt#9igjgp");
                    boolean zChangedInstance = composer2.changedInstance(baseCompanionActivity);
                    BaseCompanionActivity$onCreate$1$1$1$1$1 baseCompanionActivity$onCreate$1$1$1$1$1RememberedValue = composer2.rememberedValue();
                    if (zChangedInstance || baseCompanionActivity$onCreate$1$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        baseCompanionActivity$onCreate$1$1$1$1$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$1$1$1(baseCompanionActivity, null);
                        composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$1$1$1RememberedValue);
                    }
                    Function1 function1 = (Function1) baseCompanionActivity$onCreate$1$1$1$1$1RememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerStart(composer2, -189603767, "CC(remember):BaseCompanionActivity.kt#9igjgp");
                    boolean zChangedInstance2 = composer2.changedInstance(baseCompanionActivity);
                    Object objRememberedValue = composer2.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BaseCompanionActivity.onCreate$lambda$0$0$1$1$0(this.f$0, (byte[]) obj);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function12 = (Function1) objRememberedValue;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerStart(composer2, -189601088, "CC(remember):BaseCompanionActivity.kt#9igjgp");
                    boolean zChangedInstance3 = composer2.changedInstance(baseCompanionActivity);
                    Object objRememberedValue2 = composer2.rememberedValue();
                    if (zChangedInstance3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return BaseCompanionActivity.onCreate$lambda$0$0$1$2$0(this.f$0, ((Boolean) obj).booleanValue());
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    BiometricPromptHostKt.BiometricPromptHost(biometricPrompt, biometricPromptHelper, function1, function12, (Function1) objRememberedValue2, composer2, 0);
                    composer2 = composer2;
                }
            }
            composer2.endReplaceGroup();
            CompanionUiState companionUiStateOnCreate$lambda$0$0$0 = onCreate$lambda$0$0$0(stateCollectAsStateWithLifecycle);
            CompanionViewModel viewModel = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538573028, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance4 = composer2.changedInstance(viewModel);
            BaseCompanionActivity$onCreate$1$1$2$1 baseCompanionActivity$onCreate$1$1$2$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance4 || baseCompanionActivity$onCreate$1$1$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$2$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$2$1(viewModel);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function1 function13 = (Function1) ((KFunction) baseCompanionActivity$onCreate$1$1$2$1RememberedValue);
            CompanionViewModel viewModel2 = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538571175, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance5 = composer2.changedInstance(viewModel2);
            BaseCompanionActivity$onCreate$1$1$3$1 baseCompanionActivity$onCreate$1$1$3$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance5 || baseCompanionActivity$onCreate$1$1$3$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$3$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$3$1(viewModel2);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$3$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function0 function02 = (Function0) ((KFunction) baseCompanionActivity$onCreate$1$1$3$1RememberedValue);
            CompanionViewModel viewModel3 = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538569217, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance6 = composer2.changedInstance(viewModel3);
            BaseCompanionActivity$onCreate$1$1$4$1 baseCompanionActivity$onCreate$1$1$4$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance6 || baseCompanionActivity$onCreate$1$1$4$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$4$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$4$1(viewModel3);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$4$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function1 function14 = (Function1) ((KFunction) baseCompanionActivity$onCreate$1$1$4$1RememberedValue);
            CompanionViewModel viewModel4 = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538567073, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance7 = composer2.changedInstance(viewModel4);
            BaseCompanionActivity$onCreate$1$1$5$1 baseCompanionActivity$onCreate$1$1$5$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance7 || baseCompanionActivity$onCreate$1$1$5$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$5$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$5$1(viewModel4);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$5$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function1 function15 = (Function1) ((KFunction) baseCompanionActivity$onCreate$1$1$5$1RememberedValue);
            CompanionViewModel viewModel5 = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538565223, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance8 = composer2.changedInstance(viewModel5);
            BaseCompanionActivity$onCreate$1$1$6$1 baseCompanionActivity$onCreate$1$1$6$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance8 || baseCompanionActivity$onCreate$1$1$6$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$6$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$6$1(viewModel5);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$6$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function0 function03 = (Function0) ((KFunction) baseCompanionActivity$onCreate$1$1$6$1RememberedValue);
            ComposerKt.sourceInformationMarkerStart(composer2, -1538563498, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance9 = composer2.changedInstance(baseCompanionActivity);
            BaseCompanionActivity$onCreate$1$1$7$1 baseCompanionActivity$onCreate$1$1$7$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance9 || baseCompanionActivity$onCreate$1$1$7$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$7$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$7$1(baseCompanionActivity);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$7$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Function0 function04 = (Function0) ((KFunction) baseCompanionActivity$onCreate$1$1$7$1RememberedValue);
            ComposerKt.sourceInformationMarkerStart(composer2, -1538561904, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance10 = composer2.changedInstance(baseCompanionActivity);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (zChangedInstance10 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BaseCompanionActivity.onCreate$lambda$0$0$8$0(this.f$0);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            Function0 function05 = (Function0) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Intent intent = baseCompanionActivity.getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (baseCompanionActivity.isClipboardLaunch(intent)) {
                composer2.startReplaceGroup(-450693454);
                ComposerKt.sourceInformation(composer2, "99@4438L28");
                ComposerKt.sourceInformationMarkerStart(composer2, -1538558368, "CC(remember):BaseCompanionActivity.kt#9igjgp");
                boolean zChangedInstance11 = composer2.changedInstance(baseCompanionActivity);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (zChangedInstance11 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.companion.BaseCompanionActivity$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BaseCompanionActivity.onCreate$lambda$0$0$9$0(this.f$0);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                function0 = (Function0) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-450612854);
                composer2.endReplaceGroup();
            }
            CompanionViewModel viewModel6 = baseCompanionActivity.getViewModel();
            ComposerKt.sourceInformationMarkerStart(composer2, -1538553563, "CC(remember):BaseCompanionActivity.kt#9igjgp");
            boolean zChangedInstance12 = composer2.changedInstance(viewModel6);
            BaseCompanionActivity$onCreate$1$1$10$1 baseCompanionActivity$onCreate$1$1$10$1RememberedValue = composer2.rememberedValue();
            if (zChangedInstance12 || baseCompanionActivity$onCreate$1$1$10$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                baseCompanionActivity$onCreate$1$1$10$1RememberedValue = new BaseCompanionActivity$onCreate$1$1$10$1(viewModel6);
                composer2.updateRememberedValue(baseCompanionActivity$onCreate$1$1$10$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CompanionUiKt.CompanionSheet(companionUiStateOnCreate$lambda$0$0$0, function13, function02, function14, function15, function03, function04, function05, function0, (Function0) ((KFunction) baseCompanionActivity$onCreate$1$1$10$1RememberedValue), composer2, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0$1$1$0(BaseCompanionActivity baseCompanionActivity, byte[] bArr) {
        baseCompanionActivity.getViewModel().onBiometricUnlockResult(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0$1$2$0(BaseCompanionActivity baseCompanionActivity, boolean z) {
        baseCompanionActivity.getViewModel().onRememberDone(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0$8$0(BaseCompanionActivity baseCompanionActivity) {
        baseCompanionActivity.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0$9$0(BaseCompanionActivity baseCompanionActivity) {
        baseCompanionActivity.retryClipboardDispatch();
        return Unit.INSTANCE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        if (isClipboardLaunch(intent)) {
            return;
        }
        dispatchIntent(intent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (!this.pendingClipboardDispatch) {
                if (getViewModel().getState().getValue().getPhase() != CompanionUiState.Phase.Idle) {
                    return;
                }
                Intent intent = getIntent();
                Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
                if (!isClipboardLaunch(intent)) {
                    return;
                }
            }
            if (this.clipboardDispatched) {
                return;
            }
            this.clipboardDispatched = true;
            Intent intent2 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent2, "getIntent(...)");
            dispatchIntent(intent2);
        }
    }

    private final void retryClipboardDispatch() {
        if (hasWindowFocus()) {
            this.clipboardDispatched = true;
            getViewModel().retryClipboard();
        }
    }

    protected final boolean isClipboardLaunch(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return intent.getBooleanExtra(BaseCompanionActivityKt.EXTRA_FROM_CLIPBOARD, false);
    }

    protected final void finishWithResult() {
        String strResultTextOrNull = ProcessTextContract.INSTANCE.resultTextOrNull(getViewModel().getState().getValue().getResultText());
        if (strResultTextOrNull != null) {
            setResult(-1, new Intent().putExtra("android.intent.extra.PROCESS_TEXT", strResultTextOrNull));
        }
        finish();
    }

    public static /* synthetic */ void showNotice$default(BaseCompanionActivity baseCompanionActivity, UiNotice uiNotice, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showNotice");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        baseCompanionActivity.showNotice(uiNotice, z);
    }

    protected final void showNotice(UiNotice notice, boolean retryable) {
        Intrinsics.checkNotNullParameter(notice, "notice");
        getViewModel().showNotice(notice, retryable);
    }

    private static final CompanionUiState onCreate$lambda$0$0$0(State<CompanionUiState> state) {
        return state.getValue();
    }
}
