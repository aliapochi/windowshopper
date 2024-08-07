package com.mywishlist.windowshopper.data

import com.mywishlist.windowshopper.R
import com.mywishlist.windowshopper.model.Product

class DataSource() {
    fun loadProducts(): List<Product>{
        return listOf(
            Product(
                name = "Gaming Laptop",
                image = R.drawable.gaming_laptop,  // Image: gaming_laptop.png
                description = "High-performance gaming laptop with RTX graphics and 16GB RAM.",
                liked = false
            ),
            Product(
                name = "Wireless Headphones",
                image = R.drawable.wireless_headphones,  // Image: wireless_headphones.png
                description = "Noise-cancelling wireless headphones with 20 hours of battery life.",
                liked = false
            ),
            Product(
                name = "Electric Kettle",
                image = R.drawable.electric_kettle,  // Image: electric_kettle.png
                description = "Fast-boiling electric kettle with auto shut-off and 1.7L capacity.",
                liked = false
            ),
            Product(
                name = "Running Shoes",
                image = R.drawable.running_shoes,  // Image: running_shoes.png
                description = "Lightweight and comfortable running shoes with breathable material.",
                liked = false
            ),
            Product(
                name = "Coffee Maker",
                image = R.drawable.coffee_maker,  // Image: coffee_maker.png
                description = "Automatic coffee maker with programmable settings and 12-cup capacity.",
                liked = false
            ),
            Product(
                name = "Fitness Tracker",
                image = R.drawable.fitness_tracker,  // Image: fitness_tracker.png
                description = "Fitness tracker with activity tracking, sleep monitoring, and water resistance.",
                liked = false
            )
        )
    }
}