package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProtocolRandom.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/cipherkey/core/crypto/ProtocolRandom;", "", "source", "Lorg/cipherkey/core/crypto/RandomSource;", "<init>", "(Lorg/cipherkey/core/crypto/RandomSource;)V", "nonce", "", "salt", "keyId", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProtocolRandom {
    public static final int KEY_ID_LENGTH_BYTES = 16;
    private final RandomSource source;

    public ProtocolRandom(RandomSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    public final byte[] nonce() {
        return this.source.nextBytes(CipherAlgorithm.AES_256_GCM.getNonceLengthBytes());
    }

    public final byte[] salt() {
        return this.source.nextBytes(16);
    }

    public final byte[] keyId() {
        return this.source.nextBytes(16);
    }
}
