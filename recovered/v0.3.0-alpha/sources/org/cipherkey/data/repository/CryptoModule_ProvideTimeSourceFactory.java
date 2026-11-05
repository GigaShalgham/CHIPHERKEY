package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.sessions.TimeSource;

/* loaded from: classes3.dex */
public final class CryptoModule_ProvideTimeSourceFactory implements Factory<TimeSource> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public TimeSource get() {
        return provideTimeSource();
    }

    public static CryptoModule_ProvideTimeSourceFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static TimeSource provideTimeSource() {
        return (TimeSource) Preconditions.checkNotNullFromProvides(CryptoModule.INSTANCE.provideTimeSource());
    }

    private static final class InstanceHolder {
        static final CryptoModule_ProvideTimeSourceFactory INSTANCE = new CryptoModule_ProvideTimeSourceFactory();

        private InstanceHolder() {
        }
    }
}
