package org.cipherkey.core.crypto;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.crypto.CryptoError;

/* compiled from: AeadCipher.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J(\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lorg/cipherkey/core/crypto/AesGcmAeadCipher;", "Lorg/cipherkey/core/crypto/AeadCipher;", "<init>", "()V", "seal", "", "key", "nonce", "plaintext", "associatedData", "open", "sealed", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AesGcmAeadCipher implements AeadCipher {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int TAG_BITS = 128;

    @Deprecated
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";

    @Override // org.cipherkey.core.crypto.AeadCipher
    public byte[] seal(byte[] key, byte[] nonce, byte[] plaintext, byte[] associatedData) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, AeadFailure, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(nonce, "nonce");
        Intrinsics.checkNotNullParameter(plaintext, "plaintext");
        Intrinsics.checkNotNullParameter(associatedData, "associatedData");
        if (key.length != CipherAlgorithm.AES_256_GCM.getKeyLengthBytes()) {
            throw new AeadFailure(CryptoError.InvalidKey.INSTANCE, null, 2, null);
        }
        if (nonce.length != CipherAlgorithm.AES_256_GCM.getNonceLengthBytes()) {
            throw new AeadFailure(CryptoError.InvalidNonce.INSTANCE, null, 2, null);
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, nonce));
            cipher.updateAAD(associatedData);
            byte[] bArrDoFinal = cipher.doFinal(plaintext);
            Intrinsics.checkNotNull(bArrDoFinal);
            return bArrDoFinal;
        } catch (IllegalStateException e) {
            throw new AeadFailure(CryptoError.InputTooLarge.INSTANCE, e);
        } catch (GeneralSecurityException e2) {
            throw new AeadFailure(CryptoError.InputTooLarge.INSTANCE, e2);
        }
    }

    @Override // org.cipherkey.core.crypto.AeadCipher
    public byte[] open(byte[] key, byte[] nonce, byte[] sealed, byte[] associatedData) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, AeadFailure, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(nonce, "nonce");
        Intrinsics.checkNotNullParameter(sealed, "sealed");
        Intrinsics.checkNotNullParameter(associatedData, "associatedData");
        if (key.length != CipherAlgorithm.AES_256_GCM.getKeyLengthBytes()) {
            throw new AeadFailure(CryptoError.InvalidKey.INSTANCE, null, 2, null);
        }
        if (nonce.length != CipherAlgorithm.AES_256_GCM.getNonceLengthBytes()) {
            throw new AeadFailure(CryptoError.InvalidNonce.INSTANCE, null, 2, null);
        }
        if (sealed.length < CipherAlgorithm.AES_256_GCM.getTagLengthBytes()) {
            throw new AeadFailure(CryptoError.MalformedMessage.INSTANCE, null, 2, null);
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, nonce));
            cipher.updateAAD(associatedData);
            byte[] bArrDoFinal = cipher.doFinal(sealed);
            Intrinsics.checkNotNull(bArrDoFinal);
            return bArrDoFinal;
        } catch (GeneralSecurityException e) {
            throw new AeadFailure(CryptoError.AuthenticationFailed.INSTANCE, e);
        }
    }

    /* compiled from: AeadCipher.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/crypto/AesGcmAeadCipher$Companion;", "", "<init>", "()V", "TRANSFORMATION", "", "TAG_BITS", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
