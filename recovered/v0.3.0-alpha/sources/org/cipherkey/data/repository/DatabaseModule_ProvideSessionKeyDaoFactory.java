package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.data.database.CipherKeyDatabase;
import org.cipherkey.data.database.SessionKeyDao;

/* loaded from: classes3.dex */
public final class DatabaseModule_ProvideSessionKeyDaoFactory implements Factory<SessionKeyDao> {
    private final Provider<CipherKeyDatabase> databaseProvider;

    public DatabaseModule_ProvideSessionKeyDaoFactory(Provider<CipherKeyDatabase> provider) {
        this.databaseProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SessionKeyDao get() {
        return provideSessionKeyDao(this.databaseProvider.get());
    }

    public static DatabaseModule_ProvideSessionKeyDaoFactory create(Provider<CipherKeyDatabase> provider) {
        return new DatabaseModule_ProvideSessionKeyDaoFactory(provider);
    }

    public static SessionKeyDao provideSessionKeyDao(CipherKeyDatabase cipherKeyDatabase) {
        return (SessionKeyDao) Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSessionKeyDao(cipherKeyDatabase));
    }
}
