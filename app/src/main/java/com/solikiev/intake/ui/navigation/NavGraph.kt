package com.solikiev.intake.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solikiev.intake.data.database.entities.Meal
import com.solikiev.intake.ui.screens.*
import com.solikiev.intake.viewmodel.IntakeViewModel

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Calendar : Screen("calendar", "Calendar")
    object Templates : Screen("templates", "Templates")
    object History : Screen("history", "History")
    object DailyTargets : Screen("daily_targets", "Daily Targets")
    object MealEdit : Screen("meal_edit/{mealId}", "Edit Meal")
    object CopyDay : Screen("copy_day", "Copy Day")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntakeNavHost(
    viewModel: IntakeViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()
    var selectedMeal by remember { mutableStateOf<Meal?>(null) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = currentRoute == Screen.Home.route,
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    label = { Text("Calendar") },
                    selected = currentRoute == Screen.Calendar.route,
                    onClick = {
                        navController.navigate(Screen.Calendar.route) {
                            popUpTo(Screen.Home.route)
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = null) },
                    label = { Text("Templates") },
                    selected = currentRoute == Screen.Templates.route,
                    onClick = {
                        viewModel.loadTemplates()
                        navController.navigate(Screen.Templates.route) {
                            popUpTo(Screen.Home.route)
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("History") },
                    selected = currentRoute == Screen.History.route,
                    onClick = {
                        navController.navigate(Screen.History.route) {
                            popUpTo(Screen.Home.route)
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    uiState = uiState,
                    onGymDayToggle = { viewModel.setGymDay(it) },
                    onEditTargets = { navController.navigate(Screen.DailyTargets.route) },
                    onMealClick = { meal ->
                        selectedMeal = meal
                        navController.navigate("meal_edit/${meal.id}")
                    },
                    onMealDoneToggle = { mealId, isDone ->
                        viewModel.updateMealDoneStatus(mealId, isDone)
                    }
                )
            }

            composable(Screen.DailyTargets.route) {
                DailyTargetsScreen(
                    currentTarget = uiState.dailyTarget,
                    currentDate = uiState.currentDate,
                    onSave = { viewModel.saveDailyTarget(it) },
                    onBack = { navController.popBackStack() }
                )
            }

            composable("meal_edit/{mealId}") {
                selectedMeal?.let { meal ->
                    MealEditScreen(
                        meal = meal,
                        onSave = { viewModel.saveMeal(it) },
                        onBack = { navController.popBackStack() }
                    )
                }
            }

            composable(Screen.Calendar.route) {
                CalendarScreen(
                    onDateSelected = { date ->
                        viewModel.loadDataForDate(date)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Templates.route) {
                TemplatesScreen(
                    templates = uiState.templates,
                    onSaveTemplate = { name ->
                        viewModel.saveTemplate(name)
                        viewModel.loadTemplates()
                    },
                    onLoadTemplate = { templateId ->
                        viewModel.loadTemplate(templateId)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onDeleteTemplate = { templateId ->
                        viewModel.deleteTemplate(templateId)
                        viewModel.loadTemplates()
                    }
                )
            }

            composable(Screen.History.route) {
                HistoryScreen(
                    dates = uiState.allDates,
                    onDateSelected = { date ->
                        viewModel.loadDataForDate(date)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.CopyDay.route) {
                CopyDayScreen(
                    availableDates = uiState.allDates,
                    currentDate = uiState.currentDate,
                    onCopyFromDate = { sourceDate ->
                        viewModel.copyFromDate(sourceDate, uiState.currentDate)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
