package com.example.leaderboardscreenmodule.approutes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leaderboardscreenmodule.leaderboard.LeaderBoardScreen
import com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockList

object AppRoutes {

    const val LEADERBOARD = "leader_board"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav() {
    val navController = rememberNavController()
    val leaderBoardViewModel: LeaderBoardViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    NavHost(navController = navController, startDestination = AppRoutes.LEADERBOARD) {
        composable(route = AppRoutes.LEADERBOARD) {
            LeaderBoardScreen(
                leaderBoardViewModel = leaderBoardViewModel,
                mockList = MockList().getList()
            )
        }
    }
}