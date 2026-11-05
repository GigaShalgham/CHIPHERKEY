package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.crypto.ProtocolRandom;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideProtocolRandomFactory implements Factory<ProtocolRandom> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ProtocolRandom get() {
        return provideProtocolRandom();
    }

    public static CryptoModule_ProvideProtocolRandomFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ProtocolRandom provideProtocolRandom() {
        return (ProtocolRandom) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideProtocolRandom());
    }

    private static final class InstanceHolder {
        static final CryptoModule_ProvideProtocolRandomFactory INSTANCE = new CryptoModule_ProvideProtocolRandomFactory();

        private InstanceHolder() {
        }
    }
}
