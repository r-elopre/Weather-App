package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RizalWeatherViewModel : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    init {
        getWeatherData()
    }

    private fun getWeatherData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCurrentWeather(
                    apiKey = "7f8b1a2307fb42fa8c2155134242206",
                    location = "Rodriguez Rizal"
                )
                _weatherData.value = response
                Log.d("WeatherResponse", "Rodriguez Rizal: $response")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("WeatherResponseError", "Failed to fetch weather data for Rodriguez Rizal", e)
            }
        }
    }
}
