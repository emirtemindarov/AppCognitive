package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.logic.scaffold.StatisticsDialog

@Composable
fun GamesListItem(
    game: Game,
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    val openDialog: () -> Unit = {
        showDialog = true
    }
    val closeDialog: () -> Unit = {
        showDialog = false
    }
    if (showDialog) {
        PreGameDialog(closeDialog)
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            //.border(1.dp, Color.Blue)
            .clip(RoundedCornerShape(28.dp))
            .clickable(onClick = openDialog)
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

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            //.border(2.dp, Color.Green)
        ) {
            Image(
                painter = painterResource(id = R.drawable.game1_preview),
                contentDescription = "game 1 preview",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            //.border(2.dp, Color.Magenta)
            .background(color = Color.LightGray)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Название",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Краткое описание, вкратце рассказывающее что нужно сделать",
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
            }
        }
    }
}

/*enum class Difficulty(val text: String) {
    EASY("Упрощенная"),
    BASIC("Базовая"),
    HARD("Усложненная"),
    ADVANCED("Более сложная")
}*/

