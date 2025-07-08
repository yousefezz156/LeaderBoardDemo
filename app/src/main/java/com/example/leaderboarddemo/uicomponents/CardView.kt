package com.example.leaderboarddemo.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.mockdata.MockData

@Composable
fun CardView(mockData: MockData, modifier: Modifier = Modifier) {
    Column() {


        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = mockData.rank.toString())
            Spacer(modifier = modifier.padding(13.dp))
            CircleShapeForRank(mockData)
            Spacer(modifier = modifier.padding(24.dp))
            Text(text = mockData.first_name)
            Spacer(modifier = modifier.padding(13.dp))
            Column(horizontalAlignment = Alignment.End, modifier = modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.transaction))
                Spacer(modifier = modifier.padding(4.dp))
                Text(text = mockData.score.toString())
            }
        }

        Divider(
            color = Color.DarkGray,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 12.dp, top = 35.dp, bottom = 30.dp)
        )
    }

}