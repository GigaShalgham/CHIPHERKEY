package org.cipherkey.security.clipboard;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: ClipboardAccess.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardRead;", "", "Text", "Empty", "NonText", "Blocked", "Lorg/cipherkey/security/clipboard/ClipboardRead$Blocked;", "Lorg/cipherkey/security/clipboard/ClipboardRead$Empty;", "Lorg/cipherkey/security/clipboard/ClipboardRead$NonText;", "Lorg/cipherkey/security/clipboard/ClipboardRead$Text;", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ClipboardRead {

    /* compiled from: ClipboardAccess.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardRead$Text;", "Lorg/cipherkey/security/clipboard/ClipboardRead;", TextBundle.TEXT_ENTRY, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Text implements ClipboardRead {
        private final String text;

        public static /* synthetic */ Text copy$default(Text text, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = text.text;
            }
            return text.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        public final Text copy(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new Text(text);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Text) && Intrinsics.areEqual(this.text, ((Text) other).text);
        }

        public int hashCode() {
            return this.text.hashCode();
        }

        public String toString() {
            return "Text(text=" + this.text + ")";
        }

        public Text(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
        }

        public final String getText() {
            return this.text;
        }
    }

    /* compiled from: ClipboardAccess.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardRead$Empty;", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Empty implements ClipboardRead {
        public static final Empty INSTANCE = new Empty();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Empty)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1418856727;
        }

        public String toString() {
            return "Empty";
        }

        private Empty() {
        }
    }

    /* compiled from: ClipboardAccess.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardRead$NonText;", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "mimeType", "", "<init>", "(Ljava/lang/String;)V", "getMimeType", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NonText implements ClipboardRead {
        private final String mimeType;

        public static /* synthetic */ NonText copy$default(NonText nonText, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = nonText.mimeType;
            }
            return nonText.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMimeType() {
            return this.mimeType;
        }

        public final NonText copy(String mimeType) {
            return new NonText(mimeType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NonText) && Intrinsics.areEqual(this.mimeType, ((NonText) other).mimeType);
        }

        public int hashCode() {
            String str = this.mimeType;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "NonText(mimeType=" + this.mimeType + ")";
        }

        public NonText(String str) {
            this.mimeType = str;
        }

        public final String getMimeType() {
            return this.mimeType;
        }
    }

    /* compiled from: ClipboardAccess.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/security/clipboard/ClipboardRead$Blocked;", "Lorg/cipherkey/security/clipboard/ClipboardRead;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "clipboard_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Blocked implements ClipboardRead {
        private final String reason;

        public static /* synthetic */ Blocked copy$default(Blocked blocked, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = blocked.reason;
            }
            return blocked.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final Blocked copy(String reason) {
            return new Blocked(reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Blocked) && Intrinsics.areEqual(this.reason, ((Blocked) other).reason);
        }

        public int hashCode() {
            String str = this.reason;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Blocked(reason=" + this.reason + ")";
        }

        public Blocked(String str) {
            this.reason = str;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
