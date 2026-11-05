package com.pedigreepair.core.di


import com.pedigreepair.data.local.repository.OnboardingRepositoryImpl
import com.pedigreepair.data.local.repository.ThemeRepositoryImpl
import com.pedigreepair.data.local.repository.home.FakeHomeFeedRepositoryImpl
import com.pedigreepair.domain.repository.OnboardingRepository
import com.pedigreepair.domain.repository.ThemeRepository
import com.pedigreepair.domain.repository.home.HomeFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository

    @Binds
    @Singleton
    abstract fun bindHomeFeedRepository(
        impl: FakeHomeFeedRepositoryImpl
    ): HomeFeedRepository

}
