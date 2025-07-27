#!/bin/bash

echo "🔨 Building LeaderBoard Module AAR..."

# Build the AAR
./gradlew :leaderboardscreenmodule:assembleRelease

# Create libs directory if it doesn't exist
mkdir -p app/libs

# Copy the AAR to the main app's libs folder
cp leaderboardscreenmodule/build/outputs/aar/leaderboardscreenmodule-release.aar app/libs/

echo "✅ AAR built and copied to app/libs/"
echo "📱 Now you can use: implementation(files(\"libs/leaderboardscreenmodule-release.aar\"))" 