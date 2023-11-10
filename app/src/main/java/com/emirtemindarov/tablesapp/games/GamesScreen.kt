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
    if(gamesState.isAddingGame) {
        AddGameDialog(gamesState = gamesState, onEvent = onEvent)
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
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
        }
        items(gamesState.gamesList) { game ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${game.title} ${game.description}",
                        fontSize = 20.sp
                    )
                    Text(text = game.difficulty, fontSize = 12.sp)
                }
                IconButton(onClick = {
                    onEvent(GameEvent.DeleteGame(game))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete games"
                    )
                }
            }
        }
    }

    Button(onClick = {
        onEvent(GameEvent.ShowDialog)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add contact"
        )
    }
}