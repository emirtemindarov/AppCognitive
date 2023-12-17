package com.emirtemindarov.tablesapp.crossref

data class CrossRefsState(
    val crossRefsList: List<CrossRef> = emptyList(),

    /*
        CrossRef
    */
    val gameId: Int = -1,
    val groupId: Int = -1,

    val isAddingCrossRef: Boolean = false,
)