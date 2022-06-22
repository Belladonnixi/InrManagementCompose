/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "inr_measuring_result",
    foreignKeys = [
        ForeignKey(
            entity = BaseMedicationWeekdays::class,
            parentColumns = ["id_base_medication_weekdays"],
            childColumns = ["base_medication_weekdays_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = BaseMedicationInterval::class,
            parentColumns = ["id_base_medication_interval"],
            childColumns = ["base_medication_interval_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id_patient"],
            childColumns = ["patient_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class InrMeasuringResult(
    @PrimaryKey(autoGenerate = true)
    val id_inr_measuring_result: Long = 0,
    val timestamp: String = "null",
    val time_specified: Long = 0,
    val measuring_result: Float = 0f,
    val base_medication_weekdays_id: Long = 0,
    val base_medication_interval_id: Long = 0,
    val patient_id: Long = 0
)
