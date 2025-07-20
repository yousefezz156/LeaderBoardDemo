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
    fun start(context : Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
        }

}

