package com.example.inr_management_md3.data.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_comment")
    val idComment: Long = 0,
    @ColumnInfo(name = "comment_date")
    val commentDate: Long = 0,
    @ColumnInfo(name = "comment-day")
    val commentDay: String = ""
)
