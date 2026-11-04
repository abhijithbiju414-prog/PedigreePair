package com.pedigreepair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.pedigreepair.core.designsystem.theme.PedigreePairTheme
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.presentation.navigation.PedigreeNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themePalette = mainViewModel.activeThemePalette.collectAsStateWithLifecycle().value
            val navController = rememberNavController()

            PedigreePairTheme(activePalette = themePalette) {
                PedigreeNavGraph(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PedigreeTheme.colors.background)
                )
            }
        }
    }
}