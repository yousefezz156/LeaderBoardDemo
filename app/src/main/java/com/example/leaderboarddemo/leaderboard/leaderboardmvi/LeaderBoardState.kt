package com.example.leaderboarddemo.leaderboard.leaderboardmvi

import com.example.leaderboarddemo.mockdata.MockData

data class LeaderBoardState(
    val list: List<MockData> = emptyList(),
    var isLoadedSuccess: Boolean = false,
    var error: String=""
)
