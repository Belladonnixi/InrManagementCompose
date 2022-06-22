/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "base_medication_weekdays",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = MedicamentDosage::class,
            parentColumns = ["id_medicament_dosage"],
            childColumns = ["medicament_dosage_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class BaseMedicationWeekdays(
    @PrimaryKey(autoGenerate = true)
    val id_base_medication_weekdays: Long = 0,
    val weekdays_dosage: Float = 0f,
    val patient_id: Long = 0,
    val medicament_dosage_id: Long = 0
)
