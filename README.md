# Intake - Daily Nutrition Tracker

A comprehensive Android application for tracking daily nutritional intake with flexible meal planning and templating features.

## Features

- **Daily Nutrition Targets**: Track 5 key nutrients (Carbs, Protein, Fat, Calories, Fiber) with customizable min-max ranges
- **Flexible Meal System**: 8 meals per day with gym day/rest day modes
- **Template System**: Save and load meal planning templates
- **Calendar View**: Visual monthly calendar with color-coded performance indicators
- **History Tracking**: View past days and their nutritional data
- **Copy Functionality**: Copy planned values from previous days
- **Real-time Progress**: Visual progress bars with color-coded status indicators

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material Design 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Build Instructions

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with API level 34

### Setup

1. Clone the repository:
```bash
git clone https://github.com/solikiev/Intake.git
cd Intake
```

2. Open the project in Android Studio:
   - File → Open → Select the `Intake` directory

3. Sync Gradle files:
   - Android Studio should automatically prompt to sync
   - Or manually: File → Sync Project with Gradle Files

4. Build the project:
```bash
./gradlew build
```

5. Run on an emulator or device:
   - Click the "Run" button in Android Studio
   - Or use command line: `./gradlew installDebug`

## Project Structure

```
app/src/main/java/com/solikiev/intake/
├── data/
│   ├── database/
│   │   ├── AppDatabase.kt
│   │   ├── entities/         # Room entities
│   │   └── dao/              # Data Access Objects
│   └── repository/
│       └── IntakeRepository.kt
├── ui/
│   ├── theme/                # Material Design theme
│   ├── screens/              # Compose screens
│   ├── components/           # Reusable UI components
│   └── navigation/           # Navigation graph
├── viewmodel/
│   └── IntakeViewModel.kt
└── MainActivity.kt
```

## Usage

### Home Screen
- Toggle between Gym Day and Rest Day modes
- View daily targets and progress
- Access all 8 meals
- Mark meals as done

### Daily Targets
- Set min-max ranges for each nutrient
- Targets are saved per date
- Can be edited anytime

### Meal Tracking
- Edit planned ranges for each meal
- Input actual values consumed
- Mark meals as completed

### Templates
- Save current day configuration as a template
- Load templates to quickly set up a day
- Manage saved templates

### Calendar
- Monthly view with color indicators:
  - 🟢 Green: All nutrients within range
  - 🟡 Yellow: 1-2 nutrients outside range
  - 🔴 Red: 3+ nutrients outside range
- Tap any date to view details

### History
- Scrollable list of past days
- Tap to view full breakdown

## Database Schema

### Tables
- `daily_targets`: Stores daily nutrient targets
- `meals`: Stores meal data with planned and actual values
- `templates`: Stores saved configuration templates
- `settings`: Stores app settings (gym day mode, current date)

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.