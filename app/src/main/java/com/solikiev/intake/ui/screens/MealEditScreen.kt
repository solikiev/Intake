package com.solikiev.intake.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solikiev.intake.data.database.entities.Meal
import com.solikiev.intake.ui.components.NutrientActualInput
import com.solikiev.intake.ui.components.NutrientRangeInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealEditScreen(
    meal: Meal,
    onSave: (Meal) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var carbsPlannedMin by remember { mutableStateOf(meal.carbsPlannedMin) }
    var carbsPlannedMax by remember { mutableStateOf(meal.carbsPlannedMax) }
    var proteinPlannedMin by remember { mutableStateOf(meal.proteinPlannedMin) }
    var proteinPlannedMax by remember { mutableStateOf(meal.proteinPlannedMax) }
    var fatPlannedMin by remember { mutableStateOf(meal.fatPlannedMin) }
    var fatPlannedMax by remember { mutableStateOf(meal.fatPlannedMax) }
    var caloriesPlannedMin by remember { mutableStateOf(meal.caloriesPlannedMin) }
    var caloriesPlannedMax by remember { mutableStateOf(meal.caloriesPlannedMax) }
    var fiberPlannedMin by remember { mutableStateOf(meal.fiberPlannedMin) }
    var fiberPlannedMax by remember { mutableStateOf(meal.fiberPlannedMax) }

    var carbsActual by remember { mutableStateOf(meal.carbsActual) }
    var proteinActual by remember { mutableStateOf(meal.proteinActual) }
    var fatActual by remember { mutableStateOf(meal.fatActual) }
    var caloriesActual by remember { mutableStateOf(meal.caloriesActual) }
    var fiberActual by remember { mutableStateOf(meal.fiberActual) }
    var isDone by remember { mutableStateOf(meal.isDone) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit ${meal.mealName}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Planned Values",
                style = MaterialTheme.typography.titleMedium
            )

            NutrientRangeInput(
                label = "Carbs (g)",
                minValue = carbsPlannedMin,
                maxValue = carbsPlannedMax,
                onMinChange = { carbsPlannedMin = it },
                onMaxChange = { carbsPlannedMax = it }
            )

            NutrientRangeInput(
                label = "Protein (g)",
                minValue = proteinPlannedMin,
                maxValue = proteinPlannedMax,
                onMinChange = { proteinPlannedMin = it },
                onMaxChange = { proteinPlannedMax = it }
            )

            NutrientRangeInput(
                label = "Fat (g)",
                minValue = fatPlannedMin,
                maxValue = fatPlannedMax,
                onMinChange = { fatPlannedMin = it },
                onMaxChange = { fatPlannedMax = it }
            )

            NutrientRangeInput(
                label = "Calories",
                minValue = caloriesPlannedMin,
                maxValue = caloriesPlannedMax,
                onMinChange = { caloriesPlannedMin = it },
                onMaxChange = { caloriesPlannedMax = it }
            )

            NutrientRangeInput(
                label = "Fiber (g)",
                minValue = fiberPlannedMin,
                maxValue = fiberPlannedMax,
                onMinChange = { fiberPlannedMin = it },
                onMaxChange = { fiberPlannedMax = it }
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Actual Values",
                style = MaterialTheme.typography.titleMedium
            )

            NutrientActualInput(
                label = "Carbs (g)",
                value = carbsActual,
                onValueChange = { carbsActual = it }
            )

            NutrientActualInput(
                label = "Protein (g)",
                value = proteinActual,
                onValueChange = { proteinActual = it }
            )

            NutrientActualInput(
                label = "Fat (g)",
                value = fatActual,
                onValueChange = { fatActual = it }
            )

            NutrientActualInput(
                label = "Calories",
                value = caloriesActual,
                onValueChange = { caloriesActual = it }
            )

            NutrientActualInput(
                label = "Fiber (g)",
                value = fiberActual,
                onValueChange = { fiberActual = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Mark as Done",
                    style = MaterialTheme.typography.bodyLarge
                )
                Switch(
                    checked = isDone,
                    onCheckedChange = { isDone = it }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val updatedMeal = meal.copy(
                        carbsPlannedMin = carbsPlannedMin,
                        carbsPlannedMax = carbsPlannedMax,
                        proteinPlannedMin = proteinPlannedMin,
                        proteinPlannedMax = proteinPlannedMax,
                        fatPlannedMin = fatPlannedMin,
                        fatPlannedMax = fatPlannedMax,
                        caloriesPlannedMin = caloriesPlannedMin,
                        caloriesPlannedMax = caloriesPlannedMax,
                        fiberPlannedMin = fiberPlannedMin,
                        fiberPlannedMax = fiberPlannedMax,
                        carbsActual = carbsActual,
                        proteinActual = proteinActual,
                        fatActual = fatActual,
                        caloriesActual = caloriesActual,
                        fiberActual = fiberActual,
                        isDone = isDone
                    )
                    onSave(updatedMeal)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Meal")
            }
        }
    }
}
