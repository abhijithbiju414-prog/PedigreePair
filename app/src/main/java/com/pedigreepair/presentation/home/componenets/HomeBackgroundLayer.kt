package com.pedigreepair.presentation.home.componenets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.pedigreepair.core.designsystem.theme.PedigreeTheme

@Composable
fun HomeBackgroundLayer(
    backgroundPhotoUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PedigreeTheme.colors.background)
    ) {
        // Optional Dog Photo Layer
        if (!backgroundPhotoUrl.isNullOrBlank()) {
            AsyncImage(
                model = backgroundPhotoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.18f
            )
        }

        // Gradient & Scrim Overlay to ensure contrast with foreground content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            PedigreeTheme.colors.primary.copy(alpha = 0.12f),
                            PedigreeTheme.colors.background.copy(alpha = 0.65f),
                            PedigreeTheme.colors.background
                        )
                    )
                )
        )
    }
}