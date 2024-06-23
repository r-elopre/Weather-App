package com.example.weatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun WeatherInfoBox(title: String, value: String, brush: Brush) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                brush = brush,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = Color.White)
            Text(text = value, color = Color.White)
        }
    }
}