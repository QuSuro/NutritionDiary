package com.example.nutritiondiary.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food_product",
    indices = [Index(value = ["name"]), Index(value = ["barcode"])]
)
data class FoodProduct(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val name: String,
    val caloriesPer100g: Double,
    val proteinPer100g: Double,
    val fatPer100g: Double,
    val carbsPer100g: Double,
    val barcode: String? = null
)