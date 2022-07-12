/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are released under various
 * Open-Source licenses. Use of those libraries and frameworks are governed by their own individual
 * licenses.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.inr_management_md3.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.inr_management_md3.presentation.calendar.CalendarScreen
import com.example.inr_management_md3.presentation.screens.about.AboutScreen
import com.example.inr_management_md3.presentation.screens.dose.DoseScreen
import com.example.inr_management_md3.presentation.screens.home.HomeScreen
import com.example.inr_management_md3.presentation.screens.measure.MeasureScreen
import com.example.inr_management_md3.presentation.screens.settings.MeasureSettingsScreen
import com.example.inr_management_md3.presentation.screens.settings.MedicamentSettingsScreen
import com.example.inr_management_md3.presentation.screens.settings.SettingsScreen
import com.example.inr_management_md3.presentation.screens.settings.TargetRangeSettingsScreen
import com.example.inr_management_md3.presentation.screens.statistics.StatisticsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController) {
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
            HomeScreen(navController)
        }

        composable(
            Screens.Settings.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Home.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Home.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            SettingsScreen(navController)
        }

        composable(
            Screens.Dose.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Home.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Home.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            DoseScreen(navController)
        }

        composable(
            Screens.InrMeasure.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Home.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Home.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            MeasureScreen(navController)
        }

        composable(
            Screens.CalendarMonthView.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Home.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Home.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            CalendarScreen(navController)
        }

        composable(
            Screens.StatisticCharts.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Home.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Home.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            StatisticsScreen(navController)
        }

        composable(
            Screens.SetMedicament.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Settings.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Settings.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            MedicamentSettingsScreen(navController)
        }

        composable(
            Screens.SetTargetRange.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Settings.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Settings.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            TargetRangeSettingsScreen(navController)
        }

        composable(
            Screens.SetMeasure.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Settings.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Settings.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            MeasureSettingsScreen(navController)
        }

        composable(
            Screens.About.route,
            enterTransition = {
                if (initialState.destination.route == Screens.Settings.route) slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(600)
                )
                else null
            },
            exitTransition = {
                if (targetState.destination.route == Screens.Settings.route) slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(600)
                )
                else null
            }
        ) {
            AboutScreen(navController = navController)
        }
    }
}
