package com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUseCase
import com.example.leaderboardscreenmodule.leaderboard.domain.LeaderBoardUseCase
import com.example.leaderboardscreenmodule.leaderboard.domain.RankDataSource
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel(
    val leaderBoarderRepository: LeaderBoarderRepository = LeaderBoarderRepository(
    ),
    val leaderBoardUseCase: LeaderBoardUseCase = LeaderBoardUseCase(leaderBoarderRepository),
    val dummyDataUseCase: DummyDataUseCase= DummyDataUseCase(leaderBoarderRepository),
    val rankDataSource: RankDataSource = RankDataSource(dummyDataUseCase)
) : ViewModel() {

    private var _state = MutableStateFlow(LeaderBoardState())
    var state: StateFlow<LeaderBoardState> = _state.asStateFlow()

    private var _colors = MutableStateFlow(backgroundColorsForWhiteText)
    var colors = _colors.asStateFlow()

    lateinit var pageSource:RankDataSource

    val pageConfig =
        Pager(PagingConfig(pageSize = 6, prefetchDistance = 1),
            pagingSourceFactory = { setDataSource() })
            .flow.cachedIn(viewModelScope) // here we convert it to flow so the UI can read it

    private fun setDataSource(): RankDataSource {
        pageSource= RankDataSource(dummyDataUseCase)
        return pageSource
    }

//    fun loadNextPage() {
//        viewModelScope.launch {
//            rankDataSource.loadNextPage()
//        }
//    }


    fun onEvent(leaderBoardIntent: LeaderBoardIntent) {
        when (leaderBoardIntent) {
            is LeaderBoardIntent.GetData -> {
                getLeaderBoardData()
            }
            is LeaderBoardIntent.RefreshData ->{
                refreshScreen()
            }

        }
    }

    fun refreshScreen(){

            _state.value = _state.value.copy(isRefreshSuccess = true)

            // refresh data source
            pageSource.invalidate()

//            _state.value = _state.value.copy(
//                isRefreshSuccess = false
//            )

    }

    fun setRefreshKeyFalse(){
        _state.value=_state.value.copy(isRefreshSuccess = false)
    }

    fun getLeaderBoardData() {
        viewModelScope.launch(Dispatchers.IO) {

            try {

                _state.value = _state.value.copy(list = leaderBoarderRepository.getMockList(), isRefreshSuccess = true)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }


}