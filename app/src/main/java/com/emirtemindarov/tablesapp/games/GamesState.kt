package com.emirtemindarov.tablesapp.games

data class GamesState(
    val gamesList: List<Game> = emptyList(),

    /*
        Game
    */
    val title: String = "",
    val description: String = "",
    val difficulty: String = "",
    val isCompleted: Boolean = false,
    val bestScore: Int = 0,

    val isAddingGame: Boolean = false,

    // Нет: если внести это в Game, то метод сортировки будет сохранятся
    val sortType: GamesSortType = GamesSortType.DEFAULT
)