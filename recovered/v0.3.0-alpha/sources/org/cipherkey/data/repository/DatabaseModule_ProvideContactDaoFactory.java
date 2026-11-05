package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.data.database.CipherKeyDatabase;
import org.cipherkey.data.database.ContactDao;

/* loaded from: classes3.dex */
public final class DatabaseModule_ProvideContactDaoFactory implements Factory<ContactDao> {
    private final Provider<CipherKeyDatabase> databaseProvider;

    public DatabaseModule_ProvideContactDaoFactory(Provider<CipherKeyDatabase> provider) {
        this.databaseProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ContactDao get() {
        return provideContactDao(this.databaseProvider.get());
    }

    public static DatabaseModule_ProvideContactDaoFactory create(Provider<CipherKeyDatabase> provider) {
        return new DatabaseModule_ProvideContactDaoFactory(provider);
    }

    public static ContactDao provideContactDao(CipherKeyDatabase cipherKeyDatabase) {
        return (ContactDao) Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideContactDao(cipherKeyDatabase));
    }
}
