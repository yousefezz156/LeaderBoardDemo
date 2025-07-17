package com.example.leaderboarddemo.uicomponents

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.leaderboarddemo.R
import com.example.leaderboarddemo.mockdata.MockData
import com.skydoves.cloudy.Cloudy
import kotlinx.coroutines.delay

@Composable
fun CircleShapeTop(
    x: Dp,
    y: Dp,
    mockData: MockData,
    background_color: Int,
    show: Boolean,
    showNum1: Boolean = false,
    modifier: Modifier = Modifier
) {


    val density = LocalDensity.current

    var showBlur by remember {
        mutableStateOf(true)
    }




    val alphaAnim by animateFloatAsState(
        targetValue = if (show) 1f else 0f, // Animate to 1f when shown
        animationSpec = tween(
            durationMillis = 1000, // Match your scaleIn duration
            easing = FastOutSlowInEasing
        ), label = "alphaAnimation"
    )








    AnimatedVisibility(
        visible = show,
        enter = fadeIn(animationSpec = tween(800, 0))
                + scaleIn(initialScale = 0.8f, animationSpec = tween(800, 0)),
        exit = fadeOut(animationSpec = tween(500)),
        modifier = modifier
            .offset(x, y)

    ) {
        Box {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = modifier
                    .alpha(alphaAnim)
            ) {


                Box(
                    Modifier
                        .size(if (mockData.rank == 2 || mockData.rank == 3) 54.dp else 73.dp)
                        .clip(shape = androidx.compose.foundation.shape.CircleShape)
                        .border(
                            2.dp,
                            color = if (mockData.rank == 1) colorResource(id = R.color.dark_yello) else if (mockData.rank == 2) colorResource(
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

                    two_words += mockData.first_name.toCharArray()[0]
                    two_words += mockData.last_name.toCharArray()[0]
                    two_words = two_words.uppercase()
                    Text(text = two_words, color = Color.White, fontSize = 24.sp)

                }
                Spacer(modifier = modifier.padding(top = if (mockData.rank == 1) 10.dp else 5.dp))
                MockInfo(name = "Yousef", score = 1234, rank = mockData.rank, show = show)
            }
        }

        if (mockData.rank == 1) {
            AnimatedVisibility(
                visible = showNum1,
                enter = slideInVertically {
                    // Slide in from 40 dp from the top.
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.4f,
                ), modifier = modifier.offset(x = 20.dp, y = (-30).dp)) {
                Box(
                    modifier = modifier
                        .width(34.dp)
                        .height(26.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.group_1_1_),
                        contentDescription = null,
                        modifier = modifier
                            .width(34.dp)
                            .height(26.dp)
                    )
                }
            }
        }
    }

}



