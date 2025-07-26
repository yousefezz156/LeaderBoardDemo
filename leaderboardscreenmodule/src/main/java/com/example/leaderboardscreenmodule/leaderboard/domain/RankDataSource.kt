package com.example.leaderboardscreenmodule.leaderboard.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUseCase
import com.example.leaderboardscreenmodule.leaderboard.entity.RankUiModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

class RankDataSource(val dummyUseCase: DummyDataUseCase): PagingSource<Int, DummyDataUiModel>() {

    private val channel: Channel<Unit> = Channel(1, BufferOverflow.DROP_LATEST)
    override fun getRefreshKey(state: PagingState<Int, DummyDataUiModel>): Int? {

      return state.anchorPosition?.let { anchor ->
            val anchorPage = state.closestPageToPosition(anchor)

            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DummyDataUiModel> {

        kotlin.runCatching {
            val page = params.key ?: 1
            android.util.Log.d("RankDataSource", "Loading page: $page")
            
            if (page == 1) loadNextPage()

            channel.receive()
            val response = dummyUseCase.DummyDataPagination(page, 10)                           //useCase.rankpagination(page,10)
            android.util.Log.d("RankDataSource", "Response data size: ${response.data.size}")
            
            if(response.data.isEmpty()){
                android.util.Log.d("RankDataSource", "Response data is empty, returning mock data")
                val mockData = createMockData(page)
                return LoadResult.Page(mockData, null, if(mockData.size < 10) null else page + 1)
            }else{
                return LoadResult.Page(response.data ,null,if(response.data.size < 10)null else page+1)
            }
        }.onFailure{ exception ->
            android.util.Log.e("RankDataSource", "API call failed: ${exception.message}")
            // If API fails, return mock data instead

        }

        return LoadResult.Page(arrayListOf(), null, null)
    }

    private fun createMockData(page: Int): List<DummyDataUiModel> {
        val mockData = mutableListOf<DummyDataUiModel>()
        val startIndex = (page - 1) * 10
        
        for (i in 0 until 10) {
            val globalIndex = startIndex + i
            if (globalIndex < 100) { // Limit to 100 items
                mockData.add(
                    DummyDataUiModel(
                        id = globalIndex + 1,
                        email = "user${globalIndex + 1}@example.com",
                        firstName = "User${globalIndex + 1}",
                        lastName = "LastName${globalIndex + 1}",
                        avater = "https://reqres.in/img/faces/${(globalIndex % 12) + 1}-image.jpg"
                    )
                )
            }
        }
        
        android.util.Log.d("RankDataSource", "Created ${mockData.size} mock data items for page $page")
        return mockData
    }

    suspend fun loadNextPage() {
        android.util.Log.d("RankDataSource", "loadNextPage called")
        channel.send(Unit)
        android.util.Log.d("RankDataSource", "loadNextPage completed")
    }

}