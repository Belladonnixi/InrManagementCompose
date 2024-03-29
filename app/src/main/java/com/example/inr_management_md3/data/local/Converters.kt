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
package com.example.inr_management_md3.data.local

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

private const val SEPARATOR = ","

class Converters {
    @TypeConverter
    fun fromStringToTime(value: String): LocalTime? {
        return LocalTime.parse(value)
    }

    @TypeConverter
    fun fromTimeToString(time: LocalTime?): String {
        return time.toString()
    }

    @TypeConverter
    fun fromStringToLocalDate(value: String): LocalDate? {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun fromLocalDateToString(date: LocalDate?): String {
        return date.toString()
    }

    @TypeConverter
    fun baseMedicationDoseToString(baseMedication: MutableList<String>?): String? =
        baseMedication?.joinToString(separator = SEPARATOR) { it }

    @TypeConverter
    fun stringToBaseMedicationDose(baseMedication: String?): MutableList<String>? =
        baseMedication?.split(SEPARATOR)?.map { (it) }?.toMutableList()
}
