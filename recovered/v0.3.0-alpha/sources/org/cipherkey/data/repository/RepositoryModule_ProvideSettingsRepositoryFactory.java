package org.cipherkey.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.data.preferences.SettingsRepository;

/* loaded from: classes3.dex */
public final class RepositoryModule_ProvideSettingsRepositoryFactory implements Factory<SettingsRepository> {
    private final Provider<Context> contextProvider;

    public RepositoryModule_ProvideSettingsRepositoryFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SettingsRepository get() {
        return provideSettingsRepository(this.contextProvider.get());
    }

    public static RepositoryModule_ProvideSettingsRepositoryFactory create(Provider<Context> provider) {
        return new RepositoryModule_ProvideSettingsRepositoryFactory(provider);
    }

    public static SettingsRepository provideSettingsRepository(Context context) {
        return (SettingsRepository) Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideSettingsRepository(context));
    }
}
