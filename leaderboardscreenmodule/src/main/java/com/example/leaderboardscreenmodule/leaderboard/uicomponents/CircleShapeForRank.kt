package com.example.leaderboardscreenmodule.leaderboard.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.leaderboard.mockdata.MockData
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText

@Composable
fun CircleShapeForRank( dummyDataUiModel: DummyDataUiModel, modifier: Modifier = Modifier) {

    val first_name = dummyDataUiModel.firstName.toCharArray()[0]
    val last_name = dummyDataUiModel.lastName.toCharArray()[0]
    var two_words = ""
    two_words += first_name
    two_words += last_name
    Box(
        modifier = modifier
            .size(42.dp)
            .clip(shape = CircleShape)
            .background(
                color = backgroundColorsForWhiteText.random()
            ), contentAlignment = Alignment.Center
    ) {
        Text(text = two_words, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }

}