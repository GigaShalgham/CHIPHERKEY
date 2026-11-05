package org.cipherkey.core.messages;

import java.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: Ck1.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/cipherkey/core/messages/Ck1;", "", "<init>", "()V", "MAGIC", "", "INVITE_MAGIC", "encodeBase64Url", "bytes", "", "decodeBase64Url", TextBundle.TEXT_ENTRY, "isEnvelope", "", "isInvite", "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Ck1 {
    public static final Ck1 INSTANCE = new Ck1();
    public static final String INVITE_MAGIC = "CKS1:";
    public static final String MAGIC = "CK1:";

    private Ck1() {
    }

    public final String encodeBase64Url(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        String strEncodeToString = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(...)");
        return strEncodeToString;
    }

    public final byte[] decodeBase64Url(String text) throws Ck1FormatException {
        Intrinsics.checkNotNullParameter(text, "text");
        String str = text;
        if (str.length() == 0) {
            throw new Ck1FormatException("empty payload", null, 2, null);
        }
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '=' || cCharAt == '+' || cCharAt == '/' || cCharAt == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t') {
                throw new Ck1FormatException("invalid base64url character", null, 2, null);
            }
        }
        try {
            byte[] bArrDecode = Base64.getUrlDecoder().decode(text);
            Intrinsics.checkNotNull(bArrDecode);
            return bArrDecode;
        } catch (IllegalArgumentException unused) {
            throw new Ck1FormatException("invalid base64url encoding", null, 2, null);
        }
    }

    public final boolean isEnvelope(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.startsWith$default(text, MAGIC, false, 2, (Object) null);
    }

    public final boolean isInvite(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return StringsKt.startsWith$default(text, INVITE_MAGIC, false, 2, (Object) null);
    }
}
