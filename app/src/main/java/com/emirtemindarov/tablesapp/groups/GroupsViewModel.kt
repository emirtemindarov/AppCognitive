package com.emirtemindarov.tablesapp.groups

import android.util.Log
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
class GroupsViewModel(
    private val groupsDao: GroupsDao
): ViewModel() {
    private val _groupsSortType = MutableStateFlow(GroupsSortType.DEFAULT)

    private val _groups = _groupsSortType
        .flatMapLatest { groupsSortType ->
            when(groupsSortType) {
                GroupsSortType.DEFAULT -> groupsDao.getGroupsOrderedByDefault()
                GroupsSortType.TITLE_ASC -> groupsDao.getGroupsOrderedByTitleAsc()
                GroupsSortType.TITLE_DESC -> groupsDao.getGroupsOrderedByTitleDesc()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _groupsState = MutableStateFlow(GroupsState())
    val groupsState = combine(_groupsState, _groupsSortType, _groups) { state, sortType, gamesList ->
        state.copy(
            groupsList = gamesList,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GroupsState())

    fun onEvent(event: GroupEvent) {
        when(event) {
            is GroupEvent.DeleteGroup -> {
                viewModelScope.launch {
                    Log.d("GROUP", event.group.toString())
                    groupsDao.deleteGroup(event.group)
                }
            }
            is GroupEvent.RenameGroup -> {
                viewModelScope.launch {
                    Log.w("GROUP", "${event.id} | ${event.newTitle}")
                    groupsDao.renameGroup(event.id, event.newTitle)
                    _groupsState.update { it.copy(
                        isRenamingGroup = false,
                        title = ""
                    ) }
                }
            }
            GroupEvent.SaveGroup -> {
                val title = groupsState.value.title
                val color = getRandomColor()            // TODO Выбор цвета
                val expanded = false

                if(title.isBlank()) {
                    return
                }

                val group = Group(
                    title = title,
                    color = color,
                    expanded = expanded
                )
                viewModelScope.launch {
                    Log.i("GROUP", group.toString())
                    groupsDao.insertGroup(group)
                }
                _groupsState.update { it.copy(
                    isAddingGroup = false,
                    title = ""
                ) }
            }

            is GroupEvent.SetTitle -> {
                _groupsState.update { it.copy(
                    title = event.newTitle
                ) }
            }

            // TODO для выбора цвета при создании
            /*is GroupEvent.SetColor -> {
                _groupsState.update { it.copy(
                    color = event.newColor
                ) }
            }
            is GroupEvent.UpdateColor -> {
                viewModelScope.launch {
                    Log.w("GROUP", "${event.id} | ${event.newTitle}")
                    groupsDao.renameGroup(event.id, event.newTitle)
                    _groupsState.update { it.copy(
                        isRenamingGroup = false,
                        title = ""
                    ) }
                }
            }*/

            is GroupEvent.UpdateExpanded -> {
                viewModelScope.launch {
                    Log.w("Switch", "${event.id}")
                    groupsDao.updateExpanded(event.id)
                }
            }

            is GroupEvent.SortGroups -> {
                _groupsSortType.value = event.sortType
            }
            
            GroupEvent.ShowDialog -> {
                _groupsState.update { it.copy(
                    isAddingGroup = true
                ) }
            }
            GroupEvent.HideDialog -> {
                _groupsState.update { it.copy(
                    isAddingGroup = false
                ) }
            }

            GroupEvent.ShowRenameDialog -> {
                _groupsState.update { it.copy(
                    isRenamingGroup = true
                ) }
            }
            GroupEvent.HideRenameDialog -> {
                _groupsState.update { it.copy(
                    isRenamingGroup = false
                ) }
            }
           
        }
    }

    private fun getRandomColor(): String {
        return listOf(
            "yellow",
            "red",
            "green",
            "lightgray",
            "blue",
            "cyan",
            "white",
            "magenta"
        ).random()
    }
}