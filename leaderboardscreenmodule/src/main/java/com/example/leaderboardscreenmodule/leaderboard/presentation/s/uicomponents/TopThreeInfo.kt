package com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.R

@Composable
fun TopThreeInfo(dummyDataUiModel: DummyDataUiModel,score:Int,modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
//            modifier = modifier.padding(
//                start = if (rank == 2 || rank == 3) 25.dp else 31.dp,
//                top = if (rank == 2 || rank == 3) 16.dp else 31.dp,
//                end = if (rank == 3) 25.dp else 0.dp
//            )
    ) {
        Text(
            text = dummyDataUiModel.firstName,
            color = Color.White,
            fontSize = 12.sp,

            )
        Spacer(modifier = modifier.padding(6.dp))
        Text(
            text = score.toString() + " Pts",
            color = if (dummyDataUiModel.id == 2) colorResource(id = R.color.orange) else if (dummyDataUiModel.id == 3) colorResource(
                id = R.color.blue_light
            ) else colorResource(
                id = R.color.yellow
            ),
            fontSize = 15.sp
        )
        Spacer(modifier = modifier.padding(if (dummyDataUiModel.id == 1) 16.dp else 2.dp))
        Text(dummyDataUiModel.id.toString(), color = Color.White, fontSize = 32.sp)
    }
}