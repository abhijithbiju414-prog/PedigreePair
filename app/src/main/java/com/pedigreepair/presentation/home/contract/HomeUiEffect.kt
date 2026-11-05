package com.pedigreepair.presentation.home.contract

sealed interface HomeUiEffect {
    data class NavigateToDogDetails(val dogId: String) : HomeUiEffect
    data class NavigateToEventDetails(val eventId: String) : HomeUiEffect
    data object OpenProfile : HomeUiEffect
    data object OpenFilterSheet : HomeUiEffect
    data object OpenNotifications : HomeUiEffect
    data class ShowToast(val message: String) : HomeUiEffect
}