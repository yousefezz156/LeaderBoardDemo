package com.example.leaderboardscreenmodule.mockdata

data class MockData(
    var first_name:String,
    var last_name: String,
    var score: Int,
    var rank: Int,
    var date:String?= null,
    var colors:androidx.compose.ui.graphics.Color// for filtering
)
