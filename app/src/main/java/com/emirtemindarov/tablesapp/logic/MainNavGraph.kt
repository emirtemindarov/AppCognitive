package com.emirtemindarov.tablesapp.logic

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesScreen
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.games.GamesViewModel
import com.emirtemindarov.tablesapp.logic.login.GoogleAuthUiClient
import com.emirtemindarov.tablesapp.logic.login.LoginNavGraph
import com.emirtemindarov.tablesapp.logic.scaffold.ScaffoldScreen
import com.google.firebase.database.DatabaseReference

@Composable
fun MainNavGraph(
    usersRef: DatabaseReference,
    applicationContext: Context,
    googleAuthUiClient: GoogleAuthUiClient,
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
    mainNavController: NavHostController = rememberNavController()
) {
    NavHost(navController = mainNavController, startDestination = "parts") {
        navigation(route = "parts", startDestination = "auth") {
            /*
                SCAFFOLD
             */
            composable(route = "bottom_bar") {
                ScaffoldScreen(
                    applicationContext,
                    googleAuthUiClient,
                    usersRef,
                    gamesState,
                    onEvent,
                    mainNavController
                )
            }
            /*
                LOGIN
            */
            composable(route = "auth") {
                LoginNavGraph(
                    applicationContext,
                    googleAuthUiClient,
                    mainNavController
                )
            }
            /*
                ROOM TEST
            */
            composable(route = "room_test") {
                GamesScreen(
                    gamesState,
                    onEvent,
                    mainNavController
                )
            }
        }
    }
}

