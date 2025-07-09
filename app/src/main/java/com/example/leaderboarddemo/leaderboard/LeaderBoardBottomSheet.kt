package com.example.leaderboarddemo.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.uicomponents.DateButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoardBottomSheet(
    name: String,
    dateTextFrom: String,
    onDateTextFromChange: (String) -> Unit,
    dateTextTo: String,
    onDateTextToChange: (String) -> Unit,
    onDismess: () -> Unit,
    onNameChange: (String) -> Unit,
    onShowDatePicker: (Boolean,Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismess,
        sheetState = bottomSheetState, containerColor = Color.White
    ) {
        Column(
            modifier = modifier
                .background(color = Color.White)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp)
            ) {
                Text(text = "Name", color = Color.Black, fontSize = 14.sp)
                Spacer(modifier = modifier.padding(end = 285.dp))
                Text(
                    text = "Reset",
                    color = colorResource(id = R.color.purple),
                    fontSize = 14.sp,
                    modifier = modifier.clickable { onNameChange("") }
                )
            }
            Spacer(modifier = modifier.padding(top = 16.dp))

            TextField(
                value = name,
                onValueChange = onNameChange,
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.enter_name
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(shape = RoundedCornerShape(12.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.semi_white),
                    unfocusedBorderColor = colorResource(id = R.color.semi_white),
                    containerColor = colorResource(id = R.color.semi_white)

                )

            )
            Spacer(modifier = modifier.padding(16.dp))
            HorizontalDivider(
                color = Color.Gray, modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 12.dp)
            )
            Spacer(modifier = modifier.padding(20.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.data_range),
                    color = colorResource(id = R.color.black),
                    fontSize = 14.sp,
                )
                Spacer(modifier = modifier.padding(end = 250.dp))
                Text(
                    text = stringResource(id = R.string.reset),
                    color = colorResource(id = R.color.purple),
                    fontSize = 14.sp,
                    modifier = modifier.clickable { onDateTextFromChange(""); onDateTextToChange("") }
                )
            }
            Spacer(modifier = modifier.padding(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Column {
                    Text(text = stringResource(id = R.string.from))
                    Spacer(modifier = modifier.padding(8.dp))
                    TextField(
                        value = dateTextFrom,
                        onValueChange = onDateTextFromChange,
                        trailingIcon = {
                            IconButton(onClick = { onShowDatePicker(true, true) }       /* onShowDatePicker is responsible to check if i am in the FROM textField or in the TO text field and if i am in the from i assign the onShowDatePicker first parameter with true in and assign the second parameter with true too, second parameter is true to show the datePicker*/
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = modifier
                            .width(159.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                1.dp,
                                color = colorResource(id = R.color.semi_white),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(id = R.color.semi_white),
                            unfocusedIndicatorColor = colorResource(id = R.color.semi_white),
                            containerColor = colorResource(id = R.color.semi_white)
                        ),
                        textStyle = TextStyle(textAlign = TextAlign.Justify)
                    )
                }
                Spacer(modifier = modifier.padding(21.dp))
                Column {
                    Text(text = stringResource(id = R.string.to))
                    Spacer(modifier = modifier.padding(8.dp))
                    TextField(
                        value = dateTextTo,
                        onValueChange = onDateTextToChange,
                        trailingIcon = {
                            IconButton(onClick = { onShowDatePicker(false, true)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = modifier
                            .width(159.dp)
                            .height(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                1.dp,
                                color = colorResource(id = R.color.semi_white),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(id = R.color.semi_white),
                            unfocusedIndicatorColor = colorResource(id = R.color.semi_white),
                            containerColor = colorResource(id = R.color.semi_white)
                        ),
                        textStyle = TextStyle(textAlign = TextAlign.Justify)
                    )
                }
            }
            Spacer(modifier = modifier.padding(10.dp))

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                DateButton(text_button = R.string.today)
                Spacer(modifier = modifier.padding(start = 10.dp))
                DateButton(text_button = R.string.this_week)
                Spacer(modifier = modifier.padding(start = 10.dp))
                DateButton(text_button = R.string.this_month)
                Spacer(modifier = modifier.padding(start = 10.dp))

            }
            Spacer(modifier = modifier.padding(16.dp))
            HorizontalDivider(
                color = Color.Gray, modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 21.dp, end = 12.dp)

            )
            Spacer(modifier = modifier.padding(16.dp))
            Button(
                onClick = onDismess,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 18.dp, bottom = 22.dp),
                shape = RoundedCornerShape(6.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.blue_light
                    )
                )
            ) {
                Text(text = "Apply", color = Color.White, fontSize = 12.sp)
            }

        }
    }
}