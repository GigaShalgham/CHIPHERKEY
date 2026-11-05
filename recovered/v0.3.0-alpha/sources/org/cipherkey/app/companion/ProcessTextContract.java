package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.core.messages.Ck1;

/* compiled from: ProcessTextContract.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u001a\u0010\t\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¨\u0006\u0018"}, d2 = {"Lorg/cipherkey/app/companion/ProcessTextContract;", "", "<init>", "()V", "parsePlaintext", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText;", "raw", "", "parseEnvelope", "parse", "limitBytes", "", "isEnvelope", "", TextBundle.TEXT_ENTRY, "", "insertable", "mode", "Lorg/cipherkey/app/companion/CompanionMode;", "source", "Lorg/cipherkey/app/companion/TextSource;", "readOnly", "resultTextOrNull", "SelectedText", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProcessTextContract {
    public static final int $stable = 0;
    public static final ProcessTextContract INSTANCE = new ProcessTextContract();

    private ProcessTextContract() {
    }

    /* compiled from: ProcessTextContract.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText;", "", "Valid", "Empty", "Oversized", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Empty;", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Oversized;", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Valid;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SelectedText {

        /* compiled from: ProcessTextContract.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Valid;", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText;", TextBundle.TEXT_ENTRY, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Valid implements SelectedText {
            public static final int $stable = 0;
            private final String text;

            public static /* synthetic */ Valid copy$default(Valid valid, String str, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = valid.text;
                }
                return valid.copy(str);
            }

            /* renamed from: component1, reason: from getter */
            public final String getText() {
                return this.text;
            }

            public final Valid copy(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                return new Valid(text);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Valid) && Intrinsics.areEqual(this.text, ((Valid) other).text);
            }

            public int hashCode() {
                return this.text.hashCode();
            }

            public String toString() {
                return "Valid(text=" + this.text + ")";
            }

            public Valid(String text) {
                Intrinsics.checkNotNullParameter(text, "text");
                this.text = text;
            }

            public final String getText() {
                return this.text;
            }
        }

        /* compiled from: ProcessTextContract.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Empty;", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Empty implements SelectedText {
            public static final int $stable = 0;
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
                return -1904142918;
            }

            public String toString() {
                return "Empty";
            }

            private Empty() {
            }
        }

        /* compiled from: ProcessTextContract.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText$Oversized;", "Lorg/cipherkey/app/companion/ProcessTextContract$SelectedText;", "limitBytes", "", "<init>", "(I)V", "getLimitBytes", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Oversized implements SelectedText {
            public static final int $stable = 0;
            private final int limitBytes;

            public static /* synthetic */ Oversized copy$default(Oversized oversized, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = oversized.limitBytes;
                }
                return oversized.copy(i);
            }

            /* renamed from: component1, reason: from getter */
            public final int getLimitBytes() {
                return this.limitBytes;
            }

            public final Oversized copy(int limitBytes) {
                return new Oversized(limitBytes);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Oversized) && this.limitBytes == ((Oversized) other).limitBytes;
            }

            public int hashCode() {
                return Integer.hashCode(this.limitBytes);
            }

            public String toString() {
                return "Oversized(limitBytes=" + this.limitBytes + ")";
            }

            public Oversized(int i) {
                this.limitBytes = i;
            }

            public final int getLimitBytes() {
                return this.limitBytes;
            }
        }
    }

    public final SelectedText parsePlaintext(CharSequence raw) {
        return parse(raw, 16384);
    }

    public final SelectedText parseEnvelope(CharSequence raw) {
        return parse(raw, 262144);
    }

    private final SelectedText parse(CharSequence raw, int limitBytes) {
        if (raw == null) {
            return SelectedText.Empty.INSTANCE;
        }
        String string = StringsKt.trim((CharSequence) raw.toString()).toString();
        if (string.length() == 0) {
            return SelectedText.Empty.INSTANCE;
        }
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        if (bytes.length > limitBytes) {
            return new SelectedText.Oversized(limitBytes);
        }
        return new SelectedText.Valid(string);
    }

    public final boolean isEnvelope(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return Ck1.INSTANCE.isEnvelope(text);
    }

    public final boolean insertable(CompanionMode mode, TextSource source, boolean readOnly) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(source, "source");
        if (source != TextSource.SELECTION || readOnly) {
            return false;
        }
        return mode == CompanionMode.ENCRYPT || mode == CompanionMode.DECRYPT;
    }

    public final String resultTextOrNull(String text) {
        if (text == null || text.length() == 0) {
            return null;
        }
        return text;
    }
}
