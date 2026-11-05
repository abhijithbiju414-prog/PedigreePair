package com.pedigreepair.data.local.repository.home

import com.pedigreepair.domain.model.Gender
import com.pedigreepair.domain.model.home.CanineEvent
import com.pedigreepair.domain.model.home.CanineMatchProfile
import com.pedigreepair.domain.model.home.PedigreeCertificationTier
import com.pedigreepair.domain.repository.home.HomeFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeHomeFeedRepositoryImpl @Inject constructor() : HomeFeedRepository {

    private val _canineFeed = MutableStateFlow(
        listOf(
            CanineMatchProfile(
                id = "1",
                name = "Thor",
                breed = "German Shepherd",
                ageYears = 3,
                gender = Gender.MALE,
                certificationTier = PedigreeCertificationTier.CHAMPION_BLOODLINE,
                primaryPhotoUrl = "https://images.unsplash.com/photo-1589941013453-ec89f33b5e95",
                kennelName = "Vom Haus Edelweiss",
                locationCity = "Bengaluru",
                coatColorHex = 0xFF8B4513,
                isFavorite = false
            ),
            CanineMatchProfile(
                id = "2",
                name = "Bella",
                breed = "Golden Retriever",
                ageYears = 2,
                gender = Gender.FEMALE,
                certificationTier = PedigreeCertificationTier.KENNEL_CLUB_CERTIFIED,
                primaryPhotoUrl = "https://images.unsplash.com/photo-1552053831-71594a27632d",
                kennelName = "Sunray Kennels",
                locationCity = "Pune",
                coatColorHex = 0xFFDAA520,
                isFavorite = true
            ),
            CanineMatchProfile(
                id = "3",
                name = "Max",
                breed = "Siberian Husky",
                ageYears = 4,
                gender = Gender.MALE,
                certificationTier = PedigreeCertificationTier.REGISTERED,
                primaryPhotoUrl = "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2",
                kennelName = "Arctic Frost Line",
                locationCity = "Chandigarh",
                coatColorHex = 0xFF708090,
                isFavorite = false
            ),
            CanineMatchProfile(
                id = "4",
                name = "Zara",
                breed = "Rottweiler",
                ageYears = 2,
                gender = Gender.FEMALE,
                certificationTier = PedigreeCertificationTier.CHAMPION_BLOODLINE,
                primaryPhotoUrl = "https://images.unsplash.com/photo-1567752881298-894bb81f9379",
                kennelName = "Ironclad Rottweilers",
                locationCity = "Hyderabad",
                coatColorHex = 0xFF2F4F4F,
                isFavorite = false
            )
        )
    )

    private val mockEvents = listOf(
        CanineEvent(
            id = "evt_1",
            title = "National All-Breed Championship",
            dateLabel = "Nov 22, 2026",
            location = "Palace Grounds, Bengaluru",
            bannerImageUrl = "https://images.unsplash.com/photo-1601758228041-f3b2795255f1",
            eventType = "Championship"
        ),
        CanineEvent(
            id = "evt_2",
            title = "Working Dog Agility & Trial",
            dateLabel = "Dec 05, 2026",
            location = "Jawaharlal Nehru Stadium, Kochi",
            bannerImageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b",
            eventType = "Agility Trial"
        ),
        CanineEvent(
            id = "evt_3",
            title = "Annual Pedigree Breed Showcase",
            dateLabel = "Dec 18, 2026",
            location = "Bandra Kurla Complex, Mumbai",
            bannerImageUrl = "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e",
            eventType = "Showcase"
        )
    )

    override fun getCanineFeed(): Flow<List<CanineMatchProfile>> = _canineFeed.asStateFlow()
    override fun getUpComingEvents(): Flow<List<CanineEvent>> = flowOf(mockEvents)

    override fun getActivateBackgroundPhotoUrl(): Flow<String?> = flowOf(null)

    override suspend fun toggleFavourite(dogId: String) {
        _canineFeed.update { list ->
            list.map { dog ->
                if (dog.id == dogId) dog.copy(isFavorite = !dog.isFavorite) else dog
            }
        }
    }

}