package com.solikiev.intake.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey
    val id: Long = 1,
    val isGymDay: Boolean,
    val currentDate: String
)
