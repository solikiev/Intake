# Build Instructions for Intake App

## Prerequisites

To build this Android application, you need:

1. **Android Studio** (Hedgehog 2023.1.1 or later)
   - Download from: https://developer.android.com/studio

2. **JDK 17 or later**
   - Android Studio includes an embedded JDK
   - Or download from: https://adoptium.net/

3. **Android SDK**
   - Install via Android Studio's SDK Manager
   - Required SDK: API Level 34 (Android 14)
   - Minimum SDK: API Level 24 (Android 7.0)

4. **Internet Connection**
   - Required to download Gradle dependencies
   - Access needed to:
     - Google Maven Repository (dl.google.com)
     - Maven Central Repository (repo.maven.apache.org)

## Setup Steps

### 1. Clone the Repository
```bash
git clone https://github.com/solikiev/Intake.git
cd Intake
```

### 2. Open in Android Studio
- Launch Android Studio
- Click "Open" or "File → Open"
- Navigate to the cloned `Intake` directory
- Click "OK"

### 3. Wait for Gradle Sync
- Android Studio will automatically start syncing Gradle files
- This may take several minutes on first run as it downloads dependencies
- Watch the bottom status bar for progress

### 4. Enable Plugin Declarations
The `build.gradle.kts` file has plugin declarations commented out for compatibility with limited build environments. 

Before building in Android Studio, **uncomment** the plugin lines in `build.gradle.kts`:

```kotlin
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
}
```

### 5. Build the Project

#### Option A: Using Android Studio GUI
- Click "Build → Make Project" (Ctrl+F9 / Cmd+F9)
- Wait for the build to complete
- Check the Build tab for any errors

#### Option B: Using Command Line
```bash
# On macOS/Linux:
./gradlew build

# On Windows:
gradlew.bat build
```

### 6. Run the App

#### On an Emulator:
1. Click "Tools → Device Manager"
2. Create a new virtual device (if needed)
3. Select a device with API Level 24 or higher
4. Click the "Run" button (green play icon) or press Shift+F10

#### On a Physical Device:
1. Enable Developer Options on your Android device
2. Enable USB Debugging
3. Connect device via USB
4. Select your device from the device dropdown
5. Click the "Run" button

## Troubleshooting

### Gradle Sync Failed
- Check your internet connection
- Verify you can access:
  - https://dl.google.com
  - https://repo.maven.apache.org
- Try: File → Invalidate Caches and Restart

### SDK Not Found
- Open SDK Manager: Tools → SDK Manager
- Install Android SDK Platform 34
- Install Android SDK Build-Tools 34.0.0

### Kotlin Compiler Issues
- Ensure you're using Kotlin 1.9.20
- File → Project Structure → Project
- Check Kotlin version matches build.gradle.kts

### Build Errors
- Clean the project: Build → Clean Project
- Rebuild: Build → Rebuild Project
- Delete `.gradle` and `build` folders, then sync again

## Project Structure Verification

After successful setup, you should see:
```
Intake/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/solikiev/intake/
│   │       │   ├── data/
│   │       │   ├── ui/
│   │       │   ├── viewmodel/
│   │       │   └── MainActivity.kt
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## Building for Release

To create a release APK:

1. Generate a signing key (if you don't have one):
```bash
keytool -genkey -v -keystore intake-release-key.keystore -alias intake -keyalg RSA -keysize 2048 -validity 10000
```

2. Update `app/build.gradle.kts` with signing config

3. Build release APK:
```bash
./gradlew assembleRelease
```

4. Find the APK at:
```
app/build/outputs/apk/release/app-release.apk
```

## Additional Resources

- [Android Developer Documentation](https://developer.android.com/docs)
- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Material Design 3](https://m3.material.io/)

## Support

For issues or questions:
- Check the [GitHub Issues](https://github.com/solikiev/Intake/issues)
- Review Android Studio's Build Output for error details
- Ensure all prerequisites are correctly installed
