package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.common.DispatcherProvider;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideDispatcherProviderFactory implements Factory<DispatcherProvider> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DispatcherProvider get() {
        return provideDispatcherProvider();
    }

    public static CryptoModule_ProvideDispatcherProviderFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DispatcherProvider provideDispatcherProvider() {
        return (DispatcherProvider) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideDispatcherProvider());
    }

    private static final class InstanceHolder {
        static final CryptoModule_ProvideDispatcherProviderFactory INSTANCE = new CryptoModule_ProvideDispatcherProviderFactory();

        private InstanceHolder() {
        }
    }
}
