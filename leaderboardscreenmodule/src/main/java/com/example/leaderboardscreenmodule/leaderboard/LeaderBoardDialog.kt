package com.example.leaderboardscreenmodule.leaderboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.Dialog
import com.example.leaderboardscreenmodule.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoarderDialog(
    name: String,
    dateTextFrom: String,
    onDateTextFromChange: (String) -> Unit,
    dateTextTo: String,
    onDateTextToChange: (String) -> Unit,
    onDismess: () -> Unit,
    onNameChange: (String) -> Unit, show: Boolean,
    onShowDatePicker: (Boolean, Boolean) -> Unit, modifier: Modifier = Modifier
) {



        Dialog(onDismissRequest = { onDismess() }) {
            Card(shape = RoundedCornerShape(16.dp)) {


                Column(
                    modifier = modifier
                        .background(color = Color.White)
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
                    ) {
                        Text(text = "Name", color = Color.Black, fontSize = 14.sp)
                        Spacer(modifier = modifier.padding(end = 195.dp))
                        Text(
                            text = "Reset",
                            color = colorResource(id = R.color.purple),
                            fontSize = 12.sp,
                            modifier = modifier.clickable { onNameChange("") }
                        )
                    }
                    Spacer(modifier = modifier.padding(top = 8.dp))

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

                        ),
                        textStyle = TextStyle(color = Color.Black)

                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    HorizontalDivider(
                        color = Color.Gray, modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = modifier.padding(12.dp))
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
                        Spacer(modifier = modifier.padding(end = 155.dp))
                        Text(
                            text = stringResource(id = R.string.reset),
                            color = colorResource(id = R.color.purple),
                            fontSize = 14.sp,
                            modifier = modifier.clickable {
                                onDateTextFromChange(""); onDateTextToChange(
                                ""
                            )
                            }
                        )
                    }
                    Spacer(modifier = modifier.padding(16.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp)
                    ) {
                        Column {
                            Text(text = stringResource(id = R.string.from), color = Color.Black)
                            Spacer(modifier = modifier.padding(8.dp))
                            TextField(
                                value = dateTextFrom,
                                onValueChange = onDateTextFromChange, readOnly = true,
                                trailingIcon = {
                                    IconButton(onClick = {
                                        onShowDatePicker(
                                            true,
                                            true
                                        )
                                    }       /* onShowDatePicker is responsible to check if i am in the FROM textField or in the TO text field and if i am in the from i assign the onShowDatePicker first parameter with true in and assign the second parameter with true too, second parameter is true to show the datePicker*/
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.calendar),
                                            contentDescription = null
                                        )
                                    }
                                },
                                modifier = modifier
                                    .width(129.dp)
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
                                textStyle = TextStyle(
                                    textAlign = TextAlign.Justify,
                                    fontSize = 10.sp,
                                    color = Color.Black
                                )
                            )
                        }
                        Spacer(modifier = modifier.padding(8.dp))
                        Column {
                            Text(text = stringResource(id = R.string.to), color = Color.Black)
                            Spacer(modifier = modifier.padding(8.dp))
                            TextField(
                                value = dateTextTo, readOnly = true,
                                onValueChange = onDateTextToChange,
                                trailingIcon = {
                                    IconButton(onClick = {
                                        onShowDatePicker(false, true)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.calendar),
                                            contentDescription = null
                                        )
                                    }
                                },
                                modifier = modifier
                                    .width(129.dp)
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
                                textStyle = TextStyle(
                                    textAlign = TextAlign.Justify,
                                    fontSize = 10.sp,
                                    color = Color.Black
                                ),

                                )
                        }
                    }
                    Spacer(modifier = modifier.padding(10.dp))

//            Row(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp)
//            ) {
//                DateButton(text_button = R.string.today)
//                Spacer(modifier = modifier.padding(start = 10.dp))
//                DateButton(text_button = R.string.this_week)
//                Spacer(modifier = modifier.padding(start = 10.dp))
//                DateButton(text_button = R.string.this_month)
//                Spacer(modifier = modifier.padding(start = 10.dp))
//
//            }
//            Spacer(modifier = modifier.padding(16.dp))
//            HorizontalDivider(
//                color = Color.Gray, modifier = modifier
//                    .fillMaxWidth()
//                    .padding(start = 21.dp, end = 12.dp)
//
//            )
                    Spacer(modifier = modifier.padding(8.dp))
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

}

