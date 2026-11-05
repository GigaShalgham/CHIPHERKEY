package org.cipherkey.app.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.cipherkey.app.R
import org.cipherkey.core.common.ContactId
import org.cipherkey.data.repository.Contact

/**
 * Contact picker dropdown (v0.3.0) shared by the in-app Encrypt tab and any
 * flow that must choose a recipient. Shows lock state per contact.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactPicker(
    contacts: List<Contact>,
    selectedContactId: ContactId?,
    unlocked: Set<ContactId>,
    onContactSelected: (ContactId) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selected = contacts.firstOrNull { it.id == selectedContactId }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            value = selected?.displayName ?: stringResource(R.string.contact_choose),
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.encrypt_contact_label)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            contacts.forEach { contact ->
                DropdownMenuItem(
                    text = {
                        Text(
                            (if (contact.id in unlocked) {
                                stringResource(R.string.contact_unlocked) + "  "
                            } else {
                                stringResource(R.string.contact_locked) + "  "
                            }) + contact.displayName,
                        )
                    },
                    onClick = {
                        onContactSelected(contact.id)
                        expanded = false
                    },
                )
            }
        }
    }
}
