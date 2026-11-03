package com.pedigreepair.domain.usecase.onboarding

import com.pedigreepair.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            onboardingRepository.markTutorialCompleted()
            onboardingRepository.recordPolicyAcceptance()
        }
    }
}