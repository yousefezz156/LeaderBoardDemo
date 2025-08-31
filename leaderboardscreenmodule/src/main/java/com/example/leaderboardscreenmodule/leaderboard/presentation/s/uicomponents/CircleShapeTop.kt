package com.example.leaderboardscreenmodule.leaderboard.presentation.s.uicomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leaderboardscreenmodule.DumyApi.DummyDataUiModel
import com.example.leaderboardscreenmodule.R
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoardViewModel
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.mockdata.uicomponents.MockInfo
import kotlinx.coroutines.delay

@Composable
fun CircleShapeTop(
//    x: Dp,
//    y: Dp,
    //mockData: MockData,
    viewmodel: LeaderBoardViewModel,
    dummyDataUiModel: DummyDataUiModel,
    background_color: Int,
    //show: Boolean,
    showNum1: Boolean = false,
    modifier: Modifier = Modifier
) {


    val density = LocalDensity.current
    var state by remember {
        mutableStateOf(false)
    }
    var kingState by remember {
        mutableStateOf(false)
    }

    var stateViewmodel = viewmodel.state.collectAsState()




    LaunchedEffect(stateViewmodel.value.isRefreshSuccess) {
        if (stateViewmodel.value.isRefreshSuccess) {
            state = false
        }
            delay(if (dummyDataUiModel.id == 3) 500 else if (dummyDataUiModel.id == 2) 1000 else 2000)
            state = true
            if (dummyDataUiModel.id == 1) {
                delay(2500)
                kingState = true
        }
    }




//    val alphaAnim by animateFloatAsState(
//        targetValue = if (show) 1f else 0f, // Animate to 1f when shown
//        animationSpec = tween(
//            durationMillis = 1000, // Match your scaleIn duration
//            easing = FastOutSlowInEasing
//        ), label = "alphaAnimation"
//    )








    AnimatedVisibility(
        visible = state,
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)) +
                scaleIn(
                    animationSpec = tween(durationMillis = 1000),
                ),
        exit = fadeOut() +
                scaleOut(),
        modifier = modifier
    ) {
        Box(    contentAlignment = Alignment.TopCenter) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = modifier
                  //  .alpha(alphaAnim)
            ) {


                Box(
                    Modifier
                        .size(if (dummyDataUiModel.id == 2 || dummyDataUiModel.id  == 3) 54.dp else 73.dp)
                        .clip(shape = androidx.compose.foundation.shape.CircleShape)
                        .border(
                            2.dp,
                            color = if (dummyDataUiModel.id  == 1) colorResource(id = R.color.dark_yello) else if (dummyDataUiModel.id == 2) colorResource(
                                id = R.color.dark_orange
                            ) else colorResource(id = R.color.semi_light_blue),
                            androidx.compose.foundation.shape.CircleShape
                        )
                        .background(
                            color = colorResource(
                                id = background_color
                            )
                        ), contentAlignment = Alignment.Center
                ) {


                    var two_words = "";

                    two_words += dummyDataUiModel.firstName.toCharArray()[0]
                    two_words += dummyDataUiModel.lastName.toCharArray()[0]
                    two_words = two_words.uppercase()
                    Text(text = two_words, color = Color.White, fontSize = 24.sp)

                }
                Spacer(modifier = modifier.padding(top = if (dummyDataUiModel.id == 1) 10.dp else 5.dp))
                TopThreeInfo(dummyDataUiModel, score = 3422)
            }


        if (dummyDataUiModel.id  == 1) {
            AnimatedVisibility(
                visible = kingState,
                enter = slideInVertically { with(density) { -40.dp.roundToPx() } } +
                        expandVertically(expandFrom = Alignment.Top) +
                        fadeIn(initialAlpha = 0.4f),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-30).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_1_1_),
                    contentDescription = null,
                    modifier = Modifier
                        .width(34.dp)
                        .height(26.dp)
                )
            }
        }
            }
        }
    }





