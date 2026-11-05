package org.cipherkey.app.ui;

import android.content.Context;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.fragment.app.FragmentActivity;
import androidx.hilt.navigation.compose.HiltViewModelKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import org.cipherkey.app.R;
import org.cipherkey.app.ToolsUiState;
import org.cipherkey.app.ToolsViewModel;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.app.security.BiometricPromptHelper;
import org.cipherkey.app.security.BiometricPromptHostKt;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.messages.Ck1;

/* compiled from: DecryptScreen.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\n\u0010\u0006\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u008e\u0002"}, d2 = {"DecryptScreen", "", "viewModel", "Lorg/cipherkey/app/ToolsViewModel;", "(Lorg/cipherkey/app/ToolsViewModel;Landroidx/compose/runtime/Composer;II)V", "app_release", "state", "Lorg/cipherkey/app/ToolsUiState;", "input", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DecryptScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$6(ToolsViewModel toolsViewModel, int i, int i2, Composer composer, int i3) {
        DecryptScreen(toolsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0564  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0600  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x06d6  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x06e0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x073b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0434  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void DecryptScreen(ToolsViewModel toolsViewModel, Composer composer, final int i, final int i2) {
        ToolsViewModel toolsViewModel2;
        int i3;
        final ToolsViewModel toolsViewModel3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        final State stateCollectAsStateWithLifecycle;
        Object objRememberedValue;
        BiometricPromptRequest biometricPrompt;
        String str;
        final MutableState mutableState;
        Object obj;
        Composer composer2;
        Object objRememberedValue2;
        MutableState mutableState2;
        boolean zChangedInstance;
        Object objRememberedValue3;
        final MutableState mutableState3;
        MutableState mutableState4;
        boolean zChangedInstance2;
        Object objRememberedValue4;
        final MutableState mutableState5;
        Modifier modifier;
        UiNotice notice;
        final String resultText;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(472760814);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DecryptScreen)N(viewModel)45@1906L29,46@1967L7,48@1993L31,69@2776L21,66@2685L3456:DecryptScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                toolsViewModel2 = toolsViewModel;
                if (composerStartRestartGroup.changedInstance(toolsViewModel2)) {
                    i4 = 4;
                }
                i3 = i | i4;
            } else {
                toolsViewModel2 = toolsViewModel;
            }
            i4 = 2;
            i3 = i | i4;
        } else {
            toolsViewModel2 = toolsViewModel;
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "43@1852L15");
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
                    ViewModel viewModel = ViewModelKt.viewModel((Class<ViewModel>) ToolsViewModel.class, current, (String) null, factoryCreateHiltViewModelFactory, defaultViewModelCreationExtras, composerStartRestartGroup, 36936, 0);
                    composerStartRestartGroup.endReplaceableGroup();
                    composerStartRestartGroup.endReplaceableGroup();
                    toolsViewModel2 = (ToolsViewModel) viewModel;
                    i3 &= -15;
                }
                final ToolsViewModel toolsViewModel4 = toolsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(472760814, i3, -1, "org.cipherkey.app.ui.DecryptScreen (DecryptScreen.kt:44)");
                }
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(toolsViewModel4.getState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -943384947, "CC(remember):DecryptScreen.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MutableState mutableState6 = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                biometricPrompt = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBiometricPrompt();
                if (biometricPrompt != null) {
                    composerStartRestartGroup.startReplaceGroup(819986795);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableState = mutableState6;
                    str = "";
                    composer2 = composerStartRestartGroup;
                    obj = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(819986796);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    FragmentActivity fragmentActivity = context instanceof FragmentActivity ? (FragmentActivity) context : null;
                    if (fragmentActivity == null) {
                        composerStartRestartGroup.startReplaceGroup(1502158541);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableState = mutableState6;
                        str = "";
                        composer2 = composerStartRestartGroup;
                        obj = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1502158542);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*53@2226L108,59@2471L34,60@2540L41,61@2616L32,56@2347L316");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -787177146, "CC(remember):DecryptScreen.kt#9igjgp");
                        boolean zChanged = composerStartRestartGroup.changed(fragmentActivity);
                        Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue5 = new BiometricPromptHelper(fragmentActivity, toolsViewModel4.getBiometricStore());
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        BiometricPromptHelper biometricPromptHelper = (BiometricPromptHelper) objRememberedValue5;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -787169380, "CC(remember):DecryptScreen.kt#9igjgp");
                        boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        DecryptScreenKt$DecryptScreen$1$1$1$1 decryptScreenKt$DecryptScreen$1$1$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance3 || decryptScreenKt$DecryptScreen$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            decryptScreenKt$DecryptScreen$1$1$1$1RememberedValue = new DecryptScreenKt$DecryptScreen$1$1$1$1(toolsViewModel4, null);
                            composerStartRestartGroup.updateRememberedValue(decryptScreenKt$DecryptScreen$1$1$1$1RememberedValue);
                        }
                        Function1 function1 = (Function1) decryptScreenKt$DecryptScreen$1$1$1$1RememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -787167165, "CC(remember):DecryptScreen.kt#9igjgp");
                        boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue6 = new Function1() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return DecryptScreenKt.DecryptScreen$lambda$4$0$2$0(toolsViewModel4, (byte[]) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        Function1 function12 = (Function1) objRememberedValue6;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -787164742, "CC(remember):DecryptScreen.kt#9igjgp");
                        boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue7 = new Function1() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return DecryptScreenKt.DecryptScreen$lambda$4$0$3$0(toolsViewModel4, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                        }
                        Function1 function13 = (Function1) objRememberedValue7;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        str = "";
                        mutableState = mutableState6;
                        obj = null;
                        BiometricPromptHostKt.BiometricPromptHost(biometricPrompt, biometricPromptHelper, function1, function12, function13, composerStartRestartGroup, 0);
                        composer2 = composerStartRestartGroup;
                        Unit unit = Unit.INSTANCE;
                        composer2.endReplaceGroup();
                        Unit unit2 = Unit.INSTANCE;
                    }
                    composer2.endReplaceGroup();
                }
                Modifier modifierM927paddingVpY3zN4$default = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), ScrollKt.rememberScrollState(0, composer2, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(16), 0.0f, 2, obj);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer2, 6);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM927paddingVpY3zN4$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (!composer2.getInserting()) {
                    composer2.createNode(constructor);
                } else {
                    composer2.useNode();
                }
                Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composer2);
                Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 1701440216, "C74@2950L38,75@3013L41,73@2916L149,80@3149L14,78@3075L357,95@3670L733,162@6094L41:DecryptScreen.kt#r4glq3");
                ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.decrypt_title, composer2, 0), StringResources_androidKt.stringResource(R.string.decrypt_subtitle, composer2, 0), null, composer2, 0, 4);
                String strDecryptScreen$lambda$2 = DecryptScreen$lambda$2(mutableState);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, -1192036570, "CC(remember):DecryptScreen.kt#9igjgp");
                objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return DecryptScreenKt.DecryptScreen$lambda$5$0$0(mutableState, (String) obj2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Composer composer3 = composer2;
                mutableState2 = mutableState;
                OutlinedTextFieldKt.OutlinedTextField(strDecryptScreen$lambda$2, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$653696350$app_release(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$1355630943$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 10, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer3, 14156208, 905969664, 0, 7601976);
                composerStartRestartGroup = composer3;
                if (!StringsKt.isBlank(DecryptScreen$lambda$2(mutableState2)) || !Ck1.INSTANCE.isEnvelope(StringsKt.trim((CharSequence) DecryptScreen$lambda$2(mutableState2)).toString())) {
                    composerStartRestartGroup.startReplaceGroup(1698446762);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1701922792);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "90@3547L45,89@3512L138");
                    ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.decrypt_ck1_detected, composerStartRestartGroup, 0), ChipState.SUCCESS, null, composerStartRestartGroup, 48, 4);
                }
                composerStartRestartGroup.endReplaceGroup();
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (!composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composerStartRestartGroup);
                Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1435003619, "C97@3783L71,96@3741L350,105@4142L94,104@4104L289:DecryptScreen.kt#r4glq3");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 877575195, "CC(remember):DecryptScreen.kt#9igjgp");
                toolsViewModel3 = toolsViewModel4;
                zChangedInstance = composerStartRestartGroup.changedInstance(toolsViewModel3);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    mutableState3 = mutableState2;
                    objRememberedValue3 = new Function0() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DecryptScreenKt.DecryptScreen$lambda$5$1$0$0(toolsViewModel3, mutableState3);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    mutableState3 = mutableState2;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableState4 = mutableState3;
                ButtonKt.OutlinedButton((Function0) objRememberedValue3, RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$908107682$app_release(), composerStartRestartGroup, 805306368, 508);
                boolean z = DecryptScreen$lambda$2(mutableState4).length() <= 0;
                Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 877586706, "CC(remember):DecryptScreen.kt#9igjgp");
                zChangedInstance2 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    mutableState5 = mutableState4;
                    objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DecryptScreenKt.DecryptScreen$lambda$5$1$1$0(toolsViewModel3, mutableState5);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    mutableState5 = mutableState4;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final MutableState mutableState7 = mutableState5;
                ButtonKt.TextButton((Function0) objRememberedValue4, modifierWeight$default, z, null, null, null, null, null, null, ComposableSingletons$DecryptScreenKt.INSTANCE.m10532getLambda$528953289$app_release(), composerStartRestartGroup, 805306368, 504);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (!DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getUnlockNeeded()) {
                    composerStartRestartGroup.startReplaceGroup(1702866525);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "116@4487L127,120@4639L36,123@4801L46,124@4876L28,125@4936L33,115@4451L533");
                    int i5 = R.string.message_from;
                    String unlockContactName = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getUnlockContactName();
                    if (unlockContactName == null) {
                        unlockContactName = str;
                    }
                    String strStringResource = StringResources_androidKt.stringResource(i5, new Object[]{unlockContactName}, composerStartRestartGroup, 0);
                    String strStringResource2 = StringResources_androidKt.stringResource(R.string.locked_hint, composerStartRestartGroup, 0);
                    boolean busy = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBusy();
                    boolean biometricOffered = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBiometricOffered();
                    String strStringResource3 = StringResources_androidKt.stringResource(R.string.unlock_decrypt_action, composerStartRestartGroup, 0);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1191981292, "CC(remember):DecryptScreen.kt#9igjgp");
                    boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    DecryptScreenKt$DecryptScreen$2$3$1 decryptScreenKt$DecryptScreen$2$3$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance6 || decryptScreenKt$DecryptScreen$2$3$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        decryptScreenKt$DecryptScreen$2$3$1RememberedValue = new DecryptScreenKt$DecryptScreen$2$3$1(toolsViewModel3);
                        composerStartRestartGroup.updateRememberedValue(decryptScreenKt$DecryptScreen$2$3$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Function1 function14 = (Function1) ((KFunction) decryptScreenKt$DecryptScreen$2$3$1RememberedValue);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1191979367, "CC(remember):DecryptScreen.kt#9igjgp");
                    boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    DecryptScreenKt$DecryptScreen$2$4$1 decryptScreenKt$DecryptScreen$2$4$1RememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance7 || decryptScreenKt$DecryptScreen$2$4$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        decryptScreenKt$DecryptScreen$2$4$1RememberedValue = new DecryptScreenKt$DecryptScreen$2$4$1(toolsViewModel3);
                        composerStartRestartGroup.updateRememberedValue(decryptScreenKt$DecryptScreen$2$4$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    EncryptScreenKt.UnlockCard(strStringResource, strStringResource2, busy, biometricOffered, strStringResource3, function14, (Function0) ((KFunction) decryptScreenKt$DecryptScreen$2$4$1RememberedValue), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                    modifier = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1703433081);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "129@5048L28,128@5014L369");
                    boolean z2 = (StringsKt.isBlank(DecryptScreen$lambda$2(mutableState7)) || DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBusy()) ? false : true;
                    Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1191975788, "CC(remember):DecryptScreen.kt#9igjgp");
                    boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance8 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue8 = new Function0() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DecryptScreenKt.DecryptScreen$lambda$5$4$0(toolsViewModel3, mutableState7);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    modifier = null;
                    ButtonKt.Button((Function0) objRememberedValue8, modifierFillMaxWidth$default2, z2, null, null, null, null, null, null, ComposableSingletons$DecryptScreenKt.INSTANCE.m10531getLambda$304091891$app_release(), composerStartRestartGroup, 805306416, 504);
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBusy()) {
                    composerStartRestartGroup.startReplaceGroup(1698446762);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1703825758);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "140@5449L35,140@5433L52");
                    ComponentsKt.BusyRow(StringResources_androidKt.stringResource(R.string.decrypting, composerStartRestartGroup, 0), modifier, composerStartRestartGroup, 0, 2);
                }
                composerStartRestartGroup.endReplaceGroup();
                notice = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getNotice();
                if (notice != null) {
                    composerStartRestartGroup.startReplaceGroup(1703924833);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1703924834);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*144@5574L6,144@5547L34");
                    ComponentsKt.NoticeBanner(EncryptScreenKt.text(notice, composerStartRestartGroup, 0), null, false, composerStartRestartGroup, 0, 6);
                    Unit unit3 = Unit.INSTANCE;
                    composerStartRestartGroup.endReplaceGroup();
                    Unit unit4 = Unit.INSTANCE;
                }
                resultText = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getResultText();
                if (resultText != null) {
                    composerStartRestartGroup.startReplaceGroup(1704036216);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1704036217);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*148@5662L412,148@5650L424");
                    ComponentsKt.SectionCard(null, null, null, ComposableLambdaKt.rememberComposableLambda(49125158, true, new Function2() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return DecryptScreenKt.DecryptScreen$lambda$5$6$0(resultText, toolsViewModel3, stateCollectAsStateWithLifecycle, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 3072, 7);
                    Unit unit5 = Unit.INSTANCE;
                    composerStartRestartGroup.endReplaceGroup();
                    Unit unit6 = Unit.INSTANCE;
                }
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
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                final ToolsViewModel toolsViewModel42 = toolsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(toolsViewModel42.getState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = composerStartRestartGroup.consume(localContext2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Context context2 = (Context) objConsume2;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -943384947, "CC(remember):DecryptScreen.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                MutableState mutableState62 = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                biometricPrompt = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBiometricPrompt();
                if (biometricPrompt != null) {
                }
                Modifier modifierM927paddingVpY3zN4$default2 = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), ScrollKt.rememberScrollState(0, composer2, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(16), 0.0f, 2, obj);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_43 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_43, Alignment.INSTANCE.getStart(), composer2, 6);
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierM927paddingVpY3zN4$default2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                }
                composer2.startReusableNode();
                if (!composer2.getInserting()) {
                }
                Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer2);
                Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 1701440216, "C74@2950L38,75@3013L41,73@2916L149,80@3149L14,78@3075L357,95@3670L733,162@6094L41:DecryptScreen.kt#r4glq3");
                ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.decrypt_title, composer2, 0), StringResources_androidKt.stringResource(R.string.decrypt_subtitle, composer2, 0), null, composer2, 0, 4);
                String strDecryptScreen$lambda$22 = DecryptScreen$lambda$2(mutableState);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, -1192036570, "CC(remember):DecryptScreen.kt#9igjgp");
                objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Composer composer32 = composer2;
                mutableState2 = mutableState;
                OutlinedTextFieldKt.OutlinedTextField(strDecryptScreen$lambda$22, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$653696350$app_release(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$1355630943$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 10, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer32, 14156208, 905969664, 0, 7601976);
                composerStartRestartGroup = composer32;
                if (!StringsKt.isBlank(DecryptScreen$lambda$2(mutableState2))) {
                    composerStartRestartGroup.startReplaceGroup(1698446762);
                    composerStartRestartGroup.endReplaceGroup();
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_422 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier.Companion companion2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_422, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap22 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier22 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
                    Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (!composerStartRestartGroup.getInserting()) {
                    }
                    Composer composerM4636constructorimpl22 = Updater.m4636constructorimpl(composerStartRestartGroup);
                    Updater.m4644setimpl(composerM4636constructorimpl22, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4644setimpl(composerM4636constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4640initimpl(composerM4636constructorimpl22, Integer.valueOf(iHashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4642reconcileimpl(composerM4636constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4644setimpl(composerM4636constructorimpl22, modifierMaterializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1435003619, "C97@3783L71,96@3741L350,105@4142L94,104@4104L289:DecryptScreen.kt#r4glq3");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 877575195, "CC(remember):DecryptScreen.kt#9igjgp");
                    toolsViewModel3 = toolsViewModel42;
                    zChangedInstance = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        mutableState3 = mutableState2;
                        objRememberedValue3 = new Function0() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DecryptScreenKt.DecryptScreen$lambda$5$1$0$0(toolsViewModel3, mutableState3);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableState4 = mutableState3;
                        ButtonKt.OutlinedButton((Function0) objRememberedValue3, RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DecryptScreenKt.INSTANCE.getLambda$908107682$app_release(), composerStartRestartGroup, 805306368, 508);
                        if (DecryptScreen$lambda$2(mutableState4).length() <= 0) {
                        }
                        Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 877586706, "CC(remember):DecryptScreen.kt#9igjgp");
                        zChangedInstance2 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance2) {
                            mutableState5 = mutableState4;
                            objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return DecryptScreenKt.DecryptScreen$lambda$5$1$1$0(toolsViewModel3, mutableState5);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            final MutableState mutableState72 = mutableState5;
                            ButtonKt.TextButton((Function0) objRememberedValue4, modifierWeight$default2, z, null, null, null, null, null, null, ComposableSingletons$DecryptScreenKt.INSTANCE.m10532getLambda$528953289$app_release(), composerStartRestartGroup, 805306368, 504);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composerStartRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            if (!DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getUnlockNeeded()) {
                            }
                            if (DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBusy()) {
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            notice = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getNotice();
                            if (notice != null) {
                            }
                            resultText = DecryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getResultText();
                            if (resultText != null) {
                            }
                            SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(12)), composerStartRestartGroup, 6);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            composerStartRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                        }
                    }
                }
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            toolsViewModel3 = toolsViewModel2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DecryptScreenKt.DecryptScreen$lambda$6(toolsViewModel3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final String DecryptScreen$lambda$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$4$0$2$0(ToolsViewModel toolsViewModel, byte[] bArr) {
        toolsViewModel.onBiometricUnlockResult(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$4$0$3$0(ToolsViewModel toolsViewModel, boolean z) {
        toolsViewModel.onRememberDone(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$0$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$1$0$0(ToolsViewModel toolsViewModel, final MutableState mutableState) {
        toolsViewModel.pasteIntoInput(new Function1() { // from class: org.cipherkey.app.ui.DecryptScreenKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DecryptScreenKt.DecryptScreen$lambda$5$1$0$0$0(mutableState, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$1$0$0$0(MutableState mutableState, String str) {
        if (str != null) {
            mutableState.setValue(str);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$1$1$0(ToolsViewModel toolsViewModel, MutableState mutableState) {
        mutableState.setValue("");
        toolsViewModel.clearResult();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$4$0(ToolsViewModel toolsViewModel, MutableState mutableState) {
        toolsViewModel.decrypt(DecryptScreen$lambda$2(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DecryptScreen$lambda$5$6$0(String str, ToolsViewModel toolsViewModel, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C150@5721L148,157@6020L21,149@5680L380:DecryptScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(49125158, i, -1, "org.cipherkey.app.ui.DecryptScreen.<anonymous>.<anonymous>.<anonymous> (DecryptScreen.kt:149)");
            }
            int i2 = R.string.result_decrypted_from;
            String resultContactName = DecryptScreen$lambda$0(state).getResultContactName();
            if (resultContactName == null) {
                resultContactName = "";
            }
            String strStringResource = StringResources_androidKt.stringResource(i2, new Object[]{resultContactName}, composer, 0);
            boolean copied = DecryptScreen$lambda$0(state).getCopied();
            ComposerKt.sourceInformationMarkerStart(composer, -35351941, "CC(remember):DecryptScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(toolsViewModel);
            DecryptScreenKt$DecryptScreen$2$7$1$1$1 decryptScreenKt$DecryptScreen$2$7$1$1$1RememberedValue = composer.rememberedValue();
            if (zChangedInstance || decryptScreenKt$DecryptScreen$2$7$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                decryptScreenKt$DecryptScreen$2$7$1$1$1RememberedValue = new DecryptScreenKt$DecryptScreen$2$7$1$1$1(toolsViewModel);
                composer.updateRememberedValue(decryptScreenKt$DecryptScreen$2$7$1$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComponentsKt.ResultBlock(strStringResource, str, false, copied, (Function0) ((KFunction) decryptScreenKt$DecryptScreen$2$7$1$1$1RememberedValue), null, null, composer, 384, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final ToolsUiState DecryptScreen$lambda$0(State<ToolsUiState> state) {
        return state.getValue();
    }
}
