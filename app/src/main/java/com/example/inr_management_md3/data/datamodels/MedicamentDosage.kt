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
    tableName = "medicament_dosage",
    foreignKeys = [
        ForeignKey(
            entity = Medicament::class,
            parentColumns = ["id_medicament"],
            childColumns = ["medicament_id"]
        ),
        ForeignKey(
            entity = DosageMedicamentType::class,
            parentColumns = ["id_dosage_medicament_type"],
            childColumns = ["dosage_medicament_type_id"]
        )
    ]
)
data class MedicamentDosage(
    @PrimaryKey
    @ColumnInfo(name = "id_medicament_dosage")
    val id_medicament_dosage: Long = 0,
    @ColumnInfo(name = "medicament_id")
    val medicament_id: Long = 0,
    @ColumnInfo(name = "dosage_medicament_type_id")
    val dosageMedicamentTypeId: Long = 0
)
