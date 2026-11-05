package org.cipherkey.security.policies;

import androidx.autofill.HintConstants;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.cipherkey.core.crypto.Argon2Params;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Argon2Profile.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lorg/cipherkey/security/policies/Argon2Profile;", "", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "<init>", "(Ljava/lang/String;ILorg/cipherkey/core/crypto/Argon2Params;)V", "getParams", "()Lorg/cipherkey/core/crypto/Argon2Params;", "INTERACTIVE", "BALANCED", "PARANOID", "Companion", "policies"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Argon2Profile {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Argon2Profile[] $VALUES;
    public static final Argon2Profile BALANCED;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final Argon2Profile DEFAULT;
    public static final Argon2Profile INTERACTIVE = new Argon2Profile("INTERACTIVE", 0, new Argon2Params(14, 3, 1));
    public static final Argon2Profile PARANOID;
    private final Argon2Params params;

    private static final /* synthetic */ Argon2Profile[] $values() {
        return new Argon2Profile[]{INTERACTIVE, BALANCED, PARANOID};
    }

    public static EnumEntries<Argon2Profile> getEntries() {
        return $ENTRIES;
    }

    public static Argon2Profile valueOf(String str) {
        return (Argon2Profile) Enum.valueOf(Argon2Profile.class, str);
    }

    public static Argon2Profile[] values() {
        return (Argon2Profile[]) $VALUES.clone();
    }

    private Argon2Profile(String str, int i, Argon2Params argon2Params) {
        this.params = argon2Params;
    }

    public final Argon2Params getParams() {
        return this.params;
    }

    static {
        Argon2Profile argon2Profile = new Argon2Profile("BALANCED", 1, new Argon2Params(16, 3, 1));
        BALANCED = argon2Profile;
        PARANOID = new Argon2Profile("PARANOID", 2, new Argon2Params(18, 4, 1));
        Argon2Profile[] argon2ProfileArr$values = $values();
        $VALUES = argon2ProfileArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(argon2ProfileArr$values);
        INSTANCE = new Companion(null);
        DEFAULT = argon2Profile;
    }

    /* compiled from: Argon2Profile.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/security/policies/Argon2Profile$Companion;", "", "<init>", "()V", "DEFAULT", "Lorg/cipherkey/security/policies/Argon2Profile;", "getDEFAULT", "()Lorg/cipherkey/security/policies/Argon2Profile;", "fromName", HintConstants.AUTOFILL_HINT_NAME, "", "policies"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Argon2Profile getDEFAULT() {
            return Argon2Profile.DEFAULT;
        }

        public final Argon2Profile fromName(String name) {
            Argon2Profile next;
            Iterator<Argon2Profile> it = Argon2Profile.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (StringsKt.equals(next.name(), name, true)) {
                    break;
                }
            }
            return next;
        }
    }
}
