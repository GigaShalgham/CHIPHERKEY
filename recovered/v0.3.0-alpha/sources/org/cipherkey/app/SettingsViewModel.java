package org.cipherkey.app;

import android.content.Context;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.cipherkey.app.SettingsEvent;
import org.cipherkey.app.assistant.AssistantReadiness;
import org.cipherkey.app.assistant.FloatingAssistantService;
import org.cipherkey.app.security.BiometricKeyStore;
import org.cipherkey.data.preferences.AppSettings;
import org.cipherkey.data.preferences.SettingsRepository;
import org.cipherkey.data.repository.MessageCipher;
import org.cipherkey.security.policies.Argon2Profile;
import org.cipherkey.security.policies.KeyPolicy;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u0013J\u000e\u0010*\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u0013J\u0006\u0010+\u001a\u00020\u001eJ\u0006\u0010,\u001a\u00020\u001eJ\u0006\u0010-\u001a\u00020\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010¨\u0006."}, d2 = {"Lorg/cipherkey/app/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lorg/cipherkey/data/preferences/SettingsRepository;", "biometrics", "Lorg/cipherkey/app/security/BiometricKeyStore;", "messageCipher", "Lorg/cipherkey/data/repository/MessageCipher;", "context", "Landroid/content/Context;", "<init>", "(Lorg/cipherkey/data/preferences/SettingsRepository;Lorg/cipherkey/app/security/BiometricKeyStore;Lorg/cipherkey/data/repository/MessageCipher;Landroid/content/Context;)V", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "Lorg/cipherkey/data/preferences/AppSettings;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "_biometricAvailable", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "biometricAvailable", "getBiometricAvailable", "_overlayGranted", "overlayGranted", "getOverlayGranted", "_events", "Lorg/cipherkey/app/SettingsEvent;", "events", "getEvents", "refresh", "", "setSessionTimeoutMinutes", "minutes", "", "setSessionTimeoutMillis", "millis", "", "setArgon2Profile", "profile", "Lorg/cipherkey/security/policies/Argon2Profile;", "setBiometricEnabled", "enabled", "setAssistantEnabled", "lockAllNow", "consumeEvent", "markOnboardingComplete", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<Boolean> _biometricAvailable;
    private final MutableStateFlow<SettingsEvent> _events;
    private final MutableStateFlow<Boolean> _overlayGranted;
    private final StateFlow<Boolean> biometricAvailable;
    private final BiometricKeyStore biometrics;
    private final Context context;
    private final StateFlow<SettingsEvent> events;
    private final MessageCipher messageCipher;
    private final StateFlow<Boolean> overlayGranted;
    private final StateFlow<AppSettings> settings;
    private final SettingsRepository settingsRepository;

    @Inject
    public SettingsViewModel(SettingsRepository settingsRepository, BiometricKeyStore biometrics, MessageCipher messageCipher, @ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(settingsRepository, "settingsRepository");
        Intrinsics.checkNotNullParameter(biometrics, "biometrics");
        Intrinsics.checkNotNullParameter(messageCipher, "messageCipher");
        Intrinsics.checkNotNullParameter(context, "context");
        this.settingsRepository = settingsRepository;
        this.biometrics = biometrics;
        this.messageCipher = messageCipher;
        this.context = context;
        this.settings = FlowKt.stateIn(settingsRepository.getSettings(), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), new AppSettings(0L, null, false, false, false, 31, null));
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(false);
        this._biometricAvailable = MutableStateFlow;
        this.biometricAvailable = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<Boolean> MutableStateFlow2 = StateFlowKt.MutableStateFlow(false);
        this._overlayGranted = MutableStateFlow2;
        this.overlayGranted = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<SettingsEvent> MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._events = MutableStateFlow3;
        this.events = FlowKt.asStateFlow(MutableStateFlow3);
        refresh();
    }

    public final StateFlow<AppSettings> getSettings() {
        return this.settings;
    }

    public final StateFlow<Boolean> getBiometricAvailable() {
        return this.biometricAvailable;
    }

    public final StateFlow<Boolean> getOverlayGranted() {
        return this.overlayGranted;
    }

    public final StateFlow<SettingsEvent> getEvents() {
        return this.events;
    }

    public final void refresh() {
        this._biometricAvailable.setValue(Boolean.valueOf(this.biometrics.isDeviceCapable()));
        this._overlayGranted.setValue(Boolean.valueOf(Settings.canDrawOverlays(this.context)));
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$setSessionTimeoutMinutes$1", f = "SettingsViewModel.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$setSessionTimeoutMinutes$1, reason: invalid class name and case insensitive filesystem */
    static final class C04911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $minutes;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04911(int i, Continuation<? super C04911> continuation) {
            super(2, continuation);
            this.$minutes = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04911(this.$minutes, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setSessionTimeoutMillis(this.$minutes * KeyPolicy.MIN_SESSION_TIMEOUT_MILLIS, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void setSessionTimeoutMinutes(int minutes) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04911(minutes, null), 3, null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$setSessionTimeoutMillis$1", f = "SettingsViewModel.kt", i = {}, l = {68}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$setSessionTimeoutMillis$1, reason: invalid class name and case insensitive filesystem */
    static final class C04901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $millis;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04901(long j, Continuation<? super C04901> continuation) {
            super(2, continuation);
            this.$millis = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04901(this.$millis, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04901) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setSessionTimeoutMillis(this.$millis, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void setSessionTimeoutMillis(long millis) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04901(millis, null), 3, null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$setArgon2Profile$1", f = "SettingsViewModel.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$setArgon2Profile$1, reason: invalid class name and case insensitive filesystem */
    static final class C04871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Argon2Profile $profile;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04871(Argon2Profile argon2Profile, Continuation<? super C04871> continuation) {
            super(2, continuation);
            this.$profile = argon2Profile;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04871(this.$profile, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setArgon2Profile(this.$profile, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void setArgon2Profile(Argon2Profile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04871(profile, null), 3, null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$setBiometricEnabled$1", f = "SettingsViewModel.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$setBiometricEnabled$1, reason: invalid class name and case insensitive filesystem */
    static final class C04891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04891(boolean z, Continuation<? super C04891> continuation) {
            super(2, continuation);
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04891(this.$enabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setBiometricUnlockEnabled(this.$enabled, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (!this.$enabled) {
                SettingsViewModel.this.biometrics.clearAll();
            }
            return Unit.INSTANCE;
        }
    }

    public final void setBiometricEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04891(enabled, null), 3, null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$setAssistantEnabled$1", f = "SettingsViewModel.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$setAssistantEnabled$1, reason: invalid class name and case insensitive filesystem */
    static final class C04881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04881(boolean z, Continuation<? super C04881> continuation) {
            super(2, continuation);
            this.$enabled = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04881(this.$enabled, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setAssistantEnabled(this.$enabled, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AssistantReadiness.Action actionOnToggle = AssistantReadiness.INSTANCE.onToggle(this.$enabled, Settings.canDrawOverlays(SettingsViewModel.this.context));
            if (Intrinsics.areEqual(actionOnToggle, AssistantReadiness.Action.StartService.INSTANCE)) {
                FloatingAssistantService.INSTANCE.start(SettingsViewModel.this.context);
            } else if (Intrinsics.areEqual(actionOnToggle, AssistantReadiness.Action.StopService.INSTANCE)) {
                FloatingAssistantService.INSTANCE.stop(SettingsViewModel.this.context);
            } else if (Intrinsics.areEqual(actionOnToggle, AssistantReadiness.Action.RequestOverlayPermission.INSTANCE)) {
                SettingsViewModel.this._events.setValue(SettingsEvent.RequestOverlayPermission.INSTANCE);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            return Unit.INSTANCE;
        }
    }

    public final void setAssistantEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04881(enabled, null), 3, null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$lockAllNow$1", f = "SettingsViewModel.kt", i = {}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$lockAllNow$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.messageCipher.lockAll(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void lockAllNow() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
    }

    public final void consumeEvent() {
        this._events.setValue(null);
    }

    /* compiled from: SettingsViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "org.cipherkey.app.SettingsViewModel$markOnboardingComplete$1", f = "SettingsViewModel.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: org.cipherkey.app.SettingsViewModel$markOnboardingComplete$1, reason: invalid class name and case insensitive filesystem */
    static final class C04861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C04861(Continuation<? super C04861> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SettingsViewModel.this.new C04861(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (SettingsViewModel.this.settingsRepository.setOnboardingCompleted(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void markOnboardingComplete() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C04861(null), 3, null);
    }
}
