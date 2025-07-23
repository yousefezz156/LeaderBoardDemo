package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import com.example.leaderboardscreenmodule.DumyApi.DataApiServices
import com.example.leaderboardscreenmodule.DumyApi.DataResponse
import com.example.leaderboardscreenmodule.leaderboard.domain.RankDetailsApi
import com.example.leaderboardscreenmodule.core.Network.BaseForListResponse
import com.example.leaderboardscreenmodule.core.Network.NetworkModule
import com.example.leaderboardscreenmodule.leaderboard.entity.ApiResponse.RanksInfo
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockList

class LeaderBoarderRepository(
    private val mockList: MockList,
    private val rankDetailsApi: RankDetailsApi = NetworkModule.provideApi(),
    private val dummyDataApi: DataApiServices = NetworkModule.provideApi()
) {
    fun getMockList(): List<MockData> {
        return mockList.getList()

    }

    suspend fun getRankDetails(
        page: Int,
        itemPerPage: Int
    ): BaseForListResponse<ArrayList<RanksInfo>> {
        return rankDetailsApi.getRankDetailsByUserId(page, itemPerPage)
    }

    suspend fun getDummyData(page: Int, perPage: Int): DataResponse {
        return dummyDataApi.getDataInfo(page, perPage)
    }


}