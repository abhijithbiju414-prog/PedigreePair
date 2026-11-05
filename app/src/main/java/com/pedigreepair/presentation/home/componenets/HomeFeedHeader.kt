package com.pedigreepair.presentation.home.componenets


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.home.FeedVieLayoutMode


@Composable
fun HomeFeedHeader(
    dogCount: Int,
    layoutMode: FeedVieLayoutMode,
    onToggleLayoutMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Feed title & count
        Text(
            text = "$dogCount Matches Found",
            style = PedigreeTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = PedigreeTheme.colors.textPrimary
        )

        // Single vs Double Column Toggle Pill
        val toggleShape = RoundedCornerShape(12.dp)
        Box(
            modifier = Modifier
                .clip(toggleShape)
                .background(PedigreeTheme.colors.surface)
                .border(
                    width = 1.dp,
                    color = PedigreeTheme.colors.lineDivider,
                    shape = toggleShape
                )
                .clickable(onClick = onToggleLayoutMode)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = if (layoutMode == FeedVieLayoutMode.SINGLE_COLUMN) {
                        Icons.Outlined.GridView
                    } else {
                        Icons.Outlined.ViewAgenda
                    },
                    contentDescription = "Switch layout mode",
                    tint = PedigreeTheme.colors.primary,
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = if (layoutMode == FeedVieLayoutMode.SINGLE_COLUMN) "2-Grid" else "1-List",
                    style = PedigreeTheme.typography.labelSmall,
                    color = PedigreeTheme.colors.textPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}