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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.cipherkey.app.MainViewModel
import org.cipherkey.app.R
import org.cipherkey.app.companion.DestinationRoute
import org.cipherkey.app.companion.EXTRA_FROM_CLIPBOARD
import androidx.compose.material3.Card

/**
 * Home (v0.3.0): status chips, the quick-actions grid (in-app tools +
 * clipboard fallbacks), how-it-works and the security properties card.
 */
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigate: (String) -> Unit,
) {
    val contactCount by viewModel.contactCount.collectAsStateWithLifecycle()
    val unlockedCount by viewModel.unlockedCount.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) { viewModel.refresh() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.tagline),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        SectionCard(title = stringResource(R.string.home_status_title)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatusChip(
                    text = stringResource(R.string.home_status_contacts, contactCount),
                    positive = contactCount > 0,
                )
                StatusChip(
                    text = if (unlockedCount > 0) {
                        stringResource(R.string.home_status_unlocked, unlockedCount)
                    } else {
                        stringResource(R.string.home_status_locked)
                    },
                    positive = unlockedCount > 0,
                )
            }
        }

        SectionCard(title = stringResource(R.string.home_quick_title)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    QuickActionTile(
                        label = stringResource(R.string.home_quick_encrypt_text),
                        icon = CipherKeyIcons.Lock,
                        onClick = { onNavigate(DestinationRoute.ENCRYPT) },
                        modifier = Modifier.weight(1f),
                    )
                    QuickActionTile(
                        label = stringResource(R.string.home_quick_decrypt_text),
                        icon = CipherKeyIcons.LockOpen,
                        onClick = { onNavigate(DestinationRoute.DECRYPT) },
                        modifier = Modifier.weight(1f),
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    QuickActionTile(
                        label = stringResource(R.string.home_quick_encrypt_clip),
                        icon = CipherKeyIcons.Copy,
                        onClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    org.cipherkey.app.companion.EncryptActivity::class.java,
                                ).putExtra(EXTRA_FROM_CLIPBOARD, true),
                            )
                        },
                        modifier = Modifier.weight(1f),
                    )
                    QuickActionTile(
                        label = stringResource(R.string.home_quick_decrypt_clip),
                        icon = CipherKeyIcons.Paste,
                        onClick = {
                            context.startActivity(
                                Intent(
                                    context,
                                    org.cipherkey.app.companion.DecryptActivity::class.java,
                                ).putExtra(EXTRA_FROM_CLIPBOARD, true),
                            )
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }

        SectionCard(title = stringResource(R.string.home_how_title)) {
            Text(stringResource(R.string.home_how_step1), fontSize = 14.sp)
            Text(stringResource(R.string.home_how_step2), fontSize = 14.sp)
            Text(stringResource(R.string.home_how_step3), fontSize = 14.sp)
            Text(stringResource(R.string.home_how_step4), fontSize = 14.sp)
        }

        SectionCard(title = stringResource(R.string.home_security_title)) {
            Text(stringResource(R.string.home_security_b1), fontSize = 14.sp)
            Text(stringResource(R.string.home_security_b2), fontSize = 14.sp)
            Text(stringResource(R.string.home_security_b3), fontSize = 14.sp)
            Text(stringResource(R.string.home_security_b4), fontSize = 14.sp)
            Text(stringResource(R.string.home_security_b5), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
