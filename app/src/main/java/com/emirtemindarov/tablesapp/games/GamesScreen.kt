package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.emirtemindarov.tablesapp.crossref.AddGameToGroupsDialog
import com.emirtemindarov.tablesapp.crossref.CrossRefEvent
import com.emirtemindarov.tablesapp.crossref.CrossRefsState
import com.emirtemindarov.tablesapp.groups.GroupEvent
import com.emirtemindarov.tablesapp.groups.GroupsState
import com.emirtemindarov.tablesapp.helpers.ContextMenuWrapper
import com.emirtemindarov.tablesapp.helpers.LongPressContextMenuWrapper
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.truncate

@Composable
fun GamesScreen(
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    mainNavController: NavHostController
) {
    // (Для отладки) Добавить игру
    if (gamesState.isAddingGame) {
        AddGameDialog(
            gamesState = gamesState,
            onGameEvent = onGameEvent
        )
    }

    if (crossRefsState.isAddingCrossRef) {
        AddGameToGroupsDialog(
            gameId = crossRefsState.gameId,
            groupsState = groupsState,
            onGroupEvent = onGroupEvent,
            crossRefsState = crossRefsState,
            onCrossRefEvent = onCrossRefEvent
        )
    }

    // Основа
    Column {

        /*
        var sliderPosition by remember { mutableStateOf(0f) }
        val listState = rememberLazyListState()

        LaunchedEffect(key1 = sliderPosition) {
            val position: Int = truncate(sliderPosition).toInt()
            val offset: Float = sliderPosition - position
            CoroutineScope(Dispatchers.Main).launch {
                listState.scrollToItem(position, offset)
            }
        }
        */

        LazyColumn(
            //state = listState,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                //.border(2.dp, Color.Red)
                .weight(0.9f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(gamesState.gamesList) { game ->
                GamesListItem(
                    game,
                    gamesState,
                    onGameEvent,
                    onCrossRefEvent
                )
            }
        }

        /*Column {
            VerticalSlider(
                sliderPosition = sliderPosition,
                maxRange = gamesState.gamesList.size.toFloat()
            ) {
                sliderPosition = it
            }
        }*/








        // (для отладки) Доп. панель для переходов над scaffold-кнопками
        if (false) {
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = {
                        onGameEvent(GameEvent.ShowDialog)
                    }) {
                        Icon(Icons.Default.Add, "Add contact")
                    }

                    Button(onClick = {
                        mainNavController.navigate("auth") {
                            popUpTo("room_test") {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(Icons.Default.Lock, "Login")
                    }

                    Button(onClick = {
                        mainNavController.navigate("bottom_bar") {
                            popUpTo("room_test") {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(Icons.Default.List, "Scaffold")
                    }
                }
            }
        }





    }
}


