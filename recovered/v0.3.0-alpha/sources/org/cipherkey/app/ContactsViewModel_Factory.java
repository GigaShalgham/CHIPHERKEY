package org.cipherkey.app;

import dagger.internal.Factory;
import dagger.internal.Provider;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.data.repository.SessionKeyRepository;

/* loaded from: classes3.dex */
public final class ContactsViewModel_Factory implements Factory<ContactsViewModel> {
    private final Provider<ContactRepository> contactsRepositoryProvider;
    private final Provider<MessageCipher> messageCipherProvider;
    private final Provider<SessionKeyRepository> sessionKeysProvider;

    public ContactsViewModel_Factory(Provider<ContactRepository> provider, Provider<SessionKeyRepository> provider2, Provider<MessageCipher> provider3) {
        this.contactsRepositoryProvider = provider;
        this.sessionKeysProvider = provider2;
        this.messageCipherProvider = provider3;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public ContactsViewModel get() {
        return newInstance(this.contactsRepositoryProvider.get(), this.sessionKeysProvider.get(), this.messageCipherProvider.get());
    }

    public static ContactsViewModel_Factory create(Provider<ContactRepository> provider, Provider<SessionKeyRepository> provider2, Provider<MessageCipher> provider3) {
        return new ContactsViewModel_Factory(provider, provider2, provider3);
    }

    public static ContactsViewModel newInstance(ContactRepository contactRepository, SessionKeyRepository sessionKeyRepository, MessageCipher messageCipher) {
        return new ContactsViewModel(contactRepository, sessionKeyRepository, messageCipher);
    }
}
