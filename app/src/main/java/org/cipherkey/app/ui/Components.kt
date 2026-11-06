package org.cipherkey.app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cipherkey.app.R
import org.cipherkey.app.companion.UiNotice
import org.cipherkey.security.policies.PassphraseAdvisor

/**
 * Shared building blocks for the v0.3.0 screens — one visual language for
 * section cards, status chips, quick-action tiles, result blocks, passphrase
 * inputs, headers, busy rows and notice banners.
 */

/**
 * Titled card group used by every screen.
 *
 * v0.4.2 (deep-scan): outlined instead of elevated — a 1dp outline-colored
 * border reads calmer than a drop shadow and stays consistent with the
 * container-based contact cards. The border uses `outline` (a role our
 * schemes define) so no role ever falls back to the baseline purple.
 */
@Composable
fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            content()
        }
    }
}

/**
 * Small status pill ("Unlocked: 2" / "Contacts: 0").
 *
 * v0.4.2 (deep-scan): the two tones now use DISTINCT container roles —
 * positive states read brand indigo (primaryContainer), neutral
 * information reads slate (secondaryContainer). They used to share
 * surfaceVariant for everything non-positive, which made an informational
 * "Contacts: 0" chip look like a warning and let both chips blur together.
 */
@Composable
fun StatusChip(
    text: String,
    positive: Boolean,
    modifier: Modifier = Modifier,
) {
    val bg = if (positive) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    val fg = if (positive) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }
    Surface(
        color = bg,
        shape = MaterialTheme.shapes.small,
        modifier = modifier,
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = fg,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
    }
}

/**
 * Square quick-action button with icon + label (Home screen grid).
 *
 * v0.4.2 (deep-scan): the icon-to-label gap used to be two literal spaces
 * inside the text ("  " + label) — which collapses or doubles depending on
 * the font and never aligned. A real 8dp spacer does.
 */
@Composable
fun QuickActionTile(
    label: String,
    icon: ImageVector,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, maxLines = 1)
    }
}

/**
 * v0.4.2 (deep-scan): shared empty-state block — icon + title + body on a
 * quiet tonal surface, centered. Replaces the bare left-aligned text pairs
 * the Contacts and Encrypt screens used to render.
 */
@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.outline,
            )
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
            Text(
                body,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
    }
}

/** Read-only result block (envelope or plaintext) with copy/clear row. */
@Composable
fun ResultBlock(
    text: String,
    monospace: Boolean,
    copied: Boolean,
    onCopy: () -> Unit,
    onClear: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        androidx.compose.foundation.text.selection.SelectionContainer {
            Text(
                text = text,
                fontSize = if (monospace) 12.sp else 15.sp,
                fontFamily = if (monospace) {
                    androidx.compose.ui.text.font.FontFamily.Monospace
                } else {
                    androidx.compose.ui.text.font.FontFamily.Default
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            )
        }
        if (copied) {
            Text(
                stringResourceCompat(R.string.notice_copied),
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = onCopy, modifier = Modifier.weight(1f)) {
                Text(stringResourceCompat(R.string.action_copy))
            }
            OutlinedButton(onClick = onClear, modifier = Modifier.weight(1f)) {
                Text(stringResourceCompat(R.string.action_clear))
            }
        }
    }
}

/** Passphrase input + submit row shared by every unlock card. */
@Composable
fun PassphraseInput(
    label: String,
    buttonLabel: String,
    busy: Boolean,
    onSubmit: (CharArray) -> Unit,
) {
    var passphrase by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = passphrase,
            onValueChange = { passphrase = it },
            label = { Text(label) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        // v0.4.0: live strength hint while the user types.
        PassphraseStrengthHint(passphrase)
        Button(
            onClick = {
                onSubmit(passphrase.toCharArray())
                passphrase = ""
            },
            enabled = passphrase.isNotEmpty() && !busy,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(buttonLabel) }
    }
}

/**
 * v0.4.0: live strength estimate under a passphrase field. Display only —
 * it never blocks submission (that stays with KeyPolicy on the crypto path);
 * it teaches what a strong shared passphrase looks like while typing.
 */
@Composable
fun PassphraseStrengthHint(passphrase: String) {
    if (passphrase.isEmpty()) return
    val strength = remember(passphrase) { PassphraseAdvisor.strength(passphrase) }
    val bits = remember(passphrase) { PassphraseAdvisor.estimateBits(passphrase) }
    val (labelRes, color) = when (strength) {
        PassphraseAdvisor.Strength.WEAK ->
            R.string.passphrase_strength_weak to MaterialTheme.colorScheme.error
        PassphraseAdvisor.Strength.FAIR ->
            R.string.passphrase_strength_fair to MaterialTheme.colorScheme.tertiary
        PassphraseAdvisor.Strength.STRONG ->
            R.string.passphrase_strength_strong to MaterialTheme.colorScheme.primary
    }
    Text(
        text = stringResourceCompat(
            R.string.passphrase_strength_summary,
            stringResourceCompat(labelRes),
            bits,
        ),
        fontSize = 12.sp,
        color = color,
    )
}

/** Screen title + subtitle header. */
@Composable
fun ScreenHeader(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Text(
            subtitle,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/** Centered busy row ("Decrypting…"). */
@Composable
fun BusyRow(label: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressIndicator(modifier = Modifier.size(20.dp))
        Text(label, fontSize = 14.sp)
    }
}

/**
 * Inline error banner for typed notices.
 *
 * v0.4.2 (deep-scan): now an actual banner — errorContainer background
 * with onErrorContainer text — instead of a bare error-colored line that
 * vanished against similarly colored helper text.
 */
@Composable
fun NoticeBanner(notice: UiNotice?) {
    if (notice == null) return
    val text = notice.arg?.let { arg ->
        stringResourceCompat(notice.res, arg)
    } ?: stringResourceCompat(notice.res)
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
        )
    }
}

/** Thin indeterminate progress line shown while busy. */
@Composable
fun BusyLine(visible: Boolean) {
    if (visible) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

/** stringResource indirection so the private helpers above stay short. */
@Composable
private fun stringResourceCompat(res: Int, vararg args: Any): String =
    androidx.compose.ui.res.stringResource(res, *args)
