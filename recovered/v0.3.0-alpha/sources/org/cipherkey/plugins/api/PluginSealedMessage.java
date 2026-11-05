package org.cipherkey.plugins.api;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PublicKeyCipherPlugin.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/cipherkey/plugins/api/PluginSealedMessage;", "", "algorithmId", "", "payload", "", "<init>", "(I[B)V", "getAlgorithmId", "()I", "getPayload", "()[B", "equals", "", "other", "hashCode", "component1", "component2", "copy", "toString", "", "api"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PluginSealedMessage {
    private final int algorithmId;
    private final byte[] payload;

    public static /* synthetic */ PluginSealedMessage copy$default(PluginSealedMessage pluginSealedMessage, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = pluginSealedMessage.algorithmId;
        }
        if ((i2 & 2) != 0) {
            bArr = pluginSealedMessage.payload;
        }
        return pluginSealedMessage.copy(i, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final int getAlgorithmId() {
        return this.algorithmId;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getPayload() {
        return this.payload;
    }

    public final PluginSealedMessage copy(int algorithmId, byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        return new PluginSealedMessage(algorithmId, payload);
    }

    public String toString() {
        return "PluginSealedMessage(algorithmId=" + this.algorithmId + ", payload=" + Arrays.toString(this.payload) + ")";
    }

    public PluginSealedMessage(int i, byte[] payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        this.algorithmId = i;
        this.payload = payload;
    }

    public final int getAlgorithmId() {
        return this.algorithmId;
    }

    public final byte[] getPayload() {
        return this.payload;
    }

    public boolean equals(Object other) {
        if (!(other instanceof PluginSealedMessage)) {
            return false;
        }
        PluginSealedMessage pluginSealedMessage = (PluginSealedMessage) other;
        return this.algorithmId == pluginSealedMessage.algorithmId && Arrays.equals(this.payload, pluginSealedMessage.payload);
    }

    public int hashCode() {
        return (this.algorithmId * 31) + Arrays.hashCode(this.payload);
    }
}
