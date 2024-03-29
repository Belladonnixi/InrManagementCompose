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
    tableName = "dosage_medicament_type",
    foreignKeys = [
        ForeignKey(
            entity = MedicamentType::class,
            parentColumns = ["id_medicament_type"],
            childColumns = ["medicament_type_id"]
        )
    ]
)
data class DosageMedicamentType(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_dosage_medicament_type")
    val idDosageMedicamentType: Long = 0,
    @ColumnInfo(name = "divisibility")
    val divisibility: Float = 0f,
    @ColumnInfo(name = "medicament_type_id")
    val medicamentTypeId: Long = 0,
    @ColumnInfo(name = "max_dosage")
    val maxDosage: Int = 0
)
