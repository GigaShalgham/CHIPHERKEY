package org.cipherkey.app.companion;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: CompanionViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/cipherkey/app/companion/CompanionMode;", "", "<init>", "(Ljava/lang/String;I)V", "ENCRYPT", "DECRYPT", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CompanionMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CompanionMode[] $VALUES;
    public static final CompanionMode ENCRYPT = new CompanionMode("ENCRYPT", 0);
    public static final CompanionMode DECRYPT = new CompanionMode("DECRYPT", 1);

    private static final /* synthetic */ CompanionMode[] $values() {
        return new CompanionMode[]{ENCRYPT, DECRYPT};
    }

    public static EnumEntries<CompanionMode> getEntries() {
        return $ENTRIES;
    }

    public static CompanionMode valueOf(String str) {
        return (CompanionMode) Enum.valueOf(CompanionMode.class, str);
    }

    public static CompanionMode[] values() {
        return (CompanionMode[]) $VALUES.clone();
    }

    static {
        CompanionMode[] companionModeArr$values = $values();
        $VALUES = companionModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(companionModeArr$values);
    }

    private CompanionMode(String str, int i) {
    }
}
