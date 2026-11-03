package com.pedigreepair.domain.usecase.dog

import com.pedigreepair.domain.model.Dog
import com.pedigreepair.domain.model.GenderFilter
import com.pedigreepair.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class GetDogsUseCase(
    private val dogRepository: DogRepository
) {
    operator fun invoke(
        searchQuery: String,
        genderFilter: GenderFilter
    ): Flow<List<Dog>> {
        val sanitizedQuery = searchQuery.trim()
        return dogRepository.observeDongsFeed(sanitizedQuery, genderFilter)
    }
}