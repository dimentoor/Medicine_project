package com.example.singin_screen.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.singin_screen.data.model.Products

@Database(
    entities = [Products::class],
    version = 1
)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
    }