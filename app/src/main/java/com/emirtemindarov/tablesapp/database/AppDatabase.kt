package com.emirtemindarov.tablesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emirtemindarov.tablesapp.crossref.CrossRef
import com.emirtemindarov.tablesapp.crossref.CrossRefsDao
import com.emirtemindarov.tablesapp.games.Game
import com.emirtemindarov.tablesapp.games.GamesDao
import com.emirtemindarov.tablesapp.groups.Group
import com.emirtemindarov.tablesapp.groups.GroupsDao

@Database(
    entities = [
        Game::class,
        Group::class,
        CrossRef::class
    ],
    version = 4
)
abstract class AppDatabase: RoomDatabase() {
    abstract val gamesDao: GamesDao
    abstract val groupsDao: GroupsDao
    abstract val crossRefsDao: CrossRefsDao
}