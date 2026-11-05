package org.cipherkey.core.messages;

import kotlin.Metadata;

/* compiled from: Ck1.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/cipherkey/core/messages/Ck1Limits;", "", "<init>", "()V", "MAX_PLAINTEXT_BYTES", "", "MAX_ENVELOPE_BYTES", "MAX_ENCODED_CHARS", "messages"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Ck1Limits {
    public static final Ck1Limits INSTANCE = new Ck1Limits();
    public static final int MAX_ENCODED_CHARS = 360000;
    public static final int MAX_ENVELOPE_BYTES = 262144;
    public static final int MAX_PLAINTEXT_BYTES = 16384;

    private Ck1Limits() {
    }
}
