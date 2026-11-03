package com.pedigreepair.domain.usecase.theme

import com.pedigreepair.domain.model.theme.ThemePalette
import com.pedigreepair.domain.repository.ThemeRepository

class ApplyDogMagicThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(extractedDominantColorHex: Long): Result<Unit> {
        val alphaMask = 0x33000000L
        val baseColorOnly = extractedDominantColorHex and 0x00FFFFFFL
        val derivedLineColorHex = alphaMask or baseColorOnly

        val magicPalette = ThemePalette(
            primaryColorHex = extractedDominantColorHex,
            secondaryColorHex = extractedDominantColorHex,
            lineDividerColorHex = derivedLineColorHex,
            iconTintColorHex = extractedDominantColorHex,
            isMagicThemeActive = true
        )
        return themeRepository.applyMagicTheme(magicPalette)
    }
}