package org.cipherkey.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.cipherkey.security.policies.Argon2Profile
import org.cipherkey.security.policies.KeyPolicy

private val Context.cipherKeyDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "cipherkey_settings",
)

/**
 * User-facing security settings.
 *
 * Defaults come from [KeyPolicy]/[Argon2Profile]; persisted values are
 * re-validated on read so that a corrupted or forged store cannot loosen the
 * policy.
 */
data class AppSettings(
    val sessionTimeoutMillis: Long = KeyPolicy.DEFAULT_SESSION_TIMEOUT_MILLIS,
    val argon2Profile: Argon2Profile = Argon2Profile.DEFAULT,
    val onboardingCompleted: Boolean = false,
)

class SettingsRepository(
    private val dataStore: DataStore<Preferences>,
) {

    val settings: Flow<AppSettings> = dataStore.data.map { prefs ->
        AppSettings(
            sessionTimeoutMillis = KeyPolicy.clampSessionTimeout(
                prefs[KEY_SESSION_TIMEOUT] ?: KeyPolicy.DEFAULT_SESSION_TIMEOUT_MILLIS,
            ),
            argon2Profile = Argon2Profile.fromName(prefs[KEY_ARGON2_PROFILE])
                ?: Argon2Profile.DEFAULT,
            onboardingCompleted = prefs[KEY_ONBOARDING_COMPLETED] ?: false,
        )
    }

    suspend fun setSessionTimeoutMillis(requestedMillis: Long) {
        dataStore.edit { prefs ->
            prefs[KEY_SESSION_TIMEOUT] = KeyPolicy.clampSessionTimeout(requestedMillis)
        }
    }

    suspend fun setArgon2Profile(profile: Argon2Profile) {
        dataStore.edit { prefs ->
            prefs[KEY_ARGON2_PROFILE] = profile.name
        }
    }

    suspend fun setOnboardingCompleted() {
        dataStore.edit { prefs ->
            prefs[KEY_ONBOARDING_COMPLETED] = true
        }
    }

    /**
     * Sticky companion contact: the last contact the user encrypted for,
     * so the PROCESS_TEXT encrypt flow usually needs zero extra taps
     * (spec 003, FR-005).
     *
     * Pure storage — no existence validation here (this module cannot see
     * Room). Callers must clamp: if the id no longer matches a contact,
     * treat it as null.
     */
    val lastContactId: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_LAST_CONTACT_ID]
    }

    suspend fun setLastContactId(contactId: String?) {
        dataStore.edit { prefs ->
            if (contactId == null) {
                prefs.remove(KEY_LAST_CONTACT_ID)
            } else {
                prefs[KEY_LAST_CONTACT_ID] = contactId
            }
        }
    }

    companion object {
        private val KEY_SESSION_TIMEOUT = longPreferencesKey("session_timeout_millis")
        private val KEY_ARGON2_PROFILE = stringPreferencesKey("argon2_profile")
        private val KEY_ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        private val KEY_LAST_CONTACT_ID = stringPreferencesKey("last_contact_id")

        /** Factory for the process-wide DataStore instance. */
        fun defaultDataStore(context: Context): DataStore<Preferences> = context.cipherKeyDataStore
    }
}
