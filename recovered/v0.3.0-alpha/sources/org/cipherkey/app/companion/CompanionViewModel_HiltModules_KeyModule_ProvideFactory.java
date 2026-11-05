package org.cipherkey.app.companion;

import dagger.internal.Factory;
import org.cipherkey.app.companion.CompanionViewModel_HiltModules;

/* loaded from: classes3.dex */
public final class CompanionViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static CompanionViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return CompanionViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final CompanionViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new CompanionViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
