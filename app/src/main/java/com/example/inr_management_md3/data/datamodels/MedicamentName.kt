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
    tableName = "medicament_name",
    foreignKeys = [
        ForeignKey(
            entity = Medicament::class,
            parentColumns = ["id_medicament"],
            childColumns = ["medicament_id"]
        )
    ]
)
data class MedicamentName(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_medicament_name")
    val idMedicamentName: Long = 0,
    @ColumnInfo(name = "medicament_id")
    val medicamentId: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = ""
)
