package com.example.inr_management_md3.presentation.navigation

import androidx.annotation.StringRes
import com.example.inr_management_md3.R

sealed class Screens(val route: String, @StringRes val resourceId: Int) {
    object Home : Screens("home", R.string.home)
    object Settings : Screens("settings", R.string.settings)
    object Dose : Screens("dose", R.string.dose)
    object SetMeasure : Screens("setMeasure", R.string.set_measure)
    object SetTargetRange : Screens("setTargetRange", R.string.set_target_range)
    object SetMedicament : Screens("setMedicament", R.string.set_medicament)
    object SetDoseWeek : Screens("setDoseWeek", R.string.set_dose_week)
    object SetDoseInterval : Screens("setDoseInterval", R.string.set_dose_interval)
    object DoseAdjustment : Screens("doseAdjustment", R.string.dose_adjustment)
    object InrMeasure : Screens("inrMeasure", R.string.measure)
    object CalendarMonthView : Screens("calendarMonthView", R.string.calendar)
    object CalendarListView : Screens("calendarListView", R.string.calendar_list_view)
    object CalendarDay : Screens("calendarDay", R.string.calendar_day)
    object CalendarComment : Screens("calendarCommentScreen", R.string.calendar_comment)
    object StatisticCharts : Screens("statisticChartsScreen", R.string.statistc_charts)
}

val items = listOf(
    Screens.Home,
    Screens.Dose,
    Screens.InrMeasure,
    Screens.CalendarMonthView,
    Screens.StatisticCharts
)
