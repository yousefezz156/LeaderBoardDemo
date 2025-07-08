package com.example.leaderboarddemo.uicomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.mockdata.MockData

@Composable
fun CircleShapeTop(
    x: Dp,
    y: Dp,
    mockData: MockData,
    background_color: Int,
    modifier: Modifier = Modifier
) {
    Box {
        Box(
            Modifier
                .offset(x, y)
                .size(if (mockData.rank == 2 || mockData.rank == 3) 54.dp else 73.dp)
                .clip(shape = androidx.compose.foundation.shape.CircleShape)
                .border(2.dp, color = colorResource(id = R.color.blue_light), androidx.compose.foundation.shape.CircleShape)
                .background(
                    color = colorResource(
                        id = background_color
                    )
                ), contentAlignment = Alignment.Center
        ) {


            var two_words = "";

            two_words += mockData.first_name.toCharArray()[0]
            two_words += mockData.last_name.toCharArray()[0]
            two_words = two_words.uppercase()
            Text(text = two_words, color = Color.White, fontSize = 24.sp)

        }
        if (mockData.rank == 1) {
            Box(
                modifier = modifier
                    .width(34.dp)
                    .height(26.dp)
                    .offset(x = 176.dp, y = 25.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_1_1_),
                    contentDescription = null,
                    modifier = modifier
                        .width(34.dp)
                        .height(26.dp)
                )
            }
        }
    }

}