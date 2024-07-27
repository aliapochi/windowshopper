package com.mywishlist.windowshopper.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
         private var Instance: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, ProductDatabase::class.java,
                    "product_database")
                    .build()
                    .also{ Instance = it
                        Log.d("ProductDatabase", "Database initialized")}
            }

        }
    }


}