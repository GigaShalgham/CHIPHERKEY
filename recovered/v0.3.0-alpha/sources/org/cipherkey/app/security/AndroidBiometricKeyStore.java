package org.cipherkey.app.security;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import androidx.biometric.BiometricManager;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: BiometricKeyStore.kt */
@Singleton
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J \u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0016J \u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010H\u0016J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/cipherkey/app/security/AndroidBiometricKeyStore;", "Lorg/cipherkey/app/security/BiometricKeyStore;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "dir", "Ljava/io/File;", "getDir", "()Ljava/io/File;", "isDeviceCapable", "", "hasWrapped", "contactKey", "", "readWrapped", "", "persistWrapped", "", "blob", "clear", "clearAll", "newWrapCipher", "Ljavax/crypto/Cipher;", "newUnwrapCipher", "finishWrap", "cipher", "plain", "aad", "finishUnwrap", "cachedKey", "Ljavax/crypto/SecretKey;", "wrapKey", "generateWrapKey", "fileFor", "Companion", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidBiometricKeyStore implements BiometricKeyStore {

    @Deprecated
    public static final String ANDROID_KEYSTORE = "AndroidKeyStore";

    @Deprecated
    public static final int IV_LENGTH = 12;

    @Deprecated
    public static final int TAG_BITS = 128;

    @Deprecated
    public static final int TAG_LENGTH = 16;

    @Deprecated
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";

    @Deprecated
    public static final String WRAP_KEY_ALIAS = "cipherkey.biometric.v1";
    private volatile SecretKey cachedKey;
    private final Context context;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Inject
    public AndroidBiometricKeyStore(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final File getDir() {
        File file = new File(this.context.getFilesDir(), "biometric");
        file.mkdirs();
        return file;
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public boolean isDeviceCapable() {
        return BiometricManager.from(this.context).canAuthenticate(15) == 0;
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public boolean hasWrapped(String contactKey) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        return fileFor(contactKey).exists();
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public byte[] readWrapped(String contactKey) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        try {
            File fileFileFor = fileFor(contactKey);
            if (fileFileFor.exists()) {
                return FilesKt.readBytes(fileFileFor);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public void persistWrapped(String contactKey, byte[] blob) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        Intrinsics.checkNotNullParameter(blob, "blob");
        try {
            FilesKt.writeBytes(fileFor(contactKey), blob);
        } catch (Exception unused) {
        }
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public void clear(String contactKey) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        fileFor(contactKey).delete();
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public void clearAll() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        File[] fileArrListFiles = getDir().listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            keyStore.deleteEntry(WRAP_KEY_ALIAS);
        } catch (Exception unused) {
        }
        synchronized (this) {
            this.cachedKey = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public Cipher newWrapCipher() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, wrapKey());
            return cipher;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public Cipher newUnwrapCipher(byte[] blob) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(blob, "blob");
        if (blob.length < 28) {
            return null;
        }
        try {
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(blob, 0, 12);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, wrapKey(), new GCMParameterSpec(128, bArrCopyOfRange));
            return cipher;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public byte[] finishWrap(Cipher cipher, byte[] plain, byte[] aad) throws BadPaddingException, IllegalBlockSizeException {
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        Intrinsics.checkNotNullParameter(plain, "plain");
        Intrinsics.checkNotNullParameter(aad, "aad");
        try {
            cipher.updateAAD(aad);
            byte[] bArrDoFinal = cipher.doFinal(plain);
            byte[] iv = cipher.getIV();
            if (iv == null || iv.length != 12) {
                throw new IllegalStateException("bad IV");
            }
            Intrinsics.checkNotNull(bArrDoFinal);
            return ArraysKt.plus(iv, bArrDoFinal);
        } catch (Exception e) {
            throw new IllegalStateException("wrap failed", e);
        }
    }

    @Override // org.cipherkey.app.security.BiometricKeyStore
    public byte[] finishUnwrap(Cipher cipher, byte[] blob, byte[] aad) throws BadPaddingException, IllegalBlockSizeException {
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        Intrinsics.checkNotNullParameter(blob, "blob");
        Intrinsics.checkNotNullParameter(aad, "aad");
        try {
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(blob, 12, blob.length);
            cipher.updateAAD(aad);
            byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange);
            Intrinsics.checkNotNull(bArrDoFinal);
            return bArrDoFinal;
        } catch (Exception e) {
            throw new IllegalStateException("unwrap failed", e);
        }
    }

    private final SecretKey wrapKey() {
        SecretKey secretKeyGenerateWrapKey;
        SecretKey secretKey = this.cachedKey;
        if (secretKey != null) {
            return secretKey;
        }
        synchronized (this) {
            SecretKey secretKey2 = this.cachedKey;
            if (secretKey2 != null) {
                return secretKey2;
            }
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry(WRAP_KEY_ALIAS, null);
            KeyStore.SecretKeyEntry secretKeyEntry = entry instanceof KeyStore.SecretKeyEntry ? (KeyStore.SecretKeyEntry) entry : null;
            if (secretKeyEntry == null || (secretKeyGenerateWrapKey = secretKeyEntry.getSecretKey()) == null) {
                secretKeyGenerateWrapKey = generateWrapKey();
            }
            this.cachedKey = secretKeyGenerateWrapKey;
            return secretKeyGenerateWrapKey;
        }
    }

    private final SecretKey generateWrapKey() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        KeyGenParameterSpec.Builder userAuthenticationRequired = new KeyGenParameterSpec.Builder(WRAP_KEY_ALIAS, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setUserAuthenticationRequired(true);
        Intrinsics.checkNotNullExpressionValue(userAuthenticationRequired, "setUserAuthenticationRequired(...)");
        if (Build.VERSION.SDK_INT >= 30) {
            userAuthenticationRequired.setUserAuthenticationParameters(0, 2);
        } else {
            userAuthenticationRequired.setUserAuthenticationValidityDurationSeconds(-1);
        }
        keyGenerator.init(userAuthenticationRequired.build());
        SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "generateKey(...)");
        return secretKeyGenerateKey;
    }

    private final File fileFor(String contactKey) {
        return new File(getDir(), new Regex("[^A-Za-z0-9_-]").replace(contactKey, "_") + ".bin");
    }

    /* compiled from: BiometricKeyStore.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/cipherkey/app/security/AndroidBiometricKeyStore$Companion;", "", "<init>", "()V", "ANDROID_KEYSTORE", "", "WRAP_KEY_ALIAS", "TRANSFORMATION", "IV_LENGTH", "", "TAG_LENGTH", "TAG_BITS", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
