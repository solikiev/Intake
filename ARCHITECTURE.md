# Intake App - Architecture Documentation

## Overview

Intake is a comprehensive nutrition tracking Android application built with modern Android development practices using Jetpack Compose, Room Database, and MVVM architecture.

## Architecture Pattern: MVVM

The app follows the Model-View-ViewModel (MVVM) architecture pattern:

```
View (Compose UI) ←→ ViewModel ←→ Repository ←→ Database (Room)
```

### Benefits
- Clear separation of concerns
- Testable business logic
- Reactive UI updates via StateFlow
- Lifecycle-aware components

## Core Components

### 1. Data Layer

#### Database (Room)
Located in: `data/database/`

**Entities:**
- `DailyTarget`: Stores daily nutrition targets (min-max ranges)
- `Meal`: Stores meal data with planned and actual values
- `Template`: Stores saved meal plan templates
- `Settings`: Stores app settings (gym day mode, current date)

**DAOs (Data Access Objects):**
- `DailyTargetDao`: CRUD operations for daily targets
- `MealDao`: CRUD operations for meals
- `TemplateDao`: Template management
- `SettingsDao`: Settings management

All DAO methods use Kotlin Flows for reactive data observation.

#### Repository Pattern
Located in: `data/repository/IntakeRepository.kt`

The repository acts as a single source of truth, abstracting data sources from the ViewModel. It provides:
- Clean API for data operations
- Centralized data access
- Easy to test and maintain

### 2. ViewModel Layer

Located in: `viewmodel/IntakeViewModel.kt`

**Responsibilities:**
- Manages UI state via `IntakeUiState` data class
- Handles business logic
- Coordinates between Repository and UI
- Manages coroutines for async operations

**Key Functions:**
- `loadDataForDate()`: Loads all data for a specific date
- `setGymDay()`: Toggles between gym/rest day modes
- `saveDailyTarget()`: Persists daily targets
- `saveMeal()`: Saves meal data
- `saveTemplate()`: Creates a new template
- `loadTemplate()`: Applies a template to current day
- `copyFromDate()`: Copies planned values from another day

**State Management:**
Uses `StateFlow<IntakeUiState>` for reactive UI updates. The UI automatically recomposes when state changes.

### 3. UI Layer (Jetpack Compose)

#### Theme System
Located in: `ui/theme/`

- `Color.kt`: Color definitions including success/warning/error colors
- `Theme.kt`: Material Design 3 theme setup with dark/light mode support
- `Type.kt`: Typography definitions

#### Screens
Located in: `ui/screens/`

1. **HomeScreen**: Main dashboard
   - Displays current date and gym day toggle
   - Shows daily targets with progress indicators
   - Lists all 8 meals with done status
   - Real-time progress tracking

2. **DailyTargetsScreen**: Edit daily nutrition targets
   - Min-max range inputs for all 5 nutrients
   - Date-specific targets
   - Validation and save functionality

3. **MealEditScreen**: Edit individual meals
   - Planned ranges input
   - Actual values input
   - Done checkbox
   - Real-time validation

4. **CalendarScreen**: Monthly calendar view
   - Visual representation of past days
   - Color-coded performance indicators
   - Month navigation
   - Date selection

5. **TemplatesScreen**: Template management
   - List saved templates
   - Save current day as template
   - Load templates
   - Delete templates

6. **HistoryScreen**: Historical data view
   - Scrollable list of past days
   - Quick access to historical data
   - Date selection for details

7. **CopyDayScreen**: Copy planned values
   - Select source date
   - Preview data
   - Confirmation dialog
   - Copies only planned values

#### Reusable Components
Located in: `ui/components/`

- `NutrientRangeInput`: Min-max range input field
- `NutrientActualInput`: Single value input field
- `ProgressIndicator`: Visual progress bar with color coding
- `MealCard`: Meal display card with summary
- `CalendarDayCell`: Individual calendar day cell

#### Navigation
Located in: `ui/navigation/NavGraph.kt`

Uses Jetpack Compose Navigation with:
- Bottom navigation bar (Home, Calendar, Templates, History)
- Screen-level navigation
- Argument passing for detail screens
- Back stack management

## Data Flow

### Creating a New Meal Entry

1. User navigates to HomeScreen
2. User taps on a meal card
3. Navigation takes user to MealEditScreen
4. User inputs planned ranges and actual values
5. User taps "Save"
6. ViewModel calls `saveMeal()`
7. Repository saves to database via MealDao
8. Database emits updated data via Flow
9. ViewModel updates UI state
10. UI automatically recomposes with new data

### Loading a Template

1. User navigates to TemplatesScreen
2. User taps "Load" on a template
3. Confirmation dialog appears
4. User confirms
5. ViewModel calls `loadTemplate(templateId)`
6. Repository fetches template from database
7. ViewModel parses JSON data
8. ViewModel saves daily target and meals for current date
9. Database flows emit updated data
10. UI navigates to HomeScreen with loaded data

## Key Features Implementation

### 1. Gym Day / Rest Day Toggle

The app supports two meal name configurations:
- **Gym Day**: Shows "Intra workout" and "Post workout" for slots 2 & 3
- **Rest Day**: Shows "Morning snack 1" and "Morning snack 2" for slots 2 & 3

Implementation:
- Toggle stored in Settings entity
- When toggled, ViewModel updates meal names in database
- UI automatically reflects changes via StateFlow

### 2. Template System

Templates store complete day configurations including:
- Daily target ranges (as JSON)
- All 8 meals with planned ranges (as JSON)
- Template name and creation date

Templates are loaded by:
1. Parsing stored JSON
2. Creating new entities with current date
3. Clearing actual values (keeping only planned ranges)
4. Inserting into database

### 3. Progress Tracking

Real-time progress calculation:
- Sums actual values from all meals
- Compares against daily targets
- Applies color coding:
  - Green: Within range
  - Yellow: Within 10% of range
  - Red: Outside range

### 4. Calendar Color Coding

Days are color-coded based on performance:
- **Green**: All 5 nutrients within target ranges
- **Yellow**: 1-2 nutrients outside ranges  
- **Red**: 3+ nutrients outside ranges

Calculation done in ViewModel's `calculateDayStatus()` function.

### 5. Date-based Data Storage

All data is date-keyed:
- Each day has independent targets and meals
- Targets can be changed per day
- Historical data preserved
- Easy to navigate between dates

## Database Schema Details

### DailyTarget Table
```kotlin
@Entity(tableName = "daily_targets")
data class DailyTarget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,              // yyyy-MM-dd format
    val carbsMin: Float,
    val carbsMax: Float,
    val proteinMin: Float,
    val proteinMax: Float,
    val fatMin: Float,
    val fatMax: Float,
    val caloriesMin: Float,
    val caloriesMax: Float,
    val fiberMin: Float,
    val fiberMax: Float
)
```

### Meal Table
```kotlin
@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,              // yyyy-MM-dd format
    val mealSlot: Int,             // 1-8
    val mealName: String,          // Meal display name
    // Planned ranges
    val carbsPlannedMin: Float,
    val carbsPlannedMax: Float,
    val proteinPlannedMin: Float,
    val proteinPlannedMax: Float,
    val fatPlannedMin: Float,
    val fatPlannedMax: Float,
    val caloriesPlannedMin: Float,
    val caloriesPlannedMax: Float,
    val fiberPlannedMin: Float,
    val fiberPlannedMax: Float,
    // Actual values
    val carbsActual: Float,
    val proteinActual: Float,
    val fatActual: Float,
    val caloriesActual: Float,
    val fiberActual: Float,
    val isDone: Boolean
)
```

## Dependency Injection

Currently using manual dependency injection:
- Database instance created in MainActivity
- Repository created with DAO dependencies
- ViewModel created via ViewModelFactory

For production, consider using Hilt/Dagger for automated DI.

## Testing Strategy

Recommended testing approach:

1. **Unit Tests**: Test ViewModel logic
   - Use fake repository
   - Test state updates
   - Test business logic

2. **Integration Tests**: Test Repository and Database
   - Use in-memory database
   - Test CRUD operations
   - Test Flow emissions

3. **UI Tests**: Test Compose screens
   - Use Compose testing library
   - Test user interactions
   - Test navigation

## Performance Considerations

1. **Database Queries**: All async with coroutines
2. **UI Updates**: Efficient recomposition with StateFlow
3. **Memory**: Lazy loading where appropriate
4. **Images**: Minimal assets, vector icons preferred

## Future Enhancements

Potential improvements:
- Food database integration
- Barcode scanning
- Meal photo attachments
- Export/import functionality
- Cloud sync
- Nutritional insights and analytics
- Widget support
- Wear OS companion app

## Security Considerations

- All data stored locally
- No network communication (currently)
- User data privacy maintained
- Consider encryption for sensitive data if extended

## Accessibility

Material Design 3 provides:
- Screen reader support
- High contrast mode
- Large text support
- Touch target sizes

Additional improvements needed:
- Content descriptions for all interactive elements
- Keyboard navigation support
- Color-blind friendly indicators

## Localization

Current: English only

For internationalization:
- All strings in strings.xml
- Date formatting with locale awareness
- Number formatting considerations
- RTL layout support

## Version History

- v1.0.0: Initial release with all core features
