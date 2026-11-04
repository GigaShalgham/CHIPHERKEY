package org.cipherkey.app.companion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cipherkey.core.common.ContactId

/**
 * Compact utility sheet shared by all three companion entry activities.
 * Deliberately plain: one column, Material3, no navigation — every flow is
 * at most two steps (prepare/unlock → result).
 */
@Composable
fun CompanionSheet(
    state: CompanionUiState,
    onContactSelected: (ContactId) -> Unit,
    onEncrypt: () -> Unit,
    onUnlockEncrypt: (CharArray) -> Unit,
    onUnlockDecrypt: (CharArray) -> Unit,
    onCopy: () -> Unit,
    onInsert: () -> Unit,
    onClose: () -> Unit,
    onRetryClipboard: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        SheetHeader(state)

        if (state.busy) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        when (state.phase) {
            // Clipboard launches sit here for the brief moment between
            // activity creation and the first onWindowFocusChanged(true) —
            // the deferred read has not run yet (spec 004).
            CompanionUiState.Phase.Idle -> if (onRetryClipboard != null) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    CircularProgressIndicator()
                    Text("Reading the clipboard…", fontSize = 14.sp)
                }
            } else Unit

            CompanionUiState.Phase.Prepare ->
                if (state.mode == CompanionMode.ENCRYPT) {
                    EncryptPrepareCard(state, onContactSelected, onEncrypt, onUnlockEncrypt)
                } else {
                    // Decrypt transient state: work already in flight.
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        CircularProgressIndicator()
                        Text("Decrypting…", fontSize = 14.sp)
                    }
                }

            CompanionUiState.Phase.NeedsUnlock ->
                UnlockCard(state, onUnlockDecrypt)

            CompanionUiState.Phase.Result ->
                ResultCard(state, onCopy, onInsert, onClose)

            CompanionUiState.Phase.Notice ->
                NoticeCard(state, onClose, onRetryClipboard)
        }

        state.notice?.let { notice ->
            Text(
                text = notice,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.error,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun SheetHeader(state: CompanionUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = if (state.mode == CompanionMode.ENCRYPT) {
                "Encrypt"
            } else {
                "Decrypt"
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = when (state.source) {
                TextSource.SELECTION -> "From text selection"
                TextSource.SHARE -> "From share"
                TextSource.CLIPBOARD -> "From clipboard"
            } + "  ·  CipherKey",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun EncryptPrepareCard(
    state: CompanionUiState,
    onContactSelected: (ContactId) -> Unit,
    onEncrypt: () -> Unit,
    onUnlockEncrypt: (CharArray) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Draft", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(
            text = state.incoming ?: "",
            fontSize = 15.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .heightIn(max = 120.dp),
        )

        ContactDropdown(
            contacts = state.contacts,
            selectedContactId = state.selectedContactId,
            unlocked = state.unlocked,
            onContactSelected = onContactSelected,
        )

        if (state.selectedContactId != null && !state.selectedContactUnlocked) {
            PassphraseField(
                label = "Passphrase for ${state.selectedContact?.displayName ?: "contact"}",
                buttonLabel = "Unlock & Encrypt",
                busy = state.busy,
                onSubmit = onUnlockEncrypt,
            )
        } else {
            Button(
                onClick = onEncrypt,
                enabled = state.selectedContactId != null && !state.busy,
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Encrypt") }
        }

        if (state.selectedContactUnlocked) {
            Text(
                "Session unlocked — one tap.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactDropdown(
    contacts: List<org.cipherkey.data.repository.Contact>,
    selectedContactId: ContactId?,
    unlocked: Set<ContactId>,
    onContactSelected: (ContactId) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selected = contacts.firstOrNull { it.id == selectedContactId }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            value = selected?.displayName ?: "Choose contact",
            onValueChange = {},
            readOnly = true,
            label = { Text("Encrypt for") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            contacts.forEach { contact ->
                DropdownMenuItem(
                    text = {
                        Text(
                            (if (contact.id in unlocked) "🔓  " else "🔒  ") + contact.displayName,
                        )
                    },
                    onClick = {
                        onContactSelected(contact.id)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun UnlockCard(
    state: CompanionUiState,
    onUnlockDecrypt: (CharArray) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            "Message from ${state.pendingContactName ?: "contact"}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            "This contact's session is locked. Enter the shared passphrase to decrypt.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        PassphraseField(
            label = "Passphrase",
            buttonLabel = "Unlock & Decrypt",
            busy = state.busy,
            onSubmit = onUnlockDecrypt,
        )
    }
}

@Composable
private fun PassphraseField(
    label: String,
    buttonLabel: String,
    busy: Boolean,
    onSubmit: (CharArray) -> Unit,
) {
    var passphrase by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = passphrase,
            onValueChange = { passphrase = it },
            label = { Text(label) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = {
                // Convert to CharArray for the crypto path (which wipes it)
                // and clear the field immediately afterwards. The Compose
                // String itself cannot be zeroed — known, documented
                // limitation of the UI toolkit (same as ContactsScreen).
                onSubmit(passphrase.toCharArray())
                passphrase = ""
            },
            enabled = passphrase.isNotEmpty() && !busy,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(buttonLabel) }
    }
}

@Composable
private fun ResultCard(
    state: CompanionUiState,
    onCopy: () -> Unit,
    onInsert: () -> Unit,
    onClose: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = when (state.mode) {
                CompanionMode.ENCRYPT ->
                    "Encrypted for ${state.resultContactName ?: "contact"}"
                CompanionMode.DECRYPT ->
                    "Decrypted — from ${state.resultContactName ?: "contact"}"
            },
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
        )

        SelectionContainer {
            Text(
                text = state.resultText ?: "",
                fontSize = if (state.mode == CompanionMode.ENCRYPT) 12.sp else 15.sp,
                fontFamily = if (state.mode == CompanionMode.ENCRYPT) FontFamily.Monospace else FontFamily.Default,
                maxLines = if (state.mode == CompanionMode.ENCRYPT) 6 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 320.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(12.dp),
            )
        }

        if (state.mode == CompanionMode.ENCRYPT) {
            Text(
                "The full envelope is returned to the field — this preview is truncated.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        if (state.copied) {
            Text(
                "Copied to clipboard ✓",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        val canInsert = state.mode == CompanionMode.ENCRYPT &&
            state.source == TextSource.SELECTION && !state.readOnly

        if (canInsert) {
            Button(
                onClick = onInsert,
                enabled = !state.busy,
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Replace in field") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
                onClick = onCopy,
                modifier = Modifier.weight(1f),
            ) { Text("Copy") }
            OutlinedButton(
                onClick = onClose,
                modifier = Modifier.weight(1f),
            ) { Text("Done") }
        }
    }
}

@Composable
private fun NoticeCard(
    state: CompanionUiState,
    onClose: () -> Unit,
    onRetryClipboard: (() -> Unit)?,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.padding(vertical = 24.dp),
    ) {
        Text(
            text = state.notice ?: "Nothing to do.",
            fontSize = 15.sp,
        )
        if (state.retryable && onRetryClipboard != null) {
            Button(onClick = onRetryClipboard, modifier = Modifier.fillMaxWidth()) {
                Text("Try again")
            }
        }
        Button(onClick = onClose, modifier = Modifier.fillMaxWidth()) {
            Text("Close")
        }
    }
}
