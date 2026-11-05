package org.cipherkey.plugins.registry;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.cipherkey.plugins.api.PublicKeyCipherPlugin;

/* compiled from: PluginRegistry.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0007J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0006J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/cipherkey/plugins/registry/PluginRegistry;", "", "<init>", "()V", "plugins", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lorg/cipherkey/plugins/api/PublicKeyCipherPlugin;", "register", "plugin", "byId", "id", "available", "", "isEmpty", "", "()Z", "registry"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PluginRegistry {
    private final ConcurrentHashMap<String, PublicKeyCipherPlugin> plugins = new ConcurrentHashMap<>();

    public final PluginRegistry register(PublicKeyCipherPlugin plugin) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        if (StringsKt.isBlank(plugin.getId())) {
            throw new IllegalArgumentException("plugin id must not be blank".toString());
        }
        this.plugins.put(plugin.getId(), plugin);
        return this;
    }

    public final PublicKeyCipherPlugin byId(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return this.plugins.get(id);
    }

    public final List<PublicKeyCipherPlugin> available() {
        Collection<PublicKeyCipherPlugin> collectionValues = this.plugins.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        return CollectionsKt.sortedWith(collectionValues, new Comparator() { // from class: org.cipherkey.plugins.registry.PluginRegistry$available$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((PublicKeyCipherPlugin) t).getDisplayName(), ((PublicKeyCipherPlugin) t2).getDisplayName());
            }
        });
    }

    public final boolean isEmpty() {
        return this.plugins.isEmpty();
    }
}
