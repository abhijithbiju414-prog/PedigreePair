package com.pedigreepair.domain.repository

import com.pedigreepair.domain.model.Dog
import com.pedigreepair.domain.model.GenderFilter
import kotlinx.coroutines.flow.Flow

interface DogRepository {

    /**
     * Emits a reactive stream of dogs filtered by name/breed search text and gender.
     */
    fun observeDongsFeed(
        searchQuery: String,
        genderFilter: GenderFilter
    ): Flow<List<Dog>>

    /**
     * Retrieves full profile details for a single dog by its unique identifier.
     */
    suspend fun getDogById(dogId: String): Dog?

    /**
     * Retrieves all dogs belonging to a specific owner account.
     */
    fun observeDogsByOwnerId(ownerId: String): Flow<List<Dog>>

    /**
     * Inserts or updates a dog profile.
     */
    suspend fun upsertDog(dog: Dog): Result<Unit>

    /**
     * Removes a dog profile from the owner's account.
     */
    suspend fun deleteDog(dogId: String): Result<Unit>
}