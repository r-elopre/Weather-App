package com.example.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Comment
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.RetrofitInstance
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RizalWeatherViewModel : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments

    private val database = FirebaseDatabase.getInstance().getReference("comments")

    init {
        getWeatherData()
        fetchComments()
    }

    private fun getWeatherData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCurrentWeather(
                    apiKey = "put your api here",
                    location = "Rodriguez Rizal"
                )
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchComments() {
        viewModelScope.launch {
            try {
                val commentsSnapshot = database.get().await()
                val commentsList = commentsSnapshot.children.mapNotNull { it.getValue(Comment::class.java) }
                _comments.value = commentsList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postComment(comment: Comment) {
        viewModelScope.launch {
            try {
                database.push().setValue(comment).await()
                fetchComments() // Refresh the comments list
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
