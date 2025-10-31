package com.solikiev.intake.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solikiev.intake.data.database.entities.Meal
import com.solikiev.intake.ui.theme.GreenSuccess

@Composable
fun MealCard(
    meal: Meal,
    onMealClick: () -> Unit,
    onDoneToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onMealClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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
                    text = meal.mealName,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = { onDoneToggle(!meal.isDone) }) {
                    Icon(
                        imageVector = if (meal.isDone) Icons.Filled.CheckCircle else Icons.Outlined.Circle,
                        contentDescription = if (meal.isDone) "Done" else "Not done",
                        tint = if (meal.isDone) GreenSuccess else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Show planned ranges
            Text(
                text = "Planned:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "C: ${meal.carbsPlannedMin.toInt()}-${meal.carbsPlannedMax.toInt()}g | " +
                        "P: ${meal.proteinPlannedMin.toInt()}-${meal.proteinPlannedMax.toInt()}g | " +
                        "F: ${meal.fatPlannedMin.toInt()}-${meal.fatPlannedMax.toInt()}g",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Show actual values
            Text(
                text = "Actual:",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "C: ${meal.carbsActual.toInt()}g | " +
                        "P: ${meal.proteinActual.toInt()}g | " +
                        "F: ${meal.fatActual.toInt()}g | " +
                        "Cal: ${meal.caloriesActual.toInt()} | " +
                        "Fiber: ${meal.fiberActual.toInt()}g",
                style = MaterialTheme.typography.bodySmall,
                color = if (meal.isDone) GreenSuccess else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
