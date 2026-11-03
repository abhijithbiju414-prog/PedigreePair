package com.pedigreepair.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenRoute {
    // Onboarding routes
    @Serializable
    data object Splash : ScreenRoute

    @Serializable
    data object Tutorial : ScreenRoute

    @Serializable
    data object PrivacyPolicy : ScreenRoute

    //Main App
    @Serializable
    data object Home : ScreenRoute

    @Serializable
    data object AddDog : ScreenRoute

    @Serializable
    data class DogDetail(val dogId: String)

}