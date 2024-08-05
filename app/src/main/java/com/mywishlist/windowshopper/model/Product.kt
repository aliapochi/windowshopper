package com.mywishlist.windowshopper.model

import androidx.annotation.DrawableRes


data class Product(
    val name: String,
    @DrawableRes val image: Int,
    val description: String,
    val liked: Boolean,
    )
