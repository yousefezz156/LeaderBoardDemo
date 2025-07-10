package com.example.leaderboarddemo.leaderboard.leaderboardmvi

sealed class LeaderBoardIntent {
    object GetData: LeaderBoardIntent()

}