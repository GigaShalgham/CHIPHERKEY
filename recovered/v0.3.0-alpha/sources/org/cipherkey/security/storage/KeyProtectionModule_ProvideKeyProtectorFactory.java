package org.cipherkey.security.storage;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import org.cipherkey.core.keys.KeyProtector;

/* loaded from: classes3.dex */
public final class KeyProtectionModule_ProvideKeyProtectorFactory implements Factory<KeyProtector> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public KeyProtector get() {
        return provideKeyProtector();
    }

    public static KeyProtectionModule_ProvideKeyProtectorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static KeyProtector provideKeyProtector() {
        return (KeyProtector) Preconditions.checkNotNullFromProvides(KeyProtectionModule.INSTANCE.provideKeyProtector());
    }

    private static final class InstanceHolder {
        static final KeyProtectionModule_ProvideKeyProtectorFactory INSTANCE = new KeyProtectionModule_ProvideKeyProtectorFactory();

        private InstanceHolder() {
        }
    }
}
