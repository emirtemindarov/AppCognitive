package com.emirtemindarov.tablesapp.helpers

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

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
                onClick = {
                    collapse.invoke()
                    action.invoke()
                }
            )
        }
    }
}