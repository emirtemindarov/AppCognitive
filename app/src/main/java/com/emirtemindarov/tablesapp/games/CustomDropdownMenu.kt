package com.emirtemindarov.tablesapp.games

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import com.emirtemindarov.tablesapp.R

@Composable
fun <E> CustomDropdownMenu(
    modifier: Modifier,
    selectedItem: E,
    onItemSelected: (Int, E) -> Unit,
    itemList: List<E>?,
    defaultText: String? = itemList?.first().toString()
) {
    var isOpen by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(48.dp))
            .background(MaterialTheme.colorScheme.surface)
            .height(48.dp)
    ) {
        defaultText?.let {
            if (selectedItem == null || selectedItem.toString().isEmpty()) {
                Text(
                    text = defaultText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(.45f)
                )
            }
        }

        Text(
            text = selectedItem?.toString() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 32.dp, 3.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(.85f),
            expanded = isOpen,
            onDismissRequest = {
                isOpen = false
            }
        ) {
            itemList?.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(item.toString())
                    },
                    onClick = {
                        isOpen = false
                        onItemSelected(index, item)
                    }
                )
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(24.dp),
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
            contentDescription = "Dropdown"
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .clickable(
                    onClick = { isOpen = true }
                )
        )
    }
}
