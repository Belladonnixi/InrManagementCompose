package com.example.inr_management_md3.presentation.screens.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.navigation.Screens
import com.example.inr_management_md3.presentation.screens.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screens.Home.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = {},
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}
