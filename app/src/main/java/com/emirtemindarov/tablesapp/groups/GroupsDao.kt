package com.emirtemindarov.tablesapp.groups

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupsDao {
    @Upsert
    suspend fun insertGroup(group: Group)

    @Delete
    suspend fun deleteGroup(game: Group)

    @Query("SELECT * FROM 'group'")
    fun getGroupsOrderedByDefault(): Flow<List<Group>>

    @Query("SELECT * FROM 'group' ORDER BY title Asc")
    fun getGroupsOrderedByTitleAsc(): Flow<List<Group>>

    @Query("SELECT * FROM 'group' ORDER BY title Desc")
    fun getGroupsOrderedByTitleDesc(): Flow<List<Group>>

    @Query("UPDATE 'group' SET title = :title WHERE id = :id")
    suspend fun renameGroup(id: Int, title: String)

    @Query("UPDATE 'group' SET expanded = NOT expanded WHERE id = :id")
    suspend fun updateExpanded(id: Int)
}