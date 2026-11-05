package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CryptoProvider.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/crypto/DecryptionResult;", "", "<init>", "()V", "Success", "Failure", "Lorg/cipherkey/core/crypto/DecryptionResult$Failure;", "Lorg/cipherkey/core/crypto/DecryptionResult$Success;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class DecryptionResult {
    public /* synthetic */ DecryptionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: CryptoProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/core/crypto/DecryptionResult$Success;", "Lorg/cipherkey/core/crypto/DecryptionResult;", "plaintext", "", "<init>", "([B)V", "getPlaintext", "()[B", "equals", "", "other", "", "hashCode", "", "component1", "copy", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success extends DecryptionResult {
        private final byte[] plaintext;

        public static /* synthetic */ Success copy$default(Success success, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = success.plaintext;
            }
            return success.copy(bArr);
        }

        /* renamed from: component1, reason: from getter */
        public final byte[] getPlaintext() {
            return this.plaintext;
        }

        public final Success copy(byte[] plaintext) {
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            return new Success(plaintext);
        }

        public boolean equals(Object other) {
            return this == other;
        }

        public String toString() {
            return "Success(plaintext=" + Arrays.toString(this.plaintext) + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(byte[] plaintext) {
            super(null);
            Intrinsics.checkNotNullParameter(plaintext, "plaintext");
            this.plaintext = plaintext;
        }

        public final byte[] getPlaintext() {
            return this.plaintext;
        }

        public int hashCode() {
            return Arrays.hashCode(this.plaintext);
        }
    }

    private DecryptionResult() {
    }

    /* compiled from: CryptoProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/cipherkey/core/crypto/DecryptionResult$Failure;", "Lorg/cipherkey/core/crypto/DecryptionResult;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "<init>", "(Lorg/cipherkey/core/crypto/CryptoError;)V", "getError", "()Lorg/cipherkey/core/crypto/CryptoError;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failure extends DecryptionResult {
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
