package com.solikiev.intake.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NutrientRangeInput(
    label: String,
    minValue: Float,
    maxValue: Float,
    onMinChange: (Float) -> Unit,
    onMaxChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var minText by remember(minValue) { mutableStateOf(minValue.toString()) }
    var maxText by remember(maxValue) { mutableStateOf(maxValue.toString()) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = minText,
                onValueChange = { newValue ->
                    minText = newValue
                    newValue.toFloatOrNull()?.let { onMinChange(it) }
                },
                label = { Text("Min") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Text("-", style = MaterialTheme.typography.bodyMedium)
            OutlinedTextField(
                value = maxText,
                onValueChange = { newValue ->
                    maxText = newValue
                    newValue.toFloatOrNull()?.let { onMaxChange(it) }
                },
                label = { Text("Max") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }
    }
}
