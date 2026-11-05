package org.cipherkey.core.keys;

import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: KeyProtector.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/core/keys/KeyFingerprint;", "", "<init>", "()V", "of", "", "key", "", "ofSession", "keyId", "salt", "keys"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KeyFingerprint {
    public static final KeyFingerprint INSTANCE = new KeyFingerprint();

    private KeyFingerprint() {
    }

    public final String of(byte[] key) {
        Intrinsics.checkNotNullParameter(key, "key");
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(key);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return CollectionsKt.joinToString$default(ArraysKt.takeLast(bArrDigest, 6), ":", null, null, 0, null, new Function1() { // from class: org.cipherkey.core.keys.KeyFingerprint$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyFingerprint.of$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence of$lambda$0(byte b) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(b & UByte.MAX_VALUE)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String ofSession(byte[] keyId, byte[] salt) {
        Intrinsics.checkNotNullParameter(keyId, "keyId");
        Intrinsics.checkNotNullParameter(salt, "salt");
        byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(ArraysKt.plus(keyId, salt));
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return CollectionsKt.joinToString$default(ArraysKt.takeLast(bArrDigest, 6), ":", null, null, 0, null, new Function1() { // from class: org.cipherkey.core.keys.KeyFingerprint$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyFingerprint.ofSession$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence ofSession$lambda$0(byte b) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%03d", Arrays.copyOf(new Object[]{Integer.valueOf(b & UByte.MAX_VALUE)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
