package com.pedigreepair.presentation.splash.contract

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data object BrandVisible : SplashUiState
}