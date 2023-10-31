package com.emirtemindarov.tablesapp.bottom_nav_bar.bottom_bar

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Tab1(sharedViewModel: BottomBarViewModel) {
    Log.i("tab_1_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("tab_1_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_1_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("tab_1_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка1Прибавить")
        }
    }
}

@Composable
fun Tab2(sharedViewModel: BottomBarViewModel) {
    Log.i("tab_2_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("tab_2_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_2_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("tab_2_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка2Прибавить")
        }
    }
}

@Composable
fun Tab3(sharedViewModel: BottomBarViewModel) {
    Log.i("tab_3_button_before", "${sharedViewModel.bottomBarState}")
    val collectedState by sharedViewModel.bottomBarState.collectAsStateWithLifecycle()
    Log.i("tab_3_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_3_button_after", "${sharedViewModel.bottomBarState.value.counter}")
            Log.i("tab_3_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка3Прибавить")
        }
    }
}