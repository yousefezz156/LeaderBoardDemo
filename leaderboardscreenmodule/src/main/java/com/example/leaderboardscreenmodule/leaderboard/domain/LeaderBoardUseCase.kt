package com.example.leaderboardscreenmodule.leaderboard.domain

import com.example.leaderboardscreenmodule.leaderboard.entity.RankUiModel
import com.example.leaderboardscreenmodule.leaderboard.entity.ApiResponse.RanksInfo
import com.example.leaderboardscreenmodule.leaderboard.entity.RankPagination
import com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi.LeaderBoarderRepository

class LeaderBoardUseCase(private val leaderBoardRepo:LeaderBoarderRepository) {

    suspend fun rankpagination(page :Int, itemPerPage:Int): RankPagination{
        var modelList = arrayListOf<RankUiModel>()
        val response = leaderBoardRepo.getRankDetails(page,itemPerPage)
        response.results?.data?.map { modelList.add(it.mapData()) }
        return RankPagination(response.results.pageCount ?: 1 , modelList )
    }

    fun RanksInfo.mapData() : RankUiModel{
        return RankUiModel(
            first_name = firstName,
            last_name = lastName,
            rank = rank,
            date = Date,
            transaction = transaction
        )
    }
}