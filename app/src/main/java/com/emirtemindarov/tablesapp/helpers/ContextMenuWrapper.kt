package com.emirtemindarov.tablesapp.helpers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

@Composable
fun ContextMenuWrapper(
    dropdownMenuItems: List<ContextMenuItemContent>,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val collapse = {
        expanded = false
    }

    Box {
        IconButton(
            onClick = { expanded = true },
            content = content
        )
        ContextMenu(expanded, collapse, dropdownMenuItems)
    }
}

@Composable
fun ContextMenu(
    expanded: Boolean,
    collapse: () -> Unit,
    dropdownMenuItems: List<ContextMenuItemContent>
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = collapse
    ) {
        dropdownMenuItems.forEach { (item, action, divider) ->
            divider?.let {
                Divider()
            }
            DropdownMenuItem(
                text = item,
                onClick = action
            )
        }
    }
}