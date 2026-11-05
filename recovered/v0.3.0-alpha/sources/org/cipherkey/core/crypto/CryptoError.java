package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CryptoError.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u000b\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError;", "", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "AuthenticationFailed", "UnsupportedAlgorithm", "UnsupportedKdf", "UnsupportedMode", "UnsupportedVersion", "MalformedMessage", "InvalidNonce", "InvalidSalt", "InvalidKey", "InputTooLarge", "MessageTooLarge", "Lorg/cipherkey/core/crypto/CryptoError$AuthenticationFailed;", "Lorg/cipherkey/core/crypto/CryptoError$InputTooLarge;", "Lorg/cipherkey/core/crypto/CryptoError$InvalidKey;", "Lorg/cipherkey/core/crypto/CryptoError$InvalidNonce;", "Lorg/cipherkey/core/crypto/CryptoError$InvalidSalt;", "Lorg/cipherkey/core/crypto/CryptoError$MalformedMessage;", "Lorg/cipherkey/core/crypto/CryptoError$MessageTooLarge;", "Lorg/cipherkey/core/crypto/CryptoError$UnsupportedAlgorithm;", "Lorg/cipherkey/core/crypto/CryptoError$UnsupportedKdf;", "Lorg/cipherkey/core/crypto/CryptoError$UnsupportedMode;", "Lorg/cipherkey/core/crypto/CryptoError$UnsupportedVersion;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CryptoError {
    private final String message;

    public /* synthetic */ CryptoError(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private CryptoError(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$AuthenticationFailed;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AuthenticationFailed extends CryptoError {
        public static final AuthenticationFailed INSTANCE = new AuthenticationFailed();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AuthenticationFailed)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1442293918;
        }

        public String toString() {
            return "AuthenticationFailed";
        }

        private AuthenticationFailed() {
            super("Authentication failed", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$UnsupportedAlgorithm;", "Lorg/cipherkey/core/crypto/CryptoError;", "id", "", "<init>", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnsupportedAlgorithm extends CryptoError {
        private final int id;

        public static /* synthetic */ UnsupportedAlgorithm copy$default(UnsupportedAlgorithm unsupportedAlgorithm, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = unsupportedAlgorithm.id;
            }
            return unsupportedAlgorithm.copy(i);
        }

        /* renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final UnsupportedAlgorithm copy(int id) {
            return new UnsupportedAlgorithm(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnsupportedAlgorithm) && this.id == ((UnsupportedAlgorithm) other).id;
        }

        public int hashCode() {
            return Integer.hashCode(this.id);
        }

        public String toString() {
            return "UnsupportedAlgorithm(id=" + this.id + ")";
        }

        public UnsupportedAlgorithm(int i) {
            super("Unsupported algorithm id " + i, null);
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$UnsupportedKdf;", "Lorg/cipherkey/core/crypto/CryptoError;", "id", "", "<init>", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnsupportedKdf extends CryptoError {
        private final int id;

        public static /* synthetic */ UnsupportedKdf copy$default(UnsupportedKdf unsupportedKdf, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = unsupportedKdf.id;
            }
            return unsupportedKdf.copy(i);
        }

        /* renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final UnsupportedKdf copy(int id) {
            return new UnsupportedKdf(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnsupportedKdf) && this.id == ((UnsupportedKdf) other).id;
        }

        public int hashCode() {
            return Integer.hashCode(this.id);
        }

        public String toString() {
            return "UnsupportedKdf(id=" + this.id + ")";
        }

        public UnsupportedKdf(int i) {
            super("Unsupported KDF id " + i, null);
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$UnsupportedMode;", "Lorg/cipherkey/core/crypto/CryptoError;", "id", "", "<init>", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnsupportedMode extends CryptoError {
        private final int id;

        public static /* synthetic */ UnsupportedMode copy$default(UnsupportedMode unsupportedMode, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = unsupportedMode.id;
            }
            return unsupportedMode.copy(i);
        }

        /* renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final UnsupportedMode copy(int id) {
            return new UnsupportedMode(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnsupportedMode) && this.id == ((UnsupportedMode) other).id;
        }

        public int hashCode() {
            return Integer.hashCode(this.id);
        }

        public String toString() {
            return "UnsupportedMode(id=" + this.id + ")";
        }

        public UnsupportedMode(int i) {
            super("Unsupported mode id " + i, null);
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$UnsupportedVersion;", "Lorg/cipherkey/core/crypto/CryptoError;", "version", "", "<init>", "(I)V", "getVersion", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnsupportedVersion extends CryptoError {
        private final int version;

        public static /* synthetic */ UnsupportedVersion copy$default(UnsupportedVersion unsupportedVersion, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = unsupportedVersion.version;
            }
            return unsupportedVersion.copy(i);
        }

        /* renamed from: component1, reason: from getter */
        public final int getVersion() {
            return this.version;
        }

        public final UnsupportedVersion copy(int version) {
            return new UnsupportedVersion(version);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnsupportedVersion) && this.version == ((UnsupportedVersion) other).version;
        }

        public int hashCode() {
            return Integer.hashCode(this.version);
        }

        public String toString() {
            return "UnsupportedVersion(version=" + this.version + ")";
        }

        public UnsupportedVersion(int i) {
            super("Unsupported protocol version " + i, null);
            this.version = i;
        }

        public final int getVersion() {
            return this.version;
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$MalformedMessage;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MalformedMessage extends CryptoError {
        public static final MalformedMessage INSTANCE = new MalformedMessage();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalformedMessage)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1042026517;
        }

        public String toString() {
            return "MalformedMessage";
        }

        private MalformedMessage() {
            super("Malformed message", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$InvalidNonce;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class InvalidNonce extends CryptoError {
        public static final InvalidNonce INSTANCE = new InvalidNonce();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvalidNonce)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 426889473;
        }

        public String toString() {
            return "InvalidNonce";
        }

        private InvalidNonce() {
            super("Invalid nonce", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$InvalidSalt;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class InvalidSalt extends CryptoError {
        public static final InvalidSalt INSTANCE = new InvalidSalt();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvalidSalt)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -955925244;
        }

        public String toString() {
            return "InvalidSalt";
        }

        private InvalidSalt() {
            super("Invalid salt", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$InvalidKey;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class InvalidKey extends CryptoError {
        public static final InvalidKey INSTANCE = new InvalidKey();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InvalidKey)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 2047366129;
        }

        public String toString() {
            return "InvalidKey";
        }

        private InvalidKey() {
            super("Invalid key", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$InputTooLarge;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class InputTooLarge extends CryptoError {
        public static final InputTooLarge INSTANCE = new InputTooLarge();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InputTooLarge)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1175801992;
        }

        public String toString() {
            return "InputTooLarge";
        }

        private InputTooLarge() {
            super("Input too large", null);
        }
    }

    /* compiled from: CryptoError.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoError$MessageTooLarge;", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MessageTooLarge extends CryptoError {
        public static final MessageTooLarge INSTANCE = new MessageTooLarge();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MessageTooLarge)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -283332091;
        }

        public String toString() {
            return "MessageTooLarge";
        }

        private MessageTooLarge() {
            super("Message too large", null);
        }
    }
}
