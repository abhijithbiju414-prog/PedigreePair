package com.pedigreepair.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.model.home.FeedVieLayoutMode
import com.pedigreepair.domain.usecase.home.GetHomeFeedUseCase
import com.pedigreepair.domain.usecase.home.ToggleFavoriteUseCase
import com.pedigreepair.presentation.home.contract.HomeUiEffect
import com.pedigreepair.presentation.home.contract.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeFeedUseCase: GetHomeFeedUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _effect = Channel<HomeUiEffect>(Channel.Factory.BUFFERED)
    val effect: Flow<HomeUiEffect> = _effect.receiveAsFlow()

    init {
        loadHomeFeed()
    }

    private fun loadHomeFeed() {
        getHomeFeedUseCase()
            .onEach { bundle ->
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        dogs = bundle.dogs,
                        filteredDogs = filterDogs(bundle.dogs, current.searchQuery),
                        events = bundle.events,
                        backgroundPhotoUrl = bundle.backgroundPhotoUrl,
                        errorMessage = null
                    )
                }
            }.catch { throwable ->
                _uiState.update { current ->
                    current.copy(
                        isLoading = false,
                        errorMessage = throwable.localizedMessage ?: " Failed to load home feed"
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { current ->
            current.copy(
                searchQuery = query,
                filteredDogs = filterDogs(current.dogs, query)
            )
        }
    }

    fun onClearSearch() {
        onSearchQueryChanged("")
    }

    fun onToggleLayoutMode() {
        _uiState.update { current ->
            val nextMode = if (current.layoutMode == FeedVieLayoutMode.SINGLE_COLUMN) {
                FeedVieLayoutMode.DOUBLE_COLUMN
            } else {
                FeedVieLayoutMode.SINGLE_COLUMN
            }
            current.copy(layoutMode = nextMode)
        }
    }

    fun onToggleFavourite(dogId: String) {
        viewModelScope.launch {
            toggleFavoriteUseCase(dogId = dogId).onFailure { throwable ->
                _effect.send(
                    HomeUiEffect.ShowToast(
                        throwable.localizedMessage ?: "Failed to update favourite"
                    )
                )
            }
        }
    }

    fun onDogClicked(dogId: String) {
        viewModelScope.launch {
            _effect.send(HomeUiEffect.NavigateToDogDetails(dogId))
        }
    }

    fun onEventClicked(eventId: String) {
        viewModelScope.launch {
            _effect.send(HomeUiEffect.NavigateToEventDetails(eventId))
        }
    }

    fun onProfileClicked() {
        viewModelScope.launch {
            _effect.send(HomeUiEffect.OpenProfile)
        }
    }

    fun onFilterClicked() {
        viewModelScope.launch {
            _effect.send(HomeUiEffect.OpenFilterSheet)
        }
    }

    fun onNotificationClicked() {
        viewModelScope.launch {
            _effect.send(HomeUiEffect.OpenNotifications)
        }
    }

    private fun filterDogs(
        dogs: List<CanineMatchProfile>,
        query: String
    ): List<CanineMatchProfile> {
        if (query.isBlank()) return dogs
        val trimmed = query.trim()
        return dogs.filter { dog ->
            dog.name.contains(trimmed, ignoreCase = true) ||
                    dog.breed.contains(trimmed, ignoreCase = true) ||
                    dog.kennelName.contains(trimmed, ignoreCase = true) ||
                    dog.locationCity.contains(trimmed, ignoreCase = true)
        }
    }

}