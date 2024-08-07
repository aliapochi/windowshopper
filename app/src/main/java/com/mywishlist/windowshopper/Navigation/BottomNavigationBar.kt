package com.mywishlist.windowshopper.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mywishlist.windowshopper.model.BottomNavItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("wishlist", "Wishlist", Icons.Default.Favorite),
        BottomNavItem("settings", "Settings", Icons.Default.Settings)
    )
    NavigationBar {
        items.forEach {item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null)},
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {}
            )
        }


    }

}