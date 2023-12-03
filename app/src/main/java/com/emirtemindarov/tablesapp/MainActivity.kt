package com.emirtemindarov.tablesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.emirtemindarov.tablesapp.database.AppDatabase
import com.emirtemindarov.tablesapp.games.GamesViewModel
import com.emirtemindarov.tablesapp.logic.MainNavGraph
import com.emirtemindarov.tablesapp.logic.login.GoogleAuthUiClient
import com.emirtemindarov.tablesapp.ui.theme.TablesAppTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    private val database = Firebase.database("https://appcognitive-6948f-default-rtdb.europe-west1.firebasedatabase.app/")
    private val usersRef = database.getReference("users")
    //private val gamesRef = database.getReference("games")

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val gamesViewModel by viewModels<GamesViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel/*?*/> create(modelClass: Class<T>): T {   // !!!!
                    return GamesViewModel(db.gamesDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TablesAppTheme {
                usersRef.addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again whenever data at this location is updated.
                        for (user in dataSnapshot.children) {
                            Log.d("data", "User is: $user")
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.w("read fail", "Failed to read value.", error.toException())
                    }
                })

                val gamesState by gamesViewModel.gameState.collectAsState()
                MainNavGraph(
                    usersRef,
                    applicationContext,
                    googleAuthUiClient,
                    gamesState,
                    onEvent = gamesViewModel::onEvent
                )
            }
        }
    }
}