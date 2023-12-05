package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirtemindarov.tablesapp.R

@Composable
fun GamesListItem(
    game: Game,
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${game.title} ${game.description}",
                fontSize = 20.sp
            )
            Text(text = game.difficulty, fontSize = 12.sp)
        }
        IconButton(onClick = {
            onEvent(GameEvent.DeleteGame(game))
        }) {
            Icon(Icons.Default.Delete, "Delete games")
        }


        val difficulty_variants = listOf(
            "Упрощенная",
            "Базовая",
            "Усложненная",
            "Более сложная"
        )

        var choise by remember { mutableStateOf(difficulty_variants[1]) }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            CustomDropdownMenu(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                selectedItem = choise,
                onItemSelected = { index, item: String ->
                    choise = item
                },
                itemList = difficulty_variants
            )
        }
    }
}

/*enum class Difficulty(val dek: String) {
    EASY("Упрощенная"),
    BASIC("Базовая"),
    HARD("Усложненная"),
    ADVANCED("Более сложная")
}*/

