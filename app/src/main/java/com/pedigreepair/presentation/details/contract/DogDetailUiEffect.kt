package com.pedigreepair.presentation.details.contract

sealed interface DogDetailUiEffect {
    data object NavigateBack : DogDetailUiEffect
    data class ShowToast(val message: String) : DogDetailUiEffect
}