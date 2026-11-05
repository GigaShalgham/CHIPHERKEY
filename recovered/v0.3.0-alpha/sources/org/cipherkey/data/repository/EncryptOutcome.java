package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.crypto.CryptoError;

/* compiled from: MessageCipher.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0005\u0007\b\t\n\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome;", "", "Encrypted", "NeedsUnlock", "NoSession", "EmptyDraft", "Failed", "Lorg/cipherkey/data/repository/EncryptOutcome$EmptyDraft;", "Lorg/cipherkey/data/repository/EncryptOutcome$Encrypted;", "Lorg/cipherkey/data/repository/EncryptOutcome$Failed;", "Lorg/cipherkey/data/repository/EncryptOutcome$NeedsUnlock;", "Lorg/cipherkey/data/repository/EncryptOutcome$NoSession;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface EncryptOutcome {

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome$Encrypted;", "Lorg/cipherkey/data/repository/EncryptOutcome;", "envelope", "", "<init>", "(Ljava/lang/String;)V", "getEnvelope", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Encrypted implements EncryptOutcome {
        private final String envelope;

        public static /* synthetic */ Encrypted copy$default(Encrypted encrypted, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = encrypted.envelope;
            }
            return encrypted.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getEnvelope() {
            return this.envelope;
        }

        public final Encrypted copy(String envelope) {
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            return new Encrypted(envelope);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Encrypted) && Intrinsics.areEqual(this.envelope, ((Encrypted) other).envelope);
        }

        public int hashCode() {
            return this.envelope.hashCode();
        }

        public String toString() {
            return "Encrypted(envelope=" + this.envelope + ")";
        }

        public Encrypted(String envelope) {
            Intrinsics.checkNotNullParameter(envelope, "envelope");
            this.envelope = envelope;
        }

        public final String getEnvelope() {
            return this.envelope;
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome$NeedsUnlock;", "Lorg/cipherkey/data/repository/EncryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NeedsUnlock implements EncryptOutcome {
        public static final NeedsUnlock INSTANCE = new NeedsUnlock();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NeedsUnlock)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1899133596;
        }

        public String toString() {
            return "NeedsUnlock";
        }

        private NeedsUnlock() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome$NoSession;", "Lorg/cipherkey/data/repository/EncryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NoSession implements EncryptOutcome {
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
            return -856848520;
        }

        public String toString() {
            return "NoSession";
        }

        private NoSession() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome$EmptyDraft;", "Lorg/cipherkey/data/repository/EncryptOutcome;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EmptyDraft implements EncryptOutcome {
        public static final EmptyDraft INSTANCE = new EmptyDraft();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EmptyDraft)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -145767727;
        }

        public String toString() {
            return "EmptyDraft";
        }

        private EmptyDraft() {
        }
    }

    /* compiled from: MessageCipher.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/data/repository/EncryptOutcome$Failed;", "Lorg/cipherkey/data/repository/EncryptOutcome;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "(Lorg/cipherkey/core/crypto/CryptoError;)V", "getError", "()Lorg/cipherkey/core/crypto/CryptoError;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failed implements EncryptOutcome {
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
