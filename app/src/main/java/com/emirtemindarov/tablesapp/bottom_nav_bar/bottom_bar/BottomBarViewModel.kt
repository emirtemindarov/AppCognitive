package com.emirtemindarov.tablesapp.bottom_nav_bar.bottom_bar

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BottomBarViewModel : ViewModel() {
    private val _bottomBarState = MutableStateFlow(BottomBarState())
    val bottomBarState: StateFlow<BottomBarState> = _bottomBarState.asStateFlow()

    /*// внутренняя переменная ViewModel
    var viewModelCounter by mutableStateOf(0)
        private set*/

    /*init {

    }*/

    fun increase() {
        //viewModelCounter++
        Log.i("before_state", "${_bottomBarState.value.counter}")
        _bottomBarState.update { currentState ->
            currentState.copy(
                counter = currentState.counter + 1
            )
        }
        Log.i("after_state", "${_bottomBarState.value.counter}")
    }
}