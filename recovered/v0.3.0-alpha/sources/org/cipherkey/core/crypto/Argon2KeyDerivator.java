package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

/* compiled from: Argon2KeyDerivator.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0012¨\u0006\u0014"}, d2 = {"Lorg/cipherkey/core/crypto/Argon2KeyDerivator;", "", "<init>", "()V", "derive", "", "passphrase", "", "salt", "params", "Lorg/cipherkey/core/crypto/Argon2Params;", "outputLength", "", "secret", "associatedData", "estimatedCostMillis", "", "referenceMillisPerGibPass", "", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Argon2KeyDerivator {
    public static final int SALT_LENGTH_BYTES = 16;

    public static /* synthetic */ byte[] derive$default(Argon2KeyDerivator argon2KeyDerivator, char[] cArr, byte[] bArr, Argon2Params argon2Params, int i, byte[] bArr2, byte[] bArr3, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = CipherAlgorithm.AES_256_GCM.getKeyLengthBytes();
        }
        return argon2KeyDerivator.derive(cArr, bArr, argon2Params, i, (i2 & 16) != 0 ? null : bArr2, (i2 & 32) != 0 ? null : bArr3);
    }

    public final byte[] derive(char[] passphrase, byte[] salt, Argon2Params params, int outputLength, byte[] secret, byte[] associatedData) {
        Intrinsics.checkNotNullParameter(passphrase, "passphrase");
        Intrinsics.checkNotNullParameter(salt, "salt");
        Intrinsics.checkNotNullParameter(params, "params");
        if (salt.length != 16) {
            throw new IllegalArgumentException("salt must be 16 bytes".toString());
        }
        if (4 > outputLength || outputLength >= 513) {
            throw new IllegalArgumentException("unsupported output length".toString());
        }
        Argon2Parameters.Builder builderWithSalt = new Argon2Parameters.Builder(2).withVersion(19).withIterations(params.getIterations()).withMemoryPowOfTwo(params.getMemoryExponent()).withParallelism(params.getParallelism()).withSalt(salt);
        if (secret != null) {
            builderWithSalt.withSecret(secret);
        }
        if (associatedData != null) {
            builderWithSalt.withAdditional(associatedData);
        }
        Argon2Parameters argon2ParametersBuild = builderWithSalt.build();
        Argon2BytesGenerator argon2BytesGenerator = new Argon2BytesGenerator();
        argon2BytesGenerator.init(argon2ParametersBuild);
        byte[] bArr = new byte[outputLength];
        int iGenerateBytes = argon2BytesGenerator.generateBytes(passphrase, bArr);
        if (iGenerateBytes == outputLength) {
            return bArr;
        }
        throw new IllegalStateException(("Argon2id produced " + iGenerateBytes + " bytes").toString());
    }

    public static /* synthetic */ long estimatedCostMillis$default(Argon2KeyDerivator argon2KeyDerivator, Argon2Params argon2Params, double d, int i, Object obj) {
        if ((i & 2) != 0) {
            d = 2600.0d;
        }
        return argon2KeyDerivator.estimatedCostMillis(argon2Params, d);
    }

    public final long estimatedCostMillis(Argon2Params params, double referenceMillisPerGibPass) {
        Intrinsics.checkNotNullParameter(params, "params");
        return RangesKt.coerceAtLeast(MathKt.roundToLong(((referenceMillisPerGibPass * RangesKt.coerceAtLeast(params.getMemoryKib() / 1048576.0d, 9.765625E-4d)) * params.getIterations()) / RangesKt.coerceAtMost(params.getParallelism(), 4)), 1L);
    }
}
