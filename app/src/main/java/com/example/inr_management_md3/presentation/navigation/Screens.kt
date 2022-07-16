/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are released under various
 * Open-Source licenses. Use of those libraries and frameworks are governed by their own individual
 * licenses.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.inr_management_md3.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.inr_management_md3.R

/**
 * class for keeping navigation simple and for running bottomNavBar
 */
sealed class Screens(val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int) {
    object Home : Screens("home", R.string.home, R.drawable.ic_outline_home_28)
    object Settings : Screens("settings", R.string.settings, R.drawable.ic_launcher_foreground)
    object Dose : Screens("dose", R.string.dose, R.drawable.pill)
    object SetMeasure : Screens("setMeasure", R.string.set_measure, R.drawable.ic_launcher_foreground)
    object SetTargetRange : Screens("setTargetRange", R.string.set_target_range, R.drawable.ic_launcher_foreground)
    object SetMedicament : Screens("setMedicament", R.string.set_medicament, R.drawable.ic_launcher_foreground)
    object SetDoseWeek : Screens("setDoseWeek", R.string.set_dose_week, R.drawable.pill)
    object SetDoseInterval : Screens("setDoseInterval", R.string.set_dose_interval, R.drawable.pill)
    object DoseAdjustment : Screens("doseAdjustment", R.string.dose_adjustment, R.drawable.pill)
    object InrMeasure : Screens("inrMeasure", R.string.measure, R.drawable.messure)
    object CalendarMonthView : Screens("calendarMonthView", R.string.calendar, R.drawable.ic_baseline_calendar_month_24)
    object CalendarListView : Screens("calendarListView", R.string.calendar_list_view, R.drawable.ic_baseline_calendar_month_24)
    object CalendarDay : Screens("calendarDay", R.string.calendar_day, R.drawable.ic_baseline_calendar_month_24)
    object CalendarComment : Screens("calendarCommentScreen", R.string.calendar_comment, R.drawable.ic_baseline_calendar_month_24)
    object StatisticCharts : Screens("statisticChartsScreen", R.string.statistc_charts, R.drawable.statistics)
    object About : Screens("about", R.string.about, R.drawable.ic_launcher_foreground)
}

val items = listOf(
    Screens.Home,
    Screens.Dose,
    Screens.InrMeasure,
    Screens.CalendarMonthView,
    Screens.StatisticCharts
)
