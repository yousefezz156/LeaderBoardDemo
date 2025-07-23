package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.leaderboardscreenmodule.DumyApi.DataApiServices
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUseCase
import com.example.leaderboardscreenmodule.leaderboard.domain.LeaderBoardUseCase
import com.example.leaderboardscreenmodule.leaderboard.domain.RankDataSource
import com.example.leaderboardscreenmodule.leaderboard.domain.RankDetailsApi
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockList
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel(
    val rankDetailsApi: RankDetailsApi,
    val dataApiServices: DataApiServices,
    val leaderBoarderRepository: LeaderBoarderRepository = LeaderBoarderRepository(
        mockList = MockList(), rankDetailsApi = rankDetailsApi, dummyDataApi = dataApiServices
    ),
    val leaderBoardUseCase: LeaderBoardUseCase = LeaderBoardUseCase(leaderBoarderRepository),
    val dummyDataUseCase: DummyDataUseCase= DummyDataUseCase(leaderBoarderRepository),
    val rankDataSource: RankDataSource = RankDataSource(dummyDataUseCase)
) : ViewModel() {

    private var _state = MutableStateFlow(LeaderBoardState())
    var state: StateFlow<LeaderBoardState> = _state.asStateFlow()

    private var _colors = MutableStateFlow(backgroundColorsForWhiteText)
    var colors = _colors.asStateFlow()

    val pageConfig =
        Pager(PagingConfig(pageSize = 10),
            pagingSourceFactory = { setDataSource() })
            .flow.cachedIn(viewModelScope) // here we convert it to flow so the UI can read it

    private fun setDataSource(): RankDataSource {
        val dataSource = RankDataSource(dummyDataUseCase)
        return dataSource
    }

    fun loadNextPage() {
        viewModelScope.launch {
            rankDataSource.loadNextPage()
        }
    }

    init {
        onEvent(LeaderBoardIntent.GetData)
    }

    fun onEvent(leaderBoardIntent: LeaderBoardIntent) {
        when (leaderBoardIntent) {
            is LeaderBoardIntent.GetData -> {
                getLeaderBoardData()
            }

        }
    }

    fun getLeaderBoardData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = _state.value.copy(list = leaderBoarderRepository.getMockList())
                _state.value = _state.value.copy(isLoadedSuccess = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }


}