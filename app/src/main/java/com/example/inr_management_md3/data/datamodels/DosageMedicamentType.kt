/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosage_medicament_type")
data class DosageMedicamentType(
    @PrimaryKey(autoGenerate = true)
    val id_dosage_medicament_type: Long = 0,
    val divisibility: Float = 0f,
    val type: String = ""
)
