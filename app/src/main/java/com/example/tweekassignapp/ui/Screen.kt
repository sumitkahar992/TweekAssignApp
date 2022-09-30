package com.example.tweekassignapp.ui

sealed class Screen(val route: String){

    object News : Screen("news_screen")
    object MyKits : Screen("my kits_screen")
    object Home : Screen("home_screen")
    object Leaderboard : Screen("leaderboard_screen")
    object Account : Screen("account_screen")

    object List100: Screen("list_100")
    object Cat : Screen("cat_screen")
}
