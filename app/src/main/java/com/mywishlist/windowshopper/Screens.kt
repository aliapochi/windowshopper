package com.mywishlist.windowshopper

sealed class Screens(val screen: String) {
    data object HomeScreen: Screens("home")
    data object WishlistScreenWithShare: Screens("wishlist")
    data object SettingsScreen: Screens("settings")
}