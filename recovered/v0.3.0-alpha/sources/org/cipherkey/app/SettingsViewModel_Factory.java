package org.cipherkey.app;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.MessageCipher;

/* loaded from: classes3.dex */
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
    private final Provider<BiometricKeyStore> biometricsProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MessageCipher> messageCipherProvider;
    private final Provider<SettingsRepository> settingsRepositoryProvider;

    public SettingsViewModel_Factory(Provider<SettingsRepository> provider, Provider<BiometricKeyStore> provider2, Provider<MessageCipher> provider3, Provider<Context> provider4) {
        this.settingsRepositoryProvider = provider;
        this.biometricsProvider = provider2;
        this.messageCipherProvider = provider3;
        this.contextProvider = provider4;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SettingsViewModel get() {
        return newInstance(this.settingsRepositoryProvider.get(), this.biometricsProvider.get(), this.messageCipherProvider.get(), this.contextProvider.get());
    }

    public static SettingsViewModel_Factory create(Provider<SettingsRepository> provider, Provider<BiometricKeyStore> provider2, Provider<MessageCipher> provider3, Provider<Context> provider4) {
        return new SettingsViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static SettingsViewModel newInstance(SettingsRepository settingsRepository, BiometricKeyStore biometricKeyStore, MessageCipher messageCipher, Context context) {
        return new SettingsViewModel(settingsRepository, biometricKeyStore, messageCipher, context);
    }
}
