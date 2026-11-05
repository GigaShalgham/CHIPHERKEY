package org.cipherkey.app.companion;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.app.security.BiometricPromptRequest;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.data.repository.Contact;

/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b?\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001`B×\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\u001f\u0010 J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0007HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010H\u001a\u00020\u000bHÆ\u0003J\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u000f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0003J\u0012\u0010K\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\bL\u0010(J\u0012\u0010M\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\bN\u0010(J\u000b\u0010O\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010R\u001a\u00020\u000bHÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\t\u0010T\u001a\u00020\u000bHÆ\u0003J\t\u0010U\u001a\u00020\u000bHÆ\u0003J\t\u0010V\u001a\u00020\u000bHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u001eHÆ\u0003Jà\u0001\u0010X\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÆ\u0001¢\u0006\u0004\bY\u0010ZJ\u0013\u0010[\u001a\u00020\u000b2\b\u0010\\\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010]\u001a\u00020^HÖ\u0001J\t\u0010_\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\n\n\u0002\u00100\u001a\u0004\b/\u0010(R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0011¢\u0006\n\n\u0002\u00100\u001a\u0004\b1\u0010(R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b2\u0010(R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b3\u0010(R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b4\u0010(R\u0011\u0010\u0017\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010*R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\u001a\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b8\u0010*R\u0011\u0010\u001b\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b9\u0010*R\u0011\u0010\u001c\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b:\u0010*R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0013\u0010=\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bA\u0010*R\u0011\u0010B\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\bC\u0010*¨\u0006a"}, d2 = {"Lorg/cipherkey/app/companion/CompanionUiState;", "", "mode", "Lorg/cipherkey/app/companion/CompanionMode;", "source", "Lorg/cipherkey/app/companion/TextSource;", "phase", "Lorg/cipherkey/app/companion/CompanionUiState$Phase;", "incoming", "", "readOnly", "", "contacts", "", "Lorg/cipherkey/data/repository/Contact;", "unlocked", "", "Lorg/cipherkey/core/common/ContactId;", "selectedContactId", "pendingContactId", "pendingContactName", "resultText", "resultContactName", "copied", "notice", "Lorg/cipherkey/app/companion/UiNotice;", "retryable", "busy", "biometricOffered", "biometricPrompt", "Lorg/cipherkey/app/security/BiometricPromptRequest;", "<init>", "(Lorg/cipherkey/app/companion/CompanionMode;Lorg/cipherkey/app/companion/TextSource;Lorg/cipherkey/app/companion/CompanionUiState$Phase;Ljava/lang/String;ZLjava/util/List;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLorg/cipherkey/app/companion/UiNotice;ZZZLorg/cipherkey/app/security/BiometricPromptRequest;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMode", "()Lorg/cipherkey/app/companion/CompanionMode;", "getSource", "()Lorg/cipherkey/app/companion/TextSource;", "getPhase", "()Lorg/cipherkey/app/companion/CompanionUiState$Phase;", "getIncoming", "()Ljava/lang/String;", "getReadOnly", "()Z", "getContacts", "()Ljava/util/List;", "getUnlocked", "()Ljava/util/Set;", "getSelectedContactId-P5lcXoU", "Ljava/lang/String;", "getPendingContactId-P5lcXoU", "getPendingContactName", "getResultText", "getResultContactName", "getCopied", "getNotice", "()Lorg/cipherkey/app/companion/UiNotice;", "getRetryable", "getBusy", "getBiometricOffered", "getBiometricPrompt", "()Lorg/cipherkey/app/security/BiometricPromptRequest;", "selectedContact", "getSelectedContact", "()Lorg/cipherkey/data/repository/Contact;", "selectedContactUnlocked", "getSelectedContactUnlocked", "insertable", "getInsertable", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component8-P5lcXoU", "component9", "component9-P5lcXoU", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "copy-gvgolLs", "(Lorg/cipherkey/app/companion/CompanionMode;Lorg/cipherkey/app/companion/TextSource;Lorg/cipherkey/app/companion/CompanionUiState$Phase;Ljava/lang/String;ZLjava/util/List;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLorg/cipherkey/app/companion/UiNotice;ZZZLorg/cipherkey/app/security/BiometricPromptRequest;)Lorg/cipherkey/app/companion/CompanionUiState;", "equals", "other", "hashCode", "", "toString", "Phase", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CompanionUiState {
    public static final int $stable = 8;
    private final boolean biometricOffered;
    private final BiometricPromptRequest biometricPrompt;
    private final boolean busy;
    private final List<Contact> contacts;
    private final boolean copied;
    private final String incoming;
    private final CompanionMode mode;
    private final UiNotice notice;
    private final String pendingContactId;
    private final String pendingContactName;
    private final Phase phase;
    private final boolean readOnly;
    private final String resultContactName;
    private final String resultText;
    private final boolean retryable;
    private final String selectedContactId;
    private final TextSource source;
    private final Set<ContactId> unlocked;

    public /* synthetic */ CompanionUiState(CompanionMode companionMode, TextSource textSource, Phase phase, String str, boolean z, List list, Set set, String str2, String str3, String str4, String str5, String str6, boolean z2, UiNotice uiNotice, boolean z3, boolean z4, boolean z5, BiometricPromptRequest biometricPromptRequest, DefaultConstructorMarker defaultConstructorMarker) {
        this(companionMode, textSource, phase, str, z, list, set, str2, str3, str4, str5, str6, z2, uiNotice, z3, z4, z5, biometricPromptRequest);
    }

    /* renamed from: copy-gvgolLs$default, reason: not valid java name */
    public static /* synthetic */ CompanionUiState m10481copygvgolLs$default(CompanionUiState companionUiState, CompanionMode companionMode, TextSource textSource, Phase phase, String str, boolean z, List list, Set set, String str2, String str3, String str4, String str5, String str6, boolean z2, UiNotice uiNotice, boolean z3, boolean z4, boolean z5, BiometricPromptRequest biometricPromptRequest, int i, Object obj) {
        BiometricPromptRequest biometricPromptRequest2;
        boolean z6;
        CompanionMode companionMode2 = (i & 1) != 0 ? companionUiState.mode : companionMode;
        TextSource textSource2 = (i & 2) != 0 ? companionUiState.source : textSource;
        Phase phase2 = (i & 4) != 0 ? companionUiState.phase : phase;
        String str7 = (i & 8) != 0 ? companionUiState.incoming : str;
        boolean z7 = (i & 16) != 0 ? companionUiState.readOnly : z;
        List list2 = (i & 32) != 0 ? companionUiState.contacts : list;
        Set set2 = (i & 64) != 0 ? companionUiState.unlocked : set;
        String str8 = (i & 128) != 0 ? companionUiState.selectedContactId : str2;
        String str9 = (i & 256) != 0 ? companionUiState.pendingContactId : str3;
        String str10 = (i & 512) != 0 ? companionUiState.pendingContactName : str4;
        String str11 = (i & 1024) != 0 ? companionUiState.resultText : str5;
        String str12 = (i & 2048) != 0 ? companionUiState.resultContactName : str6;
        boolean z8 = (i & 4096) != 0 ? companionUiState.copied : z2;
        UiNotice uiNotice2 = (i & 8192) != 0 ? companionUiState.notice : uiNotice;
        CompanionMode companionMode3 = companionMode2;
        boolean z9 = (i & 16384) != 0 ? companionUiState.retryable : z3;
        boolean z10 = (i & 32768) != 0 ? companionUiState.busy : z4;
        boolean z11 = (i & 65536) != 0 ? companionUiState.biometricOffered : z5;
        if ((i & 131072) != 0) {
            z6 = z11;
            biometricPromptRequest2 = companionUiState.biometricPrompt;
        } else {
            biometricPromptRequest2 = biometricPromptRequest;
            z6 = z11;
        }
        return companionUiState.m10484copygvgolLs(companionMode3, textSource2, phase2, str7, z7, list2, set2, str8, str9, str10, str11, str12, z8, uiNotice2, z9, z10, z6, biometricPromptRequest2);
    }

    /* renamed from: component1, reason: from getter */
    public final CompanionMode getMode() {
        return this.mode;
    }

    /* renamed from: component10, reason: from getter */
    public final String getPendingContactName() {
        return this.pendingContactName;
    }

    /* renamed from: component11, reason: from getter */
    public final String getResultText() {
        return this.resultText;
    }

    /* renamed from: component12, reason: from getter */
    public final String getResultContactName() {
        return this.resultContactName;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getCopied() {
        return this.copied;
    }

    /* renamed from: component14, reason: from getter */
    public final UiNotice getNotice() {
        return this.notice;
    }

    /* renamed from: component15, reason: from getter */
    public final boolean getRetryable() {
        return this.retryable;
    }

    /* renamed from: component16, reason: from getter */
    public final boolean getBusy() {
        return this.busy;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getBiometricOffered() {
        return this.biometricOffered;
    }

    /* renamed from: component18, reason: from getter */
    public final BiometricPromptRequest getBiometricPrompt() {
        return this.biometricPrompt;
    }

    /* renamed from: component2, reason: from getter */
    public final TextSource getSource() {
        return this.source;
    }

    /* renamed from: component3, reason: from getter */
    public final Phase getPhase() {
        return this.phase;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIncoming() {
        return this.incoming;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final List<Contact> component6() {
        return this.contacts;
    }

    public final Set<ContactId> component7() {
        return this.unlocked;
    }

    /* renamed from: component8-P5lcXoU, reason: not valid java name and from getter */
    public final String getSelectedContactId() {
        return this.selectedContactId;
    }

    /* renamed from: component9-P5lcXoU, reason: not valid java name and from getter */
    public final String getPendingContactId() {
        return this.pendingContactId;
    }

    /* renamed from: copy-gvgolLs, reason: not valid java name */
    public final CompanionUiState m10484copygvgolLs(CompanionMode mode, TextSource source, Phase phase, String incoming, boolean readOnly, List<Contact> contacts, Set<ContactId> unlocked, String selectedContactId, String pendingContactId, String pendingContactName, String resultText, String resultContactName, boolean copied, UiNotice notice, boolean retryable, boolean busy, boolean biometricOffered, BiometricPromptRequest biometricPrompt) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(unlocked, "unlocked");
        return new CompanionUiState(mode, source, phase, incoming, readOnly, contacts, unlocked, selectedContactId, pendingContactId, pendingContactName, resultText, resultContactName, copied, notice, retryable, busy, biometricOffered, biometricPrompt, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean equals(Object other) {
        boolean zM10640equalsimpl0;
        boolean zM10640equalsimpl02;
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompanionUiState)) {
            return false;
        }
        CompanionUiState companionUiState = (CompanionUiState) other;
        if (this.mode != companionUiState.mode || this.source != companionUiState.source || this.phase != companionUiState.phase || !Intrinsics.areEqual(this.incoming, companionUiState.incoming) || this.readOnly != companionUiState.readOnly || !Intrinsics.areEqual(this.contacts, companionUiState.contacts) || !Intrinsics.areEqual(this.unlocked, companionUiState.unlocked)) {
            return false;
        }
        String str = this.selectedContactId;
        String str2 = companionUiState.selectedContactId;
        if (str == null) {
            zM10640equalsimpl0 = str2 == null;
        } else if (str2 != null) {
            zM10640equalsimpl0 = ContactId.m10640equalsimpl0(str, str2);
        }
        if (!zM10640equalsimpl0) {
            return false;
        }
        String str3 = this.pendingContactId;
        String str4 = companionUiState.pendingContactId;
        if (str3 == null) {
            zM10640equalsimpl02 = str4 == null;
        } else if (str4 != null) {
            zM10640equalsimpl02 = ContactId.m10640equalsimpl0(str3, str4);
        }
        return zM10640equalsimpl02 && Intrinsics.areEqual(this.pendingContactName, companionUiState.pendingContactName) && Intrinsics.areEqual(this.resultText, companionUiState.resultText) && Intrinsics.areEqual(this.resultContactName, companionUiState.resultContactName) && this.copied == companionUiState.copied && Intrinsics.areEqual(this.notice, companionUiState.notice) && this.retryable == companionUiState.retryable && this.busy == companionUiState.busy && this.biometricOffered == companionUiState.biometricOffered && Intrinsics.areEqual(this.biometricPrompt, companionUiState.biometricPrompt);
    }

    public int hashCode() {
        int iHashCode = ((((this.mode.hashCode() * 31) + this.source.hashCode()) * 31) + this.phase.hashCode()) * 31;
        String str = this.incoming;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.readOnly)) * 31) + this.contacts.hashCode()) * 31) + this.unlocked.hashCode()) * 31;
        String str2 = this.selectedContactId;
        int iM10641hashCodeimpl = (iHashCode2 + (str2 == null ? 0 : ContactId.m10641hashCodeimpl(str2))) * 31;
        String str3 = this.pendingContactId;
        int iM10641hashCodeimpl2 = (iM10641hashCodeimpl + (str3 == null ? 0 : ContactId.m10641hashCodeimpl(str3))) * 31;
        String str4 = this.pendingContactName;
        int iHashCode3 = (iM10641hashCodeimpl2 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.resultText;
        int iHashCode4 = (iHashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.resultContactName;
        int iHashCode5 = (((iHashCode4 + (str6 == null ? 0 : str6.hashCode())) * 31) + Boolean.hashCode(this.copied)) * 31;
        UiNotice uiNotice = this.notice;
        int iHashCode6 = (((((((iHashCode5 + (uiNotice == null ? 0 : uiNotice.hashCode())) * 31) + Boolean.hashCode(this.retryable)) * 31) + Boolean.hashCode(this.busy)) * 31) + Boolean.hashCode(this.biometricOffered)) * 31;
        BiometricPromptRequest biometricPromptRequest = this.biometricPrompt;
        return iHashCode6 + (biometricPromptRequest != null ? biometricPromptRequest.hashCode() : 0);
    }

    public String toString() {
        CompanionMode companionMode = this.mode;
        TextSource textSource = this.source;
        Phase phase = this.phase;
        String str = this.incoming;
        boolean z = this.readOnly;
        List<Contact> list = this.contacts;
        Set<ContactId> set = this.unlocked;
        String str2 = this.selectedContactId;
        String strM10642toStringimpl = str2 == null ? "null" : ContactId.m10642toStringimpl(str2);
        String str3 = this.pendingContactId;
        return "CompanionUiState(mode=" + companionMode + ", source=" + textSource + ", phase=" + phase + ", incoming=" + str + ", readOnly=" + z + ", contacts=" + list + ", unlocked=" + set + ", selectedContactId=" + strM10642toStringimpl + ", pendingContactId=" + (str3 != null ? ContactId.m10642toStringimpl(str3) : "null") + ", pendingContactName=" + this.pendingContactName + ", resultText=" + this.resultText + ", resultContactName=" + this.resultContactName + ", copied=" + this.copied + ", notice=" + this.notice + ", retryable=" + this.retryable + ", busy=" + this.busy + ", biometricOffered=" + this.biometricOffered + ", biometricPrompt=" + this.biometricPrompt + ")";
    }

    private CompanionUiState(CompanionMode mode, TextSource source, Phase phase, String str, boolean z, List<Contact> contacts, Set<ContactId> unlocked, String str2, String str3, String str4, String str5, String str6, boolean z2, UiNotice uiNotice, boolean z3, boolean z4, boolean z5, BiometricPromptRequest biometricPromptRequest) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(phase, "phase");
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        Intrinsics.checkNotNullParameter(unlocked, "unlocked");
        this.mode = mode;
        this.source = source;
        this.phase = phase;
        this.incoming = str;
        this.readOnly = z;
        this.contacts = contacts;
        this.unlocked = unlocked;
        this.selectedContactId = str2;
        this.pendingContactId = str3;
        this.pendingContactName = str4;
        this.resultText = str5;
        this.resultContactName = str6;
        this.copied = z2;
        this.notice = uiNotice;
        this.retryable = z3;
        this.busy = z4;
        this.biometricOffered = z5;
        this.biometricPrompt = biometricPromptRequest;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CompanionUiState(CompanionMode companionMode, TextSource textSource, Phase phase, String str, boolean z, List list, Set set, String str2, String str3, String str4, String str5, String str6, boolean z2, UiNotice uiNotice, boolean z3, boolean z4, boolean z5, BiometricPromptRequest biometricPromptRequest, int i, DefaultConstructorMarker defaultConstructorMarker) {
        CompanionMode companionMode2 = (i & 1) != 0 ? CompanionMode.ENCRYPT : companionMode;
        TextSource textSource2 = (i & 2) != 0 ? TextSource.SELECTION : textSource;
        Phase phase2 = (i & 4) != 0 ? Phase.Idle : phase;
        String str7 = (i & 8) != 0 ? null : str;
        boolean z6 = (i & 16) != 0 ? false : z;
        List listEmptyList = (i & 32) != 0 ? CollectionsKt.emptyList() : list;
        Set setEmptySet = (i & 64) != 0 ? SetsKt.emptySet() : set;
        String str8 = (i & 128) != 0 ? null : str2;
        String str9 = (i & 256) != 0 ? null : str3;
        String str10 = (i & 512) != 0 ? null : str4;
        String str11 = (i & 1024) != 0 ? null : str5;
        String str12 = (i & 2048) != 0 ? null : str6;
        boolean z7 = (i & 4096) != 0 ? false : z2;
        UiNotice uiNotice2 = (i & 8192) != 0 ? null : uiNotice;
        boolean z8 = (i & 16384) != 0 ? false : z3;
        boolean z9 = (i & 32768) != 0 ? false : z4;
        boolean z10 = (i & 65536) != 0 ? false : z5;
        boolean z11 = z6;
        List list2 = listEmptyList;
        Set set2 = setEmptySet;
        String str13 = str8;
        String str14 = str9;
        String str15 = str10;
        String str16 = str11;
        String str17 = str12;
        boolean z12 = z7;
        this(companionMode2, textSource2, phase2, str7, z11, list2, set2, str13, str14, str15, str16, str17, z12, uiNotice2, z8, z9, z10, (i & 131072) != 0 ? null : biometricPromptRequest, null);
    }

    public final CompanionMode getMode() {
        return this.mode;
    }

    public final TextSource getSource() {
        return this.source;
    }

    public final Phase getPhase() {
        return this.phase;
    }

    public final String getIncoming() {
        return this.incoming;
    }

    public final boolean getReadOnly() {
        return this.readOnly;
    }

    public final List<Contact> getContacts() {
        return this.contacts;
    }

    public final Set<ContactId> getUnlocked() {
        return this.unlocked;
    }

    /* renamed from: getSelectedContactId-P5lcXoU, reason: not valid java name */
    public final String m10486getSelectedContactIdP5lcXoU() {
        return this.selectedContactId;
    }

    /* renamed from: getPendingContactId-P5lcXoU, reason: not valid java name */
    public final String m10485getPendingContactIdP5lcXoU() {
        return this.pendingContactId;
    }

    public final String getPendingContactName() {
        return this.pendingContactName;
    }

    public final String getResultText() {
        return this.resultText;
    }

    public final String getResultContactName() {
        return this.resultContactName;
    }

    public final boolean getCopied() {
        return this.copied;
    }

    public final UiNotice getNotice() {
        return this.notice;
    }

    public final boolean getRetryable() {
        return this.retryable;
    }

    public final boolean getBusy() {
        return this.busy;
    }

    public final boolean getBiometricOffered() {
        return this.biometricOffered;
    }

    public final BiometricPromptRequest getBiometricPrompt() {
        return this.biometricPrompt;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: CompanionViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/cipherkey/app/companion/CompanionUiState$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "Idle", "Prepare", "NeedsUnlock", "Result", "Notice", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Phase {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Phase[] $VALUES;
        public static final Phase Idle = new Phase("Idle", 0);
        public static final Phase Prepare = new Phase("Prepare", 1);
        public static final Phase NeedsUnlock = new Phase("NeedsUnlock", 2);
        public static final Phase Result = new Phase("Result", 3);
        public static final Phase Notice = new Phase("Notice", 4);

        private static final /* synthetic */ Phase[] $values() {
            return new Phase[]{Idle, Prepare, NeedsUnlock, Result, Notice};
        }

        public static EnumEntries<Phase> getEntries() {
            return $ENTRIES;
        }

        public static Phase valueOf(String str) {
            return (Phase) Enum.valueOf(Phase.class, str);
        }

        public static Phase[] values() {
            return (Phase[]) $VALUES.clone();
        }

        static {
            Phase[] phaseArr$values = $values();
            $VALUES = phaseArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(phaseArr$values);
        }

        private Phase(String str, int i) {
        }
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

    public final boolean getSelectedContactUnlocked() {
        Set<ContactId> set = this.unlocked;
        String str = this.selectedContactId;
        return CollectionsKt.contains(set, str != null ? ContactId.m10637boximpl(str) : null);
    }

    public final boolean getInsertable() {
        return ProcessTextContract.INSTANCE.insertable(this.mode, this.source, this.readOnly);
    }
}
