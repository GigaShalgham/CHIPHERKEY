package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.core.crypto.Argon2KeyDerivator;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.TimeSource;

/* loaded from: classes3.dex */
public final class RepositoryModule_ProvideKeySessionManagerFactory implements Factory<KeySessionManager> {
    private final Provider<Argon2KeyDerivator> derivatorProvider;
    private final Provider<TimeSource> timeSourceProvider;

    public RepositoryModule_ProvideKeySessionManagerFactory(Provider<Argon2KeyDerivator> provider, Provider<TimeSource> provider2) {
        this.derivatorProvider = provider;
        this.timeSourceProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public KeySessionManager get() {
        return provideKeySessionManager(this.derivatorProvider.get(), this.timeSourceProvider.get());
    }

    public static RepositoryModule_ProvideKeySessionManagerFactory create(Provider<Argon2KeyDerivator> provider, Provider<TimeSource> provider2) {
        return new RepositoryModule_ProvideKeySessionManagerFactory(provider, provider2);
    }

    public static KeySessionManager provideKeySessionManager(Argon2KeyDerivator argon2KeyDerivator, TimeSource timeSource) {
        return (KeySessionManager) Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideKeySessionManager(argon2KeyDerivator, timeSource));
    }
}
