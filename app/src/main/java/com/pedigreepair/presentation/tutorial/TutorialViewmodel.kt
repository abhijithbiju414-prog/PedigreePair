package com.pedigreepair.presentation.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedigreepair.domain.usecase.tutorial.CompleteTutorialUseCase
import com.pedigreepair.domain.usecase.tutorial.GetTutorialStepsUseCase
import com.pedigreepair.presentation.tutorial.contract.TutorialUiEffect
import com.pedigreepair.presentation.tutorial.contract.TutorialUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialViewmodel @Inject constructor(
    private val getTutorialStepsUseCase: GetTutorialStepsUseCase,
    private val completeTutorialUseCase: CompleteTutorialUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TutorialUiState())
    val uiState: StateFlow<TutorialUiState> = _uiState.asStateFlow()

    private val _effect = Channel<TutorialUiEffect>(Channel.BUFFERED)
    val effect: Flow<TutorialUiEffect> = _effect.receiveAsFlow()

    init {
        loadTutorialSteps()
    }

    private fun loadTutorialSteps() {
        val steps = getTutorialStepsUseCase()
        _uiState.update { currentState ->
            currentState.copy(
                steps = steps,
                currentStepIndex = 0,
                isLoading = false
            )
        }
    }

    fun onNextClicked() {
        val currentState = _uiState.value
        if (currentState.isLoading) {
            completeWalkthrough()
        } else {
            _uiState.update { state ->
                state.copy(currentStepIndex = state.currentStepIndex + 1)
            }
        }
    }

    fun onPreviousClicked() {
        val currentIndex = _uiState.value.currentStepIndex
        if (currentIndex > 0) {
            _uiState.update { state ->
                state.copy(currentStepIndex = currentIndex - 1)
            }
        }
    }

    fun onStepSelected(index: Int) {
        val steps = _uiState.value.steps
        if (index in steps.indices) {
            _uiState.update { state ->
                state.copy(currentStepIndex = index)
            }
        }
    }

    fun onSkipClicked() {
        completeWalkthrough()
    }

    private fun completeWalkthrough() {
        viewModelScope.launch {
            completeTutorialUseCase()
            _effect.send(TutorialUiEffect.NavigateToPrivacyPolicy)
        }
    }


}