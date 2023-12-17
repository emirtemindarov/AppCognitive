package com.emirtemindarov.tablesapp.games

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val shortDescription: String,
    val description: String,
    val difficulty: String,
    val isCompleted: Boolean,
    val bestScore: Int
)