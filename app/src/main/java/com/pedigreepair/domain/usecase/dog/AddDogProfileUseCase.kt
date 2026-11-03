package com.pedigreepair.domain.usecase.dog

import com.pedigreepair.domain.model.Dog
import com.pedigreepair.domain.repository.DogRepository

class AddDogProfileUseCase(
    private val dogRepository: DogRepository
) {
    suspend operator fun invoke(dog: Dog): Result<Unit> {
        if (dog.name.isBlank()) {
            return Result.failure(IllegalArgumentException("Dog name cannot be blank"))

        }
        if (dog.breed.isBlank()) {
            return Result.failure(IllegalArgumentException("Dog breed must be specified"))
        }
        if (dog.primaryImageUrl.isBlank()) {
            return Result.failure(IllegalArgumentException("primary image is required"))
        }
        return dogRepository.upsertDog(dog)
    }
}