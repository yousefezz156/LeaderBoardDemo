package com.example.leaderboardscreenmodule.leaderboard.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leaderboardscreenmodule.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendatTextfilled(dateTextFrom: String, dateTextTo: String,upperLabel: Int, modifier: Modifier = Modifier) {
    var dateTextFrom by remember { mutableStateOf("") }
    var dateTextTo by remember { mutableStateOf("") }

    Text(text = stringResource(id = upperLabel))
    Spacer(modifier = modifier.padding(8.dp))
    TextField(value = dateTextFrom, onValueChange = { dateTextFrom = it }, trailingIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.calendar), contentDescription = null)
        }
    }, modifier = modifier
        .width(159.dp)
        .height(48.dp)
        .clip(RoundedCornerShape(12.dp))
        .border(
            1.dp,
            color = colorResource(id = R.color.semi_white),
            shape = RoundedCornerShape(12.dp)
        ), colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = colorResource(id = R.color.semi_white),
            unfocusedIndicatorColor = colorResource(id = R.color.semi_white),
            containerColor = colorResource(id = R.color.semi_white)
        ), textStyle = TextStyle(textAlign = TextAlign.Justify)
    )

}