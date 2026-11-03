package org.cipherkey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.cipherkey.data.preferences.AppSettings
import org.cipherkey.data.preferences.SettingsRepository
import org.cipherkey.security.policies.Argon2Profile

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    val settings: kotlinx.coroutines.flow.StateFlow<AppSettings> =
        settingsRepository.settings
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())

    fun setSessionTimeoutMinutes(minutes: Int) {
        viewModelScope.launch {
            settingsRepository.setSessionTimeoutMillis(minutes * 60_000L)
        }
    }

    fun setArgon2Profile(profile: Argon2Profile) {
        viewModelScope.launch {
            settingsRepository.setArgon2Profile(profile)
        }
    }

    fun markOnboardingComplete() {
        viewModelScope.launch {
            settingsRepository.setOnboardingCompleted()
        }
    }
}
