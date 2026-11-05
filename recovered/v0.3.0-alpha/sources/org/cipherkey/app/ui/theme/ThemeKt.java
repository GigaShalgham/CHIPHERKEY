package org.cipherkey.app.ui.theme;

import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Shapes;
import androidx.compose.material3.Typography;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\b0\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"LightColors", "Landroidx/compose/material3/ColorScheme;", "DarkColors", "CipherKeyTypography", "Landroidx/compose/material3/Typography;", "CipherKeyShapes", "Landroidx/compose/material3/Shapes;", "CipherKeyTheme", "", "darkTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThemeKt {
    private static final Shapes CipherKeyShapes;
    private static final Typography CipherKeyTypography;
    private static final ColorScheme LightColors = ColorSchemeKt.m2225lightColorScheme_VG5OTI$default(CipherKeyColors.INSTANCE.m10627getLightPrimary0d7_KjU(), CipherKeyColors.INSTANCE.m10617getLightOnPrimary0d7_KjU(), CipherKeyColors.INSTANCE.m10628getLightPrimaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10618getLightOnPrimaryContainer0d7_KjU(), 0, CipherKeyColors.INSTANCE.m10629getLightSecondary0d7_KjU(), CipherKeyColors.INSTANCE.m10619getLightOnSecondary0d7_KjU(), CipherKeyColors.INSTANCE.m10630getLightSecondaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10620getLightOnSecondaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10635getLightTertiary0d7_KjU(), CipherKeyColors.INSTANCE.m10623getLightOnTertiary0d7_KjU(), CipherKeyColors.INSTANCE.m10636getLightTertiaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10624getLightOnTertiaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10611getLightBackground0d7_KjU(), CipherKeyColors.INSTANCE.m10614getLightOnBackground0d7_KjU(), CipherKeyColors.INSTANCE.m10631getLightSurface0d7_KjU(), CipherKeyColors.INSTANCE.m10621getLightOnSurface0d7_KjU(), CipherKeyColors.INSTANCE.m10634getLightSurfaceVariant0d7_KjU(), CipherKeyColors.INSTANCE.m10622getLightOnSurfaceVariant0d7_KjU(), 0, 0, 0, CipherKeyColors.INSTANCE.m10612getLightError0d7_KjU(), CipherKeyColors.INSTANCE.m10615getLightOnError0d7_KjU(), CipherKeyColors.INSTANCE.m10613getLightErrorContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10616getLightOnErrorContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10625getLightOutline0d7_KjU(), CipherKeyColors.INSTANCE.m10626getLightOutlineVariant0d7_KjU(), 0, 0, CipherKeyColors.INSTANCE.m10632getLightSurfaceContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10633getLightSurfaceContainerHigh0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 808976400, 65535, null);
    private static final ColorScheme DarkColors = ColorSchemeKt.m2219darkColorScheme_VG5OTI$default(CipherKeyColors.INSTANCE.m10601getDarkPrimary0d7_KjU(), CipherKeyColors.INSTANCE.m10591getDarkOnPrimary0d7_KjU(), CipherKeyColors.INSTANCE.m10602getDarkPrimaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10592getDarkOnPrimaryContainer0d7_KjU(), 0, CipherKeyColors.INSTANCE.m10603getDarkSecondary0d7_KjU(), CipherKeyColors.INSTANCE.m10593getDarkOnSecondary0d7_KjU(), CipherKeyColors.INSTANCE.m10604getDarkSecondaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10594getDarkOnSecondaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10609getDarkTertiary0d7_KjU(), CipherKeyColors.INSTANCE.m10597getDarkOnTertiary0d7_KjU(), CipherKeyColors.INSTANCE.m10610getDarkTertiaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10598getDarkOnTertiaryContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10585getDarkBackground0d7_KjU(), CipherKeyColors.INSTANCE.m10588getDarkOnBackground0d7_KjU(), CipherKeyColors.INSTANCE.m10605getDarkSurface0d7_KjU(), CipherKeyColors.INSTANCE.m10595getDarkOnSurface0d7_KjU(), CipherKeyColors.INSTANCE.m10608getDarkSurfaceVariant0d7_KjU(), CipherKeyColors.INSTANCE.m10596getDarkOnSurfaceVariant0d7_KjU(), 0, 0, 0, CipherKeyColors.INSTANCE.m10586getDarkError0d7_KjU(), CipherKeyColors.INSTANCE.m10589getDarkOnError0d7_KjU(), CipherKeyColors.INSTANCE.m10587getDarkErrorContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10590getDarkOnErrorContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10599getDarkOutline0d7_KjU(), CipherKeyColors.INSTANCE.m10600getDarkOutlineVariant0d7_KjU(), 0, 0, CipherKeyColors.INSTANCE.m10606getDarkSurfaceContainer0d7_KjU(), CipherKeyColors.INSTANCE.m10607getDarkSurfaceContainerHigh0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 808976400, 65535, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyTheme$lambda$0(boolean z, Function2 function2, int i, int i2, Composer composer, int i3) {
        CipherKeyTheme(z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static {
        TextStyle textStyle = null;
        TextStyle textStyle2 = null;
        TextStyle textStyle3 = null;
        TextStyle textStyle4 = null;
        TextStyle textStyle5 = null;
        TextStyle textStyle6 = null;
        TextStyle textStyle7 = null;
        TextStyle textStyle8 = null;
        TextStyle textStyle9 = null;
        TextStyle textStyle10 = null;
        TextStyle textStyleM7676copyp1EtxEg$default = TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, null, null, null, null, textStyle, textStyle2, textStyle3, textStyle4, null, null, textStyle5, textStyle6, textStyle7, 32767, null).getHeadlineMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null);
        TextStyle textStyle11 = null;
        TextStyle textStyleM7676copyp1EtxEg$default2 = TextStyle.m7676copyp1EtxEg$default(new Typography(null, textStyle, textStyle2, textStyle3, textStyle4, null, textStyle11, textStyle5, textStyle6, textStyle7, null, null, textStyle8, textStyle9, textStyle10, 32767, null).getHeadlineSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null);
        TextStyle textStyle12 = null;
        TextStyle textStyle13 = null;
        TextStyle textStyleM7676copyp1EtxEg$default3 = TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, null, null, null, textStyle11, textStyle5, textStyle6, textStyle7, textStyle12, null, textStyle8, textStyle9, textStyle10, textStyle13, 32767, null).getTitleLarge(), 0L, 0L, FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null);
        TextStyle textStyle14 = null;
        TextStyle textStyle15 = null;
        TextStyle textStyleM7676copyp1EtxEg$default4 = TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, null, null, null, textStyle5, textStyle6, textStyle7, textStyle12, textStyle14, null, textStyle9, textStyle10, textStyle13, textStyle15, 32767, null).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null);
        TextStyle textStyle16 = null;
        TextStyle textStyle17 = null;
        TextStyle textStyle18 = null;
        TextStyle textStyle19 = null;
        CipherKeyTypography = new Typography(null, null, null, null, textStyleM7676copyp1EtxEg$default, textStyleM7676copyp1EtxEg$default2, textStyleM7676copyp1EtxEg$default3, textStyleM7676copyp1EtxEg$default4, TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, null, null, null, textStyle6, textStyle7, textStyle12, textStyle14, textStyle16, null, textStyle10, textStyle13, textStyle15, textStyle17, 32767, null).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), null, null, null, TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, null, null, null, textStyle7, textStyle12, textStyle14, textStyle16, textStyle18, null, textStyle13, textStyle15, textStyle17, textStyle19, 32767, null).getLabelLarge(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), TextStyle.m7676copyp1EtxEg$default(new Typography(null, null, textStyle12, textStyle14, textStyle16, textStyle18, null, null, textStyle15, textStyle17, textStyle19, null, null, null, null, 32767, null).getLabelMedium(), 0L, 0L, FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), null, 19983, null);
        CipherKeyShapes = new Shapes(RoundedCornerShapeKt.m1280RoundedCornerShape0680j_4(Dp.m8220constructorimpl(8)), RoundedCornerShapeKt.m1280RoundedCornerShape0680j_4(Dp.m8220constructorimpl(12)), RoundedCornerShapeKt.m1280RoundedCornerShape0680j_4(Dp.m8220constructorimpl(16)), RoundedCornerShapeKt.m1280RoundedCornerShape0680j_4(Dp.m8220constructorimpl(20)), RoundedCornerShapeKt.m1280RoundedCornerShape0680j_4(Dp.m8220constructorimpl(28)));
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void CipherKeyTheme(final boolean z, Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i, final int i2) {
        int i3;
        final Function2<? super Composer, ? super Integer, Unit> function2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-898125035);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CipherKeyTheme)N(darkTheme,content)102@4124L189:Theme.kt#qdlvog");
        if ((i & 6) == 0) {
            i3 = (((i2 & 1) == 0 && composerStartRestartGroup.changed(z)) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(content) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "99@4056L21");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    z = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
                    i3 &= -15;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-898125035, i3, -1, "org.cipherkey.app.ui.theme.CipherKeyTheme (Theme.kt:101)");
                }
                function2 = content;
                MaterialThemeKt.MaterialTheme(!z ? DarkColors : LightColors, CipherKeyShapes, CipherKeyTypography, function2, composerStartRestartGroup, ((i3 << 6) & 7168) | 432, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                if (!z) {
                }
                function2 = content;
                MaterialThemeKt.MaterialTheme(!z ? DarkColors : LightColors, CipherKeyShapes, CipherKeyTypography, function2, composerStartRestartGroup, ((i3 << 6) & 7168) | 432, 0);
                if (ComposerKt.isTraceInProgress()) {
                }
            }
        } else {
            function2 = content;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.theme.ThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.CipherKeyTheme$lambda$0(z, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
