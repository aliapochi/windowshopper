package com.mywishlist.windowshopper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val liked: Boolean,
    val isDefault: Boolean = false
    )
