package org.cipherkey.app.companion;

import dagger.MembersInjector;
import dagger.internal.Provider;
import org.cipherkey.app.security.BiometricKeyStore;

/* loaded from: classes3.dex */
public final class BaseCompanionActivity_MembersInjector implements MembersInjector<BaseCompanionActivity> {
    private final Provider<BiometricKeyStore> biometricsProvider;

    public BaseCompanionActivity_MembersInjector(Provider<BiometricKeyStore> provider) {
        this.biometricsProvider = provider;
    }

    public static MembersInjector<BaseCompanionActivity> create(Provider<BiometricKeyStore> provider) {
        return new BaseCompanionActivity_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BaseCompanionActivity baseCompanionActivity) {
        injectBiometrics(baseCompanionActivity, this.biometricsProvider.get());
    }

    public static void injectBiometrics(BaseCompanionActivity baseCompanionActivity, BiometricKeyStore biometricKeyStore) {
        baseCompanionActivity.biometrics = biometricKeyStore;
    }
}
