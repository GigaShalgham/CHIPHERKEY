package org.cipherkey.app.ui;

import android.content.Context;
import android.content.Intent;
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
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.unit.TextUnitKt;
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
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.MessageBundle;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.app.R;
import org.cipherkey.app.ToolsUiState;
import org.cipherkey.app.ToolsViewModel;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.app.security.BiometricPromptHelper;
import org.cipherkey.app.security.BiometricPromptHostKt;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;

/* compiled from: EncryptScreen.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0003\u001a\u0017\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\b\u001aW\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00050\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u0007¢\u0006\u0002\u0010\u0015\u001a\r\u0010\u0016\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0017¨\u0006\u0018²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0001X\u008a\u008e\u0002"}, d2 = {TextBundle.TEXT_ENTRY, "", "Lorg/cipherkey/app/companion/UiNotice;", "(Lorg/cipherkey/app/companion/UiNotice;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "EncryptScreen", "", "viewModel", "Lorg/cipherkey/app/ToolsViewModel;", "(Lorg/cipherkey/app/ToolsViewModel;Landroidx/compose/runtime/Composer;II)V", "UnlockCard", MessageBundle.TITLE_ENTRY, "hint", "busy", "", "biometricOffered", "submitLabel", "onSubmit", "Lkotlin/Function1;", "", "onBiometric", "Lkotlin/Function0;", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EmptyStateCard", "(Landroidx/compose/runtime/Composer;I)V", "app_release", "state", "Lorg/cipherkey/app/ToolsUiState;", "input"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EncryptScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EmptyStateCard$lambda$0(int i, Composer composer, int i2) {
        EmptyStateCard(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$6(ToolsViewModel toolsViewModel, int i, int i2, Composer composer, int i3) {
        EncryptScreen(toolsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UnlockCard$lambda$1(String str, String str2, boolean z, boolean z2, String str3, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        UnlockCard(str, str2, z, z2, str3, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final String text(UiNotice uiNotice, Composer composer, int i) {
        String strStringResource;
        Intrinsics.checkNotNullParameter(uiNotice, "<this>");
        ComposerKt.sourceInformationMarkerStart(composer, -983111062, "C(text):EncryptScreen.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-983111062, i, -1, "org.cipherkey.app.ui.text (EncryptScreen.kt:42)");
        }
        String arg = uiNotice.getArg();
        if (arg == null) {
            composer.startReplaceGroup(-479616968);
            composer.endReplaceGroup();
            strStringResource = null;
        } else {
            composer.startReplaceGroup(-479616967);
            ComposerKt.sourceInformation(composer, "*42@1814L23");
            strStringResource = StringResources_androidKt.stringResource(uiNotice.getRes(), new Object[]{arg}, composer, 0);
            composer.endReplaceGroup();
        }
        if (strStringResource == null) {
            composer.startReplaceGroup(123076925);
            ComposerKt.sourceInformation(composer, "42@1843L19");
            strStringResource = StringResources_androidKt.stringResource(uiNotice.getRes(), composer, 0);
        } else {
            composer.startReplaceGroup(123075685);
        }
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return strStringResource;
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x06d2  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x072d  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0794  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0328  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void EncryptScreen(ToolsViewModel toolsViewModel, Composer composer, final int i, final int i2) {
        ToolsViewModel toolsViewModel2;
        int i3;
        final ToolsViewModel toolsViewModel3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        State stateCollectAsStateWithLifecycle;
        Object objRememberedValue;
        BiometricPromptRequest biometricPrompt;
        String str;
        String str2;
        final MutableState mutableState;
        Object obj;
        Composer composer2;
        Context context;
        State state;
        int i4;
        final MutableState mutableState2;
        final MutableState mutableState3;
        Modifier modifier;
        UiNotice notice;
        final String resultText;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(1155118654);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(EncryptScreen)N(viewModel)53@2191L29,54@2252L7,56@2278L31,77@3061L21,74@2970L4519:EncryptScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                toolsViewModel2 = toolsViewModel;
                if (composerStartRestartGroup.changedInstance(toolsViewModel2)) {
                    i5 = 4;
                }
                i3 = i | i5;
            } else {
                toolsViewModel2 = toolsViewModel;
            }
            i5 = 2;
            i3 = i | i5;
        } else {
            toolsViewModel2 = toolsViewModel;
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "51@2137L15");
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
                    ComposerKt.traceEventStart(1155118654, i3, -1, "org.cipherkey.app.ui.EncryptScreen (EncryptScreen.kt:52)");
                }
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(toolsViewModel4.getState(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Context context2 = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1967470301, "CC(remember):EncryptScreen.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MutableState mutableState4 = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                biometricPrompt = EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBiometricPrompt();
                if (biometricPrompt != null) {
                    composerStartRestartGroup.startReplaceGroup(862186267);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableState = mutableState4;
                    str2 = "CC(remember):EncryptScreen.kt#9igjgp";
                    str = "";
                    composer2 = composerStartRestartGroup;
                    obj = null;
                } else {
                    composerStartRestartGroup.startReplaceGroup(862186268);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "");
                    FragmentActivity fragmentActivity = context2 instanceof FragmentActivity ? (FragmentActivity) context2 : null;
                    if (fragmentActivity == null) {
                        composerStartRestartGroup.startReplaceGroup(1544358013);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableState = mutableState4;
                        str2 = "CC(remember):EncryptScreen.kt#9igjgp";
                        str = "";
                        composer2 = composerStartRestartGroup;
                        obj = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1544358014);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "*61@2511L108,67@2756L34,68@2825L41,69@2901L32,64@2632L316");
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2123678102, "CC(remember):EncryptScreen.kt#9igjgp");
                        boolean zChanged = composerStartRestartGroup.changed(fragmentActivity);
                        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new BiometricPromptHelper(fragmentActivity, toolsViewModel4.getBiometricStore());
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        BiometricPromptHelper biometricPromptHelper = (BiometricPromptHelper) objRememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2123685868, "CC(remember):EncryptScreen.kt#9igjgp");
                        boolean zChangedInstance = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        EncryptScreenKt$EncryptScreen$1$1$1$1 encryptScreenKt$EncryptScreen$1$1$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance || encryptScreenKt$EncryptScreen$1$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            encryptScreenKt$EncryptScreen$1$1$1$1RememberedValue = new EncryptScreenKt$EncryptScreen$1$1$1$1(toolsViewModel4, null);
                            composerStartRestartGroup.updateRememberedValue(encryptScreenKt$EncryptScreen$1$1$1$1RememberedValue);
                        }
                        Function1 function1 = (Function1) encryptScreenKt$EncryptScreen$1$1$1$1RememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2123688083, "CC(remember):EncryptScreen.kt#9igjgp");
                        boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function1() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda7
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return EncryptScreenKt.EncryptScreen$lambda$4$0$2$0(toolsViewModel4, (byte[]) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        Function1 function12 = (Function1) objRememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2123690506, "CC(remember):EncryptScreen.kt#9igjgp");
                        boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(toolsViewModel4);
                        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda8
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return EncryptScreenKt.EncryptScreen$lambda$4$0$3$0(toolsViewModel4, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        Function1 function13 = (Function1) objRememberedValue4;
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        str = "";
                        str2 = "CC(remember):EncryptScreen.kt#9igjgp";
                        mutableState = mutableState4;
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
                ComposerKt.sourceInformationMarkerStart(composer2, 1743672641, "C82@3235L38,83@3298L41,81@3201L149,189@7442L41:EncryptScreen.kt#r4glq3");
                ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.encrypt_title, composer2, 0), StringResources_androidKt.stringResource(R.string.encrypt_subtitle, composer2, 0), null, composer2, 0, 4);
                if (!EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getContacts().isEmpty()) {
                    composer2.startReplaceGroup(1743728626);
                    ComposerKt.sourceInformation(composer2, "87@3404L16");
                    EmptyStateCard(composer2, 0);
                    composer2.endReplaceGroup();
                    state = stateCollectAsStateWithLifecycle;
                    composerStartRestartGroup = composer2;
                    toolsViewModel3 = toolsViewModel4;
                    context = context2;
                    i4 = 0;
                    modifier = null;
                } else {
                    composer2.startReplaceGroup(1743855602);
                    ComposerKt.sourceInformation(composer2, "93@3648L24,94@3698L46,89@3450L309,99@3855L14,97@3773L385,107@4172L801");
                    List<Contact> contacts = EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getContacts();
                    String strM10464getSelectedContactIdP5lcXoU = EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).m10464getSelectedContactIdP5lcXoU();
                    Set<ContactId> unlocked = EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getUnlocked();
                    ComposerKt.sourceInformationMarkerStart(composer2, 1718825536, str2);
                    boolean zChangedInstance4 = composer2.changedInstance(toolsViewModel4);
                    EncryptScreenKt$EncryptScreen$2$1$1 encryptScreenKt$EncryptScreen$2$1$1RememberedValue = composer2.rememberedValue();
                    if (zChangedInstance4 || encryptScreenKt$EncryptScreen$2$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        encryptScreenKt$EncryptScreen$2$1$1RememberedValue = new EncryptScreenKt$EncryptScreen$2$1$1(toolsViewModel4);
                        composer2.updateRememberedValue(encryptScreenKt$EncryptScreen$2$1$1RememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Composer composer3 = composer2;
                    context = context2;
                    ContactPickerKt.m10551ContactPicker2pW6ks(contacts, strM10464getSelectedContactIdP5lcXoU, unlocked, (Function1) ((KFunction) encryptScreenKt$EncryptScreen$2$1$1RememberedValue), StringResources_androidKt.stringResource(R.string.encrypt_contact_label, composer2, 0), null, composer3, 0, 32);
                    String strEncryptScreen$lambda$2 = EncryptScreen$lambda$2(mutableState);
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composer3, 1718832150, str2);
                    Object objRememberedValue5 = composer3.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function1() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda9
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return EncryptScreenKt.EncryptScreen$lambda$5$1$0(mutableState, (String) obj2);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    MutableState mutableState5 = mutableState;
                    String str3 = str2;
                    state = stateCollectAsStateWithLifecycle;
                    i4 = 0;
                    OutlinedTextFieldKt.OutlinedTextField(strEncryptScreen$lambda$2, (Function1<? super String, Unit>) objRememberedValue5, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$EncryptScreenKt.INSTANCE.getLambda$1903258762$app_release(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$EncryptScreenKt.INSTANCE.m10540getLambda$199194741$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 10, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer3, 14156208, 905969664, 0, 7601976);
                    composerStartRestartGroup = composer3;
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
                    if (composerStartRestartGroup.getInserting()) {
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
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1763961083, "C109@4293L71,108@4247L378,117@4684L106,116@4642L317:EncryptScreen.kt#r4glq3");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1858018119, str3);
                    toolsViewModel3 = toolsViewModel4;
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        mutableState2 = mutableState5;
                        objRememberedValue6 = new Function0() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return EncryptScreenKt.EncryptScreen$lambda$5$2$0$0(toolsViewModel3, mutableState2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    } else {
                        mutableState2 = mutableState5;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    MutableState mutableState6 = mutableState2;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue6, RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.m10537getLambda$1582896434$app_release(), composerStartRestartGroup, 805306368, 508);
                    boolean z = EncryptScreen$lambda$2(mutableState6).length() > 0;
                    Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1858030666, str3);
                    boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                    Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        mutableState3 = mutableState6;
                        objRememberedValue7 = new Function0() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda11
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return EncryptScreenKt.EncryptScreen$lambda$5$2$1$0(toolsViewModel3, mutableState3);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    } else {
                        mutableState3 = mutableState6;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    final MutableState mutableState7 = mutableState3;
                    ButtonKt.TextButton((Function0) objRememberedValue7, modifierWeight$default, z, null, null, null, null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.m10538getLambda$1801210269$app_release(), composerStartRestartGroup, 805306368, 504);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    if (EncryptScreen$lambda$0(state).getUnlockNeeded()) {
                        composerStartRestartGroup.startReplaceGroup(1745354297);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "128@5069L139,132@5237L36,135@5411L46,136@5490L28,137@5554L33,127@5029L577");
                        int i6 = R.string.message_from;
                        String unlockContactName = EncryptScreen$lambda$0(state).getUnlockContactName();
                        if (unlockContactName == null) {
                            unlockContactName = str;
                        }
                        String strStringResource = StringResources_androidKt.stringResource(i6, new Object[]{unlockContactName}, composerStartRestartGroup, 0);
                        String strStringResource2 = StringResources_androidKt.stringResource(R.string.locked_hint, composerStartRestartGroup, 0);
                        boolean busy = EncryptScreen$lambda$0(state).getBusy();
                        boolean biometricOffered = EncryptScreen$lambda$0(state).getBiometricOffered();
                        String strStringResource3 = StringResources_androidKt.stringResource(R.string.unlock_encrypt_action, composerStartRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1718884484, str3);
                        boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                        EncryptScreenKt$EncryptScreen$2$4$1 encryptScreenKt$EncryptScreen$2$4$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance7 || encryptScreenKt$EncryptScreen$2$4$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            encryptScreenKt$EncryptScreen$2$4$1RememberedValue = new EncryptScreenKt$EncryptScreen$2$4$1(toolsViewModel3);
                            composerStartRestartGroup.updateRememberedValue(encryptScreenKt$EncryptScreen$2$4$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        Function1 function14 = (Function1) ((KFunction) encryptScreenKt$EncryptScreen$2$4$1RememberedValue);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1718886537, str3);
                        boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                        EncryptScreenKt$EncryptScreen$2$5$1 encryptScreenKt$EncryptScreen$2$5$1RememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance8 || encryptScreenKt$EncryptScreen$2$5$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            encryptScreenKt$EncryptScreen$2$5$1RememberedValue = new EncryptScreenKt$EncryptScreen$2$5$1(toolsViewModel3);
                            composerStartRestartGroup.updateRememberedValue(encryptScreenKt$EncryptScreen$2$5$1RememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        UnlockCard(strStringResource, strStringResource2, busy, biometricOffered, strStringResource3, function14, (Function0) ((KFunction) encryptScreenKt$EncryptScreen$2$5$1RememberedValue), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        modifier = null;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1745976746);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "141@5682L28,140@5644L432");
                        boolean z2 = (StringsKt.isBlank(EncryptScreen$lambda$2(mutableState7)) || EncryptScreen$lambda$0(state).m10464getSelectedContactIdP5lcXoU() == null || EncryptScreen$lambda$0(state).getBusy()) ? false : true;
                        Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1718890628, str3);
                        boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(toolsViewModel3);
                        Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance9 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue8 = new Function0() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda12
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return EncryptScreenKt.EncryptScreen$lambda$5$5$0(toolsViewModel3, mutableState7);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        modifier = null;
                        ButtonKt.Button((Function0) objRememberedValue8, modifierFillMaxWidth$default2, z2, null, null, null, null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.m10542getLambda$904332592$app_release(), composerStartRestartGroup, 805306416, 504);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                }
                if (EncryptScreen$lambda$0(state).getBusy()) {
                    composerStartRestartGroup.startReplaceGroup(1740363514);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1746443978);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "153@6156L39,153@6140L56");
                    ComponentsKt.BusyRow(StringResources_androidKt.stringResource(R.string.encrypt_action, composerStartRestartGroup, i4), modifier, composerStartRestartGroup, i4, 2);
                }
                composerStartRestartGroup.endReplaceGroup();
                notice = EncryptScreen$lambda$0(state).getNotice();
                if (notice != null) {
                    composerStartRestartGroup.startReplaceGroup(1746546897);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1746546898);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*157@6285L6,157@6258L34");
                    ComponentsKt.NoticeBanner(text(notice, composerStartRestartGroup, i4), null, false, composerStartRestartGroup, 0, 6);
                    Unit unit3 = Unit.INSTANCE;
                    composerStartRestartGroup.endReplaceGroup();
                    Unit unit4 = Unit.INSTANCE;
                }
                resultText = EncryptScreen$lambda$0(state).getResultText();
                if (resultText != null) {
                    composerStartRestartGroup.startReplaceGroup(1746678027);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1746678028);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*161@6372L1050,161@6360L1062");
                    final State state2 = state;
                    final Context context3 = context;
                    ComponentsKt.SectionCard(null, null, null, ComposableLambdaKt.rememberComposableLambda(731482998, true, new Function2() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return EncryptScreenKt.EncryptScreen$lambda$5$7$0(resultText, toolsViewModel3, context3, state2, (Composer) obj2, ((Integer) obj3).intValue());
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
                Context context22 = (Context) objConsume2;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1967470301, "CC(remember):EncryptScreen.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                MutableState mutableState42 = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                biometricPrompt = EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getBiometricPrompt();
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
                ComposerKt.sourceInformationMarkerStart(composer2, 1743672641, "C82@3235L38,83@3298L41,81@3201L149,189@7442L41:EncryptScreen.kt#r4glq3");
                ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.encrypt_title, composer2, 0), StringResources_androidKt.stringResource(R.string.encrypt_subtitle, composer2, 0), null, composer2, 0, 4);
                if (!EncryptScreen$lambda$0(stateCollectAsStateWithLifecycle).getContacts().isEmpty()) {
                }
                if (EncryptScreen$lambda$0(state).getBusy()) {
                }
                composerStartRestartGroup.endReplaceGroup();
                notice = EncryptScreen$lambda$0(state).getNotice();
                if (notice != null) {
                }
                resultText = EncryptScreen$lambda$0(state).getResultText();
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
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            toolsViewModel3 = toolsViewModel2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return EncryptScreenKt.EncryptScreen$lambda$6(toolsViewModel3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final String EncryptScreen$lambda$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$4$0$2$0(ToolsViewModel toolsViewModel, byte[] bArr) {
        toolsViewModel.onBiometricUnlockResult(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$4$0$3$0(ToolsViewModel toolsViewModel, boolean z) {
        toolsViewModel.onRememberDone(z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$1$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$2$0$0(ToolsViewModel toolsViewModel, final MutableState mutableState) {
        toolsViewModel.pasteIntoInput(new Function1() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EncryptScreenKt.EncryptScreen$lambda$5$2$0$0$0(mutableState, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$2$0$0$0(MutableState mutableState, String str) {
        if (str != null) {
            mutableState.setValue(str);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$2$1$0(ToolsViewModel toolsViewModel, MutableState mutableState) {
        mutableState.setValue("");
        toolsViewModel.clearResult();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$5$0(ToolsViewModel toolsViewModel, MutableState mutableState) {
        toolsViewModel.encrypt(EncryptScreen$lambda$2(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$7$0(final String str, ToolsViewModel toolsViewModel, final Context context, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C163@6431L147,170@6727L21,162@6390L377,173@6830L313,172@6784L624:EncryptScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(731482998, i, -1, "org.cipherkey.app.ui.EncryptScreen.<anonymous>.<anonymous>.<anonymous> (EncryptScreen.kt:162)");
            }
            int i2 = R.string.result_encrypted_for;
            String resultContactName = EncryptScreen$lambda$0(state).getResultContactName();
            if (resultContactName == null) {
                resultContactName = "";
            }
            String strStringResource = StringResources_androidKt.stringResource(i2, new Object[]{resultContactName}, composer, 0);
            boolean copied = EncryptScreen$lambda$0(state).getCopied();
            ComposerKt.sourceInformationMarkerStart(composer, -1419464085, "CC(remember):EncryptScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(toolsViewModel);
            EncryptScreenKt$EncryptScreen$2$8$1$1$1 encryptScreenKt$EncryptScreen$2$8$1$1$1RememberedValue = composer.rememberedValue();
            if (zChangedInstance || encryptScreenKt$EncryptScreen$2$8$1$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                encryptScreenKt$EncryptScreen$2$8$1$1$1RememberedValue = new EncryptScreenKt$EncryptScreen$2$8$1$1$1(toolsViewModel);
                composer.updateRememberedValue(encryptScreenKt$EncryptScreen$2$8$1$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComponentsKt.ResultBlock(strStringResource, str, true, copied, (Function0) ((KFunction) encryptScreenKt$EncryptScreen$2$8$1$1$1RememberedValue), null, null, composer, 384, 96);
            ComposerKt.sourceInformationMarkerStart(composer, -1419460497, "CC(remember):EncryptScreen.kt#9igjgp");
            boolean zChanged = composer.changed(str) | composer.changedInstance(context);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return EncryptScreenKt.EncryptScreen$lambda$5$7$0$1$0(context, str);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.m10539getLambda$1890374972$app_release(), composer, 805306416, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptScreen$lambda$5$7$0$1$0(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        context.startActivity(Intent.createChooser(intent, null));
        return Unit.INSTANCE;
    }

    public static final void UnlockCard(final String title, final String hint, final boolean z, final boolean z2, final String submitLabel, final Function1<? super char[], Unit> onSubmit, final Function0<Unit> onBiometric, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(submitLabel, "submitLabel");
        Intrinsics.checkNotNullParameter(onSubmit, "onSubmit");
        Intrinsics.checkNotNullParameter(onBiometric, "onBiometric");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1624302616);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UnlockCard)N(title,hint,busy,biometricOffered,submitLabel,onSubmit,onBiometric)204@7808L688,204@7781L715:EncryptScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(title) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(hint) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(submitLabel) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onSubmit) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBiometric) ? 1048576 : 524288;
        }
        int i3 = i2;
        if (!composerStartRestartGroup.shouldExecute((599187 & i3) != 599186, i3 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1624302616, i3, -1, "org.cipherkey.app.ui.UnlockCard (EncryptScreen.kt:203)");
            }
            composer2 = composerStartRestartGroup;
            ComponentsKt.SectionCard(title, null, null, ComposableLambdaKt.rememberComposableLambda(1300991023, true, new Function2() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return EncryptScreenKt.UnlockCard$lambda$0(hint, z2, onBiometric, submitLabel, z, onSubmit, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composer2, (i3 & 14) | 3072, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return EncryptScreenKt.UnlockCard$lambda$1(title, hint, z, z2, submitLabel, onSubmit, onBiometric, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UnlockCard$lambda$0(String str, boolean z, Function0 function0, String str2, boolean z2, Function1 function1, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C208@7906L11,205@7818L127,221@8341L41,220@8304L186:EncryptScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1300991023, i, -1, "org.cipherkey.app.ui.UnlockCard.<anonymous> (EncryptScreen.kt:205)");
            }
            TextKt.m3252TextNvy7gAk(str, null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            if (z) {
                composer.startReplaceGroup(38863636);
                ComposerKt.sourceInformation(composer, "211@7990L295");
                ButtonKt.OutlinedButton(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.getLambda$1413720258$app_release(), composer, 805306416, 508);
            } else {
                composer.startReplaceGroup(30940563);
            }
            composer.endReplaceGroup();
            ComponentsKt.PassphraseInput(StringResources_androidKt.stringResource(R.string.passphrase_label, composer, 0), str2, z2, function1, null, composer, 0, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void EmptyStateCard(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(966695798);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(EmptyStateCard)232@8611L336:EncryptScreen.kt#r4glq3");
        if (!composerStartRestartGroup.shouldExecute(i != 0, i & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(966695798, i, -1, "org.cipherkey.app.ui.EmptyStateCard (EncryptScreen.kt:231)");
            }
            ComponentsKt.SectionCard(null, null, null, ComposableSingletons$EncryptScreenKt.INSTANCE.m10541getLambda$377777859$app_release(), composerStartRestartGroup, 3072, 7);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.EncryptScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return EncryptScreenKt.EmptyStateCard$lambda$0(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final ToolsUiState EncryptScreen$lambda$0(State<ToolsUiState> state) {
        return state.getValue();
    }
}
