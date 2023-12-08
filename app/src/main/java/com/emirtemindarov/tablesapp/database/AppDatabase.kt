package com.emirtemindarov.tablesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.games.GamesDao
import com.emirtemindarov.tablesapp.groups.Group
import com.emirtemindarov.tablesapp.groups.GroupsDao

@Database(
    entities = [
        Game::class,
        Group::class
    ],
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract val gamesDao: GamesDao
    abstract val groupsDao: GroupsDao
}