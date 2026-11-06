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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.R
import org.cipherkey.app.ToolsViewModel
import org.cipherkey.app.security.BiometricKeyStore
import org.cipherkey.app.security.BiometricPromptHost
import org.cipherkey.app.security.BiometricPromptRequest
import org.cipherkey.app.security.rememberBiometricPromptHelper
import org.cipherkey.core.messages.Ck1

/**
 * In-app Decrypt tab (v0.3.0): paste a CK1:… message and read its original
 * text. Auto-detects envelopes, auto-resolves the contact from the keyId,
 * and offers passphrase or fingerprint unlock.
 */
@Composable
fun DecryptScreen(
    viewModel: ToolsViewModel,
    biometrics: BiometricKeyStore,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val promptHelper = rememberBiometricPromptHelper(biometrics)
    // v0.4.2 (deep-scan): rememberSaveable — a rotation used to wipe the
    // pasted envelope. (Passphrase fields are deliberately NOT saved.)
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

    val envelopeDetected = remember(input) { Ck1.isEnvelope(input.trim()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ScreenHeader(
            title = stringResource(R.string.decrypt_title),
            subtitle = stringResource(R.string.decrypt_subtitle),
        )

        BusyLine(visible = state.busy)

        SectionCard(
            title = stringResource(R.string.decrypt_input_label),
            icon = CipherKeyIcons.LockOpen,
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text(stringResource(R.string.decrypt_input_hint)) },
                minLines = 3,
                modifier = Modifier.fillMaxWidth(),
            )
            if (envelopeDetected) {
                Text(
                    stringResource(R.string.decrypt_ck1_detected),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(onClick = {
                    viewModel.pasteIntoInput { input = it }
                }) {
                    Text(stringResource(R.string.action_paste))
                }
                // v0.5.1: see WHAT the clipboard holds before pasting.
                OutlinedButton(onClick = viewModel::previewClipboard) {
                    Text(stringResource(R.string.action_view_clipboard))
                }
            }
        }

        if (state.unlockNeeded) {
            SectionCard(
                title = stringResource(
                    R.string.message_from,
                    state.unlockContactName ?: "",
                ),
                icon = CipherKeyIcons.People,
            ) {
                // v0.4.0: old-key-era envelopes ask for the passphrase of the
                // era they were sent under — which may predate the current one.
                Text(
                    stringResource(
                        if (state.pendingEraSessionId != null) {
                            R.string.locked_era_hint
                        } else {
                            R.string.locked_hint
                        },
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                PassphraseInput(
                    label = stringResource(R.string.passphrase_label),
                    buttonLabel = stringResource(R.string.unlock_decrypt_action),
                    busy = state.busy,
                    onSubmit = viewModel::unlockAndContinue,
                )
                if (state.biometricOffered) {
                    OutlinedButton(
                        onClick = viewModel::requestBiometricUnlock,
                        enabled = !state.busy,
                        modifier = Modifier.fillMaxWidth(),
                    ) { Text(stringResource(R.string.use_fingerprint)) }
                }
            }
        } else {
            Button(
                onClick = { viewModel.decrypt(input) },
                enabled = !state.busy,
                modifier = Modifier.fillMaxWidth(),
            ) { Text(stringResource(R.string.decrypt_action)) }
        }

        NoticeBanner(state.notice)

        // v0.5.1: clipboard preview dialog ("View clipboard").
        state.clipboardPreview?.let { preview ->
            ClipboardPreviewDialog(
                preview = preview,
                onPaste = {
                    viewModel.dismissClipboardPreview()
                    viewModel.pasteIntoInput { input = it }
                },
                onDismiss = viewModel::dismissClipboardPreview,
            )
        }

        state.resultText?.takeIf { !state.resultIsEnvelope }?.let { plaintext ->
            SectionCard(
                title = stringResource(
                    R.string.result_decrypted_from,
                    state.resultContactName ?: "",
                ),
            ) {
                ResultBlock(
                    text = plaintext,
                    monospace = false,
                    copied = state.copied,
                    onCopy = viewModel::copyResult,
                    onClear = viewModel::clearResult,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
