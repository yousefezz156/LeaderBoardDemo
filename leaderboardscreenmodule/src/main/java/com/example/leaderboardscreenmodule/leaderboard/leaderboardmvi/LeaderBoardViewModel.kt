package com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.leaderboardscreenmodule.DumyApi.DataApiServices
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
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

class LeaderBoardViewModel : ViewModel() {
    private val leaderBoarderRepository: LeaderBoarderRepository by lazy { LeaderBoarderRepository() }
    private val leaderBoardUseCase: LeaderBoardUseCase by lazy { LeaderBoardUseCase(leaderBoarderRepository) }
    private val dummyDataUseCase: DummyDataUseCase by lazy { DummyDataUseCase(leaderBoarderRepository) }
    private val rankDataSource: RankDataSource by lazy { RankDataSource(dummyDataUseCase) }

    private var _state = MutableStateFlow(LeaderBoardState())
    var state: StateFlow<LeaderBoardState> = _state.asStateFlow()

    private var _colors = MutableStateFlow(backgroundColorsForWhiteText)
    var colors = _colors.asStateFlow()

    val pageConfig =
        Pager(PagingConfig(pageSize = 10),
            pagingSourceFactory = { setDataSource() })
            .flow.cachedIn(viewModelScope) // here we convert it to flow so the UI can read it

    private fun setDataSource(): RankDataSource {
        return rankDataSource
    }

    fun loadNextPage() {
        android.util.Log.d("LeaderBoardViewModel", "loadNextPage called")
        viewModelScope.launch {
            android.util.Log.d("LeaderBoardViewModel", "loadNextPage coroutine started")
            rankDataSource.loadNextPage()
            android.util.Log.d("LeaderBoardViewModel", "loadNextPage completed")
        }
    }

    init {
        // Initialize the page config
        getLeaderBoardData()
    }

    fun onEvent(leaderBoardIntent: LeaderBoardIntent) {
        android.util.Log.d("LeaderBoardViewModel", "Received event: ${leaderBoardIntent::class.java.simpleName}")
        when (leaderBoardIntent) {
            is LeaderBoardIntent.GetData -> {
                android.util.Log.d("LeaderBoardViewModel", "Processing GetData event")
                getLeaderBoardData()
            }
            is LeaderBoardIntent.LoadNextPage -> {
                android.util.Log.d("LeaderBoardViewModel", "Processing LoadNextPage event")
                loadNextPage()
            }
            is LeaderBoardIntent.RefreshScreen -> {
                android.util.Log.d("LeaderBoardViewModel", "Processing RefreshScreen event")
            }
            is LeaderBoardIntent.Idle -> {
                android.util.Log.d("LeaderBoardViewModel", "Processing Idle event")
                Unit
            }
        }
    }

    fun getLeaderBoardData() {
        android.util.Log.d("LeaderBoardViewModel", "getLeaderBoardData started")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                android.util.Log.d("LeaderBoardViewModel", "Getting mock list")
                val mockList = MockList().getList()
                android.util.Log.d("LeaderBoardViewModel", "Mock list size: ${mockList.size}")
                _state.value = _state.value.copy(list = mockList)
                _state.value = _state.value.copy(isLoadedSuccess = true)
                android.util.Log.d("LeaderBoardViewModel", "State updated, triggering paging")
                // Trigger the paging data source to load
                rankDataSource.loadNextPage()
                android.util.Log.d("LeaderBoardViewModel", "Paging triggered")
            } catch (e: Exception) {
                android.util.Log.e("LeaderBoardViewModel", "Error in getLeaderBoardData: ${e.message}")
                _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }


}