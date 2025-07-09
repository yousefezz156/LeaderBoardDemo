package com.example.leaderboarddemo.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboarddemo.R

@Composable
fun DateButton(text_button: Int,modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .border(
                1.dp, color = colorResource(id = R.color.semi_white), shape = RoundedCornerShape(12.dp)
            )
            .height(48.dp)
            .width(114.dp), shape = RoundedCornerShape(12.dp),
        onClick = { if(text_button == R.string.today) {} else if (text_button ==R.string.this_week){} else{ }},
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
    )
    {
        Text(
            text = stringResource(text_button),
            textAlign = TextAlign.Center, maxLines = 1,
            fontSize = 12.sp, color = Color.Black,

        )
    }
}