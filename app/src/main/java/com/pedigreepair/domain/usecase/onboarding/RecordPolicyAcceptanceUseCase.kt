package com.pedigreepair.domain.usecase.onboarding

import com.pedigreepair.domain.repository.OnboardingRepository

class RecordPolicyAcceptanceUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return runCatching {
            onboardingRepository.recordPolicyAcceptance()
        }
    }
}