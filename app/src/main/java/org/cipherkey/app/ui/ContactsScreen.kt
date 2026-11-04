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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.core.common.ContactId
import org.cipherkey.core.sessions.wipe
import org.cipherkey.data.repository.Contact
import org.cipherkey.data.repository.UnlockOutcome
import org.cipherkey.security.policies.Argon2Profile

@Composable
fun ContactsScreen(viewModel: ContactsViewModel) {
    val contacts by viewModel.contacts.collectAsStateWithLifecycle()
    val unlocked by viewModel.unlocked.collectAsStateWithLifecycle()
    val eras by viewModel.eras.collectAsStateWithLifecycle()
    val feedback by viewModel.feedback.collectAsStateWithLifecycle()
    val fingerprints by viewModel.fingerprints.collectAsStateWithLifecycle()

    var showAddDialog by remember { mutableStateOf(false) }
    var detailContact by remember { mutableStateOf<Contact?>(null) }

    androidx.compose.runtime.LaunchedEffect(contacts) { viewModel.refreshAll() }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { showAddDialog = true }) {
                Text("+ Add contact")
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
                text = "Contacts",
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
                    text = "No contacts yet.\n\nCreate one, agree on a passphrase in person, " +
                        "and share the invite code (CKS1:...) with them.",
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
        )
    }

    detailContact?.let { contact ->
        ContactDetailDialog(
            contact = contact,
            eras = eras[contact.id].orEmpty(),
            unlocked = contact.id in unlocked,
            fingerprint = fingerprints[contact.id],
            onUnlock = { passphrase, onResult ->
                viewModel.unlockContact(contact.id, passphrase, onResult)
            },
            onLock = { viewModel.lockContact(contact.id) },
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
            containerColor = if (unlocked) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
            else MaterialTheme.colorScheme.surfaceVariant,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(contact.displayName, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text(
                    text = if (eraCount == 0) "no key era" else "$eraCount key era(s)",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(if (unlocked) "🔓 unlocked" else "🔒 locked", fontSize = 13.sp)
        }
    }
}

@Composable
private fun AddContactDialog(
    onDismiss: () -> Unit,
    onCreate: (String, String?, Argon2Profile, (Boolean) -> Unit) -> Unit,
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
        title = { Text("New contact") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                )
                Text(
                    "Leave the invite field EMPTY to create a new key (you share the code). " +
                        "Paste the OTHER side's CKS1:... code to import their key.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                OutlinedTextField(
                    value = invite,
                    onValueChange = {
                        invite = it
                        error = null
                    },
                    label = { Text("Invite code (optional)") },
                    singleLine = true,
                    isError = error != null,
                )
                error?.let { message ->
                    Text(
                        text = message,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                Text("Argon2id strength", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Argon2Profile.entries.forEach { candidate ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = profile == candidate,
                            onCheckedChange = { if (it) profile = candidate },
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
                        if (!ok) error = "Creation failed — check the invite code and try again."
                    }
                },
            ) { Text("Create") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, enabled = !busy) { Text("Cancel") }
        },
    )
}

@Composable
private fun ContactDetailDialog(
    contact: Contact,
    eras: List<org.cipherkey.data.repository.ContactSession>,
    unlocked: Boolean,
    fingerprint: String?,
    onUnlock: (CharArray, (UnlockOutcome) -> Unit) -> Unit,
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // Unlock / lock section
                if (!unlocked) {
                    Text("Enter the shared passphrase to unlock this contact's session key.", fontSize = 13.sp)
                    OutlinedTextField(
                        value = passphrase,
                        onValueChange = { passphrase = it },
                        label = { Text("Shared passphrase") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
                    if (busy) LinearProgressIndicator(Modifier.fillMaxWidth())
                    unlockMessage?.let { Text(it, fontSize = 13.sp, color = MaterialTheme.colorScheme.primary) }
                    TextButton(
                        enabled = passphrase.isNotEmpty() && !busy,
                        onClick = {
                            busy = true
                            val chars = passphrase.toCharArray()
                            passphrase = ""
                            onUnlock(chars) { outcome ->
                                busy = false
                                unlockMessage = when (outcome) {
                                    UnlockOutcome.Unlocked, UnlockOutcome.AlreadyUnlocked -> "Unlocked ✓"
                                    UnlockOutcome.WrongPassphrase -> "Wrong passphrase"
                                    UnlockOutcome.NoSession -> "No key era — recreate the contact"
                                    is UnlockOutcome.Rejected -> outcome.reason
                                    is UnlockOutcome.Failed -> outcome.reason
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text("Unlock session") }
                } else {
                    Text("🔓 Session unlocked", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
                    fingerprint?.let {
                        Text("Key fingerprint: $it", fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(
                            "Compare with the peer's fingerprint to confirm you derived the same key.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    TextButton(onClick = onLock) { Text("Lock now") }
                }

                // Invite section
                if (latestEra != null) {
                    Text(
                        "Invite code (share over any channel, passphrase stays verbal):",
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
                                "Copied ✓"
                            } else {
                                "Copy failed — clipboard unavailable"
                            }
                        }) { Text("Copy invite") }
                        TextButton(onClick = {
                            val send = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, latestEra.inviteCode)
                            }
                            context.startActivity(Intent.createChooser(send, "Share invite"))
                        }) { Text("Share") }
                    }
                    inviteCopyMessage?.let { message ->
                        Text(
                            message,
                            fontSize = 12.sp,
                            color = if (message.startsWith("Copied")) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.error
                            },
                        )
                    }
                } else {
                    Text("No key era yet.", fontSize = 13.sp)
                }

                if (eras.size > 1) {
                    Text("${eras.size - 1} older key era(s) kept for history", fontSize = 11.sp)
                }

                if (confirmDelete) {
                    Text("Delete this contact and all its key data?", fontSize = 13.sp, color = MaterialTheme.colorScheme.error)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = onDelete) { Text("Yes, delete") }
                        TextButton(onClick = { confirmDelete = false }) { Text("Keep") }
                    }
                } else {
                    TextButton(onClick = { confirmDelete = true }) {
                        Text("Delete contact", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        },
    )
}
