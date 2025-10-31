# Contributing to Intake

Thank you for considering contributing to Intake! This document provides guidelines and instructions for contributing.

## How to Contribute

### Reporting Bugs

If you find a bug, please open an issue with:
- Clear description of the bug
- Steps to reproduce
- Expected behavior
- Actual behavior
- Android version and device info
- Screenshots if applicable

### Suggesting Features

We welcome feature suggestions! Please:
- Check if the feature is already requested
- Open an issue with "Feature Request" label
- Describe the feature and its benefits
- Provide examples or mockups if possible

### Code Contributions

#### Getting Started

1. **Fork the repository**
   ```bash
   git clone https://github.com/solikiev/Intake.git
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Set up development environment**
   - Install Android Studio
   - Open project
   - Sync Gradle files
   - Run on emulator/device

#### Development Guidelines

##### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable names
- Add comments for complex logic
- Keep functions focused and small

##### Architecture
- Follow existing MVVM pattern
- Use Repository for data access
- Keep UI logic in ViewModel
- Use Compose for all UI

##### Database Changes
- Update entities carefully
- Provide migration strategy
- Test database operations
- Document schema changes

##### UI/UX
- Follow Material Design 3 guidelines
- Ensure accessibility
- Test on different screen sizes
- Maintain consistent design language

#### Testing
- Write unit tests for ViewModels
- Test database operations
- Test UI with Compose testing
- Manual testing on real devices

#### Committing Changes

**Commit Message Format:**
```
[Type] Short description

Detailed description if needed

Fixes #issue_number (if applicable)
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style/formatting
- `refactor`: Code refactoring
- `test`: Adding tests
- `chore`: Build/tooling changes

**Examples:**
```
feat: Add water intake tracking

Implements basic water intake tracking feature
with daily goals and progress visualization.

Fixes #42
```

```
fix: Correct meal total calculation

Fixed issue where meal totals were not updating
when actual values changed.

Fixes #15
```

#### Pull Request Process

1. **Update documentation**
   - Update README if needed
   - Update FEATURES.md for new features
   - Update ARCHITECTURE.md for structural changes

2. **Test thoroughly**
   - Run all tests
   - Manual testing
   - Check on different Android versions

3. **Create Pull Request**
   - Clear title and description
   - Reference related issues
   - Include screenshots for UI changes
   - List what was tested

4. **Code Review**
   - Address reviewer feedback
   - Update PR as needed
   - Be patient and respectful

5. **Merge**
   - Squash commits if requested
   - Ensure CI passes
   - Maintainer will merge when ready

## Development Setup

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK API 34
- Git

### Initial Setup
```bash
# Clone repository
git clone https://github.com/solikiev/Intake.git
cd Intake

# Open in Android Studio
# File → Open → Select Intake folder

# Uncomment plugins in build.gradle.kts
# Sync Gradle files
# Run on device/emulator
```

### Project Structure
```
app/src/main/java/com/solikiev/intake/
├── data/           # Database and repository
├── ui/             # UI components and screens
├── viewmodel/      # ViewModels
└── MainActivity.kt # Entry point
```

## Areas Needing Help

### High Priority
- [ ] Unit tests for ViewModel
- [ ] Integration tests for Repository
- [ ] UI tests for screens
- [ ] Accessibility improvements
- [ ] Performance optimization

### Medium Priority
- [ ] Food database integration
- [ ] Barcode scanner
- [ ] Export functionality
- [ ] Meal photos
- [ ] Widgets

### Low Priority
- [ ] Dark theme improvements
- [ ] Animation enhancements
- [ ] Additional color schemes
- [ ] Localization (other languages)

## Code Review Checklist

Before submitting PR, ensure:

- [ ] Code follows Kotlin conventions
- [ ] No compiler warnings
- [ ] All tests pass
- [ ] New features have tests
- [ ] Documentation updated
- [ ] No breaking changes (or documented)
- [ ] Tested on multiple Android versions
- [ ] Tested on different screen sizes
- [ ] Accessibility considered
- [ ] Performance impact minimal

## Communication

### Discussions
- Use GitHub Discussions for questions
- Be respectful and constructive
- Search before posting
- Provide context and details

### Issues
- One issue per topic
- Use templates when available
- Provide reproduction steps
- Include relevant logs/screenshots

### Pull Requests
- Link to related issues
- Explain your changes
- Be open to feedback
- Update based on reviews

## Recognition

Contributors will be:
- Listed in CONTRIBUTORS.md
- Mentioned in release notes
- Given credit in commit messages

## Code of Conduct

### Our Standards
- Be respectful and inclusive
- Accept constructive criticism
- Focus on what's best for the project
- Show empathy towards others

### Unacceptable Behavior
- Harassment or discrimination
- Trolling or insulting comments
- Personal or political attacks
- Publishing others' private information

### Enforcement
- Issues will be reviewed by maintainers
- Violations may result in temporary or permanent ban
- Report issues to project maintainers

## Questions?

Not sure where to start?
- Check open issues labeled "good first issue"
- Ask in GitHub Discussions
- Review existing code for patterns
- Start with documentation improvements

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

## Thank You! 🎉

Your contributions make Intake better for everyone. We appreciate your time and effort!
