package com.example.tweekassignapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tweekassignapp.cat.CatScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues()
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
            LeaderBoardScreen(navController)
        }
        composable(route = Screen.News.route) {
            NewsScreen()
        }
        composable(route = Screen.Account.route) {
            AccountScreen(navController)
        }




        composable(route = Screen.List100.route) {
            ItemsList100()
        }

        composable(route = Screen.Cat.route) {
            CatScreen()
        }

        composable(route = Screen.TempConverter.route) {
            val viewModel: ConversionViewModel = viewModel()
            TempConverterScreen(navController, viewModel)
        }

        composable(route = Screen.ResultScreen.route) {
            val viewModel: ConversionViewModel = viewModel()
            ResultScreen(navController, viewModel)
        }


    }
}