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
import com.solikiev.intake.data.database.entities.DailyTarget
import com.solikiev.intake.ui.components.NutrientRangeInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyTargetsScreen(
    currentTarget: DailyTarget?,
    currentDate: String,
    onSave: (DailyTarget) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var carbsMin by remember(currentTarget) { mutableStateOf(currentTarget?.carbsMin ?: 200f) }
    var carbsMax by remember(currentTarget) { mutableStateOf(currentTarget?.carbsMax ?: 300f) }
    var proteinMin by remember(currentTarget) { mutableStateOf(currentTarget?.proteinMin ?: 120f) }
    var proteinMax by remember(currentTarget) { mutableStateOf(currentTarget?.proteinMax ?: 180f) }
    var fatMin by remember(currentTarget) { mutableStateOf(currentTarget?.fatMin ?: 50f) }
    var fatMax by remember(currentTarget) { mutableStateOf(currentTarget?.fatMax ?: 80f) }
    var caloriesMin by remember(currentTarget) { mutableStateOf(currentTarget?.caloriesMin ?: 2000f) }
    var caloriesMax by remember(currentTarget) { mutableStateOf(currentTarget?.caloriesMax ?: 2500f) }
    var fiberMin by remember(currentTarget) { mutableStateOf(currentTarget?.fiberMin ?: 25f) }
    var fiberMax by remember(currentTarget) { mutableStateOf(currentTarget?.fiberMax ?: 35f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Daily Targets") },
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
                text = "Set your daily nutrient targets for $currentDate",
                style = MaterialTheme.typography.bodyLarge
            )

            NutrientRangeInput(
                label = "Carbs (g)",
                minValue = carbsMin,
                maxValue = carbsMax,
                onMinChange = { carbsMin = it },
                onMaxChange = { carbsMax = it }
            )

            NutrientRangeInput(
                label = "Protein (g)",
                minValue = proteinMin,
                maxValue = proteinMax,
                onMinChange = { proteinMin = it },
                onMaxChange = { proteinMax = it }
            )

            NutrientRangeInput(
                label = "Fat (g)",
                minValue = fatMin,
                maxValue = fatMax,
                onMinChange = { fatMin = it },
                onMaxChange = { fatMax = it }
            )

            NutrientRangeInput(
                label = "Calories",
                minValue = caloriesMin,
                maxValue = caloriesMax,
                onMinChange = { caloriesMin = it },
                onMaxChange = { caloriesMax = it }
            )

            NutrientRangeInput(
                label = "Fiber (g)",
                minValue = fiberMin,
                maxValue = fiberMax,
                onMinChange = { fiberMin = it },
                onMaxChange = { fiberMax = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val target = DailyTarget(
                        id = currentTarget?.id ?: 0,
                        date = currentDate,
                        carbsMin = carbsMin,
                        carbsMax = carbsMax,
                        proteinMin = proteinMin,
                        proteinMax = proteinMax,
                        fatMin = fatMin,
                        fatMax = fatMax,
                        caloriesMin = caloriesMin,
                        caloriesMax = caloriesMax,
                        fiberMin = fiberMin,
                        fiberMax = fiberMax
                    )
                    onSave(target)
                    onBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Targets")
            }
        }
    }
}
