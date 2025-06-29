package com.ramunissoft.authjwtapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "settings")
data class Setting (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val url: String,
)