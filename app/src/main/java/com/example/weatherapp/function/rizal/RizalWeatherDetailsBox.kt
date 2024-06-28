package com.example.weatherapp.function.rizal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.ui.theme.WinterGradientBackground
import com.example.weatherapp.view.WeatherInfoBox
import com.example.weatherapp.viewModel.RizalWeatherViewModel

@Composable
fun RizalWeatherDetailsBox(viewModel: RizalWeatherViewModel) {
    val weatherData = viewModel.weatherData.collectAsState()


    Column {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                text = weatherData.value?.location?.localtime ?: "Loading...",
                modifier = Modifier
                    .padding(20.dp)
            )
            Text(
                text = weatherData.value?.current?.temp_c?.let { "$it°C" } ?: "Loading...",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 80.dp, end = 40.dp),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )
            weatherData.value?.let { data ->
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterStart)
                        .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Row {
                        Column {
                            Text(
                                text = "Rodriguez Rizal",
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = data.current.condition.text,
                                color = Color.Black,
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Medium,
                                fontStyle = FontStyle.Italic,
                                style = TextStyle(
                                    lineHeight = 30.sp // Adjust the value as needed
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Image(
                            painter = rememberAsyncImagePainter("https:${data.current.condition.icon}"),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )

                        //please display the icon here
                    }
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    weatherData.value?.let { data ->
                        WeatherInfoBox(
                            title = "Wind",
                            value = "${data.current.wind_kph} km/h",
                            brush = WinterGradientBackground
                        )
                        WeatherInfoBox(
                            title = "Pressure",
                            value = "${data.current.pressure_mb} MB",
                            brush = WinterGradientBackground
                        )
                        WeatherInfoBox(
                            title = "Humidity",
                            value = "${data.current.humidity}%",
                            brush = WinterGradientBackground
                        )
                    } ?: run {
                        // Placeholder while loading
                        WeatherInfoBox(title = "Wind", value = "Loading...", brush = WinterGradientBackground)
                        WeatherInfoBox(title = "Pressure", value = "Loading...", brush = WinterGradientBackground)
                        WeatherInfoBox(title = "Humidity", value = "Loading...", brush = WinterGradientBackground)
                    }
                }
            }
        }
    }

}
