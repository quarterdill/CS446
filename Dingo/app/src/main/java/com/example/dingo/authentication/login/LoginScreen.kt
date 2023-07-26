package com.example.dingo.authentication.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.dingo.CustomSwitch
import com.example.dingo.UIConstants.MEDIUM_PADDING
import com.example.dingo.common.composable.DisplayPasswordField
import com.example.dingo.common.composable.EmailField
import com.example.dingo.navigation.Screen
import com.example.dingo.ui.theme.Purple40
import com.example.dingo.ui.theme.Purple80
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        Image(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            imageVector = Icons.Rounded.AddCircle,
            contentDescription = "Placeholder"
        )
        CustomSwitch(
            "Standard",
            "Education",
            Modifier.padding(vertical = MEDIUM_PADDING),
        ) {}

        logInFields(navController = navController)
    }

}



@Composable
fun logInFields(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState
    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        EmailField(value = uiState.email, onNewValue = viewModel::onEmailChange)
        DisplayPasswordField(value = uiState.password, onNewValue = viewModel::onPasswordChange)
        Button(
            onClick = { coroutineScope.launch {
                Log.d("STATE", "in on click")
                viewModel.onSignInClick(navController)
            }},
            colors =
            ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Login", fontSize = 16.sp)
        }
        Row {
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen.SignUpScreen.route)
                },
                text = "No account? Sign Up",
                fontSize = 15.sp
            )
        }
    }


}
