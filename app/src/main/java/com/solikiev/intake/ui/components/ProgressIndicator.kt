package com.solikiev.intake.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solikiev.intake.ui.theme.GreenSuccess
import com.solikiev.intake.ui.theme.RedError
import com.solikiev.intake.ui.theme.YellowWarning

@Composable
fun ProgressIndicator(
    label: String,
    actual: Float,
    min: Float,
    max: Float,
    modifier: Modifier = Modifier
) {
    val progress = if (max > 0) (actual / max).coerceIn(0f, 1f) else 0f
    val color = when {
        actual < min -> RedError
        actual in min..max -> GreenSuccess
        actual > max && actual <= max * 1.1f -> YellowWarning
        else -> RedError
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${actual.toInt()} / ${min.toInt()}-${max.toInt()}",
                style = MaterialTheme.typography.bodyMedium,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = color
        )
    }
}
