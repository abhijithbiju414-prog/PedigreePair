package com.pedigreepair.presentation.navigation

import android.media.metrics.Event
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

    @Serializable
    data object Profile : ScreenRoute

    @Serializable
    data object FilterSheet : ScreenRoute

    @Serializable
    data object Notifications : ScreenRoute

    @Serializable
    data class EventDetails(val eventId: String) : ScreenRoute

}