package com.emirtemindarov.tablesapp.logic.scaffold

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.emirtemindarov.tablesapp.logic.login.UserData
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emirtemindarov.tablesapp.R
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
    mainNavController: NavHostController
    //sharedViewModel: ScaffoldViewModel
) {
    GamesScreen(
        gamesState = gamesState,
        onGameEvent = onGameEvent,
        groupsState = groupsState,
        onGroupEvent = onGroupEvent,
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