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
import androidx.room.PrimaryKey

@Entity(
    tableName = "dosage_medicament_type",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = com.example.inr_management_md3.data.datamodels.Medicament::class,
            parentColumns = ["id_medicament"],
            childColumns = ["medicament_id"],
            onUpdate = androidx.room.ForeignKey.RESTRICT,
            onDelete = androidx.room.ForeignKey.RESTRICT
        )
    ]
)
data class DosageMedicamentType(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_dosage_medicament_type")
    val idDosageMedicamentType: Long = 0,
    @ColumnInfo(name = "divisibility")
    val divisibility: Float = 0f,
    @ColumnInfo(name = "medicament_id")
    val medicamentId: String = ""
)
