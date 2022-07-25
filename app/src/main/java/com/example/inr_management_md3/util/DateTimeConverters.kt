package com.example.inr_management_md3.util

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class DateTimeConverters {

    fun zonedDateTimeToDate(): Date {
        val zonedDateTime: ZonedDateTime = ZonedDateTime.now()
        val instant: Instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }

    fun dateToZonedDateTime(): ZonedDateTime {
        val date: Date = Date()
        val instant: Instant = date.toInstant()
        val zoneId: ZoneId = TimeZone.getDefault().toZoneId()
        return ZonedDateTime.ofInstant(instant, zoneId)
    }
}
