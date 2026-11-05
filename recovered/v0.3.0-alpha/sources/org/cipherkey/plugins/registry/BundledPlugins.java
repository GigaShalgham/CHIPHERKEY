package org.cipherkey.plugins.registry;

import kotlin.Metadata;

/* compiled from: PluginRegistry.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"Lorg/cipherkey/plugins/registry/BundledPlugins;", "", "<init>", "()V", "registry", "Lorg/cipherkey/plugins/registry/PluginRegistry;", "getRegistry", "()Lorg/cipherkey/plugins/registry/PluginRegistry;", "MODE_B_AVAILABLE", ""}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BundledPlugins {
    public static final boolean MODE_B_AVAILABLE = false;
    public static final BundledPlugins INSTANCE = new BundledPlugins();
    private static final PluginRegistry registry = new PluginRegistry();

    private BundledPlugins() {
    }

    public final PluginRegistry getRegistry() {
        return registry;
    }
}
