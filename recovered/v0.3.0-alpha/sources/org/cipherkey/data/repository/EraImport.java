package org.cipherkey.data.repository;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SessionKeyRepository.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/data/repository/EraImport;", "", "Imported", "AlreadyPresent", "KeyBelongsToOtherContact", "Lorg/cipherkey/data/repository/EraImport$AlreadyPresent;", "Lorg/cipherkey/data/repository/EraImport$Imported;", "Lorg/cipherkey/data/repository/EraImport$KeyBelongsToOtherContact;", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface EraImport {

    /* compiled from: SessionKeyRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/data/repository/EraImport$Imported;", "Lorg/cipherkey/data/repository/EraImport;", "era", "Lorg/cipherkey/data/repository/ContactSession;", "<init>", "(Lorg/cipherkey/data/repository/ContactSession;)V", "getEra", "()Lorg/cipherkey/data/repository/ContactSession;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Imported implements EraImport {
        private final ContactSession era;

        public static /* synthetic */ Imported copy$default(Imported imported, ContactSession contactSession, int i, Object obj) {
            if ((i & 1) != 0) {
                contactSession = imported.era;
            }
            return imported.copy(contactSession);
        }

        /* renamed from: component1, reason: from getter */
        public final ContactSession getEra() {
            return this.era;
        }

        public final Imported copy(ContactSession era) {
            Intrinsics.checkNotNullParameter(era, "era");
            return new Imported(era);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Imported) && Intrinsics.areEqual(this.era, ((Imported) other).era);
        }

        public int hashCode() {
            return this.era.hashCode();
        }

        public String toString() {
            return "Imported(era=" + this.era + ")";
        }

        public Imported(ContactSession era) {
            Intrinsics.checkNotNullParameter(era, "era");
            this.era = era;
        }

        public final ContactSession getEra() {
            return this.era;
        }
    }

    /* compiled from: SessionKeyRepository.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/data/repository/EraImport$AlreadyPresent;", "Lorg/cipherkey/data/repository/EraImport;", "era", "Lorg/cipherkey/data/repository/ContactSession;", "<init>", "(Lorg/cipherkey/data/repository/ContactSession;)V", "getEra", "()Lorg/cipherkey/data/repository/ContactSession;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AlreadyPresent implements EraImport {
        private final ContactSession era;

        public static /* synthetic */ AlreadyPresent copy$default(AlreadyPresent alreadyPresent, ContactSession contactSession, int i, Object obj) {
            if ((i & 1) != 0) {
                contactSession = alreadyPresent.era;
            }
            return alreadyPresent.copy(contactSession);
        }

        /* renamed from: component1, reason: from getter */
        public final ContactSession getEra() {
            return this.era;
        }

        public final AlreadyPresent copy(ContactSession era) {
            Intrinsics.checkNotNullParameter(era, "era");
            return new AlreadyPresent(era);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AlreadyPresent) && Intrinsics.areEqual(this.era, ((AlreadyPresent) other).era);
        }

        public int hashCode() {
            return this.era.hashCode();
        }

        public String toString() {
            return "AlreadyPresent(era=" + this.era + ")";
        }

        public AlreadyPresent(ContactSession era) {
            Intrinsics.checkNotNullParameter(era, "era");
            this.era = era;
        }

        public final ContactSession getEra() {
            return this.era;
        }
    }

    /* compiled from: SessionKeyRepository.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/data/repository/EraImport$KeyBelongsToOtherContact;", "Lorg/cipherkey/data/repository/EraImport;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "repository_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class KeyBelongsToOtherContact implements EraImport {
        public static final KeyBelongsToOtherContact INSTANCE = new KeyBelongsToOtherContact();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KeyBelongsToOtherContact)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -1070672135;
        }

        public String toString() {
            return "KeyBelongsToOtherContact";
        }

        private KeyBelongsToOtherContact() {
        }
    }
}
