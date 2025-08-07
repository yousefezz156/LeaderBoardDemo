package com.example.leaderboardscreenmodule.DumyApi

import androidx.compose.ui.graphics.Color
import com.example.leaderboardscreenmodule.theme.backgroundColorsForWhiteText
import com.google.gson.annotations.SerializedName

data class DummyDataUiModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avater: String?,
    val backGroundColors: Color = backgroundColorsForWhiteText.random(),
)
