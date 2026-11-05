package org.cipherkey.app.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.cipherkey.app.BuildConfig
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.app.R
import org.cipherkey.app.SettingsEvent
import org.cipherkey.app.SettingsViewModel
import org.cipherkey.security.policies.Argon2Profile
import org.cipherkey.security.policies.KeyPolicy

/**
 * Settings (v0.3.0): unlock & security section (timeout picker, fingerprint
 * unlock, floating assistant, lock-all), key-derivation strength, and the
 * About card. Strings come from resources; every row states its trade-off.
 */
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    contactsViewModel: ContactsViewModel,
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val biometricAvailable by viewModel.biometricAvailable.collectAsStateWithLifecycle()
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
            fontWeight = FontWeight.Bold,
        )

        // ------------------------------------------------ unlock & security

        SectionCard(title = stringResource(R.string.settings_unlock_section)) {
            Text(
                stringResource(R.string.settings_timeout_title),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                stringResource(R.string.settings_timeout_summary),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            KeyPolicy.TIMEOUT_CHOICES_MILLIS.forEach { choice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
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
                        fontSize = 14.sp,
                    )
                }
            }
            TextButton(onClick = { contactsViewModel.lockAll() }) {
                Text(stringResource(R.string.lock_now))
            }

            // --------------------------------------- fingerprint unlock

            Text(
                stringResource(R.string.settings_biometric_title),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                stringResource(R.string.settings_biometric_summary),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (!biometricAvailable) {
                Text(
                    stringResource(R.string.settings_biometric_unavailable),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                )
            }
            SwitchRow(
                checked = settings.biometricUnlockEnabled && biometricAvailable,
                enabled = biometricAvailable,
                onCheckedChange = viewModel::setBiometricEnabled,
            )

            // --------------------------------------- floating assistant

            Text(
                stringResource(R.string.settings_assistant_title),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                stringResource(R.string.settings_assistant_summary),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (!overlayGranted) {
                Text(
                    stringResource(R.string.settings_assistant_needs_overlay),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                )
            }
            SwitchRow(
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

        SectionCard(title = stringResource(R.string.settings_kdf_title)) {
            Text(
                stringResource(R.string.settings_kdf_summary),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Argon2Profile.entries.forEach { profile ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    RadioButton(
                        selected = settings.argon2Profile == profile,
                        onClick = { viewModel.setArgon2Profile(profile) },
                    )
                    Column {
                        Text(
                            profile.name.lowercase().replaceFirstChar { it.uppercase() },
                            fontSize = 14.sp,
                        )
                        Text(
                            stringResource(
                                R.string.kdf_profile_desc,
                                profile.params.memoryKib / 1024,
                                profile.params.iterations,
                            ),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }

        // ------------------------------------------------ about

        SectionCard(title = stringResource(R.string.settings_about_title)) {
            // Deep-scan finding 7: the version comes from the build itself.
            Text(
                stringResource(R.string.settings_about_version, BuildConfig.VERSION_NAME),
                fontSize = 13.sp,
            )
            Text(stringResource(R.string.settings_about_line2), fontSize = 13.sp)
            Text(
                stringResource(R.string.settings_about_line3),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun SwitchRow(
    checked: Boolean,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            if (checked) stringResource(R.string.state_on) else stringResource(R.string.state_off),
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
        )
        Switch(checked = checked, enabled = enabled, onCheckedChange = onCheckedChange)
    }
}
