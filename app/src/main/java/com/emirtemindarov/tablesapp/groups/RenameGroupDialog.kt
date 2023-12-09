package com.emirtemindarov.tablesapp.groups

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenameGroupDialog(
    groupId: Int,
    groupsState: GroupsState,
    onEvent: (GroupEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // для вставки изначального значения в поле для изменения
    LaunchedEffect(key1 = Unit) {
        Log.i("GROUP ENTER DIALOG", "$groupId")
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(GroupEvent.HideRenameDialog)
        },
        title = { Text(text = "Заголовок группы") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = groupsState.title,
                    onValueChange = {
                        onEvent(GroupEvent.SetTitle(it))
                    },
                    /*placeholder = {
                        Text(text = groupsState.title)
                    }*/
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    Log.i("GROUP CLICK RENAME", "$groupId")
                    onEvent(GroupEvent.RenameGroup(groupId, groupsState.title))
                }) {
                    Text(text = "Сохранить")
                }
            }
        }
    )
}