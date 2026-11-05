package org.cipherkey.core.crypto;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Algorithms.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionMode;", "", "id", "", "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "PASSPHRASE", "PUBLIC_KEY", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EncryptionMode {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ EncryptionMode[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final EncryptionMode PASSPHRASE = new EncryptionMode("PASSPHRASE", 0, 1);
    public static final EncryptionMode PUBLIC_KEY = new EncryptionMode("PUBLIC_KEY", 1, 2);
    private final int id;

    private static final /* synthetic */ EncryptionMode[] $values() {
        return new EncryptionMode[]{PASSPHRASE, PUBLIC_KEY};
    }

    public static EnumEntries<EncryptionMode> getEntries() {
        return $ENTRIES;
    }

    public static EncryptionMode valueOf(String str) {
        return (EncryptionMode) Enum.valueOf(EncryptionMode.class, str);
    }

    public static EncryptionMode[] values() {
        return (EncryptionMode[]) $VALUES.clone();
    }

    private EncryptionMode(String str, int i, int i2) {
        this.id = i2;
    }

    public final int getId() {
        return this.id;
    }

    static {
        EncryptionMode[] encryptionModeArr$values = $values();
        $VALUES = encryptionModeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(encryptionModeArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: Algorithms.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionMode$Companion;", "", "<init>", "()V", "fromId", "Lorg/cipherkey/core/crypto/EncryptionMode;", "id", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EncryptionMode fromId(int id) {
            EncryptionMode next;
            Iterator<EncryptionMode> it = EncryptionMode.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (next.getId() == id) {
                    break;
                }
            }
            return next;
        }
    }
}
