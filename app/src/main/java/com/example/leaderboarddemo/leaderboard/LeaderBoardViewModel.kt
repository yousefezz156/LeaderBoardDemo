package com.example.leaderboarddemo.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderBoardViewModel : ViewModel(){

    private var _state = MutableStateFlow(LeaderBoardState())
    var state:StateFlow<LeaderBoardState> = _state.asStateFlow()

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
                // update the state of list
                //update the state of isSuccess
        }catch (e:Exception){
            _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }
}