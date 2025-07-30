package com.example.leaderboardscreenmodule.leaderboard.filterscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.uicomponents.CardView

@Composable
fun FilterScreen( filterList: List<MockData>,navController: NavController,modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize())
    {
        lazyColumnInfo(filterList)

    }
}

@Composable
fun lazyColumnInfo(filterList: List<MockData>, modifier: Modifier = Modifier) {

    LazyColumn{
        items(filterList){ item ->
            CardView(mockData = item, dummyDataUiModel = DummyDataUiModel(1,"l","l","j",""))
        }
    }
}