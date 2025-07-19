package com.example.leaderboardscreenmodule.integeration

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.leaderboardscreenmodule.approutes.AppNav
object LeaderBoard {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun start(modifier: Modifier = Modifier) {
        AppNav()
    }

}

