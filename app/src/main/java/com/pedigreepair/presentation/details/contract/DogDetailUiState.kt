package com.pedigreepair.presentation.details.contract

import com.pedigreepair.domain.model.home.CanineMatchProfile

data class DogDetailUiState(
    val isLoading: Boolean = true,
    val dog: CanineMatchProfile? = null,
    val isContactInitiated: Boolean = false,
    val errorMessage: String? = null
)