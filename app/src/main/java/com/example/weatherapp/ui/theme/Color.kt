package com.example.weatherapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val SunnyGradientBackground = Brush.linearGradient(
    colors = listOf(
        Color(0xFF8A2387),
        Color(0xFFE94057),
        Color(0xFFF27121)
    )
)

val WinterGradientBackground = Brush.linearGradient(
    colors = listOf(
        Color(0xFF00B4DB), // light blue
        Color(0xFF0083B0), // darker blue
        Color(0xFF1C92D2)  // icy blue
    )
)

val CloudyGradientBackground = Brush.linearGradient(
    colors = listOf(
        Color(0xFFB0C4DE), // light steel blue
        Color(0xFF778899), // light slate gray
        Color(0xFF708090)  // slate gray
    )
)