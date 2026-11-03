package org.cipherkey.app.ui

import android.content.Intent
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.ImeStatus
import org.cipherkey.app.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val status by viewModel.imeStatus.collectAsStateWithLifecycle()
    val contactCount by viewModel.contactCount.collectAsStateWithLifecycle()

    // Re-check IME status whenever the screen becomes visible (the user may
    // have just returned from system settings).
    androidx.compose.runtime.LaunchedEffect(Unit) { viewModel.refresh() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "CipherKey",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "An encryption layer that lives in your keyboard. " +
                "No servers, no accounts, no network access.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        StatusCard(status = status, contactCount = contactCount)

        HowItWorksCard()

        SecurityCard()

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun StatusCard(status: ImeStatus, contactCount: Int) {
    val context = LocalContext.current
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Setup", style = MaterialTheme.typography.titleMedium)

            StatusLine(done = status.enabled, text = "CipherKey keyboard enabled in the system")
            StatusLine(done = status.selectedAsDefault, text = "CipherKey selected as the active keyboard")
            StatusLine(done = contactCount > 0, text = "At least one contact with a shared key ($contactCount so far)")

            if (!status.enabled) {
                Button(
                    onClick = {
                        context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Enable in system settings") }
            } else if (!status.selectedAsDefault) {
                val imm = context.getSystemService(InputMethodManager::class.java)
                Button(
                    onClick = { imm?.showInputMethodPicker() },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Select CipherKey as active keyboard") }
            } else {
                Text(
                    text = "You're set up. Open any messaging app and use the Encrypt / Decrypt buttons on the keyboard.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun StatusLine(done: Boolean, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(if (done) "✅" else "⬜", fontSize = 16.sp)
        Text(text, fontSize = 14.sp)
    }
}

@Composable
private fun HowItWorksCard() {
    Card {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("How it works", style = MaterialTheme.typography.titleMedium)
            Text("1. Create a contact and set a shared passphrase — agree on it in person.", fontSize = 14.sp)
            Text("2. Send the contact the invite code (CKS1:...) over any channel; they import it with the same passphrase.", fontSize = 14.sp)
            Text("3. Sending: type your message, tap Encrypt on the keyboard — the draft is replaced by ciphertext, send it normally.", fontSize = 14.sp)
            Text("4. Receiving: copy the CK1:... message, tap Decrypt on the keyboard, read the preview, insert.", fontSize = 14.sp)
        }
    }
}

@Composable
private fun SecurityCard() {
    Card {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Security properties", style = MaterialTheme.typography.titleMedium)
            Text("• Zero Android permissions requested.", fontSize = 14.sp)
            Text("• AES-256-GCM with Argon2id key derivation (RFC 9106).", fontSize = 14.sp)
            Text("• Keys live in memory only and are wiped on lock or timeout.", fontSize = 14.sp)
            Text("• At-rest key copies are wrapped by the Android Keystore.", fontSize = 14.sp)
            Text("• No Internet access — nothing leaves this device.", fontSize = 14.sp)
            Text(
                text = "Limitations in v0.1: public-key mode not yet integrated; messages from older key eras cannot be decrypted; Latin QWERTY layout only.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
