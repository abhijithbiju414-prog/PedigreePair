package com.pedigreepair.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.presentation.navigation.BottomBarDestination
import com.pedigreepair.presentation.navigation.ScreenRoute

@Composable
fun PedigreeBottomBar(
    currentRoute: ScreenRoute?,
    onNavigateToDestination: (ScreenRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(PedigreeTheme.colors.surface)
            .navigationBarsPadding()
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = PedigreeTheme.colors.lineDivider
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarDestination.entries.forEach { destination ->
                val isSelected = currentRoute == destination.route

                BottomBarItem(
                    destination = destination,
                    isSelected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            onNavigateToDestination(destination.route)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    destination: BottomBarDestination,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconTint = animateColorAsState(
        targetValue = if (isSelected) PedigreeTheme.colors.primary else PedigreeTheme.colors.textSecondary,
        animationSpec = tween(durationMillis = 200),
        label = "iconTint"
    )

    val labelColor = animateColorAsState(
        targetValue = if (isSelected) PedigreeTheme.colors.primary else PedigreeTheme.colors.textSecondary,
        animationSpec = tween(durationMillis = 200),
        label = "labelColor"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
            contentDescription = destination.label,
            tint = iconTint.value,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = destination.label,
            style = PedigreeTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = labelColor.value,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}