package com.example.leaderboarddemo.leaderboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.mockdata.MockData
import com.example.leaderboarddemo.mockdata.MockList
import com.example.leaderboarddemo.uicomponents.CalendatTextfilled
import com.example.leaderboarddemo.uicomponents.CardView
import com.example.leaderboarddemo.uicomponents.CircleShapeTop
import com.example.leaderboarddemo.uicomponents.DateButton
import com.example.leaderboarddemo.uicomponents.MiddleBar
import com.example.leaderboarddemo.uicomponents.MockInfo
import com.example.leaderboarddemo.uicomponents.ModalBottomSheetLB
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
/*Note if you will add the navigation class then remove =viewModel() from the parameter then add it in the nav class to
 avoid recreating view model (if you will use the same LeaderBoardViewModel in more than one screen)
 */

fun LeaderBoardScreen(leaderBoardViewModel: LeaderBoardViewModel= androidx.lifecycle.viewmodel.compose.viewModel(), mockList: List<MockData>, modifier: Modifier = Modifier) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var isDateTextFrom by remember{
        mutableStateOf(false)
    }
    var isDateTextTo by remember{
        mutableStateOf(false)
    }
    var dateTextFrom by remember { mutableStateOf("") }
    var dateTextTo by remember { mutableStateOf("") }

    var bottomSheetState = rememberModalBottomSheetState()

    var showDatePicker by remember { mutableStateOf(false) }


    var name by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.1557f to Color(0xFF002F87),
                        1.0f to Color(0xFF3E97FF)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(2000f, 1400f)
                )

            )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Spacer(modifier = modifier.padding(start = 16.dp))
                Image(
                    painter = painterResource(R.drawable.frame),
                    contentDescription = null,
                    modifier = modifier.size(48.dp)
                )
                Spacer(modifier = modifier.padding(start = 16.dp))
                Column {
                    Text(
                        text = stringResource(R.string.hello),
                        color = colorResource(R.color.white)
                    )
                    Spacer(modifier = modifier.padding(top = 8.dp))
                    Text(
                        text = stringResource(R.string.welcome_challenge),
                        color = colorResource(R.color.white)
                    )

                }
                Row(horizontalArrangement = Arrangement.End, modifier = modifier.fillMaxWidth()) {
                    Box(
                        modifier = modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(
                                RoundedCornerShape(8.dp)
                            )
                            .background(color = colorResource(R.color.white))

                    ) {

                        Image(
                            painter = painterResource(R.drawable.gift),
                            contentDescription = null,
                            modifier = modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            Spacer(modifier = modifier.padding(top = 16.dp))
            Box() {
                MiddleBar()
            }



            Box {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 150.dp, start = 19.dp, end = 16.dp)
                        .height(130.dp)
                        .width(342.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(colorResource(id = R.color.blue))
                )
                {
                    MockInfo(name = "Ahmed", score = 1234, rank = 2)
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        MockInfo(name = "Ahmed", score = 1234, rank = 3)
                    }
                }
                Box(
                    modifier = modifier
                        .offset(132.dp, 104.dp)
                        .height(176.dp)
                        .width(122.dp)
                        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(
                            color = colorResource(
                                id = R.color.blue_light
                            )
                        )
                ) {

                    MockInfo(name = "Yousef", score = 1563, rank = 1)
                }
                CircleShapeTop(
                    x = 305.dp,
                    y = 110.dp,
                    mockData = mockList[2],
                    background_color = R.color.grey
                )

                CircleShapeTop(
                    x = 157.dp,
                    y = 56.dp,
                    mockData = mockList[0],
                    background_color = R.color.grey
                )

                CircleShapeTop(
                    50.dp, 110.dp,
                    mockData = mockList[1],
                    background_color = R.color.purple_200
                )


            }
        }


        Column(
            modifier = modifier
                .width(410.dp)
                .height(800.dp)
                .clip(RoundedCornerShape(topStart = 19.dp, topEnd = 19.dp))
                .background(color = Color.White)
        ) {
            Spacer(modifier = modifier.padding(21.dp))
            Row(modifier = modifier) {
                Column(modifier = modifier.padding(start = 21.dp)) {
                    Text(
                        stringResource(id = R.string.ranking),
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        stringResource(id = R.string.myrank),
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.blue_light)
                    )
                    Spacer(modifier = modifier.padding(12.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                        .padding(top = 25.dp, end = 25.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.general),
                        contentDescription = null,
                        modifier = modifier
                            .height(17.dp)
                            .width(14.dp)
                            .clickable { showBottomSheet = true }
                    )
                    Spacer(modifier = modifier.padding(8.dp))
                    Text(text = stringResource(id = R.string.filter))
                }


            }
            HorizontalDivider(
                color = Color.Gray, modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 21.dp, end = 12.dp)
            )

            Spacer(modifier = modifier.padding(top = 16.dp))
            lazyColumn(mockList = mockList)


        }

        if(showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
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
                            modifier = modifier.clickable { name = "" }
                        )
                    }
                    Spacer(modifier = modifier.padding(top = 16.dp))

                    TextField(
                        value = name,
                        onValueChange = { name = it },
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
                            focusedBorderColor = colorResource(id = R.color.Stroke),
                            unfocusedBorderColor = colorResource(id = R.color.Stroke),
                            containerColor = colorResource(id = R.color.Stroke)

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
                            modifier = modifier.clickable { dateTextFrom = ""; dateTextTo = "" }
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
                            TextField(value = dateTextFrom,
                                onValueChange = { dateTextFrom = it },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        isDateTextFrom=true
                                        showDatePicker=true}) {
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
                                        color = colorResource(id = R.color.Stroke),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = colorResource(id = R.color.Stroke),
                                    unfocusedIndicatorColor = colorResource(id = R.color.Stroke),
                                    containerColor = colorResource(id = R.color.Stroke)
                                ),
                                textStyle = TextStyle(textAlign = TextAlign.Justify)
                            )
                        }
                        Spacer(modifier = modifier.padding(21.dp))
                        Column {
                            Text(text = stringResource(id = R.string.to))
                            Spacer(modifier = modifier.padding(8.dp))
                            TextField(value = dateTextTo,
                                onValueChange = { dateTextTo = it },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        isDateTextTo=true
                                        showDatePicker=true}) {
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
                                        color = colorResource(id = R.color.Stroke),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = colorResource(id = R.color.Stroke),
                                    unfocusedIndicatorColor = colorResource(id = R.color.Stroke),
                                    containerColor = colorResource(id = R.color.Stroke)
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
                        onClick = { showBottomSheet = false },
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

        if (showDatePicker) {
            DatePickerChooser(onConfirm = {
                var c = Calendar.getInstance()
                c.timeInMillis =
                    it.selectedDateMillis!! // calender class is used to convert from ms to date
                var dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                if(isDateTextFrom) {
                    dateTextFrom = dateFormatter.format(c.time)
                }else{
                    dateTextTo = dateFormatter.format(c.time)
                }

                showDatePicker = false
                isDateTextFrom =false
                isDateTextTo = false
            }, onDismiss = {
                showDatePicker = false
                isDateTextFrom =false
                isDateTextTo = false
            })
        }



    }

}

@Composable
fun lazyColumn(mockList: List<MockData>) {
    LazyColumn {
        items(mockList) { item ->
            if (item.rank > 3)
                CardView(mockData = item)

        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    DatePickerDialog(onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "Ok")

            }


        },
        dismissButton = {

            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")

            }
        }

    ) {
        DatePicker(state = datePickerState)
    }
}


@Preview(showBackground = true)
@Composable
fun LeaderBoardPreview() {
    LeaderBoardScreen(mockList = MockList().getList())

}
