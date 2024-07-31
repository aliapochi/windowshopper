package com.mywishlist.windowshopper.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Product::class], version = 4, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object{
        @Volatile
         private var Instance: ProductDatabase? = null
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create new table with the updated schema
                db.execSQL("CREATE TABLE products_new (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, image INTEGER NOT NULL, description TEXT NOT NULL, liked INTEGER NOT NULL, isDefault INTEGER NOT NULL)")

                // Copy the data from the old table to the new table, initializing image to 0
                db.execSQL("INSERT INTO products_new (id, name, image, description, liked, isDefault) SELECT id, name, 0, description, liked, isDefault FROM products")

                // Drop the old table
                db.execSQL("DROP TABLE products")

                // Rename the new table to the old table name
                db.execSQL("ALTER TABLE products_new RENAME TO products")
            }
        }
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Step 1: Create a new table with the updated schema
                db.execSQL("""
            CREATE TABLE products_new (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL,
                image TEXT NOT NULL, -- Changed from INTEGER to TEXT
                description TEXT NOT NULL,
                liked INTEGER NOT NULL,
                isDefault INTEGER NOT NULL
            )
        """.trimIndent())

                // Step 2: Copy data from the old table to the new table
                db.execSQL("""
            INSERT INTO products_new (id, name, image, description, liked, isDefault)
            SELECT id, name, CAST(image AS TEXT), description, liked, isDefault FROM products
        """.trimIndent())

                // Step 3: Drop the old table
                db.execSQL("DROP TABLE products")

                // Step 4: Rename the new table to the old table name
                db.execSQL("ALTER TABLE products_new RENAME TO products")
            }
        }


        fun getDatabase(context: Context): ProductDatabase{
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, ProductDatabase::class.java,
                    "product_database").addMigrations(MIGRATION_3_4)
                    .build()
                    .also{ Instance = it}
            }

        }
    }


}