package com.pedigreepair.domain.model

data class PedigreeCertificate(
    val registrationNumber: String, // E.g., KCI / AKC registration number
    val authority: String, // E.g., "Kennel Club of India"
    val sirName: String, // Father's registered name
    val damName: String, // Mother's registered name
    val certificateImageUrl: String?,
    val isVerified: Boolean = false
)