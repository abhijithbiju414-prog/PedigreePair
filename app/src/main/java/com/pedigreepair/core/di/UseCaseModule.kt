package com.pedigreepair.core.di

import com.pedigreepair.domain.repository.DogRepository
import com.pedigreepair.domain.repository.OnboardingRepository
import com.pedigreepair.domain.repository.ThemeRepository
import com.pedigreepair.domain.repository.home.HomeFeedRepository
import com.pedigreepair.domain.usecase.dog.GetDogsUseCase
import com.pedigreepair.domain.usecase.home.GetHomeFeedUseCase
import com.pedigreepair.domain.usecase.home.ToggleFavoriteUseCase
import com.pedigreepair.domain.usecase.onboarding.CheckAppLaunchDestinationUseCase
import com.pedigreepair.domain.usecase.onboarding.RecordPolicyAcceptanceUseCase
import com.pedigreepair.domain.usecase.theme.ObserveActiveThemeUseCase
import com.pedigreepair.domain.usecase.tutorial.CompleteTutorialUseCase
import com.pedigreepair.domain.usecase.tutorial.GetTutorialStepsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideCheckAppLaunchDestinationUseCase(
        onboardingRepository: OnboardingRepository
    ): CheckAppLaunchDestinationUseCase {
        return CheckAppLaunchDestinationUseCase(onboardingRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideObserveActiveThemeUseCase(
        themeRepository: ThemeRepository
    ): ObserveActiveThemeUseCase {
        return ObserveActiveThemeUseCase(themeRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideCompleteTutorialUseCase(
        onboardingRepository: OnboardingRepository
    ): CompleteTutorialUseCase {
        return CompleteTutorialUseCase(onboardingRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetDogsFeedUseCase(
        dogRepository: DogRepository
    ): GetDogsUseCase {
        return GetDogsUseCase(dogRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetTutorialStepsUseCase(
        onboardingRepository: OnboardingRepository
    ): GetTutorialStepsUseCase {
        return GetTutorialStepsUseCase(onboardingRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideRecordPolicyAcceptanceUseCase(
        onboardingRepository: OnboardingRepository
    ): RecordPolicyAcceptanceUseCase {
        return RecordPolicyAcceptanceUseCase(onboardingRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetHomeFeedUseCase(
        repository: HomeFeedRepository
    ): GetHomeFeedUseCase {
        return GetHomeFeedUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideToggleFavoriteUseCase(
        repository: HomeFeedRepository
    ): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(repository)
    }
}