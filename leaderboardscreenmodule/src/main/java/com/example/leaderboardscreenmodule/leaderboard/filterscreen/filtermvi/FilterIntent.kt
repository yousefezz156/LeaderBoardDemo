package com.example.leaderboardscreenmodule.leaderboard.filterscreen.filtermvi

sealed class FilterIntent {
    object FetchData: FilterIntent()
}