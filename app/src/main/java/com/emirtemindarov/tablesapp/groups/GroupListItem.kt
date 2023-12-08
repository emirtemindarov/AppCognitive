package com.emirtemindarov.tablesapp.groups

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.helpers.RawContextMenu
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

@Composable
fun GroupListItem(
    group: Group,
    groupsState: GroupsState,
    onEvent: (GroupEvent) -> Unit,
    applicationContext: Context
) {

    if (groupsState.isRenamingGroup) {
        RenameGroupDialog(
            groupId = group.id,
            groupsState = groupsState,
            onEvent = onEvent
        )
    }

    // Вызов и закрытие контекстного меню
    var expandedContextMenu by remember { mutableStateOf(false) }
    val expandContextMenu: () -> Unit = {
        expandedContextMenu = true
    }
    val collapse: () -> Unit = {
        expandedContextMenu = false
    }

    // Контекстное меню
    val rawContextMenu: @Composable (Group, (GroupEvent) -> Unit) -> Unit = { r_Group: Group, r_OnEvent: (GroupEvent) -> Unit ->
        RawContextMenu(
            expanded = expandedContextMenu,
            collapse = collapse,
            dropdownMenuItems = listOf(
                // TODO Перекидывать на первую вкладку для выбора одной задачи, нескольких задач или отмены выбора
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_announcement_24),
                                contentDescription = "Dropdown item"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Редактировать список")
                        }
                    },
                    action = {
                        Toast.makeText(
                            applicationContext,
                            "Cработало первое действие",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                ),
                // Редактирование группы (изменение названия группы)
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_announcement_24),
                                contentDescription = "Dropdown item"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Переименовать группу")
                        }
                    },
                    action = {
                        r_OnEvent(GroupEvent.ShowRenameDialog)
                    },
                    divider = true
                ),
                // Удаление группы (TODO с диалоговым окном подтверждения)
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete group"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Удалить группу", color = Color.Red)
                        }
                    },
                    action = {
                        Toast.makeText(
                            applicationContext,
                            "Группа удалена: ${r_Group.title}",
                            Toast.LENGTH_LONG
                        ).show()
                        r_OnEvent(GroupEvent.DeleteGroup(r_Group))
                    },
                    divider = true
                )
            )
        )
    }



    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            //.border(1.dp, Color.Blue)
            .clip(RoundedCornerShape(10.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    //onPress = { /* Called when the gesture starts */ },
                    //onDoubleTap = { /* Called on Double Tap */ },
                    onLongPress = { expandContextMenu.invoke() },
                    onTap = {
                        onEvent(GroupEvent.SetExpanded(!groupsState.expanded))
                    }
                )
            }
    ) {

    /*Column(
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = "${game.title} ${game.description}",
            fontSize = 20.sp
        )
        Text(text = game.difficulty, fontSize = 12.sp)
    }*/

    /*IconButton(onClick = {
        onEvent(GameEvent.DeleteGame(game))
    }) {
        Icon(Icons.Default.Delete, "Delete games")
    }*/

        if (expandedContextMenu) {
            rawContextMenu.invoke(group, onEvent)
        }

        Column(modifier = Modifier
            .fillMaxSize()
            //.border(2.dp, Color.Magenta)
            .background(color = parseColor(group.color))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = group.title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

fun parseColor(color: String): Color {
    return when(color) {
        "yellow" -> Color.Yellow
        "red" ->  Color.Red
        "green" ->  Color.Green
        "lightgray" ->  Color.LightGray
        "blue" ->  Color.Blue
        "cyan" ->  Color.Cyan
        "white" ->  Color.White
        "magenta" ->  Color.Magenta
        else -> {
            Log.i("Color", "Default color")
            Color.LightGray
        }
    }
}

/*enum class Difficulty(val text: String) {
    EASY("Упрощенная"),
    BASIC("Базовая"),
    HARD("Усложненная"),
    ADVANCED("Более сложная")
}*/

