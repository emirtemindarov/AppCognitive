package com.emirtemindarov.tablesapp.groups

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emirtemindarov.tablesapp.crossref.AddGameToGroupsDialog
import com.emirtemindarov.tablesapp.crossref.CrossRefEvent
import com.emirtemindarov.tablesapp.crossref.CrossRefsState
import com.emirtemindarov.tablesapp.crossref.IncludeGamesIntoGroupDialog
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesState

@Composable
fun GroupsScreen(
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    applicationContext: Context,
    mainNavController: NavHostController
) {
    if (groupsState.isAddingGroup) {
        AddGroupDialog(
            groupsState = groupsState,
            onGroupEvent = onGroupEvent
        )
    }

    if (groupsState.isSortingGroups) {
        SortGroupsDialog(
            groupsState = groupsState,
            onEvent = onGroupEvent
        )
    }

    if (groupsState.isRenamingGroup) {
        RenameGroupDialog(
            groupId = groupsState.currentGroupId,
            groupsState = groupsState,
            onEvent = onGroupEvent
        )
    }

    if (crossRefsState.isAddingCrossRef) {
        IncludeGamesIntoGroupDialog(
            groupId = crossRefsState.groupId,
            gamesState = gamesState,
            onGameEvent = onGameEvent,
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

        // Кнопка добавления группы
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                onGroupEvent(GroupEvent.ShowDialog)
            }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Add, "Add group")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Создать группу")
                }
            }
        }

        LazyColumn(
            //state = listState,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                //.border(2.dp, Color.Red)
                .weight(0.9f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(groupsState.groupsList) { group ->
                Log.i("GROUP DETAILS", "$group")
                GroupListItem(
                    group,
                    groupsState,
                    onGroupEvent,
                    gamesState,
                    onGameEvent,
                    crossRefsState,
                    onCrossRefEvent,
                    applicationContext
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
                        onGroupEvent(GroupEvent.ShowDialog)
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


