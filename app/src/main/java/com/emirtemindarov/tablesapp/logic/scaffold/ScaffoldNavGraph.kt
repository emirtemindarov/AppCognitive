package com.emirtemindarov.tablesapp.logic.scaffold

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.helpers.getSharedViewModel
import com.emirtemindarov.tablesapp.logic.login.UserData

@Composable
fun ScaffoldNavGraph(
    userData: UserData?,
    onSignOut: () -> Unit,
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
    scaffoldNavController: NavHostController,
    mainNavController: NavHostController
) {
    NavHost(navController = scaffoldNavController, startDestination = "tabs") {
        navigation(route = "tabs", startDestination = "tab_1") {
            composable("tab_1") { entry ->
                val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab1", "${sharedViewModel}")
                Tab1(
                    gamesState,
                    onEvent,
                    mainNavController
                )
            }
            composable("tab_2") { entry ->
                val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab2", "${sharedViewModel}")
                Tab2(sharedViewModel)
            }
            composable("tab_3") { entry ->
                val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab3", "${sharedViewModel}")
                Tab3(userData, onSignOut, sharedViewModel)
            }
        }
    }
}