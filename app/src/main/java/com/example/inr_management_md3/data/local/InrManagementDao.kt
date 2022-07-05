/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries
 * and frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.inr_management_md3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.inr_management_md3.data.datamodels.DosageMedicamentType
import com.example.inr_management_md3.data.datamodels.Medicament
import kotlinx.coroutines.flow.Flow

@Dao
interface InrManagementDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMedicament(medicament: Medicament)

    @Insert(onConflict = REPLACE)
    suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType)

    @Query("SELECT * FROM medicament")
    fun getAllMedicaments(): Flow<List<Medicament>>

    @Query("SELECT * FROM dosage_medicament_type")
    fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>
}
