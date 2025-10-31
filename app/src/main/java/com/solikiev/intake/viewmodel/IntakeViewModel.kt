package com.solikiev.intake.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.solikiev.intake.data.database.entities.*
import com.solikiev.intake.data.repository.IntakeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class IntakeUiState(
    val currentDate: String = "",
    val isGymDay: Boolean = false,
    val dailyTarget: DailyTarget? = null,
    val meals: List<Meal> = emptyList(),
    val templates: List<Template> = emptyList(),
    val allDates: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class IntakeViewModel(private val repository: IntakeRepository) : ViewModel() {
    private val gson = Gson()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val _uiState = MutableStateFlow(IntakeUiState())
    val uiState: StateFlow<IntakeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            val today = dateFormat.format(Date())
            _uiState.update { it.copy(currentDate = today, isLoading = true) }

            // Load settings
            repository.getSettings().collect { settings ->
                if (settings == null) {
                    // Initialize default settings
                    repository.insertSettings(
                        Settings(
                            id = 1,
                            isGymDay = false,
                            currentDate = today
                        )
                    )
                    _uiState.update { it.copy(isGymDay = false) }
                } else {
                    _uiState.update { 
                        it.copy(
                            currentDate = settings.currentDate,
                            isGymDay = settings.isGymDay
                        ) 
                    }
                }
            }

            // Load data for current date
            loadDataForDate(today)
        }
    }

    fun loadDataForDate(date: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(currentDate = date, isLoading = true) }

            // Load daily target
            repository.getTargetByDate(date).collect { target ->
                _uiState.update { state ->
                    state.copy(dailyTarget = target ?: createDefaultTarget(date))
                }
            }

            // Load meals
            repository.getMealsByDate(date).collect { meals ->
                val currentMeals = if (meals.isEmpty()) {
                    createDefaultMeals(date, _uiState.value.isGymDay)
                } else {
                    meals
                }
                _uiState.update { it.copy(meals = currentMeals, isLoading = false) }
            }

            // Load all dates for history
            repository.getDistinctDates().collect { dates ->
                _uiState.update { it.copy(allDates = dates) }
            }
        }
    }

    fun setGymDay(isGymDay: Boolean) {
        viewModelScope.launch {
            _uiState.update { it.copy(isGymDay = isGymDay) }
            
            // Update settings
            repository.updateSettings(
                Settings(
                    id = 1,
                    isGymDay = isGymDay,
                    currentDate = _uiState.value.currentDate
                )
            )

            // Update meal names for slots 2 and 3
            val currentDate = _uiState.value.currentDate
            val meals = _uiState.value.meals.toMutableList()
            
            if (meals.size >= 3) {
                meals[1] = meals[1].copy(
                    mealName = if (isGymDay) "Intra workout" else "Morning snack 1"
                )
                meals[2] = meals[2].copy(
                    mealName = if (isGymDay) "Post workout" else "Morning snack 2"
                )
                
                repository.updateMeal(meals[1])
                repository.updateMeal(meals[2])
            }
        }
    }

    fun saveDailyTarget(target: DailyTarget) {
        viewModelScope.launch {
            repository.insertTarget(target)
        }
    }

    fun saveMeal(meal: Meal) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    fun updateMealDoneStatus(mealId: Long, isDone: Boolean) {
        viewModelScope.launch {
            val meal = _uiState.value.meals.find { it.id == mealId }
            meal?.let {
                repository.updateMeal(it.copy(isDone = isDone))
            }
        }
    }

    fun saveTemplate(templateName: String) {
        viewModelScope.launch {
            val currentDate = _uiState.value.currentDate
            val target = _uiState.value.dailyTarget
            val meals = _uiState.value.meals

            if (target != null) {
                val template = Template(
                    templateName = templateName,
                    createdDate = dateFormat.format(Date()),
                    dailyTargetsJson = gson.toJson(target),
                    mealsJson = gson.toJson(meals)
                )
                repository.insertTemplate(template)
            }
        }
    }

    fun loadTemplate(templateId: Long) {
        viewModelScope.launch {
            repository.getTemplateById(templateId).collect { template ->
                if (template != null) {
                    val currentDate = _uiState.value.currentDate
                    
                    // Parse and save daily target
                    val target = gson.fromJson(template.dailyTargetsJson, DailyTarget::class.java)
                    repository.insertTarget(target.copy(id = 0, date = currentDate))
                    
                    // Parse and save meals (only planned values)
                    val meals = gson.fromJson(template.mealsJson, Array<Meal>::class.java).toList()
                    meals.forEach { meal ->
                        repository.insertMeal(
                            meal.copy(
                                id = 0,
                                date = currentDate,
                                carbsActual = 0f,
                                proteinActual = 0f,
                                fatActual = 0f,
                                caloriesActual = 0f,
                                fiberActual = 0f,
                                isDone = false
                            )
                        )
                    }
                }
            }
        }
    }

    fun deleteTemplate(templateId: Long) {
        viewModelScope.launch {
            repository.deleteTemplateById(templateId)
        }
    }

    fun loadTemplates() {
        viewModelScope.launch {
            repository.getAllTemplates().collect { templates ->
                _uiState.update { it.copy(templates = templates) }
            }
        }
    }

    fun copyFromDate(sourceDate: String, targetDate: String) {
        viewModelScope.launch {
            // Copy daily target
            repository.getTargetByDate(sourceDate).first()?.let { target ->
                repository.insertTarget(target.copy(id = 0, date = targetDate))
            }

            // Copy meals (only planned values)
            repository.getMealsByDate(sourceDate).first().forEach { meal ->
                repository.insertMeal(
                    meal.copy(
                        id = 0,
                        date = targetDate,
                        carbsActual = 0f,
                        proteinActual = 0f,
                        fatActual = 0f,
                        caloriesActual = 0f,
                        fiberActual = 0f,
                        isDone = false
                    )
                )
            }
        }
    }

    private fun createDefaultTarget(date: String): DailyTarget {
        return DailyTarget(
            date = date,
            carbsMin = 200f,
            carbsMax = 300f,
            proteinMin = 120f,
            proteinMax = 180f,
            fatMin = 50f,
            fatMax = 80f,
            caloriesMin = 2000f,
            caloriesMax = 2500f,
            fiberMin = 25f,
            fiberMax = 35f
        )
    }

    private fun createDefaultMeals(date: String, isGymDay: Boolean): List<Meal> {
        val mealNames = listOf(
            "Breakfast",
            if (isGymDay) "Intra workout" else "Morning snack 1",
            if (isGymDay) "Post workout" else "Morning snack 2",
            "Lunch",
            "Snack 1",
            "Snack 2",
            "Snack 3",
            "Dinner"
        )

        return mealNames.mapIndexed { index, name ->
            Meal(
                date = date,
                mealSlot = index + 1,
                mealName = name,
                carbsPlannedMin = 20f,
                carbsPlannedMax = 40f,
                proteinPlannedMin = 15f,
                proteinPlannedMax = 30f,
                fatPlannedMin = 5f,
                fatPlannedMax = 15f,
                caloriesPlannedMin = 200f,
                caloriesPlannedMax = 400f,
                fiberPlannedMin = 3f,
                fiberPlannedMax = 6f,
                carbsActual = 0f,
                proteinActual = 0f,
                fatActual = 0f,
                caloriesActual = 0f,
                fiberActual = 0f,
                isDone = false
            )
        }
    }

    fun calculateDayStatus(
        actualCarbs: Float, actualProtein: Float, actualFat: Float, 
        actualCalories: Float, actualFiber: Float,
        target: DailyTarget
    ): String {
        var outOfRange = 0
        
        if (actualCarbs < target.carbsMin || actualCarbs > target.carbsMax) outOfRange++
        if (actualProtein < target.proteinMin || actualProtein > target.proteinMax) outOfRange++
        if (actualFat < target.fatMin || actualFat > target.fatMax) outOfRange++
        if (actualCalories < target.caloriesMin || actualCalories > target.caloriesMax) outOfRange++
        if (actualFiber < target.fiberMin || actualFiber > target.fiberMax) outOfRange++
        
        return when {
            outOfRange == 0 -> "green"
            outOfRange <= 2 -> "yellow"
            else -> "red"
        }
    }
}
