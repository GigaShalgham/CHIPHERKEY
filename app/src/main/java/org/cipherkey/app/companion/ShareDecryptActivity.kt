package org.cipherkey.app.companion

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.companion.ProcessTextContract.SelectedText

/**
 * ACTION_SEND (text/plain) entry point for the incoming workflow
 * (spec 003, User Story 2): messengers where sharing the message is more
 * natural than selecting it offer "Decrypt with CipherKey" in the
 * sharesheet. Always read-only by nature — the flow ends in an explicit
 * preview plus Copy.
 */
@AndroidEntryPoint
class ShareDecryptActivity : BaseCompanionActivity() {

    override fun dispatchIntent(intent: Intent) {
        val shared = when (intent.action) {
            Intent.ACTION_SEND ->
                intent.getCharSequenceExtra(Intent.EXTRA_TEXT)
            else -> null
        }

        when (val parsed = ProcessTextContract.parseEnvelope(shared)) {
            is SelectedText.Valid ->
                viewModel.beginDecrypt(
                    text = parsed.text,
                    source = TextSource.SHARE,
                    readOnly = true,
                )
            SelectedText.Empty -> showNotice(
                "Nothing was shared. Share the \"CK1:…\" message text with CipherKey.",
            )
            is SelectedText.Oversized -> showNotice(
                "The shared text is longer than ${parsed.limitBytes / 1024} KiB and is not a valid CK1 envelope.",
            )
        }
    }
}
