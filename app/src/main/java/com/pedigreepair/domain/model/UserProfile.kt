package com.pedigreepair.domain.model

data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val city: String,
    val profilePictureUrl: String?,
    val registeredDogs: List<Dog> = emptyList()
)
