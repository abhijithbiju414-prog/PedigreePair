package com.pedigreepair.domain.usecase.filter

import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.model.home.FeedFilterCriteria

class ApplyFeedFiltersUseCase {

    operator fun invoke(
        dogs: List<CanineMatchProfile>,
        criteria: FeedFilterCriteria
    ): List<CanineMatchProfile> {
        if (!criteria.isActive) return dogs

        return dogs.filter { dog ->
            val matchesBreed = criteria.selectedBreeds.isEmpty() ||
                    dog.breed in criteria.selectedBreeds

            val matchesGender = criteria.selectedGender == null ||
                    dog.gender == criteria.selectedGender

            val matchesTier = criteria.selectedTiers.isEmpty() ||
                    dog.certificationTier in criteria.selectedTiers

            val matchesAge = criteria.maxAgeYears == null ||
                    dog.ageYears <= criteria.maxAgeYears

            matchesBreed && matchesGender && matchesTier && matchesAge
        }
    }
}