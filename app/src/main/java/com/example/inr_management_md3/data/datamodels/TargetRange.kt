package com.example.inr_management_md3.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "target_range",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class TargetRange(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_target_range")
    val idTargetRange: Long = 0,
    @ColumnInfo(name = "patient_id")
    val patientId: Long = 0,
    @ColumnInfo(name = "target_range_from")
    val targetRangeFrom: Int = 0,
    @ColumnInfo(name = "target_range_to")
    val targetRangeTo: Int = 0
)
