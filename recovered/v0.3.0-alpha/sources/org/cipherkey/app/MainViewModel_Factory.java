package org.cipherkey.app;

import dagger.internal.Factory;
import dagger.internal.Provider;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.MessageCipher;

/* loaded from: classes3.dex */
public final class MainViewModel_Factory implements Factory<MainViewModel> {
    private final Provider<ContactRepository> contactsRepositoryProvider;
    private final Provider<MessageCipher> messageCipherProvider;

    public MainViewModel_Factory(Provider<ContactRepository> provider, Provider<MessageCipher> provider2) {
        this.contactsRepositoryProvider = provider;
        this.messageCipherProvider = provider2;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public MainViewModel get() {
        return newInstance(this.contactsRepositoryProvider.get(), this.messageCipherProvider.get());
    }

    public static MainViewModel_Factory create(Provider<ContactRepository> provider, Provider<MessageCipher> provider2) {
        return new MainViewModel_Factory(provider, provider2);
    }

    public static MainViewModel newInstance(ContactRepository contactRepository, MessageCipher messageCipher) {
        return new MainViewModel(contactRepository, messageCipher);
    }
}
