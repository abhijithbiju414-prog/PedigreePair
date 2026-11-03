package com.pedigreepair.domain.usecase.theme

import com.pedigreepair.domain.model.theme.ThemePalette
import com.pedigreepair.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class ObserveActiveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke(): Flow<ThemePalette> {
        return themeRepository.observeActiveTheme()
    }
}