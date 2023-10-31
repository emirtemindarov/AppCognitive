package com.emirtemindarov.tablesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emirtemindarov.tablesapp.bottom_nav_bar.MainNavGraph
import com.emirtemindarov.tablesapp.ui.theme.TablesAppTheme
import com.emirtemindarov.tablesapp.bottom_nav_bar.MainNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TablesAppTheme {


                MainNavGraph()





            }
        }
    }
}