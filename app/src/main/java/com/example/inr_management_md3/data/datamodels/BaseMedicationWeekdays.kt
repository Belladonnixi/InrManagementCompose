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
    tableName = "base_medication_weekdays",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"]
        ),
        ForeignKey(
            entity = MedicamentDosage::class,
            parentColumns = ["id_medicament_dosage"],
            childColumns = ["medicament_dosage_id"]
        )
    ]
)
data class BaseMedicationWeekdays(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_base_medication_weekdays")
    val idBaseMedicationWeekdays: Long = 0,
    @ColumnInfo(name = "patient_id")
    val patientId: Long? = 0,
    @ColumnInfo(name = "medicament_dosage_id")
    val medicamentDosageId: Long = 0,
    @ColumnInfo(name = "weekdays_dosage")
    val weekdaysDosage: Float = 0f
)
