package org.cipherkey.app;

import dagger.internal.Factory;
import dagger.internal.Provider;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.security.clipboard.ClipboardAccess;

/* loaded from: classes3.dex */
public final class ToolsViewModel_Factory implements Factory<ToolsViewModel> {
    private final Provider<BiometricKeyStore> biometricStoreProvider;
    private final Provider<ClipboardAccess> clipboardProvider;
    private final Provider<ContactRepository> contactsRepositoryProvider;
    private final Provider<MessageCipher> messageCipherProvider;
    private final Provider<SettingsRepository> settingsProvider;

    public ToolsViewModel_Factory(Provider<MessageCipher> provider, Provider<ContactRepository> provider2, Provider<SettingsRepository> provider3, Provider<ClipboardAccess> provider4, Provider<BiometricKeyStore> provider5) {
        this.messageCipherProvider = provider;
        this.contactsRepositoryProvider = provider2;
        this.settingsProvider = provider3;
        this.clipboardProvider = provider4;
        this.biometricStoreProvider = provider5;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ToolsViewModel get() {
        return newInstance(this.messageCipherProvider.get(), this.contactsRepositoryProvider.get(), this.settingsProvider.get(), this.clipboardProvider.get(), this.biometricStoreProvider.get());
    }

    public static ToolsViewModel_Factory create(Provider<MessageCipher> provider, Provider<ContactRepository> provider2, Provider<SettingsRepository> provider3, Provider<ClipboardAccess> provider4, Provider<BiometricKeyStore> provider5) {
        return new ToolsViewModel_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static ToolsViewModel newInstance(MessageCipher messageCipher, ContactRepository contactRepository, SettingsRepository settingsRepository, ClipboardAccess clipboardAccess, BiometricKeyStore biometricKeyStore) {
        return new ToolsViewModel(messageCipher, contactRepository, settingsRepository, clipboardAccess, biometricKeyStore);
    }
}
