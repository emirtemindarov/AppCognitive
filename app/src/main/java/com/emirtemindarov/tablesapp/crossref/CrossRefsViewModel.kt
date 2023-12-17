package com.emirtemindarov.tablesapp.crossref

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CrossRefViewModel(
    private val crossRefDao: CrossRefsDao
): ViewModel() {

    private val _crossRefs = crossRefDao.getCrossRefs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _crossRefsState = MutableStateFlow(CrossRefsState())
    val crossRefsState = combine(_crossRefsState, _crossRefs) { state, crossRefsList ->
        state.copy(
            crossRefsList = crossRefsList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CrossRefsState())

    fun onEvent(event: CrossRefEvent) {
        when(event) {
            CrossRefEvent.SaveCrossRef -> {
                val gameId = crossRefsState.value.gameId
                val groupId = crossRefsState.value.groupId

                if(gameId == -1 || groupId == -1) {
                    return
                }

                val crossRef = CrossRef(
                    gameId = gameId,
                    groupId = groupId
                )

                viewModelScope.launch {
                    crossRefDao.upsertCrossRef(crossRef)
                }
                Log.i("upsert id's details", "gameId: ${crossRef.gameId} | groupId: ${crossRef.groupId}")

                /*_crossRefsState.update { it.copy(
                    gameId = -1,
                    groupId = -1,
                ) }*/
            }

            CrossRefEvent.DeleteCrossRef -> {
                val gameId = crossRefsState.value.gameId
                val groupId = crossRefsState.value.groupId

                if(gameId == -1 || groupId == -1) {
                    return
                }

                viewModelScope.launch {
                    crossRefDao.deleteCrossRef(gameId, groupId)
                }
                Log.i("delete id's details", "gameId: $gameId | groupId: $groupId")

                /*_crossRefsState.update { it.copy(
                    gameId = -1,
                    groupId = -1,
                ) }*/
            }

            is CrossRefEvent.DeleteCrossRefsByGroupId -> {
                viewModelScope.launch {
                    crossRefDao.deleteCrossRefsByGroupId(event.groupId)
                }
                Log.i("delete crossRefs", "groupId: ${event.groupId} - deleted -> deleted binded crossRefs")
            }

            is CrossRefEvent.SetGameId -> {
                _crossRefsState.update { it.copy(
                    gameId = event.newGameId
                ) }
            }
            is CrossRefEvent.SetGroupId -> {
                _crossRefsState.update { it.copy(
                    groupId = event.newGroupId
                ) }
            }

            /*is GameEvent.SortGames -> {
                _gamesSortType.value = event.sortType
            }*/
            
            CrossRefEvent.ShowDialog -> {
                _crossRefsState.update { it.copy(
                    isAddingCrossRef = true
                ) }
            }
            CrossRefEvent.HideDialog -> {
                _crossRefsState.update { it.copy(
                    isAddingCrossRef = false
                ) }
            }

            /*GameEvent.ShowSortDialog -> {
                _gameState.update { it.copy(
                    isSortingGames = true
                ) }
            }
            GameEvent.HideSortDialog -> {
                _gameState.update { it.copy(
                    isSortingGames = false
                ) }
            }*/
           
        }
    }
}