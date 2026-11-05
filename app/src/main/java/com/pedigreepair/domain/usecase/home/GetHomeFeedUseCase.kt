package com.pedigreepair.domain.usecase.home

import com.pedigreepair.domain.model.home.HomeFeedBundle
import com.pedigreepair.domain.repository.home.HomeFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetHomeFeedUseCase(
    private val repository: HomeFeedRepository
) {
    operator fun invoke(): Flow<HomeFeedBundle> {
        return combine(
            repository.getCanineFeed(),
            repository.getUpComingEvents(),
            repository.getActivateBackgroundPhotoUrl()
        ) { dogs, events, backgroundPhotoUrl ->
            HomeFeedBundle(
                dogs = dogs,
                events = events,
                backgroundPhotoUrl = backgroundPhotoUrl
            )
        }
    }
}