package com.pedigreepair.presentation.splash

import androidx.compose.foundation.layout.Spacer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.pedigreepair.core.designsystem.theme.PedigreeMotion
import com.pedigreepair.domain.usecase.onboarding.CheckAppLaunchDestinationUseCase
import com.pedigreepair.presentation.splash.contract.SplashUiEffect
import com.pedigreepair.presentation.splash.contract.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkAppLaunchDestinationUseCase: CheckAppLaunchDestinationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    private val _effect = Channel<SplashUiEffect>(Channel.BUFFERED)
    val effect: Flow<SplashUiEffect> = _effect.receiveAsFlow()

    init {
        resolveLaunchRoute()
    }

    private fun resolveLaunchRoute() {
        viewModelScope.launch {
            delay(PedigreeMotion.DURATION_LONG_MS.toLong())
            val destination = checkAppLaunchDestinationUseCase()
            _effect.send(SplashUiEffect.NavigateTo(destination))
        }
    }
}