package com.example.weatherapp


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherapp.view.DavaoScreen
import com.example.weatherapp.view.ManilaScreen
import com.example.weatherapp.view.RizalScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppNavigation() {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = 3, // Specify the number of pages here
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> RizalScreen()
            1 -> ManilaScreen()
            2 -> DavaoScreen()
        }
    }
}