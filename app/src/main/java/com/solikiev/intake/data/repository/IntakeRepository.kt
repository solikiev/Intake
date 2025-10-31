package com.solikiev.intake.data.repository

import com.solikiev.intake.data.database.dao.*
import com.solikiev.intake.data.database.entities.*
import kotlinx.coroutines.flow.Flow

class IntakeRepository(
    private val dailyTargetDao: DailyTargetDao,
    private val mealDao: MealDao,
    private val templateDao: TemplateDao,
    private val settingsDao: SettingsDao
) {
    // Daily Targets
    fun getTargetByDate(date: String): Flow<DailyTarget?> = dailyTargetDao.getTargetByDate(date)
    fun getAllTargets(): Flow<List<DailyTarget>> = dailyTargetDao.getAllTargets()
    suspend fun insertTarget(target: DailyTarget) = dailyTargetDao.insertTarget(target)
    suspend fun updateTarget(target: DailyTarget) = dailyTargetDao.updateTarget(target)
    suspend fun deleteTarget(target: DailyTarget) = dailyTargetDao.deleteTarget(target)
    suspend fun deleteTargetByDate(date: String) = dailyTargetDao.deleteTargetByDate(date)

    // Meals
    fun getMealsByDate(date: String): Flow<List<Meal>> = mealDao.getMealsByDate(date)
    fun getMealByDateAndSlot(date: String, slot: Int): Flow<Meal?> = 
        mealDao.getMealByDateAndSlot(date, slot)
    fun getAllMeals(): Flow<List<Meal>> = mealDao.getAllMeals()
    suspend fun insertMeal(meal: Meal) = mealDao.insertMeal(meal)
    suspend fun insertMeals(meals: List<Meal>) = mealDao.insertMeals(meals)
    suspend fun updateMeal(meal: Meal) = mealDao.updateMeal(meal)
    suspend fun deleteMeal(meal: Meal) = mealDao.deleteMeal(meal)
    suspend fun deleteMealsByDate(date: String) = mealDao.deleteMealsByDate(date)
    suspend fun deleteMealByDateAndSlot(date: String, slot: Int) = 
        mealDao.deleteMealByDateAndSlot(date, slot)
    fun getDistinctDates(): Flow<List<String>> = mealDao.getDistinctDates()

    // Templates
    fun getAllTemplates(): Flow<List<Template>> = templateDao.getAllTemplates()
    fun getTemplateById(id: Long): Flow<Template?> = templateDao.getTemplateById(id)
    suspend fun insertTemplate(template: Template) = templateDao.insertTemplate(template)
    suspend fun updateTemplate(template: Template) = templateDao.updateTemplate(template)
    suspend fun deleteTemplate(template: Template) = templateDao.deleteTemplate(template)
    suspend fun deleteTemplateById(id: Long) = templateDao.deleteTemplateById(id)

    // Settings
    fun getSettings(): Flow<Settings?> = settingsDao.getSettings()
    suspend fun insertSettings(settings: Settings) = settingsDao.insertSettings(settings)
    suspend fun updateSettings(settings: Settings) = settingsDao.updateSettings(settings)
}
