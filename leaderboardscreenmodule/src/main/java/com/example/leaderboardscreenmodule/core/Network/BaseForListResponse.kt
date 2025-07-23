package com.example.leaderboardscreenmodule.core.Network

data class BaseForListResponse<T>(
    val results : BaseListResult<T>,
    val message:String,
    val statusName : String?,
    val statusCode : Int?,
)

data class BaseListResult<T>(
    val data :T,
    val count: Int,
    val pageCount :Int
)
