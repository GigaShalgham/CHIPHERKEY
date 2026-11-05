package org.cipherkey.app.security;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BiometricKeyStore.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lorg/cipherkey/app/security/BiometricModule;", "", "<init>", "()V", "provideBiometricKeyStore", "Lorg/cipherkey/app/security/BiometricKeyStore;", "impl", "Lorg/cipherkey/app/security/AndroidBiometricKeyStore;", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
/* loaded from: classes3.dex */
public final class BiometricModule {
    public static final int $stable = 0;
    public static final BiometricModule INSTANCE = new BiometricModule();

    private BiometricModule() {
    }

    @Provides
    @Singleton
    public final BiometricKeyStore provideBiometricKeyStore(AndroidBiometricKeyStore impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        return impl;
    }
}
