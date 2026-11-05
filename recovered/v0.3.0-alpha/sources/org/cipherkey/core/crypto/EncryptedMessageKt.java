package org.cipherkey.core.crypto;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EncryptedMessage.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\"\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\b¨\u0006\t"}, d2 = {"varargDeepHashCode", "", "values", "", "", "([Ljava/lang/Object;)I", "toByteArray", "", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "crypto"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EncryptedMessageKt {
    public static final int varargDeepHashCode(Object... values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return Arrays.hashCode(values);
    }

    public static final byte[] toByteArray(EncryptedMessage encryptedMessage) {
        Intrinsics.checkNotNullParameter(encryptedMessage, "<this>");
        return ArraysKt.plus(ProtocolHeader.INSTANCE.serialize(encryptedMessage), encryptedMessage.getCiphertext());
    }
}
