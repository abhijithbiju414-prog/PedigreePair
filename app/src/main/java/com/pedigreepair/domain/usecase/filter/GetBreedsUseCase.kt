package com.pedigreepair.domain.usecase.filter

import com.pedigreepair.domain.repository.filter.BreedRepository
import kotlinx.coroutines.flow.Flow

class GetBreedsUseCase(
    private val breedRepository: BreedRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return breedRepository.getAllBreeds()
    }
}