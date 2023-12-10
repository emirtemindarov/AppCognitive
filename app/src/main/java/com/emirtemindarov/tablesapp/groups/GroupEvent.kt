package com.emirtemindarov.tablesapp.groups

sealed interface GroupEvent {

    object SaveGroup: GroupEvent

    data class SortGroups(val sortType: GroupsSortType): GroupEvent
    data class DeleteGroup(val group: Group): GroupEvent

    object ShowDialog: GroupEvent
    object HideDialog: GroupEvent
    object ShowRenameDialog: GroupEvent
    object HideRenameDialog: GroupEvent
    object ShowSortDialog: GroupEvent
    object HideSortDialog: GroupEvent

    data class SetTitle(val newTitle: String): GroupEvent
    data class RenameGroup(val id: Int, val newTitle: String): GroupEvent
    // TODO потом
    //data class SetColor(val newColor: String): GroupEvent
    data class UpdateExpanded(val id: Int): GroupEvent

    data class SetCurrentGroup(val groupId: Int, val groupTitle: String) : GroupEvent
}