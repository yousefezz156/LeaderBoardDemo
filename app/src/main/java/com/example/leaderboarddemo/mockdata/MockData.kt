package com.example.leaderboarddemo.mockdata

data class MockData(
    var first_name :String,
    var last_name: String,
    var score: Int,
    var rank: Int,
    var date:String?= null  // for filtering
)
