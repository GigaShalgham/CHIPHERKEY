package org.cipherkey.app.ui;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.navigation.NavBackStackEntry;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CipherKeyApp.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$CipherKeyAppKt {
    public static final ComposableSingletons$CipherKeyAppKt INSTANCE = new ComposableSingletons$CipherKeyAppKt();

    /* renamed from: lambda$-235514242, reason: not valid java name */
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> f149lambda$235514242 = ComposableLambdaKt.composableLambdaInstance(-235514242, false, new Function4() { // from class: org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$CipherKeyAppKt.lambda__235514242$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> lambda$343251903 = ComposableLambdaKt.composableLambdaInstance(343251903, false, new Function4() { // from class: org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$CipherKeyAppKt.lambda_343251903$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> lambda$922018048 = ComposableLambdaKt.composableLambdaInstance(922018048, false, new Function4() { // from class: org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$CipherKeyAppKt.lambda_922018048$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> lambda$1500784193 = ComposableLambdaKt.composableLambdaInstance(1500784193, false, new Function4() { // from class: org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$CipherKeyAppKt.lambda_1500784193$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });

    /* renamed from: getLambda$-235514242$app_release, reason: not valid java name */
    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> m10514getLambda$235514242$app_release() {
        return f149lambda$235514242;
    }

    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> getLambda$1500784193$app_release() {
        return lambda$1500784193;
    }

    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> getLambda$343251903$app_release() {
        return lambda$343251903;
    }

    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> getLambda$922018048$app_release() {
        return lambda$922018048;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__235514242$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)80@3231L15:CipherKeyApp.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-235514242, i, -1, "org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt.lambda$-235514242.<anonymous> (CipherKeyApp.kt:80)");
        }
        EncryptScreenKt.EncryptScreen(null, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_343251903$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)81@3285L15:CipherKeyApp.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(343251903, i, -1, "org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt.lambda$343251903.<anonymous> (CipherKeyApp.kt:81)");
        }
        DecryptScreenKt.DecryptScreen(null, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_922018048$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)82@3340L16:CipherKeyApp.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(922018048, i, -1, "org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt.lambda$922018048.<anonymous> (CipherKeyApp.kt:82)");
        }
        ContactsScreenKt.ContactsScreen(null, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1500784193$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)83@3396L16:CipherKeyApp.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1500784193, i, -1, "org.cipherkey.app.ui.ComposableSingletons$CipherKeyAppKt.lambda$1500784193.<anonymous> (CipherKeyApp.kt:83)");
        }
        SettingsScreenKt.SettingsScreen(null, composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
