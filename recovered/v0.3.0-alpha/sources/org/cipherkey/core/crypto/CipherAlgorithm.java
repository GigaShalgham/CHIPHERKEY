package org.cipherkey.core.crypto;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Algorithms.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lorg/cipherkey/core/crypto/CipherAlgorithm;", "", "id", "", "keyLengthBytes", "nonceLengthBytes", "tagLengthBytes", "<init>", "(Ljava/lang/String;IIIII)V", "getId", "()I", "getKeyLengthBytes", "getNonceLengthBytes", "getTagLengthBytes", "AES_256_GCM", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CipherAlgorithm {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CipherAlgorithm[] $VALUES;
    public static final CipherAlgorithm AES_256_GCM = new CipherAlgorithm("AES_256_GCM", 0, 1, 32, 12, 16);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private final int id;
    private final int keyLengthBytes;
    private final int nonceLengthBytes;
    private final int tagLengthBytes;

    private static final /* synthetic */ CipherAlgorithm[] $values() {
        return new CipherAlgorithm[]{AES_256_GCM};
    }

    public static EnumEntries<CipherAlgorithm> getEntries() {
        return $ENTRIES;
    }

    public static CipherAlgorithm valueOf(String str) {
        return (CipherAlgorithm) Enum.valueOf(CipherAlgorithm.class, str);
    }

    public static CipherAlgorithm[] values() {
        return (CipherAlgorithm[]) $VALUES.clone();
    }

    private CipherAlgorithm(String str, int i, int i2, int i3, int i4, int i5) {
        this.id = i2;
        this.keyLengthBytes = i3;
        this.nonceLengthBytes = i4;
        this.tagLengthBytes = i5;
    }

    public final int getId() {
        return this.id;
    }

    public final int getKeyLengthBytes() {
        return this.keyLengthBytes;
    }

    public final int getNonceLengthBytes() {
        return this.nonceLengthBytes;
    }

    public final int getTagLengthBytes() {
        return this.tagLengthBytes;
    }

    static {
        CipherAlgorithm[] cipherAlgorithmArr$values = $values();
        $VALUES = cipherAlgorithmArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(cipherAlgorithmArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: Algorithms.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/crypto/CipherAlgorithm$Companion;", "", "<init>", "()V", "fromId", "Lorg/cipherkey/core/crypto/CipherAlgorithm;", "id", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CipherAlgorithm fromId(int id) {
            CipherAlgorithm next;
            Iterator<CipherAlgorithm> it = CipherAlgorithm.getEntries().iterator();
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
