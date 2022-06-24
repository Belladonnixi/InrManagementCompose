/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.presentation.screens.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicamentSettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            R.string.set_medicament
                        ),
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
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
