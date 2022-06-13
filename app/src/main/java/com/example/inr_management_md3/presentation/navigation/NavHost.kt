package com.example.inr_management_md3.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.inr_management_md3.presentation.screens.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        enterTransition = { expandIn(animationSpec = tween(800)) },
        exitTransition = { shrinkOut(animationSpec = tween(800)) }
    ) {
        composable(
            Screens.Home.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Settings.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Settings.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            HomeScreen()
        }

//        composable(
//            Screens.SettingsScreen.route,
//            enterTransition = {
//                if (initialState.destination.route == Screens.HomeScreen.route) slideIntoContainer(
//                    AnimatedContentScope.SlideDirection.Left,
//                    animationSpec = tween(600)
//                )
//                else null
//            },
//            exitTransition = {
//                if (targetState.destination.route == Screens.HomeScreen.route) slideOutOfContainer(
//                    AnimatedContentScope.SlideDirection.Right,
//                    animationSpec = tween(600)
//                )
//                else null
//            }
//        ) {
//            SettingsScreen(navController)
//        }
    }
}
