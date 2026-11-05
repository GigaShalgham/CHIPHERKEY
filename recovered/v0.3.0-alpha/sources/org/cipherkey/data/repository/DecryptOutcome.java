package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.common.ContactId;
import org.cipherkey.core.crypto.CryptoError;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0082\u0001\u0006\b\t\n\u000b\f\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome;", "", "Decrypted", "NeedsUnlock", "NotAnEnvelope", "UnknownKey", "StaleKeyEra", "Failed", "Lorg/cipherkey/data/repository/DecryptOutcome$Decrypted;", "Lorg/cipherkey/data/repository/DecryptOutcome$Failed;", "Lorg/cipherkey/data/repository/DecryptOutcome$NeedsUnlock;", "Lorg/cipherkey/data/repository/DecryptOutcome$NotAnEnvelope;", "Lorg/cipherkey/data/repository/DecryptOutcome$StaleKeyEra;", "Lorg/cipherkey/data/repository/DecryptOutcome$UnknownKey;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface DecryptOutcome {

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0010\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001c"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$Decrypted;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "plaintext", "", "contactId", "Lorg/cipherkey/core/common/ContactId;", "contactName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPlaintext", "()Ljava/lang/String;", "getContactId-Y-aFaGk", "Ljava/lang/String;", "getContactName", "component1", "component2", "component2-Y-aFaGk", "component3", "copy", "copy-ulT75bg", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/cipherkey/data/repository/DecryptOutcome$Decrypted;", "equals", "", "other", "", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Decrypted implements DecryptOutcome {
        private final String contactId;
        private final String contactName;
        private final String plaintext;

        public /* synthetic */ Decrypted(String str, String str2, String str3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3);
        }

        /* renamed from: copy-ulT75bg$default, reason: not valid java name */
        public static /* synthetic */ Decrypted m10676copyulT75bg$default(Decrypted decrypted, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = decrypted.plaintext;
            }
            if ((i & 2) != 0) {
                str2 = decrypted.contactId;
            }
            if ((i & 4) != 0) {
                str3 = decrypted.contactName;
            }
            return decrypted.m10678copyulT75bg(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPlaintext() {
            return this.plaintext;
        }

        /* renamed from: component2-Y-aFaGk, reason: not valid java name and from getter */
        public final String getContactId() {
            return this.contactId;
        }

        /* renamed from: component3, reason: from getter */
        public final String getContactName() {
            return this.contactName;
        }

        /* renamed from: copy-ulT75bg, reason: not valid java name */
        public final Decrypted m10678copyulT75bg(String plaintext, String contactId, String contactName) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
            Intrinsics.checkNotNullParameter(contactName, "contactName");
            return new Decrypted(plaintext, contactId, contactName, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Decrypted)) {
                return false;
            }
            Decrypted decrypted = (Decrypted) other;
            return Intrinsics.areEqual(this.plaintext, decrypted.plaintext) && ContactId.m10640equalsimpl0(this.contactId, decrypted.contactId) && Intrinsics.areEqual(this.contactName, decrypted.contactName);
        }

        public int hashCode() {
            return (((this.plaintext.hashCode() * 31) + ContactId.m10641hashCodeimpl(this.contactId)) * 31) + this.contactName.hashCode();
        }

        public String toString() {
            return "Decrypted(plaintext=" + this.plaintext + ", contactId=" + ContactId.m10642toStringimpl(this.contactId) + ", contactName=" + this.contactName + ")";
        }

        private Decrypted(String plaintext, String contactId, String contactName) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            Intrinsics.checkNotNullParameter(contactId, "contactId");
            Intrinsics.checkNotNullParameter(contactName, "contactName");
            this.plaintext = plaintext;
            this.contactId = contactId;
            this.contactName = contactName;
        }

        public final String getPlaintext() {
            return this.plaintext;
        }

        /* renamed from: getContactId-Y-aFaGk, reason: not valid java name */
        public final String m10679getContactIdYaFaGk() {
            return this.contactId;
        }

        public final String getContactName() {
            return this.contactName;
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\r\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0019"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$NeedsUnlock;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "contactId", "Lorg/cipherkey/core/common/ContactId;", "contactName", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContactId-Y-aFaGk", "()Ljava/lang/String;", "Ljava/lang/String;", "getContactName", "component1", "component1-Y-aFaGk", "component2", "copy", "copy-ovFUVq0", "(Ljava/lang/String;Ljava/lang/String;)Lorg/cipherkey/data/repository/DecryptOutcome$NeedsUnlock;", "equals", "", "other", "", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NeedsUnlock implements DecryptOutcome {
        private final String contactId;
        private final String contactName;

        public /* synthetic */ NeedsUnlock(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2);
        }

        /* renamed from: copy-ovFUVq0$default, reason: not valid java name */
        public static /* synthetic */ NeedsUnlock m10680copyovFUVq0$default(NeedsUnlock needsUnlock, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = needsUnlock.contactId;
            }
            if ((i & 2) != 0) {
                str2 = needsUnlock.contactName;
            }
            return needsUnlock.m10682copyovFUVq0(str, str2);
        }

        /* renamed from: component1-Y-aFaGk, reason: not valid java name and from getter */
        public final String getContactId() {
            return this.contactId;
        }

        /* renamed from: component2, reason: from getter */
        public final String getContactName() {
            return this.contactName;
        }

        /* renamed from: copy-ovFUVq0, reason: not valid java name */
        public final NeedsUnlock m10682copyovFUVq0(String contactId, String contactName) {
            Intrinsics.checkNotNullParameter(contactId, "$v$c$org-cipherkey-core-common-ContactId$-contactId$0");
            Intrinsics.checkNotNullParameter(contactName, "contactName");
            return new NeedsUnlock(contactId, contactName, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NeedsUnlock)) {
                return false;
            }
            NeedsUnlock needsUnlock = (NeedsUnlock) other;
            return ContactId.m10640equalsimpl0(this.contactId, needsUnlock.contactId) && Intrinsics.areEqual(this.contactName, needsUnlock.contactName);
        }

        public int hashCode() {
            return (ContactId.m10641hashCodeimpl(this.contactId) * 31) + this.contactName.hashCode();
        }

        public String toString() {
            return "NeedsUnlock(contactId=" + ContactId.m10642toStringimpl(this.contactId) + ", contactName=" + this.contactName + ")";
        }

        private NeedsUnlock(String contactId, String contactName) {
            Intrinsics.checkNotNullParameter(contactId, "contactId");
            Intrinsics.checkNotNullParameter(contactName, "contactName");
            this.contactId = contactId;
            this.contactName = contactName;
        }

        /* renamed from: getContactId-Y-aFaGk, reason: not valid java name */
        public final String m10683getContactIdYaFaGk() {
            return this.contactId;
        }

        public final String getContactName() {
            return this.contactName;
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$NotAnEnvelope;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NotAnEnvelope implements DecryptOutcome {
        public static final NotAnEnvelope INSTANCE = new NotAnEnvelope();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotAnEnvelope)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1782733963;
        }

        public String toString() {
            return "NotAnEnvelope";
        }

        private NotAnEnvelope() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$UnknownKey;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnknownKey implements DecryptOutcome {
        public static final UnknownKey INSTANCE = new UnknownKey();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UnknownKey)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 484295002;
        }

        public String toString() {
            return "UnknownKey";
        }

        private UnknownKey() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$StaleKeyEra;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StaleKeyEra implements DecryptOutcome {
        public static final StaleKeyEra INSTANCE = new StaleKeyEra();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StaleKeyEra)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 400362601;
        }

        public String toString() {
            return "StaleKeyEra";
        }

        private StaleKeyEra() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/data/repository/DecryptOutcome$Failed;", "Lorg/cipherkey/data/repository/DecryptOutcome;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "(Lorg/cipherkey/core/crypto/CryptoError;)V", "getError", "()Lorg/cipherkey/core/crypto/CryptoError;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failed implements DecryptOutcome {
        private final CryptoError error;

        public static /* synthetic */ Failed copy$default(Failed failed, CryptoError cryptoError, int i, Object obj) {
            if ((i & 1) != 0) {
                cryptoError = failed.error;
            }
            return failed.copy(cryptoError);
        }

        /* renamed from: component1, reason: from getter */
        public final CryptoError getError() {
            return this.error;
        }

        public final Failed copy(CryptoError error) {
            Intrinsics.checkNotNullParameter(error, "error");
            return new Failed(error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failed) && Intrinsics.areEqual(this.error, ((Failed) other).error);
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "Failed(error=" + this.error + ")";
        }

        public Failed(CryptoError error) {
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }

        public final CryptoError getError() {
            return this.error;
        }
    }
}
