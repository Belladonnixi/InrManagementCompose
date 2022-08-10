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
import java.time.LocalDate

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

    @Insert(onConflict = REPLACE)
    suspend fun addComment(comment: Comment)

    @Insert(onConflict = REPLACE)
    suspend fun addMeasureResult(measureResult: InrMeasuringResult)

    @Insert(onConflict = REPLACE)
    suspend fun addTaking(taking: Taking)

    @Insert(onConflict = REPLACE)
    suspend fun addBaseMedicationWeekly(bmw: BaseMedicationWeekdays)

    /**
     *  Selects
     */

    @Query("SELECT * FROM medicament_type")
    fun getAllMedicaments(): Flow<List<MedicamentType>>

    @Query("SELECT * FROM dosage_medicament_type")
    fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>

    @Query("SELECT * FROM target_range ORDER BY id_target_range DESC LIMIT 1")
    fun getLastTargetRange(): Flow<TargetRange>

    @Query("SELECT * FROM patient ORDER BY id_patient DESC LIMIT 1")
    fun getLastPatient(): Flow<Patient>

    @Query("SELECT * FROM medicament_dosage WHERE medicament_type_id = :idMedicamentType")
    fun getMedicamentDosageIdOfSelectedMedicamentType(idMedicamentType: Long): Flow<MedicamentDosage>

    @Query("SELECT * FROM taking_alarm ORDER BY id_taking_alarm DESC LIMIT 1")
    fun getLastTakingAlarmId(): Flow<TakingAlarm>

    @Query("SELECT * FROM measure_alarm ORDER BY id_measure_alarm DESC LIMIT 1")
    fun getLastMeasureAlarm(): Flow<MeasureAlarm>

    @Query("SELECT * FROM medicament_type WHERE id_medicament_type = :id")
    fun getTypeName(id: Long): Flow<MedicamentType>

    @Query("SELECT * FROM medicament_dosage WHERE id_medicament_dosage = :id")
    fun getMedicamentDosageWhereIdMatches(id: Long): Flow<MedicamentDosage>

    @Query("SELECT * FROM taking_alarm WHERE patient_id = :id ORDER BY id_taking_alarm DESC LIMIT 1")
    fun getLastTakingAlarmWherePatientIdMatches(id: Long): Flow<TakingAlarm>

    @Query("SELECT * FROM taking_alarm ORDER BY id_taking_alarm DESC LIMIT 1")
    fun getLastTakingAlarm(): Flow<TakingAlarm>

    @Query("SELECT * FROM comment WHERE comment_date = :date")
    fun getCommentOfTheDay(date: LocalDate): Flow<Comment>

    @Query("SELECT * FROM comment ORDER BY id_comment DESC LIMIT 1")
    fun getLastComment(): Flow<Comment>

    @Query("SELECT * FROM taking ORDER BY id_taking DESC LIMIT 1")
    fun getLastTaking(): Flow<Taking>

    @Query("SELECT * FROM base_medication_weekdays ORDER BY id_base_medication_weekdays DESC LIMIT 1")
    fun getLastBaseMedicationWeekdays(): Flow<BaseMedicationWeekdays>

    /**
     *  Booleans
     */

    @Query("SELECT EXISTS (SELECT id_target_range FROM target_range WHERE id_target_range= :id)")
    fun checkIfTargetRangeExists(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_patient FROM patient WHERE id_patient = :id)")
    fun checkIfPatientExists(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT medicament_dosage_id FROM patient WHERE id_patient = :id)")
    fun checkIfMedicamentDosageIdExistsInPatient(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_taking_alarm FROM taking_alarm WHERE id_taking_alarm = :id)")
    fun checkIfTakingAlarmIsSet(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_taking_alarm FROM taking_alarm WHERE patient_id = :id)")
    fun checkIfTakingAlarmIsSetForPatient(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_measure_alarm FROM measure_alarm WHERE id_measure_alarm = :id)")
    fun checkIfMeasureAlarmIsSet(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT comment_date FROM comment WHERE comment_date = :date)")
    fun checkIfThereIsACommentForTheDay(date: LocalDate): Boolean

    @Query("SELECT EXISTS (SELECT comment_id FROM patient WHERE id_patient = :id)")
    fun checkIfCommentIdIsInPatient(id: Long): Boolean

    @Query("SELECT EXISTS (SELECT id_taking FROM taking WHERE id_taking = :id)")
    fun checkIfTakingExists(id: Long): Boolean

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

    @Query("UPDATE patient SET comment_id = :commentId WHERE id_patient = :id")
    fun updatePatientCommentId(commentId: Long?, id: Long)

    @Query("UPDATE comment SET patient_id = :patientId WHERE id_comment = :id")
    fun updateCommentPatientId(patientId: Long?, id: Long)

    @Query("UPDATE comment SET `comment-day` = :comment WHERE id_comment = :id")
    fun updateCommentTextOfTheDay(comment: String, id: Long)

    @Query("UPDATE taking SET base_medication_weekdays_id = :bmw WHERE id_taking = :id")
    fun updateTakingBaseMedicationWeekdaysId(bmw: Long, id: Long)
}
