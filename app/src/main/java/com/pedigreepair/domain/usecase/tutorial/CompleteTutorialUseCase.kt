package com.pedigreepair.domain.usecase.tutorial

import com.pedigreepair.domain.repository.OnboardingRepository

class CompleteTutorialUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() {
        onboardingRepository.markTutorialCompleted()
    }
}