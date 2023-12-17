package com.emirtemindarov.tablesapp.helpers

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.emirtemindarov.tablesapp.groups.GroupEvent
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

// TODO переделать
@Composable
fun LongPressContextMenuWrapper(
    dropdownMenuItems: List<ContextMenuItemContent>,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val collapse = {
        expanded = false
    }

    Box(modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onLongPress = {
                expanded = true
            }
        )}
    ) {

        content.invoke()

        if (expanded) {
            ContextMenu(expanded, collapse, dropdownMenuItems)
        }
    }
}