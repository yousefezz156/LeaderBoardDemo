package com.example.leaderboardscreenmodule.approutes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.FilterScreen
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.LeaderBoardScreen
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppRoutes {

    const val LEADERBOARD = "leader_board"
    const val FILTERDATASCREEN ="filter_data_screen"
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
                navController=navController
            )
        }
        composable(route="${AppRoutes.FILTERDATASCREEN}/{json}", arguments = listOf(navArgument("json"){NavType.StringArrayType})){
            backStackEntry ->
            val json = backStackEntry.arguments?.getString("json")
            val listType = object : TypeToken<List<MockData>>() {}.type
            val filterData: List<MockData> = Gson().fromJson(json, listType)
            FilterScreen(filterList = filterData,navController)
        }

        }
    }
