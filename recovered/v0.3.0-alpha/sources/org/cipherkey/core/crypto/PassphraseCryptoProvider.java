package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.cipherkey.core.crypto.CryptoError;
import org.cipherkey.core.crypto.DecryptionResult;
import org.cipherkey.core.crypto.EncryptionResult;

/* compiled from: PassphraseCryptoProvider.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/cipherkey/core/crypto/PassphraseCryptoProvider;", "Lorg/cipherkey/core/crypto/CryptoProvider;", "aead", "Lorg/cipherkey/core/crypto/AeadCipher;", "random", "Lorg/cipherkey/core/crypto/ProtocolRandom;", "<init>", "(Lorg/cipherkey/core/crypto/AeadCipher;Lorg/cipherkey/core/crypto/ProtocolRandom;)V", "encrypt", "Lorg/cipherkey/core/crypto/EncryptionResult;", "plaintext", "", "context", "Lorg/cipherkey/core/crypto/EncryptionContext;", "([BLorg/cipherkey/core/crypto/EncryptionContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decrypt", "Lorg/cipherkey/core/crypto/DecryptionResult;", "message", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "Lorg/cipherkey/core/crypto/DecryptionContext;", "(Lorg/cipherkey/core/crypto/EncryptedMessage;Lorg/cipherkey/core/crypto/DecryptionContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fail", "Lorg/cipherkey/core/crypto/DecryptionResult$Failure;", "error", "Lorg/cipherkey/core/crypto/CryptoError;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PassphraseCryptoProvider implements CryptoProvider {
    private final AeadCipher aead;
    private final ProtocolRandom random;

    /* JADX WARN: Multi-variable type inference failed */
    public PassphraseCryptoProvider() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public PassphraseCryptoProvider(AeadCipher aead, ProtocolRandom random) {
        Intrinsics.checkNotNullParameter(aead, "aead");
        Intrinsics.checkNotNullParameter(random, "random");
        this.aead = aead;
        this.random = random;
    }

    public /* synthetic */ PassphraseCryptoProvider(AesGcmAeadCipher aesGcmAeadCipher, ProtocolRandom protocolRandom, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AesGcmAeadCipher() : aesGcmAeadCipher, (i & 2) != 0 ? new ProtocolRandom(RandomSource.INSTANCE.getSECURE()) : protocolRandom);
    }

    @Override // org.cipherkey.core.crypto.CryptoProvider
    public Object encrypt(byte[] bArr, EncryptionContext encryptionContext, Continuation<? super EncryptionResult> continuation) {
        if (encryptionContext.getMode() != EncryptionMode.PASSPHRASE) {
            throw new IllegalArgumentException("PassphraseCryptoProvider cannot serve mode " + encryptionContext.getMode());
        }
        if (bArr.length > encryptionContext.getMaxPlaintextBytes()) {
            return new EncryptionResult.Failure(CryptoError.InputTooLarge.INSTANCE);
        }
        if (encryptionContext.getKey().length != CipherAlgorithm.AES_256_GCM.getKeyLengthBytes()) {
            return new EncryptionResult.Failure(CryptoError.InvalidKey.INSTANCE);
        }
        if (encryptionContext.getSalt().length != 16) {
            return new EncryptionResult.Failure(CryptoError.InvalidSalt.INSTANCE);
        }
        EncryptionMode encryptionMode = EncryptionMode.PASSPHRASE;
        CipherAlgorithm cipherAlgorithm = CipherAlgorithm.AES_256_GCM;
        KdfAlgorithm kdfAlgorithm = KdfAlgorithm.ARGON2ID;
        byte[] keyId = encryptionContext.getKeyId();
        byte[] bArrCopyOf = Arrays.copyOf(keyId, keyId.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        Argon2Params kdfParams = encryptionContext.getKdfParams();
        byte[] salt = encryptionContext.getSalt();
        byte[] bArrCopyOf2 = Arrays.copyOf(salt, salt.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf2, "copyOf(...)");
        EncryptedMessage encryptedMessage = new EncryptedMessage(encryptionMode, cipherAlgorithm, kdfAlgorithm, bArrCopyOf, kdfParams, bArrCopyOf2, this.random.nonce(), new byte[0]);
        try {
            return new EncryptionResult.Success(EncryptedMessage.copy$default(encryptedMessage, null, null, null, null, null, null, null, this.aead.seal(encryptionContext.getKey(), encryptedMessage.getNonce(), bArr, ProtocolHeader.INSTANCE.serialize(encryptedMessage)), WorkQueueKt.MASK, null));
        } catch (AeadFailure e) {
            return new EncryptionResult.Failure(e.getError());
        }
    }

    @Override // org.cipherkey.core.crypto.CryptoProvider
    public Object decrypt(EncryptedMessage encryptedMessage, DecryptionContext decryptionContext, Continuation<? super DecryptionResult> continuation) {
        if (decryptionContext.getMode() != EncryptionMode.PASSPHRASE) {
            return fail(new CryptoError.UnsupportedMode(decryptionContext.getMode().getId()));
        }
        if (encryptedMessage.getMode() != EncryptionMode.PASSPHRASE) {
            return fail(new CryptoError.UnsupportedMode(encryptedMessage.getMode().getId()));
        }
        if (encryptedMessage.getCiphertext().length > decryptionContext.getMaxSealedBytes()) {
            return fail(CryptoError.MessageTooLarge.INSTANCE);
        }
        if (encryptedMessage.getCiphertext().length < CipherAlgorithm.AES_256_GCM.getTagLengthBytes()) {
            return fail(CryptoError.MalformedMessage.INSTANCE);
        }
        if (decryptionContext.getKey().length != CipherAlgorithm.AES_256_GCM.getKeyLengthBytes()) {
            return fail(CryptoError.InvalidKey.INSTANCE);
        }
        try {
            return new DecryptionResult.Success(this.aead.open(decryptionContext.getKey(), encryptedMessage.getNonce(), encryptedMessage.getCiphertext(), ProtocolHeader.INSTANCE.serialize(encryptedMessage)));
        } catch (AeadFailure e) {
            return fail(e.getError());
        }
    }

    private final DecryptionResult.Failure fail(CryptoError error) {
        return new DecryptionResult.Failure(error);
    }
}
