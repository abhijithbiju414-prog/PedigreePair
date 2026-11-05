package com.pedigreepair.domain.repository.home

import com.pedigreepair.domain.model.home.CanineEvent
import com.pedigreepair.domain.model.home.CanineMatchProfile
import kotlinx.coroutines.flow.Flow

interface HomeFeedRepository {
    fun getCanineFeed(): Flow<List<CanineMatchProfile>>
    fun getUpComingEvents(): Flow<List<CanineEvent>>
    fun getActivateBackgroundPhotoUrl(): Flow<String?>
    suspend fun toggleFavourite(dogId: String)
}