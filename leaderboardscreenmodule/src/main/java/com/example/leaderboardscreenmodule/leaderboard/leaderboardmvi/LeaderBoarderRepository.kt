package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import com.example.leaderboardscreenmodule.mockdata.MockData
import com.example.leaderboardscreenmodule.mockdata.MockList

class LeaderBoarderRepository(private val mockList: MockList) {
    fun getMockList():List<MockData>{
        return mockList.getList()

    }
}