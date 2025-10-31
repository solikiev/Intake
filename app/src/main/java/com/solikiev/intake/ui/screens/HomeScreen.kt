package com.solikiev.intake.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solikiev.intake.data.database.entities.Meal
import com.solikiev.intake.ui.components.MealCard
import com.solikiev.intake.ui.components.ProgressIndicator
import com.solikiev.intake.viewmodel.IntakeUiState

@Composable
fun HomeScreen(
    uiState: IntakeUiState,
    onGymDayToggle: (Boolean) -> Unit,
    onEditTargets: () -> Unit,
    onMealClick: (Meal) -> Unit,
    onMealDoneToggle: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with date and gym day toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = uiState.currentDate,
                style = MaterialTheme.typography.headlineMedium
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (uiState.isGymDay) "Gym Day" else "Rest Day",
                    style = MaterialTheme.typography.bodyMedium
                )
                Switch(
                    checked = uiState.isGymDay,
                    onCheckedChange = onGymDayToggle,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily targets summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Daily Targets",
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = onEditTargets) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit targets"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                uiState.dailyTarget?.let { target ->
                    val totalActual = calculateTotalActual(uiState.meals)
                    
                    ProgressIndicator(
                        label = "Carbs",
                        actual = totalActual.carbs,
                        min = target.carbsMin,
                        max = target.carbsMax
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressIndicator(
                        label = "Protein",
                        actual = totalActual.protein,
                        min = target.proteinMin,
                        max = target.proteinMax
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressIndicator(
                        label = "Fat",
                        actual = totalActual.fat,
                        min = target.fatMin,
                        max = target.fatMax
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressIndicator(
                        label = "Calories",
                        actual = totalActual.calories,
                        min = target.caloriesMin,
                        max = target.caloriesMax
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ProgressIndicator(
                        label = "Fiber",
                        actual = totalActual.fiber,
                        min = target.fiberMin,
                        max = target.fiberMax
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Meals list
        Text(
            text = "Meals",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.meals) { meal ->
                MealCard(
                    meal = meal,
                    onMealClick = { onMealClick(meal) },
                    onDoneToggle = { isDone -> onMealDoneToggle(meal.id, isDone) }
                )
            }
        }
    }
}

data class TotalActual(
    val carbs: Float = 0f,
    val protein: Float = 0f,
    val fat: Float = 0f,
    val calories: Float = 0f,
    val fiber: Float = 0f
)

private fun calculateTotalActual(meals: List<Meal>): TotalActual {
    return TotalActual(
        carbs = meals.sumOf { it.carbsActual.toDouble() }.toFloat(),
        protein = meals.sumOf { it.proteinActual.toDouble() }.toFloat(),
        fat = meals.sumOf { it.fatActual.toDouble() }.toFloat(),
        calories = meals.sumOf { it.caloriesActual.toDouble() }.toFloat(),
        fiber = meals.sumOf { it.fiberActual.toDouble() }.toFloat()
    )
}
