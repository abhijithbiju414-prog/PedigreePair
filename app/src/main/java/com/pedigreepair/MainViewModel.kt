package com.pedigreepair

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedigreepair.domain.model.theme.ThemePalette
import com.pedigreepair.domain.usecase.theme.ObserveActiveThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    observeActiveThemeUseCase: ObserveActiveThemeUseCase
) : ViewModel() {

    val activeThemePalette: StateFlow<ThemePalette> = observeActiveThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemePalette.Default
        )
}