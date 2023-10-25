package com.emirtemindarov.tablesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emirtemindarov.tablesapp.ui.theme.TablesAppTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.emirtemindarov.tablesapp.bottom_nav_bar.BottomBarNavGraph
import com.emirtemindarov.tablesapp.bottom_nav_bar.BottomBarViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TablesAppTheme {





                val bottomNavController = rememberNavController()


                Scaffold(
                    bottomBar = {
                        NavigationBar(containerColor = Color.Black) {
                            val backStackEntry by bottomNavController.currentBackStackEntryAsState()
                            val currentRoute = backStackEntry?.destination?.route


                            NavigationBarItem(
                                selected = currentRoute == "screen_1",  // if
                                onClick = {
                                    bottomNavController.navigate("screen_1")
                                },
                                icon = { R.drawable.baseline_table_chart_24 }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "screen_2",  // if
                                onClick = {
                                    bottomNavController.navigate("screen_2")
                                },
                                icon = { R.drawable.baseline_table_chart_24 }
                            )
                            NavigationBarItem(
                                selected = currentRoute == "screen_3",  // if
                                onClick = {
                                    bottomNavController.navigate("screen_3")
                                },
                                icon = { R.drawable.baseline_table_chart_24 }
                            )
                        }
                    }, content = { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            BottomBarNavGraph(bottomBarNavController = bottomNavController)
                        }
                    }
                )



            }
        }
    }
}

