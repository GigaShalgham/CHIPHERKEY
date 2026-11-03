package org.cipherkey.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.keyboard.compose.KeyboardLifecycleOwner
import org.cipherkey.keyboard.input.InputConnectionTextField
import org.cipherkey.keyboard.input.SensitiveFieldPolicy
import org.cipherkey.keyboard.input.TextFieldAccess
import org.cipherkey.keyboard.ui.BaseKeyboard
import org.cipherkey.keyboard.ui.Key
import org.cipherkey.keyboard.ui.KeyAction

/**
 * CipherKey input method service.
 *
 * Lifecycle of the Compose content hosted inside the IME is driven by
 * [KeyboardLifecycleOwner]; the service itself follows the standard
 * InputMethodService contract.
 *
 * Security invariants:
 *  - no field content is ever logged;
 *  - the clipboard is only read after an explicit user action;
 *  - encryption features are disabled on password/sensitive fields
 *    (see [SensitiveFieldPolicy]).
 */
@AndroidEntryPoint
class CipherKeyboardService : InputMethodService() {

    private val keyboardLifecycleOwner = KeyboardLifecycleOwner()

    private var shiftOn by mutableStateOf(false)
    private var symbolsMode by mutableStateOf(false)
    private var sensitiveField by mutableStateOf(false)

    private lateinit var textField: TextFieldAccess

    override fun onCreate() {
        super.onCreate()
        keyboardLifecycleOwner.performRestore(null)
        keyboardLifecycleOwner.dispatchEvent(Lifecycle.Event.ON_CREATE)
        textField = InputConnectionTextField(
            connectionProvider = { currentInputConnection },
            editorInfoProvider = { currentInputEditorInfo },
        )
    }

    override fun onCreateInputView(): View {
        val view = ComposeView(this)
        // The ViewTree owner extensions are the documented way to prepare a
        // ComposeView outside an Activity (an IME has no activity lifecycle).
        view.setViewTreeLifecycleOwner(keyboardLifecycleOwner)
        view.setViewTreeViewModelStoreOwner(keyboardLifecycleOwner)
        view.setViewTreeSavedStateRegistryOwner(keyboardLifecycleOwner)
        view.setContent {
            KeyboardContent()
        }
        return view
    }

    override fun onStartInputView(editorInfo: EditorInfo, restarting: Boolean) {
        super.onStartInputView(editorInfo, restarting)
        sensitiveField = SensitiveFieldPolicy.isSensitive(editorInfo)
        // Never carry state across fields.
        shiftOn = false
        symbolsMode = false
    }

    override fun onDestroy() {
        keyboardLifecycleOwner.dispatchEvent(Lifecycle.Event.ON_DESTROY)
        keyboardLifecycleOwner.clearViewModels()
        super.onDestroy()
    }

    /** Fullscreen extract mode is disabled; CipherKey always renders its own view. */
    override fun onEvaluateFullscreenMode(): Boolean = false

    @Composable
    private fun KeyboardContent() {
        Column(modifier = Modifier.fillMaxWidth()) {
            BaseKeyboard(
                shiftOn = shiftOn,
                onKey = ::handleKey,
            )
        }
    }

    private fun handleKey(key: Key) {
        when (key.action) {
            KeyAction.Text -> {
                val text = if (shiftOn) key.text.uppercase() else key.text
                textField.commitText(text)
                shiftOn = false
            }
            KeyAction.Shift -> shiftOn = !shiftOn
            KeyAction.Backspace -> textField.deleteBackward()
            KeyAction.Space -> textField.commitText(" ")
            KeyAction.Enter -> sendDefaultEditorAction(true)
            KeyAction.SymbolToggle -> {
                // TODO(phase-3): symbol/number layout. Not yet implemented —
                // the key is inert in the current build.
                symbolsMode = !symbolsMode
            }
        }
    }
}
