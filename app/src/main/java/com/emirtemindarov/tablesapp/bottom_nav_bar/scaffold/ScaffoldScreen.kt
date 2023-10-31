package com.emirtemindarov.tablesapp.bottom_nav_bar.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emirtemindarov.tablesapp.R

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
                }
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = Color.White,
                //elevation = 10.dp
            )
        },
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