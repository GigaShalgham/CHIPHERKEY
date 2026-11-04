package org.cipherkey.app.companion

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.ui.theme.CipherKeyTheme

/**
 * Extra used by CipherKey's own Home screen to route a clipboard fallback
 * through the same processing activities (spec 003, User Story 3). When
 * present, the activity reads its text from the clipboard (explicit,
 * user-initiated launch = foreground access, via the ClipboardAccess
 * chokepoint) instead of the PROCESS_TEXT extra, and hides the
 * "Replace in field" affordance.
 */
const val EXTRA_FROM_CLIPBOARD = "org.cipherkey.app.extra.FROM_CLIPBOARD"

/**
 * Shared skeleton for the companion processing activities.
 *
 * Hardening (FR-004): every input path funnels through
 * [ProcessTextContract] before any UI or crypto work happens; junk input
 * produces a graceful notice, never a crash.
 */
abstract class BaseCompanionActivity : ComponentActivity() {

    protected val viewModel: CompanionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            dispatchIntent(intent)
        }
        setContent {
            CipherKeyTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                CompanionSheet(
                    state = state,
                    onContactSelected = viewModel::selectContact,
                    onEncrypt = viewModel::encryptNow,
                    onUnlockEncrypt = viewModel::unlockAndEncrypt,
                    onUnlockDecrypt = viewModel::unlockAndDecrypt,
                    onCopy = viewModel::copyResult,
                    onInsert = ::finishWithResult,
                    onClose = { finish() },
                )
            }
        }
    }

    /** Re-dispatch on NEW_INTENT (singleTop relaunch from the toolbar). */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        dispatchIntent(intent)
    }

    protected abstract fun dispatchIntent(intent: Intent)

    /** Returns the result text to the calling field (PROCESS_TEXT contract). */
    protected fun finishWithResult() {
        val result = viewModel.state.value.resultText
        if (result != null) {
            setResult(
                Activity.RESULT_OK,
                Intent().putExtra(Intent.EXTRA_PROCESS_TEXT, result),
            )
        }
        finish()
    }

    /** Graceful dead-end notice for unusable input (FR-004). */
    protected fun showNotice(notice: String) {
        viewModel.showNotice(notice)
    }
}
