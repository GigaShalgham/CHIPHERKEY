package org.cipherkey.security.storage;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.cipherkey.core.keys.KeyProtector;

/* compiled from: KeyProtectionModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Lorg/cipherkey/security/storage/KeyProtectionModule;", "", "<init>", "()V", "provideKeyProtector", "Lorg/cipherkey/core/keys/KeyProtector;", "storage_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class KeyProtectionModule {
    public static final KeyProtectionModule INSTANCE = new KeyProtectionModule();

    private KeyProtectionModule() {
    }

    @Provides
    @Singleton
    public final KeyProtector provideKeyProtector() {
        return new AndroidKeystoreKeyProtector();
    }
}
