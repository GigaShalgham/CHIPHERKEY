package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.ContactDao;

/* loaded from: classes3.dex */
public final class RepositoryModule_ProvideContactRepositoryFactory implements Factory<ContactRepository> {
    private final Provider<ContactDao> contactDaoProvider;
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<TimeSource> timeSourceProvider;

    public RepositoryModule_ProvideContactRepositoryFactory(Provider<ContactDao> provider, Provider<TimeSource> provider2, Provider<DispatcherProvider> provider3) {
        this.contactDaoProvider = provider;
        this.timeSourceProvider = provider2;
        this.dispatchersProvider = provider3;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ContactRepository get() {
        return provideContactRepository(this.contactDaoProvider.get(), this.timeSourceProvider.get(), this.dispatchersProvider.get());
    }

    public static RepositoryModule_ProvideContactRepositoryFactory create(Provider<ContactDao> provider, Provider<TimeSource> provider2, Provider<DispatcherProvider> provider3) {
        return new RepositoryModule_ProvideContactRepositoryFactory(provider, provider2, provider3);
    }

    public static ContactRepository provideContactRepository(ContactDao contactDao, TimeSource timeSource, DispatcherProvider dispatcherProvider) {
        return (ContactRepository) Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideContactRepository(contactDao, timeSource, dispatcherProvider));
    }
}
