package com.emirtemindarov.tablesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.games.GamesDao

@Database(
    entities = [
        Game::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract val gamesDao: GamesDao
}