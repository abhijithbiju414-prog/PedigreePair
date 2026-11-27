package com.pedigreepair.domain.repository.filter

import kotlinx.coroutines.flow.Flow

interface BreedRepository {
    fun getAllBreeds(): Flow<List<String>>
}