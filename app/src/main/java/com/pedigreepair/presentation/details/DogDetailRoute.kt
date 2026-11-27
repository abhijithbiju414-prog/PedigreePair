package com.pedigreepair.presentation.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.Gender
import com.pedigreepair.presentation.details.contract.DogDetailUiEffect
import com.pedigreepair.presentation.details.contract.DogDetailUiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DogDetailRoute(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DogDetailViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                DogDetailUiEffect.NavigateBack -> onNavigateBack
                is DogDetailUiEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    DogDetailScreen(
        uiState = uiState,
        onBackClicked = viewModel::onBackClicked,
        onToggleFavorite = viewModel::onToggleFavorite,
        onInitiateContactClicked = viewModel::onInitiateContactClicked,
        modifier = modifier
    )

}


@Composable
fun DogDetailScreen(
    uiState: DogDetailUiState,
    onBackClicked: () -> Unit,
    onToggleFavorite: () -> Unit,
    onInitiateContactClicked: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PedigreeTheme.colors.background)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = PedigreeTheme.colors.primary
                )
            }

            uiState.dog != null -> {
                val dog = uiState.dog
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(bottom = 96.dp)
                ) {
                    // Top Hero Image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.1f)
                    ) {
                        AsyncImage(
                            model = dog.primaryPhotoUrl,
                            contentDescription = dog.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Top gradient scrim for back/favorite icon contrast
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Black.copy(alpha = 0.55f),
                                            Color.Transparent
                                        )
                                    )
                                )
                        )
                    }

                    // Profile Details Body
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = dog.name,
                                style = PedigreeTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = PedigreeTheme.colors.textPrimary
                            )

                            val genderBadgeColor = if (dog.gender == Gender.MALE) {
                                Color(0xFF2196F3)
                            } else {
                                Color(0xFFE91E63)
                            }

                            Text(
                                text = if (dog.gender == Gender.MALE) "Male ♂" else "Female ♀",
                                style = PedigreeTheme.typography.labelMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = genderBadgeColor,
                                modifier = Modifier
                                    .background(
                                        color = genderBadgeColor.copy(alpha = 0.12f),
                                        shape = PedigreeTheme.shapes.small
                                    )
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${dog.breed} • ${dog.ageYears} yrs",
                            style = PedigreeTheme.typography.titleMedium,
                            color = PedigreeTheme.colors.textSecondary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = PedigreeTheme.colors.textSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "${dog.locationCity} • ${dog.kennelName}",
                                style = PedigreeTheme.typography.bodyMedium,
                                color = PedigreeTheme.colors.textSecondary,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Pedigree Certification Card
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = PedigreeTheme.colors.surface,
                                    shape = PedigreeTheme.shapes.medium
                                )
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Certified",
                                tint = PedigreeTheme.colors.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            Column(modifier = Modifier.padding(start = 12.dp)) {
                                Text(
                                    text = "Pedigree Status",
                                    style = PedigreeTheme.typography.labelSmall,
                                    color = PedigreeTheme.colors.textSecondary
                                )
                                Text(
                                    text = dog.certificationTier.name.replace("_", " "),
                                    style = PedigreeTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PedigreeTheme.colors.textPrimary
                                )
                            }
                        }
                    }
                }

                // Top Bar Actions (Floating, using statusBarsPadding)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClicked,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.35f))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = onToggleFavorite,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.35f))
                    ) {
                        Icon(
                            imageVector = if (dog.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (dog.isFavorite) Color.Red else Color.White
                        )
                    }
                }

                // Fixed Bottom Contact Bar (using navigationBarsPadding)
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(PedigreeTheme.colors.surface)
                        .navigationBarsPadding()
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = PedigreeTheme.colors.lineDivider
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                    ) {
                        Button(
                            onClick = onInitiateContactClicked,
                            enabled = !uiState.isContactInitiated,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PedigreeTheme.colors.primary,
                                contentColor = PedigreeTheme.colors.onPrimary
                            ),
                            shape = PedigreeTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = if (uiState.isContactInitiated) "Inquiry Sent" else "Connect with Breeder",
                                style = PedigreeTheme.typography.labelLarge,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.errorMessage ?: "Dog profile not found",
                        style = PedigreeTheme.typography.bodyLarge,
                        color = PedigreeTheme.colors.textSecondary
                    )
                }
            }
        }
    }
}