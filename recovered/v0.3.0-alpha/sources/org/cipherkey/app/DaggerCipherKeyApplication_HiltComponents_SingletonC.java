package org.cipherkey.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.cipherkey.app.CipherKeyApplication_HiltComponents;
import org.cipherkey.app.ContactsViewModel_HiltModules;
import org.cipherkey.app.MainViewModel_HiltModules;
import org.cipherkey.app.SettingsViewModel_HiltModules;
import org.cipherkey.app.ToolsViewModel_HiltModules;
import org.cipherkey.app.assistant.FloatingAssistantService;
import org.cipherkey.app.assistant.FloatingAssistantService_MembersInjector;
import org.cipherkey.app.companion.BaseCompanionActivity_MembersInjector;
import org.cipherkey.app.companion.CompanionViewModel;
import org.cipherkey.app.companion.CompanionViewModel_HiltModules;
import org.cipherkey.app.companion.CompanionViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import org.cipherkey.app.companion.CompanionViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import org.cipherkey.app.companion.DecryptActivity;
import org.cipherkey.app.companion.EncryptActivity;
import org.cipherkey.app.companion.ShareDecryptActivity;
import org.cipherkey.app.security.AndroidBiometricKeyStore;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.app.security.BiometricModule_ProvideBiometricKeyStoreFactory;
import org.cipherkey.core.common.DispatcherProvider;
import org.cipherkey.core.crypto.AeadCipher;
import org.cipherkey.core.crypto.Argon2KeyDerivator;
import org.cipherkey.core.crypto.CryptoProvider;
import org.cipherkey.core.crypto.ProtocolRandom;
import org.cipherkey.core.keys.KeyProtector;
import org.cipherkey.core.sessions.KeySessionManager;
import org.cipherkey.core.sessions.TimeSource;
import org.cipherkey.data.database.CipherKeyDatabase;
import org.cipherkey.data.database.ContactDao;
import org.cipherkey.data.database.SessionKeyDao;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.ContactRepository;
import org.cipherkey.data.repository.CryptoModule_ProvideAeadCipherFactory;
import org.cipherkey.data.repository.CryptoModule_ProvideArgon2KeyDerivatorFactory;
import org.cipherkey.data.repository.CryptoModule_ProvideCryptoProviderFactory;
import org.cipherkey.data.repository.CryptoModule_ProvideDispatcherProviderFactory;
import org.cipherkey.data.repository.CryptoModule_ProvideProtocolRandomFactory;
import org.cipherkey.data.repository.CryptoModule_ProvideTimeSourceFactory;
import org.cipherkey.data.repository.DatabaseModule_ProvideContactDaoFactory;
import org.cipherkey.data.repository.DatabaseModule_ProvideDatabaseFactory;
import org.cipherkey.data.repository.DatabaseModule_ProvideSessionKeyDaoFactory;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.data.repository.RepositoryModule_ProvideContactRepositoryFactory;
import org.cipherkey.data.repository.RepositoryModule_ProvideKeySessionManagerFactory;
import org.cipherkey.data.repository.RepositoryModule_ProvideMessageCipherFactory;
import org.cipherkey.data.repository.RepositoryModule_ProvideSessionKeyRepositoryFactory;
import org.cipherkey.data.repository.RepositoryModule_ProvideSettingsRepositoryFactory;
import org.cipherkey.data.repository.SessionKeyRepository;
import org.cipherkey.security.clipboard.AndroidClipboardAccess;
import org.cipherkey.security.storage.KeyProtectionModule_ProvideKeyProtectorFactory;

/* loaded from: classes3.dex */
public final class DaggerCipherKeyApplication_HiltComponents_SingletonC {
    private DaggerCipherKeyApplication_HiltComponents_SingletonC() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ApplicationContextModule applicationContextModule;

        private Builder() {
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule);
            return this;
        }

        public CipherKeyApplication_HiltComponents.SingletonC build() {
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            return new SingletonCImpl(this.applicationContextModule);
        }
    }

    private static final class ActivityRetainedCBuilder implements CipherKeyApplication_HiltComponents.ActivityRetainedC.Builder {
        private SavedStateHandleHolder savedStateHandleHolder;
        private final SingletonCImpl singletonCImpl;

        private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public ActivityRetainedCBuilder savedStateHandleHolder(SavedStateHandleHolder savedStateHandleHolder) {
            this.savedStateHandleHolder = (SavedStateHandleHolder) Preconditions.checkNotNull(savedStateHandleHolder);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public CipherKeyApplication_HiltComponents.ActivityRetainedC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandleHolder, SavedStateHandleHolder.class);
            return new ActivityRetainedCImpl(this.singletonCImpl, this.savedStateHandleHolder);
        }
    }

    private static final class ActivityCBuilder implements CipherKeyApplication_HiltComponents.ActivityC.Builder {
        private Activity activity;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        private ActivityCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public ActivityCBuilder activity(Activity activity) {
            this.activity = (Activity) Preconditions.checkNotNull(activity);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public CipherKeyApplication_HiltComponents.ActivityC build() {
            Preconditions.checkBuilderRequirement(this.activity, Activity.class);
            return new ActivityCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activity);
        }
    }

    private static final class FragmentCBuilder implements CipherKeyApplication_HiltComponents.FragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private Fragment fragment;
        private final SingletonCImpl singletonCImpl;

        private FragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public FragmentCBuilder fragment(Fragment fragment) {
            this.fragment = (Fragment) Preconditions.checkNotNull(fragment);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public CipherKeyApplication_HiltComponents.FragmentC build() {
            Preconditions.checkBuilderRequirement(this.fragment, Fragment.class);
            return new FragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragment);
        }
    }

    private static final class ViewWithFragmentCBuilder implements CipherKeyApplication_HiltComponents.ViewWithFragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public ViewWithFragmentCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public CipherKeyApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewWithFragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, this.view);
        }
    }

    private static final class ViewCBuilder implements CipherKeyApplication_HiltComponents.ViewC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public ViewCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public CipherKeyApplication_HiltComponents.ViewC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.view);
        }
    }

    private static final class ViewModelCBuilder implements CipherKeyApplication_HiltComponents.ViewModelC.Builder {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private SavedStateHandle savedStateHandle;
        private final SingletonCImpl singletonCImpl;
        private ViewModelLifecycle viewModelLifecycle;

        private ViewModelCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
            this.savedStateHandle = (SavedStateHandle) Preconditions.checkNotNull(handle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
            this.viewModelLifecycle = (ViewModelLifecycle) Preconditions.checkNotNull(viewModelLifecycle);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public CipherKeyApplication_HiltComponents.ViewModelC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandle, SavedStateHandle.class);
            Preconditions.checkBuilderRequirement(this.viewModelLifecycle, ViewModelLifecycle.class);
            return new ViewModelCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.savedStateHandle, this.viewModelLifecycle);
        }
    }

    private static final class ServiceCBuilder implements CipherKeyApplication_HiltComponents.ServiceC.Builder {
        private Service service;
        private final SingletonCImpl singletonCImpl;

        private ServiceCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ServiceCBuilder service(Service service) {
            this.service = (Service) Preconditions.checkNotNull(service);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public CipherKeyApplication_HiltComponents.ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.singletonCImpl, this.service);
        }
    }

    private static final class ViewWithFragmentCImpl extends CipherKeyApplication_HiltComponents.ViewWithFragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

        ViewWithFragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl, View viewParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }
    }

    private static final class FragmentCImpl extends CipherKeyApplication_HiltComponents.FragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl = this;
        private final SingletonCImpl singletonCImpl;

        FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, Fragment fragmentParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.FragmentEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return this.activityCImpl.getHiltInternalFactoryFactory();
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
            return new ViewWithFragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl);
        }
    }

    private static final class ViewCImpl extends CipherKeyApplication_HiltComponents.ViewC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewCImpl viewCImpl = this;

        ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View viewParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }
    }

    private static final class ActivityCImpl extends CipherKeyApplication_HiltComponents.ActivityC {
        private final ActivityCImpl activityCImpl = this;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.ActivityEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl));
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public Map<Class<?>, Boolean> getViewModelKeys() {
            return LazyClassKeyMap.of(MapBuilder.newMapBuilder(5).put(CompanionViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(CompanionViewModel_HiltModules.KeyModule.provide())).put(ContactsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ContactsViewModel_HiltModules.KeyModule.provide())).put(MainViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(MainViewModel_HiltModules.KeyModule.provide())).put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(SettingsViewModel_HiltModules.KeyModule.provide())).put(ToolsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ToolsViewModel_HiltModules.KeyModule.provide())).build());
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public ViewModelComponentBuilder getViewModelComponentBuilder() {
            return new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.FragmentComponentManager.FragmentComponentBuilderEntryPoint
        public FragmentComponentBuilder fragmentComponentBuilder() {
            return new FragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewComponentBuilderEntryPoint
        public ViewComponentBuilder viewComponentBuilder() {
            return new ViewCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl);
        }

        @Override // org.cipherkey.app.MainActivity_GeneratedInjector
        public void injectMainActivity(MainActivity arg0) {
            injectMainActivity2(arg0);
        }

        @Override // org.cipherkey.app.companion.DecryptActivity_GeneratedInjector
        public void injectDecryptActivity(DecryptActivity arg0) {
            injectDecryptActivity2(arg0);
        }

        @Override // org.cipherkey.app.companion.EncryptActivity_GeneratedInjector
        public void injectEncryptActivity(EncryptActivity arg0) {
            injectEncryptActivity2(arg0);
        }

        @Override // org.cipherkey.app.companion.ShareDecryptActivity_GeneratedInjector
        public void injectShareDecryptActivity(ShareDecryptActivity arg0) {
            injectShareDecryptActivity2(arg0);
        }

        private MainActivity injectMainActivity2(MainActivity instance) {
            MainActivity_MembersInjector.injectSettingsRepository(instance, this.singletonCImpl.provideSettingsRepositoryProvider.get());
            return instance;
        }

        private DecryptActivity injectDecryptActivity2(DecryptActivity instance2) {
            BaseCompanionActivity_MembersInjector.injectBiometrics(instance2, this.singletonCImpl.provideBiometricKeyStoreProvider.get());
            return instance2;
        }

        private EncryptActivity injectEncryptActivity2(EncryptActivity instance3) {
            BaseCompanionActivity_MembersInjector.injectBiometrics(instance3, this.singletonCImpl.provideBiometricKeyStoreProvider.get());
            return instance3;
        }

        private ShareDecryptActivity injectShareDecryptActivity2(ShareDecryptActivity instance4) {
            BaseCompanionActivity_MembersInjector.injectBiometrics(instance4, this.singletonCImpl.provideBiometricKeyStoreProvider.get());
            return instance4;
        }
    }

    private static final class ViewModelCImpl extends CipherKeyApplication_HiltComponents.ViewModelC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        Provider<CompanionViewModel> companionViewModelProvider;
        Provider<ContactsViewModel> contactsViewModelProvider;
        Provider<MainViewModel> mainViewModelProvider;
        Provider<SettingsViewModel> settingsViewModelProvider;
        private final SingletonCImpl singletonCImpl;
        Provider<ToolsViewModel> toolsViewModelProvider;
        private final ViewModelCImpl viewModelCImpl = this;

        ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            initialize(savedStateHandleParam, viewModelLifecycleParam);
        }

        private void initialize(final SavedStateHandle savedStateHandleParam, final ViewModelLifecycle viewModelLifecycleParam) {
            this.companionViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 0);
            this.contactsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 1);
            this.mainViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 2);
            this.settingsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 3);
            this.toolsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 4);
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
            return LazyClassKeyMap.of(MapBuilder.newMapBuilder(5).put(CompanionViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.companionViewModelProvider).put(ContactsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.contactsViewModelProvider).put(MainViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.mainViewModelProvider).put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.settingsViewModelProvider).put(ToolsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.toolsViewModelProvider).build());
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
            return Collections.emptyMap();
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;
            private final ViewModelCImpl viewModelCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ViewModelCImpl viewModelCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.viewModelCImpl = viewModelCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                int i = this.id;
                if (i == 0) {
                    return (T) new CompanionViewModel(this.singletonCImpl.provideMessageCipherProvider.get(), this.singletonCImpl.provideContactRepositoryProvider.get(), this.singletonCImpl.provideSettingsRepositoryProvider.get(), this.singletonCImpl.androidClipboardAccessProvider.get(), this.singletonCImpl.provideBiometricKeyStoreProvider.get());
                }
                if (i == 1) {
                    return (T) new ContactsViewModel(this.singletonCImpl.provideContactRepositoryProvider.get(), this.singletonCImpl.provideSessionKeyRepositoryProvider.get(), this.singletonCImpl.provideMessageCipherProvider.get());
                }
                if (i == 2) {
                    return (T) new MainViewModel(this.singletonCImpl.provideContactRepositoryProvider.get(), this.singletonCImpl.provideMessageCipherProvider.get());
                }
                if (i == 3) {
                    return (T) new SettingsViewModel(this.singletonCImpl.provideSettingsRepositoryProvider.get(), this.singletonCImpl.provideBiometricKeyStoreProvider.get(), this.singletonCImpl.provideMessageCipherProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                }
                if (i == 4) {
                    return (T) new ToolsViewModel(this.singletonCImpl.provideMessageCipherProvider.get(), this.singletonCImpl.provideContactRepositoryProvider.get(), this.singletonCImpl.provideSettingsRepositoryProvider.get(), this.singletonCImpl.androidClipboardAccessProvider.get(), this.singletonCImpl.provideBiometricKeyStoreProvider.get());
                }
                throw new AssertionError(this.id);
            }
        }
    }

    private static final class ActivityRetainedCImpl extends CipherKeyApplication_HiltComponents.ActivityRetainedC {
        private final ActivityRetainedCImpl activityRetainedCImpl = this;
        Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;
        private final SingletonCImpl singletonCImpl;

        ActivityRetainedCImpl(SingletonCImpl singletonCImpl, SavedStateHandleHolder savedStateHandleHolderParam) {
            this.singletonCImpl = singletonCImpl;
            initialize(savedStateHandleHolderParam);
        }

        private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
            this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, 0));
        }

        @Override // dagger.hilt.android.internal.managers.ActivityComponentManager.ActivityComponentBuilderEntryPoint
        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder(this.singletonCImpl, this.activityRetainedCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedLifecycleEntryPoint
        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return this.provideActivityRetainedLifecycleProvider.get();
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                if (this.id == 0) {
                    return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();
                }
                throw new AssertionError(this.id);
            }
        }
    }

    private static final class ServiceCImpl extends CipherKeyApplication_HiltComponents.ServiceC {
        private final ServiceCImpl serviceCImpl = this;
        private final SingletonCImpl singletonCImpl;

        ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // org.cipherkey.app.assistant.FloatingAssistantService_GeneratedInjector
        public void injectFloatingAssistantService(FloatingAssistantService arg0) {
            injectFloatingAssistantService2(arg0);
        }

        private FloatingAssistantService injectFloatingAssistantService2(FloatingAssistantService instance) {
            FloatingAssistantService_MembersInjector.injectSettings(instance, this.singletonCImpl.provideSettingsRepositoryProvider.get());
            return instance;
        }
    }

    private static final class SingletonCImpl extends CipherKeyApplication_HiltComponents.SingletonC {
        Provider<AndroidBiometricKeyStore> androidBiometricKeyStoreProvider;
        Provider<AndroidClipboardAccess> androidClipboardAccessProvider;
        private final ApplicationContextModule applicationContextModule;
        Provider<AeadCipher> provideAeadCipherProvider;
        Provider<Argon2KeyDerivator> provideArgon2KeyDerivatorProvider;
        Provider<BiometricKeyStore> provideBiometricKeyStoreProvider;
        Provider<ContactRepository> provideContactRepositoryProvider;
        Provider<CryptoProvider> provideCryptoProvider;
        Provider<CipherKeyDatabase> provideDatabaseProvider;
        Provider<DispatcherProvider> provideDispatcherProvider;
        Provider<KeyProtector> provideKeyProtectorProvider;
        Provider<KeySessionManager> provideKeySessionManagerProvider;
        Provider<MessageCipher> provideMessageCipherProvider;
        Provider<ProtocolRandom> provideProtocolRandomProvider;
        Provider<SessionKeyRepository> provideSessionKeyRepositoryProvider;
        Provider<SettingsRepository> provideSettingsRepositoryProvider;
        Provider<TimeSource> provideTimeSourceProvider;
        private final SingletonCImpl singletonCImpl = this;

        @Override // org.cipherkey.app.CipherKeyApplication_GeneratedInjector
        public void injectCipherKeyApplication(CipherKeyApplication arg0) {
        }

        SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
            this.applicationContextModule = applicationContextModuleParam;
            initialize(applicationContextModuleParam);
        }

        ContactDao contactDao() {
            return DatabaseModule_ProvideContactDaoFactory.provideContactDao(this.provideDatabaseProvider.get());
        }

        SessionKeyDao sessionKeyDao() {
            return DatabaseModule_ProvideSessionKeyDaoFactory.provideSessionKeyDao(this.provideDatabaseProvider.get());
        }

        private void initialize(final ApplicationContextModule applicationContextModuleParam) {
            this.provideSettingsRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 0));
            this.androidBiometricKeyStoreProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 2));
            this.provideBiometricKeyStoreProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 1));
            this.provideDatabaseProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 5));
            this.provideTimeSourceProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 6));
            this.provideDispatcherProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 7));
            this.provideContactRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 4));
            this.provideProtocolRandomProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 9));
            this.provideSessionKeyRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 8));
            this.provideArgon2KeyDerivatorProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 11));
            this.provideKeySessionManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 10));
            this.provideKeyProtectorProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 12));
            this.provideAeadCipherProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 14));
            this.provideCryptoProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 13));
            this.provideMessageCipherProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 3));
            this.androidClipboardAccessProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 15));
        }

        @Override // dagger.hilt.android.flags.FragmentGetContextFix.FragmentGetContextFixEntryPoint
        public Set<Boolean> getDisableFragmentGetContextFix() {
            return Collections.emptySet();
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedComponentBuilderEntryPoint
        public ActivityRetainedComponentBuilder retainedComponentBuilder() {
            return new ActivityRetainedCBuilder(this.singletonCImpl);
        }

        @Override // dagger.hilt.android.internal.managers.ServiceComponentManager.ServiceComponentBuilderEntryPoint
        public ServiceComponentBuilder serviceComponentBuilder() {
            return new ServiceCBuilder(this.singletonCImpl);
        }

        private static final class SwitchingProvider<T> implements Provider<T> {
            private final int id;
            private final SingletonCImpl singletonCImpl;

            SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
                this.singletonCImpl = singletonCImpl;
                this.id = id;
            }

            @Override // javax.inject.Provider, jakarta.inject.Provider
            public T get() {
                switch (this.id) {
                    case 0:
                        return (T) RepositoryModule_ProvideSettingsRepositoryFactory.provideSettingsRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 1:
                        return (T) BiometricModule_ProvideBiometricKeyStoreFactory.provideBiometricKeyStore(this.singletonCImpl.androidBiometricKeyStoreProvider.get());
                    case 2:
                        return (T) new AndroidBiometricKeyStore(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 3:
                        return (T) RepositoryModule_ProvideMessageCipherFactory.provideMessageCipher(this.singletonCImpl.provideContactRepositoryProvider.get(), this.singletonCImpl.provideSessionKeyRepositoryProvider.get(), this.singletonCImpl.provideKeySessionManagerProvider.get(), this.singletonCImpl.provideKeyProtectorProvider.get(), this.singletonCImpl.provideCryptoProvider.get(), this.singletonCImpl.provideSettingsRepositoryProvider.get(), this.singletonCImpl.provideDispatcherProvider.get());
                    case 4:
                        return (T) RepositoryModule_ProvideContactRepositoryFactory.provideContactRepository(this.singletonCImpl.contactDao(), this.singletonCImpl.provideTimeSourceProvider.get(), this.singletonCImpl.provideDispatcherProvider.get());
                    case 5:
                        return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 6:
                        return (T) CryptoModule_ProvideTimeSourceFactory.provideTimeSource();
                    case 7:
                        return (T) CryptoModule_ProvideDispatcherProviderFactory.provideDispatcherProvider();
                    case 8:
                        return (T) RepositoryModule_ProvideSessionKeyRepositoryFactory.provideSessionKeyRepository(this.singletonCImpl.sessionKeyDao(), this.singletonCImpl.provideProtocolRandomProvider.get(), this.singletonCImpl.provideTimeSourceProvider.get(), this.singletonCImpl.provideDispatcherProvider.get());
                    case 9:
                        return (T) CryptoModule_ProvideProtocolRandomFactory.provideProtocolRandom();
                    case 10:
                        return (T) RepositoryModule_ProvideKeySessionManagerFactory.provideKeySessionManager(this.singletonCImpl.provideArgon2KeyDerivatorProvider.get(), this.singletonCImpl.provideTimeSourceProvider.get());
                    case 11:
                        return (T) CryptoModule_ProvideArgon2KeyDerivatorFactory.provideArgon2KeyDerivator();
                    case 12:
                        return (T) KeyProtectionModule_ProvideKeyProtectorFactory.provideKeyProtector();
                    case 13:
                        return (T) CryptoModule_ProvideCryptoProviderFactory.provideCryptoProvider(this.singletonCImpl.provideAeadCipherProvider.get(), this.singletonCImpl.provideProtocolRandomProvider.get());
                    case 14:
                        return (T) CryptoModule_ProvideAeadCipherFactory.provideAeadCipher();
                    case 15:
                        return (T) new AndroidClipboardAccess(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    default:
                        throw new AssertionError(this.id);
                }
            }
        }
    }
}
