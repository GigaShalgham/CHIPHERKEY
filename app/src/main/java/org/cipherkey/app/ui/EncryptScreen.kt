package org.cipherkey.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.R
import org.cipherkey.app.ToolsViewModel
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.security.BiometricPromptHost
import org.cipherkey.app.security.BiometricPromptRequest
import org.cipherkey.app.security.rememberBiometricPromptHelper

/**
 * In-app Encrypt tab (v0.3.0): type or paste a message, pick a contact,
 * encrypt — for apps where neither the selection toolbar nor the sharesheet
 * reaches the text. All crypto runs through the shared ToolsViewModel.
 */
@Composable
fun EncryptScreen(
    viewModel: ToolsViewModel,
    biometrics: BiometricKeyStore,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val promptHelper = rememberBiometricPromptHelper(biometrics)
    // v0.4.2 (deep-scan): rememberSaveable — a rotation used to wipe the
    // typed draft. (Passphrase fields are deliberately NOT saved: view-state
    // must never hold a passphrase longer than the interaction itself.)
    var input by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) { viewModel.refresh() }
    // The Remember prompt's key export is awaited INSIDE the host's effect
    // (deep-scan F1) — never pre-loaded into composition state.
    BiometricPromptHost(
        helper = promptHelper,
        request = state.biometricPrompt,
        loadRememberKey = viewModel::consumeRememberKey,
        onUnlock = viewModel::onBiometricUnlockResult,
        onRememberDone = viewModel::onRememberDone,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ScreenHeader(
            title = stringResource(R.string.encrypt_title),
            subtitle = stringResource(R.string.encrypt_subtitle),
        )

        BusyLine(visible = state.busy)

        if (state.contacts.isEmpty()) {
            EmptyState(
                icon = CipherKeyIcons.People,
                title = stringResource(R.string.contacts_empty_title),
                body = stringResource(R.string.contacts_empty_body),
            )
        } else {
            SectionCard(title = stringResource(R.string.tools_input_label)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    placeholder = { Text(stringResource(R.string.tools_input_hint)) },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(onClick = {
                        viewModel.pasteIntoInput { input = it }
                    }) {
                        Text(stringResource(R.string.action_paste))
                    }
                }
            }

            SectionCard(title = stringResource(R.string.encrypt_contact_label)) {
                ContactPicker(
                    contacts = state.contacts,
                    selectedContactId = state.selectedContactId,
                    unlocked = state.unlocked,
                    onContactSelected = viewModel::selectContact,
                )
                if (state.selectedContact != null && state.selectedContactId !in state.unlocked) {
                    UnlockCard(
                        state = state,
                        onSubmit = viewModel::unlockAndContinue,
                        onUseFingerprint = viewModel::requestBiometricUnlock,
                    )
                } else {
                    Button(
                        onClick = { viewModel.encrypt(input) },
                        enabled = !state.busy,
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text(stringResource(R.string.encrypt_action)) }
                }
            }
        }

        NoticeBanner(state.notice)

        state.resultText?.takeIf { state.resultIsEnvelope }?.let { envelope ->
            SectionCard(
                title = stringResource(
                    R.string.result_encrypted_for,
                    state.resultContactName ?: "",
                ),
            ) {
                ResultBlock(
                    text = envelope,
                    monospace = true,
                    copied = state.copied,
                    onCopy = viewModel::copyResult,
                    onClear = viewModel::clearResult,
                )
                Text(
                    stringResource(R.string.envelope_truncated_hint),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

/** Locked-contact card: passphrase (or fingerprint) before continuing. */
@Composable
private fun UnlockCard(
    state: org.cipherkey.app.ToolsUiState,
    onSubmit: (CharArray) -> Unit,
    onUseFingerprint: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            stringResource(R.string.locked_hint),
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        PassphraseInput(
            label = stringResource(R.string.passphrase_label),
            buttonLabel = stringResource(R.string.unlock_encrypt_action),
            busy = state.busy,
            onSubmit = onSubmit,
        )
        if (state.biometricOffered) {
            OutlinedButton(
                onClick = onUseFingerprint,
                enabled = !state.busy,
                modifier = Modifier.fillMaxWidth(),
            ) { Text(stringResource(R.string.use_fingerprint)) }
        }
    }
}
