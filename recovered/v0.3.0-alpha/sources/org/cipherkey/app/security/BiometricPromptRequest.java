package org.cipherkey.app.security;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BiometricKeyStore.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/app/security/BiometricPromptRequest;", "", "contactKey", "", "getContactKey", "()Ljava/lang/String;", "Unlock", "Remember", "Lorg/cipherkey/app/security/BiometricPromptRequest$Remember;", "Lorg/cipherkey/app/security/BiometricPromptRequest$Unlock;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BiometricPromptRequest {
    String getContactKey();

    /* compiled from: BiometricKeyStore.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/app/security/BiometricPromptRequest$Unlock;", "Lorg/cipherkey/app/security/BiometricPromptRequest;", "contactKey", "", "<init>", "(Ljava/lang/String;)V", "getContactKey", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Unlock implements BiometricPromptRequest {
        public static final int $stable = 0;
        private final String contactKey;

        public static /* synthetic */ Unlock copy$default(Unlock unlock, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = unlock.contactKey;
            }
            return unlock.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getContactKey() {
            return this.contactKey;
        }

        public final Unlock copy(String contactKey) {
            Intrinsics.checkNotNullParameter(contactKey, "contactKey");
            return new Unlock(contactKey);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Unlock) && Intrinsics.areEqual(this.contactKey, ((Unlock) other).contactKey);
        }

        public int hashCode() {
            return this.contactKey.hashCode();
        }

        public String toString() {
            return "Unlock(contactKey=" + this.contactKey + ")";
        }

        public Unlock(String contactKey) {
            Intrinsics.checkNotNullParameter(contactKey, "contactKey");
            this.contactKey = contactKey;
        }

        @Override // org.cipherkey.app.security.BiometricPromptRequest
        public String getContactKey() {
            return this.contactKey;
        }
    }

    /* compiled from: BiometricKeyStore.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/app/security/BiometricPromptRequest$Remember;", "Lorg/cipherkey/app/security/BiometricPromptRequest;", "contactKey", "", "<init>", "(Ljava/lang/String;)V", "getContactKey", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Remember implements BiometricPromptRequest {
        public static final int $stable = 0;
        private final String contactKey;

        public static /* synthetic */ Remember copy$default(Remember remember, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = remember.contactKey;
            }
            return remember.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getContactKey() {
            return this.contactKey;
        }

        public final Remember copy(String contactKey) {
            Intrinsics.checkNotNullParameter(contactKey, "contactKey");
            return new Remember(contactKey);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Remember) && Intrinsics.areEqual(this.contactKey, ((Remember) other).contactKey);
        }

        public int hashCode() {
            return this.contactKey.hashCode();
        }

        public String toString() {
            return "Remember(contactKey=" + this.contactKey + ")";
        }

        public Remember(String contactKey) {
            Intrinsics.checkNotNullParameter(contactKey, "contactKey");
            this.contactKey = contactKey;
        }

        @Override // org.cipherkey.app.security.BiometricPromptRequest
        public String getContactKey() {
            return this.contactKey;
        }
    }
}
