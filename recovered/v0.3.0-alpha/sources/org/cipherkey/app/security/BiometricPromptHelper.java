package org.cipherkey.app.security;

import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import java.util.concurrent.Executor;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.cipherkey.app.R;

/* compiled from: BiometricPromptHelper.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\r0\u0011J*\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00122\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r0\u0011J\u0012\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/cipherkey/app/security/BiometricPromptHelper;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "store", "Lorg/cipherkey/app/security/BiometricKeyStore;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lorg/cipherkey/app/security/BiometricKeyStore;)V", "executor", "Ljava/util/concurrent/Executor;", "canPrompt", "", "unlockKey", "", "contactKey", "", "onResult", "Lkotlin/Function1;", "", "rememberKey", "plain", "onDone", "promptInfo", "Landroidx/biometric/BiometricPrompt$PromptInfo;", "titleRes", "", "aad", "app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BiometricPromptHelper {
    public static final int $stable = 8;
    private final FragmentActivity activity;
    private final Executor executor;
    private final BiometricKeyStore store;

    public BiometricPromptHelper(FragmentActivity activity, BiometricKeyStore store) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(store, "store");
        this.activity = activity;
        this.store = store;
        Executor mainExecutor = ContextCompat.getMainExecutor(activity);
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(...)");
        this.executor = mainExecutor;
    }

    public final boolean canPrompt() {
        return this.store.isDeviceCapable();
    }

    public final void unlockKey(final String contactKey, final Function1<? super byte[], Unit> onResult) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        final byte[] wrapped = this.store.readWrapped(contactKey);
        if (wrapped == null) {
            onResult.invoke(null);
            return;
        }
        Cipher cipherNewUnwrapCipher = this.store.newUnwrapCipher(wrapped);
        if (cipherNewUnwrapCipher == null) {
            this.store.clear(contactKey);
            onResult.invoke(null);
        } else {
            new BiometricPrompt(this.activity, this.executor, new BiometricPrompt.AuthenticationCallback() { // from class: org.cipherkey.app.security.BiometricPromptHelper$unlockKey$prompt$1
                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationFailed() {
                }

                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    BiometricPrompt.CryptoObject cryptoObject = result.getCryptoObject();
                    Cipher cipher = cryptoObject != null ? cryptoObject.getCipher() : null;
                    if (cipher != null) {
                        try {
                            byte[] bArrFinishUnwrap = this.store.finishUnwrap(cipher, wrapped, this.aad(contactKey));
                            if (bArrFinishUnwrap.length == 0) {
                                onResult.invoke(null);
                                return;
                            } else {
                                onResult.invoke(bArrFinishUnwrap);
                                return;
                            }
                        } catch (Exception unused) {
                            this.store.clear(contactKey);
                            onResult.invoke(null);
                            return;
                        }
                    }
                    onResult.invoke(null);
                }

                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationError(int code, CharSequence message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    onResult.invoke(null);
                }
            }).authenticate(promptInfo(R.string.biometric_prompt_title_unlock), new BiometricPrompt.CryptoObject(cipherNewUnwrapCipher));
        }
    }

    public final void rememberKey(final String contactKey, final byte[] plain, final Function1<? super Boolean, Unit> onDone) {
        Intrinsics.checkNotNullParameter(contactKey, "contactKey");
        Intrinsics.checkNotNullParameter(plain, "plain");
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        Cipher cipherNewWrapCipher = this.store.newWrapCipher();
        if (cipherNewWrapCipher == null) {
            ArraysKt.fill$default(plain, (byte) 0, 0, 0, 6, (Object) null);
            onDone.invoke(false);
        } else {
            new BiometricPrompt(this.activity, this.executor, new BiometricPrompt.AuthenticationCallback() { // from class: org.cipherkey.app.security.BiometricPromptHelper$rememberKey$prompt$1
                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationFailed() {
                }

                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    BiometricPrompt.CryptoObject cryptoObject = result.getCryptoObject();
                    Cipher cipher = cryptoObject != null ? cryptoObject.getCipher() : null;
                    if (cipher != null) {
                        try {
                            byte[] bArrFinishWrap = this.store.finishWrap(cipher, plain, this.aad(contactKey));
                            ArraysKt.fill$default(plain, (byte) 0, 0, 0, 6, (Object) null);
                            this.store.persistWrapped(contactKey, bArrFinishWrap);
                            onDone.invoke(true);
                            return;
                        } catch (Exception unused) {
                            ArraysKt.fill$default(plain, (byte) 0, 0, 0, 6, (Object) null);
                            onDone.invoke(false);
                            return;
                        }
                    }
                    ArraysKt.fill$default(plain, (byte) 0, 0, 0, 6, (Object) null);
                    onDone.invoke(false);
                }

                @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
                public void onAuthenticationError(int code, CharSequence message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    ArraysKt.fill$default(plain, (byte) 0, 0, 0, 6, (Object) null);
                    onDone.invoke(false);
                }
            }).authenticate(promptInfo(R.string.biometric_prompt_title_remember), new BiometricPrompt.CryptoObject(cipherNewWrapCipher));
        }
    }

    private final BiometricPrompt.PromptInfo promptInfo(int titleRes) {
        BiometricPrompt.PromptInfo promptInfoBuild = new BiometricPrompt.PromptInfo.Builder().setTitle(this.activity.getString(titleRes)).setSubtitle(this.activity.getString(R.string.biometric_prompt_subtitle)).setNegativeButtonText(this.activity.getString(R.string.biometric_prompt_negative)).setAllowedAuthenticators(15).setConfirmationRequired(false).build();
        Intrinsics.checkNotNullExpressionValue(promptInfoBuild, "build(...)");
        return promptInfoBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] aad(String contactKey) {
        byte[] bytes = contactKey.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }
}
