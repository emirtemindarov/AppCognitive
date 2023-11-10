package com.emirtemindarov.tablesapp.logic

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
import com.emirtemindarov.tablesapp.logic.login.LoginScreen
import com.emirtemindarov.tablesapp.logic.scaffold.ScaffoldScreen

@Composable
fun MainNavGraph(
    // ни gamesViewState, ни gameState, и все что связано с игрой не должны здесь быть (должно создаваться после авторизации и брать данные из firebase)
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
    // должно здесь быть
    mainNavController: NavHostController = rememberNavController(),
    ) {
    NavHost(navController = mainNavController, startDestination = "parts") {
        navigation(route = "parts", startDestination = "room_test") {
            /*
                SCAFFOLD
             */
            composable(route = "bottom_bar") {
                ScaffoldScreen(gamesState, onEvent, mainNavController)
            }
            /*
                LOGIN
            */
            composable(route = "auth") {
                LoginScreen(mainNavController)
            }
            /*
                ROOM TEST
            */
            composable(route = "room_test") {
                GamesScreen(gamesState, onEvent, mainNavController)
            }
        }
    }
}

