package com.example.inr_management_md3.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.BottomNavBar
import com.example.inr_management_md3.presentation.navigation.Screens
import com.example.inr_management_md3.presentation.navigation.items
import com.example.inr_management_md3.presentation.screens.home.StatisticCard
import com.example.inr_management_md3.presentation.screens.home.TodayDoseCard
import com.example.inr_management_md3.presentation.screens.home.TomorrowDoseInrCard
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.example.inr_management_md3.presentation.theme.nautigalFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontFamily = nautigalFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.displayMedium
                    )
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screens.Settings.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .padding(16.dp)
                ) {
                    TodayDoseCard()
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    TomorrowDoseInrCard()
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    StatisticCard()
                }
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}



@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewCardsorderedCard() {
    INR_Management_Theme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
