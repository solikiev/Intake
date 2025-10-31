package com.solikiev.intake.data.database.dao

import androidx.room.*
import com.solikiev.intake.data.database.entities.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meals WHERE date = :date ORDER BY mealSlot")
    fun getMealsByDate(date: String): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE date = :date AND mealSlot = :slot")
    fun getMealByDateAndSlot(date: String, slot: Int): Flow<Meal?>

    @Query("SELECT * FROM meals ORDER BY date DESC, mealSlot")
    fun getAllMeals(): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<Meal>)

    @Update
    suspend fun updateMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("DELETE FROM meals WHERE date = :date")
    suspend fun deleteMealsByDate(date: String)

    @Query("DELETE FROM meals WHERE date = :date AND mealSlot = :slot")
    suspend fun deleteMealByDateAndSlot(date: String, slot: Int)

    @Query("SELECT DISTINCT date FROM meals ORDER BY date DESC")
    fun getDistinctDates(): Flow<List<String>>
}
