package com.pedigreepair.domain.model.home

import com.pedigreepair.domain.model.Gender


enum class PedigreeCertificationTier {
    CHAMPION_BLOODLINE,
    KENNEL_CLUB_CERTIFIED,
    REGISTERED,
    PENDING_VERIFICATION
}

enum class FeedVieLayoutMode(val columns: Int) {
    SINGLE_COLUMN(1),
    DOUBLE_COLUMN(2)
}

data class CanineMatchProfile(
    val id: String,
    val name: String,
    val breed: String,
    val ageYears: Int,
    val gender: Gender,
    val certificationTier: PedigreeCertificationTier,
    val primaryPhotoUrl: String,
    val kennelName: String,
    val locationCity: String,
    val coatColorHex: Long,
    val isFavorite: Boolean = false
)