package com.emirtemindarov.tablesapp.groups

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
            is GroupEvent.DeleteGame -> {
                viewModelScope.launch {
                    groupsDao.deleteGroup(event.group)
                }
            }
            GroupEvent.SaveGame -> {
                val title = groupsState.value.title
                val color = "Red"/*groupsState.value.color*/            // !!!!
                val expanded = false/*groupsState.value.expanded*/

                if(title.isBlank() || color.isBlank()) {
                    return
                }

                val group = Group(
                    title = title,
                    color = color,
                    expanded = expanded
                )
                viewModelScope.launch {
                    groupsDao.upsertGroup(group)
                }
                _groupsState.update { it.copy(
                    isAddingGroup = false,
                    title = "",
                    color = "Red",
                    expanded = false,
                ) }
            }

            is GroupEvent.SetTitle -> {
                _groupsState.update { it.copy(
                    title = event.newTitle
                ) }
            }
            is GroupEvent.SetColor -> {
                _groupsState.update { it.copy(
                    color = event.newColor
                ) }
            }
            is GroupEvent.SetExpanded -> {
                _groupsState.update { it.copy(
                    expanded = event.newExpanded
                ) }
            }

            is GroupEvent.SortGames -> {
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
           
        }
    }
}