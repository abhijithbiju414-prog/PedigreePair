package com.pedigreepair.presentation.home.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.pedigreepair.core.designsystem.theme.PedigreeTheme

@Composable
fun HomeSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(24.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .height(50.dp)
            .clip(shape)
            .background(PedigreeTheme.colors.surface)
            .border(
                width = 1.dp,
                color = PedigreeTheme.colors.lineDivider,
                shape = shape
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Search Icon
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = PedigreeTheme.colors.textSecondary,
                modifier = Modifier.size(20.dp)
            )

            // Input Field & Placeholder
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    Text(
                        text = "Search by dog, breed, kennel, or city...",
                        style = PedigreeTheme.typography.bodyMedium,
                        color = PedigreeTheme.colors.textSecondary
                    )
                }

                BasicTextField(
                    value = query,
                    onValueChange = onQueryChanged,
                    singleLine = true,
                    textStyle = PedigreeTheme.typography.bodyMedium.copy(
                        color = PedigreeTheme.colors.textPrimary
                    ),
                    cursorBrush = SolidColor(PedigreeTheme.colors.primary),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Trailing Clear Button
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = onClearQuery,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Clear search",
                        tint = PedigreeTheme.colors.textSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
