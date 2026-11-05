package org.cipherkey.core.keys;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FixedKeyProtector.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/cipherkey/core/keys/FixedKeyProtector;", "Lorg/cipherkey/core/keys/KeyProtector;", "keyBytes", "", "<init>", "([B)V", "key", "wrap", "plainKey", "keyId", "unwrap", "wrapped", "Companion", "keys"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FixedKeyProtector implements KeyProtector {

    @Deprecated
    public static final int NONCE_LENGTH = 12;

    @Deprecated
    public static final int TAG_LENGTH = 16;

    @Deprecated
    public static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final byte[] key;
    private static final Companion Companion = new Companion(null);
    private static final SecureRandom SECURE = new SecureRandom();

    /* JADX WARN: Multi-variable type inference failed */
    public FixedKeyProtector() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public FixedKeyProtector(byte[] bArr) {
        byte[] bArrCopyOf;
        if (bArr != null) {
            bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            if (bArrCopyOf != null) {
                if (bArrCopyOf.length != 32) {
                    throw new IllegalArgumentException("FixedKeyProtector requires a 256-bit key".toString());
                }
            } else {
                bArrCopyOf = new byte[32];
                SECURE.nextBytes(bArrCopyOf);
            }
        }
        this.key = bArrCopyOf;
    }

    public /* synthetic */ FixedKeyProtector(byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bArr);
    }

    @Override // org.cipherkey.core.keys.KeyProtector
    public byte[] wrap(byte[] plainKey, byte[] keyId) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(plainKey, "plainKey");
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        byte[] bArr = new byte[12];
        SECURE.nextBytes(bArr);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(1, new SecretKeySpec(this.key, "AES"), new GCMParameterSpec(128, bArr));
        cipher.updateAAD(keyId);
        byte[] bArrDoFinal = cipher.doFinal(plainKey);
        Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
        return ArraysKt.plus(bArr, bArrDoFinal);
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
            cipher.init(2, new SecretKeySpec(this.key, "AES"), new GCMParameterSpec(128, bArrCopyOfRange));
            cipher.updateAAD(keyId);
            byte[] bArrDoFinal = cipher.doFinal(bArrCopyOfRange2);
            Intrinsics.checkNotNull(bArrDoFinal);
            return bArrDoFinal;
        } catch (GeneralSecurityException e) {
            throw new KeyProtectionException("unwrap failed (authentication or keyId mismatch)", e);
        }
    }

    /* compiled from: FixedKeyProtector.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/cipherkey/core/keys/FixedKeyProtector$Companion;", "", "<init>", "()V", "TRANSFORMATION", "", "NONCE_LENGTH", "", "TAG_LENGTH", "SECURE", "Ljava/security/SecureRandom;", "getSECURE", "()Ljava/security/SecureRandom;", "keys"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SecureRandom getSECURE() {
            return FixedKeyProtector.SECURE;
        }
    }
}
