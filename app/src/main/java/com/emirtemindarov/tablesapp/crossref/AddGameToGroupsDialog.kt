package com.emirtemindarov.tablesapp.crossref

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.groups.GroupEvent
import com.emirtemindarov.tablesapp.groups.GroupsState
import com.emirtemindarov.tablesapp.groups.parseColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGameToGroupsDialog(
    gameId: Int,
    groupsState: GroupsState,
    onGroupEvent: (GroupEvent) -> Unit,
    crossRefsState: CrossRefsState,
    onCrossRefEvent: (CrossRefEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // выполнить поиск из crossRefsState всех записей с определенным game.id
    Log.d("crossRefs List", "${crossRefsState.crossRefsList}")
    val filteredCrossRefs = crossRefsState.crossRefsList.filter {
        Log.w("filter by gameId", "filter by gameId: $gameId")
        it.gameId == gameId
    }
    Log.d("Filtered CrossRefs", "$filteredCrossRefs")
    val selectedGroupsIdList = remember { mutableSetOf<Int>() }
    filteredCrossRefs.forEach { crossRef ->
        Log.w("adding filtered", "adding groupId of filtered crossRefs to selected list. groupId: ${crossRef.groupId}")
        selectedGroupsIdList.add(crossRef.groupId)
    }
    Log.d("Selected GroupsId List", "$selectedGroupsIdList")




    AlertDialog(
        modifier = modifier.heightIn(300.dp, 450.dp),
        onDismissRequest = {
            onCrossRefEvent(CrossRefEvent.HideDialog)
        },
        title = { Text(text = "Отметьте группы") },
        text = {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.fillMaxWidth().heightIn(250.dp, 400.dp)
            ) {


                // отображение списка групп
                items(groupsState.groupsList) { group ->

                    var selected by remember { mutableStateOf(selectedGroupsIdList.contains(group.id)) }
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp))

                            // применить стили в зависимости от того, выбран элемент или нет
                            .conditional(
                                selected,
                                ifTrue = { background(Color.White) },
                                ifFalse = { background(Color.LightGray.copy(0.5f)) }
                            )

                            // добавление ссылки на задачу в группу
                            .clickable {
                                selected = !selected   // верно
                                Log.i("SELECTED value", "group${group.id} selected: $selected")
                                onCrossRefEvent(CrossRefEvent.SetGameId(gameId))
                                Log.d("crossRefState gameId", "crossRefState gameId: ${crossRefsState.gameId}")
                                onCrossRefEvent(CrossRefEvent.SetGroupId(group.id))
                                Log.d("crossRefState groupId", "crossRefState groupId: ${crossRefsState.groupId}")
                                if (selected) {
                                    selectedGroupsIdList.add(group.id)
                                    Log.i("id added", "added: ${group.id} | Full list: $selectedGroupsIdList")
                                    onCrossRefEvent(CrossRefEvent.SaveCrossRef)
                                } else {
                                    selectedGroupsIdList.remove(group.id)
                                    Log.i("id removed", "removed: ${group.id} | Full list: $selectedGroupsIdList")
                                    onCrossRefEvent(CrossRefEvent.DeleteCrossRef)
                                }
                            }


                    ) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.fillMaxSize().padding(10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = modifier
                                        .size(25.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(parseColor(group.color).copy(0.8f))
                                ) {}
                                Spacer(modifier = modifier.width(12.dp))
                                Column {
                                    Text(text = group.title)
                                }
                            }
                            Row {
                                AnimatedVisibility(
                                    visible = selected,
                                    enter = fadeIn(
                                        // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                                        initialAlpha = 0.4f
                                    ),
                                    exit = fadeOut(
                                        // Overwrites the default animation with tween
                                        animationSpec = tween(durationMillis = 250)
                                    )
                                ) {
                                    Column(
                                        modifier = modifier
                                            .size(25.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.CheckCircle,
                                            contentDescription = "Добавлено в группу"
                                        )
                                    }
                                }
                            }
                        }

                        /*Text(text = "${crossRefsState.gameId} - crossRefsState.gameId")
                        Text(text = "${crossRefsState.groupId} - crossRefsState.groupId")
                        Text(text = "$gameId - dialog parameter gameId")*/
                    }
                }
            }
        },
        confirmButton = {
            // Кнопка "Закрыть"
            Button(
                onClick = { onCrossRefEvent(CrossRefEvent.HideDialog) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black.copy(
                        alpha = 0F,
                    )
                )
            ) {
                Text(text = "Закрыть", color = Color.Gray)
            }
        }
    )
}

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}