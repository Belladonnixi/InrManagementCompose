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
package com.example.inr_management_md3.data.repository

import com.example.inr_management_md3.data.datamodels.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface InrManagementRepository {
    /**
     *  Inserts
     */
    suspend fun addMedicament(medicamentType: MedicamentType)
    suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType)
    suspend fun addTargetRange(targetRange: TargetRange)
    suspend fun addMedicamentDosage(medicamentDosage: MedicamentDosage)
    suspend fun addTakingAlarm(takingAlarm: TakingAlarm)
    suspend fun addPatient(patient: Patient)
    suspend fun addMeasureAlarm(measureAlarm: MeasureAlarm)
    suspend fun addComment(comment: Comment)
    suspend fun addMeasureResult(measureResult: InrMeasuringResult)

    /**
     *  Selects
     */
    fun getAllMedicaments(): Flow<List<MedicamentType>>
    fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>
    fun getLastTargetRange(): Flow<TargetRange>
    fun getLastPatient(): Flow<Patient>
    fun getMedicamentDosageId(idMedicamentType: Long): Flow<MedicamentDosage>
    fun getLastTakingAlarmId(): Flow<TakingAlarm>
    fun getLastMeasureAlarm(): Flow<MeasureAlarm>
    fun getTypeName(id: Long): Flow<MedicamentType>
    fun getMedicamentDosageWhereIdMatches(id: Long): Flow<MedicamentDosage>
    fun getLastTakingAlarmWherePatientIdMatches(id: Long): Flow<TakingAlarm>
    fun getLastTakingAlarm(): Flow<TakingAlarm>
    fun getCommentOfTheDay(date: LocalDate): Flow<Comment>
    fun getLastComment(): Flow<Comment>

    /**
     *  Booleans
     */
    fun checkIfTargetRangeExists(): Boolean
    fun checkIfPatientExists(): Boolean
    fun checkIfMedicamentDosageIdExistsInPatient(id: Long): Boolean
    fun checkIfTakingAlarmIsSet(): Boolean
    fun checkIfTakingAlarmIsSetForPatient(id: Long): Boolean
    fun checkIfMeasureAlarmIsSet(): Boolean
    fun checkIfThereIsACommentForTheDay(date: LocalDate): Boolean
    fun checkIfCommentIdIsInPatient(id: Long): Boolean

    /**
     *  Updates
     */
    fun updatePatientMedicamentDosageId(medicamentDosageId: Long?, id: Long)
    fun updatePatientTargetRangeId(targetRangeId: Long?, id: Long)
    fun updateTargetRangePatientId(patientId: Long?, id: Long)
    fun updateTakingAlarmPatientId(patientId: Long?, id: Long)
    fun updateMeasureAlarmPatientId(patientId: Long?, id: Long)
    fun updatePatientCommentId(commentId: Long?, id: Long)
    fun updateCommentPatientId(patientId: Long?, id: Long)
    fun updateCommentTextOfTheDay(comment: String, id: Long)
}
