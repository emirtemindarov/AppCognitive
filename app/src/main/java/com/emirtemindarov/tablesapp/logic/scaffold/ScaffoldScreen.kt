package com.emirtemindarov.tablesapp.logic.scaffold

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.logic.login.GoogleAuthUiClient
import com.google.firebase.FirebaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(
    applicationContext: Context,
    googleAuthUiClient: GoogleAuthUiClient,
    usersRef: DatabaseReference,
    mainNavController: NavHostController,
    scaffoldNavController: NavHostController = rememberNavController()
) {
    val coroutineScope = rememberCoroutineScope()
    val userData = googleAuthUiClient.getSignedInUser()
    var addChildTrigger = false

    val onSignOut: () -> Unit = {
        coroutineScope.launch {
            googleAuthUiClient.signOut()
            Toast.makeText(
                applicationContext,
                "Signed out",
                Toast.LENGTH_LONG
            ).show()
            mainNavController.navigate("auth") {
                popUpTo("bottom_bar") {
                    inclusive = true
                }
            }
        }
    }

    usersRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            userData?.let {
                for (user in dataSnapshot.children) {
                    if (user.key == userData.userId) {
                        Log.i("sign_in", "Sign in: $user")
                    } else {
                        Log.i("new_user", "New user: ${dataSnapshot.children}, ${user.child(userData.userId).children}, ${user.child(userData.userId)}, ${user.child(userData.userId).child("parameter1")}")
                        addChildTrigger = !addChildTrigger
                    }
                }
            }
        }
        override fun onCancelled(error: DatabaseError) {
            Log.w("read fail", "Failed to read value.", error.toException())
        }
    })

    LaunchedEffect(key1 = addChildTrigger) {
        val hashMap = HashMap<String, Int>()
        hashMap.put("parameter1", 11)
        hashMap.put("parameter2", 12)
        userData?.let {
            usersRef.child(userData.userId).setValue(hashMap)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")
                },
                /*navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },*/
                actions = {
                    IconButton(onClick = {
                        mainNavController.navigate("auth") {
                            popUpTo("bottom_bar") {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(Icons.Filled.AccountCircle, "AccountIcon")
                    }
                    IconButton(onClick = {
                        mainNavController.navigate("room_test") {
                            popUpTo("bottom_bar") {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(Icons.Filled.KeyboardArrowRight, "GameScreen")
                    }
                }
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = Color.White,
                //elevation = 10.dp
            )
        },
        // Будет работать на всех вкладках, а мне надо отдельно
        /*floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(GameEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add game"
                )
            }
        },*/
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                val backStackEntry by scaffoldNavController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route


                NavigationBarItem(
                    selected = currentRoute == "tab_1",  // if
                    onClick = {
                        scaffoldNavController.navigate("tab_1")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_view_list_24),
                            contentDescription = "Default tab1 icon"
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == "tab_2",  // if
                    onClick = {
                        scaffoldNavController.navigate("tab_2")
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_announcement_24),
                            contentDescription = "Default tab2 icon"
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == "tab_3",  // if
                    onClick = {
                        scaffoldNavController.navigate("tab_3")
                    },
                    icon = {
                        if (userData?.profilePictureUrl != null) {
                            AsyncImage(
                                model = userData.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                contentDescription = "Default profile icon"
                            )
                        }
                    }
                )
            }
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ScaffoldNavGraph(
                    userData,
                    onSignOut,
                    scaffoldNavController = scaffoldNavController
                )
            }
        }
    )
}