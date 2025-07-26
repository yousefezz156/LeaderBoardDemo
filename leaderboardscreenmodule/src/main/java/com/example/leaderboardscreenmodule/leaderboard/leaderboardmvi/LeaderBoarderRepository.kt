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
    private val mockList: MockList = MockList()
) {
    private val rankDetailsApi: RankDetailsApi by lazy { NetworkModule.provideApi() }
    private val dummyDataApi: DataApiServices by lazy { NetworkModule.provideApi() }
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
        android.util.Log.d("LeaderBoarderRepository", "Getting dummy data for page: $page, perPage: $perPage")
        try {
            val response = dummyDataApi.getDataInfo(page, perPage)
            android.util.Log.d("LeaderBoarderRepository", "API call successful, data size: ${response.data.size}")
            return response
        } catch (e: Exception) {
            android.util.Log.e("LeaderBoarderRepository", "API call failed: ${e.message}")
            throw e
        }
    }


}