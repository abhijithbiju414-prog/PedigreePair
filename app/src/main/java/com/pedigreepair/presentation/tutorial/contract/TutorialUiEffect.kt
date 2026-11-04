package com.pedigreepair.presentation.tutorial.contract

/**
 * One-off side effects for the Tutorial screen.
 * Consumed exactly once via a Channel/Flow.
 */
sealed interface TutorialUiEffect {
    data object NavigateToPrivacyPolicy : TutorialUiEffect
}