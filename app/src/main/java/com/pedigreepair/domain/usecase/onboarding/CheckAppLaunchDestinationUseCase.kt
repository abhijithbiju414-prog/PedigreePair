package com.pedigreepair.domain.usecase.onboarding

import com.pedigreepair.domain.model.AppLaunchDestination
import com.pedigreepair.domain.repository.OnboardingRepository

class CheckAppLaunchDestinationUseCase(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(): AppLaunchDestination {
        return onboardingRepository.getInitialLaunchDestination()
    }
}