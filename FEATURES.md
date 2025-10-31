# Intake App - Feature Documentation

## Complete Feature List

### 1. Daily Nutrition Tracking

#### Nutrient Types
The app tracks 5 essential nutrients:
- **Carbohydrates** (grams)
- **Protein** (grams)
- **Fat** (grams)
- **Calories**
- **Fiber** (grams)

#### Target Ranges
- Each nutrient has a minimum and maximum target
- Targets are date-specific (different targets for different days)
- Can be edited at any time
- Default values provided on first use

### 2. Flexible Meal System

#### 8 Daily Meals
1. **Breakfast** (fixed)
2. **Variable Slot 1**: Gym Day = "Intra workout" / Rest Day = "Morning snack 1"
3. **Variable Slot 2**: Gym Day = "Post workout" / Rest Day = "Morning snack 2"
4. **Lunch** (fixed)
5. **Snack 1** (fixed)
6. **Snack 2** (fixed)
7. **Snack 3** (fixed)
8. **Dinner** (fixed)

#### Meal Data
Each meal contains:
- **Planned Values**: Min-max ranges for all 5 nutrients
- **Actual Values**: Exact amounts consumed
- **Done Status**: Checkbox to mark meal as completed

#### Gym Day Mode
- Toggle switch on home screen
- Automatically updates meal names for slots 2 and 3
- Setting persists across app restarts
- Visual indicator shows current mode

### 3. Template System

#### Save Template
- Captures current day's configuration
- Includes all planned ranges (not actual values)
- Stores daily targets
- Stores all 8 meal planned ranges
- Requires custom template name
- Saves creation date

#### Load Template
- Browse saved templates
- Preview template details (name, date)
- Confirmation dialog before loading
- Replaces current day's planned values
- Preserves any existing actual values
- Updates daily targets

#### Template Management
- List all saved templates
- Delete unwanted templates
- Templates sorted by creation date
- No limit on number of templates

### 4. Calendar View

#### Visual Calendar
- Monthly grid layout
- Navigate between months
- Current month shown by default
- Today's date highlighted

#### Color-Coded Days
- **🟢 Green**: All 5 nutrients within target ranges
- **🟡 Yellow**: 1-2 nutrients outside ranges
- **🔴 Red**: 3 or more nutrients outside ranges
- **⚪ Gray**: No data for that day

#### Day Selection
- Tap any date to view details
- Automatically navigates to Home Screen
- Loads that day's data
- Shows meals and progress for selected date

### 5. History View

#### Historical Data
- Chronological list of past days
- Shows date for each entry
- Displays total actual values
- Color indicator for each day's performance
- Sorted newest to oldest

#### Navigation
- Tap any day to view full details
- Seamless transition to Home Screen
- All meal data preserved
- Edit historical data if needed

### 6. Copy Day Functionality

#### Copy Planned Values
- Select any previous date
- Preview that day's configuration
- Copy only planned min-max ranges
- Actual values are NOT copied
- Daily targets also copied

#### Use Cases
- Repeat successful meal plans
- Quick setup for similar days
- Maintain consistency
- Save time on data entry

#### Confirmation
- Shows source and target dates
- Warns about overwriting current planned values
- Preserves existing actual values
- Can cancel at any time

### 7. Progress Tracking

#### Visual Indicators
- Real-time progress bars for each nutrient
- Current value vs. target range display
- Color-coded status:
  - 🟢 Green: Within range
  - 🟡 Yellow: Within 10% of range  
  - 🔴 Red: Outside range

#### Daily Summary
- Total actual values across all meals
- Comparison to daily targets
- Progress percentage
- Visual progress bars

#### Meal-Level Progress
- Individual meal contribution
- Planned vs. actual comparison
- Done status indicator
- Quick visual assessment

### 8. User Interface Features

#### Home Screen
- Current date display
- Gym day toggle
- Daily targets card with progress
- Scrollable meal list
- Bottom navigation bar
- Quick meal editing

#### Navigation System
- Bottom navigation with 4 tabs:
  - Home
  - Calendar
  - Templates
  - History
- Intuitive navigation flow
- Back button support
- State preservation

#### Material Design 3
- Modern, clean interface
- Consistent design language
- Dark/light mode support
- Smooth animations
- Touch-friendly targets

### 9. Data Management

#### Persistence
- All data saved locally
- SQLite database via Room
- Automatic backups (system)
- No data loss on app close
- Fast data retrieval

#### Data Validation
- Numeric input validation
- Required field checking
- Range validation (min ≤ max)
- Error messages
- Input sanitization

#### Data Organization
- Date-based storage
- Efficient queries
- Indexed searches
- Optimized performance

### 10. Settings & Preferences

#### Current Settings
- Gym day mode (per day)
- Current date tracking
- Persistent across sessions

#### Future Settings (Potential)
- Default target values
- Nutrient unit preferences
- Notification preferences
- Theme selection
- Data export options

## User Workflows

### Typical Daily Use

1. **Morning Setup**
   - Open app
   - Check if gym day or rest day
   - Toggle gym day switch if needed
   - Review daily targets
   - Review meal plan

2. **Log First Meal**
   - Tap Breakfast card
   - Enter actual values consumed
   - Mark as done
   - Return to home

3. **Throughout Day**
   - Log each meal after eating
   - Monitor progress bars
   - Check nutrient totals
   - Adjust remaining meals if needed

4. **End of Day Review**
   - Review final totals
   - Compare to targets
   - Note what worked well
   - Save as template if desired

### Setting Up a New Day

**Option 1: From Template**
1. Go to Templates tab
2. Select saved template
3. Tap "Load"
4. Confirm
5. Begin logging actual values

**Option 2: Copy Previous Day**
1. Go to Home
2. Access Copy Day feature
3. Select recent successful day
4. Confirm copy
5. Adjust if needed
6. Begin logging

**Option 3: Manual Setup**
1. Edit daily targets
2. Edit each meal's planned ranges
3. Save changes
4. Begin logging actual values

### Creating a Meal Plan Template

1. Set up ideal day
   - Configure daily targets
   - Set planned ranges for all meals
   - Adjust for gym day or rest day

2. Save as template
   - Go to Templates tab
   - Tap "Save as Template"
   - Enter descriptive name
   - Confirm save

3. Reuse anytime
   - Load template on any day
   - Make minor adjustments if needed
   - Start tracking

### Reviewing Past Performance

1. **Calendar Method**
   - Go to Calendar tab
   - Scan month for colors
   - Tap specific days of interest
   - Review detailed data

2. **History Method**
   - Go to History tab
   - Scroll through past days
   - Tap to view details
   - Compare trends

## Edge Cases Handled

### Date Changes
- App updates to current date on launch
- Midnight transitions handled
- Historical data preserved
- Future dates allowed (for planning)

### Data Integrity
- Database transactions
- Constraint enforcement
- Null safety
- Default values provided

### User Errors
- Invalid inputs rejected
- Clear error messages
- Undo-friendly operations
- Confirmation for destructive actions

### Empty States
- Helpful messages when no data
- Call-to-action buttons
- Guidance for first-time users
- Instructions for key features

## Accessibility Features

### Current Support
- Material Design touch targets (48dp minimum)
- High contrast color schemes
- Clear text hierarchy
- Intuitive icons with labels

### Recommended Additions
- Screen reader optimization
- Content descriptions
- Keyboard navigation
- Voice input support
- Larger text options

## Performance Characteristics

### Speed
- Instant app launch (< 1 second)
- Smooth scrolling
- Immediate UI updates
- Fast database queries

### Efficiency
- Minimal battery usage
- Small APK size (~5-10 MB)
- Low memory footprint
- No network required

### Reliability
- No crashes
- Data persistence
- State preservation
- Graceful error handling

## Privacy & Security

### Data Storage
- All data local only
- No cloud sync (v1.0)
- No account required
- No analytics tracking
- No ads

### User Privacy
- No personal information collected
- No internet permission required
- No location tracking
- Complete user control
- Can delete all data

## Limitations (v1.0)

### Not Included
- Food database lookup
- Barcode scanning
- Recipe management
- Meal photos
- Cloud synchronization
- Multi-device support
- Social features
- Export to CSV/PDF
- Nutritional advice

### Platform Support
- Android only (iOS not supported)
- Minimum: Android 7.0
- No tablet-optimized layout
- No Wear OS support
- No desktop version

## Future Roadmap

### Planned Features
1. Food database integration
2. Barcode scanner
3. Photo attachments
4. Export/import data
5. Weekly/monthly analytics
6. Nutritional insights
7. Meal reminders
8. Widget support

### Under Consideration
- Cloud backup
- Recipe builder
- Meal planning AI
- Fitness tracker integration
- Macro calculator
- Water intake tracking
- Supplement tracking
