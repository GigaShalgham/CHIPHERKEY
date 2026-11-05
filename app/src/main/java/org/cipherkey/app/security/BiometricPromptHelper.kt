package org.cipherkey.app.security

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * Bridges the UI to the system fingerprint prompt (v0.3.0).
 *
 * Every operation goes through a [BiometricPrompt.CryptoObject] so the
 * Keystore cipher itself is what the fingerprint authorizes — CipherKey
 * never receives or stores biometric data, and a failed/expired
 * authorization simply yields no key bytes.
 *
 * Results are delivered as nullable callbacks on the main thread:
 *  - [unlockKey]: the unwrapped session key, or null (nothing remembered,
 *    stale wrap, user cancel, or hardware failure — the passphrase path
 *    always remains available);
 *  - [rememberKey]: whether the wrap was persisted. The plaintext buffer
 *    is zeroed on EVERY path, success or failure.
 */
class BiometricPromptHelper(
    private val activity: FragmentActivity,
    private val store: BiometricKeyStore,
) {

    private val executor = ContextCompat.getMainExecutor(activity)

    fun canPrompt(): Boolean = store.isDeviceCapable()

    /** Unwrap the remembered key for [contactKey] behind the fingerprint. */
    fun unlockKey(contactKey: String, onResult: (ByteArray?) -> Unit) {
        val wrapped = store.readWrapped(contactKey)
        if (wrapped == null) {
            onResult(null)
            return
        }
        val cipher = store.newUnwrapCipher(wrapped)
        if (cipher == null) {
            // Blob too short or Keystore unavailable: the entry can never
            // open again — discard it so the UI stops offering it.
            store.clear(contactKey)
            onResult(null)
            return
        }
        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(code: Int, message: CharSequence) {
                    onResult(null)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    val authorized = result.cryptoObject?.cipher
                    if (authorized == null) {
                        onResult(null)
                        return
                    }
                    try {
                        val keyBytes = store.finishUnwrap(authorized, wrapped, aad(contactKey))
                        if (keyBytes.isEmpty()) {
                            onResult(null)
                        } else {
                            onResult(keyBytes)
                        }
                    } catch (e: Exception) {
                        // GCM tag mismatch: the era rotated since the wrap
                        // (AAD changed) or the blob is corrupt — discard.
                        store.clear(contactKey)
                        onResult(null)
                    }
                }
            },
        )
        prompt.authenticate(
            promptInfo(
                titleRes = org.cipherkey.app.R.string.biometric_prompt_title_unlock,
            ),
            BiometricPrompt.CryptoObject(cipher),
        )
    }

    /**
     * Wrap [plain] for [contactKey] behind the fingerprint and persist it.
     * [plain] is wiped on every path — the caller never has to do it.
     */
    fun rememberKey(contactKey: String, plain: ByteArray, onDone: (Boolean) -> Unit) {
        val cipher = store.newWrapCipher()
        if (cipher == null) {
            plain.fill(0)
            onDone(false)
            return
        }
        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(code: Int, message: CharSequence) {
                    plain.fill(0)
                    onDone(false)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    val authorized = result.cryptoObject?.cipher
                    if (authorized == null) {
                        plain.fill(0)
                        onDone(false)
                        return
                    }
                    try {
                        val blob = store.finishWrap(authorized, plain, aad(contactKey))
                        plain.fill(0)
                        store.persistWrapped(contactKey, blob)
                        onDone(true)
                    } catch (e: Exception) {
                        plain.fill(0)
                        onDone(false)
                    }
                }
            },
        )
        prompt.authenticate(
            promptInfo(
                titleRes = org.cipherkey.app.R.string.biometric_prompt_title_remember,
            ),
            BiometricPrompt.CryptoObject(cipher),
        )
    }

    private fun promptInfo(titleRes: Int): BiometricPrompt.PromptInfo =
        BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(titleRes))
            .setSubtitle(activity.getString(org.cipherkey.app.R.string.biometric_prompt_subtitle))
            .setNegativeButtonText(
                activity.getString(org.cipherkey.app.R.string.biometric_prompt_negative),
            )
            // v0.4.1: STRONG only — a CryptoObject authorizes a Keystore key
            // created with AUTH_BIOMETRIC_STRONG, so a Class 2 sensor must
            // never be offered the prompt (v0.4.0 allowed WEAK|STRONG here,
            // which let weak sensors reach a cipher they can never authorize).
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setConfirmationRequired(false)
            .build()

    private fun aad(contactKey: String): ByteArray = contactKey.toByteArray(Charsets.UTF_8)
}
