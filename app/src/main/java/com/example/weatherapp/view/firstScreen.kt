package com.example.weatherapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.function.firstScreen.AuthTextFields
import com.example.weatherapp.function.firstScreen.CreateAccountDialog
import com.example.weatherapp.ui.theme.WinterGradientBackground
import com.example.weatherapp.viewModel.AuthViewModel

@Composable
fun FirstScreen(onAuthenticated: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val authViewModel: AuthViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WinterGradientBackground)
    )

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
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = {
                authViewModel.signIn(username, password) { success, message ->
                    if (success) {
                        onAuthenticated()
                    } else {
                        errorMessage = message
                    }
                }
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
        CreateAccountDialog(onDismiss = { showDialog = false }, authViewModel = authViewModel)
    }
}