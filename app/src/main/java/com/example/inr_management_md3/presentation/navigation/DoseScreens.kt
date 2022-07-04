package com.example.inr_management_md3.presentation.navigation

import androidx.compose.runtime.Composable
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.screens.dose.BaseMedicationInterval
import com.example.inr_management_md3.presentation.screens.dose.BaseMedicationWeek
import com.example.inr_management_md3.presentation.screens.dose.TrimDose

enum class DoseScreens(
    val icon: Int,
    private val body: @Composable ((DoseScreens) -> Unit) -> Unit
) {
    Week(
        icon = R.drawable.ic_baseline_calendar_view_week_24,
        body = { onScreenChange -> BaseMedicationWeek(onScreenChange) }
    ),
    Interval(
        icon = R.drawable.ic_baseline_timelapse_24,
        body = { BaseMedicationInterval() }
    ),
    Dose(
        icon = R.drawable.pill,
        body = { TrimDose() }
    );

    @Composable
    fun content(onScreenChange: (DoseScreens) -> Unit) {
        body(onScreenChange)
    }
}
