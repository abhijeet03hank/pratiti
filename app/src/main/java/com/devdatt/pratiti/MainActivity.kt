package com.devdatt.pratiti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devdatt.pratiti.core.navigation.AppNavigation
import com.devdatt.pratiti.ui.theme.PratitiTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single-activity host for the Compose UI.
 * `@AndroidEntryPoint` lets Hilt inject dependencies into this activity and its ViewModels.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PratitiTheme {
                AppNavigation()
            }
        }
    }
}
