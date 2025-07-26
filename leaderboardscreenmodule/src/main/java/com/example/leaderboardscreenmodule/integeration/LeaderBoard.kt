package com.example.leaderboardscreenmodule.integeration

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.leaderboardscreenmodule.MainActivity
import com.example.leaderboardscreenmodule.approutes.AppNav
object LeaderBoard {
    var sdkMidlayer: SdkMidlayer?=null

    //initSDK
    fun initSdk(context : Context, sdkConfig: SdkConfig, sdkData: SdkData): LeaderBoard {
        android.util.Log.d("LeaderBoard", "Initializing SDK with base URL: ${sdkData.baseURL}")
        sdkMidlayer = SdkMidlayer(sdkData,sdkConfig,context)
        android.util.Log.d("LeaderBoard", "SDK initialized successfully")
        return this
    }

    //openSDK
    fun openSdk(context: Context){
        android.util.Log.d("LeaderBoard", "Opening SDK")
        sdkMidlayer?.start(context)
        android.util.Log.d("LeaderBoard", "SDK opened")
    }
}

