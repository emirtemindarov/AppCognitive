package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGameDialog(
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onGameEvent(GameEvent.HideDialog)
        },
        title = { Text(text = "Add game") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = gamesState.title,
                    onValueChange = {
                        onGameEvent(GameEvent.SetTitle(it))
                    },
                    placeholder = {
                        Text(text = "title field")
                    }
                )
                TextField(
                    value = gamesState.shortDescription,
                    onValueChange = {
                        onGameEvent(GameEvent.SetShortDescription(it))
                    },
                    placeholder = {
                        Text(text = "short description field")
                    }
                )
                TextField(
                    value = gamesState.description,
                    onValueChange = {
                        onGameEvent(GameEvent.SetDescription(it))
                    },
                    placeholder = {
                        Text(text = "description field")
                    }
                )
                TextField(
                    value = gamesState.difficulty,
                    onValueChange = {
                        onGameEvent(GameEvent.SetDifficulty(it))
                    },
                    placeholder = {
                        Text(text = "difficulty field")
                    }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onGameEvent(GameEvent.SaveGame)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}