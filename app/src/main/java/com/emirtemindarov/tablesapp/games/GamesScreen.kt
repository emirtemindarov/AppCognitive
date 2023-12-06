package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController

@Composable
fun GamesScreen(
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
    mainNavController: NavHostController
) {
    if (gamesState.isAddingGame) {
        AddGameDialog(gamesState = gamesState, onEvent = onEvent)
    }

    Column {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.weight(0.9f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            /*item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = CenterVertically
                ) {
                    GamesSortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(GameEvent.SortGames(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = gamesState.sortType == sortType,
                                onClick = {
                                    onEvent(GameEvent.SortGames(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }*/

            items(gamesState.gamesList) { game ->
                GamesListItem(
                    game,
                    gamesState,
                    onEvent
                )
            }
        }

        // (для отладки) Доп. панель для переходов над scaffold-кнопками
        if (true) {
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .padding(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = {
                        onEvent(GameEvent.ShowDialog)
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


