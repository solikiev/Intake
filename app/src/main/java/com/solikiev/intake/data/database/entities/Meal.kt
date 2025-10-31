package com.solikiev.intake.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val mealSlot: Int, // 1-8
    val mealName: String,
    val carbsPlannedMin: Float,
    val carbsPlannedMax: Float,
    val proteinPlannedMin: Float,
    val proteinPlannedMax: Float,
    val fatPlannedMin: Float,
    val fatPlannedMax: Float,
    val caloriesPlannedMin: Float,
    val caloriesPlannedMax: Float,
    val fiberPlannedMin: Float,
    val fiberPlannedMax: Float,
    val carbsActual: Float,
    val proteinActual: Float,
    val fatActual: Float,
    val caloriesActual: Float,
    val fiberActual: Float,
    val isDone: Boolean
)
