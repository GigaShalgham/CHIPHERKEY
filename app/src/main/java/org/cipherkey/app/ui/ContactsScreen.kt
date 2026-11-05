package org.cipherkey.app.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.app.R
import org.cipherkey.app.companion.UiNotice
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.security.BiometricPromptHost
import org.cipherkey.app.security.rememberBiometricPromptHelper
import org.cipherkey.data.repository.Contact
import org.cipherkey.data.repository.UnlockOutcome
import org.cipherkey.security.policies.Argon2Profile
import org.cipherkey.security.policies.PassphraseAdvisor

/**
 * Contacts (v0.4.1): the list, the new-contact dialog and the detail dialog.
 *
 * The v0.4.0 auto-shown "Recommended passphrase" card is GONE (user
 * feedback): a generated passphrase on each phone means the two sides can
 * never decrypt each other's messages, and an auto-shown monospace string
 * read as a system-assigned password. What stays: one rule line ("both
 * people must enter the SAME passphrase") and an OPTIONAL suggestion behind
 * an explicit button.
 *
 * The detail dialog now also offers "Use fingerprint" (v0.4.1) — the most
 * natural place a user looks for it; until now the affordance only existed
 * on the Encrypt/Decrypt tabs.
 */
@Composable
fun ContactsScreen(viewModel: ContactsViewModel, biometrics: BiometricKeyStore) {
    val contacts by viewModel.contacts.collectAsStateWithLifecycle()
    val unlocked by viewModel.unlocked.collectAsStateWithLifecycle()
    val eras by viewModel.eras.collectAsStateWithLifecycle()
    val feedback by viewModel.feedback.collectAsStateWithLifecycle()
    val fingerprints by viewModel.fingerprints.collectAsStateWithLifecycle()
    val biometricOffered by viewModel.biometricOffered.collectAsStateWithLifecycle()
    val biometricPrompt by viewModel.biometricPrompt.collectAsStateWithLifecycle()
    val dialogNotice by viewModel.dialogNotice.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(contacts) { viewModel.refreshAll() }

    // v0.4.1: the system fingerprint prompt lives at screen level (it needs
    // the FragmentActivity); the dialogs only request it.
    val promptHelper = rememberBiometricPromptHelper(biometrics)
    BiometricPromptHost(
        helper = promptHelper,
        request = biometricPrompt,
        loadRememberKey = viewModel::consumeRememberKey,
        onUnlock = viewModel::onBiometricUnlockResult,
        onRememberDone = viewModel::onRememberDone,
    )

    var showAddDialog by remember { mutableStateOf(false) }
    var detailContact by remember { mutableStateOf<Contact?>(null) }

    Scaffold(
        floatingActionButton = {
            TextButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
            ) {
                Text(
                    stringResource(R.string.contacts_add),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.contacts_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 12.dp),
            )
            feedback?.let { message ->
                Text(
                    text = message,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
            if (contacts.isEmpty()) {
                Text(
                    text = stringResource(R.string.contacts_empty_title),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 24.dp),
                )
                Text(
                    text = stringResource(R.string.contacts_empty_body),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(contacts, key = { it.id.value }) { contact ->
                        ContactCard(
                            contact = contact,
                            unlocked = contact.id in unlocked,
                            eraCount = eras[contact.id]?.size ?: 0,
                            onClick = {
                                viewModel.loadEras(contact.id)
                                viewModel.openDetail(contact.id)
                                detailContact = contact
                            },
                        )
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }

    if (showAddDialog) {
        AddContactDialog(
            onDismiss = { showAddDialog = false },
            onCreate = { name, invite, profile, onDone ->
                viewModel.createContact(name, invite, profile) { ok, _ ->
                    onDone(ok)
                    if (ok) showAddDialog = false
                }
            },
            onCopyPassphrase = { text -> viewModel.copyText(text) },
        )
    }

    detailContact?.let { contact ->
        ContactDetailDialog(
            contact = contact,
            eras = eras[contact.id].orEmpty(),
            unlocked = unlocked.contains(contact.id),
            fingerprint = fingerprints[contact.id],
            offered = biometricOffered,
            notice = dialogNotice,
            onUnlock = { passphrase, onResult ->
                viewModel.unlockContact(contact.id, passphrase, onResult)
            },
            onPassphraseUnlocked = { viewModel.offerRemember(contact.id) },
            onUseFingerprint = viewModel::requestBiometricUnlock,
            onLock = {
                viewModel.lockContact(contact.id)
                viewModel.refreshBiometricOffer(contact.id)
            },
            onDelete = {
                viewModel.deleteContact(contact.id)
                detailContact = null
            },
            onCopyInvite = { text -> viewModel.copyText(text) },
            onDismiss = { detailContact = null },
        )
    }
}

@Composable
private fun ContactCard(
    contact: Contact,
    unlocked: Boolean,
    eraCount: Int,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (unlocked) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            },
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(contact.displayName, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = if (eraCount == 0) {
                        stringResource(R.string.contact_era_count_none)
                    } else {
                        stringResource(R.string.contact_era_count, eraCount)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(
                text = stringResource(
                    if (unlocked) R.string.contact_state_unlocked
                    else R.string.contact_state_locked,
                ),
                style = MaterialTheme.typography.labelMedium,
                color = if (unlocked) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        }
    }
}

@Composable
private fun AddContactDialog(
    onDismiss: () -> Unit,
    onCreate: (String, String?, Argon2Profile, (Boolean) -> Unit) -> Unit,
    onCopyPassphrase: (String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var invite by remember { mutableStateOf("") }
    var profile by remember { mutableStateOf(Argon2Profile.DEFAULT) }
    var busy by remember { mutableStateOf(false) }
    // Deep-scan finding 6: failures are shown INSIDE the dialog — the
    // home-screen feedback column is behind the scrim and was invisible at
    // the moment the error happened.
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = { if (!busy) onDismiss() },
        title = { Text(stringResource(R.string.contact_new_title)) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.contact_name_label)) },
                    singleLine = true,
                )
                OutlinedTextField(
                    value = invite,
                    onValueChange = {
                        invite = it
                        error = null
                    },
                    label = { Text(stringResource(R.string.contact_invite_label)) },
                    singleLine = true,
                    isError = error != null,
                )
                Text(
                    stringResource(R.string.contact_invite_help),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                error?.let { message ->
                    Text(
                        text = message,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                if (invite.isBlank()) {
                    // v0.4.1: the ONE rule that must never be lost — a
                    // different passphrase on each phone means they can never
                    // decrypt each other's messages.
                    Text(
                        stringResource(R.string.contact_shared_passphrase_rule),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    OptionalPassphraseSuggestion(onCopy = onCopyPassphrase)
                }
                Text(
                    stringResource(R.string.contact_argon2_title),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
                Argon2Profile.entries.forEach { candidate ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = profile == candidate,
                            onClick = { profile = candidate },
                        )
                        Text(
                            "${candidate.name.lowercase().replaceFirstChar { it.uppercase() }} — " +
                                "${candidate.params.memoryKib / 1024} MiB / ${candidate.params.iterations} passes",
                            fontSize = 13.sp,
                        )
                    }
                }
                if (busy) LinearProgressIndicator(Modifier.fillMaxWidth())
            }
        },
        confirmButton = {
            TextButton(
                enabled = name.isNotBlank() && !busy,
                onClick = {
                    busy = true
                    error = null
                    onCreate(name, invite.ifBlank { null }, profile) { ok ->
                        busy = false
                        if (!ok) error = stringResource(R.string.contact_creation_failed)
                    }
                },
            ) { Text(stringResource(R.string.contact_create)) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !busy) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}

/**
 * v0.4.1: the passphrase suggestion is OPTIONAL and collapsed by default —
 * it never auto-shows (v0.4.0 did, which read as a compulsory,
 * system-assigned password and, taken at face value on two phones, would
 * give each side a DIFFERENT passphrase).
 */
@Composable
private fun OptionalPassphraseSuggestion(onCopy: (String) -> Unit) {
    var visible by remember { mutableStateOf(false) }
    if (!visible) {
        TextButton(onClick = { visible = true }) {
            Text(stringResource(R.string.contact_suggest_passphrase))
        }
        return
    }
    var suggestion by remember { mutableStateOf(PassphraseAdvisor.recommend()) }
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            suggestion,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(
            stringResource(R.string.contact_suggestion_bits, PassphraseAdvisor.GUARANTEED_BITS),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = { suggestion = PassphraseAdvisor.recommend() }) {
                Text(stringResource(R.string.contact_suggestion_new))
            }
            TextButton(onClick = { onCopy(suggestion) }) {
                Text(stringResource(R.string.action_copy))
            }
            TextButton(onClick = { visible = false }) {
                Text(stringResource(R.string.contact_suggest_hide))
            }
        }
        Text(
            stringResource(R.string.contact_suggestion_warning),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ContactDetailDialog(
    contact: Contact,
    eras: List<org.cipherkey.data.repository.ContactSession>,
    unlocked: Boolean,
    fingerprint: String?,
    offered: Boolean,
    notice: UiNotice?,
    onUnlock: (CharArray, (UnlockOutcome) -> Unit) -> Unit,
    onPassphraseUnlocked: () -> Unit,
    onUseFingerprint: () -> Unit,
    onLock: () -> Unit,
    onDelete: () -> Unit,
    onCopyInvite: (String) -> Boolean,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current
    var passphrase by remember { mutableStateOf("") }
    var unlockMessage by remember { mutableStateOf<String?>(null) }
    var busy by remember { mutableStateOf(false) }
    var confirmDelete by remember { mutableStateOf(false) }
    // Deep-scan finding 14: copy feedback lives where the button is.
    var inviteCopyMessage by remember { mutableStateOf<String?>(null) }

    val latestEra = eras.firstOrNull()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(contact.displayName) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // Unlock / lock section
                if (!unlocked) {
                    Text(
                        stringResource(R.string.contact_unlock_hint),
                        fontSize = 13.sp,
                    )
                    OutlinedTextField(
                        value = passphrase,
                        onValueChange = { passphrase = it },
                        label = { Text(stringResource(R.string.passphrase_label)) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
                    if (busy) LinearProgressIndicator(Modifier.fillMaxWidth())
                    unlockMessage?.let { Text(it, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary) }
                    notice?.let { n ->
                        Text(
                            n.arg?.let { stringResource(n.res, it) } ?: stringResource(n.res),
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                    // v0.4.1: fingerprint unlock offered HERE, not only on
                    // the tools tabs.
                    if (offered) {
                        OutlinedButton(
                            onClick = onUseFingerprint,
                            enabled = !busy,
                            modifier = Modifier.fillMaxWidth(),
                        ) { Text(stringResource(R.string.use_fingerprint)) }
                    }
                    TextButton(
                        enabled = passphrase.isNotEmpty() && !busy,
                        onClick = {
                            busy = true
                            val chars = passphrase.toCharArray()
                            passphrase = ""
                            onUnlock(chars) { outcome ->
                                busy = false
                                when (outcome) {
                                    UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> {
                                        unlockMessage = stringResource(R.string.contact_session_unlocked)
                                        // v0.4.1: this is the moment the
                                        // "Remember this key?" prompt can fire.
                                        onPassphraseUnlocked()
                                    }
                                    UnlockOutcome.WrongPassphrase ->
                                        unlockMessage = stringResource(R.string.notice_wrong_passphrase)
                                    UnlockOutcome.NoSession ->
                                        unlockMessage = stringResource(R.string.contact_no_era)
                                    is UnlockOutcome.Rejected -> unlockMessage = outcome.reason
                                    is UnlockOutcome.Failed -> unlockMessage = outcome.reason
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text(stringResource(R.string.contact_unlock_action)) }
                } else {
                    Text(
                        stringResource(R.string.contact_session_unlocked),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    fingerprint?.let {
                        Text(
                            stringResource(R.string.contact_key_fingerprint, it),
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    TextButton(onClick = onLock) { Text(stringResource(R.string.lock_now_short)) }
                }

                // Invite section
                if (latestEra != null) {
                    Text(
                        stringResource(R.string.contact_invite_section),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    SelectionContainer {
                        Text(
                            latestEra.inviteCode,
                            fontSize = 11.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = {
                            // Deep-scan finding 14: through the ClipboardAccess
                            // chokepoint (not a raw ClipboardManager), with
                            // honest success/failure feedback next to it.
                            inviteCopyMessage = if (onCopyInvite(latestEra.inviteCode)) {
                                stringResource(R.string.copied_ok)
                            } else {
                                stringResource(R.string.copy_failed)
                            }
                        }) { Text(stringResource(R.string.contact_copy_invite)) }
                        TextButton(onClick = {
                            val send = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, latestEra.inviteCode)
                            }
                            context.startActivity(
                                Intent.createChooser(
                                    send,
                                    context.getString(R.string.contact_share_invite),
                                ),
                            )
                        }) { Text(stringResource(R.string.action_share)) }
                    }
                    inviteCopyMessage?.let { message ->
                        Text(
                            message,
                            fontSize = 12.sp,
                            color = if (message == stringResource(R.string.copied_ok)) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.error
                            },
                        )
                    }
                } else {
                    Text(stringResource(R.string.contact_no_era), fontSize = 13.sp)
                }

                if (eras.size > 1) {
                    Text(
                        stringResource(R.string.contact_older_eras, eras.size - 1),
                        fontSize = 11.sp,
                    )
                }

                if (confirmDelete) {
                    Text(
                        stringResource(R.string.contact_delete_confirm),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.error,
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = onDelete) {
                            Text(stringResource(R.string.contact_delete_yes))
                        }
                        TextButton(onClick = { confirmDelete = false }) {
                            Text(stringResource(R.string.contact_delete_keep))
                        }
                    }
                } else {
                    TextButton(onClick = { confirmDelete = true }) {
                        Text(
                            stringResource(R.string.contact_delete),
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.close)) }
        },
    )
}
