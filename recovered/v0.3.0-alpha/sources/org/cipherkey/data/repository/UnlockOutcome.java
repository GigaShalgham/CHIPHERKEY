package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0082\u0001\u0006\b\t\n\u000b\f\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome;", "", "Unlocked", "AlreadyUnlocked", "WrongPassphrase", "NoSession", "Rejected", "Failed", "Lorg/cipherkey/data/repository/UnlockOutcome$AlreadyUnlocked;", "Lorg/cipherkey/data/repository/UnlockOutcome$Failed;", "Lorg/cipherkey/data/repository/UnlockOutcome$NoSession;", "Lorg/cipherkey/data/repository/UnlockOutcome$Rejected;", "Lorg/cipherkey/data/repository/UnlockOutcome$Unlocked;", "Lorg/cipherkey/data/repository/UnlockOutcome$WrongPassphrase;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface UnlockOutcome {

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$Unlocked;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Unlocked implements UnlockOutcome {
        public static final Unlocked INSTANCE = new Unlocked();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Unlocked)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -618002425;
        }

        public String toString() {
            return "Unlocked";
        }

        private Unlocked() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$AlreadyUnlocked;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AlreadyUnlocked implements UnlockOutcome {
        public static final AlreadyUnlocked INSTANCE = new AlreadyUnlocked();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlreadyUnlocked)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1538901577;
        }

        public String toString() {
            return "AlreadyUnlocked";
        }

        private AlreadyUnlocked() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$WrongPassphrase;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class WrongPassphrase implements UnlockOutcome {
        public static final WrongPassphrase INSTANCE = new WrongPassphrase();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WrongPassphrase)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -560424461;
        }

        public String toString() {
            return "WrongPassphrase";
        }

        private WrongPassphrase() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$NoSession;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NoSession implements UnlockOutcome {
        public static final NoSession INSTANCE = new NoSession();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NoSession)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1452144399;
        }

        public String toString() {
            return "NoSession";
        }

        private NoSession() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$Rejected;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Rejected implements UnlockOutcome {
        private final String reason;

        public static /* synthetic */ Rejected copy$default(Rejected rejected, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = rejected.reason;
            }
            return rejected.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final Rejected copy(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Rejected(reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Rejected) && Intrinsics.areEqual(this.reason, ((Rejected) other).reason);
        }

        public int hashCode() {
            return this.reason.hashCode();
        }

        public String toString() {
            return "Rejected(reason=" + this.reason + ")";
        }

        public Rejected(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        public final String getReason() {
            return this.reason;
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/data/repository/UnlockOutcome$Failed;", "Lorg/cipherkey/data/repository/UnlockOutcome;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failed implements UnlockOutcome {
        private final String reason;

        public static /* synthetic */ Failed copy$default(Failed failed, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = failed.reason;
            }
            return failed.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final Failed copy(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Failed(reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failed) && Intrinsics.areEqual(this.reason, ((Failed) other).reason);
        }

        public int hashCode() {
            return this.reason.hashCode();
        }

        public String toString() {
            return "Failed(reason=" + this.reason + ")";
        }

        public Failed(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
