package com.emirtemindarov.tablesapp.crossref

sealed interface CrossRefEvent {
    object SaveCrossRef: CrossRefEvent
    object DeleteCrossRef: CrossRefEvent

    object ShowDialog: CrossRefEvent
    object HideDialog: CrossRefEvent

    // установка текущих значений в crossRefsState классе, для работы с ними через crossRefsViewModel
    data class SetGameId(val newGameId: Int): CrossRefEvent
    data class SetGroupId(val newGroupId: Int): CrossRefEvent
}