package com.example.stethemcitatas.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quote: String = "",
    @ColumnInfo(name = "saved")
    var isSaved: Int = 0
)