package org.cipherkey.security.clipboard

import android.content.ClipboardManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Narrow, auditable view of the system clipboard.
 *
 * SECURITY CONTRACT — this interface exists so that the clipboard read path
 * is a single, reviewable chokepoint:
 *  - it is invoked ONLY from an explicit user action (the keyboard's
 *    "Decrypt clipboard" button);
 *  - CipherKey never registers clipboard listeners and never reads the
 *    clipboard in the background;
 *  - on Android 10+ the platform additionally restricts clipboard access to
 *    the focused/default input method, which CipherKey is while visible.
 */
interface ClipboardAccess {

    /**
     * Returns the current primary clip as text, or null when no clip exists
     * or it cannot be coerced to text.
     */
    fun currentText(): String?

    /**
     * Overwrites the primary clip with [text].
     *
     * SECURITY CONTRACT: used ONLY as an explicit, user-visible fallback
     * (e.g. the draft could not be replaced in the field, so the envelope is
     * handed to the user via the clipboard). Never called implicitly.
     */
    fun setText(text: String): Boolean
}

@Singleton
class AndroidClipboardAccess @Inject constructor(
    @ApplicationContext private val context: Context,
) : ClipboardAccess {

    override fun currentText(): String? {
        val manager = manager() ?: return null
        val clip = manager.primaryClip ?: return null
        if (clip.itemCount <= 0) return null
        return runCatching { clip.getItemAt(0)?.coerceToText(context)?.toString() }.getOrNull()
    }

    override fun setText(text: String): Boolean {
        val manager = manager() ?: return false
        return runCatching {
            manager.setPrimaryClip(android.content.ClipData.newPlainText("CipherKey", text))
            true
        }.getOrDefault(false)
    }

    private fun manager(): ClipboardManager? =
        context.getSystemService(ClipboardManager::class.java)
}
