package com.solikiev.intake.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solikiev.intake.data.database.entities.Template

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatesScreen(
    templates: List<Template>,
    onSaveTemplate: (String) -> Unit,
    onLoadTemplate: (Long) -> Unit,
    onDeleteTemplate: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSaveDialog by remember { mutableStateOf(false) }
    var showLoadDialog by remember { mutableStateOf<Long?>(null) }
    var showDeleteDialog by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Templates") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSaveDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Save template")
            }
        }
    ) { padding ->
        if (templates.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No templates saved yet.\nTap + to save current day as a template.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(templates) { template ->
                    TemplateCard(
                        template = template,
                        onLoad = { showLoadDialog = template.id },
                        onDelete = { showDeleteDialog = template.id }
                    )
                }
            }
        }
    }

    // Save template dialog
    if (showSaveDialog) {
        var templateName by remember { mutableStateOf("") }
        
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Save Template") },
            text = {
                Column {
                    Text("Enter a name for this template:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = templateName,
                        onValueChange = { templateName = it },
                        label = { Text("Template name") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (templateName.isNotBlank()) {
                            onSaveTemplate(templateName)
                            showSaveDialog = false
                        }
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Load template confirmation
    showLoadDialog?.let { templateId ->
        AlertDialog(
            onDismissRequest = { showLoadDialog = null },
            title = { Text("Load Template") },
            text = { Text("This will replace current day's planned values. Continue?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onLoadTemplate(templateId)
                        showLoadDialog = null
                    }
                ) {
                    Text("Load")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLoadDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Delete template confirmation
    showDeleteDialog?.let { templateId ->
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = { Text("Delete Template") },
            text = { Text("Are you sure you want to delete this template?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteTemplate(templateId)
                        showDeleteDialog = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun TemplateCard(
    template: Template,
    onLoad: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = template.templateName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Created: ${template.createdDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Row {
                Button(
                    onClick = onLoad,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Load")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}
