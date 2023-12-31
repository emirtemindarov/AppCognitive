package com.emirtemindarov.tablesapp.groups

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
fun AddGroupDialog(
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onGroupEvent(GroupEvent.HideDialog)
        },
        title = { Text(text = "Новая группа") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = groupsState.title,
                    onValueChange = {
                        onGroupEvent(GroupEvent.SetTitle(it))
                    },
                    placeholder = {
                        Text(text = "Название")
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
                    onGroupEvent(GroupEvent.SaveGroup)
                }) {
                    Text(text = "Создать")
                }
            }
        }
    )
}