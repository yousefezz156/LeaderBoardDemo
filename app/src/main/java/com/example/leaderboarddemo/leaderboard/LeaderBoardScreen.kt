package com.example.leaderboarddemo.leaderboard

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.invalidateGroupsWithKey
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.leaderboard.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboarddemo.mockdata.MockData
import com.example.leaderboarddemo.mockdata.MockList
import com.example.leaderboarddemo.uicomponents.CalendatTextfilled
import com.example.leaderboarddemo.uicomponents.CardView
import com.example.leaderboarddemo.uicomponents.CircleShapeTop
import com.example.leaderboarddemo.uicomponents.DateButton
import com.example.leaderboarddemo.uicomponents.MiddleBar
import com.example.leaderboarddemo.uicomponents.MockInfo
import kotlinx.coroutines.delay
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.Delayed


@OptIn(ExperimentalMaterial3Api::class)
@Composable
/*Note if you will add the navigation class then remove =viewModel() from the parameter then add it in the nav class to
 avoid recreating view model (if you will use the same LeaderBoardViewModel in more than one screen)
 */

fun LeaderBoardScreen(leaderBoardViewModel: LeaderBoardViewModel = viewModel(), mockList: List<MockData>, modifier: Modifier = Modifier) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var isDateTextFrom by remember{
        mutableStateOf(false)
    }
    var isDateTextTo by remember{
        mutableStateOf(false)
    }

    var isVisible1 by remember {
        mutableStateOf(false)
    }
    var isVisible2 by remember {
        mutableStateOf(false)
    }
    var isVisible3 by remember {
        mutableStateOf(false)
    }
    var showKing by remember {
        mutableStateOf(false)
    }
    var dateTextFrom by remember { mutableStateOf("") }
    var dateTextTo by remember { mutableStateOf("") }
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
                    end = Offset(100f, 1400f)
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
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = modifier.fillMaxWidth()
                    ) {
                    }
                }
                Box(
                    modifier = modifier
                        .offset(145.dp, 104.dp)
                        .height(176.dp)
                        .width(122.dp)
                        .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                        .background(
                            color = colorResource(
                                id = R.color.blue_light
                            )
                        )
                ) {

                }


                CircleShapeTop(
                    show = isVisible3,
                    x = 305.dp,
                    y = 110.dp,
                    mockData = mockList[2],
                    background_color = R.color.blue_light,
                )
                CircleShapeTop(
                    50.dp, 110.dp,
                    mockData = mockList[1],
                    background_color = R.color.orange,
                    show = isVisible2,
                )
                CircleShapeTop(
                    x = 170.dp,
                    y = 56.dp,
                    mockData = mockList[0],
                    background_color = R.color.yellow,
                    show = isVisible1,
                    showNum1 = showKing,
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
                    Row() {
                        Text(
                            stringResource(id = R.string.myrank),
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.blue_light)
                        )
                        Spacer(modifier = modifier.padding(6.dp))
                        Box(contentAlignment = Alignment.Center) {


                            Image(
                                painter = painterResource(id = R.drawable.rectangle_6),
                                contentDescription = null
                            )
                            Text(
                                text = mockList.size.toString(),
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
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
                color = Color.LightGray, modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 21.dp, end = 12.dp)
            )

            Spacer(modifier = modifier.padding(top = 16.dp))
            lazyColumn(viewModel = leaderBoardViewModel)


        }

        if (showBottomSheet) {
            LeaderBoardBottomSheet(
                name = name,
                dateTextFrom = dateTextFrom,
                onDateTextFromChange = { dateTextFrom = it },
                dateTextTo = dateTextTo,
                onDateTextToChange = { dateTextTo = it },
                onDismess = { showBottomSheet = false },
                onNameChange = { name = it },
                onShowDatePicker = { FromTextField, show ->
                    if (FromTextField) {
                        isDateTextFrom = true
                        showDatePicker = show
                    } else {
                        isDateTextTo = true
                        showDatePicker = show
                    }
                }
            )
        }

        if (showDatePicker) {
            DatePickerChooser(onConfirm = {
                var c = Calendar.getInstance()
                c.timeInMillis =
                    it.selectedDateMillis!! // calender class is used to convert from ms to date
                var dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                if (isDateTextFrom) {
                    dateTextFrom = dateFormatter.format(c.time)
                } else {
                    if (isDateTextFrom != null) {

                        dateTextTo = dateFormatter.format(c.time)
                    }
                }

                showDatePicker = false
                isDateTextFrom = false
                isDateTextTo = false
            }, dateTextTo = dateTextTo,
                dateTextFrom = dateTextFrom,
                isDateTextTo = isDateTextTo, onDismiss = {
                    showDatePicker = false
                    isDateTextFrom = false
                    isDateTextTo = false
                })
        }

    }


        LaunchedEffect(Unit) {
            delay(500)
            isVisible3 = true
            delay(1000)
            isVisible2 = true
            delay(1000)
            isVisible1 = true
            delay(1000)
            showKing = true
        }
    }




@Composable
fun lazyColumn(viewModel: LeaderBoardViewModel) {
    val list by viewModel.state.collectAsState()
    LazyColumn {
        items(list.list) { item ->
            if(item.rank>3) {
                key(item.rank) {
                    CardView(mockData = item)
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun LeaderBoardPreview() {
    LeaderBoardScreen(mockList = MockList().getList())
}
