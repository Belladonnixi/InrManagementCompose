/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inr_management_md3.data.datamodels.*
import com.example.inr_management_md3.data.local.InrManagementDao

@Database(
    entities = [
        Medicament::class,
        MedicamentDosage::class,
        DosageMedicamentType::class,
        BaseMedicationInterval::class,
        BaseMedicationWeekdays::class,
        InrMeasuringResult::class,
        Patient::class,
        Taking::class,
        TemoraryMedicationAdjustment::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun inrManagementDao(): InrManagementDao
}
