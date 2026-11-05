package org.cipherkey.app.security;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BiometricPromptHost.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0006\u001a\u001a\b\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00072\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"BiometricPromptHost", "", "prompt", "Lorg/cipherkey/app/security/BiometricPromptRequest;", "helper", "Lorg/cipherkey/app/security/BiometricPromptHelper;", "consumeRememberKey", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "onUnlockResult", "onRememberDone", "", "(Lorg/cipherkey/app/security/BiometricPromptRequest;Lorg/cipherkey/app/security/BiometricPromptHelper;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BiometricPromptHostKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BiometricPromptHost$lambda$1(BiometricPromptRequest biometricPromptRequest, BiometricPromptHelper biometricPromptHelper, Function1 function1, Function1 function12, Function1 function13, int i, Composer composer, int i2) {
        BiometricPromptHost(biometricPromptRequest, biometricPromptHelper, function1, function12, function13, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void BiometricPromptHost(final BiometricPromptRequest prompt, final BiometricPromptHelper helper, final Function1<? super Continuation<? super byte[]>, ? extends Object> consumeRememberKey, final Function1<? super byte[], Unit> onUnlockResult, final Function1<? super Boolean, Unit> onRememberDone, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(prompt, "prompt");
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(consumeRememberKey, "consumeRememberKey");
        Intrinsics.checkNotNullParameter(onUnlockResult, "onUnlockResult");
        Intrinsics.checkNotNullParameter(onRememberDone, "onRememberDone");
        Composer composerStartRestartGroup = composer.startRestartGroup(393686664);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BiometricPromptHost)N(prompt,helper,consumeRememberKey,onUnlockResult,onRememberDone)22@769L533,22@746L556:BiometricPromptHost.kt#66035d");
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(prompt) : composerStartRestartGroup.changedInstance(prompt) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(helper) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(consumeRememberKey) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onUnlockResult) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onRememberDone) ? 16384 : 8192;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(393686664, i2, -1, "org.cipherkey.app.security.BiometricPromptHost (BiometricPromptHost.kt:21)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 375770845, "CC(remember):BiometricPromptHost.kt#9igjgp");
            int i3 = i2 & 14;
            boolean zChangedInstance = (i3 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(prompt))) | composerStartRestartGroup.changedInstance(helper) | ((i2 & 7168) == 2048) | composerStartRestartGroup.changedInstance(consumeRememberKey) | ((i2 & 57344) == 16384);
            BiometricPromptHostKt$BiometricPromptHost$1$1 biometricPromptHostKt$BiometricPromptHost$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || biometricPromptHostKt$BiometricPromptHost$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                biometricPromptHostKt$BiometricPromptHost$1$1RememberedValue = new BiometricPromptHostKt$BiometricPromptHost$1$1(prompt, helper, consumeRememberKey, onRememberDone, onUnlockResult, null);
                composerStartRestartGroup.updateRememberedValue(biometricPromptHostKt$BiometricPromptHost$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(prompt, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) biometricPromptHostKt$BiometricPromptHost$1$1RememberedValue, composerStartRestartGroup, i3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.security.BiometricPromptHostKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BiometricPromptHostKt.BiometricPromptHost$lambda$1(prompt, helper, consumeRememberKey, onUnlockResult, onRememberDone, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
