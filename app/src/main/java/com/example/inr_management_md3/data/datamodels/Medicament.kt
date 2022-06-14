package com.example.inr_management_md3.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicament(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val type: String = ""
)
