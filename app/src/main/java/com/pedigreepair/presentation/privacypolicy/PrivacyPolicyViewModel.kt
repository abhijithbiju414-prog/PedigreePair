package com.pedigreepair.presentation.privacypolicy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedigreepair.domain.usecase.onboarding.RecordPolicyAcceptanceUseCase
import com.pedigreepair.presentation.privacypolicy.contract.PrivacyPolicyUiEffect
import com.pedigreepair.presentation.privacypolicy.contract.PrivacyPolicyUiState
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
class PrivacyPolicyViewModel @Inject constructor(
    private val recordPolicyAcceptanceUseCase: RecordPolicyAcceptanceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PrivacyPolicyUiState())
    val uiState: StateFlow<PrivacyPolicyUiState> = _uiState.asStateFlow()

    private val _effect = Channel<PrivacyPolicyUiEffect>(Channel.BUFFERED)
    val effect: Flow<PrivacyPolicyUiEffect> = _effect.receiveAsFlow()


    fun onAcceptAndContinueClicked() {
        if (_uiState.value.isSubmitting) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isSubmitting = true, errorMessage = null)
            }
            val result = recordPolicyAcceptanceUseCase()
            result.fold(
                onSuccess = {
                    _uiState.update { it.copy(isSubmitting = false) }
                },
                onFailure = { throwable ->
                    _uiState.update {
                        it.copy(
                            isSubmitting = false,
                            errorMessage = throwable.localizedMessage
                                ?: "Failed to record acceptance. Please try again."
                        )
                    }
                    _effect.send(
                        PrivacyPolicyUiEffect.ShowToast(
                            message = throwable.localizedMessage ?: "Failed to save agreement"
                        )
                    )
                }
            )
        }
    }
}