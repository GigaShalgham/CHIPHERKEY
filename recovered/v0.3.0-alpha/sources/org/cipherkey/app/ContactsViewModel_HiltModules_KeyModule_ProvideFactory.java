package org.cipherkey.app;

import dagger.internal.Factory;
import org.cipherkey.app.ContactsViewModel_HiltModules;

/* loaded from: classes3.dex */
public final class ContactsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static ContactsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return ContactsViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final ContactsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ContactsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
