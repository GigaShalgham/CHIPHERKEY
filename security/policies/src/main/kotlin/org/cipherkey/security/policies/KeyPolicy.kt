package org.cipherkey.security.policies

/**
 * Central security policy constants for key material and sessions.
 *
 * Every consumer (app UI, keyboard UI, repositories) validates against this
 * object so that the enforced limits exist in exactly one place.
 */
object KeyPolicy {

    /** Minimum accepted shared-passphrase length, in characters. */
    const val MIN_PASSPHRASE_CHARS = 8

    /** Hard upper bound for passphrase length (protects the KDF input). */
    const val MAX_PASSPHRASE_CHARS = 1024

    /** Default in-memory session lifetime after a successful unlock. */
    const val DEFAULT_SESSION_TIMEOUT_MILLIS: Long = 5 * 60_000L

    /** Shortest user-selectable session timeout. */
    const val MIN_SESSION_TIMEOUT_MILLIS: Long = 60_000L

    /** Longest user-selectable session timeout. */
    const val MAX_SESSION_TIMEOUT_MILLIS: Long = 60 * 60_000L

    /** Clamps a requested timeout into the accepted range. */
    fun clampSessionTimeout(requestedMillis: Long): Long =
        requestedMillis.coerceIn(MIN_SESSION_TIMEOUT_MILLIS, MAX_SESSION_TIMEOUT_MILLIS)

    /**
     * Returns a human-readable problem with the passphrase, or null when the
     * passphrase satisfies the policy. Deliberately minimal: length-only.
     * Composition/entropy rules give a false sense of security for shared
     * passphrases typed on phones; guidance text in the UI carries the real
     * advice (long, random, shared in person).
     */
    fun passphraseProblem(passphrase: CharArray): String? = when {
        passphrase.size < MIN_PASSPHRASE_CHARS ->
            "Passphrase must be at least $MIN_PASSPHRASE_CHARS characters"
        passphrase.size > MAX_PASSPHRASE_CHARS ->
            "Passphrase must be at most $MAX_PASSPHRASE_CHARS characters"
        passphrase.all { it.isWhitespace() } ->
            "Passphrase must not consist only of whitespace"
        else -> null
    }
}
