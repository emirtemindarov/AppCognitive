package com.emirtemindarov.tablesapp.games

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Upsert
    suspend fun upsertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("SELECT * FROM game")
    fun getGamesOrderedByDefault(): Flow<List<Game>>

    @Query("SELECT * FROM game ORDER BY difficulty Asc")
    fun getGamesOrderedByDifficulty(): Flow<List<Game>>

    @Query("SELECT * FROM game ORDER BY bestScore Desc")
    fun getGamesOrderedByBestScore(): Flow<List<Game>>
}