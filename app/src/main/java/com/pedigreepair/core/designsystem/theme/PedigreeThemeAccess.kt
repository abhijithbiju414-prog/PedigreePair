package com.pedigreepair.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Internal CompositionLocal. Marked internal so outside features cannot modify it.
 */
internal val LocalPedigreeColors = staticCompositionLocalOf {
    PedigreeColors(
        primary = DefaultPrimary,
        secondary = DefaultSecondary,
        lineDivider = DefaultLineDivider,
        iconTint = DefaultIconTint,
        background = BackgroundLight,
        surface = SurfaceLight,
        textPrimary = TextPrimaryLight,
        textSecondary = TextPrimaryDark,
        isMagicTheme = false
    )
}

/**
 * Public accessor for UI screens and design system components.
 * Example usage: PedigreeTheme.colors.lineDivider
 */
object PedigreeTheme {
    val colors: PedigreeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPedigreeColors.current
}