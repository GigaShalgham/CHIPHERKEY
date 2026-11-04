package org.cipherkey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.sessions.wipe
import org.cipherkey.data.repository.Contact
import org.cipherkey.data.repository.ContactRepository
import org.cipherkey.data.repository.ContactSession
import org.cipherkey.data.repository.EraImport
import org.cipherkey.data.repository.MessageCipher
import org.cipherkey.data.repository.SessionKeyRepository
import org.cipherkey.data.repository.UnlockOutcome
import org.cipherkey.core.messages.Ck1FormatException
import org.cipherkey.core.messages.InviteCodec
import org.cipherkey.security.clipboard.ClipboardAccess
import org.cipherkey.security.policies.Argon2Profile

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsRepository: ContactRepository,
    private val sessionKeys: SessionKeyRepository,
    private val messageCipher: MessageCipher,
    private val clipboard: ClipboardAccess,
) : ViewModel() {

    val contacts: kotlinx.coroutines.flow.StateFlow<List<Contact>> =
        contactsRepository.observeContacts()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _unlocked = MutableStateFlow<Set<ContactId>>(emptySet())
    val unlocked = _unlocked.asStateFlow()

    private val _eras = MutableStateFlow<Map<ContactId, List<ContactSession>>>(emptyMap())
    val eras = _eras.asStateFlow()

    private val _feedback = MutableStateFlow<String?>(null)
    val feedback = _feedback.asStateFlow()

    private val _fingerprints = MutableStateFlow<Map<ContactId, String>>(emptyMap())
    val fingerprints = _fingerprints.asStateFlow()

    fun refreshAll() {
        viewModelScope.launch {
            refreshUnlocked()
            contacts.value.forEach { loadEras(it.id, notify = false) }
        }
    }

    fun loadEras(contactId: ContactId, notify: Boolean = true) {
        viewModelScope.launch {
            val list = sessionKeys.erasForContact(contactId)
            _eras.update { it + (contactId to list) }
            refreshUnlocked()
            if (notify && list.isEmpty()) {
                _feedback.value = "No key era for this contact yet"
            }
        }
    }

    /**
     * Creates a contact and either imports the peer's invite (CKS1 code) or
     * generates a fresh key era whose invite the user then shares.
     *
     * The outcome is delivered to [onResult] as (ok, message) so the ADD
     * DIALOG can show failures itself (deep-scan finding 6: they used to be
     * written only to the home-screen feedback column behind the dialog's
     * scrim, invisible at the moment they happened).
     */
    fun createContact(
        displayName: String,
        inviteText: String?,
        profile: Argon2Profile,
        onResult: (ok: Boolean, message: String?) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val contact = contactsRepository.createContact(displayName)
                if (!inviteText.isNullOrBlank()) {
                    val invite = InviteCodec.decode(inviteText.trim())
                    when (sessionKeys.importEra(contact.id, invite)) {
                        is EraImport.Imported, is EraImport.AlreadyPresent -> Unit
                        EraImport.KeyBelongsToOtherContact -> {
                            contactsRepository.deleteContact(contact.id)
                            val message = "This invite key already belongs to another contact"
                            _feedback.value = message
                            onResult(false, message)
                            return@launch
                        }
                    }
                } else {
                    sessionKeys.createEra(contact.id, profile.params)
                }
                loadEras(contact.id, notify = false)
                _feedback.value = "Contact created"
                onResult(true, null)
            } catch (e: Ck1FormatException) {
                val message = "Invalid invite code: ${e.message}"
                _feedback.value = message
                onResult(false, message)
            } catch (e: IllegalArgumentException) {
                val message = e.message ?: "Invalid contact"
                _feedback.value = message
                onResult(false, message)
            }
        }
    }

    /**
     * Copies text (e.g. an invite code) through the ClipboardAccess
     * chokepoint (deep-scan finding 14) and reports whether it worked.
     */
    fun copyText(text: String): Boolean = clipboard.setText(text)

    fun unlockContact(contactId: ContactId, passphrase: CharArray, onResult: (UnlockOutcome) -> Unit) {
        viewModelScope.launch {
            val outcome = messageCipher.unlockContact(contactId, passphrase)
            passphrase.wipe()
            refreshUnlocked()
            refreshFingerprint(contactId)
            onResult(outcome)
        }
    }

    fun lockContact(contactId: ContactId) {
        viewModelScope.launch {
            messageCipher.lockContact(contactId)
            refreshUnlocked()
            _fingerprints.update { it - contactId }
        }
    }

    fun lockAll() {
        viewModelScope.launch {
            messageCipher.lockAll()
            refreshUnlocked()
            _fingerprints.value = emptyMap()
        }
    }

    fun deleteContact(contactId: ContactId) {
        viewModelScope.launch {
            messageCipher.lockContact(contactId)
            contactsRepository.deleteContact(contactId)
            _eras.update { it - contactId }
            // Deep-scan finding 15: the fingerprint cache belonged to the
            // unlocked session that no longer exists.
            _fingerprints.update { it - contactId }
            _feedback.value = "Contact deleted"
        }
    }

    fun clearFeedback() {
        _feedback.value = null
    }

    private suspend fun refreshUnlocked() {
        val unlockedIds = contacts.value
            .map { it.id }
            .filter { messageCipher.isUnlocked(it) }
            .toSet()
        _unlocked.value = unlockedIds
    }

    private suspend fun refreshFingerprint(contactId: ContactId) {
        val fingerprint = messageCipher.activeSessionFingerprint(contactId)
        if (fingerprint != null) {
            _fingerprints.update { it + (contactId to fingerprint) }
        } else {
            _fingerprints.update { it - contactId }
        }
    }
}
