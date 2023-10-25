package com.emirtemindarov.tablesapp.bottom_nav_bar

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun BottomBarNavGraph(
    bottomBarNavController: NavHostController
) {
    NavHost(navController = bottomBarNavController, startDestination = "bottom_bar") {
        navigation(
            startDestination = "screen_1",
            route = "bottom_bar"
        ) {
            composable("screen_1") { entry ->
                val sharedViewModel = entry.getSharedViewModel<BottomBarViewModel>(bottomBarNavController)
                Log.i("bbnc_screen1", "${sharedViewModel}")
                Screen1(sharedViewModel)
            }
            composable("screen_2") { entry ->
                val sharedViewModel = entry.getSharedViewModel<BottomBarViewModel>(bottomBarNavController)
                Log.i("bbnc_screen2", "${sharedViewModel}")
                Screen2(sharedViewModel)
            }
            composable("screen_3") { entry ->
                val sharedViewModel = entry.getSharedViewModel<BottomBarViewModel>(bottomBarNavController)
                Log.i("bbnc_screen3", "${sharedViewModel}")
                Screen3(sharedViewModel)
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.getSharedViewModel(navController: NavController): T {
    Log.i("navGraphRoute", destination.parent?.route ?: "first screen")
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}