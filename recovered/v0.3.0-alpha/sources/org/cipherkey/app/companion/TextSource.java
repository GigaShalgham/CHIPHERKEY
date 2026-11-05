package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/app/companion/TextSource;", "", "<init>", "(Ljava/lang/String;I)V", "SELECTION", "SHARE", "CLIPBOARD", "MANUAL", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextSource {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TextSource[] $VALUES;
    public static final TextSource SELECTION = new TextSource("SELECTION", 0);
    public static final TextSource SHARE = new TextSource("SHARE", 1);
    public static final TextSource CLIPBOARD = new TextSource("CLIPBOARD", 2);
    public static final TextSource MANUAL = new TextSource("MANUAL", 3);

    private static final /* synthetic */ TextSource[] $values() {
        return new TextSource[]{SELECTION, SHARE, CLIPBOARD, MANUAL};
    }

    public static EnumEntries<TextSource> getEntries() {
        return $ENTRIES;
    }

    public static TextSource valueOf(String str) {
        return (TextSource) Enum.valueOf(TextSource.class, str);
    }

    public static TextSource[] values() {
        return (TextSource[]) $VALUES.clone();
    }

    static {
        TextSource[] textSourceArr$values = $values();
        $VALUES = textSourceArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(textSourceArr$values);
    }

    private TextSource(String str, int i) {
    }
}
