package com.example.leaderboardscreenmodule.DumyApi

import retrofit2.http.GET

interface DataApiServices {

    @GET("users")
    suspend fun getDataInfo(page: Int, perPage: Int): DataResponse
}