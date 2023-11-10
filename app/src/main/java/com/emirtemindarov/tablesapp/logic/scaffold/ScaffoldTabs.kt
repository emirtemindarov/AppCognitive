package com.emirtemindarov.tablesapp.logic.scaffold

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun Tab1(sharedViewModel: ScaffoldViewModel) {
    Log.i("tab_1_button_before", "${sharedViewModel.scaffoldState}")
    val collectedState by sharedViewModel.scaffoldState.collectAsStateWithLifecycle()
    Log.i("tab_1_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_1_button_after", "${sharedViewModel.scaffoldState.value.counter}")
            Log.i("tab_1_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка1Прибавить")
        }
    }
}

@Composable
fun Tab2(sharedViewModel: ScaffoldViewModel) {
    Log.i("tab_2_button_before", "${sharedViewModel.scaffoldState}")
    val collectedState by sharedViewModel.scaffoldState.collectAsStateWithLifecycle()
    Log.i("tab_2_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_2_button_after", "${sharedViewModel.scaffoldState.value.counter}")
            Log.i("tab_2_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка2Прибавить")
        }
    }
}

@Composable
fun Tab3(sharedViewModel: ScaffoldViewModel) {
    Log.i("tab_3_button_before", "${sharedViewModel.scaffoldState}")
    val collectedState by sharedViewModel.scaffoldState.collectAsStateWithLifecycle()
    Log.i("tab_3_button_before_counter", "${collectedState.counter}")
    Column {
        key(collectedState.counter) {
            Text(text = "${collectedState.counter}")
        }
        Button(onClick = {
            sharedViewModel.increase()
            Log.i("tab_3_button_after", "${sharedViewModel.scaffoldState.value.counter}")
            Log.i("tab_3_button_after_counter", "${collectedState.counter}")
        }) {
            Text(text = "Вкладка3Прибавить")
        }
    }
}