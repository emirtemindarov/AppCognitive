package com.emirtemindarov.tablesapp.logic.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
fun LoginScreen(
    mainNavController: NavHostController
) {
    val loginNavController = rememberNavController()

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
                        mainNavController.navigate("bottom_bar") {
                            popUpTo("auth") {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(Icons.Filled.AccountBox, "AccountIcon")
                    }
                }
                //backgroundColor = MaterialTheme.colors.primary,
                //contentColor = Color.White,
                //elevation = 10.dp
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                val backStackEntry by loginNavController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route


                NavigationBarItem(
                    selected = currentRoute == "login",  // if
                    onClick = {
                        loginNavController.navigate("login")
                    },
                    icon = { R.drawable.baseline_table_chart_24 }
                )
                NavigationBarItem(
                    selected = currentRoute == "registration",  // if
                    onClick = {
                        loginNavController.navigate("registration")
                    },
                    icon = { R.drawable.baseline_table_chart_24 }
                )
                NavigationBarItem(
                    selected = currentRoute == "change_password",  // if
                    onClick = {
                        loginNavController.navigate("change_password")
                    },
                    icon = { R.drawable.baseline_table_chart_24 }
                )
            }
        }, content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                LoginNavGraph(loginNavController = loginNavController)
            }
        }
    )
}
