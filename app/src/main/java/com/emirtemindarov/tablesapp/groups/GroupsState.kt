package com.emirtemindarov.tablesapp.groups

data class GroupsState(
    val groupsList: List<Group> = emptyList(),

    /*
        Group
    */
    val title: String = "",
    val color: String = "",
    val expanded: Boolean = false,

    val isAddingGroup: Boolean = false,
    // если внести это в Game, то метод сортировки будет сохранятся
    val sortType: GroupsSortType = GroupsSortType.DEFAULT
)