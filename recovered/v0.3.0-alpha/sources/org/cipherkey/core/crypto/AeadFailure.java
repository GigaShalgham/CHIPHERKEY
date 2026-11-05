package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AeadCipher.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/core/crypto/AeadFailure;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "cause", "", "<init>", "(Lorg/cipherkey/core/crypto/CryptoError;Ljava/lang/Throwable;)V", "getError", "()Lorg/cipherkey/core/crypto/CryptoError;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AeadFailure extends Exception {
    private final CryptoError error;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AeadFailure(CryptoError error, Throwable th) {
        super(error.getMessage(), th);
        Intrinsics.checkNotNullParameter(error, "error");
        this.error = error;
    }

    public /* synthetic */ AeadFailure(CryptoError cryptoError, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(cryptoError, (i & 2) != 0 ? null : th);
    }

    public final CryptoError getError() {
        return this.error;
    }
}
