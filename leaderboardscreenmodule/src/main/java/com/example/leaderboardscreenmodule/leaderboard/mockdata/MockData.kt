package com.example.leaderboardscreenmodule.leaderboard.mockdata


data class MockData(
    var first_name: String,
    var last_name: String,
    var score: Int,
    var rank: Int,
    var date: String,
    var day: Int? = date.substring(0,2).toInt(),
    var month: Int? =date.substring(3,5).toInt(),
    var year: Int? = date.substring(6,10).toInt(),
    var colors: Int
)
