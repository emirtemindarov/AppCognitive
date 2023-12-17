package com.emirtemindarov.tablesapp.crossref

import androidx.room.*
import com.emirtemindarov.tablesapp.games.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface CrossRefsDao {
    @Upsert
    suspend fun upsertCrossRef(crossRef: CrossRef)

    @Query("DELETE FROM crossRef WHERE gameId = :gameId AND groupId = :groupId")
    suspend fun deleteCrossRef(gameId: Int, groupId: Int)

    @Query("SELECT * FROM crossRef")
    fun getCrossRefs(): Flow<List<CrossRef>>

    /*@Query("SELECT gameId FROM crossRef WHERE groupId = :groupId")
    fun getGamesIdInThisGroup(groupId: Int): Flow<List<Int>>

    @Query("SELECT groupId FROM crossRef WHERE gameId = :gameId")
    fun getGroupsIdWhereThisGameIncluded(gameId: Int): Flow<List<Int>>*/
}