package com.example.inr_management_md3.presentation.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("homeScreen")
    object SettingsScreen : Screens("settingsScreen")
    object DoseScreen : Screens("doseScreen")
}
