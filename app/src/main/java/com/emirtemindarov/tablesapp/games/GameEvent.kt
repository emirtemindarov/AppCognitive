package com.emirtemindarov.tablesapp.games

sealed interface GameEvent {
    object SaveGame: GameEvent
    data class SortGames(val sortType: GamesSortType): GameEvent
    data class DeleteGame(val game: Game): GameEvent

    object ShowDialog: GameEvent
    object HideDialog: GameEvent
    object ShowSortDialog: GameEvent
    object HideSortDialog: GameEvent

    data class SetTitle(val newTitle: String): GameEvent
    data class SetDescription(val newDescription: String): GameEvent
    data class SetDifficulty(val newDifficulty: String): GameEvent
    data class SetIsCompleted(val newIsCompleted: Boolean): GameEvent
    data class SetBestScore(val newBestScore: Int): GameEvent
}