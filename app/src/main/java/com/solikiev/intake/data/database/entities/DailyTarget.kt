package com.solikiev.intake.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_targets")
data class DailyTarget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val carbsMin: Float,
    val carbsMax: Float,
    val proteinMin: Float,
    val proteinMax: Float,
    val fatMin: Float,
    val fatMax: Float,
    val caloriesMin: Float,
    val caloriesMax: Float,
    val fiberMin: Float,
    val fiberMax: Float
)
