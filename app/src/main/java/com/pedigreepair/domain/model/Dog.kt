package com.pedigreepair.domain.model

data class Dog(
    val id: String,
    val ownerId: String,
    val name: String,
    val breed: String,
    val ageMonths: Int,
    val gender: Gender,
    val pedigreeCertified: Boolean,
    val primaryImageUrl: String,
    val additionalImages: List<String> = emptyList(),
    val bio: String = "",
    val pedigree: PedigreeCertificate? = null
)