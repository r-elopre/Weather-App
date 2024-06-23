package com.example.weatherapp.model

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime: String
)

data class Current(
    val last_updated: String,
    val temp_c: Double,
    val condition: Condition,
    val wind_kph: Double,
    val pressure_mb: Double,
    val humidity: Int
)

data class Condition(
    val text: String,
    val icon: String
)
