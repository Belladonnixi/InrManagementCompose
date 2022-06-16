/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "taking",
    foreignKeys = [
        ForeignKey(
            entity = Base_Medication_Weekdays::class,
            parentColumns = ["id_base_medication_weekdays"],
            childColumns = ["base_medication_weekdays_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = Base_Medication_Interval::class,
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
data class Taking(
    @PrimaryKey(autoGenerate = true)
    val id_taking: Long = 0,
    val base_medication_weekdays_id: Long = 0,
    val base_medication_interval_id: Long = 0,
    val patient_id: Long = 0,
    val time_specified: Date? = null,
    val taking_time: Date? = null,
    val taking_date: Date? = null,
    val comment_day: String = ""
)
