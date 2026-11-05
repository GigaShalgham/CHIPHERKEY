package org.cipherkey.core.crypto;

import kotlin.Metadata;

/* compiled from: AeadCipher.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H&J(\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/core/crypto/AeadCipher;", "", "seal", "", "key", "nonce", "plaintext", "associatedData", "open", "sealed", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface AeadCipher {
    byte[] open(byte[] key, byte[] nonce, byte[] sealed, byte[] associatedData);

    byte[] seal(byte[] key, byte[] nonce, byte[] plaintext, byte[] associatedData);
}
