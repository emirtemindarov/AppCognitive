package com.emirtemindarov.tablesapp.crossref

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.groups.Group

@Entity(primaryKeys = ["playlistId", "songId"])
data class GroupGameCrossRef(
    val groupId: Int,
    val gameId: Int
)


data class GroupsWithGames(
    @Embedded val group: Group,
    @Relation(
        parentColumn = "id",  // groupId
        entityColumn = "id",  // gameId
        associateBy = Junction(GroupGameCrossRef::class)
    )
    val games: List<Game>
)

data class GamesWithinGroups(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "id",  // gameId
        entityColumn = "id",  // groupId
        associateBy = Junction(GroupGameCrossRef::class)
    )
    val groups: List<Group>
)
