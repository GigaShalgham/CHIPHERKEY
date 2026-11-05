package org.cipherkey.data.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.keys.KeyProtector;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.data.preferences.SettingsRepository;

/* loaded from: classes3.dex */
public final class RepositoryModule_ProvideMessageCipherFactory implements Factory<MessageCipher> {
    private final Provider<ContactRepository> contactsProvider;
    private final Provider<DispatcherProvider> dispatchersProvider;
    private final Provider<KeyProtector> protectorProvider;
    private final Provider<CryptoProvider> providerProvider;
    private final Provider<SessionKeyRepository> sessionKeysProvider;
    private final Provider<KeySessionManager> sessionsProvider;
    private final Provider<SettingsRepository> settingsProvider;

    public RepositoryModule_ProvideMessageCipherFactory(Provider<ContactRepository> provider, Provider<SessionKeyRepository> provider2, Provider<KeySessionManager> provider3, Provider<KeyProtector> provider4, Provider<CryptoProvider> provider5, Provider<SettingsRepository> provider6, Provider<DispatcherProvider> provider7) {
        this.contactsProvider = provider;
        this.sessionKeysProvider = provider2;
        this.sessionsProvider = provider3;
        this.protectorProvider = provider4;
        this.providerProvider = provider5;
        this.settingsProvider = provider6;
        this.dispatchersProvider = provider7;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MessageCipher get() {
        return provideMessageCipher(this.contactsProvider.get(), this.sessionKeysProvider.get(), this.sessionsProvider.get(), this.protectorProvider.get(), this.providerProvider.get(), this.settingsProvider.get(), this.dispatchersProvider.get());
    }

    public static RepositoryModule_ProvideMessageCipherFactory create(Provider<ContactRepository> provider, Provider<SessionKeyRepository> provider2, Provider<KeySessionManager> provider3, Provider<KeyProtector> provider4, Provider<CryptoProvider> provider5, Provider<SettingsRepository> provider6, Provider<DispatcherProvider> provider7) {
        return new RepositoryModule_ProvideMessageCipherFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MessageCipher provideMessageCipher(ContactRepository contactRepository, SessionKeyRepository sessionKeyRepository, KeySessionManager keySessionManager, KeyProtector keyProtector, CryptoProvider cryptoProvider, SettingsRepository settingsRepository, DispatcherProvider dispatcherProvider) {
        return (MessageCipher) Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideMessageCipher(contactRepository, sessionKeyRepository, keySessionManager, keyProtector, cryptoProvider, settingsRepository, dispatcherProvider));
    }
}
