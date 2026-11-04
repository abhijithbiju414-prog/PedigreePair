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
){
    // Convenience aliases matching common UI token naming
    val onBackground: Color get() = textPrimary
    val onSurface: Color get() = textPrimary
    val onPrimary: Color get() = Color.White
}
