package com.example.inr_management_md3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import com.example.inr_management_md3.presentation.navigation.AppNavigation
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            INR_Management_Theme() {
                AppNavigation(navController, modifier = Modifier)
            }
        }
    }
}
