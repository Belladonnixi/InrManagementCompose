/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "medicament_dosage",
    foreignKeys = [
        ForeignKey(
            entity = Medicament::class,
            parentColumns = ["id_medicament"],
            childColumns = ["medicament_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = DosageMedicamentType::class,
            parentColumns = ["id_dosage_medicament_type"],
            childColumns = ["dosage_medicament_type_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
    ]
)
data class MedicamentDosage(
    @PrimaryKey
    val id_medicament_dosage: Long = 0,

    val medicament_id: Long = 0,
    val dosage_medicament_type_id: Long = 0
)
