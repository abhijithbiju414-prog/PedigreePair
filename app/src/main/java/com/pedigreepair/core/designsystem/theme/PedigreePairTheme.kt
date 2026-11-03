package com.pedigreepair.core.designsystem.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.pedigreepair.domain.model.theme.ThemePalette


@Composable
fun PedigreePairTheme(
    activePalette: ThemePalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val targetPrimary = Color(activePalette.primaryColorHex)
    val targetSecondary = Color(activePalette.secondaryColorHex)
    val targetLineDivider = Color(activePalette.lineDividerColorHex)
    val targetIconTint = Color(activePalette.iconTintColorHex)

    val animatedPrimary = animateColorAsState(
        targetValue = targetPrimary,
        animationSpec = tween(durationMillis = PedigreeMotion.DURATION_THEME_TRANSITION_MS),
        label = "PrimaryColorAnimation"
    ).value

    val animatedSecondary = animateColorAsState(
        targetValue = targetSecondary,
        animationSpec = tween(durationMillis = PedigreeMotion.DURATION_THEME_TRANSITION_MS),
        label = "SecondaryColorAnimation"
    ).value

    val animatedLineDivider = animateColorAsState(
        targetValue = targetLineDivider,
        animationSpec = tween(durationMillis = PedigreeMotion.DURATION_THEME_TRANSITION_MS),
        label = "LineDividerColorAnimation"
    ).value

    val animatedIconTint = animateColorAsState(
        targetValue = targetIconTint,
        animationSpec = tween(durationMillis = PedigreeMotion.DURATION_THEME_TRANSITION_MS),
        label = "IconTintColorAnimation"
    ).value

    val resolvedColors = PedigreeColors(
        primary = animatedPrimary,
        secondary = animatedSecondary,
        lineDivider = animatedLineDivider,
        iconTint = animatedIconTint,
        background = if (darkTheme) BackgroundDark else BackgroundLight,
        surface = if (darkTheme) SurfaceDark else SurfaceLight,
        textPrimary = if (darkTheme) TextPrimaryDark else TextPrimaryLight,
        textSecondary = if (darkTheme) TextSecondaryDark else TextSecondaryLight,
        isMagicTheme = activePalette.isMagicThemeActive
    )

    CompositionLocalProvider(
        LocalPedigreeColors provides resolvedColors,
    ) {
        MaterialTheme(
            typography = PedigreeTypography,
            shapes = PedigreeShapes,
            content = content
        )
    }
}