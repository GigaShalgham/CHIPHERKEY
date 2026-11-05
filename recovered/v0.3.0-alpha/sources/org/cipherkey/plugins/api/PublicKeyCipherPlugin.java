package org.cipherkey.plugins.api;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* compiled from: PublicKeyCipherPlugin.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u000e\u0010\f\u001a\u00020\rH¦@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H¦@¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0012H¦@¢\u0006\u0002\u0010\u0018R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lorg/cipherkey/plugins/api/PublicKeyCipherPlugin;", "", "id", "", "getId", "()Ljava/lang/String;", "displayName", "getDisplayName", "protocolVersion", "", "getProtocolVersion", "()I", "generateKeyPair", "Lorg/cipherkey/plugins/api/PluginKeyPair;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encrypt", "Lorg/cipherkey/plugins/api/PluginSealedMessage;", "plaintext", "", "recipientPublicKey", "([B[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decrypt", "sealed", "privateKey", "(Lorg/cipherkey/plugins/api/PluginSealedMessage;[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "api"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface PublicKeyCipherPlugin {
    Object decrypt(PluginSealedMessage pluginSealedMessage, byte[] bArr, Continuation<? super byte[]> continuation);

    Object encrypt(byte[] bArr, byte[] bArr2, Continuation<? super PluginSealedMessage> continuation);

    Object generateKeyPair(Continuation<? super PluginKeyPair> continuation);

    String getDisplayName();

    String getId();

    int getProtocolVersion();
}
