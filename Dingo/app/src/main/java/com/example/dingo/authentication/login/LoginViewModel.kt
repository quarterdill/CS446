package com.example.dingo.authentication.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.dingo.authentication.signup.SignUpUIState
import com.example.dingo.common.isValidEmail
import com.example.dingo.common.isValidPassword
import com.example.dingo.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val accountService: AccountService
) : ViewModel() {
    private val _uiState = mutableStateOf(LoginUIState())
    val uiState: State<LoginUIState> = _uiState

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        _uiState.value = _uiState.value.copy(password = newValue)
    }

    suspend fun onSignInClick() {
        Log.d("STATE", "In on click model")
        if (!email.isValidEmail()) {
            Log.d("STATE","Not a valid email")
            return
        }

//        if (!password.isValidPassword()) {
//            Log.d("STATE", password)
//            Log.d("STATE","Not a valid password")
//            return
//        }
        Log.d("STATE","trying to login")
        accountService.loginUser(email, password);
    }


}