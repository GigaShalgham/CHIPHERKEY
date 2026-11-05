package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.crypto.ProtocolRandom;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.SessionKeyDao;

/* loaded from: classes3.dex */
public final class RepositoryModule_ProvideSessionKeyRepositoryFactory implements Factory<SessionKeyRepository> {
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<ProtocolRandom> randomProvider;
    private final Provider<SessionKeyDao> sessionKeyDaoProvider;
    private final Provider<TimeSource> timeSourceProvider;

    public RepositoryModule_ProvideSessionKeyRepositoryFactory(Provider<SessionKeyDao> provider, Provider<ProtocolRandom> provider2, Provider<TimeSource> provider3, Provider<DispatcherProvider> provider4) {
        this.sessionKeyDaoProvider = provider;
        this.randomProvider = provider2;
        this.timeSourceProvider = provider3;
        this.dispatchersProvider = provider4;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SessionKeyRepository get() {
        return provideSessionKeyRepository(this.sessionKeyDaoProvider.get(), this.randomProvider.get(), this.timeSourceProvider.get(), this.dispatchersProvider.get());
    }

    public static RepositoryModule_ProvideSessionKeyRepositoryFactory create(Provider<SessionKeyDao> provider, Provider<ProtocolRandom> provider2, Provider<TimeSource> provider3, Provider<DispatcherProvider> provider4) {
        return new RepositoryModule_ProvideSessionKeyRepositoryFactory(provider, provider2, provider3, provider4);
    }

    public static SessionKeyRepository provideSessionKeyRepository(SessionKeyDao sessionKeyDao, ProtocolRandom protocolRandom, TimeSource timeSource, DispatcherProvider dispatcherProvider) {
        return (SessionKeyRepository) Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideSessionKeyRepository(sessionKeyDao, protocolRandom, timeSource, dispatcherProvider));
    }
}
