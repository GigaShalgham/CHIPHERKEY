package org.cipherkey.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.ui.CipherKeyApp
import org.cipherkey.app.ui.theme.CipherKeyTheme

/**
 * Single-activity host for all CipherKey management UI (status/onboarding,
 * contacts and key sharing, security settings).
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val contactsViewModel: ContactsViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CipherKeyTheme {
                CipherKeyApp(
                    mainViewModel = mainViewModel,
                    contactsViewModel = contactsViewModel,
                    settingsViewModel = settingsViewModel,
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.refresh()
    }
}
