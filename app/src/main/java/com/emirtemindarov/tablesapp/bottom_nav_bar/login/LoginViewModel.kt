package com.emirtemindarov.tablesapp.bottom_nav_bar.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    /*// внутренняя переменная ViewModel
    var viewModelCounter by mutableStateOf(0)
        private set*/

    /*init {

    }*/

    fun increase() {
        //viewModelCounter++
        Log.i("before_state", "${_loginState.value.counter}")
        _loginState.update { currentState ->
            currentState.copy(
                counter = currentState.counter + 1
            )
        }
        Log.i("after_state", "${_loginState.value.counter}")
    }
}