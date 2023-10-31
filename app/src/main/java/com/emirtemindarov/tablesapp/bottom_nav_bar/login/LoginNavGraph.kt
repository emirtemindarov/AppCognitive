package com.emirtemindarov.tablesapp.bottom_nav_bar.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LoginNavGraph(loginNavController: NavHostController) {
    NavHost(navController = loginNavController, startDestination = "login") {
        composable("login") { entry ->
            val sharedViewModel = entry.getSharedViewModel<LoginViewModel>(loginNavController)
            Log.i("anc_Tab1", "${sharedViewModel}")
            Tab1(sharedViewModel)
        }
        composable("registration") { entry ->
            val sharedViewModel = entry.getSharedViewModel<LoginViewModel>(loginNavController)
            Log.i("anc_Tab2", "${sharedViewModel}")
            Tab2(sharedViewModel)
        }
        composable("change_password") { entry ->
            val sharedViewModel = entry.getSharedViewModel<LoginViewModel>(loginNavController)
            Log.i("anc_Tab3", "${sharedViewModel}")
            Tab3(sharedViewModel)
        }
    }
}
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.getSharedViewModel(navController: NavController): T {
    Log.i("navGraphRoute", destination.parent?.route ?: "first Tab")
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}