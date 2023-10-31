package com.emirtemindarov.tablesapp.bottom_nav_bar

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emirtemindarov.tablesapp.bottom_nav_bar.bottom_bar.BottomBarScreen
import com.emirtemindarov.tablesapp.bottom_nav_bar.login.LoginScreen

@Composable
fun MainNavGraph(
    mainNavController: NavHostController = rememberNavController()
) {
    NavHost(navController = mainNavController, startDestination = "auth") {
        Log.i("d", "d here")
        /*
            SCAFFOLD
         */
        composable(route = "bottom_bar") {
            Log.i("f", "f here")
            BottomBarScreen(mainNavController)
        }
        
        /*
            LOGIN
        */
        composable(route = "auth") {
            Log.i("g", "g here")
            LoginScreen(mainNavController)
        }
    }
}

