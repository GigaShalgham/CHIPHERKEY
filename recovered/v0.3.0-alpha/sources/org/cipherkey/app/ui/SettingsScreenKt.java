package org.cipherkey.app.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.RadioButtonKt;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.NotificationCompat;
import androidx.hilt.navigation.compose.HiltViewModelKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.app.R;
import org.cipherkey.app.SettingsEvent;
import org.cipherkey.app.SettingsViewModel;
import org.cipherkey.data.preferences.AppSettings;
import org.cipherkey.security.policies.Argon2Profile;
import org.cipherkey.security.policies.KeyPolicy;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u0084\u0002²\u0006\n\u0010\r\u001a\u00020\u000eX\u008a\u0084\u0002²\u0006\n\u0010\u000f\u001a\u00020\u000eX\u008a\u0084\u0002²\u0006\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u008a\u0084\u0002"}, d2 = {"timeoutLabel", "", "millis", "", "(JLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "SettingsScreen", "", "viewModel", "Lorg/cipherkey/app/SettingsViewModel;", "(Lorg/cipherkey/app/SettingsViewModel;Landroidx/compose/runtime/Composer;II)V", "app_release", "settings", "Lorg/cipherkey/data/preferences/AppSettings;", "biometricAvailable", "", "overlayGranted", NotificationCompat.CATEGORY_EVENT, "Lorg/cipherkey/app/SettingsEvent;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$8(SettingsViewModel settingsViewModel, int i, int i2, Composer composer, int i3) {
        SettingsScreen(settingsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final String timeoutLabel(long j, Composer composer, int i) {
        String strStringResource;
        ComposerKt.sourceInformationMarkerStart(composer, 774608456, "C(timeoutLabel)N(millis):SettingsScreen.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(774608456, i, -1, "org.cipherkey.app.ui.timeoutLabel (SettingsScreen.kt:48)");
        }
        int i2 = (int) (j / KeyPolicy.MIN_SESSION_TIMEOUT_MILLIS);
        if (i2 < 60) {
            composer.startReplaceGroup(-498652646);
            ComposerKt.sourceInformation(composer, "51@2166L48");
            strStringResource = StringResources_androidKt.stringResource(R.string.option_minutes, new Object[]{Integer.valueOf(i2)}, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-498583113);
            ComposerKt.sourceInformation(composer, "53@2236L51");
            strStringResource = StringResources_androidKt.stringResource(R.string.option_hours, new Object[]{Integer.valueOf(i2 / 60)}, composer, 0);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return strStringResource;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0342  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void SettingsScreen(SettingsViewModel settingsViewModel, Composer composer, final int i, final int i2) {
        final SettingsViewModel settingsViewModel2;
        int i3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        final SettingsViewModel settingsViewModel3;
        State stateCollectAsStateWithLifecycle;
        final Context context;
        final LifecycleOwner lifecycleOwner;
        boolean zChangedInstance;
        Object objRememberedValue;
        Object objRememberedValue2;
        boolean zChanged;
        SettingsScreenKt$SettingsScreen$2$1 settingsScreenKt$SettingsScreen$2$1RememberedValue;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1959128616);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SettingsScreen)N(viewModel)61@2424L29,62@2513L29,63@2594L29,64@2658L29,65@2719L7,69@2917L7,70@2987L276,70@2954L309,82@3509L54,80@3415L148,85@3681L401,85@3659L423,102@4179L21,99@4088L7657:SettingsScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                settingsViewModel2 = settingsViewModel;
                if (composerStartRestartGroup.changedInstance(settingsViewModel2)) {
                    i4 = 4;
                }
                i3 = i4 | i;
            } else {
                settingsViewModel2 = settingsViewModel;
            }
            i4 = 2;
            i3 = i4 | i;
        } else {
            settingsViewModel2 = settingsViewModel;
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "59@2364L15");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    composerStartRestartGroup.startReplaceableGroup(1890788296);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "CC(hiltViewModel)P(1)*45@1969L7,50@2112L47,51@2171L54:HiltViewModel.kt#9mcars");
                    ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, LocalViewModelStoreOwner.$stable);
                    if (current == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    ViewModelProvider.Factory factoryCreateHiltViewModelFactory = HiltViewModelKt.createHiltViewModelFactory(current, composerStartRestartGroup, 0);
                    composerStartRestartGroup.startReplaceableGroup(1729797275);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "CC(viewModel)P(3,2,1)*80@3834L7,90@4209L68:ViewModel.kt#3tja67");
                    if (current instanceof HasDefaultViewModelProviderFactory) {
                        defaultViewModelCreationExtras = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                    } else {
                        defaultViewModelCreationExtras = CreationExtras.Empty.INSTANCE;
                    }
                    ViewModel viewModel = ViewModelKt.viewModel((Class<ViewModel>) SettingsViewModel.class, current, (String) null, factoryCreateHiltViewModelFactory, defaultViewModelCreationExtras, composerStartRestartGroup, 36936, 0);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceableGroup();
                    composerStartRestartGroup.endReplaceableGroup();
                    settingsViewModel2 = (SettingsViewModel) viewModel;
                    i3 &= -15;
                }
                settingsViewModel3 = settingsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1959128616, i3, -1, "org.cipherkey.app.ui.SettingsScreen (SettingsScreen.kt:60)");
                }
                final State stateCollectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getSettings(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getBiometricAvailable(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle4 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getOverlayGranted(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getEvents(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                context = (Context) objConsume;
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = composerStartRestartGroup.consume(localLifecycleOwner);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                lifecycleOwner = (LifecycleOwner) objConsume2;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526946300, "CC(remember):SettingsScreen.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(settingsViewModel3) | composerStartRestartGroup.changedInstance(lifecycleOwner);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SettingsScreenKt.SettingsScreen$lambda$4$0(lifecycleOwner, settingsViewModel3, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, 0);
                ActivityResultContracts.RequestPermission requestPermission = new ActivityResultContracts.RequestPermission();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526962782, "CC(remember):SettingsScreen.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SettingsScreenKt.SettingsScreen$lambda$5$0(((Boolean) obj).booleanValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) objRememberedValue2, composerStartRestartGroup, 48);
                SettingsEvent settingsEventSettingsScreen$lambda$3 = SettingsScreen$lambda$3(stateCollectAsStateWithLifecycle);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526968633, "CC(remember):SettingsScreen.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(stateCollectAsStateWithLifecycle) | composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(settingsViewModel3);
                settingsScreenKt$SettingsScreen$2$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged || settingsScreenKt$SettingsScreen$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    settingsScreenKt$SettingsScreen$2$1RememberedValue = new SettingsScreenKt$SettingsScreen$2$1(settingsViewModel3, stateCollectAsStateWithLifecycle, context, null);
                    composerStartRestartGroup.updateRememberedValue(settingsScreenKt$SettingsScreen$2$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(settingsEventSettingsScreen$lambda$3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) settingsScreenKt$SettingsScreen$2$1RememberedValue, composerStartRestartGroup, 0);
                Modifier modifierM927paddingVpY3zN4$default = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(16), 0.0f, 2, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM927paddingVpY3zN4$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (!composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composerStartRestartGroup);
                Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                final ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -103766807, "C106@4340L39,106@4319L61,110@4501L48,112@4590L2927,109@4468L3049,183@7639L49,185@7726L1835,182@7606L1955,230@9677L43,232@9763L1343,229@9644L1462,268@11215L45,267@11182L506,280@11698L41:SettingsScreen.kt#r4glq3");
                Composer composer2 = composerStartRestartGroup;
                ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.settings_title, composerStartRestartGroup, 0), null, null, composer2, 0, 6);
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_unlock_section, composer2, 0), IconsKt.getIcShield(), null, ComposableLambdaKt.rememberComposableLambda(-1609235419, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$0(settingsViewModel3, stateCollectAsStateWithLifecycle3, stateCollectAsStateWithLifecycle2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), composer2, 3120, 4);
                composerStartRestartGroup = composer2;
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_assistant_title, composer2, 0), IconsKt.getIcKey(), null, ComposableLambdaKt.rememberComposableLambda(-155881060, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$1(columnScopeInstance, context, managedActivityResultLauncherRememberLauncherForActivityResult, settingsViewModel3, stateCollectAsStateWithLifecycle2, stateCollectAsStateWithLifecycle4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), composerStartRestartGroup, 3120, 4);
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_kdf_title, composerStartRestartGroup, 0), IconsKt.getIcSettings(), null, ComposableLambdaKt.rememberComposableLambda(-2090087459, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$2(settingsViewModel3, stateCollectAsStateWithLifecycle2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 3120, 4);
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_about_title, composerStartRestartGroup, 0), IconsKt.getIcInfo(), null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$270673438$app_release(), composerStartRestartGroup, 3120, 4);
                SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(12)), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                settingsViewModel2 = settingsViewModel3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                settingsViewModel3 = settingsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final State stateCollectAsStateWithLifecycle22 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getSettings(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle32 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getBiometricAvailable(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle42 = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getOverlayGranted(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(settingsViewModel3.getEvents(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localContext2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                context = (Context) objConsume3;
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner2 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume22 = composerStartRestartGroup.consume(localLifecycleOwner2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                lifecycleOwner = (LifecycleOwner) objConsume22;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526946300, "CC(remember):SettingsScreen.kt#9igjgp");
                zChangedInstance = composerStartRestartGroup.changedInstance(settingsViewModel3) | composerStartRestartGroup.changedInstance(lifecycleOwner);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SettingsScreenKt.SettingsScreen$lambda$4$0(lifecycleOwner, settingsViewModel3, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, 0);
                    ActivityResultContracts.RequestPermission requestPermission2 = new ActivityResultContracts.RequestPermission();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526962782, "CC(remember):SettingsScreen.kt#9igjgp");
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission2, (Function1) objRememberedValue2, composerStartRestartGroup, 48);
                    SettingsEvent settingsEventSettingsScreen$lambda$32 = SettingsScreen$lambda$3(stateCollectAsStateWithLifecycle);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1526968633, "CC(remember):SettingsScreen.kt#9igjgp");
                    zChanged = composerStartRestartGroup.changed(stateCollectAsStateWithLifecycle) | composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(settingsViewModel3);
                    settingsScreenKt$SettingsScreen$2$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        settingsScreenKt$SettingsScreen$2$1RememberedValue = new SettingsScreenKt$SettingsScreen$2$1(settingsViewModel3, stateCollectAsStateWithLifecycle, context, null);
                        composerStartRestartGroup.updateRememberedValue(settingsScreenKt$SettingsScreen$2$1RememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        EffectsKt.LaunchedEffect(settingsEventSettingsScreen$lambda$32, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) settingsScreenKt$SettingsScreen$2$1RememberedValue, composerStartRestartGroup, 0);
                        Modifier modifierM927paddingVpY3zN4$default2 = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(16), 0.0f, 2, null);
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM927paddingVpY3zN4$default2);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (!composerStartRestartGroup.getInserting()) {
                        }
                        Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composerStartRestartGroup);
                        Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        final ColumnScope columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -103766807, "C106@4340L39,106@4319L61,110@4501L48,112@4590L2927,109@4468L3049,183@7639L49,185@7726L1835,182@7606L1955,230@9677L43,232@9763L1343,229@9644L1462,268@11215L45,267@11182L506,280@11698L41:SettingsScreen.kt#r4glq3");
                        Composer composer22 = composerStartRestartGroup;
                        ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.settings_title, composerStartRestartGroup, 0), null, null, composer22, 0, 6);
                        ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_unlock_section, composer22, 0), IconsKt.getIcShield(), null, ComposableLambdaKt.rememberComposableLambda(-1609235419, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SettingsScreenKt.SettingsScreen$lambda$7$0(settingsViewModel3, stateCollectAsStateWithLifecycle32, stateCollectAsStateWithLifecycle22, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composer22, 54), composer22, 3120, 4);
                        composerStartRestartGroup = composer22;
                        ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_assistant_title, composer22, 0), IconsKt.getIcKey(), null, ComposableLambdaKt.rememberComposableLambda(-155881060, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SettingsScreenKt.SettingsScreen$lambda$7$1(columnScopeInstance2, context, managedActivityResultLauncherRememberLauncherForActivityResult2, settingsViewModel3, stateCollectAsStateWithLifecycle22, stateCollectAsStateWithLifecycle42, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composer22, 54), composerStartRestartGroup, 3120, 4);
                        ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_kdf_title, composerStartRestartGroup, 0), IconsKt.getIcSettings(), null, ComposableLambdaKt.rememberComposableLambda(-2090087459, true, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return SettingsScreenKt.SettingsScreen$lambda$7$2(settingsViewModel3, stateCollectAsStateWithLifecycle22, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, 3120, 4);
                        ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.settings_about_title, composerStartRestartGroup, 0), IconsKt.getIcInfo(), null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$270673438$app_release(), composerStartRestartGroup, 3120, 4);
                        SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(12)), composerStartRestartGroup, 6);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        settingsViewModel2 = settingsViewModel3;
                    }
                }
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$8(settingsViewModel2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult SettingsScreen$lambda$4$0(final LifecycleOwner lifecycleOwner, final SettingsViewModel settingsViewModel, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                SettingsScreenKt.SettingsScreen$lambda$4$0$0(settingsViewModel, lifecycleOwner2, event);
            }
        };
        lifecycleOwner.getLifecycle().addObserver(lifecycleEventObserver);
        return new DisposableEffectResult() { // from class: org.cipherkey.app.ui.SettingsScreenKt$SettingsScreen$lambda$4$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                lifecycleOwner.getLifecycle().removeObserver(lifecycleEventObserver);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingsScreen$lambda$4$0$0(SettingsViewModel settingsViewModel, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_RESUME) {
            settingsViewModel.refresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$5$0(boolean z) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0(final SettingsViewModel settingsViewModel, State state, State state2, Composer composer, int i) {
        String strStringResource;
        ComposerKt.sourceInformation(composer, "C114@4638L1127,142@5839L47,143@5926L10,141@5817L145,146@5997L49,148@6120L11,145@5975L188,150@6176L542,162@6731L542,174@7311L26,174@7286L221:SettingsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1609235419, i, -1, "org.cipherkey.app.ui.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:114)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, centerVertically, composer, 54);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1697805262, "C118@4807L690,136@5695L37,133@5514L237:SettingsScreen.kt#r4glq3");
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 945711957, "C120@4898L49,121@4995L10,119@4868L171,130@5428L11,123@5060L419:SettingsScreen.kt#r4glq3");
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_biometric_title, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleSmall(), composer, 0, 0, 131070);
            if (SettingsScreen$lambda$1(state)) {
                composer.startReplaceGroup(945940364);
                ComposerKt.sourceInformation(composer, "125@5144L51");
                strStringResource = StringResources_androidKt.stringResource(R.string.settings_biometric_summary, composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(946052584);
                ComposerKt.sourceInformation(composer, "127@5257L55");
                strStringResource = StringResources_androidKt.stringResource(R.string.settings_biometric_unavailable, composer, 0);
                composer.endReplaceGroup();
            }
            TextKt.m3252TextNvy7gAk(strStringResource, null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            boolean z = SettingsScreen$lambda$0(state2).getBiometricUnlockEnabled() && SettingsScreen$lambda$1(state);
            boolean zSettingsScreen$lambda$1 = SettingsScreen$lambda$1(state);
            ComposerKt.sourceInformationMarkerStart(composer, 776543590, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(settingsViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$0$0$1$0(settingsViewModel, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SwitchKt.Switch(z, (Function1) objRememberedValue, null, null, zSettingsScreen$lambda$1, null, null, composer, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            String str = "CC(remember):SettingsScreen.kt#9igjgp";
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_timeout_title, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleSmall(), composer, 0, 0, 131070);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_timeout_summary, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            float f = 6;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f));
            Object obj = null;
            boolean z2 = true;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            String str2 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, str2);
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, Alignment.INSTANCE.getTop(), composer, 6);
            String str3 = "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh";
            char c = 60377;
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, str3);
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            String str4 = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, str4);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            String str5 = "C101@5233L9:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str5);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            String str6 = "C:SettingsScreen.kt#r4glq3";
            ComposerKt.sourceInformationMarkerStart(composer, 714393350, "C:SettingsScreen.kt#r4glq3");
            composer.startReplaceGroup(-1778069100);
            ComposerKt.sourceInformation(composer, "*157@6536L45,158@6615L48,155@6414L272");
            char c2 = 4;
            Iterator it = CollectionsKt.take(KeyPolicy.INSTANCE.getTIMEOUT_CHOICES_MILLIS(), 4).iterator();
            while (it.hasNext()) {
                float f2 = f;
                final long jLongValue = ((Number) it.next()).longValue();
                boolean z3 = SettingsScreen$lambda$0(state2).getSessionTimeoutMillis() == jLongValue ? z2 : false;
                String str7 = str;
                ComposerKt.sourceInformationMarkerStart(composer, -820841540, str7);
                boolean zChangedInstance2 = composer.changedInstance(settingsViewModel) | composer.changed(jLongValue);
                Object objRememberedValue2 = composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$7$0$1$0$0$0(settingsViewModel, jLongValue);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                str = str7;
                ChipKt.FilterChip(z3, (Function0) objRememberedValue2, ComposableLambdaKt.rememberComposableLambda(1234564994, z2, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$0$1$0$1(jLongValue, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54), null, false, null, null, null, null, null, null, null, composer, 384, 0, 4088);
                obj = null;
                f = f2;
                str4 = str4;
                str6 = str6;
                str5 = str5;
                str2 = str2;
                str3 = str3;
                z2 = true;
                c = 60377;
                c2 = 4;
            }
            String str8 = str4;
            String str9 = str5;
            String str10 = str6;
            String str11 = str;
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_43 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f));
            boolean z4 = true;
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, obj);
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, str2);
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_43, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, str3);
            boolean z5 = false;
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, modifierFillMaxWidth$default2);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, str8);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl4 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str9);
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1969175367, str10);
            composer.startReplaceGroup(-2014686925);
            ComposerKt.sourceInformation(composer, "*169@7091L45,170@7170L48,167@6969L272");
            for (Iterator it2 = CollectionsKt.drop(KeyPolicy.INSTANCE.getTIMEOUT_CHOICES_MILLIS(), 4).iterator(); it2.hasNext(); it2 = it2) {
                final long jLongValue2 = ((Number) it2.next()).longValue();
                boolean z6 = SettingsScreen$lambda$0(state2).getSessionTimeoutMillis() == jLongValue2 ? z4 : z5;
                String str12 = str11;
                ComposerKt.sourceInformationMarkerStart(composer, -1057459365, str12);
                boolean zChangedInstance3 = composer.changedInstance(settingsViewModel) | composer.changed(jLongValue2);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$7$0$2$0$0$0(settingsViewModel, jLongValue2);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ChipKt.FilterChip(z6, (Function0) objRememberedValue3, ComposableLambdaKt.rememberComposableLambda(197448417, z4, new Function2() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$0$2$0$1(jLongValue2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54), null, false, null, null, null, null, null, null, null, composer, 384, 0, 4088);
                str11 = str12;
                z5 = z5;
                z4 = z4;
            }
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -286924001, str11);
            boolean zChangedInstance4 = composer.changedInstance(settingsViewModel);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChangedInstance4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$7$0$3$0(settingsViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$876884723$app_release(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$0$1$0(SettingsViewModel settingsViewModel, boolean z) {
        settingsViewModel.setBiometricEnabled(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$1$0$0$0(SettingsViewModel settingsViewModel, long j) {
        settingsViewModel.setSessionTimeoutMillis(j);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$1$0$1(long j, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C158@6622L20,158@6617L44:SettingsScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1234564994, i, -1, "org.cipherkey.app.ui.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:158)");
            }
            TextKt.m3252TextNvy7gAk(timeoutLabel(j, composer, 0), null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$2$0$0$0(SettingsViewModel settingsViewModel, long j) {
        settingsViewModel.setSessionTimeoutMillis(j);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$2$0$1(long j, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C170@7177L20,170@7172L44:SettingsScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(197448417, i, -1, "org.cipherkey.app.ui.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:170)");
            }
            TextKt.m3252TextNvy7gAk(timeoutLabel(j, composer, 0), null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$0$3$0(SettingsViewModel settingsViewModel) {
        settingsViewModel.lockAllNow();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$1(final ColumnScope columnScope, final Context context, final ManagedActivityResultLauncher managedActivityResultLauncher, final SettingsViewModel settingsViewModel, State state, State state2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C186@7740L1259:SettingsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-155881060, i, -1, "org.cipherkey.app.ui.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:186)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, centerVertically, composer, 54);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1473765801, "C190@7909L653,206@8682L284,204@8579L406:SettingsScreen.kt#r4glq3");
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 626705283, "C192@8000L51,194@8141L11,191@7970L222:SettingsScreen.kt#r4glq3");
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_assistant_summary, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            Composer composer2 = composer;
            if (!SettingsScreen$lambda$0(state).getAssistantEnabled() || SettingsScreen$lambda$2(state2)) {
                composer2.startReplaceGroup(618780256);
            } else {
                composer2.startReplaceGroup(627011159);
                ComposerKt.sourceInformation(composer2, "198@8323L57,200@8478L11,197@8289L233");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_assistant_needs_overlay, composer2, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getError(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            boolean assistantEnabled = SettingsScreen$lambda$0(state).getAssistantEnabled();
            ComposerKt.sourceInformationMarkerStart(composer2, 368125108, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composer2.changedInstance(managedActivityResultLauncher) | composer2.changedInstance(settingsViewModel);
            Object objRememberedValue = composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$7$1$0$1$0(managedActivityResultLauncher, settingsViewModel, ((Boolean) obj).booleanValue());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            SwitchKt.Switch(assistantEnabled, (Function1) objRememberedValue, null, null, false, null, null, composer2, 0, 124);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (!SettingsScreen$lambda$0(state).getAssistantEnabled() || SettingsScreen$lambda$2(state2)) {
                composer2.startReplaceGroup(-1000676666);
            } else {
                composer2.startReplaceGroup(-991671011);
                ComposerKt.sourceInformation(composer2, "215@9101L367,215@9080L457");
                ComposerKt.sourceInformationMarkerStart(composer2, 522201067, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean zChanged = composer2.changed(columnScope) | composer2.changedInstance(context);
                Object objRememberedValue2 = composer2.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$7$1$1$0(columnScope, context);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$787672388$app_release(), composer, 805306368, 510);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$1$0$1$0(ManagedActivityResultLauncher managedActivityResultLauncher, SettingsViewModel settingsViewModel, boolean z) {
        if (z && Build.VERSION.SDK_INT >= 33) {
            managedActivityResultLauncher.launch("android.permission.POST_NOTIFICATIONS");
        }
        settingsViewModel.setAssistantEnabled(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$1$1$0(ColumnScope columnScope, Context context) {
        try {
            Result.Companion companion = Result.INSTANCE;
            context.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getPackageName())));
            Result.m8783constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8783constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit SettingsScreen$lambda$7$2(final SettingsViewModel settingsViewModel, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C234@9799L45,236@9918L11,233@9777L184,*239@10033L1049:SettingsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2090087459, i, -1, "org.cipherkey.app.ui.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:233)");
            }
            int i2 = 1;
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_kdf_summary, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            Composer composer2 = composer;
            Iterator<Argon2Profile> it = Argon2Profile.getEntries().iterator();
            while (it.hasNext()) {
                final Argon2Profile next = it.next();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i2, null);
                ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer2, 48);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor);
                } else {
                    composer2.useNode();
                }
                Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composer2);
                Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 1780310465, "C245@10319L39,243@10202L179,247@10402L662:SettingsScreen.kt#r4glq3");
                boolean z = SettingsScreen$lambda$0(state).getArgon2Profile() == next ? i2 : 0;
                ComposerKt.sourceInformationMarkerStart(composer2, 334526955, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean zChangedInstance = composer2.changedInstance(settingsViewModel) | composer2.changed(next.ordinal());
                Object objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.SettingsScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$7$2$0$0$0$0(settingsViewModel, next);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                RadioButtonKt.RadioButton(z, (Function0) objRememberedValue, null, false, null, null, composer2, 0, 60);
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, 0);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor2);
                } else {
                    composer2.useNode();
                }
                Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer2);
                Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 144568406, "C248@10435L167,253@10661L228,259@10987L11,252@10627L415:SettingsScreen.kt#r4glq3");
                String lowerCase = next.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (lowerCase.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    String strValueOf = String.valueOf(lowerCase.charAt(0));
                    Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                    String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    StringBuilder sbAppend = sb.append((Object) upperCase);
                    String strSubstring = lowerCase.substring(i2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    lowerCase = sbAppend.append(strSubstring).toString();
                }
                TextKt.m3252TextNvy7gAk(lowerCase, null, 0L, null, TextUnitKt.getSp(14), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.kdf_profile_desc, new Object[]{Integer.valueOf(next.getParams().getMemoryKib() / 1024), Integer.valueOf(next.getParams().getIterations())}, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer2 = composer;
                i2 = 1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SettingsScreen$lambda$7$2$0$0$0$0(SettingsViewModel settingsViewModel, Argon2Profile argon2Profile) {
        settingsViewModel.setArgon2Profile(argon2Profile);
        return Unit.INSTANCE;
    }

    private static final AppSettings SettingsScreen$lambda$0(State<AppSettings> state) {
        return state.getValue();
    }

    private static final boolean SettingsScreen$lambda$1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean SettingsScreen$lambda$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SettingsEvent SettingsScreen$lambda$3(State<? extends SettingsEvent> state) {
        return state.getValue();
    }
}
