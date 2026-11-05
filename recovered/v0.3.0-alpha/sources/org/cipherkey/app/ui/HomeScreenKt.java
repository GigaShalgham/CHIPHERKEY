package org.cipherkey.app.ui;

import android.content.Context;
import android.content.Intent;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
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
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.cipherkey.app.MainViewModel;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.BaseCompanionActivityKt;
import org.cipherkey.app.companion.DecryptActivity;
import org.cipherkey.app.companion.EncryptActivity;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u001a3\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u000b\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"HomeScreen", "", "onOpenEncrypt", "Lkotlin/Function0;", "onOpenDecrypt", "viewModel", "Lorg/cipherkey/app/MainViewModel;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lorg/cipherkey/app/MainViewModel;Landroidx/compose/runtime/Composer;II)V", "app_release", "contactCount", "", "unlockedCount"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HomeScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$4(Function0 function0, Function0 function02, MainViewModel mainViewModel, int i, int i2, Composer composer, int i3) {
        HomeScreen(function0, function02, mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0662  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0811  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x081d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x08a4  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x08f3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0983  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0578  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void HomeScreen(final Function0<Unit> onOpenEncrypt, final Function0<Unit> onOpenDecrypt, MainViewModel mainViewModel, Composer composer, final int i, final int i2) {
        int i3;
        MainViewModel mainViewModel2;
        final MainViewModel mainViewModel3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        int i4;
        int i5;
        Composer composer2;
        int i6;
        MainViewModel mainViewModel4;
        Composer composer3;
        State stateCollectAsStateWithLifecycle;
        final Context context;
        boolean zChangedInstance;
        HomeScreenKt$HomeScreen$1$1 homeScreenKt$HomeScreen$1$1RememberedValue;
        boolean zChangedInstance2;
        Object objRememberedValue;
        boolean zChangedInstance3;
        Object objRememberedValue2;
        Intrinsics.checkNotNullParameter(onOpenEncrypt, "onOpenEncrypt");
        Intrinsics.checkNotNullParameter(onOpenDecrypt, "onOpenDecrypt");
        Composer composerStartRestartGroup = composer.startRestartGroup(-487089746);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(HomeScreen)N(onOpenEncrypt,onOpenDecrypt,viewModel)43@1746L29,44@1825L29,45@1886L7,47@1945L23,47@1924L44,52@2065L21,49@1974L5040:HomeScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(onOpenEncrypt) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onOpenDecrypt) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            if ((i2 & 4) == 0) {
                mainViewModel2 = mainViewModel;
                int i7 = composerStartRestartGroup.changedInstance(mainViewModel2) ? 256 : 128;
                i3 |= i7;
            } else {
                mainViewModel2 = mainViewModel;
            }
            i3 |= i7;
        } else {
            mainViewModel2 = mainViewModel;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "41@1678L15");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 4) != 0) {
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
                    CreationExtras creationExtras = defaultViewModelCreationExtras;
                    i4 = 0;
                    i5 = 1;
                    ViewModel viewModel = ViewModelKt.viewModel((Class<ViewModel>) MainViewModel.class, current, (String) null, factoryCreateHiltViewModelFactory, creationExtras, composerStartRestartGroup, 36936, 0);
                    composer2 = composerStartRestartGroup;
                    composer2.endReplaceableGroup();
                    composer2.endReplaceableGroup();
                    mainViewModel2 = (MainViewModel) viewModel;
                    i3 &= -897;
                }
                MainViewModel mainViewModel5 = mainViewModel2;
                i6 = i3;
                mainViewModel4 = mainViewModel5;
                composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-487089746, i6, -1, "org.cipherkey.app.ui.HomeScreen (HomeScreen.kt:42)");
                }
                composer3 = composer2;
                State stateCollectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getContactCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getUnlockedCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composer3.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                context = (Context) objConsume;
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, 58201413, "CC(remember):HomeScreen.kt#9igjgp");
                zChangedInstance = composer3.changedInstance(mainViewModel4);
                homeScreenKt$HomeScreen$1$1RememberedValue = composer3.rememberedValue();
                if (zChangedInstance || homeScreenKt$HomeScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    homeScreenKt$HomeScreen$1$1RememberedValue = new HomeScreenKt$HomeScreen$1$1(mainViewModel4, null);
                    composer3.updateRememberedValue(homeScreenKt$HomeScreen$1$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) homeScreenKt$HomeScreen$1$1RememberedValue, composer3, 6);
                float f = 16;
                Modifier modifierM927paddingVpY3zN4$default = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, i5, null), ScrollKt.rememberScrollState(i4, composer3, i4, i5), false, null, false, 14, null), Dp.m8220constructorimpl(f), 0.0f, 2, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                int i8 = i6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM927paddingVpY3zN4$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, 467452439, "C57@2232L901,84@3172L696,104@3969L41,105@4046L10,103@3944L135,107@4088L1820,151@5951L39,150@5918L510,161@6471L44,160@6438L519,171@6967L41:HomeScreen.kt#r4glq3");
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                float f2 = 12;
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f2));
                Modifier modifierM929paddingqDBjuR0$default = PaddingKt.m929paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m8220constructorimpl(f), 0.0f, 0.0f, 13, null);
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, centerVertically, composer3, 54);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierM929paddingqDBjuR0$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                MainViewModel mainViewModel6 = mainViewModel4;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor2);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, 2135223317, "C67@2621L11,64@2509L197,70@2719L404:HomeScreen.kt#r4glq3");
                IconKt.m2546Iconww6aTOc(IconsKt.getIcShield(), (String) null, SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(32)), MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), composer3, 438, 0);
                ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, companion);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor3);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, -1798968392, "C72@2777L33,73@2854L10,71@2744L154,76@2948L32,78@3062L11,75@2915L194:HomeScreen.kt#r4glq3");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.app_name, composer3, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getHeadlineMedium(), composer3, 0, 0, 131070);
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.tagline, composer3, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 24576, 0, 262122);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_43 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_43, Alignment.INSTANCE.getTop(), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer3, companion2);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor4);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl4 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, -577386052, "C86@3277L59,85@3242L197:HomeScreen.kt#r4glq3");
                ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.home_status_contacts, new Object[]{Integer.valueOf(HomeScreen$lambda$0(stateCollectAsStateWithLifecycle2))}, composer3, 0), HomeScreen$lambda$0(stateCollectAsStateWithLifecycle2) <= 0 ? ChipState.SUCCESS : ChipState.WARNING, null, composer3, 0, 4);
                if (HomeScreen$lambda$1(stateCollectAsStateWithLifecycle) <= 0) {
                    composer3.startReplaceGroup(-577167906);
                    ComposerKt.sourceInformation(composer3, "91@3532L60,90@3493L165");
                    ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.home_status_unlocked, new Object[]{Integer.valueOf(HomeScreen$lambda$1(stateCollectAsStateWithLifecycle))}, composer3, 0), ChipState.SUCCESS, null, composer3, 48, 4);
                    composer3.endReplaceGroup();
                } else {
                    composer3.startReplaceGroup(-576967057);
                    ComposerKt.sourceInformation(composer3, "96@3735L43,95@3696L148");
                    ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.home_status_locked, composer3, 0), ChipState.NEUTRAL, null, composer3, 48, 4);
                    composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.home_quick_title, composer3, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getTitleMedium(), composer3, 0, 0, 131070);
                float f3 = 10;
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_44 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f3));
                ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier.Companion companion3 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_44, Alignment.INSTANCE.getStart(), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap5 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer3, companion3);
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor5);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl5 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl5, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, -686286625, "C108@4160L676,124@4849L1049:HomeScreen.kt#r4glq3");
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_45 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f3));
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion4 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_45, Alignment.INSTANCE.getTop(), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap6 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer3, companion4);
                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor6);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl6 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl6, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, -550108646, "C111@4315L48,109@4235L283,118@4619L48,116@4535L287:HomeScreen.kt#r4glq3");
                ComponentsKt.QuickActionTile(IconsKt.getIcLock(), StringResources_androidKt.stringResource(R.string.home_quick_encrypt_text, composer3, 0), RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null), true, onOpenEncrypt, composer3, ((i8 << 12) & 57344) | 3078, 0);
                ComponentsKt.QuickActionTile(IconsKt.getIcLockOpen(), StringResources_androidKt.stringResource(R.string.home_quick_decrypt_text, composer3, 0), RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null), true, onOpenDecrypt, composer3, ((i8 << 9) & 57344) | 3078, 0);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_46 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f3));
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion5 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_46, Alignment.INSTANCE.getTop(), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer3, companion5);
                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (!composer3.getInserting()) {
                    composer3.createNode(constructor7);
                } else {
                    composer3.useNode();
                }
                Composer composerM4636constructorimpl7 = Updater.m4636constructorimpl(composer3);
                Updater.m4644setimpl(composerM4636constructorimpl7, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer3, 1305827886, "C127@5005L48,129@5137L240,125@4924L472,138@5493L48,140@5625L240,136@5413L471:HomeScreen.kt#r4glq3");
                ImageVector icPaste = IconsKt.getIcPaste();
                String strStringResource = StringResources_androidKt.stringResource(R.string.home_quick_encrypt_clip, composer3, 0);
                Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composer3, 1011960901, "CC(remember):HomeScreen.kt#9igjgp");
                zChangedInstance2 = composer3.changedInstance(context);
                objRememberedValue = composer3.rememberedValue();
                if (!zChangedInstance2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.HomeScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$3$2$1$0$0(context);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComponentsKt.QuickActionTile(icPaste, strStringResource, modifierWeight$default, false, (Function0) objRememberedValue, composer3, 6, 8);
                ImageVector icCopy = IconsKt.getIcCopy();
                String strStringResource2 = StringResources_androidKt.stringResource(R.string.home_quick_decrypt_clip, composer3, 0);
                Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composer3, 1011976517, "CC(remember):HomeScreen.kt#9igjgp");
                zChangedInstance3 = composer3.changedInstance(context);
                objRememberedValue2 = composer3.rememberedValue();
                if (!zChangedInstance3 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.HomeScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$3$2$1$1$0(context);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComponentsKt.QuickActionTile(icCopy, strStringResource2, modifierWeight$default2, false, (Function0) objRememberedValue2, composer3, 6, 8);
                composerStartRestartGroup = composer3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.home_how_title, composerStartRestartGroup, 0), IconsKt.getIcKeyboard(), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1946378923$app_release(), composerStartRestartGroup, 3120, 4);
                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.home_security_title, composerStartRestartGroup, 0), IconsKt.getIcShield(), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$968255778$app_release(), composerStartRestartGroup, 3120, 4);
                SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(f2)), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mainViewModel3 = mainViewModel6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    MainViewModel mainViewModel7 = mainViewModel2;
                    i6 = i3 & (-897);
                    mainViewModel4 = mainViewModel7;
                    composer2 = composerStartRestartGroup;
                    i5 = 1;
                    i4 = 0;
                    composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    composer3 = composer2;
                    State stateCollectAsStateWithLifecycle22 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getContactCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
                    stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getUnlockedCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
                    ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
                    ComposerKt.sourceInformationMarkerStart(composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = composer3.consume(localContext2);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    context = (Context) objConsume2;
                    Unit unit2 = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer3, 58201413, "CC(remember):HomeScreen.kt#9igjgp");
                    zChangedInstance = composer3.changedInstance(mainViewModel4);
                    homeScreenKt$HomeScreen$1$1RememberedValue = composer3.rememberedValue();
                    if (zChangedInstance) {
                        homeScreenKt$HomeScreen$1$1RememberedValue = new HomeScreenKt$HomeScreen$1$1(mainViewModel4, null);
                        composer3.updateRememberedValue(homeScreenKt$HomeScreen$1$1RememberedValue);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) homeScreenKt$HomeScreen$1$1RememberedValue, composer3, 6);
                        float f4 = 16;
                        Modifier modifierM927paddingVpY3zN4$default2 = PaddingKt.m927paddingVpY3zN4$default(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, i5, null), ScrollKt.rememberScrollState(i4, composer3, i4, i5), false, null, false, 14, null), Dp.m8220constructorimpl(f4), 0.0f, 2, null);
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_47 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
                        int i82 = i6;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_47, Alignment.INSTANCE.getStart(), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap8 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer3, modifierM927paddingVpY3zN4$default2);
                        Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl8 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl8, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, 467452439, "C57@2232L901,84@3172L696,104@3969L41,105@4046L10,103@3944L135,107@4088L1820,151@5951L39,150@5918L510,161@6471L44,160@6438L519,171@6967L41:HomeScreen.kt#r4glq3");
                        Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                        float f22 = 12;
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_422 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f22));
                        Modifier modifierM929paddingqDBjuR0$default2 = PaddingKt.m929paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m8220constructorimpl(f4), 0.0f, 0.0f, 13, null);
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_422, centerVertically2, composer3, 54);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap22 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier22 = ComposedModifierKt.materializeModifier(composer3, modifierM929paddingqDBjuR0$default2);
                        Function0<ComposeUiNode> constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                        MainViewModel mainViewModel62 = mainViewModel4;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl22 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl22, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl22, Integer.valueOf(iHashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl22, modifierMaterializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, 2135223317, "C67@2621L11,64@2509L197,70@2719L404:HomeScreen.kt#r4glq3");
                        IconKt.m2546Iconww6aTOc(IconsKt.getIcShield(), (String) null, SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(32)), MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), composer3, 438, 0);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Modifier.Companion companion6 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy22 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap32 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier32 = ComposedModifierKt.materializeModifier(composer3, companion6);
                        Function0<ComposeUiNode> constructor32 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl32 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl32, measurePolicyColumnMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl32, currentCompositionLocalMap32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl32, Integer.valueOf(iHashCode32), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl32, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl32, modifierMaterializeModifier32, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance22 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1798968392, "C72@2777L33,73@2854L10,71@2744L154,76@2948L32,78@3062L11,75@2915L194:HomeScreen.kt#r4glq3");
                        TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.app_name, composer3, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getHeadlineMedium(), composer3, 0, 0, 131070);
                        TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.tagline, composer3, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 24576, 0, 262122);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_432 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        Modifier.Companion companion22 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy22 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_432, Alignment.INSTANCE.getTop(), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode42 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap42 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier42 = ComposedModifierKt.materializeModifier(composer3, companion22);
                        Function0<ComposeUiNode> constructor42 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl42 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl42, measurePolicyRowMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl42, currentCompositionLocalMap42, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl42, Integer.valueOf(iHashCode42), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl42, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl42, modifierMaterializeModifier42, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance22 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -577386052, "C86@3277L59,85@3242L197:HomeScreen.kt#r4glq3");
                        ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.home_status_contacts, new Object[]{Integer.valueOf(HomeScreen$lambda$0(stateCollectAsStateWithLifecycle22))}, composer3, 0), HomeScreen$lambda$0(stateCollectAsStateWithLifecycle22) <= 0 ? ChipState.SUCCESS : ChipState.WARNING, null, composer3, 0, 4);
                        if (HomeScreen$lambda$1(stateCollectAsStateWithLifecycle) <= 0) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.home_quick_title, composer3, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getTitleMedium(), composer3, 0, 0, 131070);
                        float f32 = 10;
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_442 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f32));
                        ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Modifier.Companion companion32 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyColumnMeasurePolicy32 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_442, Alignment.INSTANCE.getStart(), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode52 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap52 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier52 = ComposedModifierKt.materializeModifier(composer3, companion32);
                        Function0<ComposeUiNode> constructor52 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl52 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl52, measurePolicyColumnMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl52, currentCompositionLocalMap52, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl52, Integer.valueOf(iHashCode52), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl52, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl52, modifierMaterializeModifier52, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance32 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -686286625, "C108@4160L676,124@4849L1049:HomeScreen.kt#r4glq3");
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_452 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f32));
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        Modifier.Companion companion42 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy32 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_452, Alignment.INSTANCE.getTop(), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode62 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap62 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier62 = ComposedModifierKt.materializeModifier(composer3, companion42);
                        Function0<ComposeUiNode> constructor62 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl62 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl62, measurePolicyRowMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl62, currentCompositionLocalMap62, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl62, Integer.valueOf(iHashCode62), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl62, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl62, modifierMaterializeModifier62, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance32 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, -550108646, "C111@4315L48,109@4235L283,118@4619L48,116@4535L287:HomeScreen.kt#r4glq3");
                        ComponentsKt.QuickActionTile(IconsKt.getIcLock(), StringResources_androidKt.stringResource(R.string.home_quick_encrypt_text, composer3, 0), RowScope.weight$default(rowScopeInstance32, Modifier.INSTANCE, 1.0f, false, 2, null), true, onOpenEncrypt, composer3, ((i82 << 12) & 57344) | 3078, 0);
                        ComponentsKt.QuickActionTile(IconsKt.getIcLockOpen(), StringResources_androidKt.stringResource(R.string.home_quick_decrypt_text, composer3, 0), RowScope.weight$default(rowScopeInstance32, Modifier.INSTANCE, 1.0f, false, 2, null), true, onOpenDecrypt, composer3, ((i82 << 9) & 57344) | 3078, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_462 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f32));
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        Modifier.Companion companion52 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy42 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_462, Alignment.INSTANCE.getTop(), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode72 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap72 = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier72 = ComposedModifierKt.materializeModifier(composer3, companion52);
                        Function0<ComposeUiNode> constructor72 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (!composer3.getInserting()) {
                        }
                        Composer composerM4636constructorimpl72 = Updater.m4636constructorimpl(composer3);
                        Updater.m4644setimpl(composerM4636constructorimpl72, measurePolicyRowMeasurePolicy42, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4644setimpl(composerM4636constructorimpl72, currentCompositionLocalMap72, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4640initimpl(composerM4636constructorimpl72, Integer.valueOf(iHashCode72), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4642reconcileimpl(composerM4636constructorimpl72, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4644setimpl(composerM4636constructorimpl72, modifierMaterializeModifier72, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance42 = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1305827886, "C127@5005L48,129@5137L240,125@4924L472,138@5493L48,140@5625L240,136@5413L471:HomeScreen.kt#r4glq3");
                        ImageVector icPaste2 = IconsKt.getIcPaste();
                        String strStringResource3 = StringResources_androidKt.stringResource(R.string.home_quick_encrypt_clip, composer3, 0);
                        Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance42, Modifier.INSTANCE, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1011960901, "CC(remember):HomeScreen.kt#9igjgp");
                        zChangedInstance2 = composer3.changedInstance(context);
                        objRememberedValue = composer3.rememberedValue();
                        if (!zChangedInstance2) {
                            objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.HomeScreenKt$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return HomeScreenKt.HomeScreen$lambda$3$2$1$0$0(context);
                                }
                            };
                            composer3.updateRememberedValue(objRememberedValue);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComponentsKt.QuickActionTile(icPaste2, strStringResource3, modifierWeight$default3, false, (Function0) objRememberedValue, composer3, 6, 8);
                            ImageVector icCopy2 = IconsKt.getIcCopy();
                            String strStringResource22 = StringResources_androidKt.stringResource(R.string.home_quick_decrypt_clip, composer3, 0);
                            Modifier modifierWeight$default22 = RowScope.weight$default(rowScopeInstance42, Modifier.INSTANCE, 1.0f, false, 2, null);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1011976517, "CC(remember):HomeScreen.kt#9igjgp");
                            zChangedInstance3 = composer3.changedInstance(context);
                            objRememberedValue2 = composer3.rememberedValue();
                            if (!zChangedInstance3) {
                                objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.HomeScreenKt$$ExternalSyntheticLambda1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return HomeScreenKt.HomeScreen$lambda$3$2$1$1$0(context);
                                    }
                                };
                                composer3.updateRememberedValue(objRememberedValue2);
                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                ComponentsKt.QuickActionTile(icCopy2, strStringResource22, modifierWeight$default22, false, (Function0) objRememberedValue2, composer3, 6, 8);
                                composerStartRestartGroup = composer3;
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                composerStartRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                composerStartRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.home_how_title, composerStartRestartGroup, 0), IconsKt.getIcKeyboard(), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$1946378923$app_release(), composerStartRestartGroup, 3120, 4);
                                ComponentsKt.SectionCard(StringResources_androidKt.stringResource(R.string.home_security_title, composerStartRestartGroup, 0), IconsKt.getIcShield(), null, ComposableSingletons$HomeScreenKt.INSTANCE.getLambda$968255778$app_release(), composerStartRestartGroup, 3120, 4);
                                SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(f22)), composerStartRestartGroup, 6);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                composerStartRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                mainViewModel3 = mainViewModel62;
                            }
                        }
                    }
                }
            }
            composer2 = composerStartRestartGroup;
            i5 = 1;
            i4 = 0;
            MainViewModel mainViewModel52 = mainViewModel2;
            i6 = i3;
            mainViewModel4 = mainViewModel52;
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
            }
            composer3 = composer2;
            State stateCollectAsStateWithLifecycle222 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getContactCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
            stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mainViewModel4.getUnlockedCount(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composer3, 0, 7);
            ProvidableCompositionLocal<Context> localContext22 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume22 = composer3.consume(localContext22);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            context = (Context) objConsume22;
            Unit unit22 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer3, 58201413, "CC(remember):HomeScreen.kt#9igjgp");
            zChangedInstance = composer3.changedInstance(mainViewModel4);
            homeScreenKt$HomeScreen$1$1RememberedValue = composer3.rememberedValue();
            if (zChangedInstance) {
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            mainViewModel3 = mainViewModel2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.HomeScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return HomeScreenKt.HomeScreen$lambda$4(onOpenEncrypt, onOpenDecrypt, mainViewModel3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$3$2$1$0$0(Context context) {
        context.startActivity(new Intent(context, (Class<?>) EncryptActivity.class).putExtra(BaseCompanionActivityKt.EXTRA_FROM_CLIPBOARD, true));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit HomeScreen$lambda$3$2$1$1$0(Context context) {
        context.startActivity(new Intent(context, (Class<?>) DecryptActivity.class).putExtra(BaseCompanionActivityKt.EXTRA_FROM_CLIPBOARD, true));
        return Unit.INSTANCE;
    }

    private static final int HomeScreen$lambda$0(State<Integer> state) {
        return state.getValue().intValue();
    }

    private static final int HomeScreen$lambda$1(State<Integer> state) {
        return state.getValue().intValue();
    }
}
