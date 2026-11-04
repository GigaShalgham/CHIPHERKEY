package org.cipherkey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cipherkey.data.repository.ContactRepository
import org.cipherkey.data.repository.MessageCipher

/**
 * Home-screen state for the companion architecture (spec 003).
 *
 * The IME-status plumbing from v0.1 was removed together with the
 * `:keyboard` dependency: the shipped app no longer registers an input
 * method at all, so there is nothing to query.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactsRepository: ContactRepository,
    private val messageCipher: MessageCipher,
) : ViewModel() {

    private val _contactCount = MutableStateFlow(0)
    val contactCount = _contactCount.asStateFlow()

    private val _unlockedCount = MutableStateFlow(0)
    val unlockedCount = _unlockedCount.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _contactCount.value = contactsRepository.count()
            _unlockedCount.value = contactsRepository.observeContacts()
                .first()
                .count { messageCipher.isUnlocked(it.id) }
        }
    }

    override fun onCleared() {
        // Nothing sensitive is held here.
        super.onCleared()
    }
}
