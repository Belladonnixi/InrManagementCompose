package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "medicament_name",
    foreignKeys = [
        ForeignKey(
            entity = Medicament::class,
            parentColumns = ["id_medicament"],
            childColumns = ["medicament_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class MedicamentName(
    @PrimaryKey(autoGenerate = true)
    val id_medicament_name: Long = 0,
    val medicament_id: Long = 0,
    val name: String = ""
)
