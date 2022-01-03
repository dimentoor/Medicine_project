package com.example.singin_screen.data.model.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: ProductsDatabase? = null

    fun provideDatabse(context: Context): ProductsDatabase{
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            ProductsDatabase::class.java,"products_app_database"
        )
            .build()
            .also { db = it }
    }
}