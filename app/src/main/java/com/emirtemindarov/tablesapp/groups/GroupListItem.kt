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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.emirtemindarov.tablesapp.crossref.CrossRefEvent
import com.emirtemindarov.tablesapp.crossref.CrossRefsState
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.games.PreGameDialog
import com.emirtemindarov.tablesapp.helpers.RawContextMenu
import com.emirtemindarov.tablesapp.logic.scaffold.ContextMenuItemContent

@Composable
fun GroupListItem(
    group: Group,
    groupsState: GroupsState,
    onEvent: (GroupEvent) -> Unit,
    gamesState: GamesState,
    onGameEvent: (GameEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    applicationContext: Context
) {

    // Открытие диалогового окна задачи при нажатии на ссылки на задачу в списке группы (изначально нет определенной задачи)
    var currentGame: Game? by remember { mutableStateOf(null) }
    val setCurrentGame: (Game) -> Unit = { game ->
        currentGame = game
    }
    var showDialog by remember { mutableStateOf(false) }
    val openDialog: () -> Unit = {
        showDialog = true
    }
    val closeDialog: () -> Unit = {
        showDialog = false
    }
    if (showDialog) {
        PreGameDialog(
            currentGame!!,
            closeDialog
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

    // TODO Переделать
    // Контекстное меню заголовка группы
    val groupTitleRawContextMenu: @Composable (Group, (GroupEvent) -> Unit) -> Unit = { rGroup: Group, rOnEvent: (GroupEvent) -> Unit ->
        Log.i("GROUP DETAILS LAMBDA", "$rGroup")

        RawContextMenu(
            expanded = expandedContextMenu,
            collapse = collapse,
            dropdownMenuItems = listOf(

                // TODO? Перекидывать на первую вкладку для выбора одной задачи, нескольких задач или отмены выбора
                // Элемент контекстного меню, при нажатии открывается диалоговое окно редактирования списка задач в группе
                ContextMenuItemContent(
                    item = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Изменить список задач группы"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Редактировать список")
                        }
                    },
                    action = {
                        onCrossRefEvent(CrossRefEvent.SetGroupId(group.id))
                        onCrossRefEvent(CrossRefEvent.ShowDialog)
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
                                Icons.Default.Edit,
                                contentDescription = "Rename group"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Переименовать группу")
                        }
                    },
                    action = {
                        rOnEvent(GroupEvent.ShowRenameDialog)
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
                            "Группа удалена: ${rGroup.title}",
                            Toast.LENGTH_LONG
                        ).show()
                        rOnEvent(GroupEvent.DeleteGroup(rGroup))
                        onCrossRefEvent(CrossRefEvent.DeleteCrossRefsByGroupId(group.id))
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

    // TODO Переделать
    // Контекстное меню ссылки на задачу
    val gameRefRawContextMenu: @Composable (Game, Group, (GroupEvent) -> Unit) -> Unit = { rGame: Game, rGroup: Group, rOnEvent: (GroupEvent) -> Unit ->
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
                        Log.w("rGame.title", "Ошибка: ${rGame.title}")
                        Toast.makeText(
                            applicationContext,
                            "${rGame.title} удалена из ${rGroup.title}",
                            Toast.LENGTH_SHORT
                        ).show()
                        onCrossRefEvent(CrossRefEvent.DeleteCrossRef)
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


        if (expandedContextMenu) {
            Log.i("GROUP MENU BEFORE INVOKE", "$group")
            groupTitleRawContextMenu.invoke(group, onEvent)
        }

        if (expandedGameRefContextMenu) {
            gameRefRawContextMenu.invoke(
                gamesState.gamesList.find { game ->
                    game.id == crossRefsState.gameId
                }!!,
                group,
                onEvent
            )
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
                            onLongPress = {
                                onEvent(GroupEvent.SetCurrentGroup(group.id, group.title))
                                Log.i("GROUP SET CURRENT", "${group.id} = ${groupsState.currentGroupId}")
                                expandContextMenu.invoke()
                            },
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

            // Блок ссылки на задачу
            if (group.expanded) {
                crossRefsState.crossRefsList.filter { crossRef ->
                    crossRef.groupId == group.id
                }.forEach { crossRef ->

                    // найденная игра по gameId (find всегда найдет задачу)
                    val game = gamesState.gamesList.find { game -> game.id == crossRef.gameId }!!

                    // ссылка на задачу
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
                                    onTap = {
                                        setCurrentGame.invoke(game)
                                        openDialog.invoke()
                                    },
                                    //onDoubleTap = { /* Called on Double Tap */ },
                                    onLongPress = {
                                        onCrossRefEvent(CrossRefEvent.SetGroupId(group.id))
                                        onCrossRefEvent(CrossRefEvent.SetGameId(game.id))
                                        expandGameRefContextMenu.invoke()
                                    },
                                )
                            }
                    ) {
                        // Заголовок группы
                        Text(
                            text = game.title,
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

                            // TODO AnimatedVisibility

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

