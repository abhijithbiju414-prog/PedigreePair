package com.pedigreepair.domain.repository

import com.pedigreepair.domain.model.theme.ThemePalette
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    /**
     * Observes the currently active theme palette across the entire application lifecycle.
     * Emits whenever the user toggles or changes the dog-derived magic theme.
     */
    fun observeActiveTheme(): Flow<ThemePalette>

    /**
     * Persists and applies a custom magic theme derived from an analyzed dog photo.
     */
    suspend fun applyMagicTheme(palette: ThemePalette): Result<Unit>

    /**
     * Persists and applies a custom magic theme derived from an analyzed dog photo.
     */
    suspend fun resetToDefaultTheme(): Result<Unit>
}