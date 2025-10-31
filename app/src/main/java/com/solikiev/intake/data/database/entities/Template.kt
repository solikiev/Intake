package com.solikiev.intake.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "templates")
data class Template(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val templateName: String,
    val createdDate: String,
    val dailyTargetsJson: String,
    val mealsJson: String
)
