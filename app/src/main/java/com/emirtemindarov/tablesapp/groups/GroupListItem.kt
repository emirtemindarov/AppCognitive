package com.emirtemindarov.tablesapp.groups

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

    // Вызов и закрытие контекстного меню щаголовка группы
    var expandedContextMenu by remember { mutableStateOf(false) }
    val expandContextMenu: () -> Unit = {
        expandedContextMenu = true
    }
    val collapse: () -> Unit = {
        expandedContextMenu = false
    }

    // Контекстное меню заголовка группы
    val groupTitleRawContextMenu: @Composable (Group, (GroupEvent) -> Unit) -> Unit = { r_Group: Group, r_OnEvent: (GroupEvent) -> Unit ->
        RawContextMenu(
            expanded = expandedContextMenu,
            collapse = collapse,
            dropdownMenuItems = listOf(

                /*// TODO Перекидывать на первую вкладку для выбора одной задачи, нескольких задач или отмены выбора
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
                ),*/

                // Редактирование группы (изменение названия группы)
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Rename group"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Переименовать группу")
                        }
                    },
                    action = {
                        r_OnEvent(GroupEvent.ShowRenameDialog)
                    }
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
                                contentDescription = "Delete group",
                                tint = Color.Red
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

    // Вызов и закрытие контекстного меню ссылки на задачу
    var expandedGameRefContextMenu by remember { mutableStateOf(false) }
    val expandGameRefContextMenu: () -> Unit = {
        expandedGameRefContextMenu = true
    }
    val collapseGameRefContextMenu: () -> Unit = {
        expandedGameRefContextMenu = false
    }

    // Контекстное меню ссылки на задачу
    val gameRefRawContextMenu: @Composable (Group, (GroupEvent) -> Unit) -> Unit = { r_Group: Group, r_OnEvent: (GroupEvent) -> Unit ->
        RawContextMenu(
            expanded = expandedGameRefContextMenu,
            collapse = collapseGameRefContextMenu,
            dropdownMenuItems = listOf(

                // Удаление группы (TODO с диалоговым окном подтверждения)
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete game ref",
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Удалить из группы", color = Color.Red)
                        }
                    },
                    action = {
                        Toast.makeText(
                            applicationContext,
                            "Задача {r_Game.title} удалена из группы ${r_Group.title}",
                            Toast.LENGTH_LONG
                        ).show()
                        // TODO r_OnCrossRefEvent(GroupEvent.DeleteRef(r_Game))
                    }
                )

            )
        )
    }



    // Цвет контейнера группы
    val containerColorPale = parseColor(group.color).copy(0.1f)

    // Рамка группы
    Column (
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(15.dp))
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
            groupTitleRawContextMenu.invoke(group, onEvent)
        }

        if (expandedGameRefContextMenu) {
            gameRefRawContextMenu.invoke(group, onEvent)
        }

        // Контейнер заголовка и ссылок на задачи
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp, 1000.dp)
                //.border(2.dp, Color.Black)
        ) {

            // Заголовок с названием группы и кнопкой
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    //.border(2.dp, Color.Black)
                    .background(color = containerColorPale.copy(containerColorPale.alpha.plus(0.1f)))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            //onPress = { /* Called when the gesture starts */ },
                            onTap = { onEvent(GroupEvent.UpdateExpanded(group.id)) },
                            //onDoubleTap = { /* Called on Double Tap */ },
                            onLongPress = { expandContextMenu.invoke() },
                        )
                    }
            ) {
                // Заголовок группы
                Text(
                    text = group.title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        //.border(2.dp, Color.Black)
                )
                // Иконка свернуть/развернуть
                Box(
                    modifier = Modifier
                        //.border(2.dp, Color.Black)
                        .padding(end = 24.dp)
                ) {
                    if (group.expanded) {
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = "Список развернут",
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = "Список свернут",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            // TODO 1) crossref    2) items(group.gamesId) -> {
            // Блок ссылки на задачу
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    //.border(2.dp, Color.Black)
                    .background(color = containerColorPale)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            //onPress = { /* Called when the gesture starts */ },
                            //onTap = { onCrossRefEvent(GroupEvent.NavigateAndOpen(gameRef.id)) },
                            //onDoubleTap = { /* Called on Double Tap */ },
                            onLongPress = { expandGameRefContextMenu.invoke() },
                        )
                    }
            ) {
                // Заголовок группы
                Text(
                    text = group.title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 38.dp)
                        //.border(2.dp, Color.Black)
                )
                // Иконка свернуть/развернуть
                Box(
                    modifier = Modifier
                        //.border(2.dp, Color.Black)
                        .padding(end = 38.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Список свернут",
                        modifier = Modifier.size(20.dp)
                    )
                }
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

