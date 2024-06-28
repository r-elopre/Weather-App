package com.example.weatherapp.function.manila

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.weatherapp.model.Comment
import com.example.weatherapp.viewModel.ManilaWeatherViewModel
import java.util.*

@Composable
fun ManilaCommentDialog(
    viewModel: ManilaWeatherViewModel,
    onDismiss: () -> Unit,
    userName: String
) {
    var commentText by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Enter Comment", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    label = { Text("Comment") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancel")
                    }
                    TextButton(onClick = {
                        val comment = Comment(
                            id = UUID.randomUUID().toString(),
                            userId = "user-id", // Replace with actual user ID if needed
                            userName = userName,
                            content = commentText,
                            timestamp = System.currentTimeMillis()
                        )
                        viewModel.postComment(comment)
                        onDismiss()
                    }) {
                        Text("Post")
                    }
                }
            }
        }
    }
}