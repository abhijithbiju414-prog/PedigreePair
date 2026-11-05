package com.pedigreepair.domain.model.home

data class CanineEvent(
    val id: String,
    val title: String,
    val dateLabel: String,
    val location: String,
    val bannerImageUrl: String,
    val eventType: String
)