package com.pedigreepair.presentation.splash.contract

import com.pedigreepair.domain.model.AppLaunchDestination

sealed interface SplashUiEffect {
    data class NavigateTo(val destination: AppLaunchDestination) : SplashUiEffect
}