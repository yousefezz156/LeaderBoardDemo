package com.example.leaderboardscreenmodule.leaderboard.filterscreen.filtermvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilterScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(FilterState())
    val state: StateFlow<FilterState> = _state.asStateFlow()




    fun getFilterData(filterList : List<MockData>){
        viewModelScope.launch {
            _state.value=_state.value.copy(listOfFilter =filterList )
        }
    }
}