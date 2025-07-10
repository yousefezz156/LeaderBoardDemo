package com.example.leaderboarddemo.approutes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leaderboarddemo.leaderboard.LeaderBoardScreen
import com.example.leaderboarddemo.leaderboard.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboarddemo.mockdata.MockList

object AppRoutes {

    const val LEADERBOARD = "leader_board"
}

@Composable
fun AppNav(viewModel: LeaderBoardViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.LEADERBOARD) {
        composable(route = AppRoutes.LEADERBOARD) {
            LeaderBoardScreen(
                leaderBoardViewModel = viewModel,
                mockList = MockList().getList()
            )
        }
    }
}