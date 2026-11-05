package com.pedigreepair.presentation.home.contract

import com.pedigreepair.domain.model.home.CanineEvent
import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.model.home.FeedVieLayoutMode

data class HomeUiState(
    val isLoading: Boolean = true,
    val searchQuery: String = "",
    val layoutMode: FeedVieLayoutMode = FeedVieLayoutMode.SINGLE_COLUMN,
    val dogs: List<CanineMatchProfile> = emptyList(),
    val filteredDogs: List<CanineMatchProfile> = emptyList(),
    val events: List<CanineEvent> = emptyList(),
    val backgroundPhotoUrl: String? = null,
    val unreadNotificationCount: Int = 0,
    val errorMessage: String? = null
)