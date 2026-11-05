package org.cipherkey.app.ui;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.PointerIconCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.cipherkey.app.R;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;

/* compiled from: ContactPicker.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\u001a[\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002"}, d2 = {"ContactPicker", "", "contacts", "", "Lorg/cipherkey/data/repository/Contact;", "selectedContactId", "Lorg/cipherkey/core/common/ContactId;", "unlocked", "", "onContactSelected", "Lkotlin/Function1;", AnnotatedPrivateKey.LABEL, "", "modifier", "Landroidx/compose/ui/Modifier;", "ContactPicker-2p-W6ks", "(Ljava/util/List;Ljava/lang/String;Ljava/util/Set;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_release", "expanded", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactPickerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$6(List list, String str, Set set, Function1 function1, String str2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m10551ContactPicker2pW6ks(list, str, set, function1, str2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* renamed from: ContactPicker-2p-W6ks, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m10551ContactPicker2pW6ks(final List<Contact> contacts, final String str, final Set<ContactId> unlocked, final Function1<? super ContactId, Unit> onContactSelected, final String label, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(unlocked, "unlocked");
        Intrinsics.checkNotNullParameter(onContactSelected, "onContactSelected");
        Intrinsics.checkNotNullParameter(label, "label");
        Composer composerStartRestartGroup = composer.startRestartGroup(1903395665);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ContactPicker)N(contacts,selectedContactId:org.cipherkey.core.common.ContactId,unlocked,onContactSelected,label,modifier)39@1441L34,44@1632L17,46@1686L2244,42@1552L2378:ContactPicker.kt#r4glq3");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(contacts) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        Object obj = null;
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(str != null ? ContactId.m10637boximpl(str) : null) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(unlocked) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onContactSelected) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changed(label) ? 16384 : 8192;
        }
        int i5 = i2 & 32;
        if (i5 == 0) {
            if ((196608 & i) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 131072 : 65536;
            }
            i4 = i3;
            if (composerStartRestartGroup.shouldExecute((74899 & i4) == 74898, i4 & 1)) {
                composerStartRestartGroup.skipToGroupEnd();
            } else {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1903395665, i4, -1, "org.cipherkey.app.ui.ContactPicker (ContactPicker.kt:38)");
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -492826477, "CC(remember):ContactPicker.kt#9igjgp");
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MutableState mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Iterator<T> it = contacts.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (str == null ? false : ContactId.m10640equalsimpl0(((Contact) next).m10665getIdYaFaGk(), str)) {
                        obj = next;
                        break;
                    }
                }
                final Contact contact = (Contact) obj;
                boolean zContactPicker_2p_W6ks$lambda$1 = ContactPicker_2p_W6ks$lambda$1(mutableState);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -492820382, "CC(remember):ContactPicker.kt#9igjgp");
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return ContactPickerKt.ContactPicker_2p_W6ks$lambda$4$0(mutableState, ((Boolean) obj2).booleanValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ExposedDropdownMenuKt.ExposedDropdownMenuBox(zContactPicker_2p_W6ks$lambda$1, (Function1) objRememberedValue2, modifier2, ComposableLambdaKt.rememberComposableLambda(669791483, true, new Function3() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5(contact, label, unlocked, mutableState, contacts, onContactSelected, (ExposedDropdownMenuBoxScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i4 >> 9) & 896) | 3120, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            final Modifier modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup == null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$6(contacts, str, unlocked, onContactSelected, label, modifier3, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
                return;
            }
            return;
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        modifier2 = modifier;
        i4 = i3;
        if (composerStartRestartGroup.shouldExecute((74899 & i4) == 74898, i4 & 1)) {
        }
        final Modifier modifier32 = modifier2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
        }
    }

    private static final boolean ContactPicker_2p_W6ks$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ContactPicker_2p_W6ks$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$4$0(MutableState mutableState, boolean z) {
        ContactPicker_2p_W6ks$lambda$2(mutableState, z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5(final Contact contact, final String str, final Set set, final MutableState mutableState, final List list, final Function1 function1, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer composer, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation(composer, "C49@1821L2,51@1874L15,52@1917L271,59@2217L65,47@1696L692,66@2482L20,67@2514L1410,64@2397L1527:ContactPicker.kt#r4glq3");
        if ((i & 6) == 0) {
            i2 = i | ((i & 8) == 0 ? composer.changed(ExposedDropdownMenuBox) : composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (!composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(669791483, i2, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous> (ContactPicker.kt:47)");
            }
            String displayName = contact != null ? contact.getDisplayName() : null;
            if (displayName == null) {
                composer.startReplaceGroup(-575477086);
                ComposerKt.sourceInformation(composer, "48@1752L39");
                displayName = StringResources_androidKt.stringResource(R.string.contact_choose, composer, 0);
            } else {
                composer.startReplaceGroup(-575477861);
            }
            composer.endReplaceGroup();
            Modifier modifierMenuAnchor = ExposedDropdownMenuBox.menuAnchor(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null));
            ComposerKt.sourceInformationMarkerStart(composer, -575474915, "CC(remember):ContactPicker.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$0$0((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            int i3 = i2;
            TextFieldKt.TextField(displayName, (Function1<? super String, Unit>) objRememberedValue, modifierMenuAnchor, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1969512801, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$1(str, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1971347167, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$2(contact, set, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(175219298, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$3(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 907567152, 0, 0, 8387752);
            boolean zContactPicker_2p_W6ks$lambda$1 = ContactPicker_2p_W6ks$lambda$1(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, -575453745, "CC(remember):ContactPicker.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$4$0(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ExposedDropdownMenuBox.m2435ExposedDropdownMenuvNxi1II(zContactPicker_2p_W6ks$lambda$1, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-933623043, true, new Function3() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$5(list, function1, mutableState, set, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | ((i3 << 3) & 112), PointerIconCompat.TYPE_GRAB);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$0$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$1(String str, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C51@1876L11:ContactPicker.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1969512801, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous> (ContactPicker.kt:51)");
            }
            TextKt.m3252TextNvy7gAk(str, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$2(Contact contact, Set set, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C56@2136L11,53@1935L239:ContactPicker.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1971347167, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous> (ContactPicker.kt:53)");
            }
            IconKt.m2546Iconww6aTOc((contact == null || !set.contains(ContactId.m10637boximpl(contact.m10665getIdYaFaGk()))) ? IconsKt.getIcLock() : IconsKt.getIcLockOpen(), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getPrimary(), composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$3(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C59@2247L33:ContactPicker.kt#r4glq3");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(175219298, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous> (ContactPicker.kt:59)");
            }
            ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(ContactPicker_2p_W6ks$lambda$1(mutableState), null, composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$4$0(MutableState mutableState) {
        ContactPicker_2p_W6ks$lambda$2(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$5(List list, final Function1 function1, final MutableState mutableState, final Set set, ColumnScope ExposedDropdownMenu, Composer composer, int i) {
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer2, "C*70@2619L73,94@3763L118,73@2728L563,85@3328L403,69@2574L1326:ContactPicker.kt#r4glq3");
        if (!composer2.shouldExecute((i & 17) != 16, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-933623043, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous> (ContactPicker.kt:68)");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                final Contact contact = (Contact) it.next();
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1730371460, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$5$0$0(contact, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                ComposerKt.sourceInformationMarkerStart(composer2, -789795574, "CC(remember):ContactPicker.kt#9igjgp");
                boolean zChanged = composer2.changed(function1) | composer2.changedInstance(contact);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$5$0$1$0(function1, contact, mutableState);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, ComposableLambdaKt.rememberComposableLambda(1846538529, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$5$0$2(set, contact, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), ComposableLambdaKt.rememberComposableLambda(453605120, true, new Function2() { // from class: org.cipherkey.app.ui.ContactPickerKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ContactPickerKt.ContactPicker_2p_W6ks$lambda$5$5$0$3(set, contact, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), false, null, null, null, composer2, 27654, 484);
                composer2 = composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$5$0$0(Contact contact, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C71@2645L25:ContactPicker.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1730371460, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ContactPicker.kt:71)");
            }
            TextKt.m3252TextNvy7gAk(contact.getDisplayName(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$5$0$2(Set set, Contact contact, Composer composer, int i) {
        long onSurfaceVariant;
        ComposerKt.sourceInformation(composer, "C74@2754L515:ContactPicker.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1846538529, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ContactPicker.kt:74)");
            }
            ImageVector icLockOpen = set.contains(ContactId.m10637boximpl(contact.m10665getIdYaFaGk())) ? IconsKt.getIcLockOpen() : IconsKt.getIcLock();
            if (set.contains(ContactId.m10637boximpl(contact.m10665getIdYaFaGk()))) {
                composer.startReplaceGroup(288435328);
                ComposerKt.sourceInformation(composer, "78@3020L11");
                onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getPrimary();
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(288537783);
                ComposerKt.sourceInformation(composer, "80@3123L11");
                onSurfaceVariant = MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant();
                composer.endReplaceGroup();
            }
            IconKt.m2546Iconww6aTOc(icLockOpen, (String) null, SizeKt.m973size3ABfNKs(Modifier.INSTANCE, Dp.m8220constructorimpl(18)), onSurfaceVariant, composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$5$0$3(Set set, Contact contact, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C87@3395L161,91@3654L11,86@3354L355:ContactPicker.kt#r4glq3");
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(453605120, i, -1, "org.cipherkey.app.ui.ContactPicker.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ContactPicker.kt:86)");
            }
            TextKt.m3252TextNvy7gAk(StringResources_androidKt.stringResource(set.contains(ContactId.m10637boximpl(contact.m10665getIdYaFaGk())) ? R.string.contact_unlocked : R.string.contact_locked, composer, 0), null, MaterialTheme.INSTANCE.getColorScheme(composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, TextUnitKt.getSp(11), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24576, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ContactPicker_2p_W6ks$lambda$5$5$0$1$0(Function1 function1, Contact contact, MutableState mutableState) {
        function1.invoke(ContactId.m10637boximpl(contact.m10665getIdYaFaGk()));
        ContactPicker_2p_W6ks$lambda$2(mutableState, false);
        return Unit.INSTANCE;
    }
}
