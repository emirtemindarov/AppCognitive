package com.emirtemindarov.tablesapp.bottom_nav_bar

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Screen1(sharedViewModel: BottomBarViewModel) {
    Log.i("screen_1_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("screen_1_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("screen_1_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("screen_1_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Экран1Прибавить")
        }
    }
}

@Composable
fun Screen2(sharedViewModel: BottomBarViewModel) {
    Log.i("screen_2_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("screen_2_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("screen_2_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("screen_2_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Экран2Прибавить")
        }
    }
}

@Composable
fun Screen3(sharedViewModel: BottomBarViewModel) {
    Log.i("screen_3_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("screen_3_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("screen_3_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("screen_3_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Экран3Прибавить")
        }
    }
}