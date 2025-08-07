package com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi

sealed class LeaderBoardIntent {
    object GetData: LeaderBoardIntent()
    object RefreshData: LeaderBoardIntent()

}