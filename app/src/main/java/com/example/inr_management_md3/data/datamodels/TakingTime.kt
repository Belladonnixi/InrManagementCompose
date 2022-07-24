package com.example.inr_management_md3.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taking_time")
data class TakingTime(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_taking_time")
    val idTakingTime: Long = 0,
    @ColumnInfo(name = "taking_time")
    val takingTime: Long = 0,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = 0
)
