package com.emirtemindarov.tablesapp.groups

data class GroupsState(
    val groupsList: List<Group> = emptyList(),

    /*
        Group
    */
    val title: String = "",
    val color: String = "",
    val expanded: Boolean = false,

    val currentGroupId: Int = -1,

    val isAddingGroup: Boolean = false,
    val isRenamingGroup: Boolean = false,
    val isSortingGroups: Boolean = false,

    // Нет: если внести это в Game, то метод сортировки будет сохранятся
    val sortType: GroupsSortType = GroupsSortType.DEFAULT
)