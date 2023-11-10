package com.emirtemindarov.tablesapp.logic.scaffold

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScaffoldViewModel : ViewModel() {
    private val _scaffoldState = MutableStateFlow(ScaffoldState())
    val scaffoldState: StateFlow<ScaffoldState> = _scaffoldState.asStateFlow()

    /*// внутренняя переменная ViewModel
    var viewModelCounter by mutableStateOf(0)
        private set*/

    /*init {

    }*/

    fun increase() {
        //viewModelCounter++
        Log.i("before_state", "${_scaffoldState.value.counter}")
        _scaffoldState.update { currentState ->
            currentState.copy(
                counter = currentState.counter + 1
            )
        }
        Log.i("after_state", "${_scaffoldState.value.counter}")
    }
}