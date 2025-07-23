package com.example.leaderboardscreenmodule.leaderboard.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUseCase
import com.example.leaderboardscreenmodule.leaderboard.entity.RankUiModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

class RankDataSource(/*val useCase: LeaderBoardUseCase*/ val dummyUseCase: DummyDataUseCase): PagingSource<Int, DummyDataUiModel>() {

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
            if (page == 1) loadNextPage()

            channel.receive()
            val response = dummyUseCase.DummyDataPagination(page, 10)                           //useCase.rankpagination(page,10)
            if(response.data.isEmpty()){
                return LoadResult.Page(response.data, null, null)
            }else{
                return LoadResult.Page(response.data ,null,if(response.data.size < 10)null else page+1)
            }
        }.onFailure{
            return LoadResult.Error(it)

        }

        return LoadResult.Page(arrayListOf(), null, null)


    }

    suspend fun loadNextPage() {
        channel.send(Unit)
    }

}