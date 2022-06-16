/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "patient",
    foreignKeys = [
        ForeignKey(
            entity = Medicament_Dosage::class,
            parentColumns = ["id_medicament_dosage"],
            childColumns = ["medicament_dosage_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.RESTRICT
        )
    ]
)
data class Patient(
    @PrimaryKey(autoGenerate = true)
    val id_Patient: Long = 0,
    val account_Id: Long = 0,
    val target_range_from: Float = 0f,
    val target_range_to: Float = 0f,
    val medicament_dosage_id: Long = 0,
)
