/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicament")
data class Medicament(
    @PrimaryKey(autoGenerate = true)
    val id_medicament: Long = 0,
    val name: String = "",
    val type: String = ""
)
