package com.example.leaderboardscreenmodule.DumyApi

import com.google.gson.annotations.SerializedName

data class DataInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avater") val avater: String,

)