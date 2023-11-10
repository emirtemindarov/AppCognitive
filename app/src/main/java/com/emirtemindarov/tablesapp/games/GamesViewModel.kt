package com.emirtemindarov.tablesapp.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModel(
    private val dao: GamesDao
): ViewModel() {
    private val _gamesSortType = MutableStateFlow(GamesSortType.DEFAULT)

    private val _games = _gamesSortType
        .flatMapLatest { gamesSortType ->
            when(gamesSortType) {
                GamesSortType.DEFAULT -> dao.getGamesOrderedByDefault()
                GamesSortType.DIFFICULTY -> dao.getGamesOrderedByDifficulty()
                GamesSortType.BEST_SCORE -> dao.getGamesOrderedByBestScore()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _gameState = MutableStateFlow(GamesState())
    val gameState = combine(_gameState, _gamesSortType, _games) { state, sortType, gamesList ->
        state.copy(
            gamesList = gamesList,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GamesState())

    fun onEvent(event: GameEvent) {
        when(event) {
            is GameEvent.DeleteGame -> {
                viewModelScope.launch {
                    dao.deleteGame(event.game)
                }
            }
            GameEvent.SaveGame -> {
                val title = gameState.value.title
                val description = gameState.value.description
                val difficulty = gameState.value.difficulty
                //val isCompleted = gameState.value.isCompleted
                //val bestScore = gameState.value.bestScore

                if(title.isBlank() || description.isBlank() || difficulty.isBlank()) {
                    return
                }

                val game = Game(
                    title = title,
                    description = description,
                    difficulty = difficulty,
                    isCompleted = false,
                    bestScore = 0
                )
                viewModelScope.launch {
                    dao.upsertGame(game)
                }
                _gameState.update { it.copy(
                    isAddingGame = false,
                    title = "",
                    description = "",
                    difficulty = "",
                ) }
            }

            is GameEvent.SetTitle -> {
                _gameState.update { it.copy(
                    title = event.newTitle
                ) }
            }
            is GameEvent.SetDescription -> {
                _gameState.update { it.copy(
                    description = event.newDescription
                ) }
            }
            is GameEvent.SetDifficulty -> {
                _gameState.update { it.copy(
                    difficulty = event.newDifficulty
                ) }
            }
            is GameEvent.SetIsCompleted -> {
                _gameState.update { it.copy(
                    isCompleted = event.newIsCompleted
                ) }
            }
            is GameEvent.SetBestScore -> {
                _gameState.update { it.copy(
                    bestScore = event.newBestScore
                ) }
            }

            is GameEvent.SortGames -> {
                _gamesSortType.value = event.sortType
            }
            
            GameEvent.ShowDialog -> {
                _gameState.update { it.copy(
                    isAddingGame = true
                ) }
            }
            GameEvent.HideDialog -> {
                _gameState.update { it.copy(
                    isAddingGame = false
                ) }
            }
           
        }
    }
}