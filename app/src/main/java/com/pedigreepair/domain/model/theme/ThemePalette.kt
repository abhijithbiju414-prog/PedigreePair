package com.pedigreepair.domain.model.theme

data class ThemePalette(
    val primaryColorHex: Long,
    val secondaryColorHex: Long,
    val lineDividerColorHex: Long,
    val iconTintColorHex: Long,
    val isMagicThemeActive: Boolean
) {
    companion object {
        val Default = ThemePalette(
            primaryColorHex = AppColorTokens.DEFAULT_PRIMARY,
            secondaryColorHex = AppColorTokens.DEFAULT_SECONDARY,
            lineDividerColorHex = AppColorTokens.DEFAULT_LINE_DIVIDER,
            iconTintColorHex = AppColorTokens.DEFAULT_ICON_TINT,
            isMagicThemeActive = false
        )
    }
}