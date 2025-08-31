package com.example.leaderboardscreenmodule

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import com.example.leaderboardscreenmodule.leaderboard.presentation.s.leaderboardmvi.LeaderBoardViewModel
//import com.example.leaderboarddemo.leaderboard.leaderboardmvi.LeaderBoardViewModelFactory
import kotlinx.coroutines.delay

internal class MainActivity : ComponentActivity() {
    private lateinit var viewModel: LeaderBoardViewModel
    @RequiresApi(Build.VERSION_CODES.O)
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
internal fun testAnimatedVisibility(modifier: Modifier = Modifier) {


//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        // This Box is automatically centered within the parent Box
//        Box(
//            modifier = Modifier
//                .size(100.dp)
//                .background(Color.Blue)
//        )
//    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        // You now have access to constraints like maxWidth and maxHeight
        val boxSize = 100.dp

        // Calculate the exact center point
        val offsetX = (maxWidth - boxSize) / 2
        val offsetY = (maxHeight - boxSize) / 2

        // Use the calculated offsets to place the Box
        Box(
            modifier = Modifier
                .size(boxSize)
                .offset(x = offsetX, y = offsetY)
                .background(Color.Red)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun prevAnimatedVisiability() {
    testAnimatedVisibility()
}
