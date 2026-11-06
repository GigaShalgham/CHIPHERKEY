package org.cipherkey.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.cipherkey.app.R
import org.cipherkey.app.companion.CompanionMode
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.security.BiometricPromptRequest
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.common.SessionId
import org.cipherkey.core.sessions.wipe
import org.cipherkey.data.preferences.SettingsRepository
import org.cipherkey.data.repository.Contact
import org.cipherkey.data.repository.ContactRepository
import org.cipherkey.data.repository.DecryptOutcome
import org.cipherkey.data.repository.EncryptOutcome
import org.cipherkey.data.repository.MessageCipher
import org.cipherkey.data.repository.UnlockOutcome
import org.cipherkey.security.clipboard.ClipboardAccess

/**
 * State of the in-app Tools tabs (v0.3.0): manual encrypt and decrypt of
 * typed or pasted text, outside the PROCESS_TEXT companion flows.
 */
data class ToolsUiState(
    val contacts: List<Contact> = emptyList(),
    val unlocked: Set<ContactId> = emptySet(),
    val selectedContactId: ContactId? = null,
    /** Final output: envelope (encrypt) or plaintext (decrypt). */
    val resultText: String? = null,
    val resultContactName: String? = null,
    /** True when resultText is a CK1 envelope (encrypt direction). */
    val resultIsEnvelope: Boolean = false,
    val copied: Boolean = false,
    val busy: Boolean = false,
    val notice: org.cipherkey.app.companion.UiNotice? = null,
    /** True while a locked contact waits for a passphrase. */
    val unlockNeeded: Boolean = false,
    val pendingContactId: ContactId? = null,
    val unlockContactName: String? = null,
    /**
     * v0.4.0: non-null while the pending unlock is for a SUPERSEDED key era
     * (old-era envelope). The passphrase prompt then explains that the OLD
     * era's passphrase is wanted — and the biometric offer stays off (a
     * fingerprint wrap is bound to the latest era only).
     */
    val pendingEraSessionId: SessionId? = null,
    /** v0.3.0: a remembered fingerprint wrap exists for the pending contact. */
    val biometricOffered: Boolean = false,
    /** v0.3.0: non-null while the system biometric prompt should be shown. */
    val biometricPrompt: BiometricPromptRequest? = null,
) {
    val selectedContact: Contact?
        get() = contacts.firstOrNull { it.id == selectedContactId }
}

/**
 * ViewModel behind the in-app Encrypt and Decrypt tabs (v0.3.0).
 *
 * The tabs exist for apps where neither the selection toolbar nor the
 * sharesheet is available: the user types or pastes text directly. All
 * crypto still goes through the [MessageCipher] facade; nothing here
 * re-implements the protocol.
 */
@HiltViewModel
class ToolsViewModel @Inject constructor(
    private val messageCipher: MessageCipher,
    private val contactsRepository: ContactRepository,
    private val settings: SettingsRepository,
    private val clipboard: ClipboardAccess,
    private val biometricStore: BiometricKeyStore,
) : ViewModel() {

    private val _state = MutableStateFlow(ToolsUiState())
    val state = _state.asStateFlow()

    /** The mode the next encrypt/decrypt continues after an unlock. */
    private var pendingMode: CompanionMode = CompanionMode.ENCRYPT

    /** The text the next encrypt/decrypt continues with after an unlock. */
    private var pendingText: String? = null

    /** v0.3.0: contact unlocked most recently (biometric remember target). */
    private var lastUnlockedContactId: ContactId? = null

    /**
     * v0.4.2 (deep-scan N2 guard): the "contactId:eraId" key the outstanding
     * fingerprint prompt unwraps. If the era rotates between showing the
     * prompt and the user touching the sensor, the wrapped bytes belong to
     * the OLD era and must never be activated for the new one.
     */
    private var pendingPromptKey: String? = null

    init {
        refresh()
        // v0.4.2 (deep-scan N1): lockAll(), the 60s expiry sweeper or an era
        // rotation in another surface used to leave this tab's `unlocked`
        // snapshot stale — the Encrypt button stayed up with no unlock card.
        // Re-read the live state on every session/era change instead.
        viewModelScope.launch {
            messageCipher.sessionChanges.collect { resyncUnlockState() }
        }
    }

    /**
     * v0.4.2 (deep-scan N1): drops contacts that are no longer unlocked
     * (locked elsewhere, expired, or their era rotated) so the UI re-locks
     * immediately instead of on the next action.
     */
    private fun resyncUnlockState() {
        launchSafely {
            val live = _state.value.contacts.map { it.id }
                .filter { messageCipher.isUnlocked(it) }
                .toSet()
            _state.value = _state.value.copy(unlocked = live)
            // An unlock card that is on screen may have become wrong (the
            // wrap it offers could be bound to a rotated era).
            if (_state.value.unlockNeeded) refreshBiometricOffer()
        }
    }

    /** Loads contacts and their unlock status. */
    fun refresh() {
        launchSafely {
            val contacts = contactsRepository.observeContacts().first()
            val unlocked = contacts.map { it.id }
                .filter { messageCipher.isUnlocked(it) }
                .toSet()
            val sticky = settings.lastContactId.first()
            val selected = _state.value.selectedContactId
                ?: contacts.firstOrNull { it.id.value == sticky }?.id
                ?: contacts.firstOrNull()?.id
            _state.value = _state.value.copy(
                contacts = contacts,
                unlocked = unlocked,
                selectedContactId = selected,
            )
        }
    }

    fun selectContact(id: ContactId) {
        _state.value = _state.value.copy(selectedContactId = id)
    }

    fun dismissNotice() {
        _state.value = _state.value.copy(notice = null)
    }

    /** Encrypt tab: encrypt [draft] for the selected contact. */
    fun encrypt(draft: String) {
        val s = _state.value
        val id = s.selectedContactId ?: run {
            _state.value = s.copy(notice = UiNoticeOf(R.string.notice_no_contact_selected))
            return
        }
        if (draft.isBlank()) {
            _state.value = s.copy(notice = UiNoticeOf(R.string.notice_empty_input))
            return
        }
        pendingMode = CompanionMode.ENCRYPT
        pendingText = draft
        launchSafely {
            _state.value = _state.value.copy(busy = true, notice = null)
            encryptInternal(id, draft)
        }
    }

    /** Decrypt tab: decrypt [envelope] (auto-resolves the contact). */
    fun decrypt(envelope: String) {
        if (envelope.isBlank()) {
            _state.value = _state.value.copy(notice = UiNoticeOf(R.string.notice_empty_input))
            return
        }
        pendingMode = CompanionMode.DECRYPT
        pendingText = envelope
        launchSafely {
            _state.value = _state.value.copy(busy = true, notice = null)
            decryptInternal(envelope)
        }
    }

    /** Unlock (passphrase) and continue the pending operation.
     *
     * v0.4.0: when the pending unlock is for a superseded era
     * ([ToolsUiState.pendingEraSessionId]), it goes through
     * [MessageCipher.unlockEra] instead — the contact is NOT marked unlocked
     * for sending, and no fingerprint remember offer is made.
     */
    fun unlockAndContinue(passphrase: CharArray) {
        val id = _state.value.pendingContactId ?: _state.value.selectedContactId ?: run {
            passphrase.wipe(); return
        }
        val text = pendingText ?: run {
            passphrase.wipe(); return
        }
        val eraSessionId = _state.value.pendingEraSessionId
        launchSafely {
            try {
                _state.value = _state.value.copy(busy = true, notice = null)
                val outcome = if (eraSessionId != null) {
                    messageCipher.unlockEra(id, eraSessionId, passphrase)
                } else {
                    messageCipher.unlockContact(id, passphrase)
                }
                when (outcome) {
                    UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> {
                        _state.value = _state.value.copy(
                            unlocked = if (eraSessionId != null) {
                                _state.value.unlocked
                            } else {
                                _state.value.unlocked + id
                            },
                            unlockNeeded = false,
                            pendingEraSessionId = null,
                        )
                        if (eraSessionId == null) {
                            lastUnlockedContactId = id
                            offerRemember(id)
                        }
                        when (pendingMode) {
                            CompanionMode.ENCRYPT -> encryptInternal(id, text)
                            CompanionMode.DECRYPT -> decryptInternal(text)
                        }
                    }
                    UnlockOutcome.WrongPassphrase -> _state.value = _state.value.copy(
                        busy = false,
                        notice = UiNoticeOf(R.string.notice_wrong_passphrase),
                    )
                    UnlockOutcome.NoSession -> _state.value = _state.value.copy(
                        busy = false,
                        notice = UiNoticeOf(R.string.notice_no_era),
                    )
                    is UnlockOutcome.Rejected -> _state.value = _state.value.copy(
                        busy = false,
                        notice = UiNoticeOf(R.string.notice_passphrase_rejected, outcome.reason),
                    )
                    is UnlockOutcome.Failed -> _state.value = _state.value.copy(
                        busy = false,
                        notice = UiNoticeOf(R.string.notice_unlock_failed, outcome.reason),
                    )
                }
            } finally {
                passphrase.wipe()
            }
        }
    }

    // ------------------------------------------------------------ biometric

    /** v0.3.0: recompute whether the fingerprint button should be offered. */
    fun refreshBiometricOffer() {
        launchSafely {
            val enabled = settings.settings.first().biometricUnlockEnabled
            if (!enabled || !biometricStore.isDeviceCapable()) {
                _state.value = _state.value.copy(biometricOffered = false)
                return@launchSafely
            }
            val contactKey = currentBiometricContactKey() ?: run {
                _state.value = _state.value.copy(biometricOffered = false)
                return@launchSafely
            }
            _state.value = _state.value.copy(
                biometricOffered = biometricStore.hasWrapped(contactKey),
            )
        }
    }

    /** v0.3.0: "Use fingerprint" — show the system prompt for a wrap. */
    fun requestBiometricUnlock() {
        val id = _state.value.pendingContactId ?: _state.value.selectedContactId ?: return
        launchSafely {
            val contactKey = biometricContactKeyFor(id) ?: return@launchSafely
            if (!biometricStore.hasWrapped(contactKey)) {
                // v0.4.2 (deep-scan N2): the offer was computed for an older
                // era — drop it instead of dead-tapping the button silently.
                _state.value = _state.value.copy(biometricOffered = false)
                return@launchSafely
            }
            pendingPromptKey = contactKey
            _state.value = _state.value.copy(
                biometricPrompt = BiometricPromptRequest.Unlock(contactKey),
            )
        }
    }

    /** v0.3.0: outcome of the system prompt (null = canceled / failed). */
    fun onBiometricUnlockResult(keyBytes: ByteArray?) {
        _state.value = _state.value.copy(biometricPrompt = null)
        if (keyBytes == null) {
            _state.value = _state.value.copy(
                notice = UiNoticeOf(R.string.notice_biometric_failed),
            )
            return
        }
        val id = _state.value.pendingContactId ?: _state.value.selectedContactId
        if (id == null) {
            keyBytes.fill(0)
            return
        }
        launchSafely {
            // v0.4.2 (deep-scan N2 guard): the era may have rotated between
            // showing the prompt and touching the sensor. A wrap of the OLD
            // era must never be activated as the NEW era's key — discard it
            // and fall back to the passphrase, honestly.
            val requestedKey = pendingPromptKey
            pendingPromptKey = null
            val currentKey = biometricContactKeyFor(id)
            if (requestedKey != null && currentKey != null && requestedKey != currentKey) {
                keyBytes.fill(0)
                biometricStore.clear(requestedKey)
                _state.value = _state.value.copy(
                    busy = false,
                    unlockNeeded = true,
                    biometricOffered = false,
                    notice = UiNoticeOf(R.string.notice_biometric_stale),
                )
                return@launchSafely
            }
            val contactKey = biometricContactKeyFor(id)
            when (messageCipher.unlockContactWithKey(id, keyBytes)) {
                UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> {
                    _state.value = _state.value.copy(
                        unlocked = _state.value.unlocked + id,
                        unlockNeeded = false,
                        notice = null,
                    )
                    val text = pendingText
                    when (pendingMode) {
                        CompanionMode.ENCRYPT ->
                            if (text != null) encryptInternal(id, text)
                        CompanionMode.DECRYPT ->
                            if (text != null) decryptInternal(text)
                    }
                }
                UnlockOutcome.NoSession -> _state.value = _state.value.copy(
                    notice = UiNoticeOf(R.string.notice_no_era),
                )
                else -> {
                    contactKey?.let { biometricStore.clear(it) }
                    _state.value = _state.value.copy(
                        notice = UiNoticeOf(R.string.notice_biometric_stale),
                        unlockNeeded = true,
                        busy = false,
                    )
                }
            }
        }
    }

    /** v0.3.0: offer to remember the just-unlocked key behind the prompt. */
    fun offerRemember(contactId: ContactId) {
        launchSafely {
            val enabled = settings.settings.first().biometricUnlockEnabled
            if (!enabled || !biometricStore.isDeviceCapable()) return@launchSafely
            val contactKey = biometricContactKeyFor(contactId) ?: return@launchSafely
            if (!biometricStore.hasWrapped(contactKey)) {
                _state.value = _state.value.copy(
                    biometricPrompt = BiometricPromptRequest.Remember(contactKey),
                )
            }
        }
    }

    /** v0.3.0: the key bytes the Remember prompt should wrap. */
    suspend fun consumeRememberKey(): ByteArray? {
        if (_state.value.biometricPrompt !is BiometricPromptRequest.Remember) return null
        val id = lastUnlockedContactId ?: return null
        return messageCipher.exportSessionKey(id)
    }

    /** v0.3.0: prompt finished — [success] says whether the wrap persisted. */
    fun onRememberDone(success: Boolean) {
        _state.value = _state.value.copy(
            biometricPrompt = null,
            biometricOffered = _state.value.biometricOffered || success,
        )
    }

    private suspend fun currentBiometricContactKey(): String? {
        val id = _state.value.pendingContactId ?: _state.value.selectedContactId
            ?: return null
        return biometricContactKeyFor(id)
    }

    /** `"contactId:eraId"` — the AAD and storage key for a remembered wrap. */
    private suspend fun biometricContactKeyFor(contactId: ContactId): String? {
        val eraId = messageCipher.currentEraId(contactId)?.value ?: return null
        return "${contactId.value}:$eraId"
    }

    // ------------------------------------------------------------ results

    /** Paste the current clipboard text into the input field. */
    fun pasteIntoInput(onPasted: (String) -> Unit) {
        launchSafely {
            val clip = clipboard.read()
            if (clip is org.cipherkey.security.clipboard.ClipboardRead.Text) {
                onPasted(clip.text)
            } else {
                _state.value = _state.value.copy(
                    notice = UiNoticeOf(R.string.notice_clipboard_empty),
                )
            }
        }
    }

    /** Copy the result to the clipboard (explicit user action). */
    fun copyResult() {
        val result = _state.value.resultText ?: return
        val sensitive = !_state.value.resultIsEnvelope
        val copied = if (sensitive) {
            clipboard.setSensitiveText(result)
        } else {
            clipboard.setText(result)
        }
        _state.value = if (copied) {
            _state.value.copy(copied = true)
        } else {
            _state.value.copy(notice = UiNoticeOf(R.string.notice_clipboard_access_failed))
        }
    }

    /** Clear the result block. */
    fun clearResult() {
        _state.value = _state.value.copy(
            resultText = null,
            resultContactName = null,
            resultIsEnvelope = false,
            copied = false,
            notice = null,
            unlockNeeded = false,
            pendingEraSessionId = null,
        )
    }

    // ------------------------------------------------------------ internals

    private suspend fun encryptInternal(contactId: ContactId, draft: String) {
        _state.value = _state.value.copy(busy = true, notice = null)
        when (val out = messageCipher.encryptDraft(contactId, draft)) {
            is EncryptOutcome.Encrypted -> {
                settings.setLastContactId(contactId.value)
                _state.value = _state.value.copy(
                    busy = false,
                    resultText = out.envelope,
                    resultContactName = _state.value.selectedContact?.displayName,
                    resultIsEnvelope = true,
                    copied = false,
                )
            }
            is EncryptOutcome.NeedsUnlock -> _state.value = _state.value.copy(
                busy = false,
                unlockNeeded = true,
                pendingContactId = contactId,
                unlockContactName = _state.value.selectedContact?.displayName,
            ).also { refreshBiometricOffer() }
            is EncryptOutcome.NoSession -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_no_era),
            )
            is EncryptOutcome.EmptyDraft -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_empty_input),
            )
            is EncryptOutcome.Failed -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_encrypt_failed, out.error.toString()),
            )
        }
    }

    private suspend fun decryptInternal(envelope: String) {
        _state.value = _state.value.copy(busy = true, notice = null)
        when (val out = messageCipher.decryptEnvelope(envelope)) {
            is DecryptOutcome.Decrypted -> _state.value = _state.value.copy(
                busy = false,
                resultText = out.plaintext,
                resultContactName = out.contactName,
                resultIsEnvelope = false,
                copied = false,
            )
            is DecryptOutcome.NeedsUnlock -> _state.value = _state.value.copy(
                busy = false,
                unlockNeeded = true,
                pendingContactId = out.contactId,
                unlockContactName = out.contactName,
                pendingEraSessionId = null,
            ).also { refreshBiometricOffer() }
            DecryptOutcome.NotAnEnvelope -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_not_envelope),
            )
            DecryptOutcome.UnknownKey -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_unknown_key),
            )
            is DecryptOutcome.NeedsEraUnlock -> _state.value = _state.value.copy(
                busy = false,
                unlockNeeded = true,
                pendingContactId = out.contactId,
                unlockContactName = out.contactName,
                pendingEraSessionId = out.eraSessionId,
                // A fingerprint wrap is bound to the contact's LATEST era —
                // it can never serve a superseded-era envelope.
                biometricOffered = false,
            )
            is DecryptOutcome.Failed -> _state.value = _state.value.copy(
                busy = false,
                notice = UiNoticeOf(R.string.notice_decrypt_failed),
            )
        }
    }

    private fun launchSafely(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    busy = false,
                    notice = UiNoticeOf(
                        R.string.notice_unexpected_error,
                        e.message ?: e.javaClass.simpleName,
                    ),
                )
            }
        }
    }

    override fun onCleared() {
        pendingText = null
        super.onCleared()
    }
}

/** Local alias so the state class above stays short. */
private fun UiNoticeOf(res: Int, arg: String? = null) =
    org.cipherkey.app.companion.UiNotice(res, arg)
