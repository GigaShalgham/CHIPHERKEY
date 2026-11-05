package org.cipherkey.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.data.database.CipherKeyDatabase;

/* loaded from: classes3.dex */
public final class DatabaseModule_ProvideDatabaseFactory implements Factory<CipherKeyDatabase> {
    private final Provider<Context> contextProvider;

    public DatabaseModule_ProvideDatabaseFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CipherKeyDatabase get() {
        return provideDatabase(this.contextProvider.get());
    }

    public static DatabaseModule_ProvideDatabaseFactory create(Provider<Context> provider) {
        return new DatabaseModule_ProvideDatabaseFactory(provider);
    }

    public static CipherKeyDatabase provideDatabase(Context context) {
        return (CipherKeyDatabase) Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDatabase(context));
    }
}
