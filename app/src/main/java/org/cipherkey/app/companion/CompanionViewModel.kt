package org.cipherkey.app.companion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.sessions.wipe
import org.cipherkey.data.preferences.SettingsRepository
import org.cipherkey.data.repository.Contact
import org.cipherkey.data.repository.ContactRepository
import org.cipherkey.data.repository.DecryptOutcome
import org.cipherkey.data.repository.EncryptOutcome
import org.cipherkey.data.repository.MessageCipher
import org.cipherkey.data.repository.UnlockOutcome
import org.cipherkey.security.clipboard.ClipboardAccess
import org.cipherkey.security.clipboard.ClipboardRead

/**
 * Mode of the companion flow currently hosted by an activity.
 */
enum class CompanionMode { ENCRYPT, DECRYPT }

/**
 * Where the text came from — drives result affordances and labels.
 */
enum class TextSource { SELECTION, SHARE, CLIPBOARD }

/**
 * State machine behind all three companion entry points (spec 003):
 * the PROCESS_TEXT encrypt activity, the PROCESS_TEXT decrypt activity,
 * the ACTION_SEND decrypt activity — plus the clipboard entry used from
 * the main app's Home screen.
 *
 * Everything cryptographic is delegated to the existing [MessageCipher]
 * facade; this class owns ONLY flow state and UX outcomes.
 */
data class CompanionUiState(
    val mode: CompanionMode = CompanionMode.ENCRYPT,
    val source: TextSource = TextSource.SELECTION,
    val phase: Phase = Phase.Idle,
    /** Sanitized input text (draft or envelope candidate). */
    val incoming: String? = null,
    /** True when the calling context was read-only (no insert offer). */
    val readOnly: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val unlocked: Set<ContactId> = emptySet(),
    val selectedContactId: ContactId? = null,
    /** Contact resolved from a decrypt outcome (name display + unlock). */
    val pendingContactId: ContactId? = null,
    val pendingContactName: String? = null,
    /** Final output: envelope (encrypt) or plaintext (decrypt). */
    val resultText: String? = null,
    val resultContactName: String? = null,
    val copied: Boolean = false,
    /** Human-readable notice for edge outcomes; null = none. */
    val notice: String? = null,
    /** True when the notice offers a clipboard "Try again" (spec 004). */
    val retryable: Boolean = false,
    val busy: Boolean = false,
) {
    enum class Phase { Idle, Prepare, NeedsUnlock, Result, Notice }

    val selectedContact: Contact?
        get() = contacts.firstOrNull { it.id == selectedContactId }

    val selectedContactUnlocked: Boolean
        get() = selectedContactId in unlocked
}

@HiltViewModel
class CompanionViewModel @Inject constructor(
    private val messageCipher: MessageCipher,
    private val contactsRepository: ContactRepository,
    private val settings: SettingsRepository,
    private val clipboard: ClipboardAccess,
) : ViewModel() {

    private val _state = MutableStateFlow(CompanionUiState())
    val state = _state.asStateFlow()

    /** True once a flow has begun (idempotence across activity recreation). */
    private val begun = MutableStateFlow(false)

    // ------------------------------------------------------------ entry

    /**
     * Encrypt flow: show prepare screen (sticky contact preselected,
     * passphrase only if the session is locked).
     *
     * [readOnly] is true when the selection came from a non-editable view
     * (EXTRA_PROCESS_TEXT_READONLY): the sheet then hides "Replace in
     * field" because the calling app would ignore the result.
     */
    fun beginEncrypt(text: String, source: TextSource, readOnly: Boolean = false) {
        if (!begun.compareAndSet(false, true)) return
        startEncryptFlow(text, source, readOnly)
    }

    /** Decrypt flow: auto-resolve the contact from the envelope's keyId. */
    fun beginDecrypt(text: String, source: TextSource, readOnly: Boolean) {
        if (!begun.compareAndSet(false, true)) return
        startDecryptFlow(text, source, readOnly)
    }

    fun selectContact(id: ContactId) {
        _state.value = _state.value.copy(
            selectedContactId = id,
            notice = null,
        )
    }

    fun dismissNotice() {
        _state.value = _state.value.copy(notice = null)
    }

    /** Dead-end notice for unusable input (activity-level hardening). */
    fun showNotice(mode: CompanionMode, notice: String, retryable: Boolean = false) {
        if (!begun.compareAndSet(false, true)) return
        _state.value = CompanionUiState(
            mode = mode,
            source = _state.value.source,
            phase = CompanionUiState.Phase.Notice,
            notice = notice,
            retryable = retryable,
        )
    }

    // ------------------------------------------------------------ actions

    /** Encrypt with the selected contact (session already unlocked). */
    fun encryptNow() {
        val s = _state.value
        val id = s.selectedContactId ?: return
        val draft = s.incoming ?: return
        launchSafely {
            _state.value = _state.value.copy(busy = true, notice = null)
            when (val out = messageCipher.encryptDraft(id, draft)) {
                is EncryptOutcome.Encrypted -> {
                    settings.setLastContactId(id.value)
                    _state.value = _state.value.copy(
                        phase = CompanionUiState.Phase.Result,
                        resultText = out.envelope,
                        resultContactName = s.selectedContact?.displayName,
                        busy = false,
                        copied = false,
                    )
                }
                is EncryptOutcome.NeedsUnlock -> _state.value = _state.value.copy(
                    busy = false,
                    phase = CompanionUiState.Phase.NeedsUnlock,
                    pendingContactId = id,
                    pendingContactName = s.selectedContact?.displayName,
                )
                is EncryptOutcome.NoSession -> _state.value = _state.value.copy(
                    busy = false,
                    notice = "This contact has no key era yet. Open CipherKey, open the contact, and create or import a key first.",
                )
                is EncryptOutcome.EmptyDraft -> _state.value = _state.value.copy(
                    busy = false,
                    notice = "Nothing to encrypt.",
                )
                is EncryptOutcome.Failed -> _state.value = _state.value.copy(
                    busy = false,
                    notice = "Encryption failed: ${out.error}",
                )
            }
        }
    }
    /** Unlock (if needed) and encrypt in one step, from the prepare card. */
    fun unlockAndEncrypt(passphrase: CharArray) {
        val id = _state.value.selectedContactId ?: run {
            passphrase.wipe(); return
        }
        unlockThen(id, passphrase) { encryptNow() }
    }

    /** Unlock the contact resolved from a decrypt NeedsUnlock outcome. */
    fun unlockAndDecrypt(passphrase: CharArray) {
        val id = _state.value.pendingContactId ?: run {
            passphrase.wipe(); return
        }
        val envelope = _state.value.incoming ?: run {
            passphrase.wipe(); return
        }
        unlockThen(id, passphrase) { decryptInternal(envelope) }
    }

    /** Copy the current result to the clipboard (explicit user action). */
    fun copyResult() {
        val result = _state.value.resultText ?: return
        val sensitive = _state.value.mode == CompanionMode.DECRYPT
        // Decrypted plaintext is marked sensitive (deep-scan finding 17):
        // Android 13+ then hides the clipboard preview. Envelopes are not
        // secret by definition.
        val copied = if (sensitive) clipboard.setSensitiveText(result) else clipboard.setText(result)
        _state.value = if (copied) {
            _state.value.copy(copied = true)
        } else {
            _state.value.copy(notice = "Could not access the clipboard.")
        }
    }

    /**
     * Clipboard entry point (spec 004): reads the current clip through the
     * ClipboardAccess chokepoint and starts the flow for [mode].
     *
     * MUST be called only while the hosting activity's window has focus
     * (Android 10+ clipboard focus contract — see BaseCompanionActivity).
     * The distinct [ClipboardRead] outcomes map to distinct user-facing
     * notices; only genuinely empty/blank clips claim emptiness.
     */
    fun beginFromClipboard(mode: CompanionMode) {
        if (!begun.compareAndSet(false, true)) return
        // Set the mode BEFORE the first clipboard read: every early-exit
        // notice below must be headed "Decrypt" on decrypt launches, not
        // with the default "Encrypt" (deep-scan finding 4).
        _state.value = _state.value.copy(mode = mode)
        when (val clip = clipboard.read()) {
            is ClipboardRead.Text -> when (
                val parsed = if (mode == CompanionMode.ENCRYPT) {
                    ProcessTextContract.parsePlaintext(clip.text)
                } else {
                    ProcessTextContract.parseEnvelope(clip.text)
                }
            ) {
                is ProcessTextContract.SelectedText.Valid ->
                    if (mode == CompanionMode.ENCRYPT) {
                        startEncryptFlow(parsed.text, TextSource.CLIPBOARD, readOnly = true)
                    } else {
                        startDecryptFlow(parsed.text, TextSource.CLIPBOARD, readOnly = true)
                    }
                ProcessTextContract.SelectedText.Empty -> clipboardNotice(
                    "The clipboard text is empty (blank). Copy the text, then tap Try again.",
                )
                is ProcessTextContract.SelectedText.Oversized -> clipboardNotice(
                    "The clipboard text is longer than ${parsed.limitBytes / 1024} KiB and cannot be processed as one message.",
                    retryable = false,
                )
            }
            ClipboardRead.Empty -> clipboardNotice(
                "The clipboard is empty. Copy the text first, then tap Try again.",
            )
            is ClipboardRead.NonText -> clipboardNotice(
                "The clipboard contains non-text content (${clip.mimeType ?: "unknown type"}). " +
                    "CipherKey can only process text — copy text, then tap Try again.",
            )
            is ClipboardRead.Blocked -> clipboardNotice(
                "Android blocked clipboard access" +
                    (clip.reason?.let { " ($it)" } ?: "") +
                    ". Tap Try again while CipherKey is in the foreground.",
            )
        }
    }

    /**
     * "Try again" from a clipboard notice: resets the flow and re-reads.
     * The retry affordance is only rendered for retryable notices, and only
     * wired for clipboard launches.
     */
    fun retryClipboard() {
        val current = _state.value
        if (current.phase != CompanionUiState.Phase.Notice || !current.retryable) return
        val mode = current.mode
        begun.value = false
        _state.value = CompanionUiState(mode = mode)
        beginFromClipboard(mode)
    }

    // ------------------------------------------------------------ internals

    /** Unguarded encrypt flow (the public entry owns the begun flag). */
    private fun startEncryptFlow(text: String, source: TextSource, readOnly: Boolean) {
        launchSafely {
            val contacts = contactsRepository.observeContacts().first()
            val unlocked = contacts.map { it.id }
                .filter { messageCipher.isUnlocked(it) }
                .toSet()
            // Sticky contact, clamped to an existing contact (FR-005).
            val sticky = settings.lastContactId.first()
            val selected = contacts.firstOrNull { it.id.value == sticky }?.id
            _state.value = CompanionUiState(
                mode = CompanionMode.ENCRYPT,
                source = source,
                readOnly = readOnly,
                phase = if (contacts.isEmpty()) {
                    CompanionUiState.Phase.Notice
                } else {
                    CompanionUiState.Phase.Prepare
                },
                incoming = text,
                contacts = contacts,
                unlocked = unlocked,
                selectedContactId = selected,
                notice = if (contacts.isEmpty()) {
                    "No contacts yet. Open CipherKey and create a contact with a shared passphrase first."
                } else null,
            )
        }
    }

    /** Unguarded decrypt flow (the public entry owns the begun flag). */
    private fun startDecryptFlow(text: String, source: TextSource, readOnly: Boolean) {
        _state.value = CompanionUiState(
            mode = CompanionMode.DECRYPT,
            source = source,
            phase = CompanionUiState.Phase.Prepare,
            incoming = text,
            readOnly = readOnly,
            busy = true,
        )
        launchSafely { decryptInternal(text) }
    }

    /** Clipboard-outcome notice without re-checking the begun flag. */
    private fun clipboardNotice(message: String, retryable: Boolean = true) {
        _state.value = CompanionUiState(
            mode = _state.value.mode,
            source = TextSource.CLIPBOARD,
            phase = CompanionUiState.Phase.Notice,
            notice = message,
            retryable = retryable,
        )
    }

    private suspend fun decryptInternal(envelope: String) {
        _state.value = _state.value.copy(busy = true, notice = null)
        when (val out = messageCipher.decryptEnvelope(envelope)) {
            is DecryptOutcome.Decrypted -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.Result,
                resultText = out.plaintext,
                resultContactName = out.contactName,
                busy = false,
                copied = false,
            )
            is DecryptOutcome.NeedsUnlock -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.NeedsUnlock,
                pendingContactId = out.contactId,
                pendingContactName = out.contactName,
                busy = false,
            )
            DecryptOutcome.NotAnEnvelope -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.Notice,
                busy = false,
                notice = "This text is not a CK1 encrypted message. Select the whole \"CK1:…\" message and try again.",
            )
            DecryptOutcome.UnknownKey -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.Notice,
                busy = false,
                notice = "The key for this message is not on this device. Import the contact's invite code first.",
            )
            DecryptOutcome.StaleKeyEra -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.Notice,
                busy = false,
                notice = "This message was encrypted under an older key era that this version cannot decrypt (v0.2 limitation).",
            )
            is DecryptOutcome.Failed -> _state.value = _state.value.copy(
                phase = CompanionUiState.Phase.Notice,
                busy = false,
                notice = "Decryption failed — the message is corrupted or was tampered with.",
            )
        }
    }

    private fun unlockThen(
        contactId: ContactId,
        passphrase: CharArray,
        andThen: suspend () -> Unit,
    ) {
        launchSafely {
            try {
                _state.value = _state.value.copy(busy = true, notice = null)
                val outcome = messageCipher.unlockContact(contactId, passphrase)
                when (outcome) {
                    UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> {
                        _state.value = _state.value.copy(
                            unlocked = _state.value.unlocked + contactId,
                        )
                        andThen()
                    }
                    UnlockOutcome.WrongPassphrase -> _state.value = _state.value.copy(
                        busy = false,
                        notice = "Wrong passphrase.",
                    )
                    UnlockOutcome.NoSession -> _state.value = _state.value.copy(
                        busy = false,
                        notice = "This contact has no key era yet. Create or import one in the app first.",
                    )
                    is UnlockOutcome.Rejected -> _state.value = _state.value.copy(
                        busy = false,
                        notice = "Passphrase rejected: ${outcome.reason}",
                    )
                    is UnlockOutcome.Failed -> _state.value = _state.value.copy(
                        busy = false,
                        notice = "Unlock failed: ${outcome.reason}",
                    )
                }
            } finally {
                // The passphrase is wiped on EVERY path — including a thrown
                // exception (deep-scan finding 12: an unexpected error used
                // to crash the process and leak the buffer).
                passphrase.wipe()
            }
        }
    }

    /**
     * viewModelScope.launch + never-crash wrapper (deep-scan finding 12):
     * every repository/crypto call in the companion flows funnels through
     * here, so an unexpected exception surfaces as a notice with busy
     * cleared instead of killing the process with the sheet frozen.
     */
    private fun launchSafely(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    busy = false,
                    notice = "Unexpected error: ${e.message ?: e.javaClass.simpleName}",
                )
            }
        }
    }
}
