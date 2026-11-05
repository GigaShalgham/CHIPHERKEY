package org.cipherkey.data.preferences;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.cipherkey.security.policies.Argon2Profile;
import org.cipherkey.security.policies.KeyPolicy;

/* compiled from: SettingsRepository.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006 "}, d2 = {"Lorg/cipherkey/data/preferences/AppSettings;", "", "sessionTimeoutMillis", "", "argon2Profile", "Lorg/cipherkey/security/policies/Argon2Profile;", "onboardingCompleted", "", "biometricUnlockEnabled", "assistantEnabled", "<init>", "(JLorg/cipherkey/security/policies/Argon2Profile;ZZZ)V", "getSessionTimeoutMillis", "()J", "getArgon2Profile", "()Lorg/cipherkey/security/policies/Argon2Profile;", "getOnboardingCompleted", "()Z", "getBiometricUnlockEnabled", "getAssistantEnabled", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "preferences_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class AppSettings {
    private final Argon2Profile argon2Profile;
    private final boolean assistantEnabled;
    private final boolean biometricUnlockEnabled;
    private final boolean onboardingCompleted;
    private final long sessionTimeoutMillis;

    public AppSettings() {
        this(0L, null, false, false, false, 31, null);
    }

    public static /* synthetic */ AppSettings copy$default(AppSettings appSettings, long j, Argon2Profile argon2Profile, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = appSettings.sessionTimeoutMillis;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            argon2Profile = appSettings.argon2Profile;
        }
        Argon2Profile argon2Profile2 = argon2Profile;
        if ((i & 4) != 0) {
            z = appSettings.onboardingCompleted;
        }
        boolean z4 = z;
        if ((i & 8) != 0) {
            z2 = appSettings.biometricUnlockEnabled;
        }
        boolean z5 = z2;
        if ((i & 16) != 0) {
            z3 = appSettings.assistantEnabled;
        }
        return appSettings.copy(j2, argon2Profile2, z4, z5, z3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getSessionTimeoutMillis() {
        return this.sessionTimeoutMillis;
    }

    /* renamed from: component2, reason: from getter */
    public final Argon2Profile getArgon2Profile() {
        return this.argon2Profile;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getOnboardingCompleted() {
        return this.onboardingCompleted;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getBiometricUnlockEnabled() {
        return this.biometricUnlockEnabled;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getAssistantEnabled() {
        return this.assistantEnabled;
    }

    public final AppSettings copy(long sessionTimeoutMillis, Argon2Profile argon2Profile, boolean onboardingCompleted, boolean biometricUnlockEnabled, boolean assistantEnabled) {
        Intrinsics.checkNotNullParameter(argon2Profile, "argon2Profile");
        return new AppSettings(sessionTimeoutMillis, argon2Profile, onboardingCompleted, biometricUnlockEnabled, assistantEnabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppSettings)) {
            return false;
        }
        AppSettings appSettings = (AppSettings) other;
        return this.sessionTimeoutMillis == appSettings.sessionTimeoutMillis && this.argon2Profile == appSettings.argon2Profile && this.onboardingCompleted == appSettings.onboardingCompleted && this.biometricUnlockEnabled == appSettings.biometricUnlockEnabled && this.assistantEnabled == appSettings.assistantEnabled;
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.sessionTimeoutMillis) * 31) + this.argon2Profile.hashCode()) * 31) + Boolean.hashCode(this.onboardingCompleted)) * 31) + Boolean.hashCode(this.biometricUnlockEnabled)) * 31) + Boolean.hashCode(this.assistantEnabled);
    }

    public String toString() {
        return "AppSettings(sessionTimeoutMillis=" + this.sessionTimeoutMillis + ", argon2Profile=" + this.argon2Profile + ", onboardingCompleted=" + this.onboardingCompleted + ", biometricUnlockEnabled=" + this.biometricUnlockEnabled + ", assistantEnabled=" + this.assistantEnabled + ")";
    }

    public AppSettings(long j, Argon2Profile argon2Profile, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(argon2Profile, "argon2Profile");
        this.sessionTimeoutMillis = j;
        this.argon2Profile = argon2Profile;
        this.onboardingCompleted = z;
        this.biometricUnlockEnabled = z2;
        this.assistantEnabled = z3;
    }

    public final long getSessionTimeoutMillis() {
        return this.sessionTimeoutMillis;
    }

    public /* synthetic */ AppSettings(long j, Argon2Profile argon2Profile, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? KeyPolicy.DEFAULT_SESSION_TIMEOUT_MILLIS : j, (i & 2) != 0 ? Argon2Profile.INSTANCE.getDEFAULT() : argon2Profile, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3);
    }

    public final Argon2Profile getArgon2Profile() {
        return this.argon2Profile;
    }

    public final boolean getOnboardingCompleted() {
        return this.onboardingCompleted;
    }

    public final boolean getBiometricUnlockEnabled() {
        return this.biometricUnlockEnabled;
    }

    public final boolean getAssistantEnabled() {
        return this.assistantEnabled;
    }
}
