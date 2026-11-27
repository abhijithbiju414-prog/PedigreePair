package com.pedigreepair.domain.model.home

import com.pedigreepair.domain.model.Gender

data class FeedFilterCriteria(
    val selectedBreeds: Set<String> = emptySet(),
    val selectedGender: Gender? = null,
    val selectedTiers: Set<PedigreeCertificationTier> = emptySet(),
    val maxAgeYears: Int? = null
) {
    val isActive: Boolean
        get() = selectedBreeds.isNotEmpty() ||
                selectedGender != null ||
                selectedTiers.isNotEmpty() ||
                maxAgeYears != null
}