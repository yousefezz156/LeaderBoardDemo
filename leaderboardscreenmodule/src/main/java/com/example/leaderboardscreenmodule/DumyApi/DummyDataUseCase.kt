package com.example.leaderboardscreenmodule.DumyApi

import android.util.Log
import com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi.LeaderBoarderRepository

class DummyDataUseCase(val repo: LeaderBoarderRepository) {

    suspend fun DummyDataPagination(page:Int , perPage:Int) : DataResponsePagination{
        val modelList = arrayListOf<DummyDataUiModel>()
        
        try {
            Log.d("DummyDataUseCase", "Making API call for page: $page, perPage: $perPage")
            val response = repo.getDummyData(page,perPage)
            Log.d("DummyDataUseCase","API response data size ${response.data.size}")
            response.data.map { modelList.add(it.mapData()) }
            Log.d("DummyDataUseCase", "Mapped ${modelList.size} items")
        } catch (e: Exception) {
            Log.e("DummyDataUseCase", "API call failed: ${e.message}")
            Log.e("DummyDataUseCase", "Stack trace: ${e.stackTraceToString()}")
            // Return empty list if API fails
        }
        
        Log.d("DummyDataUseCase", "Returning ${modelList.size} items for page $page")
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