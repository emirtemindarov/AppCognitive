package com.emirtemindarov.tablesapp.logic.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emirtemindarov.tablesapp.R
import com.emirtemindarov.tablesapp.games.AddGameDialog
import com.emirtemindarov.tablesapp.games.GamesState
import com.emirtemindarov.tablesapp.games.GameEvent
import com.emirtemindarov.tablesapp.games.GamesSortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(
    mainNavController: NavHostController
) {
    val scaffoldNavController = rememberNavController()

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
                    icon = { R.drawable.baseline_table_chart_24 }
                )
                NavigationBarItem(
                    selected = currentRoute == "tab_2",  // if
                    onClick = {
                        scaffoldNavController.navigate("tab_2")
                    },
                    icon = { R.drawable.baseline_table_chart_24 }
                )
                NavigationBarItem(
                    selected = currentRoute == "tab_3",  // if
                    onClick = {
                        scaffoldNavController.navigate("tab_3")
                    },
                    icon = { R.drawable.baseline_table_chart_24 }
                )
            }
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ScaffoldNavGraph(scaffoldNavController = scaffoldNavController)
            }
        }
    )
}