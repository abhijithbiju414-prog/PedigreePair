package com.pedigreepair.domain.repository

import com.pedigreepair.domain.model.AppLaunchDestination
import com.pedigreepair.domain.model.TutorialStep
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    /**
     * Reads the persistent flags to determine which screen the app must launch first.
     */
    suspend fun getInitialLaunchDestination(): AppLaunchDestination

    /**
     * Provides the static or remote configuration of walkthrough steps.
     */
    fun getTutorialSteps(): List<TutorialStep>

    /**
     * Marks the tutorial walkthrough as viewed by the user.
     */
    suspend fun markTutorialCompleted()

    /**
     * Persists user consent to the privacy policy.
     */
    suspend fun recordPolicyAcceptance()

    /**
     * Observes whether the user has accepted the privacy policy.
     */
    fun observePolicyAcceptanceStatus(): Flow<Boolean>
}