package org.cipherkey.core.messages;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.core.crypto.EncryptedMessage;
import org.cipherkey.core.crypto.EncryptedMessageKt;

/* compiled from: MessageCodec.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/messages/MessageEncoder;", "", "<init>", "()V", "encode", "", "message", "Lorg/cipherkey/core/crypto/EncryptedMessage;", "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessageEncoder {
    public static final MessageEncoder INSTANCE = new MessageEncoder();

    private MessageEncoder() {
    }

    public final String encode(EncryptedMessage message) throws Ck1FormatException {
        Intrinsics.checkNotNullParameter(message, "message");
        byte[] byteArray = EncryptedMessageKt.toByteArray(message);
        if (byteArray.length > 262144) {
            throw new Ck1FormatException("envelope exceeds 262144 bytes", null, 2, null);
        }
        return Ck1.MAGIC + Ck1.INSTANCE.encodeBase64Url(byteArray);
    }
}
