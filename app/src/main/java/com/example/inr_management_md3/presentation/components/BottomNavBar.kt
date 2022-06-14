package com.example.inr_management_md3.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.inr_management_md3.presentation.navigation.items
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screens ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screens.iconId),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        stringResource(id = screens.resourceId),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
                onClick = {
                    navController.navigate(screens.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewBottomNavBar() {
    INR_Management_Theme {
        val navController = rememberNavController()
        BottomNavBar(navController)
    }
}
