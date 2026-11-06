package org.cipherkey.app

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cipherkey.app.assistant.AssistantReadiness
import org.cipherkey.app.assistant.FloatingAssistantService
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.ui.CipherKeyApp
import org.cipherkey.app.ui.theme.CipherKeyTheme
import org.cipherkey.data.preferences.SettingsRepository
import android.provider.Settings as AndroidSettings

/**
 * The app-wide fingerprint key store, provided to the Compose tree so the
 * tools screens can host the system biometric prompt (v0.3.0).
 */
val LocalBiometrics = staticCompositionLocalOf<BiometricKeyStore> {
    error("BiometricKeyStore not provided")
}

/**
 * Single-activity host for all CipherKey management UI (status, tools,
 * contacts and security settings).
 *
 * v0.3.0: extends [FragmentActivity] because the system BiometricPrompt
 * (fingerprint unlock / remember) requires a FragmentActivity host.
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var biometrics: BiometricKeyStore

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // v0.4.2 (deep-scan, FLAG_SECURE): CipherKey's surfaces show decrypted
        // plaintext and passphrase fields — keep them out of screenshots and
        // the recents thumbnail preview.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE,
        )
        setContent {
            androidx.compose.runtime.CompositionLocalProvider(
                LocalBiometrics provides biometrics,
            ) {
                CipherKeyTheme {
                    CipherKeyApp()
                }
            }
        }
        maybeAutoStartAssistant()
    }

    /**
     * v0.3.0: if the user enabled the floating assistant and the overlay
     * permission is granted, the bubble comes back on every launch.
     * Missing permission never crashes — Settings explains how to grant it.
     */
    private fun maybeAutoStartAssistant() {
        lifecycleScope.launch {
            runCatching {
                val settings = settingsRepository.settings.first()
                if (AssistantReadiness.shouldAutoStart(
                        enabled = settings.assistantEnabled,
                        overlayGranted = AndroidSettings.canDrawOverlays(this@MainActivity),
                    )
                ) {
                    FloatingAssistantService.start(this@MainActivity)
                }
            }
        }
    }
}
