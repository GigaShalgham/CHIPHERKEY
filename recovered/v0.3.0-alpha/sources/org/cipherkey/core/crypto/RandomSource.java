package org.cipherkey.core.crypto;

import java.security.SecureRandom;
import kotlin.Metadata;
import org.cipherkey.core.crypto.RandomSource;

/* compiled from: ProtocolRandom.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lorg/cipherkey/core/crypto/RandomSource;", "", "nextBytes", "", "count", "", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RandomSource {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    byte[] nextBytes(int count);

    /* compiled from: ProtocolRandom.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/cipherkey/core/crypto/RandomSource$Companion;", "", "<init>", "()V", "SECURE", "Lorg/cipherkey/core/crypto/RandomSource;", "getSECURE", "()Lorg/cipherkey/core/crypto/RandomSource;", "SECURE_RANDOM", "Ljava/security/SecureRandom;", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final RandomSource SECURE = new RandomSource() { // from class: org.cipherkey.core.crypto.RandomSource$Companion$$ExternalSyntheticLambda0
            @Override // org.cipherkey.core.crypto.RandomSource
            public final byte[] nextBytes(int i) {
                return RandomSource.Companion.SECURE$lambda$0(i);
            }
        };
        private static final SecureRandom SECURE_RANDOM = new SecureRandom();

        private Companion() {
        }

        public final RandomSource getSECURE() {
            return SECURE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final byte[] SECURE$lambda$0(int i) {
            byte[] bArr = new byte[i];
            SECURE_RANDOM.nextBytes(bArr);
            return bArr;
        }
    }
}
