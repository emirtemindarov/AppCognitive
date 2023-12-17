package com.emirtemindarov.tablesapp.logic.scaffold

import android.content.Context
import com.emirtemindarov.tablesapp.logic.login.UserData
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.emirtemindarov.tablesapp.crossref.CrossRefEvent
import com.emirtemindarov.tablesapp.crossref.CrossRefsState
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesScreen
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.groups.GroupEvent
import com.emirtemindarov.tablesapp.groups.GroupsScreen
import com.emirtemindarov.tablesapp.groups.GroupsState
import com.emirtemindarov.tablesapp.logic.account.AccountScreen

@Composable
fun Tab1(
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    mainNavController: NavHostController
    //sharedViewModel: ScaffoldViewModel
) {
    GamesScreen(
        gamesState = gamesState,
        onGameEvent = onGameEvent,
        groupsState = groupsState,
        onGroupEvent = onGroupEvent,
        crossRefsState = crossRefsState,
        onCrossRefEvent = onCrossRefEvent,
        mainNavController = mainNavController
        //sharedViewModel: ScaffoldViewModel
    )
}

@Composable
fun Tab2(
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    applicationContext: Context,
    mainNavController: NavHostController
    //sharedViewModel: ScaffoldViewModel
) {
    /*Log.i("tab_2_button_before", "${sharedViewModel.scaffoldState}")
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
    }*/

    GroupsScreen(
        gamesState = gamesState,
        onGameEvent = onGameEvent,
        groupsState = groupsState,
        onGroupEvent = onGroupEvent,
        crossRefsState = crossRefsState,
        onCrossRefEvent = onCrossRefEvent,
        applicationContext = applicationContext,
        mainNavController = mainNavController
        //sharedViewModel: ScaffoldViewModel
    )
}

@Composable
fun Tab3(
    userData: UserData?,
    onSignOut: () -> Unit,
    //sharedViewModel: ScaffoldViewModel
) {
    //Log.i("tab_3_button_before", "${sharedViewModel.scaffoldState}")
    AccountScreen(
        userData = userData,
        onSignOut = onSignOut,
        //sharedViewModel
    )
}