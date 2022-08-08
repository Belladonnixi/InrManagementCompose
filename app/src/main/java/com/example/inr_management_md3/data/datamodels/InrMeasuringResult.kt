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
import java.time.LocalDate
import java.time.LocalTime

@Entity(
    tableName = "inr_measuring_result",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"]
        ),
        ForeignKey(
            entity = MeasureAlarm::class,
            parentColumns = ["id_measure_alarm"],
            childColumns = ["measure_alarm_id"]
        )
    ]
)
data class InrMeasuringResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_inr_measuring_result")
    val idInrMeasuringResult: Long = 0,
    @ColumnInfo(name = "patient_id")
    var patientId: Long? = 0,
    @ColumnInfo(name = "measure_alarm_id")
    var measureAlarmId: Long = 0,
    @ColumnInfo(name = "date")
    var date: LocalDate? = null,
    @ColumnInfo(name = "time")
    var time: LocalTime? = null,
    @ColumnInfo(name = "measuring_result")
    var measuringResult: Float = 0f
)
