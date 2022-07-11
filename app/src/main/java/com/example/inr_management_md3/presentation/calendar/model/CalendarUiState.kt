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

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

data class CalendarUiState(
    val selectedDate: LocalDate? = null,
    val animateDirection: AnimationDirection? = null
) {
    val isDateSelected: Boolean
        get() {
            return selectedDate != null
        }

    val selectedDateFormatted: String
        get() {
            if (selectedDate == null) return ""
            return selectedDate.format(SHORT_DATE_FORMAT)
        }

    fun isDateSelected(date: LocalDate): Boolean {
        if (selectedDate == null) return false
        if (selectedDate == date) return true
        if (date.isBefore(selectedDate) ||
            date.isAfter(selectedDate)
        ) return false
        return true
    }

    fun setDates(new: LocalDate?): CalendarUiState {
        return copy(selectedDate = new)
    }

    fun dayDelay(currentWeekStartDate: LocalDate): Int {
        if (selectedDate == null) return 0
        // if selected week contains start date, don't have any delay
        val endWeek = currentWeekStartDate.plusDays(6)
        return if (selectedDate.isBefore(currentWeekStartDate) || selectedDate.isAfter(endWeek)
        ) {
            abs(ChronoUnit.DAYS.between(currentWeekStartDate, selectedDate)).toInt()
        } else {
            0
        }
    }

    companion object {
        private val SHORT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd")
    }
}
