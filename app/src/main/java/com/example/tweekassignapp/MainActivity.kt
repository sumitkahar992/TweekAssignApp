package com.example.tweekassignapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tweekassignapp.app.theme.TweekAssignAppTheme
import com.example.tweekassignapp.ui.AppBar
import com.example.tweekassignapp.ui.BottomBar
import com.example.tweekassignapp.ui.NavGraph
import com.example.tweekassignapp.ui.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TweekAssignAppTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    val bottomBarState = rememberSaveable { mutableStateOf(false) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    when (navBackStackEntry?.destination?.route) {
        Screen.Home.route -> bottomBarState.value = true }

    Scaffold(
        topBar = {
//            AppBar()
                 },
        bottomBar = {
            Column {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState.value
                )
            }
        },
        content = {
            NavGraph(navController = navController, paddingValues = it)
        }
    )
}












































