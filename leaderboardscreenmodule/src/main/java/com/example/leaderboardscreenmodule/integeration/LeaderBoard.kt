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
        sdkMidlayer = SdkMidlayer(sdkData,sdkConfig,context)
        return this
    }

    //openSDK
    fun openSdk(context: Context){
        sdkMidlayer?.start(context)
    }
}

