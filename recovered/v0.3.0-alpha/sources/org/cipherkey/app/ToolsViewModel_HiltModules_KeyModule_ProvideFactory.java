package org.cipherkey.app;

import dagger.internal.Factory;
import org.cipherkey.app.ToolsViewModel_HiltModules;

/* loaded from: classes3.dex */
public final class ToolsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }

    public static ToolsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return ToolsViewModel_HiltModules.KeyModule.provide();
    }

    private static final class InstanceHolder {
        static final ToolsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ToolsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }
}
