package com.example.leaderboardscreenmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.leaderboarddemo.leaderboard.leaderboardmvi.LeaderBoardViewModelFactory
import kotlinx.coroutines.delay

internal class MainActivity : ComponentActivity() {
    private lateinit var viewModel: com.example.leaderboardscreenmodule.leaderboard.leaderboardmvi.LeaderBoardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val leaderBoarderRepository = LeaderBoarderRepository(mockList = MockList())
//        val factory = LeaderBoardViewModelFactory(leaderBoarderRepository)
//
//        // Get the ViewModel
//        viewModel = ViewModelProvider(this, factory)[LeaderBoardViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            com.example.leaderboardscreenmodule.theme.LeaderBoardDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        com.example.leaderboardscreenmodule.approutes.AppNav()

                        //testAnimatedVisibility()
                    }
                }
            }
        }
    }
}

@Composable
fun testAnimatedVisibility(modifier: Modifier = Modifier) {


    var changeState by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    LaunchedEffect(Unit) {
        delay(1000)
        changeState = true
    }


    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(onClick = { changeState = true }) {
                Text(text = "test")
            }
        }
        Spacer(modifier = modifier.padding(20.dp))
        AnimatedVisibility(
            visible = changeState,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.4f
            ), modifier = modifier.offset(120.dp,50.dp )) {
//            Text(
//                "Hello",
//                Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            )
            Box(modifier = modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(color = Color.Red)){}
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun prevAnimatedVisiability() {
    testAnimatedVisibility()
}
