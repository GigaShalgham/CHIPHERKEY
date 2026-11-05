package org.cipherkey.app.ui;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.NavigationBarKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.PointerIconCompat;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.PopUpToBuilder;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.cipherkey.app.R;

/* compiled from: CipherKeyApp.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a\r\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0005\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006²\u0006\f\u0010\u0007\u001a\u0004\u0018\u00010\bX\u008a\u0084\u0002"}, d2 = {"destinations", "", "Lorg/cipherkey/app/ui/Destination;", "CipherKeyApp", "", "(Landroidx/compose/runtime/Composer;I)V", "app_release", "backStackEntry", "Landroidx/navigation/NavBackStackEntry;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CipherKeyAppKt {
    private static final List<Destination> destinations = CollectionsKt.listOf((Object[]) new Destination[]{new Destination("home", R.string.nav_home, IconsKt.getIcHome()), new Destination("encrypt", R.string.nav_encrypt, IconsKt.getIcLock()), new Destination("decrypt", R.string.nav_decrypt, IconsKt.getIcLockOpen()), new Destination("contacts", R.string.nav_contacts, IconsKt.getIcPeople()), new Destination("settings", R.string.nav_settings, IconsKt.getIcSettings())});

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$3(int i, Composer composer, int i2) {
        CipherKeyApp(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void CipherKeyApp(Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-114769069);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CipherKeyApp)37@1493L23,38@1557L30,41@1623L1147,68@2778L652,40@1593L1837:CipherKeyApp.kt#r4glq3");
        if (!composerStartRestartGroup.shouldExecute(i != 0, i & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-114769069, i, -1, "org.cipherkey.app.ui.CipherKeyApp (CipherKeyApp.kt:36)");
            }
            final NavHostController navHostControllerRememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], composerStartRestartGroup, 0);
            final State<NavBackStackEntry> stateCurrentBackStackEntryAsState = NavHostControllerKt.currentBackStackEntryAsState(navHostControllerRememberNavController, composerStartRestartGroup, 0);
            ScaffoldKt.m2869ScaffoldTvnljyQ(null, null, ComposableLambdaKt.rememberComposableLambda(717899374, true, new Function2() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CipherKeyAppKt.CipherKeyApp$lambda$1(navHostControllerRememberNavController, stateCurrentBackStackEntryAsState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1706867044, true, new Function3() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CipherKeyAppKt.CipherKeyApp$lambda$2(navHostControllerRememberNavController, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306752, 507);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CipherKeyAppKt.CipherKeyApp$lambda$3(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1(final NavHostController navHostController, final State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C42@1651L1109,42@1637L1123:CipherKeyApp.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(717899374, i, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous> (CipherKeyApp.kt:42)");
            }
            NavigationBarKt.m2688NavigationBarHsRjFd4(null, 0L, 0L, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(162474279, true, new Function3() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CipherKeyAppKt.CipherKeyApp$lambda$1$0(navHostController, state, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 31);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0(final NavHostController navHostController, State state, RowScope rowScope, Composer composer, int i) {
        boolean z;
        NavDestination destination;
        Sequence<NavDestination> hierarchy;
        RowScope NavigationBar = rowScope;
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(NavigationBar, "$this$NavigationBar");
        ComposerKt.sourceInformation(composer2, "C*48@1971L337,55@2341L214,61@2589L116,46@1873L855:CipherKeyApp.kt#r4glq3");
        int i2 = (i & 6) == 0 ? i | (composer2.changed(NavigationBar) ? 4 : 2) : i;
        boolean z2 = true;
        if (!composer2.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(162474279, i2, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous>.<anonymous> (CipherKeyApp.kt:43)");
            }
            for (final Destination destination2 : destinations) {
                NavBackStackEntry navBackStackEntryCipherKeyApp$lambda$0 = CipherKeyApp$lambda$0(state);
                if (navBackStackEntryCipherKeyApp$lambda$0 == null || (destination = navBackStackEntryCipherKeyApp$lambda$0.getDestination()) == null || (hierarchy = NavDestination.INSTANCE.getHierarchy(destination)) == null) {
                    z = false;
                } else {
                    Iterator<NavDestination> it = hierarchy.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(it.next().getRoute(), destination2.getRoute())) {
                            z = z2;
                            break;
                        }
                    }
                    z = false;
                }
                ComposerKt.sourceInformationMarkerStart(composer2, 926079617, "CC(remember):CipherKeyApp.kt#9igjgp");
                boolean zChangedInstance = composer2.changedInstance(navHostController) | composer2.changed(destination2);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CipherKeyAppKt.CipherKeyApp$lambda$1$0$0$1$0(navHostController, destination2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                NavigationBarKt.NavigationBarItem(NavigationBar, z, (Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(86625355, z2, new Function2() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CipherKeyAppKt.CipherKeyApp$lambda$1$0$0$2(destination2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), null, false, ComposableLambdaKt.rememberComposableLambda(1871207822, z2, new Function2() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CipherKeyAppKt.CipherKeyApp$lambda$1$0$0$3(destination2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), false, null, null, composer2, (i2 & 14) | 1575936, 472);
                NavigationBar = rowScope;
                composer2 = composer;
                z2 = z2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0$0$1$0(final NavHostController navHostController, Destination destination) {
        navHostController.navigate(destination.getRoute(), new Function1() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CipherKeyAppKt.CipherKeyApp$lambda$1$0$0$1$0$0(navHostController, (NavOptionsBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0$0$1$0$0(NavHostController navHostController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        navigate.popUpTo(navHostController.getGraph().getStartDestinationId(), new Function1() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CipherKeyAppKt.CipherKeyApp$lambda$1$0$0$1$0$0$0((PopUpToBuilder) obj);
            }
        });
        navigate.setLaunchSingleTop(true);
        navigate.setRestoreState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0$0$1$0$0$0(PopUpToBuilder popUpTo) {
        Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
        popUpTo.setSaveState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0$0$2(Destination destination, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C56@2371L158:CipherKeyApp.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(86625355, i, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CipherKeyApp.kt:56)");
            }
            IconKt.m2546Iconww6aTOc(destination.getIcon(), (String) null, (Modifier) null, 0L, composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$1$0$0$3(Destination destination, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C62@2624L36,62@2619L60:CipherKeyApp.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1871207822, i, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CipherKeyApp.kt:62)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(destination.getLabelRes(), composer, 0), null, 0L, null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$2(final NavHostController navHostController, PaddingValues padding, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(padding, "padding");
        ComposerKt.sourceInformation(composer, "CN(padding)73@2950L474,69@2799L625:CipherKeyApp.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(padding) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1706867044, i2, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous> (CipherKeyApp.kt:69)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, padding);
            ComposerKt.sourceInformationMarkerStart(composer, -378271810, "CC(remember):CipherKeyApp.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(navHostController);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CipherKeyAppKt.CipherKeyApp$lambda$2$0$0(navHostController, (NavGraphBuilder) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            NavHostKt.NavHost(navHostController, "home", modifierPadding, null, null, null, null, null, null, null, (Function1) objRememberedValue, composer, 48, 0, PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$2$0$0(final NavHostController navHostController, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, "home", (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableLambdaKt.composableLambdaInstance(-1594268601, true, new Function4() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return CipherKeyAppKt.CipherKeyApp$lambda$2$0$0$0(navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, "encrypt", (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$CipherKeyAppKt.INSTANCE.m10514getLambda$235514242$app_release(), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, "decrypt", (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$CipherKeyAppKt.INSTANCE.getLambda$343251903$app_release(), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, "contacts", (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$CipherKeyAppKt.INSTANCE.getLambda$922018048$app_release(), 254, (Object) null);
        NavGraphBuilderKt.composable$default(NavHost, "settings", (List) null, (List) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) null, ComposableSingletons$CipherKeyAppKt.INSTANCE.getLambda$1500784193$app_release(), 254, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$2$0$0$0(final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "CN(it)76@3049L37,77@3124L37,75@3001L179:CipherKeyApp.kt#r4glq3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1594268601, i, -1, "org.cipherkey.app.ui.CipherKeyApp.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CipherKeyApp.kt:75)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1211201260, "CC(remember):CipherKeyApp.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CipherKeyAppKt.CipherKeyApp$lambda$2$0$0$0$0$0(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1211203660, "CC(remember):CipherKeyApp.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.CipherKeyAppKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CipherKeyAppKt.CipherKeyApp$lambda$2$0$0$0$1$0(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        HomeScreenKt.HomeScreen(function0, (Function0) objRememberedValue2, null, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$2$0$0$0$0$0(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "encrypt", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CipherKeyApp$lambda$2$0$0$0$1$0(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "decrypt", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    private static final NavBackStackEntry CipherKeyApp$lambda$0(State<NavBackStackEntry> state) {
        return state.getValue();
    }
}
