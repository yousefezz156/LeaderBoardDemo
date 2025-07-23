package com.example.leaderboardscreenmodule.DumyApi

import com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi.LeaderBoarderRepository

class DummyDataUseCase(val repo: LeaderBoarderRepository ) {

    suspend fun DummyDataPagination(page:Int , perPage:Int) : DataResponsePagination{
        val modelList = arrayListOf<DummyDataUiModel>()
        val response = repo.getDummyData(page,perPage)
        response.data.map { modelList.add(it.mapData()) }
        return DataResponsePagination(page, modelList)
    }

    fun DataInfo.mapData(): DummyDataUiModel{
        return DummyDataUiModel(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avater = avater
        )
    }
}