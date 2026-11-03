package org.cipherkey.app

import android.content.Context
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.cipherkey.data.repository.ContactRepository

/** Live status of the CipherKey input method in the system. */
data class ImeStatus(
    val enabled: Boolean = false,
    val selectedAsDefault: Boolean = false,
)

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val contactsRepository: ContactRepository,
) : ViewModel() {

    private val _imeStatus = MutableStateFlow(ImeStatus())
    val imeStatus = _imeStatus.asStateFlow()

    private val _contactCount = MutableStateFlow(0)
    val contactCount = _contactCount.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val imm = appContext.getSystemService(InputMethodManager::class.java)
            val enabled = imm?.enabledInputMethodList
                ?.any { it.packageName == appContext.packageName } == true
            val selected = Settings.Secure.getString(
                appContext.contentResolver,
                Settings.Secure.DEFAULT_INPUT_METHOD,
            )?.startsWith(appContext.packageName) == true
            _imeStatus.value = ImeStatus(enabled = enabled, selectedAsDefault = selected)
            _contactCount.value = contactsRepository.count()
        }
    }

    override fun onCleared() {
        // Nothing sensitive is held here.
        super.onCleared()
    }
}
