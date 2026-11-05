package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.core.crypto.AeadCipher;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.ProtocolRandom;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideCryptoProviderFactory implements Factory<CryptoProvider> {
    private final Provider<AeadCipher> aeadProvider;
    private final Provider<ProtocolRandom> randomProvider;

    public CryptoModule_ProvideCryptoProviderFactory(Provider<AeadCipher> provider, Provider<ProtocolRandom> provider2) {
        this.aeadProvider = provider;
        this.randomProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CryptoProvider get() {
        return provideCryptoProvider(this.aeadProvider.get(), this.randomProvider.get());
    }

    public static CryptoModule_ProvideCryptoProviderFactory create(Provider<AeadCipher> provider, Provider<ProtocolRandom> provider2) {
        return new CryptoModule_ProvideCryptoProviderFactory(provider, provider2);
    }

    public static CryptoProvider provideCryptoProvider(AeadCipher aeadCipher, ProtocolRandom protocolRandom) {
        return (CryptoProvider) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideCryptoProvider(aeadCipher, protocolRandom));
    }
}
