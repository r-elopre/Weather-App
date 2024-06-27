package com.example.weatherapp

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import com.example.weatherapp.view.DavaoScreen
import com.example.weatherapp.view.FirstScreen
import com.example.weatherapp.view.ManilaScreen
import com.example.weatherapp.view.RizalScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun AppNavigation() {
    var isAuthenticated by rememberSaveable { mutableStateOf(false) }

    if (isAuthenticated) {
        AuthenticatedNavigation()
    } else {
        FirstScreen(onAuthenticated = { isAuthenticated = true })
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthenticatedNavigation() {
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
