package org.cipherkey.app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.cipherkey.data.preferences.AppSettings
import org.cipherkey.data.preferences.SettingsRepository
import org.cipherkey.data.repository.MessageCipher
import org.cipherkey.app.assistant.AssistantReadiness
import org.cipherkey.app.assistant.FloatingAssistantService
import org.cipherkey.security.policies.Argon2Profile

/**
 * One-shot UI events raised by Settings interactions (v0.3.0) — consumed
 * by the screen so navigation/side effects survive recomposition.
 */
sealed interface SettingsEvent {
    /** Open the system "Display over other apps" screen for CipherKey. */
    data object RequestOverlayPermission : SettingsEvent
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val biometrics: org.cipherkey.app.security.BiometricKeyStore,
    private val messageCipher: MessageCipher,
    @dagger.hilt.android.qualifiers.ApplicationContext private val context: Context,
) : ViewModel() {

    val settings: StateFlow<AppSettings> =
        settingsRepository.settings
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())

    /** True when this device has enrolled biometrics (drives the row state). */
    private val _biometricAvailable = MutableStateFlow(false)
    val biometricAvailable = _biometricAvailable.asStateFlow()

    /** True while the overlay permission is granted (re-checked on resume). */
    private val _overlayGranted = MutableStateFlow(false)
    val overlayGranted = _overlayGranted.asStateFlow()

    private val _events = MutableSharedFlow<SettingsEvent>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    init {
        refresh()
    }

    /** Re-reads the hardware/permission state and re-evaluates the
     * assistant lifecycle (called on init and every screen resume).
     *
     * Deep-scan F2: when the user enables the assistant without the overlay
     * permission and grants it in system settings, the service must start
     * as soon as they return — not on the next app launch. Re-running this
     * on resume makes the "enabled + granted" state converge to
     * "service running" idempotently (startForegroundService on an
     * already-running service is a no-op update). */
    fun refresh() {
        viewModelScope.launch {
            _biometricAvailable.value = biometrics.isDeviceCapable()
            val overlay = android.provider.Settings.canDrawOverlays(context)
            _overlayGranted.value = overlay
            val enabled = settingsRepository.settings.first().assistantEnabled
            if (AssistantReadiness.shouldAutoStart(enabled, overlay)) {
                runCatching { FloatingAssistantService.start(context) }
            }
        }
    }

    fun setSessionTimeoutMinutes(minutes: Int) {
        setSessionTimeoutMillis(minutes * 60_000L)
    }

    fun setSessionTimeoutMillis(millis: Long) {
        viewModelScope.launch {
            settingsRepository.setSessionTimeoutMillis(millis)
        }
    }

    fun setArgon2Profile(profile: Argon2Profile) {
        viewModelScope.launch {
            settingsRepository.setArgon2Profile(profile)
        }
    }

    /**
     * v0.3.0: fingerprint unlock toggle. Turning it OFF wipes every
     * remembered wrap and the Keystore wrap key — "off" must mean the
     * data is gone, not merely hidden.
     */
    fun setBiometricEnabled(enabled: Boolean) {
        viewModelScope.launch {
            if (enabled && !biometrics.isDeviceCapable()) {
                // Nothing enrolled: refuse silently — the row renders the
                // "No fingerprint enrolled on this device" caption instead.
                return@launch
            }
            if (!enabled) {
                biometrics.clearAll()
            }
            settingsRepository.setBiometricUnlockEnabled(enabled)
        }
    }

    /**
     * v0.3.0: floating assistant toggle. Also starts/stops the service so
     * the change is immediately visible, not just after the next launch.
     */
    fun setAssistantEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setAssistantEnabled(enabled)
            val action = AssistantReadiness
                .actionFor(enabled, android.provider.Settings.canDrawOverlays(context))
            when (action) {
                AssistantReadiness.Action.StartService ->
                    runCatching { FloatingAssistantService.start(context) }
                AssistantReadiness.Action.StopService ->
                    FloatingAssistantService.stop(context)
                AssistantReadiness.Action.None ->
                    _events.emit(SettingsEvent.RequestOverlayPermission)
            }
        }
    }

    /** Locks every unlocked session right now (panic affordance). */
    fun lockAllNow() {
        viewModelScope.launch {
            messageCipher.lockAll()
        }
    }

    fun consumeEvent() {
        // SharedFlow consumes implicitly; kept for call-site symmetry.
    }

    fun markOnboardingComplete() {
        viewModelScope.launch {
            settingsRepository.setOnboardingCompleted()
        }
    }
}
