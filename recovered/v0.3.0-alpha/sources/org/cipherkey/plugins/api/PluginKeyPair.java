package org.cipherkey.plugins.api;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PublicKeyCipherPlugin.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/cipherkey/plugins/api/PluginKeyPair;", "", "publicKey", "", "privateKey", "<init>", "([B[B)V", "getPublicKey", "()[B", "getPrivateKey", "equals", "", "other", "hashCode", "", "component1", "component2", "copy", "toString", "", "api"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PluginKeyPair {
    private final byte[] privateKey;
    private final byte[] publicKey;

    public static /* synthetic */ PluginKeyPair copy$default(PluginKeyPair pluginKeyPair, byte[] bArr, byte[] bArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = pluginKeyPair.publicKey;
        }
        if ((i & 2) != 0) {
            bArr2 = pluginKeyPair.privateKey;
        }
        return pluginKeyPair.copy(bArr, bArr2);
    }

    /* renamed from: component1, reason: from getter */
    public final byte[] getPublicKey() {
        return this.publicKey;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getPrivateKey() {
        return this.privateKey;
    }

    public final PluginKeyPair copy(byte[] publicKey, byte[] privateKey) {
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        return new PluginKeyPair(publicKey, privateKey);
    }

    public String toString() {
        return "PluginKeyPair(publicKey=" + Arrays.toString(this.publicKey) + ", privateKey=" + Arrays.toString(this.privateKey) + ")";
    }

    public PluginKeyPair(byte[] publicKey, byte[] privateKey) {
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public final byte[] getPublicKey() {
        return this.publicKey;
    }

    public final byte[] getPrivateKey() {
        return this.privateKey;
    }

    public boolean equals(Object other) {
        if (!(other instanceof PluginKeyPair)) {
            return false;
        }
        PluginKeyPair pluginKeyPair = (PluginKeyPair) other;
        return Arrays.equals(this.publicKey, pluginKeyPair.publicKey) && Arrays.equals(this.privateKey, pluginKeyPair.privateKey);
    }

    public int hashCode() {
        return (Arrays.hashCode(this.publicKey) * 31) + Arrays.hashCode(this.privateKey);
    }
}
