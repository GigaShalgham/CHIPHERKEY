package org.cipherkey.security.storage;

import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.keys.KeyProtectionException;
import org.cipherkey.core.keys.KeyProtector;

/* compiled from: AndroidKeystoreKeyProtector.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/cipherkey/security/storage/AndroidKeystoreKeyProtector;", "Lorg/cipherkey/core/keys/KeyProtector;", "<init>", "()V", "wrap", "", "plainKey", "keyId", "unwrap", "wrapped", "cachedKey", "Ljavax/crypto/SecretKey;", "masterKey", "loadOrCreateMasterKey", "Companion", "storage_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidKeystoreKeyProtector implements KeyProtector {

    @Deprecated
    public static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int IV_LENGTH = 12;

    @Deprecated
    public static final String MASTER_KEY_ALIAS = "cipherkey.master.v1";

    @Deprecated
    public static final int TAG_BITS = 128;

    @Deprecated
    public static final int TAG_LENGTH = 16;

    @Deprecated
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private volatile SecretKey cachedKey;

    @Override // org.cipherkey.core.keys.KeyProtector
    public byte[] wrap(byte[] plainKey, byte[] keyId) throws BadPaddingException, KeyProtectionException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(plainKey, "plainKey");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        if (plainKey.length == 0) {
            throw new KeyProtectionException("refusing to wrap empty key", null, 2, null);
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, masterKey());
            cipher.updateAAD(keyId);
            byte[] bArrDoFinal = cipher.doFinal(plainKey);
            byte[] iv = cipher.getIV();
            if (iv != null && iv.length == 12) {
                Intrinsics.checkNotNull(bArrDoFinal);
                return ArraysKt.plus(iv, bArrDoFinal);
            }
            throw new KeyProtectionException("unexpected Keystore IV length " + (iv != null ? Integer.valueOf(iv.length) : null), null, 2, null);
        } catch (KeyProtectionException e) {
            throw e;
        } catch (Exception e2) {
            throw new KeyProtectionException("Keystore wrap failed", e2);
        }
    }

    @Override // org.cipherkey.core.keys.KeyProtector
    public byte[] unwrap(byte[] wrapped, byte[] keyId) throws BadPaddingException, KeyProtectionException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        if (wrapped.length < 28) {
            throw new KeyProtectionException("wrapped blob truncated", null, 2, null);
        }
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(wrapped, 0, 12);
        byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(wrapped, 12, wrapped.length);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, masterKey(), new GCMParameterSpec(128, bArrCopyOfRange));
            cipher.updateAAD(keyId);
            byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
            Intrinsics.checkNotNull(bArrDoFinal);
            return bArrDoFinal;
        } catch (Exception e) {
            throw new KeyProtectionException("unwrap failed (authentication or keyId mismatch)", e);
        }
    }

    private final SecretKey masterKey() {
        SecretKey secretKey = this.cachedKey;
        if (secretKey != null) {
            return secretKey;
        }
        synchronized (this) {
            SecretKey secretKey2 = this.cachedKey;
            if (secretKey2 != null) {
                return secretKey2;
            }
            SecretKey secretKeyLoadOrCreateMasterKey = loadOrCreateMasterKey();
            this.cachedKey = secretKeyLoadOrCreateMasterKey;
            return secretKeyLoadOrCreateMasterKey;
        }
    }

    private final SecretKey loadOrCreateMasterKey() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, UnrecoverableEntryException, InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(MASTER_KEY_ALIAS, null);
        KeyStore.SecretKeyEntry secretKeyEntry = entry instanceof KeyStore.SecretKeyEntry ? (KeyStore.SecretKeyEntry) entry : null;
        if (secretKeyEntry != null) {
            SecretKey secretKey = secretKeyEntry.getSecretKey();
            Intrinsics.checkNotNullExpressionValue(secretKey, "getSecretKey(...)");
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(MASTER_KEY_ALIAS, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "generateKey(...)");
        return secretKeyGenerateKey;
    }

    /* compiled from: AndroidKeystoreKeyProtector.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/cipherkey/security/storage/AndroidKeystoreKeyProtector$Companion;", "", "<init>", "()V", "ANDROID_KEYSTORE", "", "MASTER_KEY_ALIAS", "TRANSFORMATION", "IV_LENGTH", "", "TAG_LENGTH", "TAG_BITS", "storage_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
