package org.cipherkey.security.clipboard;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public final class AndroidClipboardAccess_Factory implements Factory<AndroidClipboardAccess> {
    private final Provider<Context> contextProvider;

    public AndroidClipboardAccess_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AndroidClipboardAccess get() {
        return newInstance(this.contextProvider.get());
    }

    public static AndroidClipboardAccess_Factory create(Provider<Context> provider) {
        return new AndroidClipboardAccess_Factory(provider);
    }

    public static AndroidClipboardAccess newInstance(Context context) {
        return new AndroidClipboardAccess(context);
    }
}
