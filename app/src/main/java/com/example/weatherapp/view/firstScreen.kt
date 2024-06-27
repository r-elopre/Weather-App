package com.example.weatherapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.functions.firstScreen.AuthTextFields
import com.example.weatherapp.functions.firstScreen.CreateAccountDialog

@Composable
fun FirstScreen(onAuthenticated: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTextFields(
            username = username,
            onUsernameChange = { username = it },
            password = password,
            onPasswordChange = { password = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Simulate authentication
                onAuthenticated()
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text("Sign In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text("Create Account")
        }
    }

    if (showDialog) {
        CreateAccountDialog(onDismiss = { showDialog = false })
    }
}
