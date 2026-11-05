package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.crypto.AeadCipher;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideAeadCipherFactory implements Factory<AeadCipher> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AeadCipher get() {
        return provideAeadCipher();
    }

    public static CryptoModule_ProvideAeadCipherFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static AeadCipher provideAeadCipher() {
        return (AeadCipher) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideAeadCipher());
    }

    private static final class InstanceHolder {
        static final CryptoModule_ProvideAeadCipherFactory INSTANCE = new CryptoModule_ProvideAeadCipherFactory();

        private InstanceHolder() {
        }
    }
}
