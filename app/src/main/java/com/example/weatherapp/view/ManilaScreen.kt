package com.example.weatherapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.function.manila.ManilaCommentDialog
import com.example.weatherapp.function.manila.ManilaWeatherDetailsBox
import com.example.weatherapp.model.Comment
import com.example.weatherapp.viewModel.AuthViewModel
import com.example.weatherapp.viewModel.ManilaWeatherViewModel

@Composable
fun ManilaScreen(
    authViewModel: AuthViewModel = viewModel(),
    weatherViewModel: ManilaWeatherViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    // Collect the comments state as a compose state
    val comments by weatherViewModel.comments.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rainy),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 30.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                ManilaWeatherDetailsBox(viewModel = weatherViewModel)
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(comments) { comment ->
                            ManilaCommentItem(comment = comment)
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Button(
                            onClick = { showDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White.copy(alpha = 0.4f)
                            ),
                            shape = RectangleShape,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Comment")
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        val userName = authViewModel.getCurrentUserName() ?: "Anonymous"
        ManilaCommentDialog(
            viewModel = weatherViewModel,
            onDismiss = { showDialog = false },
            userName = userName
        )
    }
}

@Composable
fun ManilaCommentItem(comment: Comment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)) // Apply rounded corners
            .background(Color.White.copy(alpha = 0.6f)) // Set background color with transparency and rounded corners
            .padding(8.dp)
    ) {
        Text(text = comment.userName, style = MaterialTheme.typography.bodyLarge, color = Color.Black, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = comment.content, style = MaterialTheme.typography.bodyMedium, color = Color.Black, fontSize = 14.sp)
    }
}