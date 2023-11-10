package com.emirtemindarov.tablesapp.logic.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.logic.getSharedViewModel

@Composable
fun LoginNavGraph(loginNavController: NavHostController) {
    NavHost(navController = loginNavController, startDestination = "login_nav") {
        navigation(route = "login_nav", startDestination = "login") {
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
}