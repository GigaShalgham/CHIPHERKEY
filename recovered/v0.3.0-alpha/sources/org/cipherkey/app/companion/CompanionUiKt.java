package org.cipherkey.app.companion;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.text.selection.SelectionContainerKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.CompanionUiState;
import org.cipherkey.app.ui.ComponentsKt;
import org.cipherkey.app.ui.ContactPickerKt;
import org.cipherkey.app.ui.EncryptScreenKt;
import org.cipherkey.app.ui.IconsKt;
import org.cipherkey.app.ui.theme.ColorKt;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;

/* compiled from: CompanionUi.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0012\u001a«\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\u0011\u001a\u0015\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\u0013\u001aY\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\u0015\u001a7\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\u0017\u001a?\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0003¢\u0006\u0002\u0010\u0019\u001a3\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\bH\u0003¢\u0006\u0002\u0010\u001b¨\u0006\u001c"}, d2 = {"CompanionSheet", "", "state", "Lorg/cipherkey/app/companion/CompanionUiState;", "onContactSelected", "Lkotlin/Function1;", "Lorg/cipherkey/core/common/ContactId;", "onEncrypt", "Lkotlin/Function0;", "onUnlockEncrypt", "", "onUnlockDecrypt", "onCopy", "onInsert", "onClose", "onRetryClipboard", "onBiometricUnlock", "(Lorg/cipherkey/app/companion/CompanionUiState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "SheetHeader", "(Lorg/cipherkey/app/companion/CompanionUiState;Landroidx/compose/runtime/Composer;I)V", "EncryptPrepareCard", "(Lorg/cipherkey/app/companion/CompanionUiState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "UnlockSheetCard", "(Lorg/cipherkey/app/companion/CompanionUiState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "ResultCard", "(Lorg/cipherkey/app/companion/CompanionUiState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "NoticeCard", "(Lorg/cipherkey/app/companion/CompanionUiState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CompanionUiKt {

    /* compiled from: CompanionUi.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[CompanionUiState.Phase.values().length];
            try {
                iArr[CompanionUiState.Phase.Idle.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CompanionUiState.Phase.Prepare.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CompanionUiState.Phase.NeedsUnlock.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CompanionUiState.Phase.Result.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CompanionUiState.Phase.Notice.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TextSource.values().length];
            try {
                iArr2[TextSource.SELECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[TextSource.SHARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[TextSource.CLIPBOARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[TextSource.MANUAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[CompanionMode.values().length];
            try {
                iArr3[CompanionMode.ENCRYPT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[CompanionMode.DECRYPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CompanionSheet$lambda$2(CompanionUiState companionUiState, Function1 function1, Function0 function0, Function1 function12, Function1 function13, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Function0 function06, int i, int i2, Composer composer, int i3) {
        CompanionSheet(companionUiState, function1, function0, function12, function13, function02, function03, function04, function05, function06, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit EncryptPrepareCard$lambda$1(CompanionUiState companionUiState, Function1 function1, Function0 function0, Function1 function12, Function0 function02, int i, Composer composer, int i2) {
        EncryptPrepareCard(companionUiState, function1, function0, function12, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit NoticeCard$lambda$1(CompanionUiState companionUiState, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        NoticeCard(companionUiState, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ResultCard$lambda$1(CompanionUiState companionUiState, Function0 function0, Function0 function02, Function0 function03, int i, Composer composer, int i2) {
        ResultCard(companionUiState, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SheetHeader$lambda$1(CompanionUiState companionUiState, int i, Composer composer, int i2) {
        SheetHeader(companionUiState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit UnlockSheetCard$lambda$1(CompanionUiState companionUiState, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        UnlockSheetCard(companionUiState, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void CompanionSheet(final CompanionUiState state, final Function1<? super ContactId, Unit> onContactSelected, final Function0<Unit> onEncrypt, final Function1<? super char[], Unit> onUnlockEncrypt, final Function1<? super char[], Unit> onUnlockDecrypt, final Function0<Unit> onCopy, final Function0<Unit> onInsert, final Function0<Unit> onClose, Function0<Unit> function0, Function0<Unit> function02, Composer composer, final int i, final int i2) {
        int i3;
        Function0<Unit> function03;
        int i4;
        Composer composer2;
        final Function0<Unit> function04;
        final Function0<Unit> function05;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Function0<Unit> function06;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onContactSelected, "onContactSelected");
        Intrinsics.checkNotNullParameter(onEncrypt, "onEncrypt");
        Intrinsics.checkNotNullParameter(onUnlockEncrypt, "onUnlockEncrypt");
        Intrinsics.checkNotNullParameter(onUnlockDecrypt, "onUnlockDecrypt");
        Intrinsics.checkNotNullParameter(onCopy, "onCopy");
        Intrinsics.checkNotNullParameter(onInsert, "onInsert");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        Composer composerStartRestartGroup = composer.startRestartGroup(1762073354);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CompanionSheet)N(state,onContactSelected,onEncrypt,onUnlockEncrypt,onUnlockDecrypt,onCopy,onInsert,onClose,onRetryClipboard,onBiometricUnlock)75@3226L2,80@3336L11,77@3238L2469:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(state) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onContactSelected) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onEncrypt) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onUnlockEncrypt) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onUnlockDecrypt) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onCopy) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onInsert) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onClose) ? 8388608 : 4194304;
        }
        int i5 = i2 & 256;
        if (i5 == 0) {
            if ((100663296 & i) == 0) {
                function03 = function0;
                i3 |= composerStartRestartGroup.changedInstance(function03) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
            }
            i4 = i2 & 512;
            if (i4 == 0) {
                i3 |= 805306368;
            } else if ((i & 805306368) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function02) ? 536870912 : 268435456;
            }
            if (composerStartRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function04 = function02;
                function05 = function03;
            } else {
                if (i5 != 0) {
                    function03 = null;
                }
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1135390060, "CC(remember):CompanionUi.kt#9igjgp");
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Unit.INSTANCE;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function06 = (Function0) objRememberedValue;
                } else {
                    function06 = function02;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1762073354, i3, -1, "org.cipherkey.app.companion.CompanionSheet (CompanionUi.kt:76)");
                }
                Modifier modifierM296backgroundbw27NRU$default = BackgroundKt.m296backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Color.m5385copywmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getScrim(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM296backgroundbw27NRU$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composerStartRestartGroup);
                Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1359838182, "C87@3588L6,88@3641L11,90@3720L1981,82@3392L2309:CompanionUi.kt#yo7vyd");
                final Function0<Unit> function07 = function03;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m3080SurfaceT9BRK9s(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), 0.0f, 1, null)), MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, MaterialTheme.$stable).getExtraLarge(), MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getSurfaceContainerHigh(), 0L, Dp.m8220constructorimpl(2), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1468617783, true, new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CompanionUiKt.CompanionSheet$lambda$1$0(state, function07, onContactSelected, onEncrypt, onUnlockEncrypt, function06, onUnlockDecrypt, onCopy, onInsert, onClose, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composer2, 12607488, LocationRequestCompat.QUALITY_LOW_POWER);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function05 = function07;
                function04 = function06;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CompanionUiKt.CompanionSheet$lambda$2(state, onContactSelected, onEncrypt, onUnlockEncrypt, onUnlockDecrypt, onCopy, onInsert, onClose, function05, function04, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
                return;
            }
            return;
        }
        i3 |= 100663296;
        function03 = function0;
        i4 = i2 & 512;
        if (i4 == 0) {
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 306783379) == 306783378, i3 & 1)) {
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CompanionSheet$lambda$1$0(CompanionUiState companionUiState, Function0 function0, Function1 function1, Function0 function02, Function1 function12, Function0 function03, Function1 function13, Function0 function04, Function0 function05, Function0 function06, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C94@3850L21,91@3734L1957:CompanionUi.kt#yo7vyd");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1468617783, i, -1, "org.cipherkey.app.companion.CompanionSheet.<anonymous>.<anonymous> (CompanionUi.kt:91)");
            }
            Modifier modifierM925padding3ABfNKs = PaddingKt.m925padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(20));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM925padding3ABfNKs);
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
            Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 550620448, "C98@4009L18,136@5637L40:CompanionUi.kt#yo7vyd");
            SheetHeader(companionUiState, composer, 0);
            int i2 = WhenMappings.$EnumSwitchMapping$0[companionUiState.getPhase().ordinal()];
            if (i2 == 1) {
                composer.startReplaceGroup(1264698066);
                ComposerKt.sourceInformation(composer, "");
                if (function0 != null) {
                    composer.startReplaceGroup(550962904);
                    ComposerKt.sourceInformation(composer, "106@4445L42,106@4429L59");
                    ComponentsKt.BusyRow(StringResources_androidKt.stringResource(R.string.reading_clipboard, composer, 0), null, composer, 0, 2);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(1264702499);
                    composer.endReplaceGroup();
                }
                composer.endReplaceGroup();
            } else if (i2 == 2) {
                composer.startReplaceGroup(1264705676);
                ComposerKt.sourceInformation(composer, "");
                if (companionUiState.getMode() == CompanionMode.ENCRYPT) {
                    composer.startReplaceGroup(551206161);
                    ComposerKt.sourceInformation(composer, "111@4671L282");
                    EncryptPrepareCard(companionUiState, function1, function02, function12, function03, composer, 0);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(551540279);
                    ComposerKt.sourceInformation(composer, "119@5031L35,119@5015L52");
                    ComponentsKt.BusyRow(StringResources_androidKt.stringResource(R.string.decrypting, composer, 0), null, composer, 0, 2);
                    composer.endReplaceGroup();
                }
                composer.endReplaceGroup();
            } else if (i2 == 3) {
                composer.startReplaceGroup(1264723705);
                ComposerKt.sourceInformation(composer, "123@5177L58");
                UnlockSheetCard(companionUiState, function13, function03, composer, 0);
                composer.endReplaceGroup();
            } else if (i2 == 4) {
                composer.startReplaceGroup(1264728075);
                ComposerKt.sourceInformation(composer, "126@5314L44");
                ResultCard(companionUiState, function04, function05, function06, composer, 0);
                composer.endReplaceGroup();
            } else {
                if (i2 != 5) {
                    composer.startReplaceGroup(1264688877);
                    composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composer.startReplaceGroup(1264732011);
                ComposerKt.sourceInformation(composer, "129@5437L44");
                NoticeCard(companionUiState, function06, function0, composer, 0);
                composer.endReplaceGroup();
            }
            UiNotice notice = companionUiState.getNotice();
            if (notice == null) {
                composer.startReplaceGroup(552081290);
            } else {
                composer.startReplaceGroup(552081291);
                ComposerKt.sourceInformation(composer, "*133@5594L6,133@5567L34");
                ComponentsKt.NoticeBanner(EncryptScreenKt.text(notice, composer, 0), null, false, composer, 0, 6);
            }
            composer.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(4)), composer, 6);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void SheetHeader(final CompanionUiState companionUiState, Composer composer, final int i) {
        int i2;
        Composer composer2;
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-823159658);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SheetHeader)N(state)144@5778L1273:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(companionUiState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-823159658, i2, -1, "org.cipherkey.app.companion.SheetHeader (CompanionUi.kt:143)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, centerVertically, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4636constructorimpl = Updater.m4636constructorimpl(composerStartRestartGroup);
            Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1752381504, "C151@6085L11,148@5916L244,154@6169L876:CompanionUi.kt#yo7vyd");
            IconKt.m2546Iconww6aTOc(companionUiState.getMode() == CompanionMode.ENCRYPT ? IconsKt.getIcLock() : IconsKt.getIcLockOpen(), (String) null, SizeKt.m973size3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(26)), MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getPrimary(), composerStartRestartGroup, 432, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
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
            Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -616608090, "C156@6219L166,160@6425L10,155@6190L271,163@6503L396,172@6992L11,162@6474L561:CompanionUi.kt#yo7vyd");
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(companionUiState.getMode() == CompanionMode.ENCRYPT ? R.string.sheet_encrypt : R.string.sheet_decrypt, composerStartRestartGroup, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 0, 0, 131070);
            int i4 = WhenMappings.$EnumSwitchMapping$1[companionUiState.getSource().ordinal()];
            if (i4 == 1) {
                i3 = R.string.sheet_from_selection;
            } else if (i4 == 2) {
                i3 = R.string.sheet_from_share;
            } else if (i4 == 3) {
                i3 = R.string.sheet_from_clipboard;
            } else {
                if (i4 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                i3 = R.string.sheet_from_manual;
            }
            composer2 = composerStartRestartGroup;
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(i3, composerStartRestartGroup, 0) + "  ·  CipherKey", null, MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CompanionUiKt.SheetHeader$lambda$1(companionUiState, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void EncryptPrepareCard(final CompanionUiState companionUiState, final Function1<? super ContactId, Unit> function1, final Function0<Unit> function0, final Function1<? super char[], Unit> function12, final Function0<Unit> function02, Composer composer, final int i) {
        CompanionUiState companionUiState2;
        int i2;
        Function0<Unit> function03;
        Function1<? super char[], Unit> function13;
        int i3;
        int i4;
        int i5;
        String displayName;
        Composer composerStartRestartGroup = composer.startRestartGroup(1807246086);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(EncryptPrepareCard)N(state,onContactSelected,onEncrypt,onUnlockEncrypt,onBiometricUnlock)186@7284L2343:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            companionUiState2 = companionUiState;
            i2 = (composerStartRestartGroup.changedInstance(companionUiState2) ? 4 : 2) | i;
        } else {
            companionUiState2 = companionUiState;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            function03 = function0;
            i2 |= composerStartRestartGroup.changedInstance(function03) ? 256 : 128;
        } else {
            function03 = function0;
        }
        if ((i & 3072) == 0) {
            function13 = function12;
            i2 |= composerStartRestartGroup.changedInstance(function13) ? 2048 : 1024;
        } else {
            function13 = function12;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function02) ? 16384 : 8192;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1807246086, i2, -1, "org.cipherkey.app.companion.EncryptPrepareCard (CompanionUi.kt:185)");
            }
            float f = 12;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
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
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1829089462, "C188@7370L36,190@7472L11,187@7352L159,192@7520L295,208@8046L46,203@7825L278:CompanionUi.kt#yo7vyd");
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.draft_label, composerStartRestartGroup, 0), null, MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, 24576, 0, 262122);
            String incoming = companionUiState2.getIncoming();
            String str = "";
            if (incoming == null) {
                incoming = "";
            }
            TextKt.m3252TextNvy7gAk(incoming, SizeKt.m961heightInVpY3zN4$default(PaddingKt.m925padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8220constructorimpl(f)), 0.0f, Dp.m8220constructorimpl(120), 1, null), 0L, null, TextUnitKt.getSp(15), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8156getEllipsisgIe3tQ8(), false, 4, 0, null, null, composerStartRestartGroup, 24624, 24960, 241644);
            ContactPickerKt.m10551ContactPicker2pW6ks(companionUiState.getContacts(), companionUiState.m10486getSelectedContactIdP5lcXoU(), companionUiState.getUnlocked(), function1, StringResources_androidKt.stringResource(R.string.encrypt_contact_label, composerStartRestartGroup, 0), null, composerStartRestartGroup, (i2 << 6) & 7168, 32);
            composerStartRestartGroup = composerStartRestartGroup;
            if (companionUiState.m10486getSelectedContactIdP5lcXoU() != null && !companionUiState.getSelectedContactUnlocked()) {
                composerStartRestartGroup.startReplaceGroup(1829868553);
                ComposerKt.sourceInformation(composerStartRestartGroup, "223@8639L140,227@8811L46,222@8598L353");
                if (!companionUiState.getBiometricOffered()) {
                    i5 = 1821724946;
                    composerStartRestartGroup.startReplaceGroup(1821724946);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1829897259);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "213@8244L327");
                    i5 = 1821724946;
                    ButtonKt.OutlinedButton(function02, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.getLambda$1956256616$app_release(), composerStartRestartGroup, ((i2 >> 12) & 14) | 805306416, 508);
                    composerStartRestartGroup = composerStartRestartGroup;
                }
                composerStartRestartGroup.endReplaceGroup();
                int i6 = R.string.passphrase_for;
                Contact selectedContact = companionUiState.getSelectedContact();
                if (selectedContact != null && (displayName = selectedContact.getDisplayName()) != null) {
                    str = displayName;
                }
                ComponentsKt.PassphraseInput(StringResources_androidKt.stringResource(i6, new Object[]{str}, composerStartRestartGroup, 0), StringResources_androidKt.stringResource(R.string.unlock_encrypt_action, composerStartRestartGroup, 0), companionUiState.getBusy(), function13, null, composerStartRestartGroup, i2 & 7168, 16);
                composerStartRestartGroup.endReplaceGroup();
                i3 = i5;
                i4 = 0;
            } else {
                i3 = 1821724946;
                composerStartRestartGroup.startReplaceGroup(1830646157);
                ComposerKt.sourceInformation(composerStartRestartGroup, "232@8981L357");
                i4 = 0;
                ButtonKt.Button(function03, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), (companionUiState.m10486getSelectedContactIdP5lcXoU() == null || companionUiState.getBusy()) ? false : true, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.m10499getLambda$804838468$app_release(), composerStartRestartGroup, ((i2 >> 6) & 14) | 805306416, 504);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            if (companionUiState.getSelectedContactUnlocked()) {
                composerStartRestartGroup.startReplaceGroup(1831064006);
                ComposerKt.sourceInformation(composerStartRestartGroup, "245@9429L46,247@9539L21,244@9407L204");
                Composer composer2 = composerStartRestartGroup;
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.session_unlocked_hint, composerStartRestartGroup, i4), null, DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, i4) ? ColorKt.getSuccessGreenDark() : ColorKt.getSuccessGreen(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24576, 0, 262122);
                composerStartRestartGroup = composer2;
            } else {
                composerStartRestartGroup.startReplaceGroup(i3);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CompanionUiKt.EncryptPrepareCard$lambda$1(companionUiState, function1, function0, function12, function02, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void UnlockSheetCard(final CompanionUiState companionUiState, final Function1<? super char[], Unit> function1, Function0<Unit> function0, Composer composer, int i) {
        int i2;
        final int i3;
        final Function0<Unit> function02;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1426301572);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UnlockSheetCard)N(state,onUnlockDecrypt,onBiometricUnlock)259@9786L1056:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(companionUiState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            i3 = i;
            function02 = function0;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1426301572, i2, -1, "org.cipherkey.app.companion.UnlockSheetCard (CompanionUi.kt:258)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
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
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2017573755, "C261@9872L116,260@9854L219,269@10100L36,271@10202L11,268@10082L159,284@10639L41,285@10708L46,283@10602L234:CompanionUi.kt#yo7vyd");
            int i4 = R.string.message_from;
            String pendingContactName = companionUiState.getPendingContactName();
            if (pendingContactName == null) {
                pendingContactName = "";
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(i4, new Object[]{pendingContactName}, composerStartRestartGroup, 0), null, 0L, null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, 1597440, 0, 262062);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.locked_hint, composerStartRestartGroup, 0), null, MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, 24576, 0, 262122);
            if (!companionUiState.getBiometricOffered()) {
                function02 = function0;
                composerStartRestartGroup.startReplaceGroup(2007767152);
            } else {
                composerStartRestartGroup.startReplaceGroup(2017973685);
                ComposerKt.sourceInformation(composerStartRestartGroup, "274@10292L291");
                ButtonKt.Button(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.getLambda$1418606397$app_release(), composerStartRestartGroup, ((i2 >> 6) & 14) | 805306416, 508);
                function02 = function0;
            }
            composerStartRestartGroup.endReplaceGroup();
            i3 = i;
            ComponentsKt.PassphraseInput(StringResources_androidKt.stringResource(R.string.passphrase_label, composerStartRestartGroup, 0), StringResources_androidKt.stringResource(R.string.unlock_decrypt_action, composerStartRestartGroup, 0), companionUiState.getBusy(), function1, null, composerStartRestartGroup, (i2 << 6) & 7168, 16);
            composer2 = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CompanionUiKt.UnlockSheetCard$lambda$1(companionUiState, function1, function02, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void ResultCard(final CompanionUiState companionUiState, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, Composer composer, final int i) {
        int i2;
        Function0<Unit> function04;
        Composer composer2;
        String strStringResource;
        int i3;
        int i4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(1329698127);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ResultCard)N(state,onCopy,onInsert,onClose)299@10994L3179:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(companionUiState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            function04 = function0;
            i2 |= composerStartRestartGroup.changedInstance(function04) ? 32 : 16;
        } else {
            function04 = function0;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function02) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function03) ? 2048 : 1024;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1329698127, i2, -1, "org.cipherkey.app.companion.ResultCard (CompanionUi.kt:298)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
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
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -386268023, "C300@11062L962,326@12053L644,326@12034L663,367@13633L534:CompanionUi.kt#yo7vyd");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, centerVertically, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1558590673, "C307@11330L21,304@11215L236,310@11464L550:CompanionUi.kt#yo7vyd");
            IconKt.m2546Iconww6aTOc(IconsKt.getIcCheck(), (String) null, SizeKt.m973size3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(20)), DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0) ? ColorKt.getSuccessGreenDark() : ColorKt.getSuccessGreen(), composerStartRestartGroup, 438, 0);
            int i6 = WhenMappings.$EnumSwitchMapping$2[companionUiState.getMode().ordinal()];
            if (i6 == 1) {
                composerStartRestartGroup.startReplaceGroup(-1574287448);
                ComposerKt.sourceInformation(composerStartRestartGroup, "312@11558L147");
                int i7 = R.string.result_encrypted_for;
                String resultContactName = companionUiState.getResultContactName();
                strStringResource = StringResources_androidKt.stringResource(i7, new Object[]{resultContactName != null ? resultContactName : ""}, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                if (i6 != 2) {
                    composerStartRestartGroup.startReplaceGroup(-1574289251);
                    composerStartRestartGroup.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composerStartRestartGroup.startReplaceGroup(-1574281271);
                ComposerKt.sourceInformation(composerStartRestartGroup, "316@11751L148");
                int i8 = R.string.result_decrypted_from;
                String resultContactName2 = companionUiState.getResultContactName();
                strStringResource = StringResources_androidKt.stringResource(i8, new Object[]{resultContactName2 != null ? resultContactName2 : ""}, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            TextKt.m3252TextNvy7gAk(strStringResource, null, 0L, null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerStartRestartGroup, 1597440, 0, 262062);
            Composer composer3 = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            SelectionContainerKt.SelectionContainer(null, ComposableLambdaKt.rememberComposableLambda(-185116202, true, new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CompanionUiKt.ResultCard$lambda$0$1(companionUiState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer3, 54), composer3, 48, 1);
            if (companionUiState.getMode() == CompanionMode.ENCRYPT && companionUiState.getInsertable()) {
                composer3.startReplaceGroup(-384652242);
                ComposerKt.sourceInformation(composer3, "343@12804L48,345@12926L11,342@12782L187");
                i3 = 1;
                i4 = -397338775;
                i5 = 0;
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.envelope_truncated_hint, composer3, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 24576, 0, 262122);
                composer3 = composer3;
            } else {
                i3 = 1;
                i4 = -397338775;
                i5 = 0;
                composer3.startReplaceGroup(-397338775);
            }
            composer3.endReplaceGroup();
            if (companionUiState.getCopied()) {
                composer3.startReplaceGroup(-384414720);
                ComposerKt.sourceInformation(composer3, "351@13043L36,353@13150L21,350@13021L201");
                Composer composer4 = composer3;
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.action_copy, composer3, i5) + " ✓", null, DarkThemeKt.isSystemInDarkTheme(composer3, i5) ? ColorKt.getSuccessGreenDark() : ColorKt.getSuccessGreen(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer4, 24576, 0, 262122);
                composer3 = composer4;
            } else {
                composer3.startReplaceGroup(i4);
            }
            composer3.endReplaceGroup();
            if (companionUiState.getInsertable()) {
                composer3.startReplaceGroup(-384025887);
                ComposerKt.sourceInformation(composer3, "360@13413L200");
                Composer composer5 = composer3;
                ButtonKt.Button(function02, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i3, null), !companionUiState.getBusy(), null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.getLambda$1113038966$app_release(), composer5, ((i2 >> 6) & 14) | 805306416, 504);
                composer3 = composer5;
            } else {
                composer3.startReplaceGroup(i4);
            }
            composer3.endReplaceGroup();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_43 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_43, Alignment.INSTANCE.getTop(), composer3, 6);
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, i5));
            CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, companion3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor3);
            } else {
                composer3.useNode();
            }
            Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer3);
            Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer3, -172068992, "C368@13704L281,376@13998L159:CompanionUi.kt#yo7vyd");
            composer2 = composer3;
            ButtonKt.OutlinedButton(function04, RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.m10498getLambda$695312468$app_release(), composer2, ((i2 >> 3) & 14) | 805306368, 508);
            ButtonKt.OutlinedButton(function03, RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.m10496getLambda$435299755$app_release(), composer2, ((i2 >> 9) & 14) | 805306368, 508);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CompanionUiKt.ResultCard$lambda$1(companionUiState, function0, function02, function03, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ResultCard$lambda$0$1(CompanionUiState companionUiState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C336@12614L21,327@12067L620:CompanionUi.kt#yo7vyd");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-185116202, i, -1, "org.cipherkey.app.companion.ResultCard.<anonymous>.<anonymous> (CompanionUi.kt:327)");
            }
            String resultText = companionUiState.getResultText();
            if (resultText == null) {
                resultText = "";
            }
            long sp = companionUiState.getMode() == CompanionMode.ENCRYPT ? TextUnitKt.getSp(12) : TextUnitKt.getSp(15);
            String str = resultText;
            TextKt.m3252TextNvy7gAk(str, PaddingKt.m925padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.m961heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m8220constructorimpl(320), 1, null), ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null), Dp.m8220constructorimpl(12)), 0L, null, sp, null, null, companionUiState.getMode() == CompanionMode.ENCRYPT ? FontFamily.INSTANCE.getMonospace() : FontFamily.INSTANCE.getDefault(), 0L, null, null, 0L, TextOverflow.INSTANCE.m8156getEllipsisgIe3tQ8(), false, companionUiState.getMode() == CompanionMode.ENCRYPT ? 6 : Integer.MAX_VALUE, 0, null, null, composer, 0, 384, 241516);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void NoticeCard(final CompanionUiState companionUiState, Function0<Unit> function0, Function0<Unit> function02, Composer composer, final int i) {
        int i2;
        final Function0<Unit> function03;
        Object obj;
        Object obj2;
        int i3;
        int i4;
        float f;
        final Function0<Unit> function04 = function0;
        Composer composerStartRestartGroup = composer.startRestartGroup(1333978326);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(NoticeCard)N(state,onClose,onRetryClipboard)390@14312L612:CompanionUi.kt#yo7vyd");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(companionUiState) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function04) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function02) ? 256 : 128;
        }
        int i5 = i2;
        if (!composerStartRestartGroup.shouldExecute((i5 & 147) != 146, i5 & 1)) {
            function03 = function02;
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1333978326, i5, -1, "org.cipherkey.app.companion.NoticeCard (CompanionUi.kt:389)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(14));
            Modifier modifierM927paddingVpY3zN4$default = PaddingKt.m927paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m8220constructorimpl(12), 1, null);
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
            if (composerStartRestartGroup.getInserting()) {
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
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 911323087, "C403@14796L122:CompanionUi.kt#yo7vyd");
            UiNotice notice = companionUiState.getNotice();
            if (notice != null) {
                composerStartRestartGroup.startReplaceGroup(911363944);
                ComposerKt.sourceInformation(composerStartRestartGroup, "396@14545L6,396@14518L34");
                String strText = EncryptScreenKt.text(notice, composerStartRestartGroup, 0);
                obj = null;
                ComponentsKt.NoticeBanner(strText, null, false, composerStartRestartGroup, 0, 6);
            } else {
                obj = null;
                composerStartRestartGroup.startReplaceGroup(896973186);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (!companionUiState.getRetryable() || function02 == null) {
                obj2 = obj;
                i3 = i5;
                i4 = 1;
                f = 0.0f;
                function03 = function02;
                composerStartRestartGroup.startReplaceGroup(896973186);
            } else {
                composerStartRestartGroup.startReplaceGroup(911482395);
                ComposerKt.sourceInformation(composerStartRestartGroup, "399@14634L143");
                i3 = i5;
                obj2 = obj;
                i4 = 1;
                f = 0.0f;
                ButtonKt.Button(function02, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, obj), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.m10497getLambda$464547682$app_release(), composerStartRestartGroup, ((i5 >> 6) & 14) | 805306416, 508);
                function03 = function02;
                composerStartRestartGroup = composerStartRestartGroup;
            }
            composerStartRestartGroup.endReplaceGroup();
            function04 = function0;
            ButtonKt.Button(function04, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, i4, obj2), false, null, null, null, null, null, null, ComposableSingletons$CompanionUiKt.INSTANCE.getLambda$744928688$app_release(), composerStartRestartGroup, ((i3 >> 3) & 14) | 805306416, 508);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.companion.CompanionUiKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return CompanionUiKt.NoticeCard$lambda$1(companionUiState, function04, function03, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }
}
