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

@Entity(
    tableName = "temporary_medication_adjustment",
    foreignKeys = [
        ForeignKey(
            entity = InrMeasuringResult::class,
            parentColumns = ["id_inr_measuring_result"],
            childColumns = ["inr_measuring_result_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class TemporaryMedicationAdjustment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_temporary_medication_adjustment")
    val idTemporaryMedicationAdjustment: Long = 0,
    @ColumnInfo(name = "inr_measuring_result_id")
    var inrMeasuringResultId: Long = 0,
    @ColumnInfo(name = "date")
    var date: LocalDate? = null,
    @ColumnInfo(name = "dosage")
    var dosage: String = ""
)
