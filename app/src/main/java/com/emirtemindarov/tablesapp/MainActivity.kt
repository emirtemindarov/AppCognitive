package com.emirtemindarov.tablesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.emirtemindarov.tablesapp.games.GamesDatabase
import com.emirtemindarov.tablesapp.games.GamesViewModel
import com.emirtemindarov.tablesapp.logic.MainNavGraph
import com.emirtemindarov.tablesapp.ui.theme.TablesAppTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            GamesDatabase::class.java,
            "games.db"
        ).build()
    }

    private val gamesViewModel by viewModels<GamesViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel/*?*/> create(modelClass: Class<T>): T {   // !!!!
                    return GamesViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TablesAppTheme {
                val gamesState by gamesViewModel.gameState.collectAsState()
                MainNavGraph(gamesState = gamesState, onEvent = gamesViewModel::onEvent)
            }
        }
    }
}