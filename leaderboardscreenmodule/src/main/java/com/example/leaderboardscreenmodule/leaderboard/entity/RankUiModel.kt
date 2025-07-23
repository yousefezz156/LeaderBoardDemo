package com.example.leaderboardscreenmodule.leaderboard.entity

data class RankUiModel(
    var first_name:String,
    var last_name: String,
    var rank: Int,
    var transaction: Int,
    var date:String?= null,
    var colors:androidx.compose.ui.graphics.Color?= null
)
