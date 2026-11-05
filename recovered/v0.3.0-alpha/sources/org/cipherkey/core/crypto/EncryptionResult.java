package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptoProvider.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionResult;", "", "<init>", "()V", "Success", "Failure", "Lorg/cipherkey/core/crypto/EncryptionResult$Failure;", "Lorg/cipherkey/core/crypto/EncryptionResult$Success;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class EncryptionResult {
    public /* synthetic */ EncryptionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: CryptoProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionResult$Success;", "Lorg/cipherkey/core/crypto/EncryptionResult;", "message", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "<init>", "(Lorg/cipherkey/core/crypto/EncryptedMessage;)V", "getMessage", "()Lorg/cipherkey/core/crypto/EncryptedMessage;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success extends EncryptionResult {
        private final EncryptedMessage message;

        public static /* synthetic */ Success copy$default(Success success, EncryptedMessage encryptedMessage, int i, Object obj) {
            if ((i & 1) != 0) {
                encryptedMessage = success.message;
            }
            return success.copy(encryptedMessage);
        }

        /* renamed from: component1, reason: from getter */
        public final EncryptedMessage getMessage() {
            return this.message;
        }

        public final Success copy(EncryptedMessage message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new Success(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.message, ((Success) other).message);
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return "Success(message=" + this.message + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(EncryptedMessage message) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        public final EncryptedMessage getMessage() {
            return this.message;
        }
    }

    private EncryptionResult() {
    }

    /* compiled from: CryptoProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/core/crypto/EncryptionResult$Failure;", "Lorg/cipherkey/core/crypto/EncryptionResult;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "(Lorg/cipherkey/core/crypto/CryptoError;)V", "getError", "()Lorg/cipherkey/core/crypto/CryptoError;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failure extends EncryptionResult {
        private final CryptoError error;

        public static /* synthetic */ Failure copy$default(Failure failure, CryptoError cryptoError, int i, Object obj) {
            if ((i & 1) != 0) {
                cryptoError = failure.error;
            }
            return failure.copy(cryptoError);
        }

        /* renamed from: component1, reason: from getter */
        public final CryptoError getError() {
            return this.error;
        }

        public final Failure copy(CryptoError error) {
            Intrinsics.checkNotNullParameter(error, "error");
            return new Failure(error);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failure) && Intrinsics.areEqual(this.error, ((Failure) other).error);
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public String toString() {
            return "Failure(error=" + this.error + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failure(CryptoError error) {
            super(null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }

        public final CryptoError getError() {
            return this.error;
        }
    }
}
