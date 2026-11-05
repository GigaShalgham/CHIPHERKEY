package org.cipherkey.app.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.selection.SelectionContainerKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.CheckboxKt;
import androidx.compose.material3.FloatingActionButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextOverflow;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.pqc.crypto.sqisign.PrecompLvl1;
import org.cipherkey.app.ContactsViewModel;
import org.cipherkey.app.R;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;
import org.cipherkey.data.repository.ContactSession;
import org.cipherkey.data.repository.UnlockOutcome;
import org.cipherkey.security.policies.Argon2Profile;

/* compiled from: ContactsScreen.kt */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a3\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\u000e\u001aO\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\r22\u0010\u0011\u001a.\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0015\u0012\u0004\u0012\u00020\u00010\u0012H\u0003¢\u0006\u0002\u0010\u0016\u001a\u0085\u0001\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\b\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00132$\u0010\u001c\u001a \u0012\u0004\u0012\u00020\u001e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0015\u0012\u0004\u0012\u00020\u00010\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0003¢\u0006\u0002\u0010\"¨\u0006#²\u0006\u0010\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019X\u008a\u0084\u0002²\u0006\u0010\u0010\b\u001a\b\u0012\u0004\u0012\u00020&0%X\u008a\u0084\u0002²\u0006\u001c\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190'X\u008a\u0084\u0002²\u0006\f\u0010(\u001a\u0004\u0018\u00010)X\u008a\u0084\u0002²\u0006\u0016\u0010*\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00130'X\u008a\u0084\u0002²\u0006\n\u0010+\u001a\u00020\tX\u008a\u008e\u0002²\u0006\f\u0010,\u001a\u0004\u0018\u00010\u0007X\u008a\u008e\u0002²\u0006\n\u0010-\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010/\u001a\u00020\u0014X\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u00101\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\f\u00102\u001a\u0004\u0018\u00010\u0013X\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u00103\u001a\u00020\tX\u008a\u008e\u0002"}, d2 = {"ContactsScreen", "", "viewModel", "Lorg/cipherkey/app/ContactsViewModel;", "(Lorg/cipherkey/app/ContactsViewModel;Landroidx/compose/runtime/Composer;II)V", "ContactCard", "contact", "Lorg/cipherkey/data/repository/Contact;", "unlocked", "", "eraCount", "", "onClick", "Lkotlin/Function0;", "(Lorg/cipherkey/data/repository/Contact;ZILkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "AddContactDialog", "onDismiss", "onCreate", "Lkotlin/Function4;", "", "Lorg/cipherkey/security/policies/Argon2Profile;", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "ContactDetailDialog", "eras", "", "Lorg/cipherkey/data/repository/ContactSession;", "fingerprint", "onUnlock", "Lkotlin/Function2;", "", "Lorg/cipherkey/data/repository/UnlockOutcome;", "onLock", "onDelete", "(Lorg/cipherkey/data/repository/Contact;Ljava/util/List;ZLjava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "contacts", "", "Lorg/cipherkey/core/common/ContactId;", "", "feedback", "Lorg/cipherkey/app/companion/UiNotice;", "fingerprints", "showAddDialog", "detailContact", HintConstants.AUTOFILL_HINT_NAME, "invite", "profile", "busy", "passphrase", "unlockMessage", "confirmDelete"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactsScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$16(Function0 function0, Function4 function4, int i, Composer composer, int i2) {
        AddContactDialog(function0, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactCard$lambda$1(Contact contact, boolean z, int i, Function0 function0, int i2, Composer composer, int i3) {
        ContactCard(contact, z, i, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$15(Contact contact, List list, boolean z, String str, Function2 function2, Function0 function0, Function0 function02, Function0 function03, int i, Composer composer, int i2) {
        ContactDetailDialog(contact, list, z, str, function2, function0, function02, function03, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$17(ContactsViewModel contactsViewModel, int i, int i2, Composer composer, int i3) {
        ContactsScreen(contactsViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0342  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void ContactsScreen(ContactsViewModel contactsViewModel, Composer composer, final int i, final int i2) {
        ContactsViewModel contactsViewModel2;
        int i3;
        final ContactsViewModel contactsViewModel3;
        CreationExtras.Empty defaultViewModelCreationExtras;
        final ContactsViewModel contactsViewModel4;
        Composer composer2;
        Object objRememberedValue;
        final MutableState mutableState;
        Object objRememberedValue2;
        boolean zChangedInstance;
        Object objRememberedValue3;
        final Contact contactContactsScreen$lambda$9;
        final MutableState mutableState2;
        final MutableState mutableState3;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(277125624);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContactsScreen)N(viewModel)62@2761L29,63@2830L29,64@2891L29,65@2960L29,66@3037L29,68@3093L34,69@3153L43,71@3252L26,71@3227L51,74@3325L225,81@3591L11,82@3621L1681,73@3284L2018:ContactsScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                contactsViewModel2 = contactsViewModel;
                if (composerStartRestartGroup.changedInstance(contactsViewModel2)) {
                    i4 = 4;
                }
                i3 = i | i4;
            } else {
                contactsViewModel2 = contactsViewModel;
            }
            i4 = 2;
            i3 = i | i4;
        } else {
            contactsViewModel2 = contactsViewModel;
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "60@2701L15");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
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
                    ViewModel viewModel = ViewModelKt.viewModel((Class<ViewModel>) ContactsViewModel.class, current, (String) null, factoryCreateHiltViewModelFactory, defaultViewModelCreationExtras, composerStartRestartGroup, 36936, 0);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceableGroup();
                    composerStartRestartGroup.endReplaceableGroup();
                    contactsViewModel2 = (ContactsViewModel) viewModel;
                    i3 &= -15;
                }
                contactsViewModel4 = contactsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(277125624, i3, -1, "org.cipherkey.app.ui.ContactsScreen (ContactsScreen.kt:61)");
                }
                final State stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getContacts(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getUnlocked(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getEras(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle4 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getFeedback(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                State stateCollectAsStateWithLifecycle5 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getFingerprints(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                composer2 = composerStartRestartGroup;
                ComposerKt.sourceInformationMarkerStart(composer2, 29766650, "CC(remember):ContactsScreen.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    composer2.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerStart(composer2, 29768579, "CC(remember):ContactsScreen.kt#9igjgp");
                objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                List<Contact> listContactsScreen$lambda$0 = ContactsScreen$lambda$0(stateCollectAsStateWithLifecycle);
                ComposerKt.sourceInformationMarkerStart(composer2, 29771730, "CC(remember):ContactsScreen.kt#9igjgp");
                zChangedInstance = composer2.changedInstance(contactsViewModel4);
                objRememberedValue3 = composer2.rememberedValue();
                if (!zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = (Function2) new ContactsScreenKt$ContactsScreen$1$1(contactsViewModel4, null);
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                EffectsKt.LaunchedEffect(listContactsScreen$lambda$0, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue3, composer2, 0);
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2094208065, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContactsScreenKt.ContactsScreen$lambda$12(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                long background = MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getBackground();
                ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1106786169, true, new Function3() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return ContactsScreenKt.ContactsScreen$lambda$13(stateCollectAsStateWithLifecycle, stateCollectAsStateWithLifecycle2, stateCollectAsStateWithLifecycle3, contactsViewModel4, stateCollectAsStateWithLifecycle4, mutableState4, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54);
                contactsViewModel3 = contactsViewModel4;
                ScaffoldKt.m2869ScaffoldTvnljyQ(null, null, null, null, composableLambdaRememberComposableLambda, 0, background, 0L, null, composableLambdaRememberComposableLambda2, composer2, 805330944, 431);
                composerStartRestartGroup = composer2;
                if (ContactsScreen$lambda$6(mutableState)) {
                    composerStartRestartGroup.startReplaceGroup(919695850);
                } else {
                    composerStartRestartGroup.startReplaceGroup(924991673);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "128@5379L25,129@5429L218,127@5337L321");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 29839793, "CC(remember):ContactsScreen.kt#9igjgp");
                    Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        mutableState3 = mutableState;
                        objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ContactsScreenKt.ContactsScreen$lambda$14$0(mutableState3);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    } else {
                        mutableState3 = mutableState;
                    }
                    Function0 function0 = (Function0) objRememberedValue4;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 29841586, "CC(remember):ContactsScreen.kt#9igjgp");
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(contactsViewModel3);
                    Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function4() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function4
                            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                                return ContactsScreenKt.ContactsScreen$lambda$15$0(contactsViewModel3, mutableState3, (String) obj, (String) obj2, (Argon2Profile) obj3, (Function1) obj4);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    AddContactDialog(function0, (Function4) objRememberedValue5, composerStartRestartGroup, 6);
                }
                composerStartRestartGroup.endReplaceGroup();
                contactContactsScreen$lambda$9 = ContactsScreen$lambda$9(mutableState4);
                if (contactContactsScreen$lambda$9 != null) {
                    composerStartRestartGroup.startReplaceGroup(925355426);
                } else {
                    composerStartRestartGroup.startReplaceGroup(925355427);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "*144@5931L113,147@6067L37,148@6129L104,152@6259L24,139@5710L584");
                    List<ContactSession> listEmptyList = ContactsScreen$lambda$2(stateCollectAsStateWithLifecycle3).get(ContactId.m10637boximpl(contactContactsScreen$lambda$9.m10665getIdYaFaGk()));
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    boolean zContains = ContactsScreen$lambda$1(stateCollectAsStateWithLifecycle2).contains(ContactId.m10637boximpl(contactContactsScreen$lambda$9.m10665getIdYaFaGk()));
                    String str = ContactsScreen$lambda$4(stateCollectAsStateWithLifecycle5).get(ContactId.m10637boximpl(contactContactsScreen$lambda$9.m10665getIdYaFaGk()));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 524982884, "CC(remember):ContactsScreen.kt#9igjgp");
                    boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(contactsViewModel3) | composerStartRestartGroup.changedInstance(contactContactsScreen$lambda$9);
                    Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance3 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return ContactsScreenKt.ContactsScreen$lambda$16$0$0(contactsViewModel3, contactContactsScreen$lambda$9, (char[]) obj, (Function1) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                    }
                    Function2 function2 = (Function2) objRememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 524987160, "CC(remember):ContactsScreen.kt#9igjgp");
                    boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(contactsViewModel3) | composerStartRestartGroup.changedInstance(contactContactsScreen$lambda$9);
                    Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance4 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue7 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ContactsScreenKt.ContactsScreen$lambda$16$1$0(contactsViewModel3, contactContactsScreen$lambda$9);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    }
                    Function0 function02 = (Function0) objRememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 524989211, "CC(remember):ContactsScreen.kt#9igjgp");
                    boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(contactsViewModel3) | composerStartRestartGroup.changedInstance(contactContactsScreen$lambda$9);
                    Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        mutableState2 = mutableState4;
                        objRememberedValue8 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda15
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ContactsScreenKt.ContactsScreen$lambda$16$2$0(contactsViewModel3, contactContactsScreen$lambda$9, mutableState2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                    } else {
                        mutableState2 = mutableState4;
                    }
                    Function0 function03 = (Function0) objRememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 524993291, "CC(remember):ContactsScreen.kt#9igjgp");
                    Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue9 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ContactsScreenKt.ContactsScreen$lambda$16$3$0(mutableState2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ContactDetailDialog(contactContactsScreen$lambda$9, listEmptyList, zContains, str, function2, function02, function03, (Function0) objRememberedValue9, composerStartRestartGroup, 12582912);
                    composerStartRestartGroup = composerStartRestartGroup;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                contactsViewModel4 = contactsViewModel2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                final State stateCollectAsStateWithLifecycle6 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getContacts(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle22 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getUnlocked(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle32 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getEras(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle42 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getFeedback(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                State stateCollectAsStateWithLifecycle52 = FlowExtKt.collectAsStateWithLifecycle(contactsViewModel4.getFingerprints(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                composer2 = composerStartRestartGroup;
                ComposerKt.sourceInformationMarkerStart(composer2, 29766650, "CC(remember):ContactsScreen.kt#9igjgp");
                objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerStart(composer2, 29768579, "CC(remember):ContactsScreen.kt#9igjgp");
                objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                final MutableState mutableState42 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                List<Contact> listContactsScreen$lambda$02 = ContactsScreen$lambda$0(stateCollectAsStateWithLifecycle6);
                ComposerKt.sourceInformationMarkerStart(composer2, 29771730, "CC(remember):ContactsScreen.kt#9igjgp");
                zChangedInstance = composer2.changedInstance(contactsViewModel4);
                objRememberedValue3 = composer2.rememberedValue();
                if (!zChangedInstance) {
                    objRememberedValue3 = (Function2) new ContactsScreenKt$ContactsScreen$1$1(contactsViewModel4, null);
                    composer2.updateRememberedValue(objRememberedValue3);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    EffectsKt.LaunchedEffect(listContactsScreen$lambda$02, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue3, composer2, 0);
                    ComposableLambda composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(-2094208065, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ContactsScreenKt.ContactsScreen$lambda$12(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composer2, 54);
                    long background2 = MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getBackground();
                    ComposableLambda composableLambdaRememberComposableLambda22 = ComposableLambdaKt.rememberComposableLambda(-1106786169, true, new Function3() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return ContactsScreenKt.ContactsScreen$lambda$13(stateCollectAsStateWithLifecycle6, stateCollectAsStateWithLifecycle22, stateCollectAsStateWithLifecycle32, contactsViewModel4, stateCollectAsStateWithLifecycle42, mutableState42, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer2, 54);
                    contactsViewModel3 = contactsViewModel4;
                    ScaffoldKt.m2869ScaffoldTvnljyQ(null, null, null, null, composableLambdaRememberComposableLambda3, 0, background2, 0L, null, composableLambdaRememberComposableLambda22, composer2, 805330944, 431);
                    composerStartRestartGroup = composer2;
                    if (ContactsScreen$lambda$6(mutableState)) {
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    contactContactsScreen$lambda$9 = ContactsScreen$lambda$9(mutableState42);
                    if (contactContactsScreen$lambda$9 != null) {
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            contactsViewModel3 = contactsViewModel2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactsScreen$lambda$17(contactsViewModel3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean ContactsScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ContactsScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Contact ContactsScreen$lambda$9(MutableState<Contact> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$12(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C75@3378L24,75@3339L201:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2094208065, i, -1, "org.cipherkey.app.ui.ContactsScreen.<anonymous> (ContactsScreen.kt:75)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1805713079, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ContactsScreenKt.ContactsScreen$lambda$12$0$0(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            FloatingActionButtonKt.m2486ExtendedFloatingActionButtonXz6DiA((Function0) objRememberedValue, null, null, 0L, 0L, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$2007385794$app_release(), composer, 12582918, PrecompLvl1.SQIsign_RESPONSE_LENGTH);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$12$0$0(MutableState mutableState) {
        ContactsScreen$lambda$7(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$13(final State state, final State state2, final State state3, final ContactsViewModel contactsViewModel, State state4, final MutableState mutableState, PaddingValues padding, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(padding, "padding");
        ComposerKt.sourceInformation(composer, "CN(padding)83@3642L1654:ContactsScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = i | (composer.changed(padding) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1106786169, i2, -1, "org.cipherkey.app.ui.ContactsScreen.<anonymous> (ContactsScreen.kt:83)");
            }
            Modifier modifierM927paddingVpY3zN4$default = PaddingKt.m927paddingVpY3zN4$default(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding), Dp.m8220constructorimpl(16), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM927paddingVpY3zN4$default);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1590569013, "C89@3838L39,89@3817L61:ContactsScreen.kt#r4glq3");
            ComponentsKt.ScreenHeader(StringResources_androidKt.stringResource(R.string.contacts_title, composer, 0), null, null, composer, 0, 6);
            UiNotice uiNoticeContactsScreen$lambda$3 = ContactsScreen$lambda$3(state4);
            if (uiNoticeContactsScreen$lambda$3 == null) {
                composer.startReplaceGroup(1590612225);
            } else {
                composer.startReplaceGroup(1590612226);
                ComposerKt.sourceInformation(composer, "*92@3963L6,92@3935L96");
                ComponentsKt.NoticeBanner(EncryptScreenKt.text(uiNoticeContactsScreen$lambda$3, composer, 0), PaddingKt.m929paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m8220constructorimpl(8), 7, null), false, composer, 432, 0);
            }
            composer.endReplaceGroup();
            if (ContactsScreen$lambda$0(state).isEmpty()) {
                composer.startReplaceGroup(1590802473);
                ComposerKt.sourceInformation(composer, "96@4101L456");
                ComponentsKt.SectionCard(null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10522getLambda$1311529745$app_release(), composer, 3072, 7);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1591299372);
                ComposerKt.sourceInformation(composer, "108@4657L615,108@4595L677");
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart(composer, 328429368, "CC(remember):ContactsScreen.kt#9igjgp");
                boolean zChanged = composer.changed(state) | composer.changed(state2) | composer.changed(state3) | composer.changedInstance(contactsViewModel);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Function1 function1 = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda28
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ContactsScreenKt.ContactsScreen$lambda$13$0$1$0(state, contactsViewModel, state2, state3, mutableState, (LazyListScope) obj);
                        }
                    };
                    composer.updateRememberedValue(function1);
                    objRememberedValue = function1;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                LazyDslKt.LazyColumn(null, null, null, false, horizontalOrVerticalM780spacedBy0680j_4, null, null, false, null, (Function1) objRememberedValue, composer, 24576, 495);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$13$0$1$0(State state, final ContactsViewModel contactsViewModel, final State state2, final State state3, final MutableState mutableState, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<Contact> listContactsScreen$lambda$0 = ContactsScreen$lambda$0(state);
        final Function1 function1 = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactsScreenKt.ContactsScreen$lambda$13$0$1$0$0((Contact) obj);
            }
        };
        final ContactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$1 contactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$1 = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Contact contact) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((Contact) obj);
            }
        };
        LazyColumn.items(listContactsScreen$lambda$0.size(), new Function1<Integer, Object>() { // from class: org.cipherkey.app.ui.ContactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function1.invoke(listContactsScreen$lambda$0.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: org.cipherkey.app.ui.ContactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return contactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$1.invoke(listContactsScreen$lambda$0.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(802480018, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: org.cipherkey.app.ui.ContactsScreenKt$ContactsScreen$lambda$13$0$1$0$$inlined$items$default$4
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                int i3;
                ComposerKt.sourceInformation(composer, "CN(it)178@8834L22:LazyDsl.kt#428nma");
                if ((i2 & 6) == 0) {
                    i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
                } else {
                    i3 = i2;
                }
                if ((i2 & 48) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if (!composer.shouldExecute((i3 & 147) != 146, i3 & 1)) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(802480018, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:178)");
                }
                final Contact contact = (Contact) listContactsScreen$lambda$0.get(i);
                composer.startReplaceGroup(-1811526385);
                ComposerKt.sourceInformation(composer, "CN(contact)*114@4984L150,110@4755L406:ContactsScreen.kt#r4glq3");
                boolean zContains = ContactsScreenKt.ContactsScreen$lambda$1(state2).contains(ContactId.m10637boximpl(contact.m10665getIdYaFaGk()));
                List list = (List) ContactsScreenKt.ContactsScreen$lambda$2(state3).get(ContactId.m10637boximpl(contact.m10665getIdYaFaGk()));
                int size = list != null ? list.size() : 0;
                ComposerKt.sourceInformationMarkerStart(composer, -1028260588, "CC(remember):ContactsScreen.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(contactsViewModel) | composer.changedInstance(contact);
                Object objRememberedValue = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final ContactsViewModel contactsViewModel2 = contactsViewModel;
                    final MutableState mutableState2 = mutableState;
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: org.cipherkey.app.ui.ContactsScreenKt$ContactsScreen$3$1$2$1$2$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            ContactsViewModel.m10452loadErasovFUVq0$default(contactsViewModel2, contact.m10665getIdYaFaGk(), false, 2, null);
                            mutableState2.setValue(contact);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ContactsScreenKt.ContactCard(contact, zContains, size, (Function0) objRememberedValue, composer, 0);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$551402424$app_release(), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ContactsScreen$lambda$13$0$1$0$0(Contact it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.m10665getIdYaFaGk();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$14$0(MutableState mutableState) {
        ContactsScreen$lambda$7(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$15$0(ContactsViewModel contactsViewModel, final MutableState mutableState, String name, String str, Argon2Profile profile, final Function1 onDone) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        contactsViewModel.createContact(name, str, profile, new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactsScreenKt.ContactsScreen$lambda$15$0$0(onDone, mutableState, ((Boolean) obj).booleanValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$15$0$0(Function1 function1, MutableState mutableState, boolean z) {
        function1.invoke(Boolean.valueOf(z));
        if (z) {
            ContactsScreen$lambda$7(mutableState, false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$16$0$0(ContactsViewModel contactsViewModel, Contact contact, char[] passphrase, Function1 onResult) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        contactsViewModel.m10457unlockContactexK5HwQ(contact.m10665getIdYaFaGk(), passphrase, onResult);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$16$1$0(ContactsViewModel contactsViewModel, Contact contact) {
        contactsViewModel.m10456lockContactM53eizw(contact.m10665getIdYaFaGk());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$16$2$0(ContactsViewModel contactsViewModel, Contact contact, MutableState mutableState) {
        contactsViewModel.m10454deleteContactM53eizw(contact.m10665getIdYaFaGk());
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactsScreen$lambda$16$3$0(MutableState mutableState) {
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ContactCard(final Contact contact, final boolean z, final int i, final Function0<Unit> function0, Composer composer, final int i2) {
        int i3;
        long surfaceContainer;
        Composer composerStartRestartGroup = composer.startRestartGroup(-506254711);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContactCard)N(contact,unlocked,eraCount,onClick)166@6501L6,167@6546L240,174@6794L1895,164@6438L2251:ContactsScreen.kt#r4glq3");
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(contact) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 2048 : 1024;
        }
        if (!composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-506254711, i3, -1, "org.cipherkey.app.ui.ContactCard (ContactsScreen.kt:163)");
            }
            CornerBasedShape medium = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, MaterialTheme.$stable).getMedium();
            CardDefaults cardDefaults = CardDefaults.INSTANCE;
            if (z) {
                composerStartRestartGroup.startReplaceGroup(2091325851);
                ComposerKt.sourceInformation(composerStartRestartGroup, "169@6633L11");
                surfaceContainer = Color.m5385copywmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getPrimaryContainer(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(2091424431);
                ComposerKt.sourceInformation(composerStartRestartGroup, "171@6733L11");
                surfaceContainer = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getSurfaceContainer();
                composerStartRestartGroup.endReplaceGroup();
            }
            CardKt.Card(function0, null, false, medium, cardDefaults.m2079cardColorsro_MJ88(surfaceContainer, 0L, 0L, 0L, composerStartRestartGroup, CardDefaults.$stable << 12, 14), null, null, null, ComposableLambdaKt.rememberComposableLambda(556914740, true, new Function3() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ContactsScreenKt.ContactCard$lambda$0(z, contact, i, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 9) & 14) | 100663296, 230);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactCard$lambda$1(contact, z, i, function0, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactCard$lambda$0(boolean z, Contact contact, int i, ColumnScope Card, Composer composer, int i2) {
        boolean z2;
        String strStringResource;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C175@6804L1879:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(556914740, i2, -1, "org.cipherkey.app.ui.ContactCard.<anonymous> (ContactsScreen.kt:175)");
            }
            Modifier modifierM925padding3ABfNKs = PaddingKt.m925padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m8220constructorimpl(14));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, centerVertically, composer, 54);
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
            Updater.m4644setimpl(composerM4636constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1166279110, "C187@7237L11,183@7092L603,199@7708L699,218@8455L123,217@8420L253:ContactsScreen.kt#r4glq3");
            Modifier modifierM295backgroundbw27NRU = BackgroundKt.m295backgroundbw27NRU(SizeKt.m973size3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(40)), Color.m5385copywmQWz5c$default(MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getPrimary(), 0.18f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.getCircleShape());
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierM295backgroundbw27NRU);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -776258121, "C194@7555L11,192@7423L258:ContactsScreen.kt#r4glq3");
            String upperCase = StringsKt.take(StringsKt.trim((CharSequence) contact.getDisplayName()).toString(), 1).toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            String str = upperCase;
            if (StringsKt.isBlank(str)) {
                str = "?";
            }
            TextKt.m3252TextNvy7gAk(str, null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getPrimary(), null, TextUnitKt.getSp(17), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 1597440, 0, 262058);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer);
            Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -69071115, "C200@7765L242,214@8346L11,207@8024L369:ContactsScreen.kt#r4glq3");
            TextKt.m3252TextNvy7gAk(contact.getDisplayName(), null, 0L, null, TextUnitKt.getSp(16), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8156getEllipsisgIe3tQ8(), false, 1, 0, null, null, composer, 1597440, 24960, 241582);
            if (i == 0) {
                composer.startReplaceGroup(-68779375);
                ComposerKt.sourceInformation(composer, "209@8102L39");
                z2 = false;
                strStringResource = StringResources_androidKt.stringResource(R.string.contact_no_era, composer, 0);
                composer.endReplaceGroup();
            } else {
                z2 = false;
                composer.startReplaceGroup(-68686871);
                ComposerKt.sourceInformation(composer, "211@8195L47");
                strStringResource = StringResources_androidKt.stringResource(R.string.contact_eras, new Object[]{Integer.valueOf(i)}, composer, 0);
                composer.endReplaceGroup();
            }
            TextKt.m3252TextNvy7gAk(strStringResource, null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComponentsKt.StatusChip(StringResources_androidKt.stringResource(z ? R.string.contact_unlocked : R.string.contact_locked, composer, 0), z ? ChipState.SUCCESS : ChipState.NEUTRAL, null, composer, 0, 4);
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

    private static final void AddContactDialog(final Function0<Unit> function0, final Function4<? super String, ? super String, ? super Argon2Profile, ? super Function1<? super Boolean, Unit>, Unit> function4, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1520911065);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AddContactDialog)N(onDismiss,onCreate)232@8857L31,233@8907L31,234@8958L50,235@9025L34,238@9105L26,287@11305L318,296@11649L150,240@9212L2067,237@9065L2741:ContactsScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1520911065, i2, -1, "org.cipherkey.app.ui.AddContactDialog (ContactsScreen.kt:231)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1306657464, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1306659064, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1306660715, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Argon2Profile.INSTANCE.getDEFAULT(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1306662843, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1306665395, "CC(remember):ContactsScreen.kt#9igjgp");
            boolean z = (i2 & 14) == 4;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ContactsScreenKt.AddContactDialog$lambda$12$0(function0, mutableState4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m1969AlertDialogOix01E0((Function0) objRememberedValue5, ComposableLambdaKt.rememberComposableLambda(-739501167, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.AddContactDialog$lambda$13(function4, mutableState, mutableState4, mutableState2, mutableState3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-268144817, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.AddContactDialog$lambda$14(function0, mutableState4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$203211533$app_release(), ComposableLambdaKt.rememberComposableLambda(438889708, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.AddContactDialog$lambda$15(mutableState, mutableState2, mutableState3, mutableState4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.AddContactDialog$lambda$16(function0, function4, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String AddContactDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AddContactDialog$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final Argon2Profile AddContactDialog$lambda$7(MutableState<Argon2Profile> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AddContactDialog$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AddContactDialog$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$12$0(Function0 function0, MutableState mutableState) {
        if (!AddContactDialog$lambda$10(mutableState)) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$15(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, MutableState mutableState4, Composer composer, int i) {
        boolean z;
        ComposerKt.sourceInformation(composer, "C242@9285L21,241@9226L2043:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(438889708, i, -1, "org.cipherkey.app.ui.AddContactDialog.<anonymous> (ContactsScreen.kt:241)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(Modifier.INSTANCE, ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScroll$default);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1560599414, "C247@9497L13,245@9408L237,252@9688L44,254@9814L11,251@9662L199,258@9969L15,256@9878L249,263@10170L42,262@10144L177:ContactsScreen.kt#r4glq3");
            String strAddContactDialog$lambda$1 = AddContactDialog$lambda$1(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 604532259, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ContactsScreenKt.AddContactDialog$lambda$15$0$0$0(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            String str = "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh";
            String str2 = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
            OutlinedTextFieldKt.OutlinedTextField(strAddContactDialog$lambda$1, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$2026165340$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12582912, 0, 8257468);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_invite_hint, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            String strAddContactDialog$lambda$4 = AddContactDialog$lambda$4(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, 604547365, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ContactsScreenKt.AddContactDialog$lambda$15$0$1$0(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            String str3 = "CC(remember):ContactsScreen.kt#9igjgp";
            OutlinedTextFieldKt.OutlinedTextField(strAddContactDialog$lambda$4, (Function1<? super String, Unit>) objRememberedValue2, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ContactsScreenKt.INSTANCE.m10527getLambda$1674608507$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12582912, 0, 8257468);
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_kdf_title, composer, 0), null, 0L, null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 1597440, 0, 262062);
            Composer composer2 = composer;
            composer2.startReplaceGroup(604560682);
            ComposerKt.sourceInformation(composer2, "*268@10403L759");
            Iterator<Argon2Profile> it = Argon2Profile.getEntries().iterator();
            while (it.hasNext()) {
                final Argon2Profile next = it.next();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer2, 48);
                String str4 = str;
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, str4);
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                String str5 = str2;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, str5);
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor2);
                } else {
                    composer2.useNode();
                }
                Composer composerM4636constructorimpl2 = Updater.m4636constructorimpl(composer2);
                Updater.m4644setimpl(composerM4636constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, -366059149, "C271@10597L31,269@10481L174,275@10819L248,273@10680L460:ContactsScreen.kt#r4glq3");
                boolean z2 = AddContactDialog$lambda$7(mutableState3) == next;
                String str6 = str3;
                ComposerKt.sourceInformationMarkerStart(composer2, -288899940, str6);
                boolean zChanged = composer2.changed(next.ordinal());
                Object objRememberedValue3 = composer2.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ContactsScreenKt.AddContactDialog$lambda$15$0$2$0$0$0(next, mutableState3, ((Boolean) obj).booleanValue());
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                CheckboxKt.Checkbox(z2, (Function1) objRememberedValue3, null, false, null, null, composer2, 0, 60);
                String lowerCase = next.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (lowerCase.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    String strValueOf = String.valueOf(lowerCase.charAt(0));
                    Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
                    String upperCase = strValueOf.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    StringBuilder sbAppend = sb.append((Object) upperCase);
                    z = true;
                    String strSubstring = lowerCase.substring(1);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    lowerCase = sbAppend.append(strSubstring).toString();
                } else {
                    z = true;
                }
                TextKt.m3252TextNvy7gAk(lowerCase + " — " + StringResources_androidKt.stringResource(R.string.kdf_profile_desc, new Object[]{Integer.valueOf(next.getParams().getMemoryKib() / 1024), Integer.valueOf(next.getParams().getIterations())}, composer2, 0), null, 0L, null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                str = str4;
                str3 = str6;
                str2 = str5;
            }
            composer2.endReplaceGroup();
            if (AddContactDialog$lambda$10(mutableState4)) {
                composer2.startReplaceGroup(604587014);
                ComposerKt.sourceInformation(composer2, "284@11207L48");
                ProgressIndicatorKt.m2840LinearProgressIndicatorrIrjwxo(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, 0L, 0, 0.0f, composer, 6, 30);
                composer2 = composer;
            } else {
                composer2.startReplaceGroup(1551208428);
            }
            composer2.endReplaceGroup();
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
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$15$0$0$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$15$0$1$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$15$0$2$0$0$0(Argon2Profile argon2Profile, MutableState mutableState, boolean z) {
        if (z) {
            mutableState.setValue(argon2Profile);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$13(final Function4 function4, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C290@11411L137,288@11319L294:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-739501167, i, -1, "org.cipherkey.app.ui.AddContactDialog.<anonymous> (ContactsScreen.kt:288)");
            }
            boolean z = (StringsKt.isBlank(AddContactDialog$lambda$1(mutableState)) || AddContactDialog$lambda$10(mutableState2)) ? false : true;
            ComposerKt.sourceInformationMarkerStart(composer, -1991014118, "CC(remember):ContactsScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function4);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function0 function0 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ContactsScreenKt.AddContactDialog$lambda$13$0$0(function4, mutableState2, mutableState, mutableState3, mutableState4);
                    }
                };
                composer.updateRememberedValue(function0);
                objRememberedValue = function0;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10528getLambda$1807771020$app_release(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$13$0$0(Function4 function4, final MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        AddContactDialog$lambda$11(mutableState, true);
        String strAddContactDialog$lambda$1 = AddContactDialog$lambda$1(mutableState2);
        String strAddContactDialog$lambda$4 = AddContactDialog$lambda$4(mutableState3);
        if (StringsKt.isBlank(strAddContactDialog$lambda$4)) {
            strAddContactDialog$lambda$4 = null;
        }
        function4.invoke(strAddContactDialog$lambda$1, strAddContactDialog$lambda$4, AddContactDialog$lambda$7(mutableState4), new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactsScreenKt.AddContactDialog$lambda$13$0$0$1(mutableState, ((Boolean) obj).booleanValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$13$0$0$1(MutableState mutableState, boolean z) {
        AddContactDialog$lambda$11(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AddContactDialog$lambda$14(Function0 function0, MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C297@11663L126:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-268144817, i, -1, "org.cipherkey.app.ui.AddContactDialog.<anonymous> (ContactsScreen.kt:297)");
            }
            ButtonKt.TextButton(function0, null, !AddContactDialog$lambda$10(mutableState), null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10524getLambda$1336414670$app_release(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void ContactDetailDialog(final Contact contact, final List<ContactSession> list, final boolean z, final String str, final Function2<? super char[], ? super Function1<? super UnlockOutcome, Unit>, Unit> function2, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, Composer composer, final int i) {
        int i2;
        Function0<Unit> function04;
        Function0<Unit> function05;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2069040194);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContactDetailDialog)N(contact,eras,unlocked,fingerprint,onUnlock,onLock,onDelete,onDismiss)315@12160L7,316@12190L31,317@12247L42,318@12306L34,319@12366L34,470@19441L104,325@12513L29,326@12559L6856,323@12446L7106:ContactsScreen.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(contact) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(list) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            function04 = function0;
            i2 |= composerStartRestartGroup.changedInstance(function04) ? 131072 : 65536;
        } else {
            function04 = function0;
        }
        if ((1572864 & i) == 0) {
            function05 = function02;
            i2 |= composerStartRestartGroup.changedInstance(function05) ? 1048576 : 524288;
        } else {
            function05 = function02;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function03) ? 8388608 : 4194304;
        }
        if (!composerStartRestartGroup.shouldExecute((4793491 & i2) != 4793490, i2 & 1)) {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2069040194, i2, -1, "org.cipherkey.app.ui.ContactDetailDialog (ContactsScreen.kt:314)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -222220063, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -222218228, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -222216348, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -222214428, "CC(remember):ContactsScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ContactSession contactSession = (ContactSession) CollectionsKt.firstOrNull((List) list);
            final Function0<Unit> function06 = function05;
            final Function0<Unit> function07 = function04;
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m1969AlertDialogOix01E0(function03, ComposableLambdaKt.rememberComposableLambda(427698314, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactDetailDialog$lambda$12(function03, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, null, null, ComposableLambdaKt.rememberComposableLambda(1424811918, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactDetailDialog$lambda$13(contact, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(1674090319, true, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactDetailDialog$lambda$14(z, function2, str, function07, contactSession, list, mutableState, mutableState3, mutableState2, context, mutableState4, function06, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, ((i2 >> 21) & 14) | 1769520, 0, 16284);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactsScreenKt.ContactDetailDialog$lambda$15(contact, list, z, str, function2, function0, function02, function03, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String ContactDetailDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String ContactDetailDialog$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ContactDetailDialog$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ContactDetailDialog$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean ContactDetailDialog$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ContactDetailDialog$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$13(Contact contact, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C325@12515L25:ContactsScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1424811918, i, -1, "org.cipherkey.app.ui.ContactDetailDialog.<anonymous> (ContactsScreen.kt:325)");
            }
            TextKt.m3252TextNvy7gAk(contact.getDisplayName(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v17 */
    public static final Unit ContactDetailDialog$lambda$14(boolean z, final Function2 function2, String str, Function0 function0, final ContactSession contactSession, List list, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final Context context, final MutableState mutableState4, Function0 function02, Composer composer, int i) {
        Modifier modifier;
        String str2;
        ?? r2;
        int i2;
        String str3;
        String str4;
        Composer composer2;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        Composer composer3;
        char c;
        ComposerKt.sourceInformation(composer, "C328@12632L21,327@12573L6832:ContactsScreen.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1674090319, i, -1, "org.cipherkey.app.ui.ContactDetailDialog.<anonymous> (ContactsScreen.kt:327)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(Modifier.INSTANCE, ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_4 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScroll$default);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1104675675, "C:ContactsScreen.kt#r4glq3");
            if (z) {
                modifier = null;
                str2 = "CC(remember):ContactsScreen.kt#9igjgp";
                r2 = 1;
                i2 = 0;
                str3 = "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh";
                str4 = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
                composer.startReplaceGroup(1106694146);
                ComposerKt.sourceInformation(composer, "373@15072L41,372@15029L158,389@15820L124");
                ComponentsKt.StatusChip(StringResources_androidKt.stringResource(R.string.contact_unlocked, composer, 0), ChipState.SUCCESS, null, composer, 48, 4);
                if (str == null) {
                    composer.startReplaceGroup(1106894746);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(1106894747);
                    ComposerKt.sourceInformation(composer, "*378@15285L54,377@15251L265,384@15575L49,386@15722L11,383@15541L236");
                    TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_fingerprint_label, new Object[]{str}, composer, 0), null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8156getEllipsisgIe3tQ8(), false, 1, 0, null, null, composer, 24576, 24960, 241646);
                    TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_fingerprint_hint, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
                    Unit unit = Unit.INSTANCE;
                    composer.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                }
                composer2 = composer;
                ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10523getLambda$131205338$app_release(), composer2, 805306368, 510);
                composer2.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1104553999);
                ComposerKt.sourceInformation(composer, "334@12863L44,333@12833L139,339@13096L19,337@12993L449,349@13688L42,350@13766L48,351@13850L38,354@14023L820,352@13909L1074");
                modifier = null;
                r2 = 1;
                i2 = 0;
                str3 = "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh";
                str4 = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_unlock_hint, composer, 0), null, 0L, null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
                String strContactDetailDialog$lambda$1 = ContactDetailDialog$lambda$1(mutableState);
                PasswordVisualTransformation passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
                KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7907getPasswordPjHm6EE(), 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart(composer, 1005469048, "CC(remember):ContactsScreen.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda30
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$0$0(mutableState, (String) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                OutlinedTextFieldKt.OutlinedTextField(strContactDetailDialog$lambda$1, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ContactsScreenKt.INSTANCE.m10526getLambda$1585424582$app_release(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) passwordVisualTransformation, keyboardOptions, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12779520, 0, 8208316);
                composer2 = composer;
                if (ContactDetailDialog$lambda$7(mutableState2)) {
                    composer2.startReplaceGroup(1005481141);
                    ComposerKt.sourceInformation(composer2, "345@13473L48");
                    ProgressIndicatorKt.m2840LinearProgressIndicatorrIrjwxo(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, 0L, 0, 0.0f, composer2, 6, 30);
                    composer2.endReplaceGroup();
                    c = 12381;
                } else {
                    c = 12381;
                    composer2.startReplaceGroup(1091776605);
                    composer2.endReplaceGroup();
                }
                String strContactDetailDialog$lambda$4 = ContactDetailDialog$lambda$4(mutableState3);
                if (strContactDetailDialog$lambda$4 == null) {
                    composer2.startReplaceGroup(1105228992);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(1105228993);
                    ComposerKt.sourceInformation(composer2, "*347@13587L40");
                    ComponentsKt.NoticeBanner(strContactDetailDialog$lambda$4, null, false, composer2, 384, 2);
                    Unit unit3 = Unit.INSTANCE;
                    composer2.endReplaceGroup();
                    Unit unit4 = Unit.INSTANCE;
                }
                final String strStringResource = StringResources_androidKt.stringResource(R.string.contact_unlock_ok, composer2, 0);
                final String strStringResource2 = StringResources_androidKt.stringResource(R.string.notice_wrong_passphrase, composer2, 0);
                final String strStringResource3 = StringResources_androidKt.stringResource(R.string.notice_no_era, composer2, 0);
                boolean z2 = ContactDetailDialog$lambda$1(mutableState).length() > 0 && !ContactDetailDialog$lambda$7(mutableState2);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, 1005499513, "CC(remember):ContactsScreen.kt#9igjgp");
                boolean zChanged = composer2.changed(function2) | composer2.changed(strStringResource) | composer2.changed(strStringResource2) | composer2.changed(strStringResource3);
                Object objRememberedValue2 = composer2.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Function0 function03 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda31
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$2$0(function2, mutableState2, mutableState, strStringResource, strStringResource2, strStringResource3, mutableState3);
                        }
                    };
                    composer2.updateRememberedValue(function03);
                    objRememberedValue2 = function03;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                str2 = "CC(remember):ContactsScreen.kt#9igjgp";
                ButtonKt.TextButton((Function0) objRememberedValue2, modifierFillMaxWidth$default, z2, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10529getLambda$224990435$app_release(), composer2, 805306416, 504);
                composer2.endReplaceGroup();
            }
            if (contactSession != null) {
                composer2.startReplaceGroup(1107738815);
                ComposerKt.sourceInformation(composer2, "397@16089L51,396@16059L202,401@16301L279,401@16282L298,409@16601L1112");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_invite_share_label, composer2, i2), null, 0L, null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 1597440, 0, 262062);
                composer3 = composer;
                SelectionContainerKt.SelectionContainer(modifier, ComposableLambdaKt.rememberComposableLambda(-814313670, r2, new Function2() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContactsScreenKt.ContactDetailDialog$lambda$14$0$4(contactSession, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer3, 54), composer3, 48, r2);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_42 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_42, Alignment.INSTANCE.getTop(), composer3, 6);
                String str10 = str3;
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, str10);
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, companion);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                String str11 = str4;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, str11);
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
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
                ComposerKt.sourceInformationMarkerStart(composer3, 1839099384, "C410@16704L479,410@16683L556,420@17285L349,420@17264L427:ContactsScreen.kt#r4glq3");
                String str12 = str2;
                ComposerKt.sourceInformationMarkerStart(composer3, 613515258, str12);
                boolean zChangedInstance = composer3.changedInstance(context) | composer3.changedInstance(contactSession);
                Object objRememberedValue3 = composer3.rememberedValue();
                if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda33
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$5$0$0(context, contactSession);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                str6 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                str5 = "C101@5233L9:Row.kt#2w3rfo";
                str8 = str10;
                str7 = str11;
                str9 = str12;
                ButtonKt.TextButton((Function0) objRememberedValue3, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$635610232$app_release(), composer3, 805306368, 510);
                ComposerKt.sourceInformationMarkerStart(composer3, 613533720, str9);
                boolean zChangedInstance2 = composer3.changedInstance(contactSession) | composer3.changedInstance(context);
                Object objRememberedValue4 = composer3.rememberedValue();
                if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$5$1$0(context, contactSession);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$460653103$app_release(), composer3, 805306368, 510);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endReplaceGroup();
            } else {
                str5 = "C101@5233L9:Row.kt#2w3rfo";
                str6 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                str7 = str4;
                str8 = str3;
                str9 = str2;
                composer2.startReplaceGroup(1109375894);
                ComposerKt.sourceInformation(composer2, "429@17764L39,429@17759L63");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_no_era, composer2, 0), null, 0L, null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
                composer3 = composer;
                composer3.endReplaceGroup();
            }
            if (list.size() > 1) {
                composer3.startReplaceGroup(1109517564);
                ComposerKt.sourceInformation(composer3, "434@17929L58,433@17899L153");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_older_eras, new Object[]{Integer.valueOf(list.size() - 1)}, composer3, 0), null, 0L, null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262126);
                composer3 = composer;
            } else {
                composer3.startReplaceGroup(1091776605);
            }
            composer3.endReplaceGroup();
            if (ContactDetailDialog$lambda$10(mutableState4)) {
                composer3.startReplaceGroup(1109760821);
                ComposerKt.sourceInformation(composer3, "441@18159L47,443@18296L11,440@18129L207,445@18357L412");
                TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(R.string.contact_delete_confirm, composer3, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getError(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM780spacedBy0680j_43 = Arrangement.INSTANCE.m780spacedBy0680j_4(Dp.m8220constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composer, 844473419, str6);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM780spacedBy0680j_43, Alignment.INSTANCE.getTop(), composer, 6);
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, str8);
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, companion2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, str7);
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor3);
                } else {
                    composer.useNode();
                }
                Composer composerM4636constructorimpl3 = Updater.m4636constructorimpl(composer);
                Updater.m4644setimpl(composerM4636constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4644setimpl(composerM4636constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4640initimpl(composerM4636constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4642reconcileimpl(composerM4636constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4644setimpl(composerM4636constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str5);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -96844174, "C446@18439L136,449@18621L25,449@18600L147:ContactsScreen.kt#r4glq3");
                ButtonKt.TextButton(function02, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10520getLambda$1037979654$app_release(), composer, 805306368, 510);
                ComposerKt.sourceInformationMarkerStart(composer, -1388591786, str9);
                Object objRememberedValue5 = composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda35
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$6$0$0(mutableState4);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.TextButton((Function0) objRememberedValue5, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10521getLambda$1212936783$app_release(), composer, 805306374, 510);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endReplaceGroup();
            } else {
                composer3.startReplaceGroup(1110438791);
                ComposerKt.sourceInformation(composer3, "454@18836L24,454@18815L558");
                ComposerKt.sourceInformationMarkerStart(composer3, 1005652733, str9);
                Object objRememberedValue6 = composer3.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda36
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactsScreenKt.ContactDetailDialog$lambda$14$0$7$0(mutableState4);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.TextButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.getLambda$1817795103$app_release(), composer3, 805306374, 510);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$0$0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$2$0(Function2 function2, final MutableState mutableState, MutableState mutableState2, final String str, final String str2, final String str3, final MutableState mutableState3) {
        ContactDetailDialog$lambda$8(mutableState, true);
        char[] charArray = ContactDetailDialog$lambda$1(mutableState2).toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "toCharArray(...)");
        mutableState2.setValue("");
        function2.invoke(charArray, new Function1() { // from class: org.cipherkey.app.ui.ContactsScreenKt$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ContactsScreenKt.ContactDetailDialog$lambda$14$0$2$0$0(str, str2, str3, mutableState, mutableState3, (UnlockOutcome) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$2$0$0(String str, String str2, String str3, MutableState mutableState, MutableState mutableState2, UnlockOutcome outcome) {
        Intrinsics.checkNotNullParameter(outcome, "outcome");
        ContactDetailDialog$lambda$8(mutableState, false);
        if (!Intrinsics.areEqual(outcome, UnlockOutcome.Unlocked.INSTANCE) && !Intrinsics.areEqual(outcome, UnlockOutcome.AlreadyUnlocked.INSTANCE)) {
            if (Intrinsics.areEqual(outcome, UnlockOutcome.WrongPassphrase.INSTANCE)) {
                str = str2;
            } else if (Intrinsics.areEqual(outcome, UnlockOutcome.NoSession.INSTANCE)) {
                str = str3;
            } else if (outcome instanceof UnlockOutcome.Rejected) {
                str = ((UnlockOutcome.Rejected) outcome).getReason();
            } else {
                if (!(outcome instanceof UnlockOutcome.Failed)) {
                    throw new NoWhenBranchMatchedException();
                }
                str = ((UnlockOutcome.Failed) outcome).getReason();
            }
        }
        mutableState2.setValue(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$4(ContactSession contactSession, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C402@16327L231:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-814313670, i, -1, "org.cipherkey.app.ui.ContactDetailDialog.<anonymous>.<anonymous>.<anonymous> (ContactsScreen.kt:402)");
            }
            TextKt.m3252TextNvy7gAk(contactSession.getInviteCode(), null, 0L, null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m8156getEllipsisgIe3tQ8(), false, 2, 0, null, null, composer, 24576, 24960, 241646);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$5$0$0(Context context, ContactSession contactSession) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(ClipboardManager.class);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("CipherKey invite", contactSession.getInviteCode()));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$5$1$0(Context context, ContactSession contactSession) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", contactSession.getInviteCode());
        context.startActivity(Intent.createChooser(intent, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$6$0$0(MutableState mutableState) {
        ContactDetailDialog$lambda$11(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$14$0$7$0(MutableState mutableState) {
        ContactDetailDialog$lambda$11(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactDetailDialog$lambda$12(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C471@19455L80:ContactsScreen.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(427698314, i, -1, "org.cipherkey.app.ui.ContactDetailDialog.<anonymous> (ContactsScreen.kt:471)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$ContactsScreenKt.INSTANCE.m10525getLambda$1453929273$app_release(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final List<Contact> ContactsScreen$lambda$0(State<? extends List<Contact>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<ContactId> ContactsScreen$lambda$1(State<? extends Set<ContactId>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<ContactId, List<ContactSession>> ContactsScreen$lambda$2(State<? extends Map<ContactId, ? extends List<ContactSession>>> state) {
        return (Map) state.getValue();
    }

    private static final UiNotice ContactsScreen$lambda$3(State<UiNotice> state) {
        return state.getValue();
    }

    private static final Map<ContactId, String> ContactsScreen$lambda$4(State<? extends Map<ContactId, String>> state) {
        return state.getValue();
    }
}
