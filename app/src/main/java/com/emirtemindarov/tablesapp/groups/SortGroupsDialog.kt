package com.emirtemindarov.tablesapp.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesSortType

@Composable
fun SortGroupsDialog(
    groupsState: GroupsState,
    onEvent: (GroupEvent) -> Unit,
) {
    var choice by remember { mutableStateOf(groupsState.sortType) }

    AlertDialog(
        onDismissRequest = {
            onEvent(GroupEvent.HideSortDialog)
        },
        title = { /*Text(text = "Сортировать:")*/ },
        text = {
            // метод сортировки
            Column {
                GroupsSortType.values().forEach { sortType ->
                    Row(
                        verticalAlignment = CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                choice = sortType
                            }
                    ) {
                        RadioButton(
                            selected = choice == sortType,
                            onClick = {
                                choice = sortType
                            }
                        )
                        Text(
                            text = when (sortType) {
                                GroupsSortType.DEFAULT -> "По умолчанию"
                                GroupsSortType.TITLE_ASC -> "А-Я"
                                GroupsSortType.TITLE_DESC -> "Я-А"
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(GroupEvent.SortGroups(choice))
                    onEvent(GroupEvent.HideSortDialog)
                }) {
                    Text("Закрыть")
                }
            }
        }
    )
}
