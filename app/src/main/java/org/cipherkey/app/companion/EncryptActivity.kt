package org.cipherkey.app.companion

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.companion.ProcessTextContract.SelectedText

/**
 * PROCESS_TEXT entry point for the outgoing workflow (spec 003, User
 * Story 1): the user selects the draft they just typed (with any keyboard,
 * including Samsung Keyboard) and taps "Encrypt (CipherKey)" in the
 * selection toolbar. The activity encrypts the selection for the sticky
 * contact and returns the CK1 envelope as the activity result, which the
 * system uses to replace the selected text in the calling field.
 */
@AndroidEntryPoint
class EncryptActivity : BaseCompanionActivity() {

    override fun dispatchIntent(intent: Intent) {
        val fromClipboard = intent.getBooleanExtra(EXTRA_FROM_CLIPBOARD, false)

        val parsed = if (fromClipboard) {
            // Clipboard fallback launched from CipherKey's Home screen:
            // user-initiated, foreground — ClipboardAccess chokepoint.
            viewModel.peekClipboard(CompanionMode.ENCRYPT)
        } else {
            ProcessTextContract.parsePlaintext(
                intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT),
            )
        }

        when (parsed) {
            is SelectedText.Valid ->
                viewModel.beginEncrypt(
                    text = parsed.text,
                    source = if (fromClipboard) TextSource.CLIPBOARD else TextSource.SELECTION,
                )
            SelectedText.Empty -> showNotice(
                if (fromClipboard) {
                    "The clipboard is empty — copy a draft first, then tap Encrypt clipboard."
                } else {
                    "No text was selected. Select the draft text and try again."
                },
            )
            is SelectedText.Oversized -> showNotice(
                "The selected text is longer than ${parsed.limitBytes / 1024} KiB and cannot be encrypted as one message.",
            )
        }
    }
}
