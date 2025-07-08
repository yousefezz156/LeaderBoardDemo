package com.example.leaderboarddemo.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboarddemo.R

@Composable
fun MiddleBar(modifier: Modifier = Modifier) {
    val list = listOf("Leadboard", "Chanllenges", "Challenges", "challenges")
    var indexAt by remember { mutableStateOf(0) }

    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(list) { item ->

            Column(modifier = modifier
                .clickable { indexAt = list.indexOf(item) }
                .padding(start = 58.dp)) {
                Text(
                    text = item, fontSize = 14.sp,
                    color = if (list.indexOf(item) == indexAt) colorResource(R.color.white) else colorResource(
                        R.color.grey
                    )
                )
                if (list.indexOf(item) == indexAt) {
                    Divider(
                        modifier = modifier
                            .padding(top = 10.dp)
                            .width(80.dp)
                            .height(2.dp), color = colorResource(R.color.white)
                    )
                }

            }


        }

    }


}