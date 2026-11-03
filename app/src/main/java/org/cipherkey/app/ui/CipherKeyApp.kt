package org.cipherkey.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.cipherkey.app.ContactsViewModel
import org.cipherkey.app.MainViewModel
import org.cipherkey.app.R
import org.cipherkey.app.SettingsViewModel

private data class Destination(val route: String, val labelRes: Int, val glyph: String)

private val destinations = listOf(
    Destination("home", R.string.nav_dashboard, "🛡"),
    Destination("contacts", R.string.nav_contacts, "👥"),
    Destination("settings", R.string.nav_settings, "⚙"),
)

@Composable
fun CipherKeyApp(
    mainViewModel: MainViewModel,
    contactsViewModel: ContactsViewModel,
    settingsViewModel: SettingsViewModel,
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                destinations.forEach { destination ->
                    val selected = backStackEntry?.destination?.hierarchy
                        ?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Text(destination.glyph, fontSize = 18.sp) },
                        label = { Text(stringResource(destination.labelRes), fontSize = 12.sp) },
                    )
                }
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding),
        ) {
            composable("home") { HomeScreen(mainViewModel) }
            composable("contacts") { ContactsScreen(contactsViewModel) }
            composable("settings") { SettingsScreen(settingsViewModel, contactsViewModel) }
        }
    }
}
