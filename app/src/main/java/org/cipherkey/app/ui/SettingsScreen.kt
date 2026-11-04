package org.cipherkey.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.BuildConfig
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.app.SettingsViewModel
import org.cipherkey.security.policies.Argon2Profile
import org.cipherkey.security.policies.KeyPolicy
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    contactsViewModel: ContactsViewModel,
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Security settings", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Card {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Session auto-lock", style = MaterialTheme.typography.titleMedium)
                // Deep-scan finding 13: the slider keeps a LOCAL pending value
                // while dragging and writes to DataStore only when the drag
                // finishes — previously every animation frame was a disk write.
                var pendingMinutes by remember { mutableStateOf<Float?>(null) }
                val shownMinutes = (pendingMinutes ?: (settings.sessionTimeoutMillis / 60_000f)).roundToInt()
                Text(
                    "Unlocked keys are wiped from memory after $shownMinutes minute(s) of use. " +
                        "You can also wipe every session immediately with the button below.",
                    fontSize = 13.sp,
                )
                val range = (KeyPolicy.MIN_SESSION_TIMEOUT_MILLIS / 60_000f).roundToInt()..
                    (KeyPolicy.MAX_SESSION_TIMEOUT_MILLIS / 60_000f).roundToInt()
                Slider(
                    value = pendingMinutes ?: (settings.sessionTimeoutMillis / 60_000f),
                    onValueChange = { pendingMinutes = it },
                    onValueChangeFinished = {
                        pendingMinutes?.let { viewModel.setSessionTimeoutMinutes(it.roundToInt()) }
                        pendingMinutes = null
                    },
                    valueRange = range.toFloatRange(),
                    steps = 0,
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("${range.first} min", fontSize = 12.sp)
                    Text("${range.last} min", fontSize = 12.sp)
                }
                TextButton(onClick = { contactsViewModel.lockAll() }) { Text("Lock all sessions now") }
            }
        }

        Card {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Key derivation strength (new contacts)", style = MaterialTheme.typography.titleMedium)
                Text(
                    "Applies to key eras created from now on. Stronger settings make brute-force " +
                        "attacks proportionally more expensive and unlock slightly slower.",
                    fontSize = 13.sp,
                )
                Argon2Profile.entries.forEach { profile ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        androidx.compose.material3.RadioButton(
                            selected = settings.argon2Profile == profile,
                            onClick = { viewModel.setArgon2Profile(profile) },
                        )
                        Column {
                            Text(profile.name.lowercase().replaceFirstChar { it.uppercase() }, fontSize = 14.sp)
                            Text(
                                "${profile.params.memoryKib / 1024} MiB · ${profile.params.iterations} passes · 1 lane",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }
        }

        Card {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("About", style = MaterialTheme.typography.titleMedium)
                // Deep-scan finding 7: the version is taken from the build
                // itself instead of a hardcoded (previously stale "0.1.0")
                // string, and the description matches the shipped companion
                // architecture.
                Text("CipherKey ${BuildConfig.VERSION_NAME} — local-first text encryption companion.", fontSize = 13.sp)
                Text("CK1 protocol v1 · AES-256-GCM · Argon2id (RFC 9106) · Apache-2.0 license.", fontSize = 13.sp)
                Text(
                    "Public-key mode (CK1 mode 0x02) is specified but not yet integrated; " +
                        "see docs/PLUGINS.md for the evaluation.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

private fun IntRange.toFloatRange(): ClosedFloatingPointRange<Float> =
    start.toFloat()..endInclusive.toFloat()
