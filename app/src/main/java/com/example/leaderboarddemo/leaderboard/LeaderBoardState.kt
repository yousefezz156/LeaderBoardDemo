package com.example.leaderboarddemo.leaderboard

import com.example.leaderboarddemo.mockdata.MockData

data class LeaderBoardState(
    val list: List<MockData> = emptyList(),
    var isLoadedSuccess: Boolean = false,
    var error: String=""
)
