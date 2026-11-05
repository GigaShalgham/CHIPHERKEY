package org.cipherkey.app;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.cipherkey.app.ui.CipherKeyAppKt;
import org.cipherkey.app.ui.theme.ThemeKt;

/* compiled from: MainActivity.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();

    /* renamed from: lambda$-181912104, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f136lambda$181912104 = ComposableLambdaKt.composableLambdaInstance(-181912104, false, new Function2() { // from class: org.cipherkey.app.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__181912104$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1341940379 = ComposableLambdaKt.composableLambdaInstance(1341940379, false, new Function2() { // from class: org.cipherkey.app.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1341940379$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: getLambda$-181912104$app_release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m10450getLambda$181912104$app_release() {
        return f136lambda$181912104;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1341940379$app_release() {
        return lambda$1341940379;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda_1341940379$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C34@1243L61:MainActivity.kt#zawnab");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1341940379, i, -1, "org.cipherkey.app.ComposableSingletons$MainActivityKt.lambda$1341940379.<anonymous> (MainActivity.kt:34)");
            }
            ThemeKt.CipherKeyTheme(false, f136lambda$181912104, composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__181912104$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C35@1276L14:MainActivity.kt#zawnab");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-181912104, i, -1, "org.cipherkey.app.ComposableSingletons$MainActivityKt.lambda$-181912104.<anonymous> (MainActivity.kt:35)");
            }
            CipherKeyAppKt.CipherKeyApp(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
