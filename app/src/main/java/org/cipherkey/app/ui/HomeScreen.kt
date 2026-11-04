package org.cipherkey.app.ui

import android.content.Intent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.MainViewModel
import org.cipherkey.app.companion.DecryptActivity
import org.cipherkey.app.companion.EncryptActivity
import org.cipherkey.app.companion.EXTRA_FROM_CLIPBOARD

/**
 * Companion home (spec 003): status, the clipboard fallback ladder, and
 * the how-to for the selection-toolbar workflows. The IME setup card from
 * v0.1 is gone with the keyboard module.
 */
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val contactCount by viewModel.contactCount.collectAsStateWithLifecycle()
    val unlockedCount by viewModel.unlockedCount.collectAsStateWithLifecycle()

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
            text = "A companion app that encrypts your messages in any " +
                "messenger — while you keep the keyboard you already use. " +
                "No servers, no accounts, no network access.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        QuickActionsCard()

        StatusCard(contactCount = contactCount, unlockedCount = unlockedCount)

        HowItWorksCard()

        SecurityCard()

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun QuickActionsCard() {
    val context = LocalContext.current
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Clipboard fallback", style = MaterialTheme.typography.titleMedium)
            Text(
                "When the selection toolbar is unavailable: copy the text, " +
                    "then use one of these.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(context, EncryptActivity::class.java)
                                .putExtra(EXTRA_FROM_CLIPBOARD, true),
                        )
                    },
                    modifier = Modifier.weight(1f),
                ) { Text("Encrypt clipboard") }
                OutlinedButton(
                    onClick = {
                        context.startActivity(
                            Intent(context, DecryptActivity::class.java)
                                .putExtra(EXTRA_FROM_CLIPBOARD, true),
                        )
                    },
                    modifier = Modifier.weight(1f),
                ) { Text("Decrypt clipboard") }
            }
        }
    }
}

@Composable
private fun StatusCard(contactCount: Int, unlockedCount: Int) {
    Card {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Status", style = MaterialTheme.typography.titleMedium)
            StatusLine(done = contactCount > 0, text = "Contacts with shared keys: $contactCount")
            StatusLine(done = unlockedCount > 0, text = "Unlocked sessions: $unlockedCount")
            if (contactCount == 0) {
                Text(
                    "Open the Contacts tab: create a contact, set a shared passphrase, and exchange the invite code once.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun StatusLine(done: Boolean, text: String) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(if (done) "✅" else "⬜", fontSize = 16.sp)
        Text(text, fontSize = 14.sp)
    }
}

@Composable
private fun HowItWorksCard() {
    Card {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("How it works", style = MaterialTheme.typography.titleMedium)
            Text("1. Agree on a passphrase with your contact in person; exchange the invite code (CKS1:…) once.", fontSize = 14.sp)
            Text("2. Sending: type your message with any keyboard, select it, and tap \"Encrypt (CipherKey)\" in the selection toolbar. The draft is replaced by ciphertext — send it.", fontSize = 14.sp)
            Text("3. Receiving: select the CK1:… message and tap \"Decrypt (CipherKey)\" — or share it to CipherKey. Read the preview, copy if needed.", fontSize = 14.sp)
            Text("4. If the toolbar entry is missing in an app, use the clipboard fallback above.", fontSize = 14.sp)
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
            Text("• Decrypted text is previewed explicitly and never written to disk.", fontSize = 14.sp)
            Text(
                text = "Limitations in v0.2: public-key mode not yet integrated; messages from older key eras cannot be decrypted; toolbar entries depend on the target app supporting standard text selection.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
