package com.example.tweekassignapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeContent()
        }
         composable(route = Screen.MyKits.route) {
            MyKitScreen()
        }
         composable(route = Screen.Leaderboard.route) {
            BottoSheetTwo()
        }
         composable(route = Screen.News.route) {
             NewsScreen()
        }
         composable(route = Screen.Account.route) {
             AccountScreen()
        }

    }
}