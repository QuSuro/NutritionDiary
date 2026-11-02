package com.example.nutritiondiary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val name: String,
    val currentWeight: Double,
    val targetWeight: Double,
    val dailyCalorieGoal: Int
)