package org.cipherkey.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import org.cipherkey.app.ui.theme.CipherKeyTheme

/**
 * Single-activity host for all CipherKey management UI.
 *
 * NOTE: navigation scaffold (onboarding, dashboard, contacts, settings) is
 * implemented in later phases of the initial engineering session; this
 * activity is intentionally minimal until then.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CipherKeyTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "CipherKey",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }
        }
    }
}
