package com.pedigreepair.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.pedigreepair.domain.usecase.details.GetDogDetailsUseCase
import com.pedigreepair.domain.usecase.home.ToggleFavoriteUseCase
import com.pedigreepair.presentation.details.contract.DogDetailUiEffect
import com.pedigreepair.presentation.details.contract.DogDetailUiState
import com.pedigreepair.presentation.navigation.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDogDetailsUseCase: GetDogDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val route: ScreenRoute.DogDetail = savedStateHandle.toRoute()
    val dogId: String = route.dogId

    private val _uiState = MutableStateFlow(DogDetailUiState())
    val uiState: StateFlow<DogDetailUiState> = _uiState.asStateFlow()

    private val _effect = Channel<DogDetailUiEffect>(Channel.BUFFERED)
    val effect: Flow<DogDetailUiEffect> = _effect.receiveAsFlow()

    init {
        loadDogDetails()
    }

    private fun loadDogDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getDogDetailsUseCase(dogId).collectLatest { dogProfile ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        dog = dogProfile,
                        errorMessage = if (dogProfile == null) "Profile not found" else null
                    )
                }
            }
        }
    }

    fun onToggleFavorite() {
        viewModelScope.launch {
            toggleFavoriteUseCase(dogId)
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _effect.send(DogDetailUiEffect.NavigateBack)
        }
    }

    fun onInitiateContactClicked() {
        viewModelScope.launch {
            _uiState.update { it.copy(isContactInitiated = true) }
            _effect.send(DogDetailUiEffect.ShowToast("Inquiry sent to breeder!"))
        }
    }
}