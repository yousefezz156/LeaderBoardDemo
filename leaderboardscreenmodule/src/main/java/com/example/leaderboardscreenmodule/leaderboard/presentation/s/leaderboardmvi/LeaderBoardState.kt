package com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi

import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData

data class LeaderBoardState(
    val list: List<MockData> = emptyList(),
    var isRefreshSuccess: Boolean = false,
    var error: String=""
)
