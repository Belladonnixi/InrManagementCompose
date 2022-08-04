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

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.inr_management_md3.data.datamodels.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InrManagementDao {

    /**
     *  Inserts
     */

    @Insert(onConflict = REPLACE)
    suspend fun addMedicament(medicamentType: MedicamentType)

    @Insert(onConflict = REPLACE)
    suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType)

    @Insert(onConflict = REPLACE)
    suspend fun addTargetRange(targetRange: TargetRange)

    @Insert(onConflict = REPLACE)
    suspend fun addMedicamentDosage(medicamentDosage: MedicamentDosage)

    @Insert(onConflict = REPLACE)
    suspend fun addTakingAlarm(takingAlarm: TakingAlarm)

    @Insert(onConflict = REPLACE)
    suspend fun addPatient(patient: Patient)

    @Insert(onConflict = REPLACE)
    suspend fun addMeasureAlarm(measureAlarm: MeasureAlarm)

    /**
     *  Selects
     */

    @Query("SELECT * FROM medicament_type")
    fun getAllMedicaments(): Flow<List<MedicamentType>>

    @Query("SELECT * FROM dosage_medicament_type")
    fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>

    @Query("SELECT * FROM target_range ORDER BY id_target_range DESC LIMIT 1")
    fun getLastTargetRange(): Flow<TargetRange>

    @Query("SELECT id_patient FROM patient ORDER BY id_patient DESC LIMIT 1")
    fun getLastPatientId(): Flow<Patient>

    @Query("SELECT * FROM medicament_dosage WHERE medicament_type_id = :idMedicamentType")
    fun getMedicamentDosageId(idMedicamentType: Long): Flow<MedicamentDosage>

    @Query("SELECT * FROM taking_alarm ORDER BY id_taking_alarm DESC LIMIT 1")
    fun getLastTakingAlarmId(): Flow<TakingAlarm>

    @Query("SELECT id_measure_alarm FROM measure_alarm ORDER BY id_measure_alarm DESC LIMIT 1")
    fun getLastMeasureAlarm(): Flow<MeasureAlarm>

    /**
     *  Booleans
     */

    @Query("SELECT EXISTS (SELECT id_target_range FROM target_range WHERE id_target_range= :id)")
    fun checkIfTargetRangeExists(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_patient FROM patient WHERE id_patient= :id)")
    fun checkIfPatientExists(id: Long): Boolean

    /**
     *  Updates
     */

    @Query("UPDATE patient SET medicament_dosage_id = :medicamentDosageId WHERE id_patient = :id")
    fun updatePatientMedicamentDosageId(medicamentDosageId: Long?, id: Long)

    @Query("UPDATE patient SET target_range_id = :targetRangeId WHERE id_patient = :id")
    fun updatePatientTargetRangeId(targetRangeId: Long?, id: Long)

    @Query("UPDATE target_range SET patient_id = :patientId WHERE id_target_range = :id")
    fun updateTargetRangePatientId(patientId: Long?, id: Long)

    @Query("UPDATE taking_alarm SET patient_id = :patientId WHERE id_taking_alarm = :id")
    fun updateTakingAlarmPatientId(patientId: Long?, id: Long)

    @Query("UPDATE measure_alarm SET patient_id = :patientId WHERE id_measure_alarm = :id")
    fun updateMeasureAlarmPatientId(patientId: Long?, id: Long)
}
