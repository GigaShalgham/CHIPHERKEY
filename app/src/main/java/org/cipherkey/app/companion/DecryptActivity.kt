package org.cipherkey.app.companion

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.companion.ProcessTextContract.SelectedText

/**
 * PROCESS_TEXT entry point for the incoming workflow (spec 003, User
 * Story 2): the user selects a received "CK1:…" message and taps
 * "Decrypt (CipherKey)". Works on read-only text (EXTRA_PROCESS_TEXT_
 * READONLY) as well as editable fields. Shows an explicit preview before
 * anything is copied; offers result-insertion only in editable contexts.
 */
@AndroidEntryPoint
class DecryptActivity : BaseCompanionActivity() {

    override fun dispatchIntent(intent: Intent) {
        val fromClipboard = intent.getBooleanExtra(EXTRA_FROM_CLIPBOARD, false)

        val parsed = if (fromClipboard) {
            viewModel.peekClipboard(CompanionMode.DECRYPT)
        } else {
            ProcessTextContract.parseEnvelope(
                intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT),
            )
        }

        val readOnly = if (fromClipboard) {
            true
        } else {
            intent.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)
        }

        when (parsed) {
            is SelectedText.Valid ->
                viewModel.beginDecrypt(
                    text = parsed.text,
                    source = if (fromClipboard) TextSource.CLIPBOARD else TextSource.SELECTION,
                    readOnly = readOnly,
                )
            SelectedText.Empty -> showNotice(
                if (fromClipboard) {
                    "The clipboard is empty — copy the whole \"CK1:…\" message first."
                } else {
                    "No text was selected. Select the whole \"CK1:…\" message and try again."
                },
            )
            is SelectedText.Oversized -> showNotice(
                "The selected text is longer than ${parsed.limitBytes / 1024} KiB and is not a valid CK1 envelope.",
            )
        }
    }
}
