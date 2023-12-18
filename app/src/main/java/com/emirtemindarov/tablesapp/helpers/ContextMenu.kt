package com.emirtemindarov.tablesapp.helpers

import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

@Composable
fun ContextMenu(
    expanded: Boolean,
    collapse: () -> Unit,
    dropdownMenuItems: List<ContextMenuItemContent>,
    offset: DpOffset = DpOffset.Zero
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = collapse,
        offset = offset
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