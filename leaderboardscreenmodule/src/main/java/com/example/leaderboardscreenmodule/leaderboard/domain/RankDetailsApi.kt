package com.example.leaderboardscreenmodule.leaderboard.domain

import com.example.leaderboardscreenmodule.core.Network.BaseForListResponse
import com.example.leaderboardscreenmodule.leaderboard.entity.ApiResponse.RanksInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface RankDetailsApi {

    // Get Data for LeaderBoardScreen
    @GET("getRankDetails")
    suspend fun getRankDetailsByUserId(
        @Query("page") page: Int,
        @Query("itemPerPage") pagePerItem: Int
    ): BaseForListResponse<ArrayList<RanksInfo>>
}