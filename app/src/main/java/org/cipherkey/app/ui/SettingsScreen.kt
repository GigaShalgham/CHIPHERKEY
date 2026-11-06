package org.cipherkey.app.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.cipherkey.app.BuildConfig
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.app.R
import org.cipherkey.app.SettingsEvent
import org.cipherkey.app.SettingsViewModel
import org.cipherkey.app.security.BiometricGate
import org.cipherkey.app.companion.UiNotice
import org.cipherkey.security.policies.Argon2Profile
import org.cipherkey.security.policies.KeyPolicy

/**
 * Settings (v0.5, "Calm Vault"): four titled sections — Session & security,
 * Features, Key derivation, About — with labeled switch rows and a
 * prominent error-toned "Lock all sessions now" button. Every row still
 * states its trade-off; strings come from resources.
 */
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    contactsViewModel: ContactsViewModel,
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val biometricGate by viewModel.biometricGate.collectAsStateWithLifecycle()
    val overlayGranted by viewModel.overlayGranted.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Deep-scan F2: refresh on every resume — returning from the system
    // overlay-permission screen with the toggle on must start the service.
    LifecycleResumeEffect(Unit) {
        viewModel.refresh()
        onPauseOrDispose { }
    }

    // One-shot events: open the overlay-permission screen when the
    // assistant was enabled but the permission is missing.
    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                SettingsEvent.RequestOverlayPermission -> {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:${context.packageName}"),
                        ),
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            stringResource(R.string.settings_title),
            style = MaterialTheme.typography.headlineSmall,
        )

        // ------------------------------------------------ session & security

        SectionCard(
            title = stringResource(R.string.settings_unlock_section),
            icon = CipherKeyIcons.Timer,
        ) {
            Text(
                stringResource(R.string.settings_timeout_title),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                stringResource(R.string.settings_timeout_summary),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            KeyPolicy.TIMEOUT_CHOICES_MILLIS.forEach { choice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                ) {
                    RadioButton(
                        selected = settings.sessionTimeoutMillis == choice,
                        onClick = { viewModel.setSessionTimeoutMillis(choice) },
                    )
                    Text(
                        text = if (choice >= 60 * 60_000L) {
                            stringResource(
                                R.string.option_hours,
                                (choice / (60 * 60_000L)).toInt(),
                            )
                        } else {
                            stringResource(
                                R.string.option_minutes,
                                (choice / 60_000L).toInt(),
                            )
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            // v0.5: a full-width error-toned button — "lock everything"
            // is the one destructive-ish action on this screen and used
            // to be a quiet text link.
            Button(
                onClick = { contactsViewModel.lockAll() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp),
            ) { Text(stringResource(R.string.lock_now)) }
        }

        // ------------------------------------------------------- features

        SectionCard(
            title = stringResource(R.string.settings_features_section),
            icon = CipherKeyIcons.Shield,
        ) {
            // --------------------------------------- fingerprint unlock

            // v0.4.1 CI fix: a local copy enables the null/AVAILABLE checks —
            // smart casts are impossible on delegated properties.
            val gate = biometricGate
            if (gate != null && gate != BiometricGate.AVAILABLE) {
                // v0.4.1: the honest reason per failure — the v0.4.0 row
                // guessed "No fingerprint enrolled" for every case.
                NoticeBanner(
                    UiNotice(biometricReasonRes(gate)),
                    NoticeTone.WARNING,
                )
            }
            SettingSwitchRow(
                title = stringResource(R.string.settings_biometric_title),
                subtitle = stringResource(R.string.settings_biometric_summary),
                checked = settings.biometricUnlockEnabled && gate == BiometricGate.AVAILABLE,
                enabled = gate == BiometricGate.AVAILABLE,
                onCheckedChange = viewModel::setBiometricEnabled,
            )

            // --------------------------------------- floating assistant

            if (!overlayGranted) {
                NoticeBanner(
                    UiNotice(R.string.settings_assistant_needs_overlay),
                    NoticeTone.INFO,
                )
            }
            SettingSwitchRow(
                title = stringResource(R.string.settings_assistant_title),
                subtitle = stringResource(R.string.settings_assistant_summary),
                checked = settings.assistantEnabled,
                enabled = true,
                onCheckedChange = viewModel::setAssistantEnabled,
            )
            if (settings.assistantEnabled && !overlayGranted) {
                TextButton(onClick = {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:${context.packageName}"),
                        ),
                    )
                }) { Text(stringResource(R.string.settings_assistant_open_settings)) }
            }
        }

        // ------------------------------------------------ KDF strength

        SectionCard(
            title = stringResource(R.string.settings_kdf_title),
            icon = CipherKeyIcons.Key,
        ) {
            Text(
                stringResource(R.string.settings_kdf_summary),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Argon2Profile.entries.forEach { profile ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                ) {
                    RadioButton(
                        selected = settings.argon2Profile == profile,
                        onClick = { viewModel.setArgon2Profile(profile) },
                    )
                    Column {
                        Text(
                            profile.name.lowercase().replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            stringResource(
                                R.string.kdf_profile_desc,
                                profile.params.memoryKib / 1024,
                                profile.params.iterations,
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }

        // ------------------------------------------------ about

        SectionCard(
            title = stringResource(R.string.settings_about_title),
            icon = CipherKeyIcons.Info,
        ) {
            // Deep-scan finding 7: the version comes from the build itself.
            Text(
                stringResource(R.string.settings_about_version, BuildConfig.VERSION_NAME),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                stringResource(R.string.settings_about_line2),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                stringResource(R.string.settings_about_line3),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun biometricReasonRes(gate: BiometricGate): Int = when (gate) {
    BiometricGate.NONE_ENROLLED -> R.string.biometric_reason_none_enrolled
    BiometricGate.NO_HARDWARE -> R.string.biometric_reason_no_hardware
    BiometricGate.HW_UNAVAILABLE -> R.string.biometric_reason_hw_unavailable
    BiometricGate.SECURITY_UPDATE -> R.string.biometric_reason_security_update
    BiometricGate.NOT_STRONG -> R.string.biometric_reason_not_strong
    BiometricGate.AVAILABLE -> R.string.settings_biometric_summary
}
