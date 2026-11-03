package com.pedigreepair.domain.model

sealed interface AppLaunchDestination {
    object Tutorial : AppLaunchDestination
    object PrivacyPolicy : AppLaunchDestination
    object Home : AppLaunchDestination
}

