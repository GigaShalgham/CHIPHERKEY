package org.cipherkey.app.security;

import javax.crypto.Cipher;
import kotlin.Metadata;

/* compiled from: BiometricKeyStore.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\r\u001a\u00020\nH&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\bH&J \u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH&J \u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH&¨\u0006\u0016À\u0006\u0003"}, d2 = {"Lorg/cipherkey/app/security/BiometricKeyStore;", "", "isDeviceCapable", "", "hasWrapped", "contactKey", "", "readWrapped", "", "persistWrapped", "", "blob", "clear", "clearAll", "newWrapCipher", "Ljavax/crypto/Cipher;", "newUnwrapCipher", "finishWrap", "cipher", "plain", "aad", "finishUnwrap", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BiometricKeyStore {
    void clear(String contactKey);

    void clearAll();

    byte[] finishUnwrap(Cipher cipher, byte[] blob, byte[] aad);

    byte[] finishWrap(Cipher cipher, byte[] plain, byte[] aad);

    boolean hasWrapped(String contactKey);

    boolean isDeviceCapable();

    Cipher newUnwrapCipher(byte[] blob);

    Cipher newWrapCipher();

    void persistWrapped(String contactKey, byte[] blob);

    byte[] readWrapped(String contactKey);
}
