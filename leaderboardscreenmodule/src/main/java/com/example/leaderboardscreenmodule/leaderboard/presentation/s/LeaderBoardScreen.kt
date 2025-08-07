package com.example.leaderboardscreenmodule.leaderboard.presentation.s


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.leaderboardscreenmodule.R
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoardIntent
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.MockData
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents.CardView
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents.CircleShapeTop
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents.DatePickerChooser
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents.LeaderBoarderDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /*Note if you will add the navigation class then remove =viewModel() from the parameter then add it in the nav class to
         avoid recreating view model (if you will use the same LeaderBoardViewModel in more than one screen)
         */

fun LeaderBoardScreen(
    leaderBoardViewModel: LeaderBoardViewModel,
     navController: NavController,
    modifier: Modifier = Modifier
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    var isDateTextFrom by remember {
        mutableStateOf(false)
    }
    var isDateTextTo by remember {
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

    val state = leaderBoardViewModel.state.collectAsState()
    var isRefreshing = state.value.isRefreshSuccess

    val pullRefreshState = rememberPullToRefreshState()


    val currentDate = LocalDate.now()

    val formater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    var dateTextFrom by remember { mutableStateOf(currentDate.format(formater)) }
    var dateTextTo by remember { mutableStateOf(currentDate.format(formater)) }
    var showDatePicker by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val listPage = leaderBoardViewModel.pageConfig.collectAsLazyPagingItems()
    val firstThreeApiItems = listPage.itemSnapshotList.items.take(3)
    val hasTop3 = firstThreeApiItems.size == 3

    PullToRefreshBox(
        state = pullRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch { leaderBoardViewModel.onEvent(LeaderBoardIntent.RefreshData)
                isRefreshing=state.value.isRefreshSuccess
                delay(1000)
                leaderBoardViewModel.setRefreshKeyFalse() }

        }) {


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
                    )

                    if (hasTop3) {
                        CircleShapeTop(
                            show = isVisible3,
                            x = 305.dp,
                            y = 110.dp,
                            dummyDataUiModel = firstThreeApiItems[2],
                            background_color = R.color.blue_light,
                        )
                        CircleShapeTop(
                            50.dp, 110.dp,
                            dummyDataUiModel = firstThreeApiItems[1],
                            background_color = R.color.orange,
                            show = isVisible2,
                        )
                        CircleShapeTop(
                            x = 170.dp,
                            y = 56.dp,
                            dummyDataUiModel = firstThreeApiItems[0],
                            background_color = R.color.yellow,
                            show = isVisible1,
                            showNum1 = showKing,
                        )
                    }


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
                        Row {
                            Text(
                                "(",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.blue_light)
                            )
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
                                    text = "12",
                                    color = Color.White,
                                    fontSize = 10.sp
                                )
                            }
                            Text(
                                ")",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.blue_light)
                            )
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
                        Row(
                            modifier = modifier.clickable { showDialog = true },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.general),
                                contentDescription = null,
                                modifier = modifier
                                    .height(17.dp)
                                    .width(14.dp)
                            )
                            Spacer(modifier = modifier.padding(8.dp))
                            Text(text = stringResource(id = R.string.filter))
                        }

                    }


                }
                HorizontalDivider(
                    color = Color.LightGray, modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 21.dp, end = 12.dp)
                )

                Spacer(modifier = modifier.padding(top = 16.dp))
                LazyColumnForData( viewModel = leaderBoardViewModel)


            }

            if (showDialog) {
                LeaderBoarderDialog(
                    name = name,
                    dateTextFrom = dateTextFrom,
                    onDateTextFromChange = { dateTextFrom = it },
                    dateTextTo = dateTextTo,
                    onDateTextToChange = { dateTextTo = it },
                    onDismess = { showDialog = false },
                    onNameChange = { name = it }, show = showDialog, navController = navController,
                    onShowDatePicker = { fromTextField, show ->
                        if (fromTextField) {
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
                    val c = Calendar.getInstance()
                    c.timeInMillis =
                        it.selectedDateMillis!! // calender class is used to convert from ms to date
                    val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
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
    }


    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            isVisible1 = false
            isVisible2 = false
            isVisible3 = false
            showKing = false
        }

            // Optional: small delay to allow recomposition
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
fun LazyColumnForData(
    viewModel: LeaderBoardViewModel
) {
    //val list by viewModel.state.collectAsState()

    val listPage = viewModel.pageConfig.collectAsLazyPagingItems()
    var lastIndex: Boolean



    LazyColumn {

        items(listPage.itemCount) { items ->
            lastIndex = items == listPage.itemCount - 1
            listPage[items]?.let { dummy ->
                if (dummy.id > 3) {
                    CardView(dummyDataUiModel = dummy)
                }
            }
            Log.d("PagingDebug", "index $lastIndex")
        }

            when {
                listPage.loadState.append is LoadState.Loading -> {
                    item {   CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(x = 180.dp)
                            .padding(8.dp)
                    )
                    }

                }
            }







//            if(listPage.loadState.append is LoadState.Loading  ){
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(50.dp)
//                        .offset(x = 180.dp)
//                        .padding(8.dp)
//                )
//            }

        }


    }





//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun LeaderBoardPreview() {
//    LeaderBoardScreen(viewModel(), mockList = MockList().getList())
//}
