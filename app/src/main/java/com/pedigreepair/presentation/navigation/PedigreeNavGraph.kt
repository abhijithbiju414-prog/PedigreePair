package com.pedigreepair.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pedigreepair.domain.model.AppLaunchDestination
import com.pedigreepair.presentation.privacypolicy.PrivacyPolicyScreen
import com.pedigreepair.presentation.privacypolicy.PrivacyPolicyViewModel
import com.pedigreepair.presentation.splash.SplashScreen
import com.pedigreepair.presentation.splash.SplashViewModel
import com.pedigreepair.presentation.tutorial.TutorialScreen
import com.pedigreepair.presentation.tutorial.TutorialViewmodel

@Composable
fun PedigreeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Splash,
        modifier = modifier
    ) {
        composable<ScreenRoute.Splash> {
            // Automatically injected via Hilt scoped to this navigation destination
            val splashViewModel: SplashViewModel = hiltViewModel()

            SplashScreen(
                viewModel = splashViewModel,
                onNavigateToDestination = { destination ->
                    when (destination) {
                        AppLaunchDestination.Tutorial -> {
                            navController.navigate(ScreenRoute.Tutorial) {
                                popUpTo(ScreenRoute.Splash) { inclusive = true }
                            }
                        }

                        AppLaunchDestination.PrivacyPolicy -> {
                            navController.navigate(ScreenRoute.PrivacyPolicy) {
                                popUpTo(ScreenRoute.Splash) { inclusive = true }
                            }
                        }

                        AppLaunchDestination.Home -> {
                            navController.navigate(ScreenRoute.Home) {
                                popUpTo(ScreenRoute.Splash) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable<ScreenRoute.Tutorial> {
            val tutorialViewmodel: TutorialViewmodel = hiltViewModel()
            TutorialScreen(
                viewModel = tutorialViewmodel,
                onNavigateToPrivacyPolicy = {
                    navController.navigate(ScreenRoute.PrivacyPolicy) {
                        popUpTo(ScreenRoute.Tutorial) { inclusive = true }
                    }
                }
            )
        }

        composable<ScreenRoute.PrivacyPolicy> {
            val policyViewModel: PrivacyPolicyViewModel = hiltViewModel()
            PrivacyPolicyScreen(
                viewModel = policyViewModel,
                onNavigateToHome = {
                    navController.navigate(ScreenRoute.Home) {
                        popUpTo(ScreenRoute.PrivacyPolicy)
                    }
                }
            )
        }

        composable<ScreenRoute.Home> {
            // Next: HomeScreen
        }
    }
}