package com.emirtemindarov.tablesapp.games

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Game::class],
    version = 1
)
abstract class GamesDatabase: RoomDatabase() {

    abstract val dao: GamesDao
}