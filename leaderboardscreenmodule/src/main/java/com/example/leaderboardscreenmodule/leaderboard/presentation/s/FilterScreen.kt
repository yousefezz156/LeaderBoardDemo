package com.example.leaderboardscreenmodule.leaderboard.presentation.s

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents.CardView
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText

@Composable
fun FilterScreen(filterList: List<MockData>, navController: NavController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize())
    {
        lazyColumnInfo(filterList)

    }
}

@Composable
fun lazyColumnInfo(filterList: List<MockData>, modifier: Modifier = Modifier) {

    val uiList = filterList.map {
        DummyDataUiModel(id = it.rank, firstName = it.first_name, lastName = it.last_name, email ="" , avater = "", backGroundColors = backgroundColorsForWhiteText.random())
    }

    LazyColumn{
        items(uiList){ item ->
            CardView(dummyDataUiModel = item)

        }
    }
}