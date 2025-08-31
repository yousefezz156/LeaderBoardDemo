package com.example.leaderboardscreenmodule.DumyApi

import android.util.Log
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoarderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DummyDataUseCase(val repo: LeaderBoarderRepository = LeaderBoarderRepository() ) {

    suspend fun DummyDataPagination(page:Int , perPage:Int) : DataResponsePagination{
        try {
            Log.d("Pagination", "Requesting page $page with $perPage items")
            val modelList = arrayListOf<DummyDataUiModel>()
            val response = repo.getDummyData(page,perPage)
            Log.d("response","Data size ${response.data.size} items")
            val mappingData= withContext(Dispatchers.Default) {
                response.data.map { it.mapData() }
            }
            return DataResponsePagination(page, ArrayList(mappingData))
        } catch (e:Exception){
            Log.e("DummyUseCase","No Response ${e.message}")
        }

        return DataResponsePagination(0, arrayListOf())
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