package com.pedigreepair.presentation.home.componenets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.Gender
import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.model.home.PedigreeCertificationTier

@Composable
fun SingleColumnDogCard(
    dog: CanineMatchProfile,
    onCardClicked: () -> Unit,
    onFavoriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClicked),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PedigreeTheme.colors.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Photo Container with Overlays
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                AsyncImage(
                    model = dog.primaryPhotoUrl,
                    contentDescription = dog.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gender Badge (Top Left)
                GenderTag(
                    gender = dog.gender,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                )

                // Favorite Toggle Button (Top Right)
                IconButton(
                    onClick = onFavoriteToggle,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f))
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = if (dog.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (dog.isFavorite) Color(0xFFE91E63) else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Details Container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${dog.name}, ${dog.ageYears} yrs",
                        style = PedigreeTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PedigreeTheme.colors.textPrimary
                    )

                    CertificationTierBadge(tier = dog.certificationTier)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = dog.breed,
                    style = PedigreeTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = PedigreeTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dog.kennelName,
                        style = PedigreeTheme.typography.bodySmall,
                        color = PedigreeTheme.colors.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = null,
                            tint = PedigreeTheme.colors.textSecondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = dog.locationCity,
                            style = PedigreeTheme.typography.bodySmall,
                            color = PedigreeTheme.colors.textSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DoubleColumnDogCard(
    dog: CanineMatchProfile,
    onCardClicked: () -> Unit,
    onFavoriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onCardClicked),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PedigreeTheme.colors.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Photo Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                AsyncImage(
                    model = dog.primaryPhotoUrl,
                    contentDescription = dog.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Favorite Toggle Button (Top Right)
                IconButton(
                    onClick = onFavoriteToggle,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f))
                        .size(28.dp)
                ) {
                    Icon(
                        imageVector = if (dog.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (dog.isFavorite) Color(0xFFE91E63) else Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Compact Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = dog.name,
                    style = PedigreeTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = PedigreeTheme.colors.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = dog.breed,
                    style = PedigreeTheme.typography.bodySmall,
                    color = PedigreeTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${dog.ageYears}y • ${if (dog.gender == Gender.MALE) "M" else "F"}",
                        style = PedigreeTheme.typography.labelSmall,
                        color = PedigreeTheme.colors.textSecondary
                    )

                    Text(
                        text = dog.locationCity,
                        style = PedigreeTheme.typography.labelSmall,
                        color = PedigreeTheme.colors.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun GenderTag(
    gender: Gender,
    modifier: Modifier = Modifier
) {
    val isMale = gender == Gender.MALE
    val bgColor = if (isMale) Color(0xFF1976D2) else Color(0xFFD81B60)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor.copy(alpha = 0.9f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = if (isMale) "Male" else "Female",
            style = PedigreeTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun CertificationTierBadge(
    tier: PedigreeCertificationTier,
    modifier: Modifier = Modifier
) {
    val tierText = when (tier) {
        PedigreeCertificationTier.CHAMPION_BLOODLINE -> "Champion"
        PedigreeCertificationTier.KENNEL_CLUB_CERTIFIED -> "Certified"
        PedigreeCertificationTier.REGISTERED -> "Registered"
        PedigreeCertificationTier.PENDING_VERIFICATION -> "Pending"
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(PedigreeTheme.colors.primary.copy(alpha = 0.12f))
            .padding(horizontal = 6.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Verified,
            contentDescription = null,
            tint = PedigreeTheme.colors.primary,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = tierText,
            style = PedigreeTheme.typography.labelSmall,
            color = PedigreeTheme.colors.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}