package com.emirtemindarov.tablesapp.crossref

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CrossRef(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gameId: Int,
    val groupId: Int
)