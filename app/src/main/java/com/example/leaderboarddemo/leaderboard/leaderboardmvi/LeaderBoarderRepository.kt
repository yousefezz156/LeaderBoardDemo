package com.example.leaderboarddemo.leaderboard.leaderboardmvi

import com.example.leaderboarddemo.mockdata.MockData
import com.example.leaderboarddemo.mockdata.MockList

class LeaderBoarderRepository(private val mockList: MockList) {
    fun getMockList():List<MockData>{
        return mockList.getList()

    }
}