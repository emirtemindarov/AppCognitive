package com.emirtemindarov.tablesapp.games

import com.emirtemindarov.tablesapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreGameDialog(
    game: Game,
    closeDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest = closeDialog,
        /*title = { Text("Диалоговое окно") },*/
        text = {
            // Основа
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                    /*.border(1.dp, Color.Magenta)*/
            ) {

                // Картинка
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(0.dp, 300.dp)
                        //.border(4.dp, Color.Blue)
                        //.background(Color.Green)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.game1_preview),
                        contentDescription = "game 1 preview",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Название
                Text(
                    text = game.title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                // Выбор сложности
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(9.dp, 0.dp)
                        /*.border(1.dp, Color.Red)*/
                ) {
                    Column(modifier = Modifier/*.border(1.dp, Color.Red)*/) {
                        Text("Уровень")
                    }

                    val difficulty_variants = listOf(
                        "Упрощенный",
                        "Базовый",
                        "Усложненный",
                        "Более сложный"
                    )

                    var choise by remember { mutableStateOf(difficulty_variants[1]) }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            /*.border(2.dp, Color.Blue)*/
                    ) {
                        CustomDropdownMenu(
                            modifier = Modifier.padding(16.dp),
                            selectedItem = choise,
                            onItemSelected = { index, item: String ->
                                choise = item
                            },
                            itemList = difficulty_variants
                        )
                    }
                }

                // Полное описание
                LazyColumn(modifier = Modifier
                    /*.border(1.dp, Color.Red)*/
                    .heightIn(0.dp, 150.dp)
                ) {
                    item {
                        Text(text = game.description)
                    }
                }


            }
        },
        confirmButton = {
            // Кнопки действия
            Row(modifier = Modifier
                .fillMaxWidth()
                /*.border(1.dp, Color.Red)*/) {

                // Кнопка "Закрыть"
                Button(
                    onClick = closeDialog,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(
                            alpha = 0F,
                        )
                    )
                ) {
                    Text(text = "Закрыть", color = Color.Gray)
                }

                // Кнопка "Начать"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        /*.border(2.dp, Color.Green)*/,
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(onClick = closeDialog) {
                        Text("Начать")
                    }
                }
            }

        }
    )
}