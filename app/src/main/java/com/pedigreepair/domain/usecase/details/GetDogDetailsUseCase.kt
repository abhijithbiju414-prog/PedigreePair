package com.pedigreepair.domain.usecase.details

import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.repository.home.HomeFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDogDetailsUseCase(
    private val repository: HomeFeedRepository
) {
    operator fun invoke(dogId: String): Flow<CanineMatchProfile?> {
        return repository.getCanineFeed().map { dogList ->
            dogList.find { it.id == dogId }
        }
    }
}