package org.cipherkey.app.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity

/**
 * Creates the prompt helper for the current screen (v0.3.0), or null when
 * the host is not a [FragmentActivity] (BiometricPrompt's requirement —
 * MainActivity and every companion activity extend it).
 */
@Composable
fun rememberBiometricPromptHelper(store: BiometricKeyStore): BiometricPromptHelper? {
    val activity = LocalContext.current as? FragmentActivity ?: return null
    return remember(activity, store) { BiometricPromptHelper(activity, store) }
}

/**
 * Renders nothing. While [request] is non-null it launches the matching
 * system fingerprint prompt exactly once:
 *  - [BiometricPromptRequest.Unlock] unwraps the remembered key and returns
 *    the raw bytes (or null on cancel/stale) via [onUnlock];
 *  - [BiometricPromptRequest.Remember] wraps [pendingKey] — the session key
 *    exported right after a passphrase unlock — and reports whether the wrap
 *    persisted via [onRememberDone]. The helper wipes the buffer itself.
 *
 * ViewModels own the request state; this host exists because BiometricPrompt
 * is an imperative Android API that needs an Activity, not because any state
 * lives here.
 */
@Composable
fun BiometricPromptHost(
    helper: BiometricPromptHelper?,
    request: BiometricPromptRequest?,
    pendingKey: ByteArray?,
    onUnlock: (ByteArray?) -> Unit,
    onRememberDone: (Boolean) -> Unit,
) {
    LaunchedEffect(request, helper) {
        val prompt = helper ?: return@LaunchedEffect
        when (val current = request) {
            null -> Unit
            is BiometricPromptRequest.Unlock ->
                prompt.unlockKey(current.contactKey) { keyBytes -> onUnlock(keyBytes) }
            is BiometricPromptRequest.Remember -> {
                val plain = pendingKey
                if (plain == null) {
                    onRememberDone(false)
                } else {
                    prompt.rememberKey(current.contactKey, plain) { done -> onRememberDone(done) }
                }
            }
        }
    }
}
