package org.cipherkey.app.security;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class BiometricModule_ProvideBiometricKeyStoreFactory implements Factory<BiometricKeyStore> {
    private final Provider<AndroidBiometricKeyStore> implProvider;

    public BiometricModule_ProvideBiometricKeyStoreFactory(Provider<AndroidBiometricKeyStore> provider) {
        this.implProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BiometricKeyStore get() {
        return provideBiometricKeyStore(this.implProvider.get());
    }

    public static BiometricModule_ProvideBiometricKeyStoreFactory create(Provider<AndroidBiometricKeyStore> provider) {
        return new BiometricModule_ProvideBiometricKeyStoreFactory(provider);
    }

    public static BiometricKeyStore provideBiometricKeyStore(AndroidBiometricKeyStore androidBiometricKeyStore) {
        return (BiometricKeyStore) Preconditions.checkNotNullFromProvides(BiometricModule.INSTANCE.provideBiometricKeyStore(androidBiometricKeyStore));
    }
}
