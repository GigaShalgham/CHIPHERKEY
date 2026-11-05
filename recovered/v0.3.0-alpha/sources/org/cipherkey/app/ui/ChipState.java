package org.cipherkey.app.ui;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Components.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/cipherkey/app/ui/ChipState;", "", "<init>", "(Ljava/lang/String;I)V", "SUCCESS", "WARNING", "NEUTRAL", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChipState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ChipState[] $VALUES;
    public static final ChipState SUCCESS = new ChipState("SUCCESS", 0);
    public static final ChipState WARNING = new ChipState("WARNING", 1);
    public static final ChipState NEUTRAL = new ChipState("NEUTRAL", 2);

    private static final /* synthetic */ ChipState[] $values() {
        return new ChipState[]{SUCCESS, WARNING, NEUTRAL};
    }

    public static EnumEntries<ChipState> getEntries() {
        return $ENTRIES;
    }

    public static ChipState valueOf(String str) {
        return (ChipState) Enum.valueOf(ChipState.class, str);
    }

    public static ChipState[] values() {
        return (ChipState[]) $VALUES.clone();
    }

    static {
        ChipState[] chipStateArr$values = $values();
        $VALUES = chipStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(chipStateArr$values);
    }

    private ChipState(String str, int i) {
    }
}
