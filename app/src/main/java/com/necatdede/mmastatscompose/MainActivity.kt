package com.necatdede.mmastatscompose

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.necatdede.mmastatscompose.presentation.screens.home.HomeScreen
import com.necatdede.mmastatscompose.presentation.screens.settings.SettingsScreen
import com.necatdede.mmastatscompose.ui.theme.MMAStatsComposeTheme
import com.necatdede.mmastatscompose.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context) {
        val savedLanguage = LocaleManager.getSavedLanguage(newBase)
        val context = LocaleManager.setLocale(newBase, savedLanguage)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MMAStatsComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onTabSelected = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(currentRoute: String?, onTabSelected: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { onTabSelected("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
            label = { Text(stringResource(R.string.home)) }
        )
        NavigationBarItem(
            selected = currentRoute == "settings",
            onClick = { onTabSelected("settings") },
            icon = { Icon(Icons.Default.Settings, contentDescription = stringResource(R.string.settings)) },
            label = { Text(stringResource(R.string.settings)) }
        )
    }
}

@HiltAndroidApp
class MMAStatsApplication : Application(){
        override fun onCreate() {
            super.onCreate()
            LocaleManager.setLocale(this, LocaleManager.getSavedLanguage(this))
        }
}
