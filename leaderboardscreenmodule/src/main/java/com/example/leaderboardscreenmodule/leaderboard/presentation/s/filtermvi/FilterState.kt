package com.example.leaderboardscreenmodule.leaderboard.presentation.s.filtermvi

import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData

data class FilterState(
    val listOfFilter : List<MockData> = emptyList()
)
