package com.pedigreepair.domain.repository

import com.pedigreepair.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun observeCurrentUser(): Flow<UserProfile?>

    suspend fun getCurrentUser(): UserProfile

    suspend fun saveUserProfile(profile: UserProfile): Result<Unit>
}