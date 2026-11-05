package org.cipherkey.app;

import dagger.MembersInjector;
import dagger.internal.Provider;
import org.cipherkey.data.preferences.SettingsRepository;

/* loaded from: classes3.dex */
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<SettingsRepository> settingsRepositoryProvider;

    public MainActivity_MembersInjector(Provider<SettingsRepository> provider) {
        this.settingsRepositoryProvider = provider;
    }

    public static MembersInjector<MainActivity> create(Provider<SettingsRepository> provider) {
        return new MainActivity_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainActivity mainActivity) {
        injectSettingsRepository(mainActivity, this.settingsRepositoryProvider.get());
    }

    public static void injectSettingsRepository(MainActivity mainActivity, SettingsRepository settingsRepository) {
        mainActivity.settingsRepository = settingsRepository;
    }
}
