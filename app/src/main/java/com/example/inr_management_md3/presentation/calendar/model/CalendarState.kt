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
package com.example.inr_management_md3.presentation.calendar.model

import androidx.compose.runtime.mutableStateOf
import com.example.inr_management_md3.util.getNumberWeeks
import java.time.LocalDate
import java.time.Period
import java.time.YearMonth

class CalendarState {
    val calendarUiState = mutableStateOf(CalendarUiState())
    val listMonths: List<Month>

    // Defaulting to start at 01/01 of current year
    private val calendarStartDate: LocalDate = LocalDate.now()
        .withMonth(1).withDayOfMonth(1)

    // Defaulting to 2 years from current date.
    private val calendarEndDate: LocalDate = LocalDate.now().plusYears(2)
        .withMonth(12).withDayOfMonth(31)

    private val periodBetweenCalendarStartEnd: Period = Period.between(
        calendarStartDate,
        calendarEndDate
    )

    init {
        val tempListMonths = mutableListOf<Month>()
        var startYearMonth = YearMonth.from(calendarStartDate)
        for (numberMonth in 0..periodBetweenCalendarStartEnd.toTotalMonths()) {
            val numberWeeks = startYearMonth.getNumberWeeks()
            val listWeekItems = mutableListOf<Week>()
            for (week in 0 until numberWeeks) {
                listWeekItems.add(
                    Week(
                        number = week,
                        yearMonth = startYearMonth
                    )
                )
            }
            val month = Month(startYearMonth, listWeekItems)
            tempListMonths.add(month)
            startYearMonth = startYearMonth.plusMonths(1)
        }
        listMonths = tempListMonths.toList()
    }

    fun setSelectedDay(newDate: LocalDate) {
        calendarUiState.value = updateSelectedDay(newDate)
    }

    private fun updateSelectedDay(newDate: LocalDate): CalendarUiState {
        val currentState = calendarUiState.value

        return when (val selectedDate = currentState.selectedDate) {
            null -> {
                currentState.setDates(newDate)
            }
            else -> {
                val animationDirection = if (newDate.isBefore(selectedDate)) {
                    AnimationDirection.BACKWARDS
                } else {
                    AnimationDirection.FORWARDS
                }
                this.calendarUiState.value = currentState.copy(
                    selectedDate = null,
                    animateDirection = animationDirection
                )
                updateSelectedDay(newDate = newDate)
            }
        }
    }

    companion object {
        const val DAYS_IN_WEEK = 7
    }
}
