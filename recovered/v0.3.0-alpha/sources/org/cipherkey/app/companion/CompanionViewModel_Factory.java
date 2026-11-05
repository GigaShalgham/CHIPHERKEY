package org.cipherkey.app.companion;

import dagger.internal.Factory;
import dagger.internal.Provider;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.security.clipboard.ClipboardAccess;

/* loaded from: classes3.dex */
public final class CompanionViewModel_Factory implements Factory<CompanionViewModel> {
    private final Provider<BiometricKeyStore> biometricsProvider;
    private final Provider<ClipboardAccess> clipboardProvider;
    private final Provider<ContactRepository> contactsRepositoryProvider;
    private final Provider<MessageCipher> messageCipherProvider;
    private final Provider<SettingsRepository> settingsProvider;

    public CompanionViewModel_Factory(Provider<MessageCipher> provider, Provider<ContactRepository> provider2, Provider<SettingsRepository> provider3, Provider<ClipboardAccess> provider4, Provider<BiometricKeyStore> provider5) {
        this.messageCipherProvider = provider;
        this.contactsRepositoryProvider = provider2;
        this.settingsProvider = provider3;
        this.clipboardProvider = provider4;
        this.biometricsProvider = provider5;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CompanionViewModel get() {
        return newInstance(this.messageCipherProvider.get(), this.contactsRepositoryProvider.get(), this.settingsProvider.get(), this.clipboardProvider.get(), this.biometricsProvider.get());
    }

    public static CompanionViewModel_Factory create(Provider<MessageCipher> provider, Provider<ContactRepository> provider2, Provider<SettingsRepository> provider3, Provider<ClipboardAccess> provider4, Provider<BiometricKeyStore> provider5) {
        return new CompanionViewModel_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CompanionViewModel newInstance(MessageCipher messageCipher, ContactRepository contactRepository, SettingsRepository settingsRepository, ClipboardAccess clipboardAccess, BiometricKeyStore biometricKeyStore) {
        return new CompanionViewModel(messageCipher, contactRepository, settingsRepository, clipboardAccess, biometricKeyStore);
    }
}
