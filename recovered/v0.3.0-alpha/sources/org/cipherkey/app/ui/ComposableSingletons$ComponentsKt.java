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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.R;

/* compiled from: Components.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ComposableSingletons$ComponentsKt {
    public static final ComposableSingletons$ComponentsKt INSTANCE = new ComposableSingletons$ComponentsKt();

    /* renamed from: lambda$-1974439287, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f150lambda$1974439287 = ComposableLambdaKt.composableLambdaInstance(-1974439287, false, new Function3() { // from class: org.cipherkey.app.ui.ComposableSingletons$ComponentsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ComponentsKt.lambda__1974439287$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: getLambda$-1974439287$app_release, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m10515getLambda$1974439287$app_release() {
        return f150lambda$1974439287;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lambda__1974439287$lambda$0(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C231@8161L51,232@8229L10,233@8285L54,233@8256L84:Components.kt#r4glq3");
        if (!composer.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1974439287, i, -1, "org.cipherkey.app.ui.ComposableSingletons$ComponentsKt.lambda$-1974439287.<anonymous> (Components.kt:231)");
            }
            IconKt.m2546Iconww6aTOc(IconsKt.getIcCopy(), (String) null, SizeKt.m973size3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(16)), 0L, composer, 438, 8);
            TextKt.m3252TextNvy7gAk("  ", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 6, 0, 262142);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.action_copy, composer, 0), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
