/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "temporary_medication_adjustment",
    foreignKeys = [
        ForeignKey(
            entity = Inr_Measuring_Result::class,
            parentColumns = ["id_inr_measuring_result"],
            childColumns = ["inr_measuring_result_id"],
            onUpdate = ForeignKey.RESTRICT,
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Temorary_Medication_Adjustment(
    @PrimaryKey(autoGenerate = true)
    val id_temporary_medication_adjustment: Long = 0,
    val inr_measuring_reseult_id: Long = 0,
    val date: Date? = null,
    val dosage: Float = 0f
)
