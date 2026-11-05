package org.cipherkey.core.messages;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;
import org.cipherkey.core.crypto.Argon2Params;
import org.cipherkey.core.crypto.KdfAlgorithm;

/* compiled from: InviteCodec.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/cipherkey/core/messages/InviteCodec;", "", "<init>", "()V", "VERSION", "", "PAYLOAD_LENGTH", "encode", "", "invite", "Lorg/cipherkey/core/messages/SessionInvite;", "decode", TextBundle.TEXT_ENTRY, "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InviteCodec {
    public static final InviteCodec INSTANCE = new InviteCodec();
    public static final int PAYLOAD_LENGTH = 41;
    public static final int VERSION = 1;

    private InviteCodec() {
    }

    public final String encode(SessionInvite invite) {
        Intrinsics.checkNotNullParameter(invite, "invite");
        if (invite.getKeyId().length != 16) {
            throw new IllegalArgumentException("keyId must be 16 bytes".toString());
        }
        if (invite.getSalt().length != 16) {
            throw new IllegalArgumentException("salt must be 16 bytes".toString());
        }
        byte[] bArr = new byte[41];
        bArr[0] = 1;
        bArr[1] = (byte) KdfAlgorithm.ARGON2ID.getId();
        ArraysKt.copyInto$default(invite.getParams().encode(), bArr, 2, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(invite.getKeyId(), bArr, 9, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(invite.getSalt(), bArr, 25, 0, 0, 12, (Object) null);
        return Ck1.INVITE_MAGIC + Ck1.INSTANCE.encodeBase64Url(bArr);
    }

    public final SessionInvite decode(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!Ck1.INSTANCE.isInvite(text)) {
            throw new Ck1FormatException("missing CKS1: prefix", null, 2, null);
        }
        Ck1 ck1 = Ck1.INSTANCE;
        String strSubstring = text.substring(5);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        byte[] bArrDecodeBase64Url = ck1.decodeBase64Url(strSubstring);
        if (bArrDecodeBase64Url.length != 41) {
            throw new Ck1FormatException("invite payload must be 41 bytes", null, 2, null);
        }
        if ((bArrDecodeBase64Url[0] & UByte.MAX_VALUE) != 1) {
            throw new Ck1FormatException("unsupported invite version " + (bArrDecodeBase64Url[0] & UByte.MAX_VALUE), null, 2, null);
        }
        int i = bArrDecodeBase64Url[1] & UByte.MAX_VALUE;
        if (i != KdfAlgorithm.ARGON2ID.getId()) {
            throw new Ck1FormatException("unsupported KDF id " + i, null, 2, null);
        }
        try {
            return new SessionInvite(ArraysKt.copyOfRange(bArrDecodeBase64Url, 9, 25), ArraysKt.copyOfRange(bArrDecodeBase64Url, 25, 41), Argon2Params.INSTANCE.decode(ArraysKt.copyOfRange(bArrDecodeBase64Url, 2, 9)));
        } catch (IllegalArgumentException e) {
            throw new Ck1FormatException("invalid Argon2id parameters", e);
        }
    }
}
