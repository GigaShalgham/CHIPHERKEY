package org.cipherkey.app.ui;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.R;

/* compiled from: DecryptScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$DecryptScreenKt {
    public static final ComposableSingletons$DecryptScreenKt INSTANCE = new ComposableSingletons$DecryptScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$653696350 = ComposableLambdaKt.composableLambdaInstance(653696350, false, new Function2() { // from class: org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DecryptScreenKt.lambda_653696350$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1355630943 = ComposableLambdaKt.composableLambdaInstance(1355630943, false, new Function2() { // from class: org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$DecryptScreenKt.lambda_1355630943$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$908107682 = ComposableLambdaKt.composableLambdaInstance(908107682, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$DecryptScreenKt.lambda_908107682$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: lambda$-528953289, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f162lambda$528953289 = ComposableLambdaKt.composableLambdaInstance(-528953289, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$DecryptScreenKt.lambda__528953289$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: lambda$-304091891, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f161lambda$304091891 = ComposableLambdaKt.composableLambdaInstance(-304091891, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$DecryptScreenKt.lambda__304091891$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: getLambda$-304091891$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m10531getLambda$304091891$app_release() {
        return f161lambda$304091891;
    }

    /* renamed from: getLambda$-528953289$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m10532getLambda$528953289$app_release() {
        return f162lambda$528953289;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1355630943$app_release() {
        return lambda$1355630943;
    }

    public final Function2<Composer, Integer, Unit> getLambda$653696350$app_release() {
        return lambda$653696350;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$908107682$app_release() {
        return lambda$908107682;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_653696350$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C81@3192L44,81@3187L50:DecryptScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(653696350, i, -1, "org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt.lambda$653696350.<anonymous> (DecryptScreen.kt:81)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.decrypt_input_label, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1355630943$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C82@3274L43,82@3269L49:DecryptScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1355630943, i, -1, "org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt.lambda$1355630943.<anonymous> (DecryptScreen.kt:82)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.decrypt_input_hint, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_908107682$lambda$0(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C100@3936L54,101@4007L10,102@4039L37,102@4034L43:DecryptScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(908107682, i, -1, "org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt.lambda$908107682.<anonymous> (DecryptScreen.kt:100)");
            }
            IconKt.m2546Iconww6aTOc(IconsKt.getIcPaste(), (String) null, SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(18)), 0L, composer, 438, 8);
            TextKt.m3252TextNvy7gAk("  ", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 262142);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.action_paste, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__528953289$lambda$0(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C111@4353L37,111@4348L43:DecryptScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-528953289, i, -1, "org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt.lambda$-528953289.<anonymous> (DecryptScreen.kt:111)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.action_clear, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__304091891$lambda$0(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C133@5223L57,134@5297L10,135@5329L39,135@5324L45:DecryptScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-304091891, i, -1, "org.cipherkey.app.ui.ComposableSingletons$DecryptScreenKt.lambda$-304091891.<anonymous> (DecryptScreen.kt:133)");
            }
            IconKt.m2546Iconww6aTOc(IconsKt.getIcLockOpen(), (String) null, SizeKt.m959height3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(18)), 0L, composer, 438, 8);
            TextKt.m3252TextNvy7gAk("  ", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 262142);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.decrypt_action, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
