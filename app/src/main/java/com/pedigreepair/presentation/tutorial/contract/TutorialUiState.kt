package com.pedigreepair.presentation.tutorial.contract

import com.pedigreepair.domain.model.TutorialStep

data class TutorialUiState(
    val steps: List<TutorialStep> = emptyList(),
    val currentStepIndex: Int = 0,
    val isLoading: Boolean = true
) {
    val isLastStep: Boolean
        get() = steps.isNotEmpty() && currentStepIndex == steps.lastIndex
}