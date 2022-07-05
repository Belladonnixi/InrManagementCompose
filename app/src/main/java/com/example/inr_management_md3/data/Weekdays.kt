package com.example.inr_management_md3.data

import com.example.inr_management_md3.R

data class Weekdays(
    val days: Int
)

object LoadWeekdays {
    val weekdays = listOf(
        Weekdays(R.string.monday),
        Weekdays(R.string.tuesday),
        Weekdays(R.string.wednesday),
        Weekdays(R.string.thursday),
        Weekdays(R.string.thursday),
        Weekdays(R.string.friday),
        Weekdays(R.string.saturday),
        Weekdays(R.string.sunday)
    )
}
