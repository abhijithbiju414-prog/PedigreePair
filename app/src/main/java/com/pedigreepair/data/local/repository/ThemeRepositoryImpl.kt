package com.pedigreepair.data.local.repository

import com.pedigreepair.data.local.preferenece.PedigreePreferences
import com.pedigreepair.domain.model.theme.ThemePalette
import com.pedigreepair.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeRepositoryImpl @Inject constructor(
    private val preferences: PedigreePreferences
) : ThemeRepository {
    override fun observeActiveTheme(): Flow<ThemePalette> {
        return combine(
            preferences.isMagicThemeActive,
            preferences.magicPrimaryHex,
            preferences.magicSecondaryHex,
            preferences.magicLineDividerHex,
            preferences.magicIconTintHex
        ) { isMagicActive, primary, secondary, lineDivider, iconInt ->
            if (isMagicActive && primary != null && secondary != null && lineDivider != null && iconInt != null) {
                ThemePalette(
                    primaryColorHex = primary,
                    secondaryColorHex = secondary,
                    lineDividerColorHex = lineDivider,
                    iconTintColorHex = iconInt,
                    isMagicThemeActive = true
                )
            } else {
                ThemePalette.Default
            }

        }
    }

    override suspend fun applyMagicTheme(palette: ThemePalette): Result<Unit> {
        return runCatching {
            preferences.saveMagicThemePalette(
                primaryHex = palette.primaryColorHex,
                secondaryHex = palette.secondaryColorHex,
                lineDividerHex = palette.lineDividerColorHex,
                iconTintHex = palette.iconTintColorHex
            )
        }
    }

    override suspend fun resetToDefaultTheme(): Result<Unit> {
        return runCatching {
            preferences.clearMagicThemePalette()
        }
    }
}