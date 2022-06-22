/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "base_medication_interval",
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
data class BaseMedicationInterval(
    @PrimaryKey(autoGenerate = true)
    val id_base_medication_interval: Long = 0,
    val dosage_on_taking_days: Float = 0f,
    val taking_days_row: Int = 0,
    val taking_break_days_row: Int = 0,
    val patient_id: Long = 0,
    val medicament_dosage_id: Long = 0
)
