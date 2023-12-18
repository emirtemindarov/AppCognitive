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
import androidx.compose.ui.unit.DpOffset
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

@Composable
fun RawContextMenu(
    expanded: Boolean,
    collapse: () -> Unit,
    offset: DpOffset = DpOffset.Zero,
    dropdownMenuItems: List<ContextMenuItemContent>,
) {
    ContextMenu(expanded, collapse, dropdownMenuItems, offset)
}