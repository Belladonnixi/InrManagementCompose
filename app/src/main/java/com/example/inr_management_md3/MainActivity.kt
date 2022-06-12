package com.example.inr_management_md3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.inr_management_md3.presentation.screens.HomeScreen
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            INR_Management_Theme {
                HomeScreen()
            }
        }
    }
}
