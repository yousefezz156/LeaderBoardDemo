package com.example.leaderboardscreenmodule.DumyApi

import com.google.gson.annotations.SerializedName

data class supportInfo(
    @SerializedName("url") val url: String,
    @SerializedName("text") val text: String

)
