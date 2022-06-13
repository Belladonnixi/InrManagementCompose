package com.example.inr_management_md3.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inr_management_md3.presentation.navigation.AppNavigation
import com.example.inr_management_md3.presentation.navigation.Screens
import com.example.inr_management_md3.presentation.navigation.items

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomNavBar() {
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = {
//            NavigationBar {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                items.forEach { screens ->
//                    NavigationBarItem(
//                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
//                        label = { Text(stringResource(id = screens.resourceId)) },
//                        selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
//                        onClick = {
//                            navController.navigate(screens.route) {
//                                // Pop up to the start destination of the graph to
//                                // avoid building up a large stack of destinations
//                                // on the back stack as users select items
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                // Avoid multiple copies of the same destination when
//                                // reselecting the same item
//                                launchSingleTop = true
//                                // Restore state when reselecting a previously selected item
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding)) {
//            navController.navigate(Screens.Home.route)
//        }
//    }
//}
