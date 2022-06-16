/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.*

@Entity(tableName = "medicament_dosage")
data class Medicament_Dosage(
    @PrimaryKey
    val id_medicament_dosage: Long = 0,

    @Embedded
    val id_medicament: List<Medicament>,
    @Relation(
        parentColumn = "id_Medicament",
        entityColumn = "id_dosage_medicament_type"
    )
    val id_dosage_medicament_type: List<Dosage_Medicament_Type>,
)
