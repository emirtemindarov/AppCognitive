package com.emirtemindarov.tablesapp.crossref

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface GroupGameCrossRefDao {
    @Transaction
    @Query("SELECT * FROM 'group'")
    fun getGroupsWithGames(): List<GroupsWithGames>

    @Transaction
    @Query("SELECT * FROM game")
    fun getGamesWithinGroups(): List<GamesWithinGroups>
}