package com.example.singin_screen.data.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.singin_screen.data.model.Products

@Dao
interface ProductsDao {
    @Query("SELECT * FROM Products")
    suspend fun getAll(): List<Products>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALL(products: List<Products>)
}