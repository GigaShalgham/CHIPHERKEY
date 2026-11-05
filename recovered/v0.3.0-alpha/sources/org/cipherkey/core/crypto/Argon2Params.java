package org.cipherkey.core.crypto;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Argon2Params.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u001b"}, d2 = {"Lorg/cipherkey/core/crypto/Argon2Params;", "", "memoryExponent", "", "iterations", "parallelism", "<init>", "(III)V", "getMemoryExponent", "()I", "getIterations", "getParallelism", "memoryKib", "getMemoryKib", "encode", "", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Argon2Params {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int ENCODED_LENGTH = 7;
    public static final int MAX_ITERATIONS = 65536;
    public static final int MAX_MEMORY_EXPONENT = 22;
    public static final int MAX_PARALLELISM = 64;
    public static final int MIN_MEMORY_EXPONENT = 3;
    private final int iterations;
    private final int memoryExponent;
    private final int parallelism;

    public static /* synthetic */ Argon2Params copy$default(Argon2Params argon2Params, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = argon2Params.memoryExponent;
        }
        if ((i4 & 2) != 0) {
            i2 = argon2Params.iterations;
        }
        if ((i4 & 4) != 0) {
            i3 = argon2Params.parallelism;
        }
        return argon2Params.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMemoryExponent() {
        return this.memoryExponent;
    }

    /* renamed from: component2, reason: from getter */
    public final int getIterations() {
        return this.iterations;
    }

    /* renamed from: component3, reason: from getter */
    public final int getParallelism() {
        return this.parallelism;
    }

    public final Argon2Params copy(int memoryExponent, int iterations, int parallelism) {
        return new Argon2Params(memoryExponent, iterations, parallelism);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Argon2Params)) {
            return false;
        }
        Argon2Params argon2Params = (Argon2Params) other;
        return this.memoryExponent == argon2Params.memoryExponent && this.iterations == argon2Params.iterations && this.parallelism == argon2Params.parallelism;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.memoryExponent) * 31) + Integer.hashCode(this.iterations)) * 31) + Integer.hashCode(this.parallelism);
    }

    public String toString() {
        return "Argon2Params(memoryExponent=" + this.memoryExponent + ", iterations=" + this.iterations + ", parallelism=" + this.parallelism + ")";
    }

    public Argon2Params(int i, int i2, int i3) {
        this.memoryExponent = i;
        this.iterations = i2;
        this.parallelism = i3;
        if (3 > i || i >= 23) {
            throw new IllegalArgumentException("memory exponent must be in 3..22 (KiB, power of two)".toString());
        }
        if (1 > i2 || i2 >= 65537) {
            throw new IllegalArgumentException("iterations must be in 1..65536".toString());
        }
        if (1 > i3 || i3 >= 65) {
            throw new IllegalArgumentException("parallelism must be in 1..64".toString());
        }
        if ((1 << i) < i3 * 8) {
            throw new IllegalArgumentException(("memory (2^" + i + " KiB) must be at least 8*parallelism KiB").toString());
        }
    }

    public final int getMemoryExponent() {
        return this.memoryExponent;
    }

    public final int getIterations() {
        return this.iterations;
    }

    public final int getParallelism() {
        return this.parallelism;
    }

    public final int getMemoryKib() {
        return 1 << this.memoryExponent;
    }

    public final byte[] encode() {
        byte b = (byte) this.memoryExponent;
        int i = this.iterations;
        int i2 = this.parallelism;
        return new byte[]{b, (byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i, (byte) (i2 >>> 8), (byte) i2};
    }

    /* compiled from: Argon2Params.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/cipherkey/core/crypto/Argon2Params$Companion;", "", "<init>", "()V", "ENCODED_LENGTH", "", "MIN_MEMORY_EXPONENT", "MAX_MEMORY_EXPONENT", "MAX_ITERATIONS", "MAX_PARALLELISM", "decode", "Lorg/cipherkey/core/crypto/Argon2Params;", "bytes", "", "crypto"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Argon2Params decode(byte[] bytes) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            if (bytes.length != 7) {
                throw new IllegalArgumentException("bad Argon2Params length");
            }
            return new Argon2Params(bytes[0] & UByte.MAX_VALUE, ((bytes[1] & UByte.MAX_VALUE) << 24) | ((bytes[2] & UByte.MAX_VALUE) << 16) | ((bytes[3] & UByte.MAX_VALUE) << 8) | (bytes[4] & UByte.MAX_VALUE), (bytes[6] & UByte.MAX_VALUE) | ((bytes[5] & UByte.MAX_VALUE) << 8));
        }
    }
}
