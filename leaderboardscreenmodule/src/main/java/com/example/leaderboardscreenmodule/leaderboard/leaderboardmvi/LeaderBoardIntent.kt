package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

sealed class LeaderBoardIntent {
    object GetData: LeaderBoardIntent()

}