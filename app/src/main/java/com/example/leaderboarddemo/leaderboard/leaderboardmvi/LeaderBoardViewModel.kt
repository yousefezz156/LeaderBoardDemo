package com.example.leaderboarddemo.leaderboard.leaderboardmvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaderboarddemo.mockdata.MockList
import com.example.leaderboarddemo.ui.theme.backgroundColorsForWhiteText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel(val leaderBoarderRepository: LeaderBoarderRepository= LeaderBoarderRepository(
    MockList()
)
) : ViewModel(){

    private var _state = MutableStateFlow(LeaderBoardState())
    var state: StateFlow<LeaderBoardState> = _state.asStateFlow()

    private var _colors = MutableStateFlow(backgroundColorsForWhiteText)
    var colors= _colors.asStateFlow()

    init{
        onEvent(LeaderBoardIntent.GetData)
    }

    fun onEvent(leaderBoardIntent: LeaderBoardIntent){
        when(leaderBoardIntent){
            is LeaderBoardIntent.GetData -> {
                getLeaderBoardData()
            }

        }
    }

    fun getLeaderBoardData(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                _state.value =_state.value.copy(list = leaderBoarderRepository.getMockList())
                _state.value = _state.value.copy(isLoadedSuccess = true)
            }catch (e:Exception){
                _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }


}