package com.example.inr_management_md3.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measure_alarm")
data class MeasureAlarm(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_measure_alarm")
    val idMeasureAlarm: Long = 0,
    @ColumnInfo(name = "every_x_days")
    val everyXDays: Int = 0,
    @ColumnInfo(name = "start_date")
    val startDate: Long = 0,
    @ColumnInfo(name = "measure_time")
    val measureTime: Long = 0,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = 0
)
