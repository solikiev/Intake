package com.solikiev.intake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.solikiev.intake.data.database.AppDatabase
import com.solikiev.intake.data.repository.IntakeRepository
import com.solikiev.intake.ui.navigation.IntakeNavHost
import com.solikiev.intake.ui.theme.IntakeTheme
import com.solikiev.intake.viewmodel.IntakeViewModel
import com.solikiev.intake.viewmodel.IntakeViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: IntakeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize database and repository
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = IntakeRepository(
            dailyTargetDao = database.dailyTargetDao(),
            mealDao = database.mealDao(),
            templateDao = database.templateDao(),
            settingsDao = database.settingsDao()
        )

        // Create ViewModel
        viewModel = ViewModelProvider(
            this,
            IntakeViewModelFactory(repository)
        )[IntakeViewModel::class.java]

        setContent {
            IntakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IntakeNavHost(viewModel = viewModel)
                }
            }
        }
    }
}
