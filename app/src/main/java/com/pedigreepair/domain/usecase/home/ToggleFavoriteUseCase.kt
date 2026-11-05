package com.pedigreepair.domain.usecase.home

import com.pedigreepair.domain.repository.home.HomeFeedRepository

class ToggleFavoriteUseCase(
    private val repository: HomeFeedRepository
) {
    suspend operator fun invoke(dogId: String): Result<Unit> {
        return runCatching {
            repository.toggleFavourite(dogId = dogId)
        }
    }
}