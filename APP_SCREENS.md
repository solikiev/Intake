# Intake App - Screen Descriptions

This document describes what each screen looks like and how it functions. Since the app cannot be built in this environment, these descriptions serve as visual documentation.

## 1. Home Screen (Main Dashboard)

### Layout
```
┌─────────────────────────────────┐
│ 2024-10-31      [Rest Day] [🔄] │ ← Header with date and toggle
├─────────────────────────────────┤
│ Daily Targets              [✏️] │ ← Targets card with edit button
│ ┌─────────────────────────────┐ │
│ │ Carbs:  180 / 200-300g     │ │ ← Progress bar (green/yellow/red)
│ │ ████████░░░░░░              │ │
│ │                             │ │
│ │ Protein: 95 / 120-180g     │ │
│ │ ██████░░░░░░░░░             │ │
│ │                             │ │
│ │ Fat: 42 / 50-80g           │ │
│ │ ████████░░░░░░              │ │
│ │                             │ │
│ │ Calories: 1520 / 2000-2500 │ │
│ │ ████████░░░░░░░░░░          │ │
│ │                             │ │
│ │ Fiber: 18 / 25-35g         │ │
│ │ ██████░░░░░░░░              │ │
│ └─────────────────────────────┘ │
├─────────────────────────────────┤
│ Meals                           │
│ ┌─────────────────────────────┐ │
│ │ Breakfast              [✓]  │ │ ← Done checkbox (green if checked)
│ │ Planned: C:40-60 P:25-35   │ │
│ │ Actual: C:52 P:30 F:12     │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Morning snack 1        [ ]  │ │
│ │ Planned: C:20-40 P:15-30   │ │
│ │ Actual: C:0 P:0 F:0        │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Morning snack 2        [ ]  │ │
│ │ ...                         │ │
│ └─────────────────────────────┘ │
│ [More meals - scrollable]       │
└─────────────────────────────────┘
┌─────────────────────────────────┐
│ [🏠 Home] [📅 Calendar] [⭐ Tem] │ ← Bottom navigation
│            [📋 History]          │
└─────────────────────────────────┘
```

### Interactions
- **Toggle switch**: Changes between Gym Day / Rest Day
- **Edit button**: Opens Daily Targets screen
- **Meal card tap**: Opens Meal Edit screen
- **Checkbox**: Toggles done status
- **Progress bars**: Show real-time progress (color-coded)

### Colors
- Green progress: Within target range
- Yellow progress: Within 10% of range
- Red progress: Outside range
- Done meals: Green checkmark icon

---

## 2. Daily Targets Screen

### Layout
```
┌─────────────────────────────────┐
│ [←] Edit Daily Targets          │ ← Top bar with back button
├─────────────────────────────────┤
│ Set your daily nutrient targets │
│ for 2024-10-31                  │
│                                 │
│ Carbs (g)                       │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 200 │ - │ Max: 300 │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ Protein (g)                     │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 120 │ - │ Max: 180 │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ Fat (g)                         │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 50  │ - │ Max: 80  │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ Calories                        │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min:2000 │ - │ Max:2500 │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ Fiber (g)                       │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 25  │ - │ Max: 35  │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ ┌─────────────────────────────┐ │
│ │      Save Targets           │ │ ← Primary button
│ └─────────────────────────────┘ │
└─────────────────────────────────┘
```

### Interactions
- **Text fields**: Numeric keyboard for input
- **Save button**: Saves targets and returns to home
- **Back button**: Returns without saving

---

## 3. Meal Edit Screen

### Layout
```
┌─────────────────────────────────┐
│ [←] Edit Breakfast              │
├─────────────────────────────────┤
│ Planned Values                  │
│                                 │
│ Carbs (g)                       │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 40  │ - │ Max: 60  │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ Protein (g)                     │
│ ┌──────────┐   ┌──────────┐   │
│ │ Min: 25  │ - │ Max: 35  │   │
│ └──────────┘   └──────────┘   │
│                                 │
│ [... more nutrients ...]        │
│                                 │
│ ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  │ ← Divider
│                                 │
│ Actual Values                   │
│                                 │
│ ┌─────────────────────────────┐ │
│ │ Carbs (g):            52    │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Protein (g):          30    │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Fat (g):              12    │ │
│ └─────────────────────────────┘ │
│                                 │
│ [... more nutrients ...]        │
│                                 │
│ Mark as Done          [ON] 🔄  │ ← Toggle switch
│                                 │
│ ┌─────────────────────────────┐ │
│ │        Save Meal            │ │
│ └─────────────────────────────┘ │
└─────────────────────────────────┘
```

### Interactions
- **All fields**: Numeric keyboard input
- **Done toggle**: Mark meal complete
- **Save button**: Saves and returns
- **Back button**: Returns without saving

---

## 4. Calendar Screen

### Layout
```
┌─────────────────────────────────┐
│ Calendar                        │
├─────────────────────────────────┤
│ [←] October 2024 [→]           │ ← Month navigation
│                                 │
│ Sun Mon Tue Wed Thu Fri Sat     │
│     1🟢  2🟡  3🔴  4⚪  5🟢     │
│  6🟢  7🟢  8🟡  9🟢 10🟢 11🟡 12🟢│
│ 13🔴 14🟢 15🟢 16🟢 17🟡 18🟢 19🟢│
│ 20🟢 21🟢 22🟡 23🟢 24🟢 25🟡 26🟢│
│ 27🟢 28🟢 29🟢 30🟢 [31]        │
│                                 │
│ Legend:                         │
│ 🟢 All targets met              │
│ 🟡 1-2 targets missed           │
│ 🔴 3+ targets missed            │
│ ⚪ No data                      │
└─────────────────────────────────┘
┌─────────────────────────────────┐
│ [🏠] [📅] [⭐] [📋]             │
└─────────────────────────────────┘
```

### Interactions
- **Arrow buttons**: Navigate months
- **Date tap**: Load that day's data and go to home
- **Color indicators**: Visual performance summary
- **Today**: Highlighted with border

---

## 5. Templates Screen

### Layout
```
┌─────────────────────────────────┐
│ Templates                       │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ Gym Day - Heavy Training    │ │
│ │ Created: 2024-10-15         │ │
│ │         [Load] [🗑️]        │ │ ← Load and delete buttons
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Rest Day - Light Eating     │ │
│ │ Created: 2024-10-10         │ │
│ │         [Load] [🗑️]        │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ Weekend - Flexible          │ │
│ │ Created: 2024-10-05         │ │
│ │         [Load] [🗑️]        │ │
│ └─────────────────────────────┘ │
│                                 │
│                           [➕]  │ ← Floating action button
└─────────────────────────────────┘
┌─────────────────────────────────┐
│ [🏠] [📅] [⭐] [📋]             │
└─────────────────────────────────┘
```

### Interactions
- **Load button**: Shows confirmation, then loads template
- **Delete button**: Shows confirmation, then deletes
- **+ button**: Opens dialog to save current day as template
- **Empty state**: "No templates saved yet" message

### Save Template Dialog
```
┌─────────────────────────────────┐
│ Save Template                   │
├─────────────────────────────────┤
│ Enter a name for this template: │
│                                 │
│ ┌─────────────────────────────┐ │
│ │ Template name               │ │
│ └─────────────────────────────┘ │
│                                 │
│          [Cancel]  [Save]       │
└─────────────────────────────────┘
```

---

## 6. History Screen

### Layout
```
┌─────────────────────────────────┐
│ History                         │
├─────────────────────────────────┤
│ ┌─────────────────────────────┐ │
│ │ 2024-10-30          🟢      │ │
│ │ View details →              │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-29          🟡      │ │
│ │ View details →              │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-28          🟢      │ │
│ │ View details →              │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-27          🔴      │ │
│ │ View details →              │ │
│ └─────────────────────────────┘ │
│ [More dates - scrollable]       │
└─────────────────────────────────┘
┌─────────────────────────────────┐
│ [🏠] [📅] [⭐] [📋]             │
└─────────────────────────────────┘
```

### Interactions
- **Date card tap**: Loads that day and navigates to home
- **Scroll**: View all historical dates
- **Color indicator**: Quick visual summary
- **Empty state**: "No history available yet"

---

## 7. Copy Day Screen

### Layout
```
┌─────────────────────────────────┐
│ [←] Copy from Day               │
├─────────────────────────────────┤
│ Select a day to copy planned    │
│ values from:                    │
│                                 │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-30                  │ │
│ │                      Copy → │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-29                  │ │
│ │                      Copy → │ │
│ └─────────────────────────────┘ │
│ ┌─────────────────────────────┐ │
│ │ 2024-10-28                  │ │
│ │                      Copy → │ │
│ └─────────────────────────────┘ │
│                                 │
│ [Scrollable list]               │
└─────────────────────────────────┘
```

### Confirmation Dialog
```
┌─────────────────────────────────┐
│ Copy Day                        │
├─────────────────────────────────┤
│ Copy planned values from        │
│ 2024-10-30 to 2024-10-31?      │
│                                 │
│ This will replace current       │
│ planned values but keep actual  │
│ values unchanged.               │
│                                 │
│          [Cancel]  [Copy]       │
└─────────────────────────────────┘
```

### Interactions
- **Date card tap**: Shows confirmation dialog
- **Copy button**: Copies planned values to current day
- **Cancel**: Dismisses dialog
- **Back button**: Returns to previous screen

---

## UI Design Principles

### Color Scheme
- **Primary**: Purple (#6650a4)
- **Success**: Green (#4CAF50)
- **Warning**: Yellow (#FFC107)
- **Error**: Red (#F44336)
- **Info**: Blue (#2196F3)
- **Background**: Light Gray (#F5F5F5)

### Typography
- **Headlines**: 22sp, bold
- **Titles**: 16sp, medium
- **Body**: 14sp, regular
- **Labels**: 12sp, regular

### Spacing
- **Small**: 8dp
- **Medium**: 16dp
- **Large**: 24dp
- **Touch targets**: Minimum 48dp

### Animations
- **Screen transitions**: 300ms slide
- **Button press**: 100ms scale
- **Progress updates**: 200ms smooth

### Accessibility
- **Min touch target**: 48x48dp
- **Contrast ratio**: 4.5:1 minimum
- **Text size**: Scalable with system settings
- **Screen reader**: Full support

---

## Responsive Design

### Phone (Portrait)
- Single column layout
- Bottom navigation
- Scrollable content
- Full-width cards

### Tablet (Landscape)
- Two-column layout (optional future)
- Side navigation (optional future)
- Master-detail view (optional future)
- Grid calendar (enhanced)

---

## Dark Mode

All screens support dark mode with:
- Dark backgrounds (#121212)
- High contrast text
- Adjusted color palette
- Reduced eye strain

---

## Navigation Flow

```
Home Screen
├─→ Daily Targets Screen ─→ [Save] ─→ Back to Home
├─→ Meal Edit Screen ─→ [Save] ─→ Back to Home
├─→ Calendar Screen ─→ [Select Date] ─→ Home (new date)
├─→ Templates Screen
│   ├─→ [Save Template] ─→ Dialog ─→ [Save] ─→ Templates
│   └─→ [Load Template] ─→ Dialog ─→ [Load] ─→ Home
├─→ History Screen ─→ [Select Date] ─→ Home (new date)
└─→ Copy Day Screen ─→ [Copy] ─→ Dialog ─→ [Confirm] ─→ Home
```

---

## Key Features Visualization

### Progress Indicators
```
Within range (Green):
████████████████████ 100%

Close to range (Yellow):
███████████████░░░░░ 75%

Outside range (Red):
████░░░░░░░░░░░░░░░░ 20%
```

### Meal Status
```
Not Done: [ ] ⚪
Done:     [✓] 🟢
```

### Calendar Colors
```
Perfect Day:  🟢 (All targets met)
Good Day:     🟡 (1-2 targets missed)
Needs Work:   🔴 (3+ targets missed)
No Data:      ⚪ (Not tracked yet)
```

This completes the visual documentation of all screens in the Intake app!
