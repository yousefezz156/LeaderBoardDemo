package com.example.leaderboardscreenmodule.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.leaderboardscreenmodule.R

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val blue = Color(0X002F87)
val blue_light = Color(0X3E97FF)
val grey = Color(0Xbbbbbb)


val backgroundColorsForWhiteText = listOf( // Blues
    Color(0xFFD98C55), // Dark Blue (Material Blue 900)
    Color(0xFF3BA040), // Medium Dark Blue (Material Blue 700)
    Color(0xFF2D9F99), // Standard Blue (Material Blue 600)
    Color(0xFFA09EFC), // Dark Cyan-Blue (Material Light Blue 800
    Color(0xFFAD1457), // Dark Pink (Material Pink 700)
    Color(0xFF4A148C), // Dark Purple (Material Purple 900)
)

object ColorWhitelabel {
    var active_color: Color = Color(0xFF0D0D80)
    var main_color: Color = Color(0xFFFFFFFF)
    var second_text_color: Color = Color(0xFF727272)
    var main_text_color: Color = Color(0xFF101018)
    var variant_text_color: Color = Color(0xFFFFFFFF)
    var button_color: Color = Color(0xFF0D0D80)
    var error_color: Color = Color(0xFF960415)
    var success_color: Color = Color(0xFF20C090)
    var failing_image_color: Color = Color(0xFF989898)
    var failing_image_bg: Color = Color(0xFFE8E8E8)
    var color_scheme_is_dark: Boolean = false

    var fontFamily = FontFamily(Font(R.font.poppins_regular))
}


