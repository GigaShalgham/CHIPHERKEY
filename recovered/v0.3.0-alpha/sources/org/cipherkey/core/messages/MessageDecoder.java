package org.cipherkey.core.messages;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.core.crypto.AeadFailure;
import org.cipherkey.core.crypto.CryptoError;
import org.cipherkey.core.crypto.EncryptedMessage;
import org.cipherkey.core.crypto.ProtocolHeader;

/* compiled from: MessageCodec.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lorg/cipherkey/core/messages/MessageDecoder;", "", "<init>", "()V", "decode", "Lorg/cipherkey/core/crypto/EncryptedMessage;", TextBundle.TEXT_ENTRY, "", "errorOf", "Lorg/cipherkey/core/crypto/CryptoError;", "e", "Lorg/cipherkey/core/messages/Ck1FormatException;", "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessageDecoder {
    public static final MessageDecoder INSTANCE = new MessageDecoder();

    private MessageDecoder() {
    }

    public final EncryptedMessage decode(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (text.length() > 360000) {
            throw new Ck1FormatException("encoded message too large", null, 2, null);
        }
        if (!Ck1.INSTANCE.isEnvelope(text)) {
            throw new Ck1FormatException("missing CK1: prefix", null, 2, null);
        }
        Ck1 ck1 = Ck1.INSTANCE;
        String strSubstring = text.substring(4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        byte[] bArrDecodeBase64Url = ck1.decodeBase64Url(strSubstring);
        if (bArrDecodeBase64Url.length > 262144) {
            throw new Ck1FormatException("payload too large", null, 2, null);
        }
        try {
            return ProtocolHeader.INSTANCE.parse(bArrDecodeBase64Url);
        } catch (AeadFailure e) {
            throw new Ck1FormatException(e.getError().getMessage(), e);
        }
    }

    public final CryptoError errorOf(Ck1FormatException e) {
        int iHashCode;
        Intrinsics.checkNotNullParameter(e, "e");
        String message = e.getMessage();
        if (message != null && ((iHashCode = message.hashCode()) == -223138467 ? message.equals("payload too large") : !(iHashCode == -48944636 ? !message.equals("encoded message too large") : !(iHashCode == 664391903 && message.equals("envelope exceeds 262144 bytes"))))) {
            return CryptoError.MessageTooLarge.INSTANCE;
        }
        return CryptoError.MalformedMessage.INSTANCE;
    }
}
