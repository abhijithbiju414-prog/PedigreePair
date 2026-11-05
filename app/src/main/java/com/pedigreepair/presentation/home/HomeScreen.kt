package com.pedigreepair.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedigreepair.core.designsystem.theme.PedigreeTheme
import com.pedigreepair.domain.model.home.FeedVieLayoutMode
import com.pedigreepair.presentation.home.componenets.DoubleColumnDogCard
import com.pedigreepair.presentation.home.componenets.HomeBackgroundLayer
import com.pedigreepair.presentation.home.componenets.HomeEventsRail
import com.pedigreepair.presentation.home.componenets.HomeFeedHeader
import com.pedigreepair.presentation.home.componenets.HomeSearchBar
import com.pedigreepair.presentation.home.componenets.HomeTopBar
import com.pedigreepair.presentation.home.componenets.SingleColumnDogCard
import com.pedigreepair.presentation.home.contract.HomeUiEffect
import com.pedigreepair.presentation.home.contract.HomeUiState
import com.pedigreepair.presentation.home.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreenRoute(
    onNavigateToDogDetails: (String) -> Unit,
    onNavigateToEventDetails: (String) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenFilterSheet: () -> Unit,
    onOpenNotifications: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    bottomBarAnchor: @Composable () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeUiEffect.NavigateToDogDetails -> onNavigateToDogDetails(effect.dogId)
                is HomeUiEffect.NavigateToEventDetails -> onNavigateToEventDetails(effect.eventId)
                is HomeUiEffect.OpenProfile -> onOpenProfile()
                is HomeUiEffect.OpenFilterSheet -> onOpenFilterSheet()
                is HomeUiEffect.OpenNotifications -> onOpenNotifications()
                is HomeUiEffect.ShowToast -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onClearSearch = viewModel::onClearSearch,
        onToggleLayoutMode = viewModel::onToggleLayoutMode,
        onToggleFavorite = viewModel::onToggleFavourite,
        onDogClicked = viewModel::onDogClicked,
        onEventClicked = viewModel::onEventClicked,
        onProfileClicked = viewModel::onProfileClicked,
        onFilterClicked = viewModel::onFilterClicked,
        onNotificationClicked = viewModel::onNotificationClicked,
        bottomBarAnchor = bottomBarAnchor,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSearchQueryChanged: (String) -> Unit,
    onClearSearch: () -> Unit,
    onToggleLayoutMode: () -> Unit,
    onToggleFavorite: (String) -> Unit,
    onDogClicked: (String) -> Unit,
    onEventClicked: (String) -> Unit,
    onProfileClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onNotificationClicked: () -> Unit,
    bottomBarAnchor: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = bottomBarAnchor,
        containerColor = Color.Transparent,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // Layer 1: Background Canvas (Theme + Photo scrim)
            HomeBackgroundLayer(backgroundPhotoUrl = uiState.backgroundPhotoUrl)

            // Layer 2: Main Content Stack
            Column(modifier = Modifier.fillMaxSize()) {
                // Top Fixed Area: Top Bar & Search
                HomeTopBar(
                    unreadNotificationsCount = uiState.unreadNotificationCount,
                    onProfileClicked = onProfileClicked,
                    onFilterClicked = onFilterClicked,
                    onNotificationClicked = onNotificationClicked
                )

                HomeSearchBar(
                    query = uiState.searchQuery,
                    onQueryChanged = onSearchQueryChanged,
                    onClearQuery = onClearSearch
                )

                // Scrollable Feed Area (Events Rail + Dog Grid)
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PedigreeTheme.colors.primary)
                    }
                } else {
                    val columnCount = when (uiState.layoutMode) {
                        FeedVieLayoutMode.SINGLE_COLUMN -> 1
                        FeedVieLayoutMode.DOUBLE_COLUMN -> 2
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columnCount),
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 24.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Header Items spanning all columns
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            HomeEventsRail(
                                events = uiState.events,
                                onEventClicked = onEventClicked
                            )
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            HomeFeedHeader(
                                dogCount = uiState.filteredDogs.size,
                                layoutMode = uiState.layoutMode,
                                onToggleLayoutMode = onToggleLayoutMode
                            )
                        }

                        // Empty State if no dogs match search
                        if (uiState.filteredDogs.isEmpty()) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (uiState.searchQuery.isNotBlank()) {
                                            "No matches found for \"${uiState.searchQuery}\""
                                        } else {
                                            "No dogs available right now"
                                        },
                                        style = PedigreeTheme.typography.bodyMedium,
                                        color = PedigreeTheme.colors.textSecondary
                                    )
                                }
                            }
                        }

                        // Dog Profile Items
                        items(
                            items = uiState.filteredDogs,
                            key = { it.id },
                            span = { GridItemSpan(1) }
                        ) { dog ->
                            if (uiState.layoutMode == FeedVieLayoutMode.SINGLE_COLUMN) {
                                SingleColumnDogCard(
                                    dog = dog,
                                    onCardClicked = { onDogClicked(dog.id) },
                                    onFavoriteToggle = { onToggleFavorite(dog.id) }
                                )
                            } else {
                                DoubleColumnDogCard(
                                    dog = dog,
                                    onCardClicked = { onDogClicked(dog.id) },
                                    onFavoriteToggle = { onToggleFavorite(dog.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
