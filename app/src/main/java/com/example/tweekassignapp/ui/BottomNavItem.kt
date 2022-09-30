package com.example.tweekassignapp.ui

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title : String,
    val image : ImageVector,
    val route : String
){
    object News : BottomNavItem(
        title = "News",
        image = Icons.Outlined.Home,
        route = Screen.News.route
    )
    object MyKits : BottomNavItem(
        title = "My kits",
        image = Icons.Outlined.Search,
        route = Screen.MyKits.route
    )
    object Home : BottomNavItem(
        title = "Home",
        image = Icons.Outlined.Person,
        route = Screen.Home.route
    )
    object Leaderboard : BottomNavItem(
        title = "Leaderboard",
        image = Icons.Outlined.Settings,
        route = Screen.Leaderboard.route
    )
    object Account : BottomNavItem(
        title = "Account",
        image = Icons.Outlined.Favorite,
        route = Screen.Account.route
    )
}
