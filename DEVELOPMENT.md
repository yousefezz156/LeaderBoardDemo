# 🚀 Development Workflow

## Fast Development Setup

### 1. Local Development (Recommended)
```bash
# Build and run with local module (fastest)
./gradlew assembleDebug
./gradlew installDebug
```

### 2. Switch Between Development and Production

**Switch to Development Mode:**
```bash
./gradlew -b scripts/switch-to-development.gradle switchToDevelopment
```

**Switch to Production Mode:**
```bash
./gradlew -b scripts/switch-to-production.gradle switchToProduction
```

### 3. Debug the LeaderBoard Module

**View Debug Logs:**
```bash
# In Android Studio Logcat, filter by:
MainApp|API_LOGS|NetworkModule|DummyDataUseCase|LeaderBoarderRepository|LeaderBoardLazyColumn|RankDataSource|SdkMidlayer|LeaderBoard
```

**Test Different Scenarios:**
- Click "Open LeaderBoard" - Default configuration
- Click "Test Real API" - Test with real API
- Click "Test Mock Data" - Test with mock data

### 4. Quick Testing Commands

**Build Module Only:**
```bash
./gradlew :leaderboardscreenmodule:assembleDebug
```

**Run Tests:**
```bash
./gradlew :leaderboardscreenmodule:test
./gradlew :app:connectedAndroidTest
```

**Clean and Rebuild:**
```bash
./gradlew clean
./gradlew assembleDebug
```

## Development Tips

### 1. Use Local Module for Development
- ✅ Fastest build times
- ✅ Immediate feedback
- ✅ Easy debugging
- ✅ No network dependency

### 2. Use JitPack Only for Production
- ✅ Version control
- ✅ Distribution
- ✅ CI/CD integration

### 3. Debug API Responses
- Check `API_LOGS` for HTTP requests/responses
- Use mock data for UI testing
- Use real API for integration testing

### 4. Common Issues
- **Build errors**: Clean and rebuild
- **API errors**: Check network logs
- **UI issues**: Check debug logs

## Workflow Summary

1. **Development**: Use local module → Fast iteration
2. **Testing**: Use mock data → Quick UI testing
3. **Integration**: Use real API → End-to-end testing
4. **Production**: Use JitPack → Release to users

This workflow eliminates the slow GitHub → JitPack → wait cycle! 