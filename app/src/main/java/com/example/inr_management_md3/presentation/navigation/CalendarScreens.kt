package com.example.inr_management_md3.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

enum class CalendarScreens(
    val icon: ImageVector,
    private val body: @Composable ((CalendarScreens) -> Unit) -> Unit
) {
    Month(
        icon = Icons.Filled.CalendarMonth,
        body = { onScreenChange ->
            com.example.inr_management_md3.presentation.calendar.MonthViewCalendar(onScreenChange)
        }
    ),
    List(
        icon = Icons.Filled.List,
        body = {}
    );

    @Composable
    fun content(onScreenChange: (CalendarScreens) -> Unit) {
        body(onScreenChange)
    }
}
