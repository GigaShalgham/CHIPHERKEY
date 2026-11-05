package org.cipherkey.app.ui;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.R;

/* compiled from: SettingsScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$SettingsScreenKt {
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$876884723 = ComposableLambdaKt.composableLambdaInstance(876884723, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$SettingsScreenKt.lambda_876884723$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$787672388 = ComposableLambdaKt.composableLambdaInstance(787672388, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$SettingsScreenKt.lambda_787672388$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$270673438 = ComposableLambdaKt.composableLambdaInstance(270673438, false, new Function2() { // from class: org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SettingsScreenKt.lambda_270673438$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$270673438$app_release() {
        return lambda$270673438;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$787672388$app_release() {
        return lambda$787672388;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$876884723$app_release() {
        return lambda$876884723;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_876884723$lambda$0(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C175@7357L53,176@7427L10,177@7459L33,177@7454L39:SettingsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(876884723, i, -1, "org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt.lambda$876884723.<anonymous> (SettingsScreen.kt:175)");
            }
            IconKt.m2546Iconww6aTOc(IconsKt.getIcLock(), (String) null, SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(18)), 0L, composer, 438, 8);
            TextKt.m3252TextNvy7gAk("  ", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 262142);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.lock_now, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_787672388$lambda$0(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C224@9477L57,224@9472L63:SettingsScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(787672388, i, -1, "org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt.lambda$787672388.<anonymous> (SettingsScreen.kt:224)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_assistant_open_settings, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_270673438$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C271@11318L62,271@11313L86,272@11417L45,272@11412L69,274@11516L45,276@11635L11,273@11494L184:SettingsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(270673438, i, -1, "org.cipherkey.app.ui.ComposableSingletons$SettingsScreenKt.lambda$270673438.<anonymous> (SettingsScreen.kt:271)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_about_version, new Object[]{"0.3.0-alpha"}, composer, 0), null, 0L, null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_about_line2, composer, 0), null, 0L, null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.settings_about_line3, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
