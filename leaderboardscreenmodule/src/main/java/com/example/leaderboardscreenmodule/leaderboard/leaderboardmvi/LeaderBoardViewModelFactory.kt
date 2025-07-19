package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class LeaderBoardViewModelFactory(
//    private val leaderBoarderRepository: LeaderBoarderRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LeaderBoardViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return LeaderBoardViewModel(leaderBoarderRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}