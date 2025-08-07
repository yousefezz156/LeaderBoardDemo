package com.example.leaderboardscreenmodule.leaderboard.presentation.s.filtermvi

sealed class FilterIntent {
    object FetchData: FilterIntent()
}