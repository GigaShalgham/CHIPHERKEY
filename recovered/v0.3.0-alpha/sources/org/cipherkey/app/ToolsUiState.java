package org.cipherkey.app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.companion.UiNotice;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;

/* compiled from: ToolsViewModel.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u0012\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b5\u0010\u001fJ\u000b\u00106\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u00108\u001a\u00020\rHÆ\u0003J\t\u00109\u001a\u00020\rHÆ\u0003J\t\u0010:\u001a\u00020\rHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\t\u0010<\u001a\u00020\rHÆ\u0003J\u0012\u0010=\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b>\u0010\u001fJ\u000b\u0010?\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010@\u001a\u00020\rHÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0017HÆ\u0003J¶\u0001\u0010B\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0015\u001a\u00020\r2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001¢\u0006\u0004\bC\u0010DJ\u0013\u0010E\u001a\u00020\r2\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010G\u001a\u00020HHÖ\u0001J\t\u0010I\u001a\u00020\nHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010 \u001a\u0004\b*\u0010\u001fR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010/\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b0\u00101¨\u0006J"}, d2 = {"Lorg/cipherkey/app/ToolsUiState;", "", "contacts", "", "Lorg/cipherkey/data/repository/Contact;", "unlocked", "", "Lorg/cipherkey/core/common/ContactId;", "selectedContactId", "resultText", "", "resultContactName", "resultIsEnvelope", "", "copied", "busy", "notice", "Lorg/cipherkey/app/companion/UiNotice;", "unlockNeeded", "pendingContactId", "unlockContactName", "biometricOffered", "biometricPrompt", "Lorg/cipherkey/app/security/BiometricPromptRequest;", "<init>", "(Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLorg/cipherkey/app/companion/UiNotice;ZLjava/lang/String;Ljava/lang/String;ZLorg/cipherkey/app/security/BiometricPromptRequest;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContacts", "()Ljava/util/List;", "getUnlocked", "()Ljava/util/Set;", "getSelectedContactId-P5lcXoU", "()Ljava/lang/String;", "Ljava/lang/String;", "getResultText", "getResultContactName", "getResultIsEnvelope", "()Z", "getCopied", "getBusy", "getNotice", "()Lorg/cipherkey/app/companion/UiNotice;", "getUnlockNeeded", "getPendingContactId-P5lcXoU", "getUnlockContactName", "getBiometricOffered", "getBiometricPrompt", "()Lorg/cipherkey/app/security/BiometricPromptRequest;", "selectedContact", "getSelectedContact", "()Lorg/cipherkey/data/repository/Contact;", "component1", "component2", "component3", "component3-P5lcXoU", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component11-P5lcXoU", "component12", "component13", "component14", "copy", "copy-eam0GUE", "(Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLorg/cipherkey/app/companion/UiNotice;ZLjava/lang/String;Ljava/lang/String;ZLorg/cipherkey/app/security/BiometricPromptRequest;)Lorg/cipherkey/app/ToolsUiState;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ToolsUiState {
    public static final int $stable = 8;
    private final boolean biometricOffered;
    private final BiometricPromptRequest biometricPrompt;
    private final boolean busy;
    private final List<Contact> contacts;
    private final boolean copied;
    private final UiNotice notice;
    private final String pendingContactId;
    private final String resultContactName;
    private final boolean resultIsEnvelope;
    private final String resultText;
    private final String selectedContactId;
    private final String unlockContactName;
    private final boolean unlockNeeded;
    private final Set<ContactId> unlocked;

    public /* synthetic */ ToolsUiState(List list, Set set, String str, String str2, String str3, boolean z, boolean z2, boolean z3, UiNotice uiNotice, boolean z4, String str4, String str5, boolean z5, BiometricPromptRequest biometricPromptRequest, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, set, str, str2, str3, z, z2, z3, uiNotice, z4, str4, str5, z5, biometricPromptRequest);
    }

    public final List<Contact> component1() {
        return this.contacts;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getUnlockNeeded() {
        return this.unlockNeeded;
    }

    /* renamed from: component11-P5lcXoU, reason: not valid java name and from getter */
    public final String getPendingContactId() {
        return this.pendingContactId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getUnlockContactName() {
        return this.unlockContactName;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getBiometricOffered() {
        return this.biometricOffered;
    }

    /* renamed from: component14, reason: from getter */
    public final BiometricPromptRequest getBiometricPrompt() {
        return this.biometricPrompt;
    }

    public final Set<ContactId> component2() {
        return this.unlocked;
    }

    /* renamed from: component3-P5lcXoU, reason: not valid java name and from getter */
    public final String getSelectedContactId() {
        return this.selectedContactId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getResultText() {
        return this.resultText;
    }

    /* renamed from: component5, reason: from getter */
    public final String getResultContactName() {
        return this.resultContactName;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getResultIsEnvelope() {
        return this.resultIsEnvelope;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getCopied() {
        return this.copied;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getBusy() {
        return this.busy;
    }

    /* renamed from: component9, reason: from getter */
    public final UiNotice getNotice() {
        return this.notice;
    }

    /* renamed from: copy-eam0GUE, reason: not valid java name */
    public final ToolsUiState m10462copyeam0GUE(List<Contact> contacts, Set<ContactId> unlocked, String selectedContactId, String resultText, String resultContactName, boolean resultIsEnvelope, boolean copied, boolean busy, UiNotice notice, boolean unlockNeeded, String pendingContactId, String unlockContactName, boolean biometricOffered, BiometricPromptRequest biometricPrompt) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(unlocked, "unlocked");
        return new ToolsUiState(contacts, unlocked, selectedContactId, resultText, resultContactName, resultIsEnvelope, copied, busy, notice, unlockNeeded, pendingContactId, unlockContactName, biometricOffered, biometricPrompt, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(Object other) {
        boolean zM10640equalsimpl0;
        boolean zM10640equalsimpl02;
        if (this == other) {
            return true;
        }
        if (!(other instanceof ToolsUiState)) {
            return false;
        }
        ToolsUiState toolsUiState = (ToolsUiState) other;
        if (!Intrinsics.areEqual(this.contacts, toolsUiState.contacts) || !Intrinsics.areEqual(this.unlocked, toolsUiState.unlocked)) {
            return false;
        }
        String str = this.selectedContactId;
        String str2 = toolsUiState.selectedContactId;
        if (str == null) {
            zM10640equalsimpl0 = str2 == null;
        } else if (str2 != null) {
            zM10640equalsimpl0 = ContactId.m10640equalsimpl0(str, str2);
        }
        if (!zM10640equalsimpl0 || !Intrinsics.areEqual(this.resultText, toolsUiState.resultText) || !Intrinsics.areEqual(this.resultContactName, toolsUiState.resultContactName) || this.resultIsEnvelope != toolsUiState.resultIsEnvelope || this.copied != toolsUiState.copied || this.busy != toolsUiState.busy || !Intrinsics.areEqual(this.notice, toolsUiState.notice) || this.unlockNeeded != toolsUiState.unlockNeeded) {
            return false;
        }
        String str3 = this.pendingContactId;
        String str4 = toolsUiState.pendingContactId;
        if (str3 == null) {
            zM10640equalsimpl02 = str4 == null;
        } else if (str4 != null) {
            zM10640equalsimpl02 = ContactId.m10640equalsimpl0(str3, str4);
        }
        return zM10640equalsimpl02 && Intrinsics.areEqual(this.unlockContactName, toolsUiState.unlockContactName) && this.biometricOffered == toolsUiState.biometricOffered && Intrinsics.areEqual(this.biometricPrompt, toolsUiState.biometricPrompt);
    }

    public int hashCode() {
        int iHashCode = ((this.contacts.hashCode() * 31) + this.unlocked.hashCode()) * 31;
        String str = this.selectedContactId;
        int iM10641hashCodeimpl = (iHashCode + (str == null ? 0 : ContactId.m10641hashCodeimpl(str))) * 31;
        String str2 = this.resultText;
        int iHashCode2 = (iM10641hashCodeimpl + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.resultContactName;
        int iHashCode3 = (((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.resultIsEnvelope)) * 31) + Boolean.hashCode(this.copied)) * 31) + Boolean.hashCode(this.busy)) * 31;
        UiNotice uiNotice = this.notice;
        int iHashCode4 = (((iHashCode3 + (uiNotice == null ? 0 : uiNotice.hashCode())) * 31) + Boolean.hashCode(this.unlockNeeded)) * 31;
        String str4 = this.pendingContactId;
        int iM10641hashCodeimpl2 = (iHashCode4 + (str4 == null ? 0 : ContactId.m10641hashCodeimpl(str4))) * 31;
        String str5 = this.unlockContactName;
        int iHashCode5 = (((iM10641hashCodeimpl2 + (str5 == null ? 0 : str5.hashCode())) * 31) + Boolean.hashCode(this.biometricOffered)) * 31;
        BiometricPromptRequest biometricPromptRequest = this.biometricPrompt;
        return iHashCode5 + (biometricPromptRequest != null ? biometricPromptRequest.hashCode() : 0);
    }

    public String toString() {
        List<Contact> list = this.contacts;
        Set<ContactId> set = this.unlocked;
        String str = this.selectedContactId;
        String strM10642toStringimpl = str == null ? "null" : ContactId.m10642toStringimpl(str);
        String str2 = this.resultText;
        String str3 = this.resultContactName;
        boolean z = this.resultIsEnvelope;
        boolean z2 = this.copied;
        boolean z3 = this.busy;
        UiNotice uiNotice = this.notice;
        boolean z4 = this.unlockNeeded;
        String str4 = this.pendingContactId;
        return "ToolsUiState(contacts=" + list + ", unlocked=" + set + ", selectedContactId=" + strM10642toStringimpl + ", resultText=" + str2 + ", resultContactName=" + str3 + ", resultIsEnvelope=" + z + ", copied=" + z2 + ", busy=" + z3 + ", notice=" + uiNotice + ", unlockNeeded=" + z4 + ", pendingContactId=" + (str4 != null ? ContactId.m10642toStringimpl(str4) : "null") + ", unlockContactName=" + this.unlockContactName + ", biometricOffered=" + this.biometricOffered + ", biometricPrompt=" + this.biometricPrompt + ")";
    }

    private ToolsUiState(List<Contact> contacts, Set<ContactId> unlocked, String str, String str2, String str3, boolean z, boolean z2, boolean z3, UiNotice uiNotice, boolean z4, String str4, String str5, boolean z5, BiometricPromptRequest biometricPromptRequest) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(unlocked, "unlocked");
        this.contacts = contacts;
        this.unlocked = unlocked;
        this.selectedContactId = str;
        this.resultText = str2;
        this.resultContactName = str3;
        this.resultIsEnvelope = z;
        this.copied = z2;
        this.busy = z3;
        this.notice = uiNotice;
        this.unlockNeeded = z4;
        this.pendingContactId = str4;
        this.unlockContactName = str5;
        this.biometricOffered = z5;
        this.biometricPrompt = biometricPromptRequest;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ToolsUiState(List list, Set set, String str, String str2, String str3, boolean z, boolean z2, boolean z3, UiNotice uiNotice, boolean z4, String str4, String str5, boolean z5, BiometricPromptRequest biometricPromptRequest, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List listEmptyList = (i & 1) != 0 ? CollectionsKt.emptyList() : list;
        Set setEmptySet = (i & 2) != 0 ? SetsKt.emptySet() : set;
        String str6 = (i & 4) != 0 ? null : str;
        String str7 = (i & 8) != 0 ? null : str2;
        String str8 = (i & 16) != 0 ? null : str3;
        boolean z6 = (i & 32) != 0 ? false : z;
        boolean z7 = (i & 64) != 0 ? false : z2;
        boolean z8 = (i & 128) != 0 ? false : z3;
        UiNotice uiNotice2 = (i & 256) != 0 ? null : uiNotice;
        boolean z9 = (i & 512) != 0 ? false : z4;
        String str9 = (i & 1024) != 0 ? null : str4;
        String str10 = (i & 2048) != 0 ? null : str5;
        boolean z10 = (i & 4096) == 0 ? z5 : false;
        this(listEmptyList, setEmptySet, str6, str7, str8, z6, z7, z8, uiNotice2, z9, str9, str10, z10, (i & 8192) == 0 ? biometricPromptRequest : null, null);
    }

    public final List<Contact> getContacts() {
        return this.contacts;
    }

    public final Set<ContactId> getUnlocked() {
        return this.unlocked;
    }

    /* renamed from: getSelectedContactId-P5lcXoU, reason: not valid java name */
    public final String m10464getSelectedContactIdP5lcXoU() {
        return this.selectedContactId;
    }

    public final String getResultText() {
        return this.resultText;
    }

    public final String getResultContactName() {
        return this.resultContactName;
    }

    public final boolean getResultIsEnvelope() {
        return this.resultIsEnvelope;
    }

    public final boolean getCopied() {
        return this.copied;
    }

    public final boolean getBusy() {
        return this.busy;
    }

    public final UiNotice getNotice() {
        return this.notice;
    }

    public final boolean getUnlockNeeded() {
        return this.unlockNeeded;
    }

    /* renamed from: getPendingContactId-P5lcXoU, reason: not valid java name */
    public final String m10463getPendingContactIdP5lcXoU() {
        return this.pendingContactId;
    }

    public final String getUnlockContactName() {
        return this.unlockContactName;
    }

    public final boolean getBiometricOffered() {
        return this.biometricOffered;
    }

    public final BiometricPromptRequest getBiometricPrompt() {
        return this.biometricPrompt;
    }

    public final Contact getSelectedContact() {
        Object next;
        Iterator<T> it = this.contacts.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String strM10665getIdYaFaGk = ((Contact) next).m10665getIdYaFaGk();
            String str = this.selectedContactId;
            if (str == null ? false : ContactId.m10640equalsimpl0(strM10665getIdYaFaGk, str)) {
                break;
            }
        }
        return (Contact) next;
    }
}
