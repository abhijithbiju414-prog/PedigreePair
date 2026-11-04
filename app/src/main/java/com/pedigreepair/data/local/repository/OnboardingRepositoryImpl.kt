package com.pedigreepair.data.local.repository

import com.pedigreepair.data.local.preferenece.PedigreePreferences
import com.pedigreepair.domain.model.AppLaunchDestination
import com.pedigreepair.domain.model.TutorialStep
import com.pedigreepair.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val preferences: PedigreePreferences
) : OnboardingRepository {
    override suspend fun getInitialLaunchDestination(): AppLaunchDestination {
        val isTutorialCompleted = preferences.isTutorialCompleted.first()
        val isPolicyAccepted = preferences.isPolicyAccepted.first()

        return when {
            !isTutorialCompleted -> AppLaunchDestination.Tutorial
            !isPolicyAccepted -> AppLaunchDestination.PrivacyPolicy
            else -> AppLaunchDestination.Home
        }
    }

    override fun getTutorialSteps(): List<TutorialStep> {
        return listOf(
            TutorialStep(
                id = 1,
                title = "Verified Pedigree Lineage",
                description = "Discover certified canine mates with validated lineage and kennel club records.",
                illustrationResName = "ic_tutorial_pedigree"
            ),
            TutorialStep(
                id = 2,
                title = "Manage Multi-Dog Kennels",
                description = "Register, organize, and monitor multiple canine profiles from one owner dashboard.",
                illustrationResName = "ic_tutorial_multi_dog"
            ),
            TutorialStep(
                id = 3,
                title = "Adaptive Coat Theme",
                description = "Upload your dog's photograph and watch app borders, icons, and lines adjust to their coat.",
                illustrationResName = "ic_tutorial_magic_theme"
            )
        )
    }

    override suspend fun markTutorialCompleted() {
        preferences.setTutorialCompleted(true)
    }

    override suspend fun recordPolicyAcceptance() {
        preferences.setPolicyAccepted(true)
    }

    override fun observePolicyAcceptanceStatus(): Flow<Boolean> {
        return preferences.isPolicyAccepted
    }

}