package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

sealed class LeaderBoardIntent {
    object GetData: LeaderBoardIntent()
    object Idle: LeaderBoardIntent()
    object LoadNextPage: LeaderBoardIntent()
    object RefreshScreen: LeaderBoardIntent()
}