package org.cipherkey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.cipherkey.app.companion.UiNotice
import org.cipherkey.app.security.BiometricDiagnostics
import org.cipherkey.app.security.BiometricGate
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.security.BiometricPromptRequest
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.sessions.wipe
import org.cipherkey.data.preferences.SettingsRepository
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
    private val biometricStore: BiometricKeyStore,
    private val settings: SettingsRepository,
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

    // ------------------------------------------------------------ biometric (v0.4.1)

    /** System-prompt request while the detail dialog is open (null = none). */
    private val _biometricPrompt = MutableStateFlow<BiometricPromptRequest?>(null)
    val biometricPrompt: StateFlow<BiometricPromptRequest?> = _biometricPrompt.asStateFlow()

    /** True when the contact dialog should offer "Use fingerprint". */
    private val _biometricOffered = MutableStateFlow(false)
    val biometricOffered: StateFlow<Boolean> = _biometricOffered.asStateFlow()

    /** Honest in-dialog notices (rendered where the user looks, not behind the scrim). */
    private val _dialogNotice = MutableStateFlow<UiNotice?>(null)
    val dialogNotice: StateFlow<UiNotice?> = _dialogNotice.asStateFlow()

    private var dialogContactId: ContactId? = null
    private var lastUnlockedContactId: ContactId? = null

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

    /**
     * v0.4.1: opens the detail dialog — recomputes the fingerprint offer
     * for THIS contact/era and remembers whose dialog is on screen.
     */
    fun openDetail(contactId: ContactId) {
        dialogContactId = contactId
        _dialogNotice.value = null
        refreshBiometricOffer(contactId)
    }

    /** v0.4.1: gate + wrap presence for the current dialog contact. */
    fun refreshBiometricOffer(contactId: ContactId) {
        viewModelScope.launch {
            val enabled = settings.settings.first().biometricUnlockEnabled
            val gate = BiometricDiagnostics.gateFor(biometricStore.strongAuthResult())
            if (!enabled || gate != BiometricGate.AVAILABLE) {
                _biometricOffered.value = false
                return@launch
            }
            val key = biometricContactKeyFor(contactId)
                ?: run { _biometricOffered.value = false; return@launch }
            _biometricOffered.value = biometricStore.hasWrapped(key)
        }
    }

    /** v0.4.1: "Use fingerprint" in the dialog — show the system prompt. */
    fun requestBiometricUnlock() {
        val id = dialogContactId ?: return
        viewModelScope.launch {
            val key = biometricContactKeyFor(id) ?: return@launch
            if (!biometricStore.hasWrapped(key)) return@launch
            _dialogNotice.value = null
            _biometricPrompt.value = BiometricPromptRequest.Unlock(key)
        }
    }

    /** v0.4.1: outcome of the system prompt (null = canceled / failed). */
    fun onBiometricUnlockResult(keyBytes: ByteArray?) {
        _biometricPrompt.value = null
        if (keyBytes == null) {
            _dialogNotice.value = UiNotice(R.string.notice_biometric_failed, null)
            return
        }
        val id = dialogContactId
        if (id == null) {
            keyBytes.fill(0)
            return
        }
        viewModelScope.launch {
            val key = biometricContactKeyFor(id)
            when (messageCipher.unlockContactWithKey(id, keyBytes)) {
                UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> {
                    refreshUnlocked()
                    refreshFingerprint(id)
                    _dialogNotice.value = null
                    _biometricOffered.value = true
                }
                UnlockOutcome.NoSession ->
                    _dialogNotice.value = UiNotice(R.string.notice_no_era, null)
                else -> {
                    // Stale wrap (era rotated): discard it honestly.
                    key?.let { biometricStore.clear(it) }
                    _dialogNotice.value = UiNotice(R.string.notice_biometric_stale, null)
                    _biometricOffered.value = false
                }
            }
        }
    }

    /** v0.4.1: after a passphrase unlock, offer to remember the key. */
    fun offerRemember(contactId: ContactId) {
        viewModelScope.launch {
            val enabled = settings.settings.first().biometricUnlockEnabled
            val gate = BiometricDiagnostics.gateFor(biometricStore.strongAuthResult())
            if (!enabled || gate != BiometricGate.AVAILABLE) return@launch
            val key = biometricContactKeyFor(contactId) ?: return@launch
            lastUnlockedContactId = contactId
            if (!biometricStore.hasWrapped(key)) {
                _biometricPrompt.value = BiometricPromptRequest.Remember(key)
            }
        }
    }

    /** v0.4.1: the key bytes the Remember prompt should wrap. */
    suspend fun consumeRememberKey(): ByteArray? {
        if (_biometricPrompt.value !is BiometricPromptRequest.Remember) return null
        val id = lastUnlockedContactId ?: return null
        return messageCipher.exportSessionKey(id)
    }

    /** v0.4.1: prompt finished — [success] says whether the wrap persisted. */
    fun onRememberDone(success: Boolean) {
        _biometricPrompt.value = null
        _biometricOffered.value = _biometricOffered.value || success
    }

    /** `"contactId:eraId"` — the AAD and storage key for a remembered wrap. */
    private suspend fun biometricContactKeyFor(contactId: ContactId): String? {
        val eraId = messageCipher.currentEraId(contactId)?.value ?: return null
        return "${contactId.value}:$eraId"
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
