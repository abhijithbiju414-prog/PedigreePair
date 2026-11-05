package com.pedigreepair.domain.model.home

data class HomeFeedBundle(
    val dogs: List<CanineMatchProfile>,
    val events: List<CanineEvent>,
    val backgroundPhotoUrl: String?
)
