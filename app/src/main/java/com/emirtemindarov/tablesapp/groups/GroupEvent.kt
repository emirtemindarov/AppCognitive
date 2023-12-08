package com.emirtemindarov.tablesapp.groups

import androidx.compose.ui.graphics.Color

sealed interface GroupEvent {
    object SaveGame: GroupEvent
    data class SortGames(val sortType: GroupsSortType): GroupEvent
    data class DeleteGame(val group: Group): GroupEvent

    object ShowDialog: GroupEvent
    object HideDialog: GroupEvent

    data class SetTitle(val newTitle: String): GroupEvent
    data class SetColor(val newColor: String): GroupEvent
    data class SetExpanded(val newExpanded: Boolean): GroupEvent
}