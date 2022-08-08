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
package com.example.inr_management_md3.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 *  Class for all date time converters between database and LocalDate, ZonedDate
 *  and Time in UI
 */

class DateTimeConverters {

    fun zonedDateTimeToDate(localDate: LocalDate): Date {
        val zonedDateTime: ZonedDateTime = ZonedDateTime.now()
        val instant: Instant = zonedDateTime.toInstant()
        return Date.from(instant)
    }

    fun dateToZonedDateTime(): ZonedDateTime {
        val date = Date()
        val instant: Instant = date.toInstant()
        val zoneId: ZoneId = TimeZone.getDefault().toZoneId()
        return ZonedDateTime.ofInstant(instant, zoneId)
    }
}
