package com.emirtemindarov.tablesapp.groups

sealed interface GroupEvent {
    object SaveGroup: GroupEvent

    data class SortGroups(val sortType: GroupsSortType): GroupEvent
    data class DeleteGroup(val group: Group): GroupEvent
    data class RenameGroup(val id: Int, val newTitle: String): GroupEvent

    object ShowDialog: GroupEvent
    object HideDialog: GroupEvent
    object ShowRenameDialog: GroupEvent
    object HideRenameDialog: GroupEvent

    data class SetTitle(val newTitle: String): GroupEvent
    data class SetColor(val newColor: String): GroupEvent
    data class SetExpanded(val newExpanded: Boolean): GroupEvent
}