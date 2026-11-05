package org.cipherkey.app.security;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class AndroidBiometricKeyStore_Factory implements Factory<AndroidBiometricKeyStore> {
    private final Provider<Context> contextProvider;

    public AndroidBiometricKeyStore_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AndroidBiometricKeyStore get() {
        return newInstance(this.contextProvider.get());
    }

    public static AndroidBiometricKeyStore_Factory create(Provider<Context> provider) {
        return new AndroidBiometricKeyStore_Factory(provider);
    }

    public static AndroidBiometricKeyStore newInstance(Context context) {
        return new AndroidBiometricKeyStore(context);
    }
}
