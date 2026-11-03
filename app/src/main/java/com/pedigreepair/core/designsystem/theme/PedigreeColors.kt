package com.pedigreepair.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class PedigreeColors(
    val primary: Color,
    val secondary: Color,
    val lineDivider: Color,
    val iconTint: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val isMagicTheme: Boolean
)
