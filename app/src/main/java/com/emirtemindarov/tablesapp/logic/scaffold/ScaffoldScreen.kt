package com.emirtemindarov.tablesapp.logic.scaffold

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.helpers.ContextMenuWrapper
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
    gamesState: GamesState,
    onEvent: (GameEvent) -> Unit,
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
                "Выход из аккаунта",
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

    val backStackEntry by scaffoldNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (currentRoute == "tab_3") {
                        IconButton(onClick = {
                            scaffoldNavController.popBackStack()
                        }, modifier = Modifier.wrapContentSize()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                contentDescription = "Account top-left back icon",
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }
                },

                title = {
                    Row {
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = when (currentRoute) {
                                "tab_1" -> "Задачи"
                                "tab_2" -> "Группы"
                                "tab_3" -> ""
                                else -> "Ошибка"
                            }
                        )
                    }
                },

                actions = {
                    // Выпадающее меню справа сверху для вкладки 1
                    if (currentRoute == "tab_1") {

                            ContextMenuWrapper(
                                dropdownMenuItems = listOf(
                                    ContextMenuItemContent(
                                        item = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_announcement_24),
                                                    contentDescription = "Dropdown item"
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(text = "Сортировать")
                                            }
                                        },
                                        action = {
                                            Toast.makeText(
                                                applicationContext,
                                                "Cработало первое действие",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        },
                                    ),
                                    ContextMenuItemContent(
                                        item = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.baseline_announcement_24),
                                                    contentDescription = "Dropdown item"
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(text = "Руководство")
                                            }
                                        },
                                        action = {
                                            Toast.makeText(
                                                applicationContext,
                                                "Cработало второе действие",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        },
                                        divider = true
                                    )
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_sort_24),
                                    contentDescription = "GameScreen top-right sort icon"
                                )
                            }

                    }

                    // Выпадающее меню справа сверху для вкладки 2
                    if (currentRoute == "tab_2") {

                        ContextMenuWrapper(
                            dropdownMenuItems = listOf(
                                ContextMenuItemContent(
                                    item = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_announcement_24),
                                                contentDescription = "Dropdown item"
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(text = "Сортировать")
                                        }
                                    },
                                    action = {
                                        Toast.makeText(
                                            applicationContext,
                                            "Cработало первое действие",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                ),
                                ContextMenuItemContent(
                                    item = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_announcement_24),
                                                contentDescription = "Dropdown item"
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(text = "Руководство")
                                        }
                                    },
                                    action = {
                                        Toast.makeText(
                                            applicationContext,
                                            "Cработало второе действие",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },
                                    divider = true
                                )
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_sort_24),
                                contentDescription = "GameScreen top-right sort icon"
                            )
                        }


                    }



                    // Доп. кнопки перехода для отладки
                    if (false) {
                        IconButton(onClick = {
                            mainNavController.navigate("auth") {
                                popUpTo("bottom_bar") {
                                    inclusive = true
                                }
                            }
                        }) {
                            Icon(Icons.Filled.AccountCircle, "AccountIcon")
                        }
                    }
                    if (false) {
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
                }
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = Color.White,
                //elevation = 10.dp
            )
        },

        // Будет работать на всех вкладках, а надо отдельно
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
            if (currentRoute != "tab_3") {
                NavigationBar(containerColor = Color.Black) {

                    NavigationBarItem(
                        selected = currentRoute == "tab_1",  // if    TODO переделать
                        onClick = {
                            if (currentRoute != "tab_1") {
                                scaffoldNavController.navigate("tab_1")
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_view_list_24),
                                contentDescription = "Default tab1 icon"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "tab_2",  // if    TODO переделать
                        onClick = {
                            if (currentRoute != "tab_2") {
                                scaffoldNavController.navigate("tab_2")
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_announcement_24),
                                contentDescription = "Default tab2 icon"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "tab_3",  // if    TODO переделать
                        onClick = {
                            if (currentRoute != "tab_3") {
                                scaffoldNavController.navigate("tab_3")
                            }
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
            }
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ScaffoldNavGraph(
                    userData,
                    onSignOut,
                    gamesState,
                    onEvent,
                    scaffoldNavController,
                    mainNavController
                )
            }
        }
    )
}