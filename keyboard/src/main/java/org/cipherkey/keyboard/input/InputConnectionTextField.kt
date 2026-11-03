package org.cipherkey.keyboard.input

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection

/**
 * [TextFieldAccess] implementation backed by the IME's current
 * [InputConnection].
 *
 * Every operation is defensive: InputConnection state varies wildly across
 * apps and can be null or disconnected at any time. No operation throws;
 * all failures are reported through return values so the keyboard can keep
 * its normal typing behaviour intact.
 *
 * NOTE: never logs field content. Not even in debug builds.
 */
class InputConnectionTextField(
    private val connectionProvider: () -> InputConnection?,
    private val editorInfoProvider: () -> EditorInfo?,
) : TextFieldAccess {

    override val isSensitiveField: Boolean
        get() = SensitiveFieldPolicy.isSensitive(editorInfoProvider())

    override fun commitText(text: String): Boolean {
        if (text.isEmpty()) return true
        val connection = connectionProvider() ?: return false
        return connection.commitText(text, 1)
    }

    override fun deleteBackward(): Boolean {
        val connection = connectionProvider() ?: return false
        if (connection.deleteSurroundingText(1, 0)) return true
        // Fallback for editors that do not implement deleteSurroundingText.
        val down = connection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
        val up = connection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL))
        return down && up
    }

    override fun replaceAll(newText: String): Boolean {
        val connection = connectionProvider() ?: return false
        connection.finishComposingText()

        // Strategy 1: compute exact bounds around the cursor and delete them.
        val before = connection.getTextBeforeCursor(MAX_DRAFT_LENGTH, 0)
        val after = connection.getTextAfterCursor(MAX_DRAFT_LENGTH, 0)
        if (before != null && after != null) {
            val deleted = connection.deleteSurroundingText(before.length, after.length)
            if (deleted) {
                return connection.commitText(newText, 1)
            }
        }

        // Strategy 2: select-all via context menu action, then overwrite the
        // selection. Works in most editors (incl. web views) but is not
        // guaranteed everywhere.
        val selected = connection.performContextMenuAction(android.R.id.selectAll)
        if (selected) {
            return connection.commitText(newText, 1)
        }
        return false
    }

    override fun readDraft(): String? {
        val connection = connectionProvider() ?: return null
        val before = connection.getTextBeforeCursor(MAX_DRAFT_LENGTH, 0) ?: return null
        val after = connection.getTextAfterCursor(MAX_DRAFT_LENGTH, 0) ?: return null
        return before.toString() + after.toString()
    }

    private companion object {
        /** Generous upper bound for draft reads (see security policy for input limits). */
        const val MAX_DRAFT_LENGTH = 100_000
    }
}
