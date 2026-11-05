package org.cipherkey.app.assistant;

import dagger.MembersInjector;
import dagger.internal.Provider;
import org.cipherkey.data.preferences.SettingsRepository;

/* loaded from: classes3.dex */
public final class FloatingAssistantService_MembersInjector implements MembersInjector<FloatingAssistantService> {
    private final Provider<SettingsRepository> settingsProvider;

    public FloatingAssistantService_MembersInjector(Provider<SettingsRepository> provider) {
        this.settingsProvider = provider;
    }

    public static MembersInjector<FloatingAssistantService> create(Provider<SettingsRepository> provider) {
        return new FloatingAssistantService_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(FloatingAssistantService floatingAssistantService) {
        injectSettings(floatingAssistantService, this.settingsProvider.get());
    }

    public static void injectSettings(FloatingAssistantService floatingAssistantService, SettingsRepository settingsRepository) {
        floatingAssistantService.settings = settingsRepository;
    }
}
