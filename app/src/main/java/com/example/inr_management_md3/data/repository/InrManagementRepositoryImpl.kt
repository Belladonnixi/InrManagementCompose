package com.example.inr_management_md3.data.repository

import com.example.inr_management_md3.data.AppDataBase
import com.example.inr_management_md3.data.datamodels.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class InrManagementRepositoryImpl(private val appDataBase: AppDataBase) : InrManagementRepository {
    /**
     *  Inserts
     */
    override suspend fun addMedicament(medicamentType: MedicamentType) {
        appDataBase.inrManagementDao().addMedicament(medicamentType)
    }

    override suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType) {
        appDataBase.inrManagementDao().addDosageMedicamentType(dosageMedicamentType)
    }

    override suspend fun addTargetRange(targetRange: TargetRange) {
        appDataBase.inrManagementDao().addTargetRange(targetRange)
    }

    override suspend fun addMedicamentDosage(medicamentDosage: MedicamentDosage) {
        appDataBase.inrManagementDao().addMedicamentDosage(medicamentDosage)
    }

    override suspend fun addTakingAlarm(takingAlarm: TakingAlarm) {
        appDataBase.inrManagementDao().addTakingAlarm(takingAlarm)
    }

    override suspend fun addPatient(patient: Patient) {
        appDataBase.inrManagementDao().addPatient(patient)
    }

    override suspend fun addMeasureAlarm(measureAlarm: MeasureAlarm) {
        appDataBase.inrManagementDao().addMeasureAlarm(measureAlarm)
    }

    override suspend fun addComment(comment: Comment) {
        appDataBase.inrManagementDao().addComment(comment)
    }

    /**
     *  Selects
     */
    override fun getAllMedicaments(): Flow<List<MedicamentType>> =
        appDataBase.inrManagementDao().getAllMedicaments()

    override fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>> =
        appDataBase.inrManagementDao().getAllDosageMedicamentTypes()

    override fun getLastTargetRange(): Flow<TargetRange> =
        appDataBase.inrManagementDao().getLastTargetRange()

    override fun getLastPatient(): Flow<Patient> =
        appDataBase.inrManagementDao().getLastPatient()

    override fun getMedicamentDosageId(idMedicamentType: Long): Flow<MedicamentDosage> =
        appDataBase.inrManagementDao()
            .getMedicamentDosageIdOfSelectedMedicamentType(idMedicamentType)

    override fun getLastTakingAlarmId(): Flow<TakingAlarm> =
        appDataBase.inrManagementDao().getLastTakingAlarmId()

    override fun getLastMeasureAlarm(): Flow<MeasureAlarm> =
        appDataBase.inrManagementDao().getLastMeasureAlarm()

    override fun getTypeName(id: Long): Flow<MedicamentType> =
        appDataBase.inrManagementDao().getTypeName(id)

    override fun getMedicamentDosageWhereIdMatches(id: Long): Flow<MedicamentDosage> =
        appDataBase.inrManagementDao().getMedicamentDosageWhereIdMatches(id)

    override fun getLastTakingAlarmWherePatientIdMatches(id: Long): Flow<TakingAlarm> =
        appDataBase.inrManagementDao().getLastTakingAlarmWherePatientIdMatches(id)

    override fun getLastTakingAlarm(): Flow<TakingAlarm> =
        appDataBase.inrManagementDao().getLastTakingAlarm()

    override fun getCommentOfTheDay(date: LocalDate): Flow<Comment> =
        appDataBase.inrManagementDao().getCommentOfTheDay(date)

    override fun getLastComment(): Flow<Comment> =
        appDataBase.inrManagementDao().getLastComment()

    /**
     *  Booleans
     */
    override fun checkIfTargetRangeExists(): Boolean =
        appDataBase.inrManagementDao().checkIfTargetRangeExists(1)

    override fun checkIfPatientExists(): Boolean =
        appDataBase.inrManagementDao().checkIfPatientExists(1)

    override fun checkIfMedicamentDosageIdExistsInPatient(id: Long): Boolean =
        appDataBase.inrManagementDao().checkIfMedicamentDosageIdExistsInPatient(id)

    override fun checkIfTakingAlarmIsSet(): Boolean =
        appDataBase.inrManagementDao().checkIfTakingAlarmIsSet(1)

    override fun checkIfTakingAlarmIsSetForPatient(id: Long): Boolean =
        appDataBase.inrManagementDao().checkIfTakingAlarmIsSetForPatient(id)

    override fun checkIfMeasureAlarmIsSet(): Boolean =
        appDataBase.inrManagementDao().checkIfMeasureAlarmIsSet(1)

    override fun checkIfThereIsACommentForTheDay(date: LocalDate): Boolean =
        appDataBase.inrManagementDao().checkIfThereIsACommentForTheDay(date)

    override fun checkIfCommentIdIsInPatient(id: Long): Boolean =
        appDataBase.inrManagementDao().checkIfCommentIdIsInPatient(id)

    /**
     *  Updates
     */
    override fun updatePatientMedicamentDosageId(medicamentDosageId: Long?, id: Long) =
        appDataBase.inrManagementDao().updatePatientMedicamentDosageId(medicamentDosageId, id)

    override fun updatePatientTargetRangeId(targetRangeId: Long?, id: Long) =
        appDataBase.inrManagementDao().updatePatientTargetRangeId(targetRangeId, id)

    override fun updateTargetRangePatientId(patientId: Long?, id: Long) =
        appDataBase.inrManagementDao().updateTargetRangePatientId(patientId, id)

    override fun updateTakingAlarmPatientId(patientId: Long?, id: Long) =
        appDataBase.inrManagementDao().updateTakingAlarmPatientId(patientId, id)

    override fun updateMeasureAlarmPatientId(patientId: Long?, id: Long) =
        appDataBase.inrManagementDao().updateMeasureAlarmPatientId(patientId, id)

    override fun updatePatientCommentId(commentId: Long?, id: Long) =
        appDataBase.inrManagementDao().updatePatientCommentId(commentId, id)

    override fun updateCommentPatientId(patientId: Long?, id: Long) =
        appDataBase.inrManagementDao().updateCommentPatientId(patientId, id)
}
