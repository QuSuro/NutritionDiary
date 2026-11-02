package com.example.nutritiondiary.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_entry",
    foreignKeys = [
        ForeignKey(
            entity = FoodProduct::class,
            parentColumns = ["productId"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["productId"])]
)
data class MealEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Int = 0,
    val productId: Int,
    val mealType: String,
    val portionGrams: Double,
    val calories: Double,
    val timestamp: Long
)