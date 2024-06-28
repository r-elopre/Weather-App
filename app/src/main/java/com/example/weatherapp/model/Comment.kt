package com.example.weatherapp.model

data class Comment(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
