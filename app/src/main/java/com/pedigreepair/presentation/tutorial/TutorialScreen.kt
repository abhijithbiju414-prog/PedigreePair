package com.pedigreepair.presentation.tutorial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.TutorialStep
import com.pedigreepair.presentation.tutorial.contract.TutorialUiEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TutorialScreen(
    viewModel: TutorialViewmodel,
    onNavigateToPrivacyPolicy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is TutorialUiEffect.NavigateToPrivacyPolicy -> {
                    onNavigateToPrivacyPolicy()
                }
            }
        }
    }

    if (uiState.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(PedigreeTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = PedigreeTheme.colors.primary)
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PedigreeTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar: Skip Action
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            AnimatedVisibility(
                visible = !uiState.isLastStep,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TextButton(onClick = viewModel::onSkipClicked) {
                    Text(
                        text = "Skip",
                        style = PedigreeTheme.typography.labelLarge,
                        color = PedigreeTheme.colors.onBackground.copy(alpha = 0.6f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Center Content: Current Step
        val currentStep = uiState.steps.getOrNull(uiState.currentStepIndex)
        if (currentStep != null) {
            TutorialStepContent(step = currentStep)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Indicator Dots
        TutorialStepIndicators(
            stepCount = uiState.steps.size,
            currentIndex = uiState.currentStepIndex,
            onStepClicked = viewModel::onStepSelected
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Bottom Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.currentStepIndex > 0) {
                TextButton(onClick = viewModel::onPreviousClicked) {
                    Text(
                        text = "Back",
                        style = PedigreeTheme.typography.labelLarge,
                        color = PedigreeTheme.colors.primary
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }

            Button(
                onClick = viewModel::onNextClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PedigreeTheme.colors.primary,
                    contentColor = PedigreeTheme.colors.onPrimary
                ),
                shape = PedigreeTheme.shapes.medium
            ) {
                Text(
                    text = if (uiState.isLastStep) "Get Started" else "Next",
                    style = PedigreeTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun TutorialStepContent(
    step: TutorialStep,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for illustration container
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(PedigreeTheme.shapes.large)
                .background(PedigreeTheme.colors.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = step.illustrationResName,
                style = PedigreeTheme.typography.bodySmall,
                color = PedigreeTheme.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = step.title,
            style = PedigreeTheme.typography.headlineMedium,
            color = PedigreeTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = step.description,
            style = PedigreeTheme.typography.bodyMedium,
            color = PedigreeTheme.colors.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TutorialStepIndicators(
    stepCount: Int,
    currentIndex: Int,
    onStepClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(stepCount) { index ->
            val isSelected = index == currentIndex
            Box(
                modifier = Modifier
                    .size(if (isSelected) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) PedigreeTheme.colors.primary
                        else PedigreeTheme.colors.lineDivider.copy(alpha = 0.4f)
                    )
                    .clickable { onStepClicked(index) }
            )
        }
    }
}