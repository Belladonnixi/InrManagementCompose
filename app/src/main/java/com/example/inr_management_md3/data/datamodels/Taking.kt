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
package com.example.inr_management_md3.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "taking",
    foreignKeys = [
        ForeignKey(
            entity = BaseMedicationWeekdays::class,
            parentColumns = ["id_base_medication_weekdays"],
            childColumns = ["base_medication_weekdays_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = BaseMedicationInterval::class,
            parentColumns = ["id_base_medication_interval"],
            childColumns = ["base_medication_interval_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Taking(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_taking")
    val idTaking: Long = 0,
    @ColumnInfo(name = "base_medication_weekdays_id")
    val baseMedicationWeekdaysId: Long = 0,
    @ColumnInfo(name = "base_medication_interval_id")
    val baseMedicationIntervalId: Long = 0,
    @ColumnInfo(name = "patient_id")
    val patientId: Long = 0,
    @ColumnInfo(name = "time_specified")
    val timeSpecified: Long = 0,
    @ColumnInfo(name = "taking_time")
    val takingTime: Long = 0,
    @ColumnInfo(name = "taking_date")
    val takingDate: Long = 0,
    @ColumnInfo(name = "comment_date")
    val commentDate: Long = 0,
    @ColumnInfo(name = "comment_day")
    val commentDay: String = ""
)
