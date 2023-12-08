package com.emirtemindarov.tablesapp.groups

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.emirtemindarov.tablesapp.games.Game

/*@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Game::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("childClassColumn"),
            onCreate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    )
)*/
@Entity
data class Group(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val color: String,
    val expanded: Boolean
)


