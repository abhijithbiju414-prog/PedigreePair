package com.pedigreepair.presentation.home.componenets


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pedigreepair.core.designsystem.theme.PedigreeTheme

@Composable
fun HomeTopBar(
    unreadNotificationsCount: Int,
    onProfileClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onNotificationClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: User Profile Avatar
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .border(
                    width = 1.5.dp,
                    color = PedigreeTheme.colors.primary,
                    shape = CircleShape
                )
                .clickable(onClick = onProfileClicked),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Open Profile",
                tint = PedigreeTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
        }

        // Center: App Title Branding
        Text(
            text = "PedigreePair",
            style = PedigreeTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = PedigreeTheme.colors.textPrimary
        )

        // Right Actions: Filter & Notification with unread badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Filter Button
            IconButton(onClick = onFilterClicked) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Open Filter",
                    tint = PedigreeTheme.colors.textPrimary
                )
            }

            // Notification Bell with Badge
            IconButton(onClick = onNotificationClicked) {
                BadgedBox(
                    badge = {
                        if (unreadNotificationsCount > 0) {
                            Badge(
                                containerColor = PedigreeTheme.colors.primary,
                                contentColor = PedigreeTheme.colors.onPrimary
                            ) {
                                Text(
                                    text = if (unreadNotificationsCount > 9) "9+" else unreadNotificationsCount.toString(),
                                    style = PedigreeTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = PedigreeTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}