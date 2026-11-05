package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.crypto.Argon2KeyDerivator;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideArgon2KeyDerivatorFactory implements Factory<Argon2KeyDerivator> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Argon2KeyDerivator get() {
        return provideArgon2KeyDerivator();
    }

    public static CryptoModule_ProvideArgon2KeyDerivatorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Argon2KeyDerivator provideArgon2KeyDerivator() {
        return (Argon2KeyDerivator) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideArgon2KeyDerivator());
    }

    private static final class InstanceHolder {
        static final CryptoModule_ProvideArgon2KeyDerivatorFactory INSTANCE = new CryptoModule_ProvideArgon2KeyDerivatorFactory();

        private InstanceHolder() {
        }
    }
}
