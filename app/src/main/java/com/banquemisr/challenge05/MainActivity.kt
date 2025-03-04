package com.banquemisr.challenge05

import BMTheme
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import com.banquemisr.challenge05.navigation.RootNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMTheme(isDarkTheme = false) {
                RootNavigation()
            }
        }
    }
}
