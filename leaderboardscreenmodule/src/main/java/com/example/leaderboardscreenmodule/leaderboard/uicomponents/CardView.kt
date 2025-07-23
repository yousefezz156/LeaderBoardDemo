package com.example.leaderboardscreenmodule.leaderboard.uicomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leaderboardscreenmodule.R
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText

@Composable
fun CardView(mockData: MockData, modifier: Modifier = Modifier) {
    Column() {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 21.dp, end = 12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(contentAlignment = Alignment.Center, modifier = modifier.width(30.dp)){
                Text(text = mockData.rank.toString(), color = Color.Black)
            }
            Spacer(modifier = modifier.padding(4.dp))
            CircleShapeForRank(mockData)
            Spacer(modifier = modifier.padding(12.dp))
            Text(text = mockData.first_name, color = Color.Black)
            Spacer(modifier = modifier.padding(13.dp))
            Column(horizontalAlignment = Alignment.End, modifier = modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.transaction), color = colorResource(id = R.color.grey))
                Spacer(modifier = modifier.padding(4.dp))
                Text(text = mockData.score.toString(), color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

        if(mockData.rank !=10) {
            Divider(
                color = colorResource(id = R.color.grey),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 21.dp, end = 12.dp, top = 25.dp, bottom = 12.dp)
            )
        }else{
            Spacer(modifier = modifier.padding(bottom = 12.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun prev() {
    CardView(mockData = MockData(
        "YOUSEF",
        "eZZ",
        2123,
        10,
        colors = Color.Green
    )
    )
}