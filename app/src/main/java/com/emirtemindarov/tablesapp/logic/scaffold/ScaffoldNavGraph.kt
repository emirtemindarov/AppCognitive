package com.emirtemindarov.tablesapp.logic.scaffold

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.emirtemindarov.tablesapp.crossref.CrossRefEvent
import com.emirtemindarov.tablesapp.crossref.CrossRefsState
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.groups.GroupEvent
import com.emirtemindarov.tablesapp.groups.GroupsState
import com.emirtemindarov.tablesapp.helpers.getSharedViewModel
import com.emirtemindarov.tablesapp.logic.login.UserData

@Composable
fun ScaffoldNavGraph(
    userData: UserData?,
    onSignOut: () -> Unit,
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    scaffoldNavController: NavHostController,
    applicationContext: Context,
    mainNavController: NavHostController
) {
    NavHost(navController = scaffoldNavController, startDestination = "tabs") {
        navigation(route = "tabs", startDestination = "tab_1") {
            composable("tab_1") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab1", "$sharedViewModel")*/
                Tab1(
                    gamesState,
                    onGameEvent,
                    groupsState,
                    onGroupEvent,
                    crossRefsState,
                    onCrossRefEvent,
                    mainNavController
                    //sharedViewModel
                )
            }
            composable("tab_2") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab2", "$sharedViewModel")*/
                Tab2(
                    gamesState,
                    onGameEvent,
                    groupsState,
                    onGroupEvent,
                    crossRefsState,
                    onCrossRefEvent,
                    applicationContext,
                    mainNavController,
                    //sharedViewModel
                )
            }
            composable("tab_3") { entry ->
                /*val sharedViewModel = entry.getSharedViewModel<ScaffoldViewModel>(scaffoldNavController)
                Log.i("snc_Tab3", "$sharedViewModel")*/
                Tab3(
                    userData,
                    onSignOut,
                    //sharedViewModel
                )
            }
        }
    }
}