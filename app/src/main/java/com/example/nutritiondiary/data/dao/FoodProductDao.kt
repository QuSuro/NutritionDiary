package com.example.nutritiondiary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nutritiondiary.data.entity.FoodProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodProductDao {
    @Insert
    suspend fun insertProduct(product: FoodProduct)

    @Query("SELECT * FROM food_product WHERE name LIKE '%' || :query || '%'")
    fun searchByName(query: String): Flow<List<FoodProduct>>

    @Query("SELECT * FROM food_product WHERE barcode = :code")
    suspend fun findByBarcode(code: String): FoodProduct?

    @Query("SELECT * FROM food_product ORDER BY name ASC")
    fun getAllProducts(): Flow<List<FoodProduct>>
}