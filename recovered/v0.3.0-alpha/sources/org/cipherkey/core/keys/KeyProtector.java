package org.cipherkey.core.keys;

import kotlin.Metadata;

/* compiled from: KeyProtector.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lorg/cipherkey/core/keys/KeyProtector;", "", "wrap", "", "plainKey", "keyId", "unwrap", "wrapped", "keys"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface KeyProtector {
    byte[] unwrap(byte[] wrapped, byte[] keyId);

    byte[] wrap(byte[] plainKey, byte[] keyId);
}
