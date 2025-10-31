package com.solikiev.intake.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.solikiev.intake.data.database.dao.*
import com.solikiev.intake.data.database.entities.*

@Database(
    entities = [DailyTarget::class, Meal::class, Template::class, Settings::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyTargetDao(): DailyTargetDao
    abstract fun mealDao(): MealDao
    abstract fun templateDao(): TemplateDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "intake_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
