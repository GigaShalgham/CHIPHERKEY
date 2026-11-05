package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: CryptoProvider.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\rH¦@¢\u0006\u0002\u0010\u000e¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/core/crypto/CryptoProvider;", "", "encrypt", "Lorg/cipherkey/core/crypto/EncryptionResult;", "plaintext", "", "context", "Lorg/cipherkey/core/crypto/EncryptionContext;", "([BLorg/cipherkey/core/crypto/EncryptionContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decrypt", "Lorg/cipherkey/core/crypto/DecryptionResult;", "message", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "Lorg/cipherkey/core/crypto/DecryptionContext;", "(Lorg/cipherkey/core/crypto/EncryptedMessage;Lorg/cipherkey/core/crypto/DecryptionContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CryptoProvider {
    Object decrypt(EncryptedMessage encryptedMessage, DecryptionContext decryptionContext, Continuation<? super DecryptionResult> continuation);

    Object encrypt(byte[] bArr, EncryptionContext encryptionContext, Continuation<? super EncryptionResult> continuation);
}
