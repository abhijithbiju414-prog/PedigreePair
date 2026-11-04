package com.pedigreepair.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.AppLaunchDestination
import com.pedigreepair.presentation.splash.contract.SplashUiEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToDestination: (AppLaunchDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    // Collect one-off side effects safely
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SplashUiEffect.NavigateTo -> onNavigateToDestination(effect.destination)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PedigreeTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .border(
                        width = 2.dp,
                        color = PedigreeTheme.colors.lineDivider,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "PedigreePair Brand Icon",
                    tint = PedigreeTheme.colors.iconTint,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "PedigreePair",
                style = MaterialTheme.typography.headlineLarge,
                color = PedigreeTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.width(48.dp),
                thickness = 2.dp,
                color = PedigreeTheme.colors.lineDivider
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Verified Canine Matrimony",
                style = MaterialTheme.typography.bodyMedium,
                color = PedigreeTheme.colors.textSecondary
            )
        }
    }
}