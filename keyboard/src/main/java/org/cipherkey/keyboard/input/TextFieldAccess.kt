package org.cipherkey.keyboard.input

/**
 * Narrow, framework-independent view of the current text field.
 *
 * The keyboard's logic layer depends on this interface instead of
 * [android.view.inputmethod.InputConnection] so that replacement, insertion
 * and draft-reading behaviour can be unit tested on the JVM with fakes.
 */
interface TextFieldAccess {

    /** Commits [text] at the cursor. Returns false when no input connection is available. */
    fun commitText(text: String): Boolean

    /** Deletes the character before the cursor. Returns false when nothing was deleted. */
    fun deleteBackward(): Boolean

    /**
     * Replaces the entire content of the field with [newText].
     * Returns false when the field could not be edited.
     */
    fun replaceAll(newText: String): Boolean

    /**
     * Reads the current draft (text around the cursor). Returns null when
     * the text cannot be read (missing connection or non-cooperating editor).
     */
    fun readDraft(): String?

    /** True while the field reports that it is a password / sensitive field. */
    val isSensitiveField: Boolean
}
