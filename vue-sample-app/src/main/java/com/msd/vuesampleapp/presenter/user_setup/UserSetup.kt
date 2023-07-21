package com.msd.vuesampleapp.presenter.user_setup

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msd.vuesampleapp.presenter.util.Screen
import kotlinx.coroutines.launch

@Composable
fun UserSetupView(navController: NavController) {
    var msdBaseUrl by remember { mutableStateOf("") }
    var msdToken by remember { mutableStateOf("") }

    val tokenFocusRequester = remember { FocusRequester() }

    val localContext = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    fun handleContinuePress() {
        if (msdBaseUrl.isNotEmpty() && msdToken.isNotEmpty()) {
            com.msd.vuesampleapp.data.controller.VueSDKController.initVueSDK(localContext, msdToken, msdBaseUrl)
            navController.navigate(Screen.SelectUserScreen.Route)
        } else {
            scope.launch {
                snackbarHostState.showSnackbar("Enter base url and token to continue")
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, content = { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(padding)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MSD SDK Sample App",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = msdBaseUrl,
                onValueChange = { msdBaseUrl = it },
                label = { Text("Base url") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = msdToken,
                onValueChange = { msdToken = it },
                label = { Text("Token") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(tokenFocusRequester)
                    .focusTarget()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { handleContinuePress() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
        }
    })

}
