/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.repository

import com.example.inr_management_md3.data.AppDataBase
import com.example.inr_management_md3.data.datamodels.DosageMedicamentType
import com.example.inr_management_md3.data.datamodels.Medicament
import kotlinx.coroutines.flow.Flow

interface InrManagementRepository {
    suspend fun addMedicament(medicament: Medicament)
    suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType)
    suspend fun getAllMedicaments(): Flow<List<Medicament>>
    suspend fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>
}

class InrManagementRepositoryImpl(private val appDataBase: AppDataBase) : InrManagementRepository {
    override suspend fun addMedicament(medicament: Medicament) {
        appDataBase.inrManagementDao().addMedicament(medicament)
    }

    override suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType) {
        appDataBase.inrManagementDao().addDosageMedicamentType(dosageMedicamentType)
    }

    override suspend fun getAllMedicaments(): Flow<List<Medicament>> =
        appDataBase.inrManagementDao().getAllMedicaments()

    override suspend fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>> =
        appDataBase.inrManagementDao().getAllDosageMedicamentTypes()
}
