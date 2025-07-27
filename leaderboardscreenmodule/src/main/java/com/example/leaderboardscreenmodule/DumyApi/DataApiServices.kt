package com.example.leaderboardscreenmodule.DumyApi

import retrofit2.http.GET
import retrofit2.http.Query

interface DataApiServices {

    @GET("users")
    suspend fun getDataInfo(@Query("page") page: Int,@Query("per_page") perPage: Int = 4): DataResponse
}