package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData

data class LeaderBoardState(
    val list: List<MockData> = emptyList(),
    var isLoadedSuccess: Boolean = false,
    var isScreenRefreshed :Boolean = false,
    var error: String=""
)
