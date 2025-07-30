package com.example.leaderboardscreenmodule.leaderboard.filterscreen.filtermvi

import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData

data class FilterState(
    val listOfFilter : List<MockData> = emptyList()
)
