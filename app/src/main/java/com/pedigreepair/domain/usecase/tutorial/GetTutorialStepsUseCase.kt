package com.pedigreepair.domain.usecase.tutorial

import com.pedigreepair.domain.model.TutorialStep
import com.pedigreepair.domain.repository.OnboardingRepository

class GetTutorialStepsUseCase(
    private val onboardingUseCase: OnboardingRepository
    ) {
    operator fun invoke(): List<TutorialStep> {
        return onboardingUseCase.getTutorialSteps()
    }
}