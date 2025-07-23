package com.example.leaderboardscreenmodule.DumyApi

data class DataResponse(
    val page :Int,
    val perPage :Int,
    val total:Int,
    val total_pages:Int,
    val data: ArrayList<DataInfo>,
    val support: List<supportInfo>
)
