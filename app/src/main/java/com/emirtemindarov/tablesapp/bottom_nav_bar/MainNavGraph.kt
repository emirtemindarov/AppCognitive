package com.emirtemindarov.tablesapp.bottom_nav_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.bottom_nav_bar.login.LoginScreen
import com.emirtemindarov.tablesapp.bottom_nav_bar.scaffold.ScaffoldScreen

@Composable
fun MainNavGraph(
    mainNavController: NavHostController = rememberNavController()
) {
    NavHost(navController = mainNavController, startDestination = "parts") {
        navigation(route = "parts", startDestination = "auth") {
            /*
                SCAFFOLD
             */
            composable(route = "bottom_bar") {
                ScaffoldScreen(mainNavController)
            }
            /*
                LOGIN
            */
            composable(route = "auth") {
                LoginScreen(mainNavController)
            }
        }
    }
}

